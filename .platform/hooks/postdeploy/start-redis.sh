#!/bin/bash

# Redis가 이미 실행 중인지 확인하고, 실행 중이 아니라면 시작
if ! sudo docker ps | grep -q redis; then
  echo "Starting Redis container..."
  sudo docker run -d --name redis -p 6379:6379 redis:latest
else
  echo "Redis is already running."
fi