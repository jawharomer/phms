package com.joh.phms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "PRODUCT_STEPUPS")
public class ProductStepUp {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_PRODUCT_STEPUP")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "I_PRODUCT", nullable = false)
	private Product product;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRATION_DATE", nullable = false)
	private Date expirationDate;

	@Column(name = "QUANTITY", nullable = false)
	private Integer quantity;

	@Column(name = "PAYMENT_AMOUNT", nullable = false)
	private Double paymentAmount;

	@Column(name = "BONUS_QUANTITY")
	private Integer bonusQuantity;

	@Column(name = "SOLD_QUANTITY", nullable = false)
	private Integer soldQuantity;

	@Column(name = "NOTE")
	private String note;

	@PrePersist
	public void prePersist() {
		if (soldQuantity == null) {
			soldQuantity = 0;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Integer getBonusQuantity() {
		return bonusQuantity;
	}

	public void setBonusQuantity(Integer bonusQuantity) {
		this.bonusQuantity = bonusQuantity;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getSoldQuantity() {
		return soldQuantity;
	}

	public void setSoldQuantity(Integer soldQuantity) {
		this.soldQuantity = soldQuantity;
	}

	@Override
	public String toString() {
		return "ProductStepUp [id=" + id + ", product=" + product + ", expirationDate=" + expirationDate + ", quantity="
				+ quantity + ", paymentAmount=" + paymentAmount + ", bonusQuantity=" + bonusQuantity + ", soldQuantity="
				+ soldQuantity + ", note=" + note + "]";
	}

}
