# -*- encoding: utf-8 -*-
'''
@File    :   Assembly.py
@Time    :   2023/03/02 13:36:06
@Author  :   zeroc 
'''
flag = bytes.fromhex(hex(8319050641287963497)[2:])[::-1]
flag += bytes.fromhex(hex(8387496331138198835)[2:])[::-1]
flag += bytes.fromhex(hex(7293129038958649207)[2:])[::-1]
flag += bytes.fromhex(hex(8217139)[2:])[::-1]
print(flag)
#! ictf{4ss3mbly_ftw_7d8f6e3b}