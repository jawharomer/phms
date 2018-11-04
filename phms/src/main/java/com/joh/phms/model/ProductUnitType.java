package com.joh.phms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.joh.phms.validator.ProductValidation;

@Entity
@Table(name = "PRODUCT_UNIT_TYPES")
public class ProductUnitType {

	@NotNull(groups = { ProductValidation.Insert.class })
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_PRODUCT_UNIT_TYPE")
	private Integer id;

	@Column(name = "UNIT_TYPE_NAME", nullable = false)
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
		return "ProductUnitType [id=" + id + ", name=" + name + "]";
	}

}
