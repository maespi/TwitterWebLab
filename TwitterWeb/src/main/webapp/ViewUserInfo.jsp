<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container rounded border border-secondary mt-5">
<c:choose>
    <c:when test="${empty target}">
        <h4 class="text-center">My Profile</h4>
    </c:when>
    <c:otherwise>
        <h4 class="text-center">${target.user}</h4>
    </c:otherwise>
</c:choose>
  <div class="text-center"><img src="imgs/army-helicopter.png" class="rounded img-fluid"  alt="Avatar"></div>
  <hr>
  <p class="text-capitalize text-left align-middle"> <i class="fa fa-id-card fa-fw mr-5"></i>${user.user}</p>
  <p class="text-capitalize text-left align-middle"> <i class="fa fa-id-badge fa-fw mr-5"></i>${user.mail}</p>
  <div class="d-grid gap-2 d-md-flex justify-content-md-end">
  <button type="button" class="btn btn-primary bckg-primary-color mb-3 "><i class="fa fa-user-plus"></i>&nbsp;Edit</button> 
  </div>
 </div>
<br>