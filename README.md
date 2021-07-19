# Social Rating Score Microservice

Microservice responsible to collect user data and calculate the user social rating score.

## Usage

In the project root folder, execute to build the project:
```
docker-compose build
```
After that, execute to run all services:
```
docker-compose up
```
You can check in Docker documentation [https://docs.docker.com/compose/environment-variables]  to pass a .env file as a configuration for the seed variable. The name is:
```
SOCIAL_RATING_SEED
```

After completing the start phase, send the payload below as a POST to (http://localhost:8080/social-rating):
```json
{
"first_name": "rodrigo",
"last_name": "kossmann",
"age": 32
}
```
OR

Use the postman_collection in the utils folder.

The following line should be printed in the log:
```
Rodrigo Kossmann has 7.0 score
```

## Services
Service                 | PORT            | Description
-------------           | -------------   | -------------
zookeeper               | localhost:2181  | Needed for Kafka 
kafka                   | localhost:9092  | Service used to schedule in queue
kafdrop                 | localhost:19000 | Service used to check information about kafka
redis                   | localhost:6379  | Service used to save the data
score-data-collector    | localhost:8080  | Microservice for collect the data
score-rating-calculator | localhost:8090  | Microservice for calculate the user score and save the data on redis

## Technology stack
- Java 11
- Spring Boot
- Apache Kafka
- Redis
- Docker

## Future improvements:
- Metrics collect and visualization
- Dynamic jar name on dockerfile
- Improvements on docker-compose build process when there is a new implementation in score-data-collector or score-rating-calculator 