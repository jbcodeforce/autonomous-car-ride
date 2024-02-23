# Autonomous-car-ride manager microservice

This project illustrates event sourcing and CQRS patterns implementation using Java and Quarkus, the "Supersonic Subatomic Java Framework".

it can run locally using Docker compose and being deployed on AWS EKS or ECS Fargate.

## What the app does

Expose CRUD for CarRide and TripFare Entities with Postgresql as database. It supports also a control interface to create n records randomly (this is a command to be used for demonstration purpose).

## Development time

The following notes if for development activities

### Running the application in dev mode

* First start Postgresql locally with `docker compose up -d`
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