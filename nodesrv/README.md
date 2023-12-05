# Simple server on Node.js

```shell
docker build -t dgcd/reference-snapshot:nodesrv -f ./nodesrv/deploy/Dockerfile ./nodesrv && \
docker push     dgcd/reference-snapshot:nodesrv

helm upgrade \
  --debug \
  --install kubelab-nodesrv nodesrv/deploy/helm-chart/ \
  --set image.digest=488d007c22dc68e7262d6944e727e82ac186618a933f361a3c294b31c5a7b32e \
  --set global.namespace=kubelab-dev \
  --namespace kubelab-dev
```

