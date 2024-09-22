package dev.alex96jvm.selmag.manager.client;

import dev.alex96jvm.selmag.manager.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RestClientProductsRestClient implements ProductsRestClient{

    private final RestClient restClient;

    @Override
    public List<Product> findAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(String title, String details) {
        return null;
    }

    @Override
    public Optional<Product> findProduct(int productId) {
        return Optional.empty();
    }

    @Override
    public void updateProduct(int productId, String title, String details) {

    }

    @Override
    public void deleteProduct(int productId) {

    }
}
