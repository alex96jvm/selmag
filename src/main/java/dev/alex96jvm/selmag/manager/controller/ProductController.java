package dev.alex96jvm.selmag.manager.controller;

import dev.alex96jvm.selmag.manager.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products")
public class ProductController {
    private final ProductService productService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String getProductList(Model model){
        model.addAttribute("products", this.productService.findAllProducts());
        return "catalogue/products/list";
    }
}
