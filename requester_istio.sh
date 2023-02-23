#!/bin/bash

export CLUSTER_HOST=192.168.88.42 && \
export CLUSTER_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="http2")].nodePort}') && \
printf "address: $CLUSTER_HOST:$CLUSTER_PORT\n"

curl http://$CLUSTER_HOST:$CLUSTER_PORT/node -H "Host: kubelab.lab.example.com" && \
curl http://$CLUSTER_HOST:$CLUSTER_PORT/nnode -H "Host: kubelab.lab.example.com" && \
curl http://$CLUSTER_HOST:$CLUSTER_PORT/api -s -X POST \
    -H "Content-Type: application/json" \
    -H "Host: kubelab.lab.example.com" \
    -d '{"requestName": "istio_req_123", "requestId": 123}' | jq && \
curl http://$CLUSTER_HOST:$CLUSTER_PORT/test -H "Host: kubelab.lab.example.com" && \
printf "\n\n"
