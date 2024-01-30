FROM openjdk:17-jdk-alpine

# Create a user
RUN addgroup -S app && adduser -S app -G app

# Use the user
USER app

# Copy the jar file into the image
COPY target/*.jar app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]