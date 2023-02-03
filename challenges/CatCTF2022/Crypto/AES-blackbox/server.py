#!/usr/bin/python
from hashlib import sha256
import socketserver
from secret import flag
import signal
import string
import random
import os
from secret import blackbox_enc ,flag
from AES_2r import AES_2r_enc
from random import randint
from Crypto.Util.number import long_to_bytes,bytes_to_long


class Task(socketserver.BaseRequestHandler):
    def _recvall(self):
        BUFF_SIZE = 2048
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

    def recv(self, prompt=b'[-] '):
        self.send(prompt, newline=False)
        return self._recvall()

    def proof_of_work(self):
        random.seed(os.urandom(8))
        proof = ''.join(
            [random.choice(string.ascii_letters+string.digits) for _ in range(20)])
        _hexdigest = sha256(proof.encode()).hexdigest()
        self.send(f"[+] sha256(XXXX+{proof[4:]}) == {_hexdigest}".encode())
        x = self.recv(prompt=b'[+] Plz tell me XXXX: ')
        if len(x) != 4 or sha256(x+proof[4:].encode()).hexdigest() != _hexdigest:
            return False
        return True

    def handle(self):
        if not self.proof_of_work():
            self.send(b'[!] Wrong!')
            return
        signal.alarm(60)
        for i in range(20):
            self.send(b"round %d"%i)
            choice=randint(0,1)
            key=[bytes_to_long(bytes([randint(0,2**8-1)for _ in range(16)])) for j in range(2)]
            f=(AES_2r_enc if choice else blackbox_enc)
            for times in range(4):
                self.send(b"[+] plz input a block to enc:")
                plaintext=bytes.fromhex(self.recv().decode())
                plaintext=plaintext.rjust(16,b"\00")[:16]
                ciphertext=f(plaintext,key)
                ciphertext=hex(bytes_to_long(ciphertext))
                self.send(ciphertext.encode())
            self.send(b"[+] now plz tell me the choice:")
            ans=self.recv()
            ans=int(ans,2)
            if ans!=choice:
                self.send(b"sorry,you are wrong!")
                return
            else:
                self.send(b"next try!")
        self.send(b'here is your flag')
        self.send(flag)


class ThreadedServer(socketserver.ThreadingMixIn, socketserver.TCPServer):
    pass


class ForkedServer(socketserver.ForkingMixIn, socketserver.TCPServer):
    pass


if __name__ == "__main__":
    HOST, PORT = '0.0.0.0', 10001
    server = ForkedServer((HOST, PORT), Task)
    server.allow_reuse_address = True
    print(HOST, PORT)
    server.serve_forever()

