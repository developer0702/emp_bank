package com.optum.ftps.ob.core.employer.details.config;

import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;

@Slf4j
@Configuration
public class WebClientConfig {

    @Value("${bis.base-url}")
    String baseUrl;

    @Value("${bis.authorization-token}")
    String authorizationToken;

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

    private WebClient createWebClient(WebClient.Builder builder, String baseUrl) {
        return builder.baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + authorizationToken)
                .defaultHeader("X-Upstream-ENV", "dev")
                .clientConnector(new ReactorClientHttpConnector(createSecureHttpClient()))
                .build();
    }

    @Bean("employerWebClient")
    public WebClient employerWebClient(WebClient.Builder builder) {
        return createWebClient(builder, baseUrl);
    }
}
