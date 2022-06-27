<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="imgs/hacker color.png">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title> Twitter </title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="Assets/css/all.css">
<link href="css/MainStyle.css" rel="stylesheet">
<script type="text/javascript">
$(document).ready(function(){
	$.ajaxSetup({ cache: false }); //Avoids Internet Explorer caching!	
	$(document).on("click",".logout", async function(event) {
		const response = await fetch($(this).attr('id'));
		$('#content').html(await response.text());
		$('#lcolumn').html('');
		$('#rcolumn').html('');
		event.preventDefault();
	});
	$(document).on("click",".menu", function(event) {
		$('#content').load($(this).attr('id'));
		event.preventDefault();
	});
	//Show Personal Timeline
	$(document).on("click",".myTime",function(event){
		var tweet = $(this).parent();
		$.post( "GetOwnTimeline", { own: "true" } , function(event) {
			$("#content").load("ViewOwnTimeline.jsp");	
		});
		event.preventDefault();
	});
	
	/*Explore Users*/
	$(document).on("click",".users", function(event) {
		$('#lcolumn').load('GetUserInfo');
		$('#content').load('GetUsers');
		event.preventDefault();
	});
	
	$(document).on("submit","form", function(event) {
		$('#content').load($(this).attr('action'),$(this).serialize());
		event.preventDefault();
	});
	
	/* Add tweet */
	$(document).on("click","#addTweet",function(event){
		$.post( "AddTweet", { content: $("#tweetContent").text()}, function(event) {
			$("#content").load("GetOwnTimeline");	
		});
		event.preventDefault();
	});
	
	/* Delete tweet */
	$(document).on("click",".delTweet",function(event){
		var tweet = $(this).parent();
		$.post( "DelTweet", { id: tweet.parent().parent().parent().attr('id') } , function(event) {
			$("#content").load("GetOwnTimeline");
		});
		event.preventDefault();
	});
	
	/* Delete user */
	$(document).on("click",".deleteUser",function(event){
		var user = $(this).parent().parent().parent().find('h4').text();
		$.post( "DelUser", { user: user } , function(event) {
			$("#content").load("GetOwnTimeline");//$("#content").load("ViewOwnTimeline.jsp");
		});
		console.log(user);
		event.preventDefault();
	});
	
	/* Follow user */
	$(document).on("click",".followUser",function(event){
		var user = $(this).parent().parent().parent().find('h4').text();
		$.post( "FollowUser", { fuser: user, follow: "true" }, function(event) {
			$("#lcolumn").load("GetUserInfo");
			$('#content').load('GetUsers');
		});
		event.preventDefault();
	});
	/* UnFollow user */
	$(document).on("click",".unFollowUser",function(event) {
		var user = $(this).parent().parent().parent().find('h4').text();
		$.post( "FollowUser", { fuser: user, follow: "false" }, function(event) {
			$("#lcolumn").load("GetUserInfo");
			$('#content').load('GetUsers');
		});
		console.log($(this).parent().parent().parent().find('h4').text())
		event.preventDefault();
	});
});
</script>
</head>
<body>

 	<!-- Begin Navigation -->
 	<div class="w3-bar w3-indigo fixed-top" id="navigation">
    <jsp:include page="${menu}" />
 	</div>
 	<!-- End Navigation -->
	
	<div class="row mx-1" >
 	<!-- Left Column -->
 	<div class="col">
		<div id="lcolumn">
			<p></p>
		</div>
	</div>
	<!-- Begin Main	Content -->
	<div class="v-100 col-8 mt-3" id="content">	
	<jsp:include page="${content}" />
	</div>
	<!-- End Main Content -->
	
	<!-- Right Column -->
	<div class="col">
		<div id="rcolumn">
			<p></p>
		</div>
	</div>
	
	</div>

	<script>
		function stack() {
  			var x = document.getElementById("stack");
  			if (x.className.indexOf("w3-show") == -1) {
    			x.className += " w3-show";
  			} else { 
    		x.className = x.className.replace(" w3-show", "");
  			}
		}
	</script>

  </body>
</html>