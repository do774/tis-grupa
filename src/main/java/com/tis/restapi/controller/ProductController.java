package com.tis.restapi.controller;

import com.tis.restapi.dto.PopularProductDTO;
import com.tis.restapi.dto.PopularProductsResponse;
import com.tis.restapi.dto.ProductRequest;
import com.tis.restapi.model.Product;
import com.tis.restapi.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody ProductRequest productRequest) {
        Product product = new Product();
        product.setCode(productRequest.getCode());
        product.setName(productRequest.getName());
        product.setPriceEur(productRequest.getPriceEur());
        product.setDescription(productRequest.getDescription());
        return productService.createProduct(product);
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) String code,
                                     @RequestParam(required = false) String name) {
        return productService.getProducts(code, name);
    }

    @GetMapping("/popular")
    public PopularProductsResponse getPopularProducts() {
        List<PopularProductDTO> popularProducts = productService.getPopularProducts();
        return new PopularProductsResponse(popularProducts);
    }
}
