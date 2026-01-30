# 💸 Expense Tracker Backend

A **Spring Boot–based RESTful backend** for an Expense Tracker application.  
This project provides **secure user authentication**, **expense tracking**, and **category management**, following **clean architecture** and **production-ready practices**.

---

## 🚀 Overview

The Expense Tracker backend allows users to:

- Register and manage accounts
- Authenticate securely using **JWT**
- Create, update, delete, and filter expenses
- Organize expenses using categories
- Handle errors consistently with structured responses

The application is **stateless**, **scalable**, and built using industry-standard Spring technologies.

---

## 🧱 Project Structure

```text
src/main/java/com/ExpenseTracker
│
├── config          # Security, JWT, and authentication config
├── controllers     # REST controllers
├── dtos            # Request & response DTOs
├── entities        # JPA entities
├── ENUMs           # Enums (ExpenseType, etc.)
├── exceptions      # Custom exceptions & global handler
├── mappers         # Entity ↔ DTO mappers
├── repos           # Spring Data JPA repositories
├── services        # Business logic
│
└── ExpenseTrackerApplication.java

## 🔐 Security & Authentication

### 🔒 Authentication Mechanism
- **Spring Security–based authentication**
- **Stateless JWT authentication**
- **Secure `/login` endpoint**
- **Password hashing using BCrypt**
- **Custom JWT filter** for request validation
- **Custom `UserDetailsService` implementation**

---

### 🔄 Authentication Flow

1. User sends credentials to the **`/login`** endpoint  
2. Credentials are authenticated using **`AuthenticationManager`**  
3. Upon successful authentication, a **JWT token** is generated  
4. The JWT token is returned to the client  
5. The token must be included in requests to access **protected endpoints**

## ✨ Features

### 👤 User Management
- Register a new user
- Get all users (paginated)
- Get user by username
- Update user by:
  - ID
  - Username
- Delete user by:
  - ID
  - Username

---

### 💳 Expense Management
- Add a new expense
- Get all expenses (paginated)
- Update expense by ID
- Delete expense by ID
- Filter expenses by:
  - Expense type (`ExpenseType`)
  - Title
  - Category

---

### 🗂️ Category Management
- Create a category
- Get all categories (paginated)
- Get category by name
- Update category by name
- Delete category by name
- Prevent duplicate categories


## 📡 API Endpoints

### 🔑 Authentication

| Method | Endpoint | Description |
|------|--------|------------|
| POST | `/login` | Authenticate user & get JWT |

---

### 👤 Users

| Method | Endpoint | Description |
|------|--------|------------|
| POST | `/users/register` | Register a new user |
| GET | `/users` | Get all users (paginated) |
| GET | `/users/username/{username}` | Get user by username |
| PUT | `/users/id/{id}` | Update user by ID |
| PUT | `/users/username/{username}` | Update user by username |
| DELETE | `/users/id/{id}` | Delete user by ID |
| DELETE | `/users/{username}` | Delete user by username |

---

### 💳 Expenses

| Method | Endpoint | Description |
|------|--------|------------|
| POST | `/expenses/addExpense` | Add a new expense |
| GET | `/expenses/` | Get all expenses (paginated) |
| GET | `/expenses/type/{type}` | Filter by expense type |
| GET | `/expenses/title/{title}` | Filter by title |
| GET | `/expenses/category/{category}` | Filter by category |
| PUT | `/expenses/{id}` | Update expense by ID |
| DELETE | `/expenses/delete/{id}` | Delete expense by ID |

---

### 🗂️ Categories

| Method | Endpoint | Description |
|------|--------|------------|
| POST | `/categories/addCategory` | Create a category |
| GET | `/categories/` | Get all categories (paginated) |
| GET | `/categories/{name}` | Get category by name |
| PUT | `/categories/{name}` | Update category by name |
| DELETE | `/categories/delete/{name}` | Delete category by name |

---

## ⚠️ Exception Handling

- Global exception handling is implemented using `@ControllerAdvice`.

### Custom Exceptions
- User not found
- Duplicate username
- Expense not found
- Category not found
- Duplicate category

---

## ✅ Validation

- Request DTO validation using `@Valid`
- Prevents invalid or incomplete data
- Ensures clean API contracts

---

## 🛠️ Tech Stack

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token)
- Hibernate
- REST APIs
- Pageable & Pagination

---

## ▶️ How to Run

- Clone the repository
- Open in IntelliJ IDEA / Eclipse
- Configure database credentials in `application.properties`
- Run `ExpenseTrackerApplication`
- Authenticate via `/login` to obtain JWT
- Use JWT in Authorization header: Authorization: Bearer <token>

---

## 🔮 Future Enhancements

- Role-based authorization (Admin / User)
- Refresh tokens & token expiration handling
- Swagger / OpenAPI documentation
- Monthly expense reports & analytics
- Recurring expenses
- Docker & CI/CD support
- Audit logs & activity tracking

---

## 📝 Notes

- This project follows clean architecture principles
- Designed to be scalable and production-ready
- APIs and structure may evolve as features are added


