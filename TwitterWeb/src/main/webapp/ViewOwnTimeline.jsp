<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>

 <script type="text/javascript">
 $(document).ready(function(){
	$('#navigation').load('MenuController');
	//$('#rcolumn').load('GetNotFollowedUsers');
	$('#lcolumn').load('GetUserInfo');
	$('#iterator').load('GetUserTweets');
 });
 </script>
 
<div class="container rounded border border-secondary mt-5">
	<h6 class="w3-opacity"> ${user.user}, what are you thinking? </h6>
	<p id="tweetContent" contenteditable="true" class="border p-2"> </p>
	<button id="addTweet" type="button" class="btn btn-primary bckg-primary-color mb-2 "><i class="fa fa-pencil"></i> &nbsp;Post</button> 
</div>
 
<div id="iterator">
</div>
 


