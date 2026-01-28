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

### Expense Management
- Add a new expense
- Retrieve expenses (with pagination)
- Update and delete expenses by ID
- Filter expenses by:
  - Type (`ExpenseType` enum)
  - Title
  - Category

### Category Management
- Add a new category
- Retrieve all categories (with pagination)
- Get, update, or delete category by name
- Prevent duplicate categories

### Security & Authentication
- User authentication using Spring Security
- JWT-based stateless authentication
- Secure login endpoint that issues JWT tokens
- Password hashing using BCrypt
- Role-based access setup (ready for admin & regular users)
- Custom JWT filter for request validation

### Exception Handling
- Global exception handler using `@ControllerAdvice`
- Custom exceptions for:
  - User not found
  - Duplicate username
  - Expense not found
  - Category not found
  - Duplicate category
- Returns structured error responses with timestamp, status, error, and message

### Validation
- Input validation for request DTOs using `@Valid`
- Prevents invalid or incomplete data submissions

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

## API Endpoints

### Users
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST | `/users/register` | Register a new user |
| GET | `/users/` | Get all users (paginated) |
| PUT | `/users/{id}` | Update user by ID |
| PUT | `/users/username/{username}` | Update user by username |
| DELETE | `/users/{id}` | Delete user by ID |
| DELETE | `/users/username/{username}` | Delete user by username |

### Expenses
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST | `/expenses/addExpense` | Add a new expense |
| GET | `/expenses/` | Get all expenses (paginated) |
| GET | `/expenses/type/{type}` | Filter expenses by type |
| GET | `/expenses/title/{title}` | Filter expenses by title |
| GET | `/expenses/category/{category}` | Filter expenses by category |
| PUT | `/expenses/{id}` | Update expense by ID |
| DELETE | `/expenses/delete/{id}` | Delete expense by ID |

### Categories
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST | `/categories/addCategory` | Add a new category |
| GET | `/categories/` | Get all categories (paginated) |
| GET | `/categories/{name}` | Get category by name |
| PUT | `/categories/{name}` | Update category by name |
| DELETE | `/categories/delete/{name}` | Delete category by name |

---

## How to Run
1. Clone the repository
2. Open the project in IntelliJ IDEA / Eclipse
3. Configure database details in `application.properties`
4. Run the Spring Boot application
5. Use the `/login` endpoint to authenticate and obtain a JWT

---

## Future Enhancements

The following features are planned for upcoming versions of the Expense Tracker backend:

### Security & Authentication
- Full role-based access control for different user types (admin vs regular users)
- Refresh tokens and token expiration handling
- Multi-factor authentication (MFA) support

### API & Data Management
- Advanced filtering, sorting, and reporting for expenses
- Bulk import/export of expenses and categories
- Recurring expense tracking

### User Experience
- Email notifications for expense summaries or alerts
- Improved input validation messages
- Activity logs and audit trail for users

### Documentation & DevOps
- Swagger/OpenAPI documentation for all endpoints
- Integration with CI/CD pipelines for automated testing and deployment
- Docker and Kubernetes support for containerized deployments

### Analytics & Insights
- Dashboard with visual charts and summaries
- Expense category trends and monthly summaries
- Predictive insights based on historical expense data

---

## Notes
This project is under active development.  
APIs and structure may evolve as new features are added.  

The backend follows **stateless, production-ready authentication practices** using JWT and Spring Security, making it suitable for real-world RESTful applications.
