FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

ENTRYPOINT ["java","-jar","target/Generic_Data_Ingestion_Service-0.0.1-SNAPSHOT.jar"]