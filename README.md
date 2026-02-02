# 🚀 Quiz Application – Microservices Backend (Spring Boot)

A **backend-only Quiz Application** built using **Spring Boot Microservices**, demonstrating real-world microservices architecture with **Service Discovery**, **API Gateway**, and **inter-service communication**.

This project focuses on **backend system design and API development**, and is designed to be consumed by any frontend or client application.

---

## 🧱 Backend Architecture
```
Client (Postman / Any Frontend)
↓
API Gateway (Spring Cloud Gateway)
↓
Service Registry (Eureka Server)
↓
Quiz Service ↔ Question Service
↓
Database
```

---

## 🧩 Microservices Overview

### 🔹 Service Registry (Eureka Server)
- Central service discovery component  
- Registers all microservices dynamically  
- Enables load balancing and fault tolerance  
- Removes hardcoded service URLs  

---

### 🔹 API Gateway
- Single entry point for all client requests  
- Routes requests dynamically using Eureka  
- Enables CORS for browser-based clients  
- Simplifies client-side communication  

---

### 🔹 Quiz Service
Responsible for managing quiz operations.

**Key Responsibilities:**
- Create quizzes dynamically  
- Generate unique **Quiz PIN**  
- Fetch random questions using Question Service  
- Serve questions one-by-one  
- Submit responses and calculate results  

---

### 🔹 Question Service
Responsible for question management and evaluation.

**Key Responsibilities:**
- Store and manage quiz questions  
- Fetch random questions by category  
- Hide correct answers from clients  
- Evaluate responses and return scores  

---

## 🛠️ Technology Stack (Backend)

- Java  
- Spring Boot  
- Spring Cloud Gateway  
- Eureka Server  
- OpenFeign (Inter-service communication)  
- Spring Data JPA  
- Hibernate  
- H2 / SQL Database  

---

## 🔄 Backend Request Flow

1. Client sends request to **API Gateway**  
2. Gateway routes request to the appropriate microservice  
3. Services discover each other via **Eureka**  
4. Quiz Service communicates with Question Service using **Feign**  
5. Database operations handled independently by each service  

---

## 🧪 Core API Endpoints

### 📌 Quiz Service
```
POST /quiz/create
GET /quiz/pin/{quizCode}
GET /quiz/{quizId}/question/{index}
POST /quiz/submit/{quizId}
```

### 📌 Question Service
```
GET /question/allQuestions
GET /question/category/{category}
GET /question/generate
POST /question/getQuestions
POST /question/getScore
```

> All APIs are accessed through the **API Gateway**

---

## ▶️ How to Run Backend Services

### 1️⃣ Start Services in Order
1. Eureka Server  
2. API Gateway  
3. Question Service  
4. Quiz Service  

---

### 2️⃣ Create a Quiz (Admin Operation)

Using **Postman**:

POST http://localhost:8765/quiz-service/quiz/create

**Request Body:**
```json
{
  "categoryName": "Java",
  "numQuestions": 5,
  "title": "Java Basics Quiz"
}
The system generates a unique Quiz PIN, which can be used by any client to access the quiz.
```
---

### 📌 Key Backend Concepts Demonstrated

Microservices architecture

Service discovery with Eureka

API Gateway pattern

Inter-service communication using Feign

RESTful API design

Loose coupling between services

Backend-first system design

---

### 🚀 Future Backend Enhancements

Authentication & authorization

Rate limiting at API Gateway

Circuit breaker (Resilience4j)

Caching using Redis

Centralized configuration (Spring Cloud Config)

Docker & cloud deployment

---

### 👨‍💻 Author

Yashpal Parmar
Computer Science Engineering Student
Backend & Microservices Enthusiast

🔗 GitHub: https://github.com/yashpal8078

🔗 LinkedIn: https://www.linkedin.com/in/yashpal-parmar-

⭐ This project focuses entirely on backend microservices architecture and API development.


