FROM openjdk:11

ADD target/vending_machine-0.0.1-SNAPSHOT.jar vending_machine-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "vending_machine-0.0.1-SNAPSHOT.jar"]
