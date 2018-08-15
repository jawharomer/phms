package com.joh.phms.domain.model;

public class ProductD {

	private Integer productId;
	private String code;
	private String name;
	private String scientificName;
	private String unitType;
	private Integer stockLevel;
	private Double cost;
	private Double profit;
	private Double price;
	private String category;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public Integer getStockLevel() {
		return stockLevel;
	}

	public void setStockLevel(Integer stockLevel) {
		this.stockLevel = stockLevel;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getScientificName() {
		return scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "ProductD [productId=" + productId + ", code=" + code + ", name=" + name + ", scientificName="
				+ scientificName + ", unitType=" + unitType + ", stockLevel=" + stockLevel + ", cost=" + cost
				+ ", profit=" + profit + ", price=" + price + ", category=" + category + "]";
	}

}
