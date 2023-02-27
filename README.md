# Loan Application System
This is a loan application system that allows users to apply for loans and view their loan status.

## Technologies Used
* Monolithic Architecture
* Java 17
* Spring Boot 3.0.1
* Spring Security
* JPA
* MySQL 8.0
* Docker
* JWT
* JUNIT
* Swagger
## API Documentation
* The API documentation for this application is available at http://localhost:8080/swagger-ui/index.html
## Setup

Clone the project

```bash
  https://github.com/mustafa-erduran/loan-application-system.git
```

Go to the project directory

```bash
  cd loan-application-system
```
Start the Docker containers (The docker compose up command automatically creates the image)

```bash
  docker compose up
```
## Endpoints
**This application exposes the following endpoints:**

* POST   /api/v1/loan/application: Creates a new loan application.
* GET    /api/v1/loan/result/{citizenId}/{birthDate}: Shows a loan application result.
* POST   /api/v1/auth/register: Creates a new user.
* POST   /api/v1/auth/authentication: Creates a new token for acces other endpoints.
* GET    /api/v1/users: Shows all users info.
* GET    /api/v1/users/{id}: Shows a desired user info.
* DELETE /api/v1/users/{id}: Deletes a desired user.
* DELETE /api/v1/users: Deletes all users.

Contributing
If you'd like to contribute to this project, please fork the repository and submit a pull request with your changes.
