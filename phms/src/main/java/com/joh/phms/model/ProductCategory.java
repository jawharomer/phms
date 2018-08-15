package com.joh.phms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.joh.phms.validator.ProductCategoryValidator;

@Entity
@Table(name = "PRODUCT_CATEGORIES")
public class ProductCategory {

	@NotNull(message = "product category id is null")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_PRODUCT_CATEGORY")
	private Integer id;

	@NotBlank(groups = { ProductCategoryValidator.Insert.class,
			ProductCategoryValidator.Update.class }, message = "name is blank")
	@Column(name = "CATEGORY_NAME", unique = true)
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ProductCategory [id=" + id + ", name=" + name + "]";
	}

}
