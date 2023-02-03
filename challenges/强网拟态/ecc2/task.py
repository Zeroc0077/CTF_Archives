from base64 import b64encode
from secret import flag
from Crypto.Cipher import AES
from Crypto.Util.Padding import pad
from EllipticCurve import Curve
from hashlib import sha384, sha256
from Crypto.Util.number import inverse
from random import randint

def sign(msg, privkey, curve, skip=-1):
    while True:
        e = int(sha384(msg).hexdigest(), 16)
        k = randint(1, curve.order - 1)
        t = randint(2**19, 2**20 - 1)
        t_ = inverse(t, curve.order)
        kk = k * t_ % curve.order
        R = curve.mult(t, curve.base)
        Q = curve.mult(kk, R, skip)
        r = Q[0].to_int()
        k_ = inverse(k, curve.order)
        s = k_ * (e + privkey * r) % curve.order
        if r != 0 and s != 0:
            return r, s


p = 0xfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffeffffffff0000000000000000ffffffff
a = -3
b = 0xb3312fa7e23ee7e4988e056be3f82d19181d9c6efe8141120314088f5013875ac656398d8a2ed19d2a85c8edd3ec2aef
order = 0xffffffffffffffffffffffffffffffffffffffffffffffffc7634d81f4372ddf581a0db248b0a77aecec196accc52973
x = 0xaa87ca22be8b05378eb1c71ef320ad746e1d3b628ba79b9859f741e082542a385502f25dbf55296c3a545e3872760ab7
y = 0x3617de4a96262c6f5d9e98bf9292dc29f8f41dbd289a147ce9da3113b5f0b8c00a60b1ce1d7e819d7a431d7c90ea0e5f
E = Curve(p, a, b, order, x, y)
skip = randint(0, 32)
privkey = randint(1, order)
pubkey = E.mult(privkey, E.base)
print("your public key: {}".format(pubkey))
menu = '''1.sign
2.get flag
'''
while True:
    print(menu)
    op = int(input(">").strip())
    if op == 1:
        msg = input("msg:").strip().encode()
        sig = sign(msg, privkey, E, skip)
        print(sig)
    elif op == 2:
        key = sha256(str(privkey).encode()).digest()
        aes = AES.new(key, AES.MODE_ECB)
        ct = b64encode(aes.encrypt(pad(flag, 16))).decode()
        print("flag: {}".format(ct))
    else:
        print("bye~")
        exit()
