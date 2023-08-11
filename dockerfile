# -----------------------  Stage 1 (Install Maven and Build source code) -----------------------

# initialize build and set base image for first stage
FROM maven:3.9.3-eclipse-temurin-17-alpine as stage1

# speed up Maven JVM a bit
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"

# set working directory
WORKDIR /build

# copy just pom.xml
COPY pom.xml .

# go-offline using the pom.xml
RUN mvn dependency:go-offline

# copy your other files
COPY ./src ./src

# compile the source code and package it in a jar file
# RUN mvn clean install -Dmaven.test.skip=true
RUN mvn clean verify -PintegrationTest

# -----  Stage 2 (Install JDK and create final image for Springboot app) -------------
# set base image for second stage
FROM openjdk:17-oraclelinux8

# Basic Props
VOLUME /tmp
EXPOSE 8080

# set deployment directory
WORKDIR /springboot-app

# copy over the built artifact from the maven image
COPY --from=stage1 /build/target/sb-docker-aca-*.jar /springboot-app/app.jar
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar /springboot-app/app.jar