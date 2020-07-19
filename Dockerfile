FROM openjdk:12
ADD target/eComerce.jar eComerce.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "eComerce.jar"]