from random import randint
from libnum import gcd, s2n

from secret import flag

plain = flag[6:-1]
assert flag == 'hgame{' + plain + '}'
v = bin(s2n(plain))[2:]
l = len(v)
a = [2 << i for i in range(l)]
m = randint(sum(a), 2 << l + 1)
w = randint(0, m)
assert gcd(w, m) == 1
b = [w * i % m for i in a]

c = 0
for i in range(l):
    c += b[i] * int(v[i])

print(f'm = {m}')
print(f'b0 = {b[0]}')
print(f'c = {c}')

# m = 1528637222531038332958694965114330415773896571891017629493424
# b0 = 69356606533325456520968776034730214585110536932989313137926
# c = 93602062133487361151420753057739397161734651609786598765462162
