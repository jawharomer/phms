package com.joh.phms.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.joh.phms.domain.model.DoctorCustomerOrderD;

@Component
public class ReportDAO {
	@PersistenceContext
	private EntityManager em;

	public List<DoctorCustomerOrderD> findDoctorCustomerOrder(int doctorId, Date from, Date to) {

		Query query = em.createNativeQuery("SELECT  CO.I_CUSTOMER_ORDER,CO.CUSTOMER_NAME,ORDER_TIME,TOTAL_PRICE\n"
				+ ",SUM(PRICE*QUANTITY) AS TOTAL_PAYMENT,DISCOUNT_TYPE,(\n" + "CASE\n"
				+ "WHEN I_DISCOUNT_TYPE=3 THEN  SUM(PRICE*QUANTITY)-TOTAL_PRICE\n" + "ELSE SUM(PRICE*QUANTITY)*PROFIT\n"
				+ "END\n" + ") INCOME\n" + "FROM DOCTORS D\n" + "INNER JOIN CUSTOMER_ORDERS CO USING(I_DOCTOR)\n"
				+ "INNER JOIN CUSTOMER_ORDER_DETAILS COD USING(I_CUSTOMER_ORDER)\n"
				+ "LEFT OUTER JOIN DISCOUNT_TYPES USING(I_DISCOUNT_TYPE)\n"
				+ "WHERE I_DOCTOR=:I_DOCTOR AND ORDER_TIME BETWEEN :from AND :to\n" + "GROUP BY I_CUSTOMER_ORDER\n"
				+ "ORDER BY ORDER_TIME;");

		query.setParameter("I_DOCTOR", doctorId);
		query.setParameter("from", from, TemporalType.TIMESTAMP);
		query.setParameter("to", to, TemporalType.TIMESTAMP);
		
		

		List<Object[]> rows = query.getResultList();

		List<DoctorCustomerOrderD> doctorCustomerOrderDs = new ArrayList<>();
		for (Object[] columns : rows) {
			DoctorCustomerOrderD doctorCustomerOrderD = new DoctorCustomerOrderD();

			doctorCustomerOrderD.setCustomerOrderId(Integer.parseInt("" + columns[0]));
			doctorCustomerOrderD.setCustomerName((String) columns[1]);
			doctorCustomerOrderD.setOrderTime((Date) columns[2]);
			doctorCustomerOrderD.setTotalPrice((Double) columns[3]);
			doctorCustomerOrderD.setTotalPayment((Double) columns[4]);
			doctorCustomerOrderD.setDiscountType((String) columns[5]);
			doctorCustomerOrderD.setIncome((Double) columns[6]);

			doctorCustomerOrderDs.add(doctorCustomerOrderD);
		}
		return doctorCustomerOrderDs;

	}

}
