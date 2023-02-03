from magic import Magic

with open('flag.txt', 'rb') as f:
    flag = f.read()

banner = '''
███╗   ███╗ █████╗  ██████╗ ██╗ ██████╗    ███████╗██╗ ██████╗ ███╗   ██╗
████╗ ████║██╔══██╗██╔════╝ ██║██╔════╝    ██╔════╝██║██╔════╝ ████╗  ██║
██╔████╔██║███████║██║  ███╗██║██║         ███████╗██║██║  ███╗██╔██╗ ██║
██║╚██╔╝██║██╔══██║██║   ██║██║██║         ╚════██║██║██║   ██║██║╚██╗██║
██║ ╚═╝ ██║██║  ██║╚██████╔╝██║╚██████╗    ███████║██║╚██████╔╝██║ ╚████║
╚═╝     ╚═╝╚═╝  ╚═╝ ╚═════╝ ╚═╝ ╚═════╝    ╚══════╝╚═╝ ╚═════╝ ╚═╝  ╚═══╝                                                                         
'''
print(banner)
magic = Magic(137)          # most magical number

C, K, Q = magic.random_list(3) #! C, K, Q为定义的三个MagicList对象，有lst、U属性，lst为137的MagicElement元组，U为18770元组
P1, P2 = C*K, K*Q #! 由C, K, Q进行自定义的乘法得到P1, P2
pk, sk = (C, P1, P2), (C, K, Q)
print('C =',  pk[0])
print('P1 =', pk[1])
print('P2 =', pk[2])
#! 这里P1、P2、C已知，如果能写出求乘法逆元的函数，就可以求得Q
H = magic.shake(b'Gotta make you understand~') #! 将字符串转换为magic对象
S = H*Q                     # sign #! 利用乘法进行签名
assert P1*S == C*H*P2       # verify #! 验签
print('S =', S)

H = magic.shake(b'Never gonna give you flag~')
try:
    S_ = magic(input('> ')[:magic.N]) #! 需要使输入的S_= H * Q, 也就是需要求得Q, 关键在求逆元
    if P1*S_ == C*H*P2:
        print(flag)
    else:
        print('Ooh~~~give~you~up~')
except:
    print('You know the rules and so~do~I~')
