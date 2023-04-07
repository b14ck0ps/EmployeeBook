## Paperless Office Software

This is a software designed to assist a reputable shipping company to become paperless. The software allows the company to store and manage employee data, including their leave information.

### Features
- Includes all newly joined employees and their leave information
- Supports two types of employees: Non-Staff and Staff
- Manages two types of leaves: Annual and Sick
- Calculates leave based on an employee's joining date
- Generates reports on employee leave information
### Technology Stack
- Programming Language: Java 8
- Tomcat 8.5.87
- Framework: Spring Framework
- Database: MySQL

### Database Design
The following is the database schema used in the application.

```
CREATE DATABASEE empbook;

CREATE TABLE employees (
employee_id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
employee_type ENUM('NON_STAFF', 'STAFF') NOT NULL,
joining_date DATE NOT NULL,
PRIMARY KEY (employee_id)
);

CREATE TABLE leave (
leave_id INT NOT NULL AUTO_INCREMENT,
employee_id INT NOT NULL,
leave_type ENUM('ANNUAL', 'SICK') NOT NULL,
number_of_days FLOAT NOT NULL,
PRIMARY KEY (leave_id),
FOREIGN KEY (employee_id) REFERENCES employees(employee_id) ON DELETE CASCADE
);
```

### Install
- Import The pom.xml to the IDE
- Create Database and tables
- Update DB info in AppConfig