# SevaSaathi Platform

SevaSaathi is an AI-powered platform that connects users in small towns and rural areas with local service providers. The platform improves access to trusted services while creating employment opportunities for skilled workers in underserved communities.

## Technology Stack

- **Backend**: Spring Boot 3.x with Java 17
- **Database**: MySQL 8.0 (primary), Redis (caching), Elasticsearch (search)
- **Build Tool**: Maven
- **Testing**: JUnit 5, jqwik (property-based testing), Testcontainers
- **Security**: Spring Security with JWT
- **Documentation**: OpenAPI 3 (Swagger)

## Prerequisites

- Java 17 or higher
- Maven 3.8 or higher
- MySQL 8.0
- Redis 6.0 or higher
- Elasticsearch 8.0 or higher

## Quick Start

### 1. Clone and Build

```bash
git clone <repository-url>
cd seva-saathi-platform
mvn clean install
```

### 2. Database Setup

Create a MySQL database and user:

```sql
CREATE DATABASE sevasaathi_db;
CREATE USER 'sevasaathi_user'@'localhost' IDENTIFIED BY 'sevasaathi_pass';
GRANT ALL PRIVILEGES ON sevasaathi_db.* TO 'sevasaathi_user'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Environment Configuration

Set the following environment variables or update `application.yml`:

```bash
# Database
export DB_USERNAME=sevasaathi_user
export DB_PASSWORD=sevasaathi_pass

# Redis
export REDIS_HOST=localhost
export REDIS_PORT=6379

# Elasticsearch
export ELASTICSEARCH_URIS=http://localhost:9200

# JWT Secret (change in production)
export JWT_SECRET=your-secure-jwt-secret-key

# External Services (optional for development)
export OPENAI_API_KEY=your-openai-api-key
export MAPS_API_KEY=your-google-maps-api-key
export RAZORPAY_KEY_ID=your-razorpay-key-id
export RAZORPAY_KEY_SECRET=your-razorpay-key-secret
```

### 4. Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080/api/v1`

### 5. API Documentation

Once the application is running, access the API documentation at:
- Swagger UI: `http://localhost:8080/api/v1/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/api/v1/v3/api-docs`

## Development Profiles

### Development Profile (`dev`)
- Uses MySQL database with `create-drop` DDL mode
- Enables SQL logging
- H2 console available at `/h2-console`

### Test Profile (`test`)
- Uses H2 in-memory database
- Mock configurations for external services
- Reduced logging levels

### Production Profile (`prod`)
- Uses MySQL with `validate` DDL mode
- Optimized logging levels
- Production security settings

## Project Structure

```
src/
├── main/
│   ├── java/com/sevasaathi/platform/
│   │   ├── config/          # Configuration classes
│   │   ├── controller/      # REST controllers
│   │   ├── dto/            # Data Transfer Objects
│   │   ├── entity/         # JPA entities
│   │   ├── exception/      # Exception handling
│   │   ├── repository/     # Data repositories
│   │   │   ├── jpa/        # JPA repositories
│   │   │   └── elasticsearch/ # Elasticsearch repositories
│   │   ├── service/        # Business logic services
│   │   ├── util/           # Utility classes
│   │   └── SevaSaathiApplication.java
│   └── resources/
│       ├── application.yml  # Application configuration
│       └── static/         # Static resources
└── test/
    ├── java/               # Test classes
    └── resources/          # Test resources
```

## Key Features

### Core Services
- **User Management**: Registration, authentication, profile management
- **Service Provider Management**: Provider registration, skill management, availability
- **Search & Discovery**: Location-based search, AI recommendations
- **Booking System**: Appointment scheduling, calendar management
- **Communication**: Real-time messaging, multilingual chatbot
- **Payment Processing**: Multiple payment methods, escrow functionality
- **Rating System**: Reviews and ratings for service providers

### Technical Features
- **Microservices Architecture**: Modular service design
- **AI Integration**: OpenAI GPT for chatbot, recommendation engine
- **Real-time Communication**: WebSocket support for messaging
- **Caching**: Redis for performance optimization
- **Search**: Elasticsearch for advanced search capabilities
- **Security**: JWT-based authentication, role-based access control
- **Testing**: Comprehensive unit and property-based testing

## Testing

### Run All Tests
```bash
mvn test
```

### Run Specific Test Categories
```bash
# Unit tests only
mvn test -Dtest="*Test"

# Property-based tests only
mvn test -Dtest="*Properties"

# Integration tests
mvn test -Dtest="*IT"
```

### Property-Based Testing

The project uses jqwik for property-based testing to verify correctness properties across all inputs:

```java
@Property
@Report(Reporting.GENERATED)
void testUserRegistrationCompleteness(@ForAll("userGenerator") User user) {
    // Property test implementation
    // Validates: Requirements 1.1 - Registration creates complete accounts
}
```

## Monitoring and Health Checks

### Health Endpoints
- Application health: `GET /api/v1/actuator/health`
- Detailed health: `GET /api/v1/actuator/health` (with authentication)
- Metrics: `GET /api/v1/actuator/metrics`
- Prometheus metrics: `GET /api/v1/actuator/prometheus`

### Logging

Logs are written to:
- Console: Structured format for development
- File: `logs/seva-saathi.log` with rotation

Log levels can be configured via environment variables:
- `LOG_LEVEL`: Application log level (default: INFO)
- `SQL_LOG_LEVEL`: SQL query log level (default: WARN)
- `SECURITY_LOG_LEVEL`: Security log level (default: WARN)

## Contributing

1. Follow the existing code structure and naming conventions
2. Write both unit tests and property-based tests for new functionality
3. Update documentation for any API changes
4. Ensure all tests pass before submitting changes
5. Follow the microservices architecture principles

## License

This project is proprietary software for SevaSaathi Platform.