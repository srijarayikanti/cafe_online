# 🛍️ Cafe Online — Customer Portal (Spring Boot)

The **Cafe Online** module is the customer-facing portal of the Cafe Management System. It provides a seamless online ordering experience with product catalog browsing, cart management, and order summary features — all powered by a secure Spring Boot backend.

---

## 🚀 Features

- 🗂️ Product Catalog — browse available menu items with details & pricing
- 🛒 Customer Cart — add/remove items, real-time total calculation
- 📋 Order Summary — view itemized order breakdown before checkout
- 🔐 Secure JWT-based Authentication
- 💾 Transactional Cart Operations with rollback support
- 🗃️ MySQL Integration via Spring Data JPA

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3.x |
| Security | Spring Security + JWT |
| ORM | Spring Data JPA + Hibernate |
| Database | MySQL |
| Build Tool | Maven |
| API Style | RESTful APIs |

---

## ⚙️ Setup & Run

### Prerequisites
- Java 17+
- MySQL 8+
- Maven 3.6+

### Steps

```bash
# 1. Clone the repository
git clone https://github.com/srijarayikanti/cafe_online.git
cd cafe_online

# 2. Create MySQL database
CREATE DATABASE cafe_online;

# 3. Update application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/cafe_online
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

# 4. Build and run
mvn clean install
mvn spring-boot:run
```

---

## 📡 Key API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/products` | Get all available products |
| GET | `/api/products/{id}` | Get product details |
| POST | `/api/cart/add` | Add product to cart |
| DELETE | `/api/cart/remove/{id}` | Remove item from cart |
| GET | `/api/cart/summary` | Get cart with total |
| POST | `/api/order/confirm` | Confirm and place order |

---

## 🔗 Related Repositories

- **Main Backend:** [Cafe_management_system_java_springboot](https://github.com/srijarayikanti/Cafe_management_system_java_springboot)
- **Frontend (React):** [cafe-ui](https://github.com/srijarayikanti/cafe-ui)

---

## 👩‍💻 Author

**Rayikanti Srija** — Java Backend Developer  
[GitHub](https://github.com/srijarayikanti) | [LinkedIn](https://linkedin.com/in/srija-srinivas-593121238/)
