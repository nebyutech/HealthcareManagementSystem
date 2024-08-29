# Healthcare Management System

This project is a simple Healthcare Management System built using Java, JDBC, and the DAO pattern. It allows for managing patients, doctors, and appointments with basic CRUD (Create, Read, Update, Delete) functionality.

## Features

- **Manage Patients**: Add, view, update, and delete patient records.
- **Manage Doctors**: Add, view, update, and delete doctor records.
- **Manage Appointments**: Schedule, view, update, and cancel appointments.

## Project Structure

- `src/`
    - `main/java/healthcare/`
        - `model/`: Contains the model classes (Patient, Doctor, Appointment).
        - `dao/`: Contains the DAO classes responsible for database operations.
        - `jdbc/`: Contains the DatabaseConnection class for handling database connectivity.
        - `HealthRunner.java`: The main class with a simple console menu for managing the system.

## Getting Started

### Prerequisites

- Java JDK 8 or above
- MySQL or any other relational database
- JDBC driver for your database

### Setting Up the Database

1. Create a database named `healthcare`.
2. Create the necessary tables:

   ```sql
   CREATE TABLE Patients (
       patientId INT PRIMARY KEY AUTO_INCREMENT,
       firstName VARCHAR(50),
       lastName VARCHAR(50),
       dateOfBirth DATE,
       email VARCHAR(100),
       phoneNumber VARCHAR(15)
   );

   CREATE TABLE Doctors (
       doctorId INT PRIMARY KEY AUTO_INCREMENT,
       firstName VARCHAR(50),
       lastName VARCHAR(50),
       specialty VARCHAR(50),
       email VARCHAR(100)
   );

   CREATE TABLE Appointments (
       appointmentId INT PRIMARY KEY AUTO_INCREMENT,
       patientId INT,
       doctorId INT,
       appointmentDate DATE,
       notes TEXT,
       FOREIGN KEY (patientId) REFERENCES Patients(patientId),
       FOREIGN KEY (doctorId) REFERENCES Doctors(doctorId)
   );
