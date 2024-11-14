#!/bin/bash
# 기존 redis 컨테이너 중지 및 제거
# shellcheck disable=SC2046
if [ $(docker ps -q -f name=redis) ]; then
  docker stop redis
  docker rm redis
fi
