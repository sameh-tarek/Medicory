# Test the API here :
<a href="https://interstellar-capsule-619026.postman.co/collection/28660393-3250146c-3baa-4035-9ebe-837a4e7a0ce0?source=rip_html">
	<img alt="Run in Postman" src="https://run.pstmn.io/button.svg">
</a>

# Requirements
- Java Development Kit (JDK) 17 or above
- MySQL Database (You can either use a local MySQL instance or connect to a remote one)
# How to Run
1 - [Database Configuration](DB#readme)

2- Clone the project repository from Git (if it's not already cloned).

3- Import the project into your favorite Java IDE (e.g., IntelliJ, Eclipse, etc.).

4- Build the project to resolve dependencies.

## Features

### Doctor

| No. | Feature                      | Description                                                                | Endpoint                        |
|----:|------------------------------|----------------------------------------------------------------------------|---------------------------------|
|  1. | Patient Personal Information | Doctor get Patient Personal Information with current Authenticated user id | `GET /doctor/patient/personal-information` |
|  2. | Patient Chronic Diseases     | Doctor get Patient Chronic Diseases with current Authenticated user id     | `GET /doctor/patient/chronic-diseases`                |
|  ..                                                                                                                |       


# ERD

![medicoryERD2](https://github.com/said-ahmd/health_card/assets/108232157/6a505ea2-e375-43d8-b5a5-611f9f1c8301)


