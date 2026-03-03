# Imagen base: Usamos Eclipse Temurin con JDK 17 sobre una distribución ligera de Linux (Alpine) para optimizar el tamaño
FROM eclipse-temurin:17-jdk-alpine

# Establece el directorio de trabajo dentro del contenedor. Las siguientes instrucciones se ejecutarán aquí
WORKDIR /app

# Copia el archivo JAR compilado desde tu máquina (host) al sistema de archivos del contenedor y lo renombra a app.jar
COPY target/invex-employee-service-0.0.1.jar app.jar

# Informa a Docker que el contenedor escuchará en el puerto 8080 en tiempo de ejecución
EXPOSE 8080

# Comando que se ejecuta al iniciar el contenedor: levanta la aplicación Java
ENTRYPOINT ["java","-jar","app.jar"]