# 🎓 Student Management App

A simple **Student Management System** built using **Java Swing, JDBC, and MySQL**.
This project allows users to perform basic CRUD operations (Create, Read, Update, Delete) on student records through a graphical interface.

---

## 🚀 Features

* ➕ Add Student
* 📋 View Students
* ✏️ Update Student
* ❌ Delete Student
* 💻 Simple GUI using Java Swing

---

## 🛠️ Technologies Used

* Java (Swing for GUI)
* JDBC (Database Connectivity)
* MySQL (Database)

---

## 🗄️ Database Setup

Run the following SQL commands:

```sql
CREATE DATABASE student_db;

USE student_db;

CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    course VARCHAR(50),
    marks INT
);
```

---

## ⚙️ Setup Instructions

### 1. Clone Repository

```bash
git clone https://github.com/your-username/student-management-app.git
cd student-management-app
```

---

### 2. Add MySQL Connector

Download MySQL Connector JAR and place it in the project folder.

---

### 3. Update Database Credentials

Open `StudentManagementApp.java` and change:

```java
"root",
"your_password"
```

---

### 4. Compile and Run

#### 👉 Windows (PowerShell)

```bash
javac -cp ".;mysql-connector-j-9.6.0.jar" StudentManagementApp.java
java -cp ".;mysql-connector-j-9.6.0.jar" StudentManagementApp
```

#### 👉 Linux/Mac

```bash
javac -cp ".:mysql-connector-j-9.6.0.jar" StudentManagementApp.java
java -cp ".:mysql-connector-j-9.6.0.jar" StudentManagementApp
```

---

## 📂 Project Structure

```
student-management-app/
│── StudentManagementApp.java
│── mysql-connector-j-9.6.0.jar
```

---

## 📸 Output

A simple window with buttons:

* Add Student
* View Students
* Update Student
* Delete Student

---

## 📌 Notes

* Ensure MySQL server is running
* Database name: `student_db`
* Table name: `students`

---

## 👩‍💻 Author
Priya S Patil

Priya S Patil
