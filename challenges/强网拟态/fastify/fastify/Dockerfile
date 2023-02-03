FROM node:latest

# Create app directory
COPY ./app /usr/src/app
COPY ./flag /flag
WORKDIR /usr/src/app
RUN npm install --save --registry=https://registry.npm.taobao.org

USER node
CMD [ "node", "app.js" ]