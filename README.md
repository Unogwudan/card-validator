==== ABOUT CARD VALIDATOR ====

Card validator is a Spring Boot App that allows users retrieve details about their cards. It also stores the statistics of the number of request made per card.
It leverages on an external service called blinklist to retrieve the card details.
A local cache is also implemented to reduce latency.
Finally the retrieved details get sent to a Kaka queue for further processing.


To run the code normally in an IDE or command line:

Ensure you have Kafka running on your machine.

1) Create a database with the name card-validator in MSSQL.
2) Set the active profile inside the application.properties file to dev.
3) Run the project

To run the docker container:

Execute these commands: 

Ensure you have Kafka running on your machine.

1) Change the active profile inside the application.properties file to staging

2) mvn clean package -Dmaven.test.skip=true ====== This builds the jar file

3) docker-compose up ====== This builds the image and sets up the db, and finally spins the container.

Note: You don't have to create a database if you are running the Docker container, it will automatically pull and run an MSSQL Linux container.


======= SWAGGER URL =============
localhost:8020/swagger-ui.html#!/

======  HEALTH ENDPOINT =========
localhost:8020/actuator/health
