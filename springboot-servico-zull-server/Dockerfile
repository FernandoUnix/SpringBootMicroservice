FROM openjdk:12
VOLUME /tmp
EXPOSE 8090
ADD ./target/springboot-servico-zull-server-0.0.1-SNAPSHOT.jar zuul-server.jar
ENTRYPOINT ["java","-jar","/zuul-server.jar"]