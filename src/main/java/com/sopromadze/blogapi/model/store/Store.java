package com.sopromadze.blogapi.model.store;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sopromadze.blogapi.model.Product;
import com.sopromadze.blogapi.model.ProductLog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "stores", uniqueConstraints = { @UniqueConstraint(columnNames = { "storename" }) })
public class Store {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank
	@Column(name = "name")
	@Size(max = 40)
	private String name;

	@NotBlank
	@Column(name = "adress")
	@Size(max = 100)
	private String adress;

	@NotBlank
	@Column(name = "region")
	@Size(max = 15)
	private String region;

	@NotBlank
	@Column(name = "city")
	@Size(max = 15)
	private String city;

	@JsonIgnore
	@OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Product> products;

	@JsonIgnore
	@OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductLog> productLogs;

	public Store(String name, String adress, String region, String city) {
		this.name = name;
		this.adress = adress;
		this.region = region;
		this.city = city;
	}

	public List<Product> getProducts() {

		return products == null ? null : new ArrayList<>(products);
	}

	public void setProducts(List<Product> products) {

		if (products == null) {
			this.products = null;
		} else {
			this.products = Collections.unmodifiableList(products);
		}
	}

	public List<ProductLog> getProductLogs() {
		return productLogs == null ? null : new ArrayList<>(productLogs);
	}

	public void setProductLogs(List<ProductLog> productLogs) {

		if (productLogs == null) {
			this.productLogs = null;
		} else {
			this.productLogs = Collections.unmodifiableList(productLogs);
		}
	}
}
