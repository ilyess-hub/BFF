FROM openjdk:17
COPY /target/BFF-0.0.1-SNAPSHOT.jar /bff.jar
ENTRYPOINT ["java","-jar","/bff.jar"]
EXPOSE 9393

