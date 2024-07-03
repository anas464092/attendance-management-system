
# Attendance Management System

Welcome to the Attendance Management System! This project is designed to manage student attendance and student data efficiently using Java, Object-Oriented Programming (OOP) principles, and SQL. For UI we use Java swing a buildin library for the java gui which is lighter in size to use. Below you will find an overview of the system's features and how to set it up.


## Features
- Add Student: Easily add new students to the database with their relevant information.
- Delete Student: Remove student records from the database when they are no longer needed.
- Update Student Data: Modify existing student information to keep records current.
- Mark Attendance: Record attendance for individual students or entire classes.
- Search Student Data: Quickly find student information using various search criteria.


## Technologies Used

- VS Code
- IntelliJ
- JDBC
- Java Swing
- Java awt
- My SQL
- OOP  Principles
## Prerequisites

Before running the project, ensure you have the following installed on your machine.

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/#java22) 
- [MySQL](https://dev.mysql.com/downloads/installer/)
- [IDE (e.g., IntelliJ IDEA)](https://www.jetbrains.com/idea/download/?section=windows)

## Installation

#### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/attendance-management-system.git
cd attendance-management-system
```

#### 2. Configure Database Connection

Update the database connection details in the code according to your username and password
```Java
public class DBConfig {
    public static final String URL = "jdbc:mysql://localhost:3306/attendance_management_system";
    public static final String USER = "yourusername";
    public static final String PASSWORD = "yourpassword";
}
```

#### 3. Run the Application
- Open the project in your IDE.
- Build and run the main class (Main.java).

## Contact
If you have any questions or feedback, please feel free to contact me at anasrehman464092@gmail.com