## HCC K8S Support

Temporary home of support files and scripts used to manage or configure the deployment of the Contribution API service.
These will likely move to a new home once we have things working consistently.

# Ingress, Egress, and Port Usage

Since the Spotless tool doesn't like comments in the yaml files, I am saving the context info here on why we have
certain ports and connection types enabled.

## Ingress

### Spring Boot Apps

HTTP over 8080, HTTPS over 443 with the F5 Load Balancer

```yaml
- protocol: TCP
  port: 8080
- protocol: TCP
  port: 443
```

### Tomcat

```yaml
- port: 8009
  protocol: TCP
- port: 8082
  protocol: TCP
```

## Egress

HTTPS over 443 with the F5 Load Balancer

```yaml
- protocol: TCP
  port: 443
```

### ElasticSearch

```yaml
- port: 9200
  protocol: TCP
- port: 9300
  protocol: TCP
```

### MySQL

```yaml
- port: 3306
  protocol: TCP
- port: 33060
  protocol: TCP
```

### PostgreSQL

```yaml
- port: 5432
  protocol: TCP
```

### Core Banking / Common Optum Ports

```yaml
- protocol: TCP
  port: 1433
- protocol: TCP
  port: 443
- protocol: TCP
  port: 80
- protocol: TCP
  port: 53
- protocol: TCP
  port: 6444
- protocol: TCP
  port: 4317
- protocol: TCP
  port: 9093
```

### BIS DB2

```yaml
- port: 50002
  protocol: TCP
```

