<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 <script type="text/javascript">
 $(document).ready(function(){
	$('#navigation').load('MenuController');
	$('#rcolumn').load('SearchUser');
	$('#lcolumn').load('GetUserInfo');
	$('#iterator').load('GetUserTweets');
 });
 </script>

<c:if test = "${empty target}">
	<div class="container rounded border border-secondary mt-5">
		<h6 class="w3-opacity"> ${user.user}, what are you thinking? </h6>
		<p id="tweetContent" contenteditable="true" class="border p-2"> </p>
		<button id="addTweet" type="button" class="btn btn-primary bckg-primary-color mb-2 "><i class="fa fa-pencil"></i> &nbsp;Post</button> 
	</div>
</c:if>

<div id="iterator" class="mt-5">
</div>
 
<!-- Error Area -->
<c:if test = "${error}">
    <div class="fixed-bottom alert alert-danger d-flex align-items-center justify-content-md-center" role="alert">
    	<svg class="bi flex-shrink-0 me-2 justify-content-md-center" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
	  	<div> ${error_msg} </div>
	</div>
</c:if>
