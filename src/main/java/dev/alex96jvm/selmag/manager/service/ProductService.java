package dev.alex96jvm.selmag.manager.service;

import dev.alex96jvm.selmag.manager.entity.Product;
import java.util.List;

public interface ProductService {
   List<Product> findAllProducts();
}
