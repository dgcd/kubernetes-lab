FROM dgcd/reference-snapshot:java-runtime

WORKDIR /kubelab/backend

COPY ./build/libs/kubelab-backend.jar .

USER starter:starter

ENTRYPOINT ["java", "-jar", "kubelab-backend.jar"]
