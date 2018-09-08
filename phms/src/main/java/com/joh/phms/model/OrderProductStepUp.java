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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joh.phms.validator.OrderProductStepUpValidator;

@Entity
@Table(name = "ORDER_PRODUCT_STEPUPS")

public class OrderProductStepUp {

	@NotNull(message = "Id id null", groups = { OrderProductStepUpValidator.Edit.class })
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_ORDER_PRODUCT_STEPUP")
	private Integer id;

	@Valid()
	@NotNull(message = "Vendor is null", groups = { OrderProductStepUpValidator.Insert.class })
	@ManyToOne()
	@JoinColumn(name = "I_VENDOR", nullable = false, updatable = false)
	private Vendor vendor;

	@Column(name = "ORDER_TIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date orderTime;

	@Column(name = "REFERENCE_INVOICE_ID")
	private Integer referecneInvoiceId;

	@Column(name = "TOTAL_PAYMENT_AMOUNT", nullable = false)
	private Double totalPaymentAmount;

	@Column(name = "DISCOUNT")
	private BigDecimal discount;

	@Size(min = 0, groups = { OrderProductStepUpValidator.Insert.class })
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "I_ORDER_PRODUCT_STEPUP")
	private List<ProductStepUp> productStepUps = new ArrayList<>();

	public List<ProductStepUp> getProductStepUps() {
		return productStepUps;
	}

	public void setProductStepUps(List<ProductStepUp> productStepUps) {
		this.productStepUps = productStepUps;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getReferecneInvoiceId() {
		return referecneInvoiceId;
	}

	public void setReferecneInvoiceId(Integer referecneInvoiceId) {
		this.referecneInvoiceId = referecneInvoiceId;
	}

	public Double getTotalPaymentAmount() {
		return totalPaymentAmount;
	}

	public void setTotalPaymentAmount(Double totalPaymentAmount) {
		this.totalPaymentAmount = totalPaymentAmount;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "OrderProductStepUp [id=" + id + ", vendor=" + vendor + ", orderTime=" + orderTime
				+ ", referecneInvoiceId=" + referecneInvoiceId + ", totalPaymentAmount=" + totalPaymentAmount
				+ ", discount=" + discount + ", productStepUps=" + productStepUps + "]";
	}

}
