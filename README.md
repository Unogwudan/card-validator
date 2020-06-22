To run the code normally in an IDE or command line:

1) Create a database with the name card-validator in MSSQL.
2) Set the active profile inside the application.properties file to dev.
3) Run the project

To run the docker container:

Execute these commands: 

1) Change the active profile inside the application.properties file to staging

2) mvn clean package -Dmaven.test.skip=true ====== This builds the jar file

3) docker-compose up ====== This builds the image and sets up the db, and finally spins the container.

Note: You don't have to create a database if you are running the Docker container, it will automatically pull and run an MSSQL Linux container.


//Swagger URL
localhost:8020/swagger-ui.html#!/

//Health Endpoint
localhost:8020/actuator/health
