
# Keycloak Integration with Spring Boot(https://github.com/savindu29/keycloak-user-management.git)

This project demonstrates how to integrate Keycloak with a Spring Boot application for user management.


Full complete guide is available as a PDF file in the project.

## Prerequisites

- Docker
- Java 8 or higher
- Maven

## Getting Started

### Install Keycloak Using Docker

To install Keycloak using Docker, run the following command:

```bash
docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:24.0.4 start-dev
```

This command will run Keycloak on the default port 8080.

To run Keycloak on a different port, use:

```bash
docker run -p 8095:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:24.0.4 start-dev
```

### Access Keycloak

Open your browser and navigate to `http://localhost:8095`. Use the following credentials to log in:

- **Username:** admin
- **Password:** admin

### Setup Keycloak

1. **Create Realm:**
   - Add realm name and create.

2. **Create Client:**
   - Add any client ID and name, then click next.

3. **Configure `admin-cli`:**
   - Go to the clients and select `admin-cli`.
   - Enable client-authentication and service account roles.
   - Add the logout URL (optional), then click Save.

4. **Assign Realm Management Role:**
   - Go to the class containing the main method and edit the port and realm name.

5. **Setup Email Verification:**
   - Go to the realm settings and email tab.
   - Add your default email and save.
   - Fill in the details for the SMTP server (e.g., smtp.gmail.com) and click on test connection.

### Run Spring Boot Application

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/savindu29/keycloak-user-management.git
   ```

2. **Configure Application Properties:**

   Edit the `application.properties` file to match your Keycloak details (realm, domain, client).

3. **Run the Application:**

   ```bash
   mvn spring-boot:run
   ```

4. **Access Swagger UI:**

   Open the following URL in your browser: `http://localhost:8070/swagger-ui/index.html`

### API Usage

- **Create User:** Fill in the details and execute. You will receive a verification email.
- **Login:**
  - Use Postman to send a POST request to `http://localhost:8095/realms/dev-realm/protocol/openid-connect/token` with `client_secret`, `client_id`, `grant_type`, `username`, and `password`.
  - Get the access token and use it to access other user-related APIs like get, update, and send verification.
- **Forget Password:** Use your username to reset your password. You will receive a reset password email.

### Complete Guide

For a complete step-by-step guide, refer to the `Keycloak Integration with Spring Boot.pdf` included in this repository.

### Resources

- [Keycloak Documentation](https://www.keycloak.org/documentation)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Video Tutorial](https://youtu.be/kTcmbZqNiGw?si=7eMBSvVf-StJ-3MS)

