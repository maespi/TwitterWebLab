<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
 $(document).ready(function(){
	$('#rcolumn').empty();
	$('#lcolumn').empty();
 });
 </script>

<div class="d-flex flex-column justify-content-md-center align-items-center my-4" id="content">
<form class="row gy-1 gx-3 align-items-center rounded border border-secondary mt-4 mb-5 p-md-3" action="EditUser" method="POST">
	<p>      
    <label class="secondary-color"><b> ${tmp.user} </b></label>
    <div class="text-center"><img src="imgs/army-helicopter.png" class="rounded img-fluid"  alt="Avatar"></div>
	<hr>
	<p>       
    <label class="secondary-color"><b> User mail </b></label>
    <input class="form-control" type="text" name="mail" value="${tmp.mail}" required></p>
    <p>      
    <label class="secondary-color"><b> User Password </b></label>
    <input class="form-control" type="text" name="pwd" value="${tmp.pwd}" required></p>
    <p>
    <input type="hidden" name="edit" value="true" />
    <input class="btn btn-primary bckg-primary-color" type="submit" name="sumbit" value="Save"></p>
</form>
</div>

<c:if test = "${error}">
	    <div class="fixed-bottom alert alert-danger d-flex align-items-center justify-content-md-center" role="alert">
	    	<svg class="bi flex-shrink-0 me-2 justify-content-md-center" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
		  	<div> ${error_msg} </div>
		</div>
</c:if>