<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="utilisateur" scope="session" type="com.jee.yougetnicecar.models.Utilisateur"/>
<%--
  Created by IntelliJ IDEA.
  User: cytech
  Date: 02/11/2022
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.blue_grey-light_blue.min.css"/>
<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
<script src="https://storage.googleapis.com/code.getmdl.io/1.0.6/material.min.js"></script>
   <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">	  
<!-- Always shows a header, even in smaller screens. -->


<!-- Uses a transparent header that draws on top of the layout's background -->


<header class="mdl-layout__header">
    <div class="mdl-layout__header-row">
        <!-- Title -->
    	<span class="mdl-navigation__link mdl-layout-title" type="button" onclick="location.href='/'">you.getNiceCar()</span>
   		<!-- Add spacer, to align navigation to the right -->
        <div class="mdl-layout-spacer"></div>
        <!-- Navigation -->
        <nav class="mdl-navigation">
            <c:if test="${utilisateur.login != null}">
            	<button type="submit" style="background-color: #f5f5f5; margin-right: 10px;" class="mdl-button" onclick="location.href='/compte'"> 
				<i class="mdl-icon-toggle__label material-icons">person</i> FLO</button>
                <button type="submit" style="background-color: #f5f5f5; margin-right: 10px;" class="mdl-button" onclick="location.href='/deconnexion'">
                <i class="mdl-icon-toggle__label material-icons">logout</i> Deconnexion</button>
                <c:if test="${utilisateur.role == 'ADMIN'}">
                    <button type="submit" style="background-color: #f5f5f5; margin-right: 10px;" class="mdl-button" onclick="location.href='/produit/admin'">
                <i class="mdl-icon-toggle__label material-icons">manage_accounts</i> Administration</button>
                </c:if>
            </c:if>
            <c:if test="${utilisateur.login == null}">
                <button type="submit" style="background-color: #f5f5f5; margin-right: 10px;" class="mdl-button" onclick="location.href='/connexion'">
                <i class="mdl-icon-toggle__label material-icons">login</i> Connexion</button> 
                <button type="submit" style="background-color: #f5f5f5; margin-left: 10px;" class="mdl-button" onclick="location.href='/inscription'">
                <i class="mdl-icon-toggle__label material-icons">person_add</i> Inscription</button> 
            </c:if>
        </nav>
    </div>
</header>
<div class="mdl-layout__drawer">
    <span class="mdl-layout-title">you.getNiceCar()</span>
    <nav class="mdl-navigation">
        <a class="mdl-navigation__link" type="button" onclick="location.href='/'">
        <i class="mdl-icon-toggle__label material-icons">home</i> Accueil</a>
        <a class="mdl-navigation__link" type="button" onclick="location.href='/boutique'">
        <i class="mdl-icon-toggle__label material-icons">shopping_cart</i> Boutique</a>
        <a class="mdl-navigation__link" type="button" onclick="location.href='/panier'">
        <i class="mdl-icon-toggle__label material-icons">shopping_bag</i> Panier</a>
		<a class="mdl-navigation__link" type="button" onclick="location.href='/compte'"> 
		<i class="mdl-icon-toggle__label material-icons">person</i> Compte</a>
    </nav>
</div>


