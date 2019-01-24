FROM  java:8
WORKDIR /server
ADD target/*.jar app.jar
ENV spring.cloud.config.failFast=false
CMD "java -jar app.jar"
CMD  ["java", "-jar", "app.jar"]