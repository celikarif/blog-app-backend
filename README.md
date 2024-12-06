How to Run the Backend Application

1) Clone the repository to your local machine:

### `git clone <repository-url>`

2) Navigate to the backend project directory:

### `cd <backend-project-folder>`

3) Ensure you have a database (e.g., MySQL, PostgreSQL) set up. Update the database connection details in the application.properties or application.yml file:

### `spring.datasource.url=jdbc:mysql://localhost:3306/<database-name>
spring.datasource.username=<username>
spring.datasource.password=<password>`

4) Build the project to download dependencies:
### `./mvnw clean install`

5) Run the application
