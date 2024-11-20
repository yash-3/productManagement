# Product Management System

This is a simple Product Management System built with Spring Boot, JUnit 5, Mockito, and other essential tools. The system allows for basic CRUD (Create, Read, Update, Delete) operations on product entities.

## Features

- **Create a Product**: Add new products to the database.
- **Read Products**: Retrieve products either by their ID or all products.
- **Update Product**: Modify product details.
- **Delete Product**: Remove a product from the database.

## Tech Stack

- **Backend**: Java 21, Spring Boot, Spring Data JPA
- **Database**: H2 Database (for development purposes)
- **Testing**: JUnit 5, Mockito
- **Build Tool**: Maven wrapper 3.9.9
- **Version Control**: Git
- **API Documentation**: Swagger

## Setup and Installation

### Prerequisites

Before running this application, make sure you have the following installed:

- Java 21 or later
- IDE (IntelliJ IDEA, Eclipse, etc.)

### Steps to Set Up

1. **Build the Project**:
   ```bash
   mvnw clean install
2. **Run the Application**:
   ```bash
   mvnw spring-boot:run

The application will run on http://localhost:8080.

Swagger UI is available at: http://localhost:8080/swagger-ui/index.html