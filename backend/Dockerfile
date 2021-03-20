FROM adoptopenjdk/openjdk14-openj9:alpine
VOLUME /tmp
COPY target/app.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=prod", "-jar", "/app.jar"]
