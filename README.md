# Rentora

Rentora is a rental property management application built with Spring Boot and PostgreSQL. The application is designed to help manage buildings, rooms, tenants, rental agreements, rent payments, electricity bills, and maintenance-related costs.

A React-based frontend is currently under development to provide a user interface for the rental property management workflows.

## Project Overview

Rentora is being developed as a hands-on full-stack application to model common rental property management workflows and apply clean software design principles.

The project focuses on building backend services, managing relational data, and developing a frontend interface for interacting with the application.

## Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Data JPA
* Maven

### Frontend

* React

### Database

* PostgreSQL

### Tools

* Git
* GitHub

## Architecture

The backend follows a layered architecture to separate responsibilities:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

### Controller Layer

Handles HTTP requests and responses and exposes application functionality through web endpoints.

### Service Layer

Contains business logic and coordinates application workflows.

### Repository Layer

Handles database access using Spring Data JPA.

## Current Modules

* Building management
* Room management
* Tenant management
* Rental agreement management
* Rent tracking
* Electricity bill tracking
* Maintenance cost tracking

## Design Decisions

### Layered Architecture

Controllers, services, and repositories are separated to keep responsibilities clear and make the application easier to maintain and extend.

### Service Layer for Business Logic

Business logic is kept in the service layer rather than being placed directly in controllers. This helps keep controllers focused on request handling and makes business logic easier to test and maintain.

### PostgreSQL

PostgreSQL was selected as the relational database because the application contains structured relationships between buildings, rooms, tenants, and rental agreements.

### Backend and Frontend Separation

The backend and frontend are being developed as separate layers. This allows the Spring Boot backend to handle application logic and data access while the React frontend focuses on the user interface and user interactions.

## Project Structure

```text
Rentora
├── src
│   └── main
│       ├── java
│       └── resources
├── pom.xml
├── mvnw
└── mvnw.cmd
```

## Running the Project

### Prerequisites

* Java 17
* PostgreSQL

### Run with Maven Wrapper

On Windows:

```bash
mvnw.cmd spring-boot:run
```

On Linux/macOS:

```bash
./mvnw spring-boot:run
```

## Project Status

The backend is actively being developed using Spring Boot and PostgreSQL. A React-based frontend is currently under development to provide a user interface for the rental property management workflows.

The project is being used as a hands-on application to improve backend engineering, API design, database modelling, frontend integration, testing, and software development practices.

## Author

Sana V H
