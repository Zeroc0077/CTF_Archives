from Crypto.Util.number import *
from challenges import chall3_secret

class RSAServe:
    def __init__(self) -> None:
        def create_keypair(nbits):
            p = getPrime(nbits // 2)
            q = getPrime(nbits // 2)
            N = p*q
            phi = (p-1)*(q-1)
            e = 65537
            d = inverse(e, phi)
            leak = p >> 253
            return N, e, d, leak
        self.N, self.e, self.d, self.leak = create_keypair(1024)
        self.m = chall3_secret
    
    def encrypt(self):
        m_ = bytes_to_long(self.m)
        c = pow(m_, self.e, self.N)
        return hex(c)

    def check(self, msg):
        return msg == self.m

    def pubkey(self):
        return {"N":self.N, "e":self.e, "leak":self.leak}