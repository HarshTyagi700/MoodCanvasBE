# 🎨 MoodCanvas Backend

MoodCanvas is a full-stack Pinterest-inspired web application designed with a microservices architecture. This repository contains the **Spring Boot-based backend services** that handle authentication, pin management, pinboard organization, analytics tracking, search, and inter-service communication.

---

## 🧱 Microservices Overview

The project follows a **modular microservices architecture**, using **Spring Boot**, **Apache Kafka**, **Elasticsearch**, **Consul**, and **JWT** for a robust, scalable backend.

| Service        | Description                                                                 |
|----------------|-----------------------------------------------------------------------------|
| `gateway-ms`   | Entry point for all API traffic. Handles routing, load balancing, and JWT validation. |
| `user-ms`      | Manages user registration, login, and authentication. Issues and validates JWT tokens. |
| `pin-ms`       | Handles CRUD operations for Pins. Integrates with Elasticsearch and publishes Kafka events. |
| `pinboard-ms`  | Manages pinboards and pin-to-board mappings. Uses Guava caching for pinboard lookups. |
| `analytics-ms` | Consumes Kafka events and aggregates metrics like likes/views/saves for pins. |
| `base-domains` | Shared module with DTOs, utility classes, and Kafka event models.   |

---

## 🔧 Tech Stack

- **Java 17**
- **Spring Boot 3+**
- **Spring Cloud (Gateway, Config, Consul)**
- **Apache Kafka**
- **Elasticsearch**
- **Google Guava Cache**
- **JWT Authentication**
- **Lombok**
