package com.sopromadze.blogapi.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;





@Data
public class ProductRequest {

	@NotNull
	private Long categoryId;

	@NotNull
	private Long storeId;

	private String name;

	private Long quantity;


}
