# Invoice Generator Application

## 📌 Description
A Spring Boot application that generates professional invoice PDFs using MongoDB for data storage.

---

## 🚀 Tech Stack
- Java 21
- Spring Boot
- MongoDB
- OpenPDF
- Maven

---

## 📂 Features
- Create invoice
- Fetch invoice by ID
- Generate and download invoice PDF
- Automatic tax and total calculation
- MongoDB persistence

---

## ⚙️ Configuration

### application.properties
spring.application.name=invoicegenerator
spring.data.mongodb.uri=mongodb://localhost:27017/invoice_db
server.port=8090

---

## 🔗 API Endpoints

### ➕ Create Invoice
POST /api/invoices

### 📄 Get Invoice by ID
GET /api/invoices/{id}

### 🧾 Download Invoice PDF
GET /api/invoices/{id}/pdf

---

## ▶️ Run Application

### Start MongoDB
mongod --dbpath C:\data\db

### Run Spring Boot
mvn spring-boot:run

---

## 🧪 Testing
- APIs tested using Postman
- PDF preview verified successfully

---

## 👨‍💻 Author
**Rajavel Murugan**







