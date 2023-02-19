# Backend module

```shell
./gradlew :backend:build

docker build -t dgcd/reference-snapshot:backend-1 -f ./backend/deploy/Dockerfile ./backend && \
docker push dgcd/reference-snapshot:backend-1

helm upgrade \
  --debug \
  --install kubelab-backend backend/deploy/helm-chart/ \
  --set image.digest=a134dbebaa78c9818baf51a946610336b1b9c0e54a555ab2ccae2114cf54d272 \
  --set global.namespace=kubelab-dev \
  --namespace kubelab-dev
```
