# Features

**Host ID**: http://localhost:8081

### 1. Auth

| No. | Feature                      | Description                                                     | Endpoint                                           |
|----:|------------------------------|-----------------------------------------------------------------|----------------------------------------------------|
|  1. | Authenticate                | Authenticate a user and generate an access token                | `POST /auth/authenticate`                          |
|  2. | Forgot Password             | Send a password reset code to the user's email                  | `POST /auth/forgot-password`                       |
|  3. | Verify Password Reset Code  | Verify the password reset code sent to the user's email         | `POST /auth/verify-password-reset-code`            |
|  4. | Reset Password              | Reset the user's password using the reset code                  | `POST /auth/reset-password`                        |

### 2. Doctor

| No. | Feature                      | Description                                                     | Endpoint                                           |
|----:|------------------------------|-----------------------------------------------------------------|----------------------------------------------------|
|  1. | Patient Personal Information | Retrieve personal information of a patient                      | `GET /doctors/patients/{userCode}/personal-information` |
|  2. | Patient Chronic Diseases     | Retrieve chronic diseases of a patient                          | `GET /doctors/patients/{userCode}/chronic-diseases`      |
|  3. | Add Chronic Disease          | Add a new chronic disease for a patient                        | `POST /doctors/patients/{userCode}/chronic-diseases`     |
|  4. | Find Chronic Disease         | Find chronic disease by ID                                      | `GET /doctors/patients/chronic-diseases/{diseasesId}`     |
|  5. | Update Chronic Disease       | Update chronic disease                                          | `PUT /doctors/patients/{userCode}/chronic-diseases/{diseasesId}` |
|  6. | Delete Chronic Disease       | Delete chronic disease                                          | `DELETE /doctors/patients/chronic-diseases/{diseasesId}` |
|  7. | Patient Allergies            | Retrieve allergies of a patient                                | `GET /doctors/patients/{userCode}/allergies`             |
|  8. | Add Allergies                | Add a new allergy for a patient                                | `POST /doctors/patients/{userCode}/allergies`            |
|  9. | Find Allergies               | Find allergy by ID                                              | `GET /doctors/patients/allergies/{allergiesId}`            |
| 10. | Update Allergies             | Update allergy                                                  | `PUT /doctors/patients/{userCode}/allergies/{allergiesId}` |
| 11. | Delete Allergies             | Delete allergy                                                  | `DELETE /doctors/patients/allergies/{allergiesId}` |
| 12. | Patient Immunizations        | Retrieve immunizations of a patient                            | `GET /doctors/patients/{userCode}/immunizations`         |
| 13. | Add Immunization             | Add a new immunization for a patient                           | `POST /doctors/patients/{userCode}/immunizations`        |
| 14. | Find Immunization            | Find immunization by ID                                         | `GET /doctors/patients/immunizations/{immunizationId}`        |
| 15. | Update Immunization          | Update immunization                                             | `PUT /doctors/patients/{userCode}/immunizations/{immunizationId}` |
| 16. | Delete Immunization          | Delete immunization                                             | `DELETE /doctors/patients/immunizations/{immunizationId}` |
| 17. | Patient Surgeries            | Retrieve surgical history of a patient                         | `GET /doctors/patients/{userCode}/surgeries`              |
| 18. | Add Surgery                  | Add a new surgery for a patient                                | `POST /doctors/patients/{userCode}/surgeries`             |
| 19. | Find Surgery                 | Find surgery by ID                                              | `GET /doctors/patients/surgeries/{surgeryId}`             |
| 20. | Update Surgery               | Update surgery                                                  | `PUT /doctors/patients/{userCode}/surgeries/{surgeryId}`  |
| 21. | Delete Surgery               | Delete surgery                                                  | `DELETE /doctors/patients/surgeries/{surgeryId}`         |
| 22. | Patient Prescriptions        | Retrieve all patient prescriptions                             | `GET /doctors/patients/{userCode}/prescriptions`         |
| 23. | Add Prescription             | Add a new prescription for a patient                           | `POST /doctors/patients/{userCode}/prescriptions`        |
| 24. | Find Prescription            | Find prescription by ID                                         | `GET /doctors/patients/prescriptions?prescriptionId={prescriptionId}` |
| 25. | Patient Lab Tests            | Retrieve all lab tests for a patient                           | `GET /doctors/patients/{userCode}/tests`                 |
| 26. | Delete Lab Test              | Delete lab test from history                                    | `DELETE /doctors/patients/tests/{testId}`                |
| 27. | Update Lab Test              | Update lab test                                                 | `PUT /doctors/patients/tests/{testId}`                   |
| 28. | Add Lab Tests                | Add lab tests for patient that required now                     | `POST /doctors/patients/{userCode}/tests`                |
| 29. | Get Active Lab Tests         | Get active lab tests                                            | `GET /doctors/patients/{userCode}/active-tests`          |
| 30. | Find Lab Test                | Find lab test by ID                                             | `GET /doctors/patients/tests?testId={testId}`            |
| 31. | Patient Imaging Tests        | Retrieve all imaging tests for a patient                       | `GET /doctors/patients/{userCode}/imaging-tests`         |
| 32. | Delete Imaging Test          | Delete imaging test from history                                | `DELETE /doctors/patients/imaging-tests/{testId}`        |
| 33. | Update Imaging Test          | Update imaging test                                             | `PUT /doctors/patients/imaging-tests/{testId}`           |
| 34. | Add Imaging Tests            | Add imaging tests for patient that required now                 | `POST /doctors/patients/{userCode}/imaging-tests`        |
| 35. | Get Active Imaging Tests     | Get active imaging tests                                        | `GET /doctors/patients/{userCode}/active-imaging-tests`  |
| 36. | Find Imaging Test            | Find imaging test by ID                                         | `GET /doctors/patients/imaging-tests?testId={testId}`    |


--------
## 3. Admin-Users

### Doctor
| No. | Feature       | Description                                            | Endpoint                                   |
|----:|---------------|--------------------------------------------------------|--------------------------------------------|
|  1. | Find Doctor   | Retrieve doctor ( id, name,status ) using code         | `GET /admin/doctors/code/{code}`           |
|  2. | Find Doctor   | Retrieve doctor ( id, name,status ) using email        | `GET /admin/doctors/email/{email}`         |
|  3. | Find Doctors  | Retrieve doctors ( id, name,status ) using doctor name | `GET/admin/doctors/name/{name}`            |
|  4. | Show Doctor   | Show all data of the doctor using ID                   | `GET /admin/doctors/id/{doctorId}/doctor`  |
|  5. | Add Doctor    | add doctor as new user                                 | `POST /admin/doctors/doctor `              |
|  6. | Update Doctor | update doctor's data using ID                          | `PUT /admin/doctors/id/{doctorId}/doctor ` |
|  7. | Delete Doctor | Disable (delete) doctor using id                       | `PUT /admin/doctors/id/{doctorId}  `       |

### Lab
| No. | Feature    | Description                                      | Endpoint                          |
|----:|------------|--------------------------------------------------|-----------------------------------|
|  1. | Find Lab   | Retrieve lab ( id, name,status ) using code      | `GET /admin/labs/code/{code}`     |
|  2. | Find lab   | Retrieve lab ( id, name,status ) using email     | `GET /admin/labs/email/{email}`   |
|  3. | Find labs  | Retrieve labs ( id, name,status ) using lab name | `GET/admin/labs/name/{name}`      |
|  4. | Show lab   | Show all data of the lab using ID                | `GET /admin/labs/id/{labId}/lab`  |
|  5. | Add lab    | add lab as new user                              | `POST /admin/labs/doctor `        |
|  6. | Update lab | update lab's data using ID                       | `PUT /admin/labs/id/{labId}/lab ` |
|  7. | Delete lab | Disable (delete) lab using id                    | `DEL /admin/labs/id/{labId}  `    |

### Pharmacy
| No. | Feature          | Description                                                 | Endpoint                                          |
|----:|------------------|-------------------------------------------------------------|---------------------------------------------------|
|  1. | Find Pharmacy    | Retrieve pharmacy ( id, name,status ) using code            | `GET /admin/pharmacies/code/{code}`               |
|  2. | Find Pharmacy    | Retrieve pharmacy ( id, name,status ) using email           | `GET /admin/pharmacies/email/{email}`             |
|  3. | Find Pharmacies  | Retrieve pharmacies ( id, name,status ) using pharmacy name | `GET /admin/pharmacies/name/{name}`               |
|  4. | Show Pharmacy    | Show all data of the pharmacy using ID                      | `GET /admin/pharmacies/id/{pharmacyId}/pharmacy`  |
|  5. | Add Pharmacy     | add pharmacy as new user                                    | `POST /admin/pharmacies/pharmacy `                |
|  6. | Update Pharmacy  | update pharmacy's data using ID                             | `PUT /admin/pharmacies/id/{pharmacyId}/pharmacy ` |
|  7. | Delete Pharmacy  | Disable (delete) pharmacy using ID                          | `DEL /admin/pharmacies/id/{pharmacyId} `          |

### Owner
| No. | Feature      | Description                                         | Endpoint                                |
|----:|--------------|-----------------------------------------------------|-----------------------------------------|
|  1. | Find Owner   | Retrieve owner ( id, name,status ) using code       | `GET /admin/owners/code/{code}`         |
|  2. | Find Owner   | Retrieve owner ( id, name,status ) using email      | `GET /admin/owners/email/{email}`       |
|  3. | Find Owners  | Retrieve owner ( id, name,status ) using owner name | `GET /admin/owners/name/{name}`         |
|  4. | Show Owner   | Show all data of the owner using ID                 | `GET /admin/owners/id/{ownerId}/owner`  |
|  5. | Add Owner    | add owner as new user                               | `POST /admin/owners/owner `             |
|  6. | Update Owner | update owner's data using ID                        | `PUT /admin/owners/id/{ownerId}/owner ` |
|  7. | Delete Owner | Disable (delete) owner using ID                     | `DEL /admin/owners/id/{ownerId} `       |

### Admin
| No. | Feature      | Description                                         | Endpoint                                 |
|----:|--------------|-----------------------------------------------------|------------------------------------------|
|  1. | Find Admin   | Retrieve admin ( id, name,status ) using code       | `GET /admin/admins/code/{code}`          |
|  2. | Find Admin   | Retrieve admin ( id, name,status ) using email      | `GET /admin/admins/email/{email}`        |
|  3. | Find Admins  | Retrieve admin ( id, name,status ) using admin name | `GET /admin/admins/name/{name}`          |
|  4. | Show Admin   | Show all data of the admin using ID                 | `GET /admin/admins/id/{adminId}/admin`   |
|  5. | Add Admin    | add admin as new user                               | `POST /admin/admins  `                   |
|  6. | Update Admin | update admin's data using ID                        | `PUT /admin/admins/id/{adminId}/admin `  |
|  7. | Delete Admin | Disable (delete) admin using ID                     | `DEL /admin/admins/id/{adminId} `        |

### Clinic
| No. | Feature       | Description                                           | Endpoint                                   |
|----:|---------------|-------------------------------------------------------|--------------------------------------------|
|  1. | Find Clinic   | Retrieve clinic ( id, name,status ) using code        | `GET /admin/clinics/code/{code}`           |
|  2. | Find Clinic   | Retrieve clinic ( id, name,status ) using email       | `GET /admin/clinics/email/{email}`         |
|  3. | Find Clinics  | Retrieve clinic ( id, name,status ) using clinic name | `GET /admin/clinics/name/{name}`           |
|  4. | Show Clinic   | Show all data of the clinic using ID                  | `GET /admin/clinics/id/{clinicId}/clinic`  |
|  5. | Add Clinic    | add clinic as new user                                | `POST /admin/clinics/clinic  `             |
|  6. | Update Clinic | update clinic's data using ID                         | `PUT /admin/clinics/id/{clinicId}/clinic ` |
|  7. | Delete Clinic | Disable (delete) clinic using ID                      | `DEL /admin/clinics/id/{aclinicId} `       |


### Hospital
| No. | Feature          | Description                                               | Endpoint                                       |
|----:|------------------|-----------------------------------------------------------|------------------------------------------------|
|  1. | Find Hospital    | Retrieve hospital ( id, name,status ) using code          | `GET /admin/hospitals/code/{code}`             |
|  2. | Find Hospital    | Retrieve hospital ( id, name,status ) using email         | `GET /admin/hospitals/email/{email}`           |
|  3. | Find Hospitals   | Retrieve hospital ( id, name,status ) using hospital name | `GET /admin/hospitals/name/{name}`             |
|  4. | Show Hospital    | Show all data of the hospital using ID                    | `GET /admin/hospital/id/{hospitalId}/hospital` |
|  5. | Add Hospital     | add hospital as new user                                  | `POST /admin/hospitals/hospital `              |
|  6. | Update Hospital  | update hospital's data using ID                           | `PUT /admin/clinics/id/{hospitalId}/hospital ` |
|  7. | Delete Hospital  | Disable (delete) hospital using ID                        | `DEL /admin/hospitals/id/{hospitalId} `        |


