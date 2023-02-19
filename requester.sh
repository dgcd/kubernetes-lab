#!/bin/bash

export CLUSTER_HOST=192.168.88.42 && \
export CLUSTER_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="http2")].nodePort}') && \
printf "address: $CLUSTER_HOST:$CLUSTER_PORT\n"

curl http://$CLUSTER_HOST:$CLUSTER_PORT/node && \
curl http://$CLUSTER_HOST:$CLUSTER_PORT/nnode && \
curl http://$CLUSTER_HOST:$CLUSTER_PORT/api -s -X POST -H "Content-Type: application/json" \
     -d '{"requestName": "req_42", "requestId": 42}' | jq && \
curl http://$CLUSTER_HOST:$CLUSTER_PORT/test && \
printf "\n\n"
