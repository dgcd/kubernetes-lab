# Модуль с ресурсами Istio

- установить
    - https://istio.io/latest/docs/ops/integrations/prometheus/
    - https://istio.io/latest/docs/ops/integrations/kiali/

- установить чарт

```shell
helm upgrade \
  --debug \
  --install kubelab-istio istio/helm-chart/ \
  --set global.namespace=kubelab-dev \
  --namespace kubelab-dev
```
