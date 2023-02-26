from Crypto.Util.number import *
from challenges import chall1_secret
class RSAServe:
    def __init__(self) -> None:
        self.e = 65537
        self.p = getPrime(128)
        self.q = getPrime(100)
        self.r = getPrime(100)
        self.m = chall1_secret

    def encrypt(self):
        m_ = bytes_to_long(self.m)
        c = pow(m_, self.e, self.p*self.q*self.r)
        return hex(c)

    def check(self, msg):
        return msg == self.m

    def pubkey(self):
        return self.p*self.q*self.r, self.e, self.p
