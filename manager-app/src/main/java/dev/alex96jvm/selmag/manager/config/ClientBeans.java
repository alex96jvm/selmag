package dev.alex96jvm.selmag.manager.config;

import dev.alex96jvm.selmag.manager.client.RestClientProductsRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientBeans {
    @Bean
    public RestClientProductsRestClient productsRestClient(
            @Value("${selmag.services.catalogue.uri:http://localhost:8081}") String catalogueBaseUri){
        return new RestClientProductsRestClient(RestClient.builder()
                .baseUrl(catalogueBaseUri)
                .build());
    }
}
