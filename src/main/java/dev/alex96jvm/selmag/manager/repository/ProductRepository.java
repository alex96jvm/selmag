package dev.alex96jvm.selmag.manager.repository;

import dev.alex96jvm.selmag.manager.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();

    Product save(Product product);
}
