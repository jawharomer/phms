<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="currentDate" value="${now}" pattern="yyyy-MM-dd" />

<section id="admin-body">
	<section id="section-right" class="card">
		<ul class="list-group">
			<sec:authorize access="hasAnyRole('ADMIN', 'SALE')">
				<li class="list-group-item"><a
					href="<c:url value="/customerOrders/add"/>">Sale Point</a></li>

				<li class="list-group-item"><a
					href="<c:url value="/customerOrders"/>?from=${currentDate}&to=${currentDate}">
						Customer Orders</a></li>
			</sec:authorize>
			<sec:authorize access="hasAnyRole('ADMIN')">
				<li class="list-group-item"><a
					href="<c:url value="/adminStock"/>">Stock</a></li>
				<li class="list-group-item"><a href="<c:url value="/doctors"/>">Doctors</a></li>
				<li class="list-group-item"><a
					href="<c:url value="/productStepUps/search/orderDate"/>?from=${currentDate}&to=${currentDate}">
						Stock By Order</a></li>


				<li class="list-group-item"><a
					href="<c:url value="/productStepUps/search/expirationDate"/>?from=${currentDate}&to=${currentDate}">
						Stock By Expiration</a></li>









				<li class="list-group-item"><a href="<c:url value="/vendors"/>">
						Vendors</a></li>



				<li class="list-group-item"><a
					href="<c:url value="/productCategories"/>"> Product Categories</a></li>
			</sec:authorize>
			
		</ul>
	</section>

	<section id="main-content">

		<tiles:insertAttribute name="adminBody" />

	</section>

</section>