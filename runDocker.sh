#!/bin/bash
mvn clean install -DskipTests
docker build -t cliente-persona .
cd ../spring-boot-tech-test-transaction
mvn clean install -DskipTests
docker build -t cuenta-movimiento .
cd ../spring-boot-tech-test
docker-compose up -d

