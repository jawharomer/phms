package com.joh.phms.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.joh.phms.domain.model.DoctorCustomerOrderD;
import com.joh.phms.domain.model.NotificationD;
import com.joh.phms.domain.model.NotificationD.NotificationType;
import com.joh.phms.model.Country;

@Component
public class ReportDAO {
	@PersistenceContext
	private EntityManager em;

	public List<DoctorCustomerOrderD> findDoctorCustomerOrder(int doctorId, Date from, Date to) {

		Query query = em.createNativeQuery(
				"SELECT I_CUSTOMER_ORDER,CUSTOMER_NAME,ORDER_TIME,TOTAL_PRICE,TOTAL_PRICE-TOTAL_PRICE*IFNULL(DISCOUNT_AMOUNT,0) AS TOTAL_PAYMENT,DISCOUNT_AMOUNT,DISCOUNT_TYPE\n"
						+ ",\n" + "(\n" + "CASE \n"
						+ "WHEN I_DISCOUNT_TYPE=3 THEN -TOTAL_PRICE*IFNULL(DISCOUNT_AMOUNT,0) \n"
						+ "ELSE (TOTAL_PRICE-TOTAL_PRICE*IFNULL(DISCOUNT_AMOUNT,0))*PROFIT \n" + "END\n" + ") INCOME\n"
						+ "FROM DOCTORS D\n" + "INNER JOIN \n" + "CUSTOMER_ORDERS C USING(I_DOCTOR)\n"
						+ "LEFT OUTER JOIN DISCOUNT_TYPES DT USING(I_DISCOUNT_TYPE)\n" + "WHERE I_DOCTOR=:I_DOCTOR \n"
						+ "AND ORDER_TIME BETWEEN :from AND :to");

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
			doctorCustomerOrderD.setDiscountAmount((BigDecimal) columns[5]);
			doctorCustomerOrderD.setDiscountType((String) columns[6]);
			doctorCustomerOrderD.setIncome((Double) columns[7]);

			doctorCustomerOrderDs.add(doctorCustomerOrderD);
		}
		return doctorCustomerOrderDs;

	}

	public List<NotificationD> findAdminNotifications() {

		List<NotificationD> notificationDs = new ArrayList<>();

		// Notification-1

		Query query = em.createNativeQuery("SELECT SUM(QUANTITY-SOLD_QUANTITY) AS EXPIRATE\n" + "FROM PRODUCT_STEPUPS\n"
				+ "WHERE EXPIRATION_DATE<=CURDATE()+INTERVAL 90 DAY\n" + "AND QUANTITY-SOLD_QUANTITY>0");

		Object totalExpirationResult = query.getSingleResult();

		int totalExpiration = 0;
		if (totalExpirationResult != null)
			totalExpiration = Integer.parseInt("" + totalExpirationResult);

		//
		NotificationD not1 = new NotificationD();
		not1.setTitle("Product Expiration");
		not1.setEtc("" + totalExpiration);
		not1.setMessage("Number of Product is About to be expired in stock in next 90 days ");

		not1.setNotificationType(NotificationType.DANGER);

		notificationDs.add(not1);

		// Notification-2

		query = em.createNativeQuery(
				"SELECT ROUND(IFNULL(SUM(TOTAL_PRICE),0),3) FROM CUSTOMER_ORDERS WHERE DATE(ORDER_TIME)=CURDATE();");

		Object totalTodayCustomerPriceResult = query.getSingleResult();

		double totalTodayCustomerPrice = 0;

		if (totalTodayCustomerPriceResult != null)
			totalTodayCustomerPrice = Double.parseDouble("" + totalTodayCustomerPriceResult);

		//
		NotificationD not2 = new NotificationD();
		not2.setTitle("Today Total Customer Price");
		not2.setEtc("" + totalTodayCustomerPrice);
		not2.setMessage("Total customer price without discount");

		not2.setNotificationType(NotificationType.INFO);

		notificationDs.add(not2);

		// Notification-3

		query = em.createNativeQuery(
				"SELECT ROUND(SUM(TOTAL_PRICE)-IFNULL(SUM(TOTAL_PRICE*DISCOUNT_AMOUNT),0),3) FROM CUSTOMER_ORDERS WHERE DATE(ORDER_TIME)=CURDATE();");

		Object totalTodayCustomerPriceResultWithDiscount = query.getSingleResult();

		double totalTodayCustomerPriceWithDiscount = 0;

		if (totalTodayCustomerPriceResultWithDiscount != null)
			totalTodayCustomerPriceWithDiscount = Double.parseDouble("" + totalTodayCustomerPriceResultWithDiscount);

		NotificationD not3 = new NotificationD();
		not3.setTitle("Today Total Customer Order Income ");
		not3.setEtc("" + totalTodayCustomerPriceWithDiscount);
		not3.setMessage("Total customer price after make discount");

		not3.setNotificationType(NotificationType.INFO);

		notificationDs.add(not3);

		// Notification-4

		query = em.createNativeQuery(
				"SELECT ROUND(SUM(TOTAL_PRICE*DISCOUNT_AMOUNT),3) FROM CUSTOMER_ORDERS WHERE DATE(ORDER_TIME)=CURDATE();");

		Object totalTodayCustomerDiscountResult = query.getSingleResult();

		double totalTodayCustomerDiscount = 0;

		if (totalTodayCustomerDiscountResult != null)
			totalTodayCustomerDiscount = Double.parseDouble("" + totalTodayCustomerDiscountResult);

		NotificationD not4 = new NotificationD();
		not4.setTitle("Today Total Customer Discount ");
		not4.setEtc("" + totalTodayCustomerDiscount);
		not4.setMessage("Total discount made to customer");

		not4.setNotificationType(NotificationType.INFO);

		notificationDs.add(not4);

		// Notification-5

		query = em.createNativeQuery(
				"SELECT ROUND(IFNULL(SUM(PAYMENT_AMOUNT),0)) FROM PRODUCT_STEPUPS WHERE DATE(STEPUP_TIME)=CURDATE();");

		Object totalProductStepUpPaymentamountResult = query.getSingleResult();

		double totalProductStepUpPaymentamount = 0;

		if (totalProductStepUpPaymentamountResult != null)
			totalProductStepUpPaymentamount = Double.parseDouble("" + totalProductStepUpPaymentamountResult);

		NotificationD not5 = new NotificationD();
		not5.setTitle("Today total Stockup Payment Amount");
		not5.setEtc("" + totalProductStepUpPaymentamount);
		not5.setMessage("The total today order amount payment");

		not5.setNotificationType(NotificationType.INFO);

		notificationDs.add(not5);

		return notificationDs;

	}

	public List<Country> findAllCountry() {

		Query query = em.createNativeQuery("SELECT I_COUNTRY,COUNTRY_CODE,COUNTRY_NAME FROM COUNTRIES;");

		List<Object[]> rows = query.getResultList();

		List<Country> countries = new ArrayList<>();
		for (Object[] columns : rows) {
			Country country = new Country();

			country.setId(Integer.parseInt("" + columns[0]));
			country.setCode((String) columns[1]);
			country.setName((String) columns[2]);

			countries.add(country);
		}
		return countries;
	}

}
