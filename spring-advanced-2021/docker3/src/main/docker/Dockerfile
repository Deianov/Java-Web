
# use base image 'openjdk'
FROM openjdk:11.0.7-jre

# copy and rename docker.jar
COPY build/libs/docker3.jar app.jar

# run command: "java -jar /app.jar"
ENTRYPOINT ["java","-jar","/app.jar"]