<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 <script type="text/javascript">
 $(document).ready(function(){
	$('#navigation').load('ViewMenuLoggedAnonymous.jsp');
	//$('#rcolumn').load('SearchUser');
	//$('#lcolumn').load('');
	$('#iterator').load('GetUserTweets');
 });
 </script>

<div id="iterator" class="mt-5">
</div>
 
<!-- Error Area -->
<c:if test = "${error}">
    <div class="fixed-bottom alert alert-danger d-flex align-items-center justify-content-md-center" role="alert">
    	<svg class="bi flex-shrink-0 me-2 justify-content-md-center" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
	  	<div> ${error_msg} </div>
	</div>
</c:if>
