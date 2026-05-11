# Task Manager App - Backend

Spring Boot backend for a task management application. This project exposes a REST API for creating, reading, updating, and deleting tasks, and persists data in MySQL using Spring Data JPA.

## Features

- RESTful CRUD API for tasks
- MySQL persistence with Spring Data JPA
- Request validation for required task fields
- Automatic `createdAt` timestamp on task creation
- CORS configured for an Angular frontend running on `http://localhost:4200`

## Tech Stack

- Java 21
- Spring Boot 4.0.4
- Spring Web MVC
- Spring Data JPA
- Spring Validation
- MySQL
- Lombok

## Project Structure

```text
src/
	main/
		java/com/thushara/task_manager_backend/
			controller/TaskController.java
			model/entity/Task.java
			repository/TaskRepository.java
			service/TaskService.java
		resources/application.properties
	test/
		java/com/thushara/task_manager_backend/
			TaskManagerBackendApplicationTests.java
```

## Prerequisites

Make sure the following are installed on your machine:

- Java 21 or later
- Maven 3.9+ or use the included Maven Wrapper
- MySQL 8+ running locally

## Database Setup

The application connects to MySQL using the following defaults from `application.properties`:

- Database: `task-manager-app_db`
- Host: `localhost`
- Port: `3306`
- Username: `root`

If your local MySQL configuration is different, update:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/task-manager-app_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=<Your Password>
```

The database will be created automatically if it does not already exist.

## Run Locally

### Using Maven Wrapper

```bash
./mvnw spring-boot:run
```

### Using Maven

```bash
mvn spring-boot:run
```

The application starts on the default Spring Boot port:

```text
http://localhost:8080
```

## Build the Project

```bash
./mvnw clean package
```

This creates the runnable JAR inside the `target/` directory.

## API Endpoints

Base path: `/api/tasks`

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/api/tasks` | Get all tasks |
| GET | `/api/tasks/{id}` | Get a task by ID |
| POST | `/api/tasks` | Create a new task |
| PUT | `/api/tasks/{id}` | Update an existing task |
| DELETE | `/api/tasks/{id}` | Delete a task |

## Task Model

```json
{
	"id": 1,
	"title": "Finish project documentation",
	"description": "Write a complete README for GitHub",
	"status": "In Progress",
	"createdAt": "2026-05-11T14:30:00"
}
```

### Required Fields

- `title` is required
- `description` is optional
- `status` is optional

## API Examples

### Create a Task

```bash
curl -X POST http://localhost:8080/api/tasks \
	-H "Content-Type: application/json" \
	-d '{
		"title": "Build backend API",
		"description": "Implement task CRUD endpoints",
		"status": "Open"
	}'
```

### Get All Tasks

```bash
curl http://localhost:8080/api/tasks
```

### Get Task By ID

```bash
curl http://localhost:8080/api/tasks/1
```

### Update a Task

```bash
curl -X PUT http://localhost:8080/api/tasks/1 \
	-H "Content-Type: application/json" \
	-d '{
		"title": "Build backend API",
		"description": "Add validation and persistence",
		"status": "Completed"
	}'
```

### Delete a Task

```bash
curl -X DELETE http://localhost:8080/api/tasks/1
```

## Frontend Integration

This backend already allows requests from an Angular frontend running at:

```text
http://localhost:4200
```

If your frontend runs on a different origin, update the `@CrossOrigin` value in `TaskController`.

## Testing

Run the test suite with:

```bash
./mvnw test
```

## Notes

- The application uses `spring.jpa.hibernate.ddl-auto=update`, so the schema is managed automatically during development.
- Do not keep hardcoded database credentials in production. Move them to environment variables or a secure secrets manager before deployment.

## Troubleshooting

- If the app cannot connect to MySQL, confirm the database server is running and the credentials in `application.properties` are correct.
- If port `8080` is already in use, stop the other service or change `server.port` in `application.properties`.
- If Lombok errors appear in your IDE, make sure Lombok support is enabled.

## License

No license has been defined for this project yet.
=======
>>>>>>> Stashed changes
# Task Manager Application

A professional Full-Stack Task Management System designed for efficient task tracking and team collaboration. [cite_start]This application allows users to manage tasks through a modern interface while ensuring data persistence through a robust backend.

---

##  Tech Stack

### **Frontend**
- **Framework:** Angular v21.1.4
- **Styling:** Tailwind CSS (Modern Glassmorphism UI) 
- **Forms:** Reactive Forms for complex validations and scalability 
- **Testing:** Vitest

### **Backend**
- **Framework:** Java Spring Boot 
- **Database:** MySQL 
- **Architecture:** Controller-Service-Repository pattern
- **Build Tool:** Maven.

---

##  Project Structure

- **`/frontend`**: Angular source code including components for task forms, dashboards, and API services.
- **`/backend`**: Spring Boot REST API for managing tasks, entity models, and data persistence.

---

##  Setup & Installation

### **Prerequisites**
- Java 17 or higher
- Node.js & npm (Angular CLI)
- MySQL Server
- Maven

### **1. Database Configuration**
1. Create a MySQL database named `task_manager`.
2. Open `/backend/src/main/resources/application.properties`.
3. Update the following lines with your MySQL credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/task_manager
   spring.datasource.username=YOUR_USERNAME
   spring.datasource.password=YOUR_PASSWORD
   spring.jpa.hibernate.ddl-auto=update
