package com.joh.phms.domain.model;

import java.math.BigDecimal;
import java.util.Date;

public class DoctorCustomerOrderD {

	private int customerOrderId;
	private String customerName;
	private Date orderTime;
	private Double totalPrice;
	private Double totalPayment;
	private String discountType;
	private BigDecimal discountAmount;
	private Double income;

	public int getCustomerOrderId() {
		return customerOrderId;
	}

	public void setCustomerOrderId(int customerOrderId) {
		this.customerOrderId = customerOrderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(Double totalPayment) {
		this.totalPayment = totalPayment;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	@Override
	public String toString() {
		return "DoctorCustomerOrderD [customerOrderId=" + customerOrderId + ", customerName=" + customerName
				+ ", orderTime=" + orderTime + ", totalPrice=" + totalPrice + ", totalPayment=" + totalPayment
				+ ", discountType=" + discountType + ", discountAmount=" + discountAmount + ", income=" + income + "]";
	}

}
