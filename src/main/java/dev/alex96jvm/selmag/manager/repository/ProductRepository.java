package dev.alex96jvm.selmag.manager.repository;

import dev.alex96jvm.selmag.manager.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();

    Product save(Product product);

    Optional<Product> findById(Integer productId);
}
