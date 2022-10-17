#!/bin/bash

# GET User by id
echo "GET User by id"
curl -v 'http://localhost:8080/user/3'
echo

# GET Content by id
echo "GET Content by id"
curl -v 'http://localhost:8080/content/2'
echo

# POST Content
echo "POST Content"
curl -v POST 'http://localhost:8080/content' \
-H 'Content-Type: application/json; charset=utf-8' \
--data-binary '@body.json'

# GET Comment by user id
echo
echo "GET Comment by user id"
curl -v 'http://localhost:8080/comments/user/1'