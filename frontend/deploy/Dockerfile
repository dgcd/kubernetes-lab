FROM nginx:alpine

LABEL maintainer="dgcd"

RUN rm /etc/nginx/conf.d/default.conf

COPY deploy/nginx/default.conf /etc/nginx/conf.d/
COPY dist/ /etc/nginx/html/

EXPOSE 80
