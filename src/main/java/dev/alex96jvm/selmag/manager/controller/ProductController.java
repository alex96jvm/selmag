package dev.alex96jvm.selmag.manager.controller;

import dev.alex96jvm.selmag.manager.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
}
