FROM openjdk:8
COPY ./target/ConichiChallenge-0.0.1-SNAPSHOT.jar /usr/src/conichi/
WORKDIR /usr/src/conichi
EXPOSE 8080
CMD ["java", "-jar", "ConichiChallenge-0.0.1-SNAPSHOT.jar"]