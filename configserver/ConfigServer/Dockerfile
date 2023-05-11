FROM openjdk:11
EXPOSE 8889
ADD /target/confserver-0.0.1-SNAPSHOT.jar confserver.jar
ENTRYPOINT [ "java", "-jar", "confserver.jar" ]