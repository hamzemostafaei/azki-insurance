# Use the official OpenJDK 21 image as the base image
FROM openjdk:21-jdk-slim

RUN apt-get update && apt-get install -y telnet net-tools iputils-ping curl vim-tiny && rm -rf /var/lib/apt/lists/*

# Set the working directory inside the container
WORKDIR /workspace

# Copy the JAR file into the container
COPY target/reservation-service-1.0-SNAPSHOT.jar app.jar

# Run the application
CMD ["sh", "-c", "exec java $JAVA_OPTS -jar app.jar"]