package com.joh.phms.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity(name = "CUSTOMER_ORDER_DETAILS")
public class CustomerOrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_CUSTOMER_ORDER_DETAIL")
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "I_CUSTOMER_ORDER")
	private CustomerOrder customerOrder;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "COD_PRODUCT_STEPUPS", joinColumns = @JoinColumn(name = "I_CUSTOMER_ORDER_DETAIL"), inverseJoinColumns = @JoinColumn(name = "I_PRODUCT_STEPUP"))
	public List<ProductStepUp> productStepUpIds = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "I_PRODUCT", nullable = false)
	private Product product;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "PRODUCT_CODE")
	private String productCode;

	@Column(name = "QUANTITY")
	private Integer quantity;

	@Column(name = "PRICE")
	private Double price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public List<ProductStepUp> getProductStepUpIds() {
		return productStepUpIds;
	}

	public void setProductStepUpIds(List<ProductStepUp> productStepUpIds) {
		this.productStepUpIds = productStepUpIds;
	}

	@Override
	public String toString() {
		return "CustomerOrderDetail [id=" + id + ", productName=" + productName + ", productCode=" + productCode
				+ ", quantity=" + quantity + ", price=" + price + "]";
	}

}
