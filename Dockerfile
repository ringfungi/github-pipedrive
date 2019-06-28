FROM openjdk:12
ADD  build/libs/github-client-0.0.1-SNAPSHOT.jar github-client-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "github-client-0.0.1-SNAPSHOT.jar"]