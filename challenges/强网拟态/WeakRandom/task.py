#!/usr/bin/env python
import os, random, hashlib, string
from signal import alarm
from secret import flag


class WeakRandom:
    def __init__(self, seed, n, s):
        self.x = seed
        self.n = n
        self.s = s

    def next(self):
        x = int((self.x ** 2) // (10 ** (self.s // 2))) % self.n
        self.x = x
        high = (int(hashlib.sha256(str(x).encode()).hexdigest(), 16) >> 16) & (2 ** 16 - 1)
        low = x & (2 ** 16 - 1)
        result = high << 16 | low
        return result


def proof_of_work():
    random.seed(os.urandom(8))
    proof = ''.join([random.choice(string.ascii_letters + string.digits) for _ in range(20)])
    digest = hashlib.sha256(proof.encode()).hexdigest()
    print("sha256(XXXX+%s) == %s" % (proof[4:], digest))
    print("Give me XXXX:")
    x = input()
    if len(x) != 4 or hashlib.sha256((x + proof[4:]).encode()).hexdigest() != digest:
        return False
    return True


def main():
    alarm(60)
    if not proof_of_work():
        return
    alarm(100)
    print("Welcome to the predict game!")
    n = 10000000000
    s = 4
    seed = os.urandom(4)
    seed = int.from_bytes(seed, byteorder="big")
    r = WeakRandom(seed, n, s)
    count = 0
    for i in range(100):
        try:
            x = r.next()
            guess = int(input("Please your guess : "))
            if guess == x:
                print("Success!")
                count += 1
            else:
                print(f"Fail! The number is {x}")
            if count >= 20:
                print(f"You win! The flag is : {flag}")
        except:
            print("Error!")


if __name__ == "__main__":
    main()
