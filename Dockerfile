FROM optum-docker-prod.repo1.uhc.com/ob-innovation/base-image:j21_20241210

ENV HOST='0.0.0.0'
EXPOSE 8080

USER root

# Fetch and Install Optum Certs
ENV OPTUM_CA_HOME="/usr/local/share/ca-certificates"

RUN apk add curl

RUN mkdir -p "$OPTUM_CA_HOME"

RUN curl -L http://ocsp.optum.com/pki/OptumRootCA.crt -o $OPTUM_CA_HOME/OptumRootCA.crt
RUN curl -L http://ocsp.optum.com/pki/OptumInternalPolicyCA.crt -o $OPTUM_CA_HOME/OptumInternalPolicyCA.crt
RUN curl -L http://ocsp.optum.com/pki/OptumInternalIssuingCA2.crt -o $OPTUM_CA_HOME/OptumInternalIssuingCA2.crt
RUN curl -L http://ocsp.optum.com/pki/OptumInternalPolicyCA2.crt -o $OPTUM_CA_HOME/OptumInternalPolicyCA2.crt

# Log downloads for info/debugging
RUN ls -al $OPTUM_CA_HOME

# Install certs command
RUN update-ca-certificates

# Fetch needed key/trust stores
ENV OPTUM_KEYSTORE_HOME="/optum/keystores"
RUN mkdir -p "$OPTUM_KEYSTORE_HOME"
RUN chown -R 1001:1001 /optum/
#RUN chmod a+rw "$OPTUM_KEYSTORE_HOME"

# Download needed key/trust stores
ENV OPTUM_STANDARD_TRUSTSTORE="$OPTUM_KEYSTORE_HOME/standard_trusts.jks"
RUN curl -L https://repo1.uhc.com/artifactory/UHG-certificates/standard_trusts.jks -o $OPTUM_STANDARD_TRUSTSTORE

# Log downloads for info/debugging
RUN ls -al $OPTUM_KEYSTORE_HOME

# Fetch ElasticAPM agent
RUN mkdir -p /opt/elastic/apm
RUN chmod -R 755 /opt/elastic/apm
RUN curl -L https://repo1.uhc.com/artifactory/UHG-Thirdparty-Snapshots/com/elastic/apm/agents/java/current/elastic-apm-agent.jar -o /opt/elastic/apm/elastic-apm-agent.jar

# ElasticAPM agent java argument
ENV ES_AGENT="-javaagent:/opt/elastic/apm/elastic-apm-agent.jar"

# ElasticAPM required environment variables
#  Defaulted to Development environment values! Can and should be overridden by pod env variables
ENV ELASTIC_APM_SERVICE_NAME="ob_core_employer-details-api_uhgwm110-005677"
ENV ELASTIC_APM_GLOBAL_LABELS="com_uhg_appid=uhgwm110-005677"
ENV ELASTIC_APM_SERVER_URL="https://eapm-nonprod.uhc.com:443"
ENV ELASTIC_APM_VERIFY_SERVER_CERT="false"
ENV ELASTIC_APM_ENABLED="true"
ENV ELASTIC_APM_LOG_LEVEL="WARN"
ENV ELASTIC_APM_ENVIRONMENT="OB_CORE_dev"

RUN addgroup -S spring && adduser -S spring -G spring

USER spring:spring

COPY ./target/*.jar EmployerDetailsApi.jar
COPY ./src/main/resources/* ./

WORKDIR /

CMD ["/bin/sh", "-c","java $ES_AGENT -jar /EmployerDetailsApi.jar"]
