#!/bin/bash
sudo docker run -v /host/directory:/container/directory -d -p 5432:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=dev postgres

