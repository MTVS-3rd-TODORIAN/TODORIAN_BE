#!/bin/bash

# 모든 기존 컨테이너와 네트워크를 정리
echo "Stopping and removing all containers and networks..."
docker compose down

# 새로운 컨테이너와 네트워크를 생성
echo "Starting docker-compose up..."
docker compose up -d
