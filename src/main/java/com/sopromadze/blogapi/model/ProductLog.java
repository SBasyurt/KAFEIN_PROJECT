package com.sopromadze.blogapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sopromadze.blogapi.model.store.Store;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@Table(name = "productLogs")
public class ProductLog {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
	private String name;

    @Column(name = "decrease")
	private String decrease;

    @Column(name = "quantity")
	private Long quantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    public ProductLog(String name,Long quantity) {
        this.name = name;
        this.quantity = quantity;
        if(quantity<5)
        this.decrease = "Warning";
    }

    @JsonIgnore
    public Product getProduct() {
        return product;
    }

    @JsonIgnore
    public Store getStore() {
        return store;
    }
    
    @JsonIgnore
    public Category getCategory() {
        return category;
    }
}
