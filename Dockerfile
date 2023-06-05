FROM maven:3.8.3-openjdk-17
WORKDIR /app
COPY . /app
RUN mvn clean install -X
CMD mvn spring-boot:run