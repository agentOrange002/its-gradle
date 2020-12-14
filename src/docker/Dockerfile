#FROM openjdk:14-jdk-alpine
##ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#ARG DEPENDENCY=build
#RUN echo ${DEPENDENCY}
#COPY ${DEPENDENCY}/libs/its-0.0.1-SNAPSHOT.jar /app/lib/its.jar
#ENTRYPOINT ["java","-jar","/app/lib/its.jar"]

FROM openjdk:14-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
RUN apk --update add fontconfig ttf-dejavu
