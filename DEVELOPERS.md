# User API Dev Guide

## Building
You will need:

*	Java JDK 8
*	Gradle 7.3.1
*   Git
*   Docker Engine and Docker Compose

Clone the project and use Gradle to build the server

The command below will build the project and run tests
```
./gradlew clean build
```

The command below will build project and skip tests
```
./gradlew clean build -x test
```

## Testing
The command run all tests in the project
```
./gradlew test
```

## Deploying
Deployment has not yet been configured. Application can be deployed via AWS Elastic Beanstalk. Follow the
[AWS Elastic Beanstalk documentation](https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/GettingStarted.html) 
to set this up.

## Additional Information

### Local Development

We will need our application to connect to our PostgresSQL database using docker.

From the project directory, run the following commands.
```
cd docker
docker-compose up
```

Once the docker container is running, we can start our application by running the following command.
```
./gradlew bootRun
```

