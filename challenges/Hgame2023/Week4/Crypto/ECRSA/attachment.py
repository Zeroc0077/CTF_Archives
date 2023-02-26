from sage.all import *
from sage.all_cmdline import *
from Crypto.Util.number import *
from secret import flag

Nbits = 512
x = bytes_to_long(flag)
f = open('./output', 'w')

def gen_pubkey(Nbits):
    p = getPrime(Nbits // 2)
    q = getPrime(Nbits // 2)
    n = p*q
    while True:
        a = getRandomInteger(Nbits // 2)
        b = getRandomInteger(Nbits // 2)
        if gcd(4*a**3 + 27*b**2, n) == 1:
            break
    E = EllipticCurve(Zmod(n), [a, b])
    e = getPrime(64)
    f.write(f"p={p}\nq={q}\n")
    return n, E, e

n, E, e = gen_pubkey(Nbits)
pt = E.lift_x(Integer(x))
ct = pt * e
f.write(f"n = {n}\na = {E.a4()}\nb = {E.a6()}\ne = {e}\n")
f.write(f"ciphertext = {long_to_bytes(int(ct.xy()[0]))}\n")

# p = 115192265954802311941399019598810724669437369433680905425676691661793518967453
# q = 109900879774346908739236130854229171067533592200824652124389936543716603840487
# n = 12659731371633323406361071735480743870942884407511647144758055911931321534333057725377899993936046070028289182446615763391740446071787318153462098556669611
# a = 34573016245861396068378040882622992245754693028152290874131112955018884485688
# b = 103282137133820948206682036569671566996381438254897510344289164039717355513886
# e = 11415307674045871669
# ciphertext = b'f\xb1\xae\x08`\xe8\xeb\x14\x8a\x87\xd6\x18\x82\xaf1q\xe4\x84\xf0\x87\xde\xedF\x99\xe0\xf7\xdcH\x9ai\x04[\x8b\xbbHR\xd6\xa0\xa2B\x0e\xd4\xdbr\xcc\xad\x1e\xa6\xba\xad\xe9L\xde\x94\xa4\xffKP\xcc\x00\x907\xf3\xea'


