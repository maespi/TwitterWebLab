<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container rounded border border-secondary mt-5">
<c:choose>
    <c:when test="${(empty target && empty param.target_user) || ((not empty target ) && (target.user == user.user)) || ((not empty param.target_user ) && (param.target_user == user.user))}">
        <h4 id="user" class="text-center">My Profile</h4>
        <div class="text-center"><img src="imgs/army-helicopter.png" class="rounded img-fluid"  alt="Avatar"></div>
		<hr>
		<p class=" text-left align-middle"> <i class="fa fa-id-card fa-fw mr-5"></i>${user.user}</p>
		<p class=" text-left align-middle"> <i class="fa fa-id-badge fa-fw mr-5"></i>${user.mail}</p>
    </c:when>
    <c:when test="${not empty param.target_user}">
        <h4 id="user" class="text-center">${param.target_user}</h4>
        <div class="text-center"><img src="imgs/army-helicopter.png" class="rounded img-fluid"  alt="Avatar"></div>
		<hr>
		<p class="text-left align-middle"> <i class="fa fa-id-card fa-fw mr-5"></i>${param.target_user}</p>
		<p class="text-left align-middle"> <i class="fa fa-id-badge fa-fw mr-5"></i>${param.target_mail}</p>
    </c:when>
    <c:otherwise>
        <h4 id="user" class="text-center">${target.user}</h4>
        <div class="text-center"><img src="imgs/army-helicopter.png" class="rounded img-fluid"  alt="Avatar"></div>
		<hr>
		<p class=" text-left align-middle"> <i class="fa fa-id-card fa-fw mr-5"></i>${target.user}</p>
		<p class=" text-left align-middle"> <i class="fa fa-id-badge fa-fw mr-5"></i>${target.mail}</p>
    </c:otherwise>
</c:choose>
  
  <div class="d-grid gap-2 d-md-flex justify-content-md-end">
  <c:if test="${(user.admin || param.user_admin) && ((not empty param.target_user && (param.target_user != param.user)) || (not empty target && (user.user != target.user)))}">
		<button type="button" class="deleteUser btn btn-danger mb-3"><i class="fa fa-trash"></i>&nbsp;DELETE</button> 
  </c:if>
  <c:choose>
    <c:when test="${(empty target && empty param.target_user) || ((not empty target ) && (target.user == user.user)) || ((not empty param.target_user ) && (param.target_user == user.user))}">
        <form action="EditUser" method="POST">
    		<input type="hidden" name="edit" value="false" />
    		<button type="submit" class="btn btn-primary bckg-primary-color mb-3 "><i class="fa-solid fa-user-pen"></i>&nbsp;Edit</button>
		 </form>
    </c:when>
    <c:otherwise>
    	<c:choose>
		    <c:when test="${follow || param.target_follow}">
		        <button type="button" class="unFollowUser btn btn-danger mb-3 "><i class="fa fa-user-minus"></i>&nbsp;Unfollow</button> 
		    </c:when>
		    <c:when test="${param.anon}">
		    	<form action="GetUserTweets" method="POST">
		    		<input type="hidden" name="target" value="${param.target_user}" />
		    		<button type="submit" class="btn btn-danger mb-3 "><i class="fa fa-id-badge"></i>&nbsp;Show Tweets</button> 
		    	</form>
		    </c:when>
		    <c:otherwise>
		        <button type="button" class="followUser btn btn-success mb-3 "><i class="fa-solid fa-user-gear"></i>&nbsp;Follow</button> 
		    </c:otherwise>
		</c:choose>
    </c:otherwise>
</c:choose>
  </div>
 </div>
<br>