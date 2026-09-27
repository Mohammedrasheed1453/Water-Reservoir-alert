# Reservoir Water Monitoring API

A backend service for tracking reservoir stations, recording water-level readings, and automatically raising alerts when a reading crosses a danger threshold — built as practice for Vassar Labs' Track 1 (Software Engineer) hireathon.

## What it does

- Register reservoir stations with a name, location, and danger threshold
- Record water-level readings for a station
- Automatically raise an alert the moment a reading crosses the threshold — no manual step involved
- Store raw, unstructured sensor payloads separately, for cases where incoming data doesn't fit a fixed schema
- A simple web UI to demo the whole flow live

## Tech stack

- **Java 17**, **Spring Boot**
- **MySQL** — for structured, relational data (stations, readings, alerts)
- **MongoDB** — for flexible, unstructured raw sensor logs
- **JUnit 5 + Mockito** — unit tests on the core automation logic
- Plain HTML/CSS/JS — a lightweight demo UI, served directly by Spring Boot

## Why two databases

Stations, readings, and alerts have clear structure and clear relationships — a reading always belongs to one station, and an alert always traces back to one reading. That fits a relational model well, so these live in **MySQL**, where we get consistency and can enforce those relationships properly.

Raw sensor payloads, on the other hand, are unpredictable — different sensor firmware versions might send different fields, and forcing that into a rigid schema would mean constant migrations every time the format changes slightly. So raw logs are stored as-is in **MongoDB**, which doesn't require a fixed shape.

## Architecture
Client (Postman / browser UI)
│
▼
Controller layer → REST endpoints, request validation
│
▼
Service layer → business logic (threshold check + alert creation lives here)
│
┌────┴────┐
▼ ▼
MySQL MongoDB
(JPA) (raw logs)


## The core automation flow

1. A reading is submitted: `POST /stations/{id}/readings`
2. The reading is saved
3. The reading's water level is compared against the station's configured threshold
4. If it's crossed, an alert is automatically created — linked to both the station and the exact reading that triggered it
5. No manual step is involved anywhere in this chain

## API endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/stations` | Create a station |
| GET | `/stations` | List all stations |
| GET | `/stations/{id}` | Get one station |
| POST | `/stations/{id}/readings` | Record a reading (auto-checks threshold) |
| GET | `/stations/{id}/readings` | List a station's readings |
| GET | `/alerts` | List all alerts (optional `?severity=HIGH`) |
| GET | `/alerts/summary` | Alert counts grouped by severity |
| POST | `/stations/{id}/raw-logs` | Store a raw, unstructured sensor payload |

## Running locally

### Prerequisites
- Java 17+
- Maven
- MySQL running locally
- MongoDB running locally

### Setup

1. Create the database:
```sql
CREATE DATABASE reservoir_db;
```

2. Update `src/main/resources/application.properties` with your MySQL credentials:
```properties
spring.datasource.username=root
spring.datasource.password=your_password_here
```

3. Run the app:
mvn spring-boot:run


4. Open the demo UI:

http://localhost:8080/index.html


### Running tests

mvn test


## Example request

POST /stations/1/readings
Content-Type: application/json

{
"waterLevel": 95
}


If this exceeds the station's threshold, an alert is created automatically — visible immediately via `GET /alerts`.

<img width="1917" height="883" alt="image" src="https://github.com/user-attachments/assets/18d1b259-b03c-489a-abf1-9f48e53f8419" />
<img width="1910" height="717" alt="image" src="https://github.com/user-attachments/assets/c7a096e1-791c-45af-a048-00b5dbf8bc02" />

