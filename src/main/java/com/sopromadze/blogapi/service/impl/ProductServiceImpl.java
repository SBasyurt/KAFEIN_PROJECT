package com.sopromadze.blogapi.service.impl;

import com.sopromadze.blogapi.model.Category;
import com.sopromadze.blogapi.model.Product;
import com.sopromadze.blogapi.model.ProductLog;
import com.sopromadze.blogapi.payload.ApiResponse;
import com.sopromadze.blogapi.payload.ProductRequest;
import com.sopromadze.blogapi.payload.ProductResponse;
import com.sopromadze.blogapi.repository.CategoryRepository;
import com.sopromadze.blogapi.exception.ResourceNotFoundException;
import com.sopromadze.blogapi.exception.UnauthorizedException;
import com.sopromadze.blogapi.repository.ProductRepository;
import com.sopromadze.blogapi.repository.ProductLogRepository;
import com.sopromadze.blogapi.repository.StoreRepository;
import com.sopromadze.blogapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sopromadze.blogapi.model.store.Store;
import com.sopromadze.blogapi.payload.PagedResponse;
import org.springframework.data.domain.Page;


import static com.sopromadze.blogapi.utils.AppConstants.CATEGORY;
import static com.sopromadze.blogapi.utils.AppConstants.ID;
import static com.sopromadze.blogapi.utils.AppConstants.POST;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductLogRepository productLogRepository;

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Product updateProduct(Long id, ProductRequest newProductRequest) {
		Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(POST, ID, id));
		Category category = categoryRepository.findById(newProductRequest.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException(CATEGORY, ID, newProductRequest.getCategoryId()));
		Store store = storeRepository.findById(newProductRequest.getStoreId())
				.orElseThrow(() -> new ResourceNotFoundException(CATEGORY, ID, newProductRequest.getStoreId()));
			product.setCategory(category);
			product.setStore(store);	
			product.setQuantity(newProductRequest.getQuantity());

			ProductLog productLog = new ProductLog(newProductRequest.getName(),newProductRequest.getQuantity());
			productLog.setCategory(category);
			productLog.setStore(store);	
			productLog.setProduct(product);	
			productLogRepository.save(productLog);
			return productRepository.save(product);
	}

	@Override
	public ApiResponse deleteProduct(Long id) {
		Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(POST, ID, id));
		try{
		productRepository.deleteById(id);
		return new ApiResponse(Boolean.TRUE, "You successfully deleted post");
		}catch(Exception e){

		ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "fail");
		
		throw new UnauthorizedException(apiResponse);
		}
	}

	@Override
	public ProductResponse addProduct(ProductRequest productRequest) {
		Product product = new Product();
		ProductResponse productResponse = new ProductResponse();
		Category category = categoryRepository.findById(productRequest.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException(CATEGORY, ID, productRequest.getCategoryId()));
		Store store = storeRepository.findById(productRequest.getStoreId())
				.orElseThrow(() -> new ResourceNotFoundException(CATEGORY, ID, productRequest.getStoreId()));
			product.setCategory(category);
			product.setStore(store);	
			product.setQuantity(productRequest.getQuantity());
			product.setName(productRequest.getName());

			product = productRepository.save(product);
			productResponse.setName(product.getName());
			productResponse.setStore(product.getStore().getName());
			productResponse.setQuantity(product.getQuantity());
			productResponse.setCategory(product.getCategory().getName());
			return productResponse;
	}

	@Override
	public PagedResponse<Product> getProductByFilter(String Category, String name, String region, String City) {
		Page<Store> list = storeRepository.findByNameAndRegionAndCity(name, region, City );
		PagedResponse<Product> response = new PagedResponse<Product>();
		for (Store i : list){
			for ( Product p : i.getProducts()){
			if(Category.equals(p.getCategory().getName())){
				Product res = new Product();
				res.setName(p.getName());
				res.setQuantity(p.getQuantity());
				response.getContent().add(res);
				}
			}
		}
			return response;
	}


	@Override
	public Product getProduct(Long id) {

		return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(POST, ID, id));
	}

}
