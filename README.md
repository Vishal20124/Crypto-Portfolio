# 💹 Crypto Portfolio Tracker

A Spring Boot-based backend application that allows users to:

- Track their cryptocurrency holdings
- Monitor real-time price changes
- Set alerts for fluctuations
- Evaluate their portfolio performance

---

## 🌍 Domain
**Finance / Cryptocurrency / Portfolio Management**

---

## 🎯 Objectives

- Enable users to add and manage their crypto assets.
- Provide real-time valuation using simulated or live price data.
- Allow users to set custom alerts based on price thresholds.
- Maintain secure user authentication and role-based access.
- Offer insights into portfolio performance with gains/losses.

---

## 🧰 Tech Stack

| Layer      | Technology                           |
|------------|--------------------------------------|
| Framework  | Spring Boot                          |
| Security   | Spring Security + JWT (optional)     |
| Persistence| Spring Data JPA                      |
| Database   | MySQL                                |
| Build Tool | Maven or Gradle                      |
| Utilities  | Lombok, ModelMapper (optional)       |
| Testing    | JUnit                                |
| Docs       | Swagger (springdoc-openapi)          |
| API        | CoinGecko (optional for real-time)   |

---

## 📦 Key Modules

1. **User & Role Management**
2. **Portfolio Management**
3. **Price Simulation & Tracking**
4. **Alerts & Notifications**
5. **Security & Authentication**

---

## 🧑‍💻 Roles & Access

### Entities Overview

- `User`: id, name, email, password  
- `CryptoHolding`: id, userId, coinName, symbol, quantityHeld, buyPrice, buyDate  
- `CryptoPrice`: symbol, currentPrice, timestamp  
- `Alert`: id, userId, symbol, triggerPrice, direction (above/below), status, triggeredAt  
- `PortfolioAsset`: id, coinName, symbol, quantityHeld, buyPrice, buyDate

### Role Access

| Role  | Access Description |
|-------|--------------------|
| Admin | Manage users       |
| User  | Add/update/delete crypto assets, view portfolio, manage alerts |

---

## 🔗 REST API Endpoints

### AuthController
- `POST /auth/register`

### CryptoController
- `GET /api/crypto/refresh-prices`
- `GET /api/crypto/valuation/{userId}`

### MarketController
- `GET /api/market/top-coins`

### PortfolioController
- `POST /portfolio/add`
- `GET /portfolio/my`
- `PUT /portfolio/update/{id}`
- `DELETE /portfolio/delete/{id}`

### AlertController
- `POST /alerts/create`
- `GET /alerts/triggered`
- `GET /alerts/test-price`
- `GET /alerts/my`

---

## 🧪 Example Workflow

1. Supplier registers an item and creates a shipment.  
2. Transporter updates shipment status and checkpoints.  
3. System logs each event and detects delivery delays.  
4. Alerts and reports are generated accordingly.

---

## 🧬 Sample `application.properties`

```properties
# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/crypto_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=Vishal

# Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.defer-datasource-initialization=true

# SQL Logging
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
debug=true
