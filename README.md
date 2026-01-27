# Expense Tracker (Backend)

🚧 **Work in Progress** 🚧  

This is a Spring Boot–based backend for an Expense Tracker application.  
The project currently implements **User CRUD operations** along with **secure authentication and authorization using Spring Security and JWT**.

---

## Features (Implemented)

### User Management
- Create/Register a user
- Get all users with pagination
- Update user by:
  - ID
  - Username
- Delete user by:
  - ID
  - Username

### Security & Authentication
- User authentication using Spring Security
- JWT-based stateless authentication
- Secure login endpoint that issues JWT tokens
- Password hashing using BCrypt
- Role-based access setup (ready for admin & regular users)
- Custom JWT filter for request validation

---

## Tech Stack
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- JWT (JSON Web Tokens)
- REST APIs
- Pagination using `Pageable`

---

## Status
- ✅ User CRUD completed
- ✅ Spring Security integrated
- ✅ JWT authentication implemented
- ⏳ Expense module (planned)
- ⏳ Validation & Exception handling (planned)

---

## Future Enhancements
- Expense CRUD operations
- Full role-based access control
- Global exception handling
- Input validation
- Swagger/OpenAPI documentation
- Refresh tokens & token expiration handling

---

## How to Run
1. Clone the repository
2. Open the project in IntelliJ IDEA / Eclipse
3. Configure database details in `application.properties`
4. Run the Spring Boot application
5. Use the `/login` endpoint to authenticate and obtain a JWT

---

## Notes
This project is under active development.  
APIs and structure may evolve as new features are added.  

The backend follows **stateless, production-ready authentication practices** using JWT and Spring Security, making it suitable for real-world RESTful applications.
