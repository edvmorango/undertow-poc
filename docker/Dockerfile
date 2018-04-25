FROM openjdk:8-jre-alpine
VOLUME /tmp
ENV PROFILE_ACTIVE staging
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
ADD private.jwk /private.jwk
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]