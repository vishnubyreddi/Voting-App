FROM openjdk:17
EXPOSE 8080
ADD target/todo-app.jar todo-app.jar
ENTRYPOINT ["java","-jar","/todo-app.jar"]