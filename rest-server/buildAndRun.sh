#!/bin/sh
mvn clean package && docker build -t com.patrickhub.bookshop.restserver/rest-server .
docker rm -f rest-server || true && docker run -d -p 8080:8080 -p 4848:4848 --name rest-server com.patrickhub.bookshop.restserver/rest-server 
