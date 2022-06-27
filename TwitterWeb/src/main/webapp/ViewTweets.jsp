<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach var="t" items="${tweets}">       
 <div id="${t.id}" class="container card bg-light my-3">
	<div class="card-body d-flex flex-row pb-0" >
		<div class="col-1 d-flex flex-column align-items-center">
			<img src="imgs/ghost.png" alt="Avatar" class="" style="width:60px">
			<h4> ${t.uid}</h4>
		</div>
		<div class="col-10 m-2 border rounded border-1 ">
   			<p class="card-text text-left p-2"> ${t.content} </p>
		</div>
		<div class="col align-self-end">
			<div class="d-grid gap-2 mb-3">
				<c:choose>
			    <c:when test= "${(user.user == t.uid) || user.admin}">
					<button type="button" class="delTweet btn btn-outline-danger"><i class="fa fa-trash"></i></button>
				</c:when>
				 <c:otherwise>
						<button type="button" class="likeTweet btn btn-outline-success"><i class="fa fa-thumbs-up"></i></button>
				</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<p class="m-0 opacity-25 small"> ${t.postDateTime} </p>
 </div>
</c:forEach>
