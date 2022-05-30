#!/bin/bash


CONTAINER_NAME=docker3

# Stop container by name
docker stop $(docker container ls -a -q --filter="name=$CONTAINER_NAME")

exit 0