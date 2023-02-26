// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.13;

import "./openzeppelin-contracts/interfaces/IERC20.sol";
import "./openzeppelin-contracts/interfaces/IERC3156FlashBorrower.sol";
import "./openzeppelin-contracts-upgradeable/proxy/utils/Initializable.sol";
import "./openzeppelin-contracts-upgradeable/proxy/utils/UUPSUpgradeable.sol";
import "./openzeppelin-contracts-upgradeable/access/OwnableUpgradeable.sol";

import "./Diamond.sol";
import "./Burner.sol";
import "./SaltyPretzel.sol";

contract Vault is Initializable, UUPSUpgradeable, OwnableUpgradeable {

    uint constant public AUTHORITY_THRESHOLD = 10_000 ether;

    Diamond diamond;
    SaltyPretzel saltyPretzel;

    function initialize(address diamond_, address saltyPretzel_) public initializer {
        __Ownable_init();
        diamond = Diamond(diamond_);
        saltyPretzel = SaltyPretzel(saltyPretzel_);
    }

    function governanceCall(bytes calldata data) external {
        require(msg.sender == owner() || saltyPretzel.getCurrentVotes(msg.sender) >= AUTHORITY_THRESHOLD);
        (bool success,) = address(this).call(data);
        require(success);
    }

    function flashloan(address token, uint amount, address receiver) external {
        uint balanceBefore = IERC20(token).balanceOf(address(this));
        IERC20(token).transfer(receiver, amount);
        IERC3156FlashBorrower(receiver).onFlashLoan(msg.sender, token, amount, 0, "");
        uint balanceAfter = IERC20(token).balanceOf(address(this));
        require(balanceBefore == balanceAfter);
    }

    function burn(address token, uint amount) external {
        require(msg.sender == owner() || msg.sender == address(this));
        Burner burner = new Burner();
        IERC20(token).transfer(address(burner), amount);
        burner.destruct();
    }
    
    function _authorizeUpgrade(address) internal override view {
        require(msg.sender == owner() || msg.sender == address(this));
        require(IERC20(diamond).balanceOf(address(this)) == 0);
    }
}
