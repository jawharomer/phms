<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<section id="admin-body">
	<section id="section-right">
		<ul class="cus-navbar">
			<li><a href='<c:url value="/stock" />'>Stock</a></li>
			<li><a href='<c:url value="/customerOrders/add" />'>Add Customer Order</a></li>
		    <li><a href='<c:url value="/doctors" />'>Doctors</a></li>
		
		</ul>
	</section>

	<section id="main-content">

		<tiles:insertAttribute name="adminBody" />

	</section>

</section>