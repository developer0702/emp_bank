package com.optum.ftps.ob.core.employer.details.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLException;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;


import org.springframework.beans.factory.annotation.Value;


import reactor.netty.http.client.HttpClient;



@Slf4j
@Configuration
public class WebClientConfig {

    private final String employerAssociationServiceBaseUrl;

    private final String employerServiceBaseUrl;

    public WebClientConfig(
            @Value("${aggregate.base.url}") String employerAssociationServiceBaseUrl,
            @Value("${base.url}") String employerServiceBaseUrl
    ) {
        this.employerAssociationServiceBaseUrl = employerAssociationServiceBaseUrl;
        this.employerServiceBaseUrl = employerServiceBaseUrl;
    }



    /**
     * Creates an SSL-enabled HttpClient.
     * Uses an insecure trust manager for self-signed certificates (should be avoided in production).
     */
    private HttpClient createSecureHttpClient() {
        return HttpClient.create()
                .secure(
                        sslContextSpec -> {
                            try {
                                sslContextSpec.sslContext(
                                        SslContextBuilder.forClient()
                                                .trustManager(
                                                        InsecureTrustManagerFactory
                                                                .INSTANCE) // Insecure trust manager
                                                // (use properly signed
                                                // certs in production)
                                                .build());
                            } catch (SSLException e) {
                                throw new RuntimeException(e);
                            }
                        });
    }
    }