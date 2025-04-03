package com.tis.restapi.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PopularProductDTO {
	private String name;
	private BigDecimal averageRating;

	public PopularProductDTO(String name, BigDecimal averageRating) {
		this.name = name;
		this.averageRating = averageRating;
	}
}
