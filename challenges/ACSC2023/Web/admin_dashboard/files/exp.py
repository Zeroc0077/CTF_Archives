# -*- encoding: utf-8 -*-
'''
@File    :   exp.py
@Time    :   2023/02/25 14:39:45
@Author  :   zeroc 
'''
from Crypto.Util.number import *
x1 = 0x906d71a2ea2c326a057abb973939f85c
x2 = 0x185a176cfb8263ca3ccd1fe6c89a2791
x3 = 0x2863b71f7827d1893359249463f61444
M = 0xc4f3b4b3deadbeef1337c0dedeadc0dd
A = ((x3 - x2) * inverse(x2 - x1, M)) % M
C = (x2 - A * x1) % M
X = 418296719726
# X = gmp_import('admin')
token = (A * X + C) % M
print(hex(token)[2:])