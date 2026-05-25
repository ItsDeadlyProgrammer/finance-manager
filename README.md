# 💰 Personal Finance Manager API

A comprehensive Personal Finance Manager system developed using Kotlin and Spring Boot that allows users to manage income, expenses, categories, savings goals, and reports through REST APIs and a simple dashboard interface.

---

# 🚀 Features

## ✅ User Authentication
- User Registration
- User Login
- Session-based Authentication
- Secure Session Handling
- Logout Support

---

## ✅ Transaction Management
- Create Transactions
- Update Transactions
- Delete Transactions
- View Transactions
- Income & Expense Tracking
- Filtering Support

---

## ✅ Category Management
- Default Categories
- Custom Categories
- Category Validation
- Delete Custom Categories

---

## ✅ Savings Goals
- Create Savings Goals
- Track Goal Progress
- Remaining Amount Calculation
- Goal Updates & Deletion

---

## ✅ Reports & Analytics
- Monthly Reports
- Yearly Reports
- Net Savings Calculation
- Category-wise Analysis

---

# 🛠️ Tech Stack

| Component | Technology |
|---|---|
| Language | Kotlin |
| Framework | Spring Boot 3 |
| Security | Spring Security |
| Database | H2 Database |
| Build Tool | Gradle |
| Testing | JUnit / API Testing |
| Public Access | ngrok |

---

# 📂 Project Structure

```text
src/
 ├── controller/
 ├── service/
 ├── repository/
 ├── model/
 ├── dto/
 ├── exception/
 └── config/
```

---

# ⚙️ Setup Instructions

## 1️⃣ Clone Repository

```bash
git clone https://github.com/ItsDeadlyProgrammer/finance-manager
cd finance-manager
```

---

## 2️⃣ Build Project

```bash
gradle clean build
```

---

## 3️⃣ Run Application

### Using Gradle

```bash
gradle bootRun
```

### OR Using Generated JAR

```bash
java -jar build/libs/app.jar
```

---

# 🌐 Application Access

## Dashboard

```text
http://localhost:8080
```

---

# 🔗 API Base URL

```text
http://localhost:8080/api
```

---

# 🌍 Live Demo

Temporary public deployment using ngrok tunnel:

```text
 https://ranged-divisive-duh.ngrok-free.dev 
```
---

# 🔐 Authentication APIs

## Register User

### Endpoint

```http
POST /api/auth/register
```

### Request

```json
{
  "username": "user@example.com",
  "password": "password123",
  "fullName": "John Doe",
  "phoneNumber": "+1234567890"
}
```

---

## Login

### Endpoint

```http
POST /api/auth/login
```

### Request

```json
{
  "username": "user@example.com",
  "password": "password123"
}
```

---

## Logout

### Endpoint

```http
POST /api/auth/logout
```

---

# 💳 Transaction APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | /api/transactions | Create Transaction |
| GET | /api/transactions | Get Transactions |
| PUT | /api/transactions/{id} | Update Transaction |
| DELETE | /api/transactions/{id} | Delete Transaction |

---

# 📁 Category APIs

| Method | Endpoint | Description |
|---|---|---|
| GET | /api/categories | Get Categories |
| POST | /api/categories | Create Category |
| DELETE | /api/categories/{id} | Delete Category |

---

# 🎯 Goal APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | /api/goals | Create Goal |
| GET | /api/goals | Get All Goals |
| GET | /api/goals/{id} | Get Goal |
| PUT | /api/goals/{id} | Update Goal |
| DELETE | /api/goals/{id} | Delete Goal |

---

# 📊 Report APIs

| Method | Endpoint |
|---|---|
| GET | /api/reports/monthly/{year}/{month} |
| GET | /api/reports/yearly/{year} |

---

# 🖥️ Frontend Dashboard

The project contains integrated frontend testing pages:

- auth-test.html
- category-test.html
- transaction-test.html
- goal-test.html
- report-test.html

All accessible from:

```text
http://localhost:8080
```

through the central dashboard homepage.



# 🧪 Testing

The APIs were tested using:
- Browser Dashboard Pages
- cURL
- Automated API Testing Scripts

---

# ⚠️ Important Note

The application runs correctly in the local Spring Boot environment.

Due to deployment/runtime configuration limitations encountered during cloud hosting, public access has been temporarily exposed using an ngrok tunnel for evaluation purposes.

If the ngrok URL expires, the project can still be executed locally using the provided setup instructions.

---

# 📝 Additional Comments

Please review the project using the provided GitHub repository and ngrok deployment link.

The backend APIs, dashboard pages, authentication flow, CRUD operations, reports, and savings goal functionality work correctly when the Spring Boot application server is running locally.

If any issue occurs with the public ngrok tunnel expiration, the application can be started locally using:

```bash
gradle bootRun
```

or

```bash
java -jar build/libs/app.jar
```

All source code, API endpoints, frontend pages, and screenshots are included in the submission.

---

# 👨‍💻 Author

Harshvardhan Singh 

