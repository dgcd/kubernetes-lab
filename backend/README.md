# Backend module

```shell
./gradlew :backend:build

docker build -t dgcd/reference-snapshot:backend -f ./backend/deploy/Dockerfile ./backend && \
docker push     dgcd/reference-snapshot:backend

helm upgrade \
  --debug \
  --install kubelab-backend backend/deploy/helm-chart/ \
  --set image.digest=9bfab24fd001d47194c597ad0c96bc3323573ecd04437c832facafde763ff561 \
  --set global.namespace=kubelab-dev \
  --namespace kubelab-dev
```
