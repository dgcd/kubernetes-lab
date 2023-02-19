FROM eclipse-temurin:19-jre-focal

LABEL maintainer="dgcd"

WORKDIR /kubelab/backend

COPY ./build/libs/kubelab-backend.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "kubelab-backend.jar"]
