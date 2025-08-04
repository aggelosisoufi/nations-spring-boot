# Nation Service Backend

Full‑stack project backend built with **Spring Boot 2.7.18** and **Java 8**.  
Provides REST APIs for countries, languages, regions, and advanced country statistics filtering with pagination and Swagger documentation.

---

## 📦 Tech Stack

- **Java 8**
- **Spring Boot 2.7.18**
- **Spring Data JPA / Hibernate**
- **MariaDB 10.11**
- **Swagger / OpenAPI 3 (springdoc‑openapi)**
- **Docker** (for MariaDB)
- **Maven**

---

## 🚀 Features

1. **Countries API**
    - `GET /api/countries/all` → Returns all countries ordered by name (asc)

2. **Country Languages API**
    - `GET /api/country-languages/{countryId}` → Returns all languages spoken in a country

3. **Regions API**
    - `GET /api/regions` → Returns all regions (used for dropdown in the Angular frontend)

4. **Country Stats API**
    - `GET /api/country-stats/max-gdp-ratio` → Returns each country’s record with the highest GDP‑to‑population ratio
    - `POST /api/country-stats/search` → Advanced filtering with:
        - Region
        - Year range
        - Population
        - GDP
    - Supports **pagination** and **sorting** via `page`, `size`, `sort` query params

---

## 📂 Project Structure

```
src/main/java/com/isoufi/angelos/nationservice
  ├── config                   # Database configuration (MariaDB)
  ├── controller               # REST controllers
  ├── dto                      # Data Transfer Objects
  ├── entity.mariadb           # JPA entities (MariaDB)
  ├── exception                # Custom exception handlers (if implemented)
  ├── filter                   # Advanced filters (BaseFilter, CountryStatAdvancedFilter)
  ├── projection               # JPA projections for optimized queries
  ├── repository.mariadb       # Spring Data JPA repositories
  ├── service                  # Service layer
  ├── specification            # JPA Specifications for dynamic queries
  │    └── CountryStatSpecification.java
  └── NationServiceApplication.java
```

---

## ⚙️ Database Setup (Docker + MariaDB)

1. **Run MariaDB via Docker**

```bash
docker compose up -d
```

`docker-compose.yml`:

```yaml
services:
  mariadb:
    image: mariadb:10.11
    container_name: mariadb_nation
    platform: linux/arm64/v8
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: nation
      MYSQL_USER: nation_user
      MYSQL_PASSWORD: nation_pass
    ports:
      - "3306:3306"
    volumes:
      - mariadb_data:/var/lib/mysql
      - ./nation.sql:/docker-entrypoint-initdb.d/nation.sql:ro

volumes:
  mariadb_data:
```

2. **Verify Database Connection**

Default credentials:

```
Host: localhost
Port: 3306
User: nation_user
Password: nation_pass
Database: nation
```

3. **Import Sample Data**

If using Docker with the mounted `nation.sql`, the data loads automatically.  
Otherwise, import manually:

```bash
mysql -h localhost -u nation_user -p nation < nation.sql
```

---

## ⚙️ Spring Boot Configuration

`application.yml` example:

```yaml
server:
  port: 8888

spring:
  datasource:
    mariadb:
      url: jdbc:mariadb://localhost:3306/nation
      username: nation_user
      password: nation_pass
  jpa:
    hibernate:
      ddl-auto: none
    show-sql: true
    properties:
      hibernate.format_sql: true
```

---

## ▶️ Run the Backend

```bash
mvn clean spring-boot:run
```

Access:

- Backend: **http://localhost:8888**
- Swagger UI: **http://localhost:8888/swagger-ui/index.html**

---

## 📄 Example API Requests

**1. Get All Countries**

```
GET http://localhost:8888/api/countries/all
```

**Response:**
```json
[
  { "id": 1, "name": "Albania", "area": 28748.00, "countryCode2": "AL" },
  { "id": 2, "name": "Belgium", "area": 30528.00, "countryCode2": "BE" }
]
```

---

**2. Get Languages for a Country**

```
GET http://localhost:8888/api/country-languages/1
```

**Response:**
```json
["English", "French", "German"]
```

---

**3. Get Regions (for dropdown)**

```
GET http://localhost:8888/api/regions
```

**Response:**
```json
[
  { "id": 1, "name": "Western Europe" },
  { "id": 2, "name": "Eastern Europe" }
]
```

---

**4. Advanced Search for Country Stats**

```
POST http://localhost:8888/api/country-stats/search?page=0&size=10&sort=year,desc
```

**Body:**
```json
{
  "regionId": { "equals": 1 },
  "year": { "from": 2000, "to": 2020 }
}
```

---

## ✅ Notes

- Uses **Swagger/OpenAPI** for API documentation and testing
- **CORS** configured for `http://localhost:4200` (Angular frontend)
- DTOs are built using **Builder pattern** to avoid exposing JPA entities
- Optimized **batch inserts & Hibernate properties** for efficiency
