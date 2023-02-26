FROM node:19

RUN sed -i 's/deb.debian.org/mirrors.ustc.edu.cn/g' /etc/apt/sources.list \
  && sed -i 's|security.debian.org/debian-security|mirrors.ustc.edu.cn/debian-security|g' /etc/apt/sources.list \
  && apt-get update \
  && apt-get install -y chromium \
  && rm -rf /var/lib/apt/lists/*

WORKDIR /app
ADD . .
RUN yarn config set registry https://registry.npmmirror.com && yarn

CMD ["yarn", "start"]