# define a imagem base
FROM openjdk:17
ADD targetapp/autenticacao-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

EXPOSE 8081