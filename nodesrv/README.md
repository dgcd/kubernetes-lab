# Simple server on Node.js

```shell
docker build -t dgcd/reference-snapshot:nodesrv-1 -f ./nodesrv/deploy/Dockerfile ./nodesrv && \
docker push dgcd/reference-snapshot:nodesrv-1

helm upgrade \
  --debug \
  --install kubelab-nodesrv nodesrv/deploy/helm-chart/ \
  --set image.digest=94aef27ad8ac66b6bedbbcb207714b0d9b43cc7b9e11228846cc99984a0fc1d8 \
  --set global.namespace=kubelab-dev \
  --namespace kubelab-dev
```

