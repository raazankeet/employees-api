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
    [Acess it live](#access-it-live)
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

2. The API will be accessible at `http://localhost:8080/api/employees`.

3. Access the Swagger UI for API documentation at `http://localhost:8080/swagger-ui/index.html`.

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
    docker build -t your-image-name:latest .
    ```

2. Run the Docker container:

    ```sh
    docker run -p 8080:8080 your-image-name:latest
    ```

3. Access the API at `http://localhost:8080/api/employees`.

## Deploying to Azure

1. Build and push the Docker image to your container registry:

    ```sh
    docker build -t your-registry/your-image-name:latest .
    docker push your-registry/your-image-name:latest
    ```

2. Deploy to Azure Container Instances (ACI) or Azure Kubernetes Service (AKS) using the Azure CLI:

    ```sh
    az container create --resource-group your-resource-group --name your-container-name --image your-registry/your-image-name:latest --dns-name-label your-dns-name --ports 80
    ```

3. Access your deployed service at `http://your-dns-name.<region>.azurecontainer.io/api/employees`.

## Access it live
    Acess it live here at `https://myemployeesapi.azurewebsites.net/`

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any enhancements or bug fixes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
