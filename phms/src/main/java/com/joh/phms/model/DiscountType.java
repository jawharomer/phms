package com.joh.phms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "DISCOUNT_TYPES")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class DiscountType {

	public enum type {
		ByUser, ByAdmin, ByDoctor;
		public int getId() {
			return ordinal() + 1;
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_DISCOUNT_TYPE")
	private Integer id;

	@NotBlank(message = "discount type is blank")
	@Column(name = "DISCOUNT_TYPE", unique = true)
	private String discountType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	@Override
	public String toString() {
		return "DiscountType [id=" + id + ", discountType=" + discountType + "]";
	}

}
