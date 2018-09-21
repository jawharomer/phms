<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container">
	<div class="row">
		<c:forEach items="${notificationDs}" var="item" varStatus="loop">
			<div class="col-sm-6" style="margin-bottom: 10px">
				<div class="card bg-danger">

					<c:choose>
						<c:when test="${loop.index==0}">
							<div class="card-header  bg-danger">${item.title}</div>
						</c:when>
						<c:otherwise>
							<div class="card-header  bg-light">${item.title}</div>
						</c:otherwise>
					</c:choose>

					<div class="card-body bg-dark text-light">
						<p class="card-text">
						<h3>${item.etc}</h3>
						${item.message}
						</p>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

</div>