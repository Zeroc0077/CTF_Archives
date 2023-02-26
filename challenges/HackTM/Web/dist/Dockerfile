FROM php:8.0-apache

COPY ./chal/html /var/www/html
COPY ./chal/db /sqlite3/db
COPY ./chal/flag.txt /02d92f5f-a58c-42b1-98c7-746bbda7abe9/flag.txt
RUN chmod -R 777 /sqlite3/
RUN chmod -R 777 /var/www/html/
