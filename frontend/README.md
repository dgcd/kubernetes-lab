# Frontend module (Nginx + Vue.js)

```shell
# build front

docker build -t dgcd/reference-snapshot:frontend -f ./frontend/deploy/Dockerfile ./frontend && \
docker push     dgcd/reference-snapshot:frontend

helm upgrade \
  --debug \
  --install kubelab-frontend frontend/deploy/helm-chart/ \
  --set image.digest=a0a634043f93c89fe0dfa49a808a8bf1965328b797a1357648d94f95b6c5d9aa \
  --set global.namespace=kubelab-dev \
  --namespace kubelab-dev
```
