FROM mayan31370/openjdk-alpine-with-chinese-timezone:8-jdk

EXPOSE 8080

RUN apk --no-cache add \
    fontconfig \
    ttf-dejavu

ENTRYPOINT ["java","-Xmx256m","-Xms256m","-jar","-Dserver.port=8080","/app.jar"]

ADD target/*.jar app.jar
