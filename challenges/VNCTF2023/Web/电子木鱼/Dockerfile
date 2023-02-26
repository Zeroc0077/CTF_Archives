FROM debian:buster-slim
ENV FLAG=flag{not}
WORKDIR /app
COPY ./chall .

RUN useradd -ms /bin/bash user && mv start.sh / && chmod +x /start.sh
USER user
EXPOSE 2333
CMD ["/start.sh"]
