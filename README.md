# 💸 simplified-picpay-challange

A backend project developed as a **challenge proposed by PicPay**, with several improvements and enhancements.  
In this project, I implemented **JUnit tests**, **validations**, **exception handling**, **DDD with bounded contexts**, and a **layered architecture**.

This is a **Java and Spring Boot API** designed to manage **user CRUD operations** and **financial transactions** between users.  
It also includes **Docker** containerization, **H2Database** as the cache database, and **CI/CD integration** for continuous deployment. 🚀

___It’s worth mentioning that this is a backend-only project built entirely with Java and Spring Boot.___

---

## 🚀 Technologies Used

- **Java** ☕
- **Spring Boot** 🌱
- **H2 Database** 💾
- **Docker** 🐋
- **JUnit** 🧪
- **Mockito** 🎭
- **DDD (Domain-Driven Design)** 🏛️
- **CI/CD** ⚙️

---

## 🔧 How It Works

1. The API allows creating users and performing secure financial transactions between them.
2. Each transaction validates if the sender has sufficient balance and the correct permissions.
3. All requests and responses follow **RESTful** standards, with proper exception handling and validation layers.
4. The application architecture follows **DDD and clean principles**, dividing the code into clear bounded contexts.

---

## 🧱 Project Structure

```
picpay-challenge-api/
│
├── src/
│   ├── main/
│   │   ├── java/          # Source code
│   │   └── resources/     # application.properties
│   └── test/              # JUnit tests
│
├── Dockerfile
├── docker-compose.yml
└── pom.xml
```

---

## ⚙️ Configuration

Configure your `application.properties` file to connect to PostgreSQL if you're using docker compose file:

```properties
spring.datasource.url=${POSTGRE_DATABASE_URL}
spring.datasource.username=${POSTGRE_USERNAME_ADMIN}
spring.datasource.password=${POSTGRE_PASSWORD_ADMIN}
spring.jpa.hibernate.ddl-auto=update
```

Or use the standard configuration for H2Database, like this:

```properties
spring-datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

---

## 📦 Docker Setup

To build and run the containerized environment:

```sh
docker-compose up --build
```

---

## 🧾 Example Request

**POST /transaction**

**Body:**
```json
{
  "amount": 100.0,
  "senderId": 4,
  "receiverId": 15
}
```

**POST /users**

**Body:**
```json
{
    "firstName": "Vítor",
    "lastName": "Lougon",
    "document": "99911199922",
    "email": "vitor.lougon@gmail.com",
    "password": "T3est!@#",
    "balance": 1000,
    "userType": "COMMON"
}
```

---

## 📜 License

This project is licensed under the **MIT License** — feel free to use and improve it! 😊

---

Made with ❤️ and **Java** by [VitorLougon](https://github.com/LougonVitor)! 🚀  