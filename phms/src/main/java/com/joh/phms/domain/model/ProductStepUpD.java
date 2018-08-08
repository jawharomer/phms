package com.joh.phms.domain.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class ProductStepUpD {

	private Integer prodcutStepUpId;

	@NotNull(message = "{productStepUpD.productId.null}")
	private Integer productId;

	@NotNull(message = "{productStepUpD.vendor.null}")
	private Integer vendorId;

	@NotNull(message = "{productStepUpD.expirationDate.null}")
	private Date expirationDate;

	@NotNull(message = "{productStepUpD.productionDate.null}")
	private Date productionDate;

	@NotNull(message = "{productStepUpD.quantity.null}")
	private Integer quantity;

	@NotNull(message = "{productStepUpD.paymentAmount.null}")
	private Double paymentAmount;

	private Integer bonusQuantity;
	private String note;

	public Integer getProdcutStepUpId() {
		return prodcutStepUpId;
	}

	public void setProdcutStepUpId(Integer prodcutStepUpId) {
		this.prodcutStepUpId = prodcutStepUpId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getVendorId() {
		return vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	@Override
	public String toString() {
		return "ProductStepUpD [prodcutStepUpId=" + prodcutStepUpId + ", productId=" + productId + ", vendorId="
				+ vendorId + ", expirationDate=" + expirationDate + ", productionDate=" + productionDate + ", quantity="
				+ quantity + ", paymentAmount=" + paymentAmount + ", bonusQuantity=" + bonusQuantity + ", note=" + note
				+ "]";
	}

}
