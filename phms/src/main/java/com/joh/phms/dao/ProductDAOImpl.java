package com.joh.phms.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.joh.phms.domain.model.ProductD;

public class ProductDAOImpl implements ProductDAOExt {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<ProductD> findStock() {
		List<ProductD> productDs = new ArrayList<>();

		Query query = em.createNativeQuery("SELECT P.I_PRODUCT,P.PRODUCT_CODE,P.PRODUCT_NAME,P.UNIT_TYPE,\n"
				+ "IFNULL(SUM(QUANTITY-SOLD_QUANTITY),0) AS STOCK_LEVEL,\n"
				+ "ROUND(SUM(PAYMENT_AMOUNT)/SUM(QUANTITY),3) as COST,\n"
				+ "ROUND(SUM(PAYMENT_AMOUNT)/(SUM(QUANTITY))+(SUM(PAYMENT_AMOUNT)/SUM(QUANTITY))*P.PROFIT,3) as PRICE,SCIENTIFIC_NAME,PC.CATEGORY_NAME AS CATEGORY,COUNTRY\n"
				+ "FROM PRODUCTS P LEFT OUTER JOIN PRODUCT_CATEGORIES PC USING(I_PRODUCT_CATEGORY) \n"
				+ "LEFT OUTER JOIN PRODUCT_STEPUPS PS  USING(I_PRODUCT)\n" + "GROUP BY P.I_PRODUCT\n"
				+ "ORDER BY PRODUCT_CODE");

		List<Object[]> resultList = query.getResultList();

		for (Object row[] : resultList) {
			ProductD productD = new ProductD();

			productD.setProductId((Integer) row[0]);
			productD.setCode((String) row[1]);
			productD.setName((String) row[2]);
			productD.setUnitType((String) row[3]);
			productD.setStockLevel(Integer.parseInt("" + row[4]));
			productD.setCost((Double) row[5]);
			productD.setPrice((Double) row[6]);
			productD.setScientificName((String) row[7]);
			productD.setCategory((String) row[8]);
			productD.setCountry((String) row[9]);

			productDs.add(productD);
		}
		return productDs;
	}

	@Override
	public ProductD findProductByCode(String productCode) {

		Query query = em.createNativeQuery("SELECT P.I_PRODUCT,P.PRODUCT_CODE,P.PRODUCT_NAME,P.UNIT_TYPE,\n"
				+ "IFNULL(SUM(QUANTITY),0) AS STOCK_LEVEL,\n" + "ROUND(SUM(PAYMENT_AMOUNT)/SUM(QUANTITY),3) as COST,\n"
				+ "ROUND(SUM(PAYMENT_AMOUNT)/(SUM(QUANTITY))+(SUM(PAYMENT_AMOUNT)/SUM(QUANTITY))*P.PROFIT,3) as PRICE,SCIENTIFIC_NAME,COUNTRY\n"
				+ "FROM PRODUCTS P\n" + "LEFT OUTER JOIN PRODUCT_STEPUPS PS  USING(I_PRODUCT)\n"
				+ "WHERE PRODUCT_CODE= ?1 \nGROUP BY P.I_PRODUCT");

		query.setParameter(1, productCode);
		System.out.println(query);

		Object[] row = (Object[]) query.getSingleResult();

		ProductD productD = new ProductD();

		productD.setProductId((Integer) row[0]);
		productD.setCode((String) row[1]);
		productD.setName((String) row[2]);
		productD.setUnitType((String) row[3]);
		productD.setStockLevel(Integer.parseInt("" + row[4]));
		productD.setCost((Double) row[5]);
		productD.setPrice((Double) row[6]);
		productD.setScientificName((String) row[7]);
		productD.setCountry((String) row[8]);

		return productD;

	}
}
