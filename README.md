# Product Inventory Management API

A RESTful backend application built with Java and Spring Boot for managing products, categories, and users.

The project was developed to practice building a structured backend application with authentication, authorization, database persistence, validation, testing, and containerization.

## Features
- Product, Category, and User management
- RESTful CRUD operations
- Product filtering, sorting, and pagination
- Product–Category relationship
- Request validation and global exception handling
- PostgreSQL database integration
- JWT-based authentication
- Role-based authorization (USER / ADMIN)
- Bcrypt password hashing
- Spring Data JPA and Hibernate
- Swagger / OpenAPI documentation
- Docker and Docker Compose support
- Unit and integration testing

## Architecture
The application follows a layered architecture:
"Controller → Service → Repository → JPA/Hibernate → PostgreSQL"
DTOs and request models are used to separate the API layer from persistence entities.

## Tech Stack
- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT
- PostgreSQL
- Maven
- Docker & Docker Compose
- JUnit & Mockito
- Swagger / OpenAPI

## Authentication
Authentication is implemented using JWT.
Users can register and log in through the authentication endpoints. After successful login, the generated JWT can be used to access protected endpoints.
Role-based authorization is also implemented, with selected operations restricted to ADMIN users.

## Running with Docker
Build the application:

## Running with Docker

Before starting the application, create a `.env` file in the project root and configure the required environment variables:

```env
DB_USERNAME=your_database_username
DB_PASSWORD=your_database_password
JWT_SECRET=your_jwt_secret
```

Build the application:

```bash
./mvnw clean package
```

Start the Spring Boot application and PostgreSQL database:

```bash
docker compose up --build
```

The API will be available at:

`http://localhost:8080`

## API Documentation

After starting the application, Swagger UI is available at:

`http://localhost:8080/swagger-ui/index.html`

Swagger can be used to explore and test the available API endpoints.

## Future Improvements

- Improve automated test coverage
- Add refresh token support
- Standardize authentication error responses
- Add Docker health checks and persistent database volumes