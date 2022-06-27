<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="d-flex flex-column justify-content-md-center align-items-center mt-4" id="content">
<form class="row gy-1 gx-3 align-items-center rounded border border-secondary mt-4 p-md-3" action="GetOwnTimeline" method="POST">
	<p>      
    <label class="secondary-color"><b> User id </b></label>
    <input class="form-control" type="text" name="target" value="" required></p>
    <p>
    <input class="btn btn-primary bckg-primary-color" type="submit" name="sumbit" value="Search"></p>
</form>
</div>
