FROM python:3.7-alpine

#Cat cat by lx56@blog.lxscloud.top

COPY ./app /app
COPY requirements.txt /app
COPY ./flag /flag

RUN cd /app \
&& pip install -i https://pypi.tuna.tsinghua.edu.cn/simple --no-cache-dir -r requirements.txt \
&& chmod 555 app.py

#USER nobody:nogroup
CMD cd /app && python app.py
