# maven
FROM maven:3.9.2 AS maven

# Copies the required files from the user system to Image. Docker will copy the src folder and pom.xml file into the image.
COPY ./pom.xml ./pom.xml
COPY ./src ./src

# Maven command to resolve application dependencies present in pom.xml.
RUN mvn dependency:go-offline -B

RUN mvn package

# java
FROM openjdk:20

WORKDIR /ooml_codegen

COPY ./target/OOMLCodeGen-1.0-SNAPSHOT.jar /ooml_codegen

ENTRYPOINT ["java","-jar","OOMLCodeGen-1.0-SNAPSHOT.jar"]