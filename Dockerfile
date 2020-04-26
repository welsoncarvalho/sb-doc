FROM openjdk:8

COPY target/sb-doc*.jar /opt/sb-doc.jar
WORKDIR /opt

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "sb-doc.jar"]
