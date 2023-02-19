# Gateway module

```shell
./gradlew :gateway:build

docker build -t dgcd/reference-snapshot:gateway-1 -f ./gateway/deploy/Dockerfile ./gateway && \
docker push dgcd/reference-snapshot:gateway-1

kubectl apply -f gateway/deploy/secrets.yaml

helm upgrade \
  --debug \
  --install kubelab-gateway gateway/deploy/helm-chart/ \
  --set image.digest=31fe81569fedac2ce545731266620b20a23d122dc85463e02ac8dbbd3aac297d \
  --set global.namespace=kubelab-dev \
  --namespace kubelab-dev
```
