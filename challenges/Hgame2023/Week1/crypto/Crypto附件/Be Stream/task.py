from flag import flag
assert type(flag) == bytes

key = [int.from_bytes(b"Be water", 'big'), int.from_bytes(b"my friend", 'big')]

def stream(i):
    if i==0:
        return key[0]
    elif i==1:
        return key[1]
    else:
        return (stream(i-2)*7 + stream(i-1)*4)

enc = b""
for i in range(len(flag)):
    water = stream((i//2)**6) % 256
    enc += bytes([water ^ flag[i]])

print(enc)
# b'\x1a\x15\x05\t\x17\t\xf5\xa2-\x06\xec\xed\x01-\xc7\xcc2\x1eXA\x1c\x157[\x06\x13/!-\x0b\xd4\x91-\x06\x8b\xd4-\x1e+*\x15-pm\x1f\x17\x1bY'
