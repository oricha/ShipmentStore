# Store Service

## Docs
    /docs/openapi.yaml
    /Store_API.postman_collection.json
## Run  App
1. In a first console, run the following command to start the database and ActiveMQ:
```
docker-compose up 
```

2. In a second console, run the following command to start the application:
```
gradle build
gradle bootRun
```

3. Verify APP and ActiveMQ is running

 open [http://localhost:8080/health](http://localhost:8080/health)

ActiveMQ [http://localhost:15672/#/queues](http://localhost:15672/#/queues)

4. Run integration tests
```
./gradlew cucumber
```