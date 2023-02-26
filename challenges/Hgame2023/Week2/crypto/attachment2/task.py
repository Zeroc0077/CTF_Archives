import socketserver
import signal
from challenges.challenge1 import RSAServe as C0S
from challenges.challenge2 import RSAServe as C1S
from challenges.challenge3 import RSAServe as C2S
from challenges.challenge4 import RSAServe as C3S


FLAG = flag = b'hgame{This is a fake flag}'
SCORE = [0, 0, 0, 0]
BANNER = """
 ____  ____    _    
|  _ \/ ___|  / \   
| |_) \___ \ / _ \  
|  _ < ___) / ___ \ 
|_| \_\____/_/   \_\

Here are four challenges(1, 2, 3, 4), solve them all then you can get flag.
"""
MEMU = """
/----------------------------\\
|          options           |
| 1. get public key          |
| 2. get cipher text         |
| 3. check                   |
\\---------------------------/
"""


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

    def recv(self, prompt=b'> '):
        self.send(prompt, newline=False)
        return self._recvall()

    def timeout_handler(self, signum, frame):
        raise TimeoutError

    def Serve(self, S):
        self.send(MEMU.encode())
        while True:
            option = self.recv()
            if option == b'1':
                pubkey = S.pubkey()
                for s in pubkey:
                    self.send(str(s).encode())
            elif option == b'2':
                c = S.encrypt()
                self.send(c.encode())
            elif option == b'3':
                usr_answer = self.recv(b"input your answer: ")
                return S.check(usr_answer)
            else:
                self.send(b"invaild option")

    def handle(self):
        signal.signal(signal.SIGALRM, self.timeout_handler)
        signal.alarm(600)

        self.send(BANNER.encode())
        while True:
            self.send(f'your score {sum(SCORE)}'.encode())
            if sum(SCORE) == 4:
                self.send(FLAG)
                break
            self.send(b'select challange')
            code = self.recv()
            if code == b'1':
                S = C0S()
                res = self.Serve(S)
                if res == True:
                    SCORE[0] = 1
            elif code == b'2':
                S = C1S()
                res = self.Serve(S)
                if res == True:
                    SCORE[1] = 1
            elif code == b'3':
                S = C2S()
                res = self.Serve(S)
                if res == True:
                    SCORE[2] = 1
            elif code == b'4':
                S = C3S()
                res = self.Serve(S)
                if res == True:
                    SCORE[3] = 1
            else:
                self.send(b'invaild input')

class ThreadedServer(socketserver.ThreadingMixIn, socketserver.TCPServer):
    pass


class ForkedServer(socketserver.ForkingMixIn, socketserver.TCPServer):
    pass


if __name__ == "__main__":
    HOST, PORT = '0.0.0.0', 10002
    server = ForkedServer((HOST, PORT), Task)
    server.allow_reuse_address = True
    print(HOST, PORT)
    server.serve_forever()
