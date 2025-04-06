# Data Visualizer Application

## Prerequisites
- Java 17+
- Maven 3.8+
- MongoDB 4.4+

## Getting Started

1. Build the project:
```
mvn clean install
```

2. Start services individually:

**Database Stats Agent** (port 8081):
```
cd database-stats-agent && mvn spring-boot:run
```

**Collection Analysis Agent** (port 8082):
```
cd collection-analysis-agent && mvn spring-boot:run
```

## Spring Boot Actuator Endpoints
Both services inherit Actuator configuration from the common module:
- Health check: `http://localhost:<port>/actuator/health`
- Metrics: `http://localhost:<port>/actuator/metrics`
- Custom metrics available at `/actuator/custom-metrics`

## Configuration
- Update ports in respective `application.properties` files
- MongoDB connection configured in common module