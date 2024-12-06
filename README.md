How to Run the Backend Application

1) Clone the repository to your local machine:

### `git clone <repository-url>`

2) Navigate to the backend project directory:

### `cd <backend-project-folder>`

3) Ensure you have a database (e.g., MySQL, PostgreSQL) set up. Update the database connection details in the application.properties or application.yml file:

### `This project uses PostgreSQL as the database.`

spring.datasource.url=jdbc:mysql://localhost:3306/<database-name>
spring.datasource.username=<username>
spring.datasource.password=<password>

4) Build the project to download dependencies:

### `./mvnw clean install`

5) Run the application


Blog Application Java Spring Boot Documentation


1. User

Manages user-related operations.

    Base URL: /user

POST	/save	Creates a new user.

GET	/getAllUsers	Retrieves all users.

2. Post

Handles blog post management.

    Base URL: /post


POST	/save	Creates a new post.

GET	/listOfPosts	Lists all posts.

GET	/getPostById	Retrieves a post by its ID.

GET	/listOfPostsByUserId	Retrieves posts by a specific user ID.

POST	/by-categories	Retrieves posts by category IDs.

DELETE	/delete	Deletes a specific post.

PUT	/update	Updates an existing post.

3. Comment

Manages comments on posts.

    Base URL: /comment

HTTP Method	Endpoint	Description

POST	/save	Adds a new comment.

DELETE	/delete	Deletes a specific comment.

PUT	/update	Updates an existing comment.

GET	/getAllCommentsByPostId	Retrieves comments for a specific post ID.

4. Category

Handles category-related operations.

    Base URL: /category

HTTP Method	Endpoint	Description

POST	/save	Creates a new category.

GET		Lists all categories.

DELETE	/delete	Deletes a specific category.

PUT	/update	Updates an existing category.



Service Implementations

UserService

Manages user-related operations:

    Hashes passwords using BCryptPasswordEncoder.
    Saves and retrieves users.

PostService

Provides operations for post management:

    Create, list, update, and delete posts.
    Filters posts by categories or user.

CommentService

Handles CRUD operations for comments:

    Filters comments by post ID.

CategoryService

Offers CRUD operations for categories:

    Add, list, update, and delete categories.
