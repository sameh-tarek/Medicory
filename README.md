# Requirements
- Java Development Kit (JDK) 17 or above
- MySQL Database (You can either use a local PostgreSQL instance or connect to a remote one)

# How to Run
1- create Database with name "medicory"

2- create user in this database with username "medicory" and password "medicory" 
```code
  CREATE USER 'medicory'@'localhost' IDENTIFIED BY 'medicory';
```

3- create database sql tables  
```code
  -- 1
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    role VARCHAR(255),
    is_enabled BOOLEAN
);

-- 2
CREATE TABLE pharmacies (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    google_maps_link VARCHAR(255),
    address VARCHAR(255) NOT NULL,
    owner_name VARCHAR(255),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 3
CREATE TABLE owners (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255),
    middle_name VARCHAR(255),
    last_name VARCHAR(255),
    gender VARCHAR(255),
    date_of_birth DATE,
    address VARCHAR(255),
    blood_type VARCHAR(255),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 4
CREATE TABLE labs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    google_maps_link VARCHAR(255),
    address VARCHAR(255) NOT NULL,
    owner_name VARCHAR(255),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 5
CREATE TABLE hospitals (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    google_maps_link VARCHAR(255),
    address VARCHAR(255) NOT NULL,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 6
CREATE TABLE clinics (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    google_maps_link VARCHAR(255),
    address VARCHAR(255) NOT NULL,
    owner_name VARCHAR(255),
    specialization VARCHAR(255),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 7
CREATE TABLE owner_phone_numbers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    phone VARCHAR(255),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 8
CREATE TABLE relative_phone_numbers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    phone VARCHAR(255),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 9
CREATE TABLE current_prescriptions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    owner_id BIGINT,
    FOREIGN KEY (owner_id) REFERENCES owners(id)
);

-- 10
CREATE TABLE medicines (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);

-- 11
CREATE TABLE medications (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    current_prescription_id BIGINT,
    dose VARCHAR(255) NOT NULL,
    frequency INT NOT NULL,
    FOREIGN KEY (current_prescription_id) REFERENCES current_prescriptions(id),
    FOREIGN KEY (id) REFERENCES medicines(id)
);

-- 12
CREATE TABLE medications_currently_required (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    owner_id BIGINT,
    FOREIGN KEY (owner_id) REFERENCES owners(id)
);

-- 13
CREATE TABLE lab_tests (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255),
    owner_id BIGINT,
    FOREIGN KEY (owner_id) REFERENCES owners(id)
);

-- 14
CREATE TABLE lab_tests_required (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255)
);

-- 15
CREATE TABLE lab_tests_required_results (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    result VARCHAR(255),
    lab_test_required_id BIGINT,
    FOREIGN KEY (lab_test_required_id) REFERENCES lab_tests_required(id)
);

-- 16
CREATE TABLE lab_tests_results (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    result VARCHAR(255),
    lab_test_id BIGINT,
    FOREIGN KEY (lab_test_id) REFERENCES lab_tests(id)
);

-- 17
CREATE TABLE imaging_tests (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255),
    owner_id BIGINT,
    FOREIGN KEY (owner_id) REFERENCES owners(id)
);

-- 18
CREATE TABLE imaging_tests_required (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255)
);

-- 19
CREATE TABLE imaging_tests_required_results (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    result VARCHAR(255),
    imaging_test_required_id BIGINT,
    FOREIGN KEY (imaging_test_required_id) REFERENCES imaging_tests_required(id)
);

-- 20
CREATE TABLE imaging_tests_results (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    result VARCHAR(255),
    imaging_test_id BIGINT,
    FOREIGN KEY (imaging_test_id) REFERENCES imaging_tests(id)
);

-- 21
CREATE TABLE allergies (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

-- 22
CREATE TABLE chronic_diseases (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

-- 23
CREATE TABLE immunizations (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

-- 24
CREATE TABLE surgeries (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL
);

```

4- Clone the project repository from Git (if it's not already cloned).

5- Import the project into your favorite Java IDE (e.g., IntelliJ, Eclipse, etc.).

6- Build the project to resolve dependencies.

 
