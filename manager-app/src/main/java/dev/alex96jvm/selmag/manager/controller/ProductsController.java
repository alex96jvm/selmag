package dev.alex96jvm.selmag.manager.controller;

import dev.alex96jvm.selmag.manager.client.BadRequestException;
import dev.alex96jvm.selmag.manager.client.ProductsRestClient;
import dev.alex96jvm.selmag.manager.controller.payload.NewProductPayload;
import dev.alex96jvm.selmag.manager.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products")
public class ProductsController {

    private final ProductsRestClient productsRestClient;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String getProductList(Model model){
        model.addAttribute("products", this.productsRestClient.findAllProducts());
        return "catalogue/products/list";
    }

    @GetMapping("create")
    public String getNewProductPage(){
        return "catalogue/products/new_product";
    }

    @PostMapping("create")
    public String createProduct(NewProductPayload payload,
                                Model model){
        try {
            Product product = this.productsRestClient.createProduct(payload.title(), payload.details());
            return "redirect:/catalogue/products/%d".formatted(product.id());
        } catch (BadRequestException e) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", e.getErrors());
            return "catalogue/products/new_product";
        }
    }
}
