# Frontend module (Nginx + Vue.js)

```shell
# build front

docker build -t dgcd/reference-snapshot:frontend -f ./frontend/deploy/Dockerfile ./frontend && \
docker push     dgcd/reference-snapshot:frontend

helm upgrade \
  --debug \
  --install kubelab-frontend frontend/deploy/helm-chart/ \
  --set image.digest=eee30ba59877eeccfe58004b16681cb4ec927382d3c4c9fa95562a0e36413d94 \
  --set global.namespace=kubelab-dev \
  --namespace kubelab-dev
```
