package dev.alex96jvm.selmag.manager.controller;

import dev.alex96jvm.selmag.manager.controller.payload.NewProductPayload;
import dev.alex96jvm.selmag.manager.controller.payload.UpdateProductPayload;
import dev.alex96jvm.selmag.manager.entity.Product;
import dev.alex96jvm.selmag.manager.service.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("catalogue/products/{productId:\\d+}")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final MessageSource messageSource;

    @ModelAttribute("product")
    public Product product(@PathVariable("productId") int productId){
        return this.productService.findProduct(productId)
                .orElseThrow(() -> new NoSuchElementException("catalogue.errors.product.not_found"));
    }

        @GetMapping()
    public String getProduct(){
        return "/catalogue/products/product";
    }

    @GetMapping("edit")
    public String getProductEditPage(){
        return "/catalogue/products/edit";
    }

    @PostMapping("edit")
    public String updateProduct(@ModelAttribute("product") Product product,
                                UpdateProductPayload payload){
        this.productService.updateProduct(product.getId(), payload.title(), payload.details());
        return "redirect:/catalogue/products/%d".formatted(product.getId());
    }

    @PostMapping("delete")
    public String deleteProduct(@ModelAttribute("product") Product product){
        this.productService.deleteProduct(product.getId());
        return "redirect:/catalogue/products/list";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String noSuchElementException(NoSuchElementException e, Model model,
                                         HttpServletResponse response, Locale locale){
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error",
                this.messageSource.getMessage(e.getMessage(), new Object[0],
                        e.getMessage(), locale));
        return "errors/404";
    }

}
