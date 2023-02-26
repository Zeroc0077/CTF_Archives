import base64

payload = '\\\\\\\\\\\\\'/**/OR/**/1=1/**/--/**/'.encode()
payload = base64.a85decode(payload)

with open("D:/lc/CTF/LACTF2023/Web/src/payload.png", "wb") as f:
    f.write(payload)