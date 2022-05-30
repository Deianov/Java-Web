#!/bin/bash

# constants
IMAGE_MYSQL=mysql:8


# Stop container by name
docker stop $(docker container ls -a -q --filter="name=mysql")

# Remove image
docker rmi -f $IMAGE_MYSQL

# run containers
docker-compose -f src/main/docker/docker-compose-db.yaml up