# Game Service - SmileGate VN

This project is a backend service developed for **SmileGate Vietnam**.  
It provides REST APIs for managing games, categories, supported languages, and multilingual game names.  
The system is designed with scalability, clean architecture, and easy integration with front-end applications in mind.  

---

## 1. Introduction

The **Game Service** is a Monolithic Spring Boot application that manages game-related data with multilingual support.  
Core features include:

- Manage **games** (CRUD + pagination, search, filtering)  
- Manage **categories** as lookup table  
- Manage **game names** in multiple languages, with one default name required  
- Manage **languages** supported by the system  
- Provide REST APIs for integration with front-end or other services  
- Designed with clean architecture, following SOLID principles and design patterns (Repository, Mapper, Factory, Singleton)  

---

## 2. Database Schema

The service uses **MySQL** as its relational database.  

```sql
CREATE DATABASE IF NOT EXISTS game_service
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
USE game_service;

-- Categories
CREATE TABLE categories (
  code VARCHAR(36) PRIMARY KEY,
  display_name VARCHAR(100) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Games
CREATE TABLE games (
  id VARCHAR(50) PRIMARY KEY,
  category VARCHAR(36) NOT NULL,
  image VARCHAR(255),
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT fk_game_category FOREIGN KEY (category) REFERENCES categories(code)
);

-- Game names
CREATE TABLE game_names (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  game_id VARCHAR(50) NOT NULL,
  language CHAR(2) NOT NULL,
  value VARCHAR(255) NOT NULL,
  default_name BOOLEAN NOT NULL DEFAULT 0,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT fk_game FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE,
  CONSTRAINT fk_language FOREIGN KEY (language) REFERENCES languages(code)
);

-- Languages
CREATE TABLE languages (
  code CHAR(2) PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);
```

---

## 3. Installation Guide

### Prerequisites
- Java 17+  
- Maven 3.8+  
- MySQL 8.0+  

### Steps
```bash
# 1. Clone repository
git clone https://github.com/MinhAnh-IT/Game-Service---Smilegate-VN.git

# 2. Navigate to project
cd Game-Service---Smilegate-VN

# 3. Configure database credentials
# in src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/game_service?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

# 4. Build project
mvn clean install

# 5. Run
mvn spring-boot:run
```

Service runs at:  
 => `http://localhost:8080`  

---

## 4. API Usage Guide

### **Categories**
- `GET /api/categories` → Get all categories  
- `POST /api/categories` → Create a new category  
- `PUT /api/categories/{code}` → Update a category by code  
- `DELETE /api/categories/{code}` → Delete a category by code  

### **Games**
- `GET /api/games` → Get paginated list of games (supports filters: `category`, `keyword`)  
- `GET /api/games/{id}` → Get a specific game by ID  
- `POST /api/games` → Create a new game (multipart/form-data, supports image upload + names)  
- `PUT /api/games/{id}` → Update a game (multipart/form-data, only metadata, not names)  
- `DELETE /api/games/{id}` → Delete one game  
- `DELETE /api/games` → Delete multiple games (send body as JSON array of IDs)  

### **Game Names**
- `GET /api/games/{gameId}/names` → Get all names for a game  
- `GET /api/games/{gameId}/names/default` → Get the default name for a game  
- `GET /api/games/{gameId}/names/lang/{language}` → Get a game name by language  
- `POST /api/games/{gameId}/names` → Add a new game name  
- `PUT /api/games/{gameId}/names/{id}` → Update a game name  
- `DELETE /api/games/{gameId}/names/{id}` → Delete a game name  

### **Languages**
- `GET /api/languages` → Get all supported languages  

---

## 5. Example Request / Response

**Example: Get Categories**
```http
GET http://localhost:8080/api/categories
```

**Response**
```json
{
  "code": 200,
  "message": "Success",
  "data": [
    { "code": "ACTION", "displayName": "Action" },
    { "code": "RPG", "displayName": "Role-Playing Game" }
  ]
}
```

---

## 6. Technologies Used
- Java 17  
- Spring Boot 3.5.5  
- Spring Data JPA  
- MySQL 8  
- MapStruct (DTO mapping)  
- Lombok (reduce boilerplate)  
- Spring Validation (input validation)  

---

## 7. Contribution

Contributions are welcome!  

1. Fork repository  
2. Create a feature branch  
   ```bash
   git checkout -b feature/your-feature
   ```
3. Commit changes  
   ```bash
   git commit -m "feat: add your feature"
   ```
4. Push branch and open PR  

---

## 8. Repository

[GitHub Repository]: https://github.com/MinhAnh-IT/Game-Service---Smilegate-VN.git
