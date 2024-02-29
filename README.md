# Autonomous-car-ride manager microservice

This project illustrates the event sourcing and CQRS patterns implementation using Java and Quarkus, the "Supersonic Subatomic Java Framework".

* [Event sourcing](https://jbcodeforce.github.io/eda-studies/patterns/event-sourcing/) pattern is about persisting the state of business entity as a sequence of state-changing events as immutable facts ordered over time.
* [Command-Query Responsibility Segregation (CQRS)](https://jbcodeforce.github.io/eda-studies/patterns/cqrs/) is a pattern to separate operations for querying data with operations for updating data so that they may be handled independently, in separate applications or microservices.

This project in the implementation of the Command part of an autonomous robot taxi ride share. The use case analysis and domain driven design is [documented here](https://jbcodeforce.github.io/eda-studies/solutions/autonomous-car/). The code can run locally using Docker compose and being deployed on AWS EKS or ECS Fargate.

![](./docs/cqrs-es.drawio.png)

The demonstration also illustrates the needs to represent the data in different formats: Data Transfer Object at the API level, Business Entity for the persistence, and Event definitions to share state changes. 

## What the app does

This microservice exposes CRUD operations for CarRide and TripFare Entities with Postgresql as database. 

## Development time

The following notes if for development activities:

### Running the application in dev mode

* First start Postgresql and Kafka locally with `docker compose up -d`
* Run the application in dev mode:

```shell script
quarkus dev
```

* APIs are accessible at http://localhost:8080/q/swagger-ui/

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.


### Packaging and running the application

The application can be packaged into a docker image using:

```shell script
./scripts/buildAll.sh
```

## Running a local end to end demonstration


## Running with RDS Postgresql

* We can create the RDS postgreSQL engine DB with the aws CLI:

```sh
./scripts/createRDS-postgres.sh
```

* Change the property with the URL of the server:

```
quarkus.datasource.jdbc.url=jdbc:postgresql://tripdb.<>.us-west-2.rds.amazonaws.com:5432/postgres
```

* Starting in dev mode, the SQL schema will be created automatically. 