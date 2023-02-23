# Simple server on Node.js

```shell
docker build -t dgcd/reference-snapshot:nodesrv -f ./nodesrv/deploy/Dockerfile ./nodesrv && \
docker push     dgcd/reference-snapshot:nodesrv

helm upgrade \
  --debug \
  --install kubelab-nodesrv nodesrv/deploy/helm-chart/ \
  --set image.digest=2f167ede01ca81cd8825923a07aa4c3059e8cfbb26575bdf78afd8988c586b8e \
  --set global.namespace=kubelab-dev \
  --namespace kubelab-dev
```

