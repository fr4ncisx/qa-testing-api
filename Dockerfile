FROM amazoncorretto:21-alpine-jdk

WORKDIR /app

COPY pom.xml ./
COPY mvnw ./
COPY .mvn .mvn

RUN ./mvnw dependency:go-offline -B

COPY src ./src

RUN ./mvnw clean package -DskipTests && cp target/*.jar SimpleAPI.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "SimpleAPI.jar"]