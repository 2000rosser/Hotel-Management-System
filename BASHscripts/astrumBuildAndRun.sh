#!/bin/bash

cd ..

# mvn clean install
mvn clean compile
mvn package

yes | docker container prune
docker-compose up --build --force-recreate --remove-orphans
