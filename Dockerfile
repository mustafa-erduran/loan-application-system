FROM openjdk:17-alpine3.14
MAINTAINER mustafa-erduran
WORKDIR /app
COPY ./target/loan-application-system-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
ENTRYPOINT ["java","-jar","loan-application-system-0.0.1-SNAPSHOT.jar"]
