# Employee API

This project is a Spring Boot application providing a RESTful API for managing employee data. The application supports operations such as creating, updating, deleting, and fetching employee details.

## Table of Contents

- [Employee API](#employee-api)
  - [Table of Contents](#table-of-contents)
  - [Features](#features)
  - [Technologies Used](#technologies-used)
  - [Prerequisites](#prerequisites)
  - [Setup](#setup)
  - [Running the Application](#running-the-application)
  - [API Endpoints](#api-endpoints)
  - [Running in Docker](#running-in-docker)
  - [Deploying to Azure](#deploying-to-azure)
  - [Access Live API](#access-live-api)
  - [Contributing](#contributing)
  - [License](#license)

## Features

- Create, update, delete, and retrieve employees.
- Delete all employees.
- Reload employee data from a predefined CSV dataset.
- API documentation with Swagger.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- H2 Database
- Swagger/OpenAPI
- Docker

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Docker (optional, for containerization)
- An Azure account (optional, for deployment)

## Setup

1. Clone the repository:

    ```sh
    git clone https://github.com/raazankeet/employee-api.git
    cd employee-api
    ```

2. Build the application:

    ```sh
    mvn clean package -DskipTests
    ```

## Running the Application

1. Start the application:

    ```sh
    mvn spring-boot:run
    ```

2. The API will be accessible at `http://localhost:8080`.

## API Endpoints

### Get All Employees

- **URL:** `/api/employees`
- **Method:** `GET`
- **Description:** Retrieve a list of all employees.

### Get Employee by ID

- **URL:** `/api/employees/{id}`
- **Method:** `GET`
- **Description:** Retrieve an employee by their unique ID.

### Create Employee

- **URL:** `/api/employees`
- **Method:** `POST`
- **Description:** Add a new employee to the system.
- **Body:** JSON representation of the employee.

### Update Employee

- **URL:** `/api/employees/{id}`
- **Method:** `PUT`
- **Description:** Update the details of an existing employee.
- **Body:** JSON representation of the updated employee.

### Delete Employee

- **URL:** `/api/employees/{id}`
- **Method:** `DELETE`
- **Description:** Remove an employee from the system.

### Delete All Employees

- **URL:** `/api/employees`
- **Method:** `DELETE`
- **Description:** Remove all employees from the system.

### Reload Employee Data

- **URL:** `/api/employees/reload`
- **Method:** `POST`
- **Description:** Reload the employee data from a predefined CSV dataset.

## Running in Docker

1. Build the Docker image:

    ```sh
    docker build -t employee-api .
    ```

2. Run the Docker container:

    ```sh
    docker run -p 8080:8080 employee-api
    ```

## Deploying to Azure

1. Ensure you have the [Azure CLI](https://docs.microsoft.com/en-us/cli/azure/install-azure-cli) installed and are logged in.

2. Create a resource group:

    ```sh
    az group create --name employee-api-rg --location eastus
    ```

3. Create an App Service plan:

    ```sh
    az appservice plan create --name employee-api-plan --resource-group employee-api-rg --sku B1 --is-linux
    ```

4. Create a web app:

    ```sh
    az webapp create --resource-group employee-api-rg --plan employee-api-plan --name myemployeesapi --deployment-container-image-name raazankeet/employee-api:latest
    ```

5. Configure continuous deployment from your Docker repository:

    ```sh
    az webapp config container set --name myemployeesapi --resource-group employee-api-rg --docker-custom-image-name raazankeet/employee-api:latest --docker-registry-server-url https://index.docker.io
    ```

## Access Live API

You can access the live API at: [https://myemployeesapi.azurewebsites.net/](https://myemployeesapi.azurewebsites.net/)

## Contributing

Contributions are welcome! Please submit a pull request or open an issue to discuss any changes.

## License

This project is licensed under the custom License. See the [LICENSE](LICENSE) file for details.
