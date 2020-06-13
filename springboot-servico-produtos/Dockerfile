FROM openjdk:12
VOLUME /tmp
ADD ./target/springboot-servico-produtos-0.0.1-SNAPSHOT.jar servico-produtos.jar
ENTRYPOINT ["java","-jar","/servico-produtos.jar"]