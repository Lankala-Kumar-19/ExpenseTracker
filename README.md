# Expense Tracker (Backend)

🚧 **Work in Progress** 🚧  

This is a Spring Boot–based backend for an Expense Tracker application.  
Currently, the project implements **basic User CRUD operations** and **user authentication & authorization** using Spring Security.  

---

## Features (Implemented)
- Create/Register a user
- Get all users with pagination
- Update user by:
  - ID
  - Username
- Delete user by:
  - ID
  - Username
- **Secure authentication using Spring Security**
- **Password hashing with BCrypt**
- **Role-based access setup** (ready for admin/regular users)

---

## Tech Stack
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- REST APIs
- Pagination using `Pageable`

---

## Status
- ✅ User CRUD completed
- ✅ Authentication & Authorization integrated
- ⏳ Expense module (planned)
- ⏳ Validation & Exception handling (planned)

---

## Future Enhancements
- Expense CRUD operations
- JWT-based authentication for stateless APIs
- Full role-based access control
- Global exception handling
- Input validation
- Swagger/OpenAPI documentation

---

## How to Run
1. Clone the repository
2. Open the project in IntelliJ IDEA / Eclipse
3. Configure database details in `application.properties`
4. Run the Spring Boot application

---

## Notes
This project is under active development.  
APIs and structure may change as new features are added.  
Spring Security integration ensures that user data is protected and the backend is production-ready.
