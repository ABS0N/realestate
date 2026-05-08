# 1. fázis: Építés (Build)
# Egy könnyű Maven image-et használunk a kód lefordításához
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
# Csak a konfigurációt másoljuk először, hogy gyorsabb legyen a cache-elés
COPY pom.xml .
COPY src ./src
# Lefordítjuk a kódot és becsomagoljuk egy JAR fájlba
RUN mvn clean package -DskipTests

# 2. fázis: Futtatás (Runtime)
# Itt már csak a kész JAR-ra és a Java környezetre van szükségünk
FROM eclipse-temurin:21-jre
WORKDIR /app
# Átmásoljuk az első fázisban elkészült JAR fájlt
COPY --from=build /app/target/*.jar app.jar
# Megnyitjuk a 8080-as portot (ezen fut alapból a Spring)
EXPOSE 8080
# Elindítjuk az alkalmazást
ENTRYPOINT ["java", "-jar", "app.jar"]