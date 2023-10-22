#!/bin/bash

cd ..

kubectl delete deployment admin-deployment
kubectl delete service admin
kubectl delete deployment client-deployment
kubectl delete service client
kubectl delete deployment hms-deployment
kubectl delete service hms
kubectl delete deployment hotel-deployment
kubectl delete service hotel



mvn clean install
mvn clean compile
mvn package

cd admin
docker build -t 2000rosser/admin:latest .
docker push 2000rosser/admin:latest
kubectl apply -f admin-service-deployment.yaml
kubectl apply -f admin-service.yaml
cd ..

cd client-new
docker build -t 2000rosser/client:latest .
docker push 2000rosser/client:latest
kubectl apply -f client-service-deployment.yaml
kubectl apply -f client-service.yaml
cd ..

cd hms
docker build -t 2000rosser/hms:latest .
docker push 2000rosser/hms:latest
kubectl apply -f hms-service-deployment.yaml
kubectl apply -f hms-service.yaml
cd ..

cd hotel
docker build -t 2000rosser/hotel:latest .
docker push 2000rosser/hotel:latest
kubectl apply -f hotel-service-deployment.yaml
kubectl apply -f hotel-service.yaml
cd ..

# cd hotel2
# docker build -t 2000rosser/hotel2:latest .
# docker push 2000rosser/hotel2:latest
# kubectl apply -f hotel2-service-deployment.yaml
# kubectl apply -f hotel2-service.yaml
# cd ..

# echo "Waiting for 10 seconds"
# sleep 10


# kubectl port-forward service/admin 8085:80 &
# kubectl port-forward service/client 8084:80 &
# kubectl port-forward service/hms 8083:80 &
# kubectl port-forward service/hotel 8080:80 &
