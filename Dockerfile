FROM openjdk:17-jdk
WORKDIR /app
COPY target/todo-app.jar /app/todo-app.jar
EXPOSE 8080
CMD ["java", "-jar", "todo-app.jar"]
