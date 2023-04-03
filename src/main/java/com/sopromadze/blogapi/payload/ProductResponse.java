package com.sopromadze.blogapi.payload;

import lombok.Data;

import com.sopromadze.blogapi.model.store.Store;
import com.sopromadze.blogapi.model.Category;

@Data
public class ProductResponse {
	
	private String name;
	private Long quantity;
	private String store;
	private String category;

}
