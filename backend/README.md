# Backend module

```shell
./gradlew :backend:build

docker build -t dgcd/reference-snapshot:backend -f ./backend/deploy/Dockerfile ./backend && \
docker push     dgcd/reference-snapshot:backend

helm upgrade \
  --debug \
  --install kubelab-backend backend/deploy/helm-chart/ \
  --set image.digest=1d76f7f648543cd26cf2b18e64ec3dc4bb39847a5778e6d9c6f05c342c5d14ad \
  --set global.namespace=kubelab-dev \
  --namespace kubelab-dev
```
