package com.diviso.graeshoppe.order.client.store;

import com.diviso.graeshoppe.order.client.ExcludeFromComponentScan;
import com.diviso.graeshoppe.order.client.TokenRelayRequestInterceptor;
import com.diviso.graeshoppe.order.security.oauth2.AuthorizationHeaderUtil;

import feign.RequestInterceptor;
import java.io.IOException;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ExcludeFromComponentScan
@EnableConfigurationProperties
public class StoreClientConfiguration {

	@Bean(name = "oauth2RequestInterceptor")
    public RequestInterceptor getOAuth2RequestInterceptor(AuthorizationHeaderUtil authorizationHeaderUtil) throws IOException {
        return new TokenRelayRequestInterceptor(authorizationHeaderUtil);
    }
}
