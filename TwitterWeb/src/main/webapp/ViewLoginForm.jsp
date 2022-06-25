<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="d-flex flex-column justify-content-md-center align-items-center vh-100" id="content">
<form class="row gy-2 gx-3 align-items-center rounded border border-secondary p-md-3" action="LoginController" method="POST">
	<p>      
    <label class="secondary-color"><b> User id </b></label>
    <input class="form-control" type="text" name="user" value="${user.user}" required></p>
    <p>      
    <label class="secondary-color"><b> User Password </b></label>
    <input class="form-control" type="password" name="pwd" value=""></p>
    <p>
    <input class="btn btn-primary bckg-primary-color" type="submit" name="sumbit" value="Submit"></p>
</form>
</div>

<c:if test = "${error}">
	    <div class="fixed-bottom alert alert-danger d-flex align-items-center justify-content-md-center" role="alert">
	    	<svg class="bi flex-shrink-0 me-2 justify-content-md-center" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
		  	<div> ${error_msg} </div>
		</div>
</c:if>