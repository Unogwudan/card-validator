FROM openjdk:latest

ADD target/card-validator-1.0.0.jar card-validator-1.0.0.jar

EXPOSE 8020

ENTRYPOINT ["java","-jar","card-validator-1.0.0.jar"]