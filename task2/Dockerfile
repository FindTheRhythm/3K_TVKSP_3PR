
# ЭТАП 1. Сборка приложения
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /build

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -DskipTests -B


# ЭТАП 2. Запуск приложения
FROM eclipse-temurin:21-jre

LABEL project="TVKSP Practical Work 3"
LABEL application="Spring Boot PostgreSQL API"
LABEL author="Шурупов Д.А."
LABEL student.group="ИКБО-14-23"

ENV DB_HOST=db
ENV DB_PORT=5432
ENV DB_NAME=practice3
ENV DB_USER=practice_user

WORKDIR /app

RUN apt-get update \
    && apt-get install -y --no-install-recommends wget ca-certificates \
    && mkdir -p /app/static \
    && wget --tries=3 --timeout=30 \
       -O /app/static/MIREA_Gerb_Colour.png \
       https://www.mirea.ru/upload/medialibrary/80f/MIREA_Gerb_Colour.png \
    && test -s /app/static/MIREA_Gerb_Colour.png \
    && rm -rf /var/lib/apt/lists/*

COPY --from=builder /build/target/practical3-1.0.0.jar /app/app.jar

RUN mkdir -p /app/resources/static \
    && cp /app/static/MIREA_Gerb_Colour.png \
          /app/resources/static/MIREA_Gerb_Colour.png

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
