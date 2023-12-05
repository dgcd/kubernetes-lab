# Gateway module

```shell
kubectl apply -f gateway/deploy/secrets.yaml

./gradlew :gateway:build

docker build -t dgcd/reference-snapshot:gateway -f ./gateway/deploy/Dockerfile ./gateway && \
docker push     dgcd/reference-snapshot:gateway

helm upgrade \
  --debug \
  --install kubelab-gateway gateway/deploy/helm-chart/ \
  --set image.digest=de73ac68c7e33eeb2e58e6a5b8abf10b17db6a2de98d0494ef30fe9322bb0b15 \
  --set global.namespace=kubelab-dev \
  --namespace kubelab-dev
```
