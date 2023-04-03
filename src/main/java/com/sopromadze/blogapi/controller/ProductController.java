package com.sopromadze.blogapi.controller;

import com.sopromadze.blogapi.model.Product;
import com.sopromadze.blogapi.payload.ApiResponse;
import com.sopromadze.blogapi.payload.ProductRequest;
import com.sopromadze.blogapi.payload.ProductResponse;
import com.sopromadze.blogapi.security.CurrentUser;
import com.sopromadze.blogapi.security.UserPrincipal;
import com.sopromadze.blogapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sopromadze.blogapi.payload.PagedResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest productRequest,
			@CurrentUser UserPrincipal currentUser) {
				ProductResponse productResponse = productService.addProduct(productRequest);

		return new ResponseEntity< >(productResponse, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable(name = "id") Long id) {
		Product product = productService.getProduct(id);

		return new ResponseEntity< >(product, HttpStatus.OK);
	}

	@GetMapping("/{Category}/{name}/{region}/{City}")
	public ResponseEntity<PagedResponse<Product>> getProductByFilter(
			 @PathVariable(name = "Category") String Category
			,@PathVariable(name = "name") String name
			,@PathVariable(name = "region") String region
			,@PathVariable(name = "City") String City) {
		PagedResponse<Product> response = productService.getProductByFilter(Category, name, region, City);

		return new ResponseEntity< >(response, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable(name = "id") Long id,
			@Valid @RequestBody ProductRequest newProductRequest, @CurrentUser UserPrincipal currentUser) {
		Product product = productService.updateProduct(id, newProductRequest);

		return new ResponseEntity< >(product, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable(name = "id") Long id, @CurrentUser UserPrincipal currentUser) {
		ApiResponse apiResponse = productService.deleteProduct(id);

		return new ResponseEntity< >(apiResponse, HttpStatus.OK);
	}
}
