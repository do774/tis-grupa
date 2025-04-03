package com.tis.restapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String reviewer;
	private String text;
	private int rating;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
}
