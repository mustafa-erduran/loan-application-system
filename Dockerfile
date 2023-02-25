FROM openjdk:17-alpine3.14
MAINTAINER mustafa-erduran
COPY ./target/loan-application-system-0.0.1-SNAPSHOT.jar loan-application-system-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","loan-application-system-0.0.1-SNAPSHOT.jar"]
