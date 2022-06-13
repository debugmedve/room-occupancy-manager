# room-occupancy-manager

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