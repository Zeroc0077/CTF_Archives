# -*- encoding: utf-8 -*-
'''
@File    :   exp.py
@Time    :   2023/02/26 23:38:38
@Author  :   zeroc 
'''
table = [5, 196, 31, 227, 223, 97, 237, 105, 170, 24, 75, 99, 70, 41, 154, 165, 33, 253, 174, 77, 158, 219, 187, 44, 128, 224, 132, 167, 110, 217, 146, 178, 198, 92, 153, 255, 157, 59, 16, 159, 34, 123, 79, 13, 64, 76, 226, 252, 26, 135, 220, 35, 171, 94, 121, 207, 231, 122, 49, 238, 243, 235, 25, 38, 20, 90, 12, 56, 202, 210, 8, 145, 209, 18, 222, 50, 86, 81, 233, 234, 230, 17, 204, 228, 69, 14, 130, 246, 152, 250, 156, 218, 175, 58, 102, 39, 166, 131, 114, 126, 21, 168, 189, 3, 61, 116, 111, 98, 80, 141, 72, 11, 74, 205, 241, 88, 63, 129, 239, 83, 71, 199, 249, 19, 147, 221, 107,
         236, 109, 15, 124, 245, 143, 186, 150, 212, 113, 164, 191, 40, 4, 192, 193, 139, 84, 240, 144, 201, 169, 96, 180, 151, 195, 68, 2, 104, 213, 138, 54, 118, 161, 101, 148, 27, 163, 203, 108, 173, 48, 67, 172, 53, 225, 1, 51, 216, 115, 119, 208, 7, 247, 120, 55, 65, 200, 23, 106, 149, 184, 215, 47, 9, 251, 78, 89, 185, 211, 182, 194, 100, 28, 52, 29, 190, 62, 37, 32, 214, 42, 0, 127, 206, 91, 73, 232, 85, 125, 46, 197, 60, 244, 103, 95, 10, 82, 57, 162, 177, 117, 242, 176, 45, 30, 155, 112, 181, 134, 136, 140, 93, 183, 6, 179, 229, 43, 142, 160, 87, 254, 248, 137, 22, 133, 66, 188, 36]
enc = [
    0x100 - 0x73,  # *a1 = a3 + 0x73;
    0x6D,  # *a1 = a3 - 0x6D;
    0x100 - 0x52,  # *a1 = a3 + 0x52;
    0x53,  # *a1 = a3 ^ 0x53;
    0xB5,  # *a1 = a3 ^ 0xB5;
    0x27,  # *a1 = (a3 | 0xD9) + (a3 | 0x26) - 38;
    0xF,  # *a1 = a3 ^ 0xF;
    0x31,  # *a1 = a3 ^ 0x31;
    0xE2,  # *a1 = a3 ^ 0xE2;
    0x6E,  # *a1 = a3 ^ 0x6E;
    0x12,  # *a1 = (a3 | 0xEE) + (a3 | 0x11) - 17;
    0xF5,  # *a1 = (a3 | 0xB) + (a3 | 0xF4) + 12;
    0x4B,  # *a1 = a3 - 0x4B;
    0x82,  # *a1 = (a3 | 0x7E) + (a3 | 0x81) + 127;
    0x14,  # *a1 = a3 - 0x14;
    0x44,  # *a1 = (a3 | 0xBC) + (a3 | 0x43) - 67;
    0x100 - 0x18,  # *a1 = a3 + 0x18;
    0xC7,  # *a1 = a3 ^ 0xC7;
    0x20,  # *a1 = a3 - 0x20;
    0xEF,  # *a1 = a3 ^ 0xEF;
    0x21,  # *a1 = (a3 | 0xDF) + (a3 | 0x20) - 32;
    0xC,  # *a1 = (a3 | 0xF4) + (a3 | 0xB) - 11;
    0x3D,  # *a1 = a3 ^ 0x3D;
    0x1B,  # *a1 = a3 ^ 0x1B;
    0x16,  # *a1 = (a3 | 0xEA) + (a3 | 0x15) - 21;
    0x84,  # *a1 = (a3 | 0x7C) + (a3 | 0x83) + 125;
    0x91,  # *a1 = a3 ^ 0x91;
    0xF5,  # *a1 = (a3 | 0xB) + (a3 | 0xF4) + 12;
    0x89,  # *a1 = a3 ^ 0x89;
    0xD1,  # *a1 = a3 ^ 0xD1;
    0x1B,  # *a1 = (a3 | 0xE5) + (a3 | 0x1A) - 26;
    0x100 - 0x7A,  # *a1 = a3 + 0x7A;
    0x8A,  # *a1 = a3 ^ 0x8A;
    0xEF,  # *a1 = a3 ^ 0xEF;
    0xAD,  # *a1 = a3 ^ 0xAD;
    0xF2,  # *a1 = (a3 | 0xE) + (a3 | 0xF1) + 15;
    0xDA,  # *a1 = a3 ^ 0xDA;
    0x100 - 0x35,  # *a1 = a3 + 0x35;
    0x58,  # *a1 = (a3 | 0xA8) + (a3 | 0x57) - 87;
    0x100 - 0x2E,  # *a1 = a3 + 0x2E;
    0xB0,  # *a1 = a3 ^ 0xB0;
    0x51,  # *a1 = a3 - 0x51;
    0x2A,  # *a1 = a3 ^ 0x2A;
    0x63,  # *a1 = a3 ^ 0x63;
    0x93,  # *a1 = a3 ^ 0x93;
    0xC6,  # *a1 = (a3 | 0x3A) + (a3 | 0xC5) + 59;
    0xE7,  # *a1 = a3 ^ 0xE7;
    0xB0,  # *a1 = a3 ^ 0xB0;
    0x11,  # *a1 = a3 ^ 0x11;
    0x100 - 0x67,  # *a1 = a3 + 0x67;
    0x100 - 0x6B,  # *a1 = a3 + 0x6B;
    0xF3,  # *a1 = (a3 | 0xD) + (a3 | 0xF2) + 14;
    0x35,  # *a1 = a3 - 0x35;
    0xE,  # *a1 = a3 ^ 0xE;
    0xD1,  # *a1 = a3 ^ 0xD1;
    0x58,  # *a1 = a3 ^ 0x58;
    0x84,  # *a1 = a3 ^ 0x84;
    0x7C,  # *a1 = a3 ^ 0x7C;
    0x55,  # *a1 = a3 ^ 0x55;
    0x51,  # *a1 = (a3 | 0xAF) + (a3 | 0x50) - 80;
    0x100 - 0x49,  # *a1 = a3 + 0x49;
    0xCF,  # *a1 = a3 ^ 0xCF;
    0x100 - 0x59,  # *a1 = a3 + 0x59;
    0x57,  # *a1 = a3 ^ 0x57;
]
log = []
#! generate the table
v27 = 0
for i in range(256):
    a24 = i
    v26 = table[i]
    v27 = (table[i] + v27) & 0xFF
    table[i], table[v27] = table[v27], table[i]
    log.append([i, v27])
#! generate the key array
K = []
key = [0, 0, 0, 0]
for i in range(64):
    v13 = (key[1] + 5) & 0xff
    v14 = table[v13]
    v15 = (table[(v14 + key[2]) & 0xff] + key[3]) & 0xff
    v16 = v15
    v17 = table[v16]
    v18 = (v17 + v13 + key[3]) & 0xff
    table[v13] = v17
    table[v16] = v14
    v19 = table[(v15 + table[(v13 + table[(v18 + key[0]) & 0xff]) & 0xff]) & 0xff]
    key = [v19, v13, v15, v18]
    K.append(key)
#! BruteForce decrypt the previous value
flag = []
for i in range(64):
    for j in range(256):
        if enc[i] == (5889 * ((j ^ K[i][0]) + 2 * (j & K[i][0])) + 3584) & 0xff:
            flag.append(j)
            break
#! Reverse the first encryption
for i in range(255, -1, -1):
    m, n = log[i]
    flag[m >> 2] ^= i
    flag[n >> 2] ^= log[i][1]
    if log[i][0] ^ log[i][1] > 3:
        flag[m >> 2] ^= flag[n >> 2]
print(bytes(flag))
#! ACSC{pyth0n_numb4_0n3___s0_m4ny_functi0ns_s0_l1ttl3_us3ful_c0d3}