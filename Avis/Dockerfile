FROM node:15-alpine

WORKDIR /app

COPY package.json /app/

RUN npm install

COPY . /app
EXPOSE 5505
CMD [ "node", "server.js" ]