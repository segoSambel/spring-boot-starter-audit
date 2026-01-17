# Audit Demo App

Simple Spring Boot application demonstrating the usage of
`spring-boot-starter-audit`.

## Run

```bash
mvn spring-boot:run
```

## Try
Create User:
```bash
curl -X POST http://localhost:8080/api/users/username
```

Delete User:
```bash
curl -X DELETE http://localhost:8080/api/users/username
```

Failure Case:
```bash
curl -X DELETE http://localhost:8080/api/users/admin
```

Get Audit List
```bash
curl http://localhost:8080/api/audits
```