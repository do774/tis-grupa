package com.tis.restapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
	
	@NotBlank(message = "Code is required.")
    @Size(min = 15, max = 15, message = "Code must be 15 characters long.")
    private String code;

    @NotBlank(message = "Name is required.")
    private String name;

    @NotNull(message = "Price in EUR is required.")
    private BigDecimal priceEur;
	private String description;
}
