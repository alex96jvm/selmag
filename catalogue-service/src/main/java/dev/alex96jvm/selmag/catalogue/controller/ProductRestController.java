package dev.alex96jvm.selmag.catalogue.controller;

import dev.alex96jvm.selmag.catalogue.controller.payload.UpdateProductPayload;
import dev.alex96jvm.selmag.catalogue.entity.Product;
import dev.alex96jvm.selmag.catalogue.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalogue-api/products/{productId:\\d+}")

public class ProductRestController {

    private final ProductService productService;

    @ModelAttribute
    public Product getProduct(@PathVariable("productId") int productId){
        return this.productService.findProduct(productId)
                .orElseThrow(() -> new NoSuchElementException("catalogue.errors.product.not_found"));
    }

    @GetMapping
    public Product findProduct(@ModelAttribute("product") Product product){
        return product;
    }

    @PostMapping
    public ResponseEntity<?> updateProduct(@PathVariable("productId") int productId,
                                              @Valid @RequestBody UpdateProductPayload payload,
                                              BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors()) {
            if(bindingResult instanceof BindException exception){
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            this.productService.updateProduct(productId, payload.title(), payload.details());
            return ResponseEntity.noContent()
                    .build();
        }
    }
}
