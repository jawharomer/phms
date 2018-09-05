package com.joh.phms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.joh.phms.model.ProductStepUp;

public interface ProductStepUpDAO extends CrudRepository<ProductStepUp, Integer> {

	@Query(value = "SELECT PS.* FROM PRODUCTS P INNER JOIN  PRODUCT_STEPUPS PS USING(I_PRODUCT)\n"
			+ "WHERE  QUANTITY-SOLD_QUANTITY>0 \n" + "AND PRODUCT_CODE= ?1 " + "ORDER BY EXPIRATION_DATE\n"
			+ "LIMIT 1;", nativeQuery = true)
	ProductStepUp findProductStepUpForStockDown(String productCode);

	@Modifying
	@Query(value = "UPDATE PRODUCT_STEPUPS SET SOLD_QUANTITY=SOLD_QUANTITY+1 WHERE I_PRODUCT_STEPUP= ?1 ", nativeQuery = true)
	void stockDown(Integer id);

	List<ProductStepUp> findAllByExpirationDateLessThanEqualOrderByExpirationDate(Date to);

}
