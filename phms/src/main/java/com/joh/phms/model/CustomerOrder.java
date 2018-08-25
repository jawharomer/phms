package com.joh.phms.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "CUSTOMER_ORDERS")
public class CustomerOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_CUSTOMER_ORDER")
	private int id;

	@Column(name = "CUSTOMER_NAME")
	private String customerName;

	@Column(name = "ORDER_TIME",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date orderTime;

	@Column(name = "TOTAL_PRICE")
	private Double totalPrice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "I_DOCTOR")
	private Doctor doctor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "I_DISCOUNT_TYPE")
	private DiscountType discountType;

	@Column(name = "DISCOUNT_AMOUNT")
	@Min(value = 0, message = "minimum discountAmount is 0")
	@Max(value = 1, message = "maximum discountAmount is 1")
	private BigDecimal discountAmount;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "I_CUSTOMER_ORDER")
	private List<CustomerOrderDetail> customerOrderDetails = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public List<CustomerOrderDetail> getCustomerOrderDetails() {
		return customerOrderDetails;
	}

	public void setCustomerOrderDetails(List<CustomerOrderDetail> customerOrderDetails) {
		this.customerOrderDetails = customerOrderDetails;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	@Override
	public String toString() {
		return "CustomerOrder [id=" + id + ", customerName=" + customerName + ", orderTime=" + orderTime
				+ ", totalPrice=" + totalPrice + ", doctor=" + doctor + ", discountType=" + discountType
				+ ", discountAmount=" + discountAmount + ", customerOrderDetails=" + customerOrderDetails + "]";
	}

}
