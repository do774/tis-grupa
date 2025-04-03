package com.tis.restapi.service;

import com.tis.restapi.dto.PopularProductDTO;
import com.tis.restapi.model.Product;
import com.tis.restapi.model.Review;
import com.tis.restapi.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class ProductServiceTest {

	private final ProductService productService;
	private final ProductRepository productRepository;

	@Autowired
	public ProductServiceTest(ProductService productService, ProductRepository productRepository) {
		this.productService = productService;
		this.productRepository = productRepository;
	}

	@BeforeEach
	public void setup() {
		productRepository.deleteAll();
	}

	@Test
	public void testCreateProduct_Success() {
		Product product = new Product();
		product.setCode("PROD00000000001");
		product.setName("Test Product");
		product.setPriceEur(BigDecimal.valueOf(100));
		product.setDescription("Test description");

		Product saved = productService.createProduct(product);
		assertThat(saved.getId()).isNotNull();
		assertThat(saved.getPriceUsd()).isNotNull();
		assertThat(saved.getPriceUsd()).isNotEqualTo(product.getPriceEur());
	}

	@Test
	public void testCreateProduct_DuplicateCode() {
		Product product1 = new Product();
		product1.setCode("PROD00000000002");
		product1.setName("Product 1");
		product1.setPriceEur(BigDecimal.valueOf(200));
		product1.setDescription("First product");
		productService.createProduct(product1);

		Product product2 = new Product();
		product2.setCode("PROD00000000002");
		product2.setName("Product 2");
		product2.setPriceEur(BigDecimal.valueOf(150));
		product2.setDescription("Second product");

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			productService.createProduct(product2);
		});
		assertThat(exception.getMessage()).contains("already exists");
	}

	@Test
	public void testGetProducts_Filtering() {
		Product product = new Product();
		product.setCode("PROD00000000003");
		product.setName("Xiaomi 13");
		product.setPriceEur(BigDecimal.valueOf(600));
		product.setDescription("Xiaomi flagship phone");
		productService.createProduct(product);

		List<Product> products = productService.getProducts("PROD00000000003", "Xiaomi");
		assertThat(products).hasSize(1);
		assertThat(products.get(0).getName()).isEqualTo("Xiaomi 13");
	}

	@Test
	public void testGetPopularProducts() {
		Product product = new Product();
		product.setCode("PROD00000000004");
		product.setName("Popular Product");
		product.setPriceEur(BigDecimal.valueOf(700));
		product.setDescription("Description");

		Review r1 = new Review();
		r1.setReviewer("User1");
		r1.setText("Great");
		r1.setRating(5);
		r1.setProduct(product);

		Review r2 = new Review();
		r2.setReviewer("User2");
		r2.setText("Good");
		r2.setRating(4);
		r2.setProduct(product);

		product.setReviews(Arrays.asList(r1, r2));
		productService.createProduct(product);

		List<PopularProductDTO> popular = productService.getPopularProducts();
		assertThat(popular).hasSize(1);
		PopularProductDTO dto = popular.get(0);
		assertThat(dto.getName()).isEqualTo("Popular Product");
		assertThat(dto.getAverageRating()).isNotNull();
	}
}
