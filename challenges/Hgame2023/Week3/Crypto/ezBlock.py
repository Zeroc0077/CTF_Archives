from secret import flag


def s_substitute(m):
    c = 0
    s_box = {0: 0x6, 1: 0x4, 2: 0xc, 3: 0x5, 4: 0x0, 5: 0x7, 6: 0x2, 7: 0xe, 8: 0x1, 9: 0xf, 10: 0x3, 11: 0xd, 12: 0x8,
             13: 0xa, 14: 0x9, 15: 0xb}
    for i in range(0, 16, 4):
        t = (m >> i) & 0xf
        t = s_box[t]
        c += t << i
    return c


def enc(m, key):
    n = len(key) # 5
    t = m
    for i in range(n - 1):
        t = t ^ key[i]
        t = s_substitute(t)
    c = t ^ key[n - 1]
    return c


f = flag[6:-1]
assert flag == 'hgame{' + f + '}'
key = [int(i, 16) for i in f.split('_')]
print(len(key))
m_list = [i * 0x1111 for i in range(16)]
c_list = [enc(m, key) for m in m_list]
print(c_list)

# 5
# [28590, 33943, 30267, 5412, 11529, 3089, 46924, 59533, 12915, 37743, 64090, 53680, 18933, 49378, 23512, 44742]