#!/bin/bash

# constants
IMAGE_APP=deianov/docker3:latest
CONTAINER_NAME=docker3


# Stop all the containers
# -q - image IDs
#docker stop $(docker container ls -a -q)

# Stop container by name
docker stop $(docker container ls -a -q --filter="name=$CONTAINER_NAME")

# Remove image
docker rmi -f $IMAGE_APP

# Remove all stopped containers
docker container prune --force

# Rebuild image
docker build -t $IMAGE_APP -f src/main/docker/Dockerfile .

# Run containers
docker-compose -f src/main/docker/docker-compose.yaml up