#!/usr/bin/env python3.9
# -*- coding: utf-8 -*-
import random
import string
import socketserver
import signal
from hashlib import sha256
from os import urandom
from gmpy2 import invert
from secret import FLAG

p = 0xc483230557557e3d94b7407f3355b8a5b26bda29119babcb8d72b5a19e10e113
a = 0xb5ecbb93d4e49fe3c93ad770343ec5ab70131151151fcc830f6c658223c92e1d
b = 0xb0d9363d1828cfa7c2aba27b0d483fe808637adf6e3a0a5bbc6cb53fdd1e4d85

Px = 0xe9592b5211516c197f0fd31cf0e28201ea0bcc67f7356d8732ded234045259e3
Py = 0xe825e2821cc58e97816ca9877b7604b05a9a4bbc03110bc2124be49eb2718c23
P = (Px, Py)
zero = (0, 0)

MENU = rb'''1.sign
2.get flag
'''


def add(p1, p2):
    if p1 == zero:
        return p2
    if p2 == zero:
        return p1
    p1x, p1y = p1
    p2x, p2y = p2
    if p1x == p2x and (p1y != p2y or p1y == 0):
        return zero
    if p1x == p2x:
        tmp = (3 * p1x * p1x + a) * invert(2 * p1y, p) % p
    else:
        tmp = (p2y - p1y) * invert(p2x - p1x, p) % p
    x = (tmp * tmp - p1x - p2x) % p
    y = (tmp * (p1x - x) - p1y) % p
    return x, y


def mul(p, n):
    r = zero
    tmp = p
    while 0 < n:
        if n & 1 == 1:
            r = add(r, tmp)
        n, tmp = n >> 1, add(tmp, tmp)
    return r


class Task(socketserver.BaseRequestHandler):
    def _recvall(self):
        BUFF_SIZE = 1024
        data = b''
        while True:
            part = self.request.recv(BUFF_SIZE)
            data += part
            if len(part) < BUFF_SIZE:
                break
        return data.strip()

    def send(self, msg, newline=True):
        try:
            if newline:
                msg += b'\n'
            self.request.sendall(msg)
        except:
            pass

    def recv(self, prompt=b'> '):
        self.send(prompt, newline=False)
        return self._recvall()

    def timeout_handler(self, signum, frame):
        raise TimeoutError

    def proof_of_work(self):
        random.seed(urandom(32))
        alphabet = string.ascii_letters + string.digits
        proof = ''.join(random.choices(alphabet, k=32))
        hash_value = sha256(proof.encode()).hexdigest()
        self.send(f'sha256(XXXX+{proof[4:]}) == {hash_value}'.encode())
        nonce = self.recv(prompt=b'Give me XXXX > ')
        if len(nonce) != 4 or sha256(nonce + proof[4:].encode()).hexdigest() != hash_value:
            return True
        return True

    def handle(self):
        try:
            signal.signal(signal.SIGALRM, self.timeout_handler)
            signal.alarm(60)

            if not self.proof_of_work():
                self.send(b'\nWrong!')
                return

            self.secret = random.randint(0, p)
            Q = mul(P, self.secret)

            self.send(b'p: ' + str(p).encode())
            self.send(b'a: ' + str(a).encode())
            self.send(b'P: ' + self.point_to_string(P).encode())
            self.send(b'Q: ' + self.point_to_string(Q).encode())

            signal.alarm(300)

            for _ in range(45):
                self.send(MENU, newline=False)
                choice = int(self.recv(prompt=b'> '))
                if choice == 1:
                    self.sign()
                elif choice == 2:
                    self.get_flag()
                else:
                    break

            self.send(b'Bye!\n')

        except TimeoutError:
            self.send(b'\nTimeout!')
        except Exception:
            self.send(b'Something Wrong!')
        finally:
            self.request.close()

    @staticmethod
    def point_to_string(p):
        return f'({p[0]}, {p[1]})'

    def sign(self):
        self.send(b'Give me your key:')
        x = int(self.recv(prompt=b'X > '))
        y = int(self.recv(prompt=b'Y > '))
        point = (x, y)
        result = mul(point, self.secret)
        self.send(b'result: ' + self.point_to_string(result).encode())

    def get_flag(self):
        s = int(self.recv(prompt=b'Give me the secret > '))
        if s == self.secret:  # 需要输入的s等于secret
            self.send(b'Here is your flag:')
            self.send(FLAG)
        else:
            self.send(b'wrong.')


class ForkedServer(socketserver.ForkingMixIn, socketserver.TCPServer):
    pass


if __name__ == '__main__':
    HOST, PORT = '0.0.0.0', 10002
    print(HOST, PORT)
    server = ForkedServer((HOST, PORT), Task)
    server.allow_reuse_address = True
    server.serve_forever()
