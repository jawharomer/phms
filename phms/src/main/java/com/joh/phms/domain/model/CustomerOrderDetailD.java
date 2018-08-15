package com.joh.phms.domain.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CustomerOrderDetailD {

	private Integer customerOrderId;
	private Integer productStepUpId;
	private Integer productId;
	private String productCode;
	private String productName;

	@NotNull(message = "{customerOrderDetailD.quantity.null}")
	@Min(value = 1,message="minimum quantity is 1")
	private Integer quantity;

	@NotNull(message = "{customerOrderDetailD.price.null}")
	private Double price;

	public Integer getCustomerOrderId() {
		return customerOrderId;
	}

	public void setCustomerOrderId(Integer customerOrderId) {
		this.customerOrderId = customerOrderId;
	}

	public Integer getProductStepUpId() {
		return productStepUpId;
	}

	public void setProductStepUpId(Integer productStepUpId) {
		this.productStepUpId = productStepUpId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "CustomerOrderDetailD [customerOrderId=" + customerOrderId + ", productStepUpId=" + productStepUpId
				+ ", productId=" + productId + ", productCode=" + productCode + ", productName=" + productName
				+ ", quantity=" + quantity + ", price=" + price + "]";
	}

}
