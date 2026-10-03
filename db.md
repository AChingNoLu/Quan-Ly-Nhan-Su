CREATE DATABASE IF NOT EXISTS employeemanagementsystem;
USE employeemanagementsystem;

CREATE TABLE login (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL
);

INSERT INTO login (username, password)
VALUES ('admin', '123456');

CREATE TABLE employee (
    emID VARCHAR(30) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    fname VARCHAR(100) NOT NULL,
    dob DATE NOT NULL,
    salary DECIMAL(12,2) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(30) NOT NULL,
    email VARCHAR(100) NOT NULL,
    education VARCHAR(100),
    designation VARCHAR(100) NOT NULL,
    cccd VARCHAR(30) NOT NULL
);

CREATE TABLE attendance (
    emID VARCHAR(30) NOT NULL,
    date DATE NOT NULL,
    status ENUM('Present', 'Absent', 'Late') NOT NULL,
    PRIMARY KEY (emID, date),
    CONSTRAINT fk_attendance_employee
        FOREIGN KEY (emID) REFERENCES employee(emID)
        ON DELETE CASCADE
);

CREATE TABLE payroll (
    id INT AUTO_INCREMENT PRIMARY KEY,
    emID VARCHAR(30),
    month INT NOT NULL,
    year INT NOT NULL,
    final_salary DECIMAL(12,2) NOT NULL,
    CONSTRAINT fk_payroll_employee
        FOREIGN KEY (emID) REFERENCES employee(emID)
        ON DELETE CASCADE
);
