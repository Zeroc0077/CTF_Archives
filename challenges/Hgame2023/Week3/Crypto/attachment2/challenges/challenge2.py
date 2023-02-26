from Crypto.Util.number import *
from challenges import chall2_secret

def next_prime(p):
    k=1
    while True:
        if isPrime(p+k):
            return p+k
        k+=1

class RSAServe:
    def __init__(self) -> None:
        def creat_keypair(nbits, beta):
            p = getPrime(nbits // 2)
            q = next_prime(p+getRandomNBitInteger(int(nbits*beta)))
            N = p*q
            phi = (p-1)*(q-1)
            while True:
                e = getRandomNBitInteger(16)
                if GCD(e, phi) == 2:
                    break
            d = inverse(e, phi)
            return N, e, d
        self.N, self.e, self.d = creat_keypair(1024, 0.25)
        self.m = chall2_secret

    def encrypt(self):
        m_ = bytes_to_long(self.m)
        c = pow(m_, self.e, self.N)
        return hex(c)

    def check(self, msg):
        return msg == self.m

    def pubkey(self):
        return {"N":self.N, "e":self.e}
