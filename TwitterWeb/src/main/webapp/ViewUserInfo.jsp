<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container rounded border border-secondary mt-5">
  <h4>My Profile</h4>
  <p><img src="imgs/army-helicopter.png" class="w3-circle" style="height:106px;width:106px" alt="Avatar"></p>
  <hr>
  <p class="text-capitalize text-left align-middle"> <i class="fa fa-id-card fa-fw mr-5"></i>${user.user}</p>
  <p class="text-capitalize text-left align-middle"> <i class="fa fa-id-badge fa-fw mr-5"></i>${user.mail} </p>
  <button type="button" class="btn btn-primary bckg-primary-color mb-3"><i class="fa fa-user-plus"></i> &nbsp;Edit</button> 
 </div>
<br>