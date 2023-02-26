from Crypto.Util.number import *
from math import isqrt
from challenges import chall1_secret

class RSAServe:
    def __init__(self) -> None:
        def create_keypair(size):
            while True:
                p = getPrime(size // 2)
                q = getPrime(size // 2)
                if q < p < 2*q:
                    break
            N = p*q
            phi = (p-1)*(q-1)
            max_d = isqrt(isqrt(N)) // 3
            max_d_bits = max_d.bit_length() - 1
            while True:
                d = getRandomNBitInteger(max_d_bits)
                try:
                    e = int(inverse(d, phi))
                except ZeroDivisionError:
                    continue
                if (e * d) % phi == 1:
                    break
            return N, e, d
        self.N, self.e, self.d = create_keypair(1024)
        self.m = chall1_secret
    
    def encrypt(self):
        m_ = bytes_to_long(self.m)
        c = pow(m_ ,self.e, self.N)
        return hex(c)

    def check(self, msg):
        return msg == self.m

    def pubkey(self):
        return {"N":self.N, "e":self.e}