package com.joh.phms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

@Entity
@Table(name = "PRODUCTS")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_PRODUCT")
	private int id;

	@NotBlank(message = "product code is null")
	@Column(name = "PRODUCT_CODE", unique = true)
	private String code;

	@NotBlank(message = "product name is null")
	@Column(name = "PRODUCT_NAME")
	private String name;

	@NotBlank(message = "Scientific name is blank")
	@Column(name = "SCIENTIFIC_NAME")
	private String scientificName;

	@NotNull(message = "product profit is null")
	@Max(value = 1)
	@Column(name = "PROFIT", nullable = false)
	private Double profit;

	@Valid
	@NotNull(message = "unit type is null")
	@ManyToOne()
	@JoinColumn(name = "I_PRODUCT_UNIT_TYPE")
	private ProductUnitType productUnitType;

	@Valid()
	@NotNull(message = "category is null")
	@ManyToOne()
	@JoinColumn(name = "I_PRODUCT_CATEGORY", nullable = false)
	private ProductCategory productCategory;

	@NotBlank(message = "Country  is blank")
	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "PACKET_SIZE")
	private Integer packetSize;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public String getScientificName() {
		return scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getPacketSize() {
		return packetSize;
	}

	public void setPacketSize(Integer packetSize) {
		this.packetSize = packetSize;
	}

	public ProductUnitType getProductUnitType() {
		return productUnitType;
	}

	public void setProductUnitType(ProductUnitType productUnitType) {
		this.productUnitType = productUnitType;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", code=" + code + ", name=" + name + ", scientificName=" + scientificName
				+ ", profit=" + profit + ", productUnitType=" + productUnitType + ", productCategory=" + productCategory
				+ ", country=" + country + ", packetSize=" + packetSize + "]";
	}

}
