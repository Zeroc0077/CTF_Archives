FROM ubuntu:20.04

ENV FLAG=flag{not_here}

RUN sed -i s@/archive.ubuntu.com/@/mirrors.ustc.edu.cn/@g /etc/apt/sources.list && \
    sed -i s@/security.ubuntu.com/@/mirrors.ustc.edu.cn/@g /etc/apt/sources.list && \
    apt-get update && apt-get -y dist-upgrade && \
    apt-get install -y lib32z1 xinetd && \
    apt-get install -y qemu libpixman-1-0 libpixman-1-dev libsnappy-dev

RUN useradd -m ctf

WORKDIR /home/ctf

COPY ./lib/libglib-2.0.so.0 /lib/x86_64-linux-gnu/libglib-2.0.so.0
COPY ./lib/libgio-2.0.so.0 /lib/x86_64-linux-gnu/libgio-2.0.so.0
COPY ./lib/libgmodule-2.0.so.0 /lib/x86_64-linux-gnu/libgmodule-2.0.so.0
COPY ./lib/libgobject-2.0.so.0 /lib/x86_64-linux-gnu/libgobject-2.0.so.0
COPY ./lib/libncursesw.so.5 /lib/x86_64-linux-gnu/libncursesw.so.5
COPY ./lib/libtinfo.so.5 /lib/x86_64-linux-gnu/libtinfo.so.5
COPY ./lib/libffi.so.6 /lib/x86_64-linux-gnu/libffi.so.6


RUN mkdir /home/ctf/usr && \ 
    cp -rf /lib* /home/ctf && \
    cp -rf /usr/lib* /home/ctf/usr


RUN mkdir /home/ctf/dev && \
    mknod /home/ctf/dev/null c 1 3 && \
    mknod /home/ctf/dev/zero c 1 5 && \
    mknod /home/ctf/dev/random c 1 8 && \
    mknod /home/ctf/dev/urandom c 1 9 && \
    chmod 666 /home/ctf/dev/*

RUN mkdir /home/ctf/bin && \
    cp /bin/ls /home/ctf/bin/ls && \
    cp /bin/cat /home/ctf/bin/cat && \
    cp /bin/sh /home/ctf/bin/sh

COPY ./ctf.xinetd /etc/xinetd.d/ctf
COPY ./start.sh /start.sh
RUN echo "Blocked by ctf_xinetd" > /etc/banner_fail


RUN chmod +x /start.sh

COPY ./bin/ /home/ctf/
COPY ./pc-bios/ /home/ctf/pc-bios

RUN chown -R root:ctf /home/ctf && \
    chmod -R 750 /home/ctf && \
    chmod 766 /home/ctf/dev/null

CMD ["/start.sh"]

EXPOSE 9999