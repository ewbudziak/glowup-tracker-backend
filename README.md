# GlowUp Tracker Backend

Backend application for a habit-tracking system built with Spring Boot.  
The project supports user authentication with JWT, habit management, daily check-ins, and streak statistics.

## About the project

This project was built to practice designing a full backend application with authentication, ownership checks, relational data, and business logic beyond simple CRUD.

The system allows users to:
- register and log in
- create and manage habits
- mark habits as completed for the current day
- view check-in history
- track habit streak statistics
```md
The backend follows a layered architecture:

Controller → Service → Repository → Database

```

## Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JWT authentication
- Spring Data JPA / Hibernate
- MySQL
- Maven

## Features

- user registration
- login with JWT token generation
- password hashing with BCrypt
- secured endpoints with Spring Security
- create, list, update, and delete habits
- daily habit check-ins
- check-in history
- streak statistics:
    - done today
    - current streak
    - longest streak
- ownership validation for protected resources
- global exception handling

## API Endpoints

### Auth
- `POST /api/auth/register`
- `POST /api/auth/login`

### Habits
- `POST /api/habits`
- `GET /api/habits`
- `PUT /api/habits/{habitId}`
- `DELETE /api/habits/{habitId}`

### Check-ins
- `POST /api/habits/{habitId}/checkins`
- `GET /api/habits/{habitId}/checkins`

### Stats
- `GET /api/habits/{habitId}/stats`

## Authentication

All habit, check-in, and stats endpoints require a JWT token.

After logging in, include the token in the request header:

```http
Authorization: Bearer YOUR_TOKEN
```

## Example Requests

### Register 

```json
{
  "email": "test@test.com",
  "password": "password123"
}
```

### Login 
```json
{
  "email": "test@test.com",
  "password": "password123"
}
```

### Create Habit
```json
{
  "name": "Morning walk",
  "frequencyType": "DAILY",
  "targetPerWeek": 7
}
```

### Update Habit
```json
{
  "name": "Morning walk updated",
  "frequencyType": "DAILY",
  "targetPerWeek": 6
}
```

## Running Locally

### 1. Clone the repository 
` git clone https://github.com/ewbudziak/glowup-tracker-backend.git`
######
`cd glowup-tracker-backend`

### 2. Create a MySQL database
Example:
`CREATE DATABASE glowup_tracker;`

### 3. Set environment variables
The application uses environment variables for sensitive data.

Required variables:

- `DB_USERNAME`
- `DB_PASSWORD`
- `JWT_SECRET`

Example values:

- `DB_USERNAME=root`
- `DB_PASSWORD=your_password`
- `JWT_SECRET=your_long_random_secret_key`

### 4. Configure application properties

The application expects values such as:

`spring.datasource.username=${DB_USERNAME}`

`spring.datasource.password=${DB_PASSWORD}`

`security.jwt.secret=${JWT_SECRET}`

### 5. Run the application

Using Maven:
` ./mvnw spring-boot:run `
or directly from IntelliJ


## Project Structure
```md
src/main/java/com/ewelinabudziak/glowup_tracker
├── auth → authentication logic
├── habit → habits, check-ins, statistics
├── security → JWT configuration and filters
├── user → user entity and repository
└── exception → global exception handling
```

## What I Learned

Through this project I practiced:
- designing REST APIs with Spring Boot
- structuring code into controller, service, repository, entity, and DTO layers
- securing endpoints with JWT and Spring Security
- validating ownership of resources
- working with relational mappings in JPA
- handling exceptions consistently across the application
- implementing business logic such as habit streak calculation

## Future Improvements

- React frontend integration
- refresh tokens
- Swagger / OpenAPI documentation
- Docker support
- role-based authorization



