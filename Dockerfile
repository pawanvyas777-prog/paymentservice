FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY target/paymentservice-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENV JAVA_OPTS="-Xms128m -Xmx256m"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]


# ENTRYPOINT ["java", "-jar", "app.jar"]

