// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.13;

import "./VaultFactory.sol";
import "./Vault.sol";
import "./Diamond.sol";
import "./SaltyPretzel.sol";

contract Setup {

    uint constant public DIAMONDS = 100;
    uint constant public SALTY_PRETZELS = 100 ether;
    bool claimed;

    VaultFactory public vaultFactory;
    Vault public vault;
    Diamond public diamond;
    SaltyPretzel public saltyPretzel;

    constructor () {
        vaultFactory = new VaultFactory();
        vault = vaultFactory.createVault(keccak256("The tea in Nepal is very hot."));
        diamond = new Diamond(DIAMONDS);
        saltyPretzel = new SaltyPretzel();
        vault.initialize(address(diamond), address(saltyPretzel));
        diamond.transfer(address(vault), DIAMONDS);
    }

    function claim() external {
        require(!claimed);
        claimed = true;
        saltyPretzel.mint(msg.sender, SALTY_PRETZELS);
    }

    function isSolved() external view returns (bool) {
        return diamond.balanceOf(address(this)) == DIAMONDS;
    }
}