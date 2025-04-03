package com.tis.restapi.controller;

import com.tis.restapi.model.Product;
import com.tis.restapi.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductViewController {

    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/view/products")
    public String viewProducts(Model model) {
        List<Product> products = productService.getProducts("", "");
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/view/popular")
    public String viewPopularProducts(Model model) {
        model.addAttribute("popularProducts", productService.getPopularProducts());
        return "popular";
    }
}
