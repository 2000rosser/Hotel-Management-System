#!/bin/bash

cd..

yes | docker container prune
docker-compose up --build --force-recreate --remove-orphans
