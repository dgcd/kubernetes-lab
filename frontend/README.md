# Frontend module (Nginx + Vue.js)

```shell
# build front
docker build -t dgcd/reference-snapshot:frontend-2 -f ./frontend/deploy/Dockerfile ./frontend && \
docker push dgcd/reference-snapshot:frontend-2

helm upgrade \
  --debug \
  --install kubelab-frontend frontend/deploy/helm-chart/ \
  --set image.digest=a8746320fa4f5feb55b0b994f359d99cf990e7b638195a5b54b8778113f1a9b1 \
  --set global.namespace=kubelab-dev \
  --namespace kubelab-dev
```
