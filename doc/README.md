# Features

**Host ID**: http://localhost:8081

## 1. Doctor

| No. | Feature                      | Description                                                     | Endpoint                                           |
|----:|------------------------------|-----------------------------------------------------------------|----------------------------------------------------|
|  1. | Patient Personal Information | Retrieve personal information of a patient                      | `GET /doctors/patients/{userCode}/personal-information` |
|  2. | Patient Chronic Diseases     | Retrieve chronic diseases of a patient                          | `GET /doctors/patients/{userCode}/chronic-diseases`      |
|  3. | Add Chronic Disease          | Add a new chronic disease for a patient                        | `POST /doctors/patients/{userCode}/chronic-diseases`     |
|  4. | Find Chronic Disease         | Find chronic disease by ID                                      | `GET /doctors/patients/chronic-diseases /{diseasesId}`     |
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
## 2. Admin

### Admin-Clinic


### Admin-Lab


### Admin-Pharmacy

