<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>

<div class="w3-bar w3-indigo  fixed-top">
	<a class="w3-bar-item w3-button" href="MainController"> <i class="fa fa-home" aria-hidden="true"></i> </a>
	<a class="menu w3-bar-item w3-button w3-hide-small" id="AnonymousAccess" href=#> Timeline </a>
	<a class="users_anon w3-bar-item w3-button w3-hide-small" id="Users" href=#> Explore Users </a>
	<a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium" onclick="stack()">&#9776;</a>
</div>

<div id="stack" class="w3-bar-block w3-red w3-hide w3-hide-large w3-hide-medium">
	<a class="myTime w3-bar-item w3-button" id="GetOwnTimeline" href=#> Timeline </a>
	<a class="users_anon w3-bar-item w3-button " id="Users" href=#> Explore Users </a>
</div>
