package com.sopromadze.blogapi.service;

import com.sopromadze.blogapi.model.Product;
import com.sopromadze.blogapi.payload.ApiResponse;
import com.sopromadze.blogapi.payload.PagedResponse;
import com.sopromadze.blogapi.payload.ProductRequest;
import com.sopromadze.blogapi.payload.ProductResponse;

public interface ProductService {

	Product updateProduct(Long id, ProductRequest newProductRequest);

	ApiResponse deleteProduct(Long id);

	ProductResponse addProduct(ProductRequest productRequest);

	Product getProduct(Long id);

	PagedResponse<Product> getProductByFilter(String Category, String name, String region, String City);

}
