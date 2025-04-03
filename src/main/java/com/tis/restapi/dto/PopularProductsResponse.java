package com.tis.restapi.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PopularProductsResponse {
	private List<PopularProductDTO> popularProducts;

	public PopularProductsResponse(List<PopularProductDTO> popularProducts) {
		this.popularProducts = popularProducts;
	}
}