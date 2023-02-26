from PIL import Image
from Crypto.Util.number import *
from random import shuffle, randint, getrandbits

flagImg = Image.open('flag.png')
width = flagImg.width
height = flagImg.height

def makeSourceImg():
    colors = long_to_bytes(getrandbits(width * height * 24))[::-1]
    img = Image.new('RGB', (width, height))
    x = 0
    for i in range(height):
        for j in range(width):
            img.putpixel((j, i), (colors[x], colors[x + 1], colors[x + 2]))
            x += 3
    return img

def xorImg(keyImg, sourceImg):
    img = Image.new('RGB', (width, height))
    for i in range(height):
        for j in range(width):
            p1, p2 = keyImg.getpixel((j, i)), sourceImg.getpixel((j, i))
            img.putpixel((j, i), tuple([(p1[k] ^ p2[k]) for k in range(3)]))
    return img

n1 = makeSourceImg()
n2 = makeSourceImg()
n3 = makeSourceImg()
nonce = [n1, n2, n3]

index = list(range(16))
shuffle(index)
e=0
for i in index:
    im = Image.open(f"source/picture{i}.png")
    key = nonce[randint(0, 2)]
    encImg = xorImg(key, im)
    encImg.save(f'pics/enc{e}.png')
    e+=1