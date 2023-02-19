FROM eclipse-temurin:19-jre-focal

LABEL maintainer="dgcd"

WORKDIR /kubelab/gateway

COPY ./build/libs/kubelab-gateway.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "kubelab-gateway.jar"]
