package com.tis.restapi.service;

import com.tis.restapi.dto.PopularProductDTO;
import com.tis.restapi.model.Product;
import com.tis.restapi.model.Review;
import com.tis.restapi.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CurrencyConversionService currencyConversionService;

    public ProductService(ProductRepository productRepository, CurrencyConversionService currencyConversionService) {
        this.productRepository = productRepository;
        this.currencyConversionService = currencyConversionService;
    }

    public Product createProduct(Product product) {
        if (productRepository.findByCode(product.getCode()) != null) {
            throw new IllegalArgumentException("Product with code " + product.getCode() + " already exists.");
        }
        
        BigDecimal priceUsd = currencyConversionService.convertEurToUsd(product.getPriceEur());
        product.setPriceUsd(priceUsd);
        return productRepository.save(product);
    }

    public List<Product> getProducts(String code, String name) {
        if (code == null) {
            code = "";
        } else {
        	// zna ostati \n u postmanu iz nekog razloga, pa trimam
            code = code.trim();
        }
        if (name == null) {
            name = "";
        } else {
            name = name.trim();
        }
        return productRepository.findByCodeContainingIgnoreCaseAndNameContainingIgnoreCase(code, name);
    }

    public List<PopularProductDTO> getPopularProducts() {
        List<Product> products = productRepository.findAll();
        List<PopularProductDTO> popularProducts = new ArrayList<>();

        for (Product product : products) {
            List<Review> reviews = product.getReviews();
            if (reviews != null && !reviews.isEmpty()) {
                double average = calculateAverageRating(reviews);
                BigDecimal avgRounded = roundRating(average);
                popularProducts.add(new PopularProductDTO(product.getName(), avgRounded));
            }
        }

        popularProducts.sort(Comparator.comparing(PopularProductDTO::getAverageRating).reversed());
        return popularProducts.size() > 3 ? popularProducts.subList(0, 3) : popularProducts;
    }

    private double calculateAverageRating(List<Review> reviews) {
        return reviews.stream().mapToInt(Review::getRating).average().orElse(0);
    }

    private BigDecimal roundRating(double rating) {
        return BigDecimal.valueOf(rating).setScale(1, RoundingMode.HALF_UP);
    }
}
