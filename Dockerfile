
FROM openjdk:17
COPY /target/* /bff.jar
ENTRYPOINT ["java","-jar","bff.jar"]
EXPOSE 9393

