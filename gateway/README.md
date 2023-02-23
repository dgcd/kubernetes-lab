# Gateway module

```shell
./gradlew :gateway:build

docker build -t dgcd/reference-snapshot:gateway -f ./gateway/deploy/Dockerfile ./gateway && \
docker push     dgcd/reference-snapshot:gateway

kubectl apply -f gateway/deploy/secrets.yaml

helm upgrade \
  --debug \
  --install kubelab-gateway gateway/deploy/helm-chart/ \
  --set image.digest=6ba05a5d1a4f0f3c8d0f82c7a53cf40801ff875759709b03d6f06ba65523079c \
  --set global.namespace=kubelab-dev \
  --namespace kubelab-dev
```
