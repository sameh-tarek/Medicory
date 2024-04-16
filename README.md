# Test the API here:
- [API DOC](http://localhost:7777/v3/api-docs)
- [Swagger UI](http://localhost:7777/swagger-ui//index.html)

<a href="https://interstellar-capsule-619026.postman.co/collection/28660393-3250146c-3baa-4035-9ebe-837a4e7a0ce0?source=rip_html">
	<img alt="Run in Postman" src="https://run.pstmn.io/button.svg">
</a>

# Requirements
- Java Development Kit (JDK) 17 or above
- MySQL Database (You can either use a local MySQL instance or connect to a remote one)

# How to Run
1. [Database Configuration](DB#readme)
2. Clone the project repository from Git (if it's not already cloned).
3. Import the project into your favorite Java IDE (e.g., IntelliJ, Eclipse, etc.).
4. Build the project to resolve dependencies.

## Features

### Doctor
### Doctor

| No. | Feature                      | Description                                                     | Endpoint                                     |
|----:|------------------------------|-----------------------------------------------------------------|----------------------------------------------|
|  1. | Patient Personal Information | Retrieve personal information of a patient                      | `GET /doctor/patient/personal-information`  |
|  2. | Patient Chronic Diseases     | Retrieve chronic diseases of a patient                          | `GET /doctor/patient/chronic-diseases`      |
|  3. | Add Chronic Disease          | Add a new chronic disease for a patient                        | `POST /doctor/patient/chronic-diseases`     |
|  4. | Patient Allergies            | Retrieve allergies of a patient                                | `GET /doctor/patient/allergies`             |
|  5. | Add Allergies                | Add a new allergy for a patient                                | `POST /doctor/patient/allergies`            |
|  6. | Patient Immunizations        | Retrieve immunizations of a patient                            | `GET /doctor/patient/immunizations`         |
|  7. | Add Immunization             | Add a new immunization for a patient                           | `POST /doctor/patient/immunizations`        |
|  8. | Patient Surgeries            | Retrieve surgical history of a patient                         | `GET /doctor/patient/surgeries`             |
|  9. | Add Surgery                  | Add a new surgery for a patient                                | `POST /doctor/patient/surgeries`            |
|  .. |

--------
### Admin

#### Admin-Clinic
| No. | Feature               | Description                              | Endpoint                                     |
|----:|-----------------------|------------------------------------------|----------------------------------------------|
|  1. | Clinic's data (id)    | Retrieve clinic's data using clinic id   | `GET /admin/clinics/clinicId/{clinicId}`     |
|  2. | Clinic's data (name)  | Retrieve clinic's data using clinic name | `GET /admin/clinics/clinicName/{clinicName}` |
|  3. | Clinic's data (email) | Retrieve clinic's data using user email  | `GET /admin/clinics/userEmail/{userEmail}`   |
|  4. | Clinics' data         | Retrieve data of all clinics             | `GET /admin/clinics `                        |
|  5. | Add clinic            | Add new user -> CLINIC                   | `POST /admin/clinics/clinic `                |
|  6. | Update Clinic         | Update an existing user ->CLINIC         | `PUT /admin/clinics/clinic/{clinicId} `      |
|  7. | Delete Clinic         | Delete Clinic using id                   | `DEL /admin/clinics/clinic/{clinicId}`       |
|  .. |


#### Admin-Lab
| No. | Feature            | Description                          | Endpoint                                 |
|----:|--------------------|--------------------------------------|------------------------------------------|
|  1. | Lab's data (id)    | Retrieve lab's data using lab id     | `GET  /admin/labs/labId/{labId} `        |
|  2. | Lab's data (name)  | Retrieve lab's data using lab name   | `GET  /admin/labs/labName/{labName} `    |
|  3. | Lab's data (email) | Retrieve lab's data using user email | `GET  /admin/labs/userEmail/{userEmail}` |
|  4. | Labs' data         | Retrieve data of all labs            | `GET  /admin/labs`                       |
|  5. | Add new lab        | Add new user -> LAB                  | `POST /admin/labs/lab `                  |
|  6. | Update lab (id)    | Update an existing user ->LAB        | `PUT  /admin/labs/lab/{labId} `          |
|  7. | Delete lab (id)    | Delete Lab using id                  | `DEL  /admin/labs/lab/{labId}`           |
|  .. |


#### Admin-Pharmacy
| No. | Feature                 | Description                                  | Endpoint                                           |
|----:|-------------------------|----------------------------------------------|----------------------------------------------------|
|  1. | Pharmacy's data (id)    | Retrieve pharmacy's data using pharmacy id   | `GET  /admin/pharmacies/pharmacyId/{pharmaId} `    |
|  2. | Pharmacy's data (name)  | Retrieve pharmacy's data using pharmacy name | `GET  /admin/pharmacies/pharmacyName/{pharmaName}` |
|  3. | Pharmacy's data (email) | Retrieve pharmacy's data using user email    | `GET  /admin/pharmacies/userEmail/{userEmail}`     |
|  4. | Pharmcies' data         | Retrieve data of all pharmacies              | `GET  /admin/pharmacies`                           |
|  5. | Add Pharmacy            | Add new user -> PHARMACY                     | `POST /admin/pharmacies/pharmacy `                 |
|  6. | Update Pharmacy         | Update an existing user ->PHARMACY           | `PUT  /admin/pharmacies/pharmacy/{pharmacyId}  `   |
|  7. | Delete Pharmacy         | Delete pharmacy using id                     | `DEL  /admin/pharmacies/pharmacy/{pharmacyId}    ` |
|  .. |




# ERD

![medicoryERD2](https://github.com/said-ahmd/health_card/assets/108232157/6a505ea2-e375-43d8-b5a5-611f9f1c8301)
