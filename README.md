# Game Service - SmileGate VN

This project is a backend service developed for **SmileGate Vietnam**.  
It provides REST APIs for managing games, categories, and multilingual game names, supporting scalable operations and easy integration with front-end applications.

---

## 1. Introduction

The **Game Service** is a Monolithic Spring Boot application that provides a robust backend solution for managing game-related data. Core features include:

– Manage games (CRUD)
– Support multiple languages for game names
– Manage categories as a lookup table
– Provide REST APIs for integration with front-end or other services
– Designed with clean architecture principles and strictly following SOLID
– Apply common design patterns such as Singleton, Factory, Repository, and Mapper to ensure maintainability, scalability, and extensibility
---

## 2. Database Schema

The system uses **MySQL** as the relational database.  
Below is the SQL schema for creating the database and tables.

```sql
CREATE DATABASE IF NOT EXISTS game_service
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
USE game_service;

-- Game Categories (lookup table)
-- =========================
CREATE TABLE categories (
  code VARCHAR(32) PRIMARY KEY,       
  display_name VARCHAR(100) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Games table
-- =========================
CREATE TABLE games (
  id VARCHAR(50) PRIMARY KEY,          
  category VARCHAR(32) NOT NULL,
  image VARCHAR(255),                  
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT fk_game_category FOREIGN KEY (category) REFERENCES categories(code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Game names (multi-language support)
-- =========================
CREATE TABLE game_names (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  game_id VARCHAR(50) NOT NULL,
  language CHAR(2) NOT NULL,           -- EN, KO, JA
  value VARCHAR(255) NOT NULL,
  is_default BOOLEAN NOT NULL DEFAULT 0,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT fk_game FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

---

## 3. Installation Guide

### Prerequisites
- Java 17+  
- Maven 3.8+  
- MySQL 8.0+  

### Steps
1. Clone the repository
   ```bash
   git clone https://github.com/MinhAnh-IT/Game-Service---Smilegate-VN.git
   ```

2. Navigate to the project directory
   ```bash
   cd Game-Service---Smilegate-VN
   ```

3. Configure database 
   Update your `application.properties` or `application.yml` with your local MySQL credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/game_service?useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

4. **Build the project**
   ```bash
   mvn clean install
   ```

5. **Run the project**
   ```bash
   mvn spring-boot:run
   ```

The application will start at:  
`http://localhost:8080`

---

## 4. Usage Guide

### API Endpoints

- Categories
  - `GET /api/categories` → Get all categories  

- Games
  - `GET /api/games` → Get all games  
  - `GET /api/games/{id}` → Get a specific game by ID  
  - `POST /api/games` → Create a new game  
  - `PUT /api/games/{id}` → Update a game  
  - `DELETE /api/games/{id}` → Delete a game  
  - `DELETE /api/games` → Delete multiple games  

### Example Request
```http
GET http://localhost:8080/api/categories
```

 Example Response
```json
{
  "code": 200,
  "message": "Success",
  "data": [
    { "code": "ADVENTURE", "displayName": "Adventure" },
    { "code": "ACTION", "displayName": "Action" },
    { "code": "RPG", "displayName": "Role-Playing Game" }
  ]
}
```

---

## 5. Technologies Used

- Java 17
- Spring Boot 3.5.5
- Spring Data JPA
- MySQL 8
- MapStruct (DTO mapping)  
- Lombok (reduce boilerplate code)  
- Spring Validation (input validation)  

---

## 6. Contribution

Contributions are welcome!
If you want to contribute:  

1. Fork the project  
2. Create a new feature branch  
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. Commit your changes  
   ```bash
   git commit -m "feat: add your feature"
   ```
4. Push the branch  
   ```bash
   git push origin feature/your-feature-name
   ```
5. Open a Pull Request  

---

## 7. Repository

[GitHub Repository]: https://github.com/MinhAnh-IT/Game-Service---Smilegate-VN.git
