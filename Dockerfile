FROM eclipse-temurin:17-jre-alpine
ADD build/libs/bookstore-0.0.1-SNAPSHOT.jar /app/app.jar
CMD java -jar /app/app.jar