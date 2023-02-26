from Crypto.Util.number import *

def gen_key(kbits):
    while True:
        p = getPrime(kbits)
        q = getPrime(kbits)
        if p % 4 == 3 and q % 4== 3:
            break
    return p, q

p ,q = gen_key(256)
flag =  open("flag", 'rb').read()
pt = bytes_to_long(flag)
c = pow(pt, 2, p*q)

print(f"p={p}\nq={q}")
print(f"c={hex(c)[2:]}")

"""
p=65428327184555679690730137432886407240184329534772421373193521144693375074983
q=98570810268705084987524975482323456006480531917292601799256241458681800554123
c=4e072f435cbffbd3520a283b3944ac988b98fb19e723d1bd02ad7e58d9f01b26d622edea5ee538b2f603d5bf785b0427de27ad5c76c656dbd9435d3a4a7cf556
"""
