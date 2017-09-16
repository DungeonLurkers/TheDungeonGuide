#!/bin/bash

POSTGRES_CONTAINER="test-postgres-db-$$"
BUILDER_CONTAINER="test-builder-$$"

trap "docker rm -f ${POSTGRES_CONTAINER} ${BUILDER_CONTAINER}"

#run database
docker run --name ${POSTGRES_CONTAINER}\
    -e POSTGRES_USER=tdg \
    -e POSTGRES_DB=tdg \
    -e POSTGRES_PASSWORD='test' \
    -d postgres:latest

#build app
docker run --name ${BUILDER_CONTAINER} \
    --link "${POSTGRES_CONTAINER}:postgres.local" \
    -e POSTGRES_URL="jdbc:postgresql://postgres.local:5432/" \
    -e POSTGRES_USER=tdg \
    -e POSTGRES_DB=tdg \
    -e POSTGRESS_PASSWORD='test' \
    -d