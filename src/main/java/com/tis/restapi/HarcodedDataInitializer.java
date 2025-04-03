package com.tis.restapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.tis.restapi.model.Product;
import com.tis.restapi.model.Review;
import com.tis.restapi.service.ProductService;
import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class HarcodedDataInitializer implements CommandLineRunner {
	private final ProductService productService;

    public HarcodedDataInitializer(ProductService productService) {
        this.productService = productService;
    }

	@Override
	public void run(String... args) throws Exception {
		Product p1 = new Product();
		p1.setCode("PROD00000000001");
		p1.setName("Samsung Galaxy S25");
		p1.setPriceEur(new BigDecimal("800"));
		p1.setDescription("Samsung flagship phone");
		Review r1 = new Review();
		r1.setReviewer("Marko");
		r1.setText("Best smartphone");
		r1.setRating(5);
		r1.setProduct(p1);
		Review r2 = new Review();
		r2.setReviewer("Josip");
		r2.setText("Very nice phone");
		r2.setRating(4);
		r2.setProduct(p1);
		p1.setReviews(Arrays.asList(r1, r2));
		productService.createProduct(p1);

		Product p2 = new Product();
		p2.setCode("PROD00000000002");
		p2.setName("iPhone 15");
		p2.setPriceEur(new BigDecimal("700"));
		p2.setDescription("Apple iPhone SE");
		Review r3 = new Review();
		r3.setReviewer("Ivana");
		r3.setText("Great phone");
		r3.setRating(5);
		r3.setProduct(p2);
		Review r4 = new Review();
		r4.setReviewer("Matija");
		r4.setText("Great phone, but bad battery life");
		r4.setRating(4);
		r4.setProduct(p2);
		p2.setReviews(Arrays.asList(r3, r4));
		productService.createProduct(p2);

		Product p3 = new Product();
		p3.setCode("PROD00000000003");
		p3.setName("Xiaomi 15");
		p3.setPriceEur(new BigDecimal("600"));
		p3.setDescription("Xiaomi flagship phone");
		Review r5 = new Review();
		r5.setReviewer("Marry");
		r5.setText("Nice design, but laggy");
		r5.setRating(4);
		r5.setProduct(p3);
		Review r6 = new Review();
		r6.setReviewer("John");
		r6.setText("Best phone ever");
		r6.setRating(5);
		r6.setProduct(p3);
		p3.setReviews(Arrays.asList(r5, r6));
		productService.createProduct(p3);

		Product p4 = new Product();
		p4.setCode("PROD00000000004");
		p4.setName("OnePlus 13");
		p4.setPriceEur(new BigDecimal("650"));
		p4.setDescription("OnePlus phone");
		Review r7 = new Review();
		r7.setReviewer("Bobby");
		r7.setText("Super fast charging");
		r7.setRating(4);
		r7.setProduct(p4);
		Review r8 = new Review();
		r8.setReviewer("Roby");
		r8.setText("Pretty good");
		r8.setRating(4);
		r8.setProduct(p4);
		p4.setReviews(Arrays.asList(r7, r8));
		productService.createProduct(p4);

		Product p5 = new Product();
		p5.setCode("PROD00000000005");
		p5.setName("Google Pixel 9");
		p5.setPriceEur(new BigDecimal("750"));
		p5.setDescription("Google flagship phone");
		Review r9 = new Review();
		r9.setReviewer("Elvis");
		r9.setText("Best camera");
		r9.setRating(5);
		r9.setProduct(p5);
		Review r10 = new Review();
		r10.setReviewer("Jerry");
		r10.setText("Great software");
		r10.setRating(4);
		r10.setProduct(p5);
		p5.setReviews(Arrays.asList(r9, r10));
		productService.createProduct(p5);
	}
}
