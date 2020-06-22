To run the code normally in an IDE:
1) Set the active profile inside the application.properties file to dev

To run the docker image:

Execute these commands: 

1) Change the active profile inside the application.properties file to staging

2) mvn clean package -Dmaven.test.skip=true ====== This builds the jar file

3) docker-compose up ====== This builds the image and sets up the db, and finally spins the container.