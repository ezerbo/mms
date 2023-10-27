#!/bin/bash

docker stop mms_pg && \
docker rm mms_pg && \
docker run -d \
	--name mms_pg \
	-v "$PWD/postgresql.conf":/etc/postgresql/postgresql.conf \
	-e POSTGRES_PASSWORD=diallo1990 \
	-e POSTGRES_HOST_AUTH_METHOD=trust \
	-p 5432:5432 \
	postgres \
	-c 'config_file=/etc/postgresql/postgresql.conf'