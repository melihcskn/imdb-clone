# imdb-clone

This is a imdb clone project that aims to recreate Internet Movie Database website

For the backend:

Websitedata uses spring boot and mysql

Review/rating uses spring boot and keycloak for authorization

User operations uses spring boot and mariaDb with keycloak for identity management

Keycloak is used for identity management and access controls

Rabbitmq is used as message-broker

For the frontend:

ReactJS is used for frontend

How to run(with docker):

In main folder open preferred cli and then execute the following commands

docker-compose -f ./backend/rabbitmq/docker-compose.yml up -d

docker-compose -f ./backend/keycloak/docker-compose.yml up -d

docker-compose -f ./backend/user-operations/docker-compose.yml up -d

docker-compose -f ./backend/rating/docker-compose.yml up -d

docker-compose -f ./backend/MovieSiteData/docker-compose.yml up -d

docker-compose -f ./frontend/docker-compose.yml up -d
