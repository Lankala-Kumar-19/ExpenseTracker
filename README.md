# 💸 Expense Tracker 

A **Spring Boot–based RESTful** for an Expense Tracker application.  
This project provides secure JWT authentication, expense tracking, and category management, following clean architecture and production-ready best practice.

**Live Demo:** [https://expense-tracker-tau-ten-61.vercel.app](https://expense-tracker-tau-ten-61.vercel.app)

---

## 🚀 Overview

The Expense Tracker backend allows users to:

- Register and manage accounts
- Authenticate securely using **JWT**
- Create, update, delete, and filter expenses
- Organize expenses using categories
- Access APIs via Swagger / OpenAPI
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
```

---
## 🔐 Security & Authentication

### 🔒 Authentication Mechanism
- **Spring Security–based authentication**
- **Stateless JWT authentication**
- **Secure `/login` endpoint**
- **Password hashing using BCrypt**
- **Custom JWT filter** for request validation
- **Custom `UserDetailsService` implementation**



### 🔄 Authentication Flow

1. User sends credentials to the **`/login`** endpoint  
2. Credentials are authenticated using **`AuthenticationManager`**  
3. Upon successful authentication, a JWT token is generated
4. The JWT contains:
   - username (subject)
   - role (USER / ADMIN)
   - expiration time
5. The token must be included in requests using:
   Authorization: Bearer <token>

---
## 🛡️ Role-Based Access Control (RBAC)

The application supports **role-based authorization** using Spring Security.

### Roles
- `USER` – Default role for registered users
- `ADMIN` – Elevated privileges for system management

### Enforcement
- Roles are embedded inside the JWT as claims
- Authorization is enforced using:
  - `@PreAuthorize`
  - `hasRole("ADMIN")`
- Admin-only endpoints are protected under `/admin/**`

### Admin Capabilities
- View all users
- Change user roles
- Delete users (cannot delete self)
- View, update, and delete **any expense**
- Create, update, and delete **global categories**

---


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
- Get expense by ID
- Update expense by ID
- Delete expense by ID
- Filter expenses by:
  - Expense type
  - Title
  - Category

---

### 🗂️ Category Management
- Create a category
- Get all categories (paginated)
- Get category by id
- Update category by id
- Delete category by id
- Prevent duplicate categories

---

## 🔐 Security Highlights

- Stateless JWT authentication
- Role-based authorization (USER / ADMIN)
- BCrypt password hashing
- Admin privilege isolation
- Self-deletion prevention for admins
- No database hit on each request (JWT-based auth)

---


## 📡 API Endpoints

### 🔑 Authentication

| Method | Endpoint | Description |
|------|--------|------------|
| POST | `/login` | Authenticate user & get JWT |

---

### 👤 Users

| Method | Endpoint | Description |
|------|--------|------------|
| POST | `/users` | Register a new user |
| GET | `/users` | Get all users (paginated) |
| GET | `/users/username/{username}` | Get user by username |
| PUT | `/users/id/{id}` | Update user by ID |
| PUT | `/users/username/{username}` | Update user by username |
| DELETE | `/users/id/{id}` | Delete user by ID |
| DELETE | `/users/username/{username}` | Delete user by username |

---

### 💳 Expenses

| Method | Endpoint | Description |
|------|--------|------------|
| POST | `/expenses` | Add a new expense |
| GET | `/expenses` | Get all expenses (paginated) |
| GET | `/expenses/{id}` | Get expense by ID |
| GET | `/expenses/type/{type}` | Filter by expense type |
| GET | `/expenses/title/{title}` | Filter by title |
| GET | `/expenses/category/{category}` | Filter by category |
| PUT | `/expenses/{id}` | Update expense by ID |
| DELETE | `/expenses/{id}` | Delete expense by ID |

---

### 🗂️ Categories

| Method | Endpoint | Description |
|------|--------|------------|
| POST | `/categories` | Create a category |
| GET | `/categories` | Get all categories (paginated) |
| GET | `/categories/{id}` | Get category by id |
| PUT | `/categories/{id}` | Update category by id |
| DELETE | `/categories/{id}` | Delete category by id |

---

### 🔐 Admin APIs (ADMIN role required)

| Method | Endpoint | Description |
|------|--------|------------|
| GET | `/admin/users` | Get all users (paginated) |
| GET | `/admin/users/{username}` | Get user by username |
| PUT | `/admin/users/{id}/role` | Change user role |
| DELETE | `/admin/users/{id}` | Delete user |
| GET | `/admin/expenses` | Get all expenses |
| PUT | `/admin/expenses/{id}` | Update any expense |
| DELETE | `/admin/expenses/{id}` | Delete any expense |
| GET | `/admin/categories` | Get all categories |
| POST | `/admin/categories` | Create category |
| PUT | `/admin/categories/{name}` | Update category |
| DELETE | `/admin/categories/{name}` | Delete category |

---

# 📘 Swagger / OpenAPI Documentation

⚠️ Swagger is enabled for development purposes only.


🔗 **Access it here:**  
[http://localhost:8080/swagger-ui/index.html](https://expensetracker-2z0y.onrender.com/swagger-ui/index.html#/admin-controller/updateExpense)

---

## ⚠️ Exception Handling

- Global exception handling is implemented using `@ControllerAdvice`.

- All exceptions are handled in a single centralized class
- Each exception is mapped to a predefined `ErrorCode`
- HTTP status codes are derived from the associated `ErrorCode`
- Ensures consistent error responses across all APIs

### Supported Error Codes
- USER_NOT_FOUND
- DUPLICATE_USERNAME
- CATEGORY_NOT_FOUND
- EXPENSE_NOT_FOUND
- DUPLICATE_CATEGORY

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

- Refresh tokens & token expiration handling
- Fine-grained permissions (ADMIN_READ, ADMIN_WRITE)
- Monthly expense reports & analytics
- Recurring expenses
- Audit logs & activity tracking

---

## 📝 Notes

- This project follows clean architecture principles
- Designed to be scalable and production-ready
- Swagger-documented and production-ready
- APIs and structure may evolve as features are added


