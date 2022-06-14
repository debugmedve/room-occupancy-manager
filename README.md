# room-occupancy-manager

## Open project

As maven project you can open it with your favorite IDE or use maven in command line. 
The tests are run at maven's `test` and `verify` phase as well. 

## Run

### From IDE

Run from IDE

### From CMD

```bash
$ mvn spring-boot:run
```

or 

```bash
$ mvn clean package
$ java -jar target/room-occupancy-manager-1.0-SNAPSHOT.jar
```

and so on... either as Linux service or within a container.

## Spring profiles

* `debug` - for debug logging level purpose

## Swagger

http://localhost:8080/rom/swagger-ui/index.html

## Actuators

### Health

Health check for monitoring

http://localhost:8080/rom/actuator/health

### Loggers

To get or change logger level runtime

http://localhost:8080/rom/actuator/loggers

### Prometheus

To get micrometer data from JVM and or the system (to visualize on grafana dashboard or equivalent)

http://localhost:8080/rom/actuator/prometheus

etc...
