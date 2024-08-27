package dev.alex96jvm.selmag.manager.repository;

import dev.alex96jvm.selmag.manager.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class InMemoryProductRepository implements ProductRepository{
    List<Product> products = Collections.synchronizedList(new LinkedList<>());
    public InMemoryProductRepository(){
        IntStream.range(1, 4)
                .forEach(i -> this.products.add(new Product(i, "Товар №%d".formatted(i),
                        "описание товара №%d".formatted(i))));
    }

    @Override
    public List<Product> findAll() {
        return Collections.unmodifiableList(this.products);
    }
}
