#FROM eclipse-temurin
#FROM eclipse-temurin:17-jre-alpine
#FROM openjdk:17-jdk-slim
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

# Используем образ с Java Development Kit (JDK)
FROM maven:3.8.5-openjdk-17-slim AS build

# Копируем файлы с исходным кодом приложения в контейнер
COPY ./ /app

# Задаем рабочую директорию внутри контейнера
WORKDIR /app

# Выполняем сборку приложения с помощью Maven
RUN mvn clean package

# Создаем новый образ, используя JRE
FROM openjdk:17-jdk-slim

# Копируем собранный JAR файл из предыдущего образа в новый образ
COPY --from=build /app/target/*.jar /app/app.jar

# Задаем точку входа для запуска приложения
ENTRYPOINT ["java","-jar","/app/app.jar"]
