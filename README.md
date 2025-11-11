# 💸 simplified-picpay-challange

A backend project developed as a **challenge proposed by PicPay**, with several improvements and enhancements.  
In this project, I implemented **JUnit tests**, **validations**, **exception handling**, **DDD with bounded contexts**, and a **layered architecture**.

This is a **Java and Spring Boot API** designed to manage **user CRUD operations** and **financial transactions** between users.  
It also includes **JWT authentication**, **Docker** containerization, **PostgreSQL** as the database, and **CI/CD integration** for continuous deployment. 🚀

___It’s worth mentioning that this is a backend-only project built entirely with Java and Spring Boot.___

---

## 🚀 Technologies Used

- **Java** ☕
- **Spring Boot** 🌱
- **PostgreSQL** 🐘
- **Docker** 🐋
- **JUnit** 🧪
- **JWT Authentication** 🔐
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

Configure your `application.properties` file to connect to PostgreSQL:

```properties
spring.datasource.url=${POSTGRE_DATABASE_URL}
spring.datasource.username=${POSTGRE_USERNAME_ADMIN}
spring.datasource.password=${POSTGRE_PASSWORD_ADMIN}
```

Or use a local configuration, for example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/picpay
spring.datasource.username=your-username
spring.datasource.password=your-password
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

**Headers:**
```
Content-Type: application/json
Authorization: Bearer <your_jwt_token>
```

**Body:**
```json
{
  "amount": 100.0,
  "senderId": 4,
  "receiverId": 15
}
```

---

## 📜 License

This project is licensed under the **MIT License** — feel free to use and improve it! 😊

---

Made with ❤️ and **Java** by [VitorLougon](https://github.com/LougonVitor)! 🚀  