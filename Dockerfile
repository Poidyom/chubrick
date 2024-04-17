FROM ubuntu:latest
LABEL authors="mmm"

RUN apt update \
    && apt install -y maven

WORKDIR /app

COPY . .

RUN mvn package
CMD ["java", "-jar", "target/chubrick-1.0-SNAPSHOT.jar"]