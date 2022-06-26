<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach var="p" items="${users}">       
 <div id="${p.user.user}" class="container card bg-light my-3">
	<jsp:include page="ViewUserInfo.jsp">
		 <jsp:param name="target_user" value="${p.user.user}" />
		 <jsp:param name="target_mail" value="${p.user.mail}" />
		 <jsp:param name="target_follow" value="${p.follow}" />
		 <jsp:param name="user" value="${user}" />
	</jsp:include>
 </div>
</c:forEach>
