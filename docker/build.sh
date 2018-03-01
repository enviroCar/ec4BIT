#/bin/bash

scriptDir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "${scriptDir}"

cd ../ && \
  mvn clean install && \
  docker build -t ec4bit/adapter ./docker/Dockerfile
