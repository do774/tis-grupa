package com.tis.restapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tis.restapi.dto.ProductRequest;
import com.tis.restapi.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductControllerTest {

    private final MockMvc mockMvc;
    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProductControllerTest(MockMvc mockMvc, ProductRepository productRepository, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.productRepository = productRepository;
        this.objectMapper = objectMapper;
    }

    @BeforeEach
    public void setup() {
        productRepository.deleteAll();
    }

    @Test
    public void testCreateProductEndpoint() throws Exception {
        ProductRequest request = new ProductRequest();
        request.setCode("PROD00000000005");
        request.setName("Controller Test Product");
        request.setPriceEur(BigDecimal.valueOf(500));
        request.setDescription("Controller test description");

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.code", is("PROD00000000005")))
                .andExpect(jsonPath("$.name", is("Controller Test Product")))
                .andExpect(jsonPath("$.priceEur", is(500)))
                .andExpect(jsonPath("$.description", is("Controller test description")))
                .andExpect(jsonPath("$.priceUsd", notNullValue()));
    }

    @Test
    public void testGetProductsEndpoint() throws Exception {
        ProductRequest request = new ProductRequest();
        request.setCode("PROD00000000006");
        request.setName("Filter Controller Product");
        request.setPriceEur(BigDecimal.valueOf(600));
        request.setDescription("Test product for filtering");

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/products")
                .param("code", "PROD00000000006")
                .param("name", "Filter"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].code", is("PROD00000000006")))
                .andExpect(jsonPath("$[0].name", is("Filter Controller Product")));
    }

    @Test
    public void testGetPopularProductsEndpointWhenEmpty() throws Exception {
        mockMvc.perform(get("/products/popular"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.popularProducts", empty()));
    }
}
