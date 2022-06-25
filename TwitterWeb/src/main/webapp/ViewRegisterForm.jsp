<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form class="row gy-2 gx-3 align-items-center rounded border border-secondary p-md-3" action="RegisterController" method="POST">
	<p>      
    <label class="secondary-color"><b> User id </b></label>
    <input class="form-control" type="text" name="user" value="${user.user}" required minlength="5" ></p>
    <p>      
    <label class="secondary-color"><b> Password </b></label>
    <input class="form-control" type="text" name="pwd" value = "${user.pwd}" required></p>
    <p>
    <label class="secondary-color"><b> Mail address </b></label>
    <input class="form-control" type="email" name="mail" value = "${user.mail}" required></p>
    <p>
    <input class="btn btn-primary bckg-primary-color" type="submit" name="sumbit" value="Submit"></p>
</form>
<p>
<p class="row lign-items-center" style="color: red">${error}</p>