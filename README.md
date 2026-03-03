# Hyrion Backend

Hyrion Backend is a REST API service designed to aggregate, process, and serve IT job offers from various platforms.

This project is built with a strong emphasis on clean code, maintainability, and advanced architectural patterns, making it a highly scalable foundation for a job board aggregator.

## Architecture & Patterns

This project moves away from the traditional, tightly-coupled layered architecture (Controller -> Service -> Entity) and implements industry-standard patterns:

* **Hexagonal Architecture:** The core business logic is completely isolated from external frameworks, databases, and UI. Communication happens strictly through defining input and output ports.
* **Domain-Driven Design:** Features a rich domain model with a strictly encapsulated Aggregates, Value Objects, and Guard Clauses to ensure business invariants are always met. No anemic domain models.
* **CQRS (Command and Query Responsibility Segregation):**
  * **Command Side:** Optimized for complex business validation and state mutation before saving to the database.
  * **Query Side:** *(Work in Progress)* Optimized for high-performance data retrieval directly into flat DTOs, bypassing the domain model entirely.

## Project Structure

The package structure reflects the architectural choices (separated by Bounded Contexts and CQRS sides):

```text
pl.hyrion.hyrionbackend.offer
├── command/                       # Write-model
│   ├── adapter/                   # Web Controllers, DB Adapters
│   ├── application/               # Orchestration (Use Cases, Ports)
│   ├── domain/                    # Aggregates, Exceptions
│   └── infrastructure/            # Spring Configuration
└── query/                         # Read-model
```

## Roadmap | TODOs

### 1. Core Architecture & CQRS Implementation
The initial phase focuses on establishing the foundational architecture of the system. This includes separating the write and read models using the CQRS pattern, ensuring robust domain validation for incoming data, and building the initial REST API for both data ingestion and retrieval. 
Also, prioritization of setting up continuous integration pipelines and standardizing API error responses.

### 2. Production-Ready Infrastructure
This phase will transition the application from a local development environment to a deployment-ready state. 
The primary goals include migrating from an in-memory database to a robust relational database engine, introducing schema versioning tools for safe data migrations, and fully containerizing the application to ensure consistent execution across different environments.

### 3. Advanced Features, Security & Optimization
The final planned phase focuses on scaling, performance, and API protection. Planned features include introducing caching mechanisms to significantly speed up data retrieval, securing the endpoints against unauthorized scrapers, providing interactive API documentation for end-users, and implementing background jobs for automated data maintenance and archiving.