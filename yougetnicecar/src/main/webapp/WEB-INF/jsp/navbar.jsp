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
<!-- Always shows a header, even in smaller screens. -->


<!-- Uses a transparent header that draws on top of the layout's background -->


<header class="mdl-layout__header">
    <div class="mdl-layout__header-row">
        <!-- Title -->
        <span class="mdl-layout-title">you.getNiceCar</span>
        <!-- Add spacer, to align navigation to the right -->
        <div class="mdl-layout-spacer"></div>
        <!-- Navigation -->
        <nav class="mdl-navigation">
            <c:if test="${utilisateur.login != null}">
                <a class="mdl-navigation__link" type="button"  onclick="location.href='/deconnexion'" >Se deconnecter</a>
                <c:if test="${utilisateur.role == 'ADMIN'}">
                    <a class="mdl-navigation__link" type="button"  onclick="location.href='/produit/admin'">Administration</a>
                </c:if>
            </c:if>
            <c:if test="${utilisateur.login == null}">
                <a class="mdl-navigation__link" type="button"  onclick="location.href='/connexion'">Se connecter</a>
                <a class="mdl-navigation__link" type="button"  onclick="location.href='/inscription'">S'inscrire</a>
            </c:if>
        </nav>
    </div>
</header>
<div class="mdl-layout__drawer">
    <span class="mdl-layout-title">you.getNiceCar()</span>
    <nav class="mdl-navigation">
        <c:if test="${utilisateur.login != null}">
            <a class="mdl-navigation__link" type="button"  onclick="location.href='/deconnexion'" >Se deconnecter</a>
            <c:if test="${utilisateur.role == 'ADMIN'}">
                <a class="mdl-navigation__link" type="button"  onclick="location.href='/produit/admin'">Administration</a>
            </c:if>
        </c:if>
        <c:if test="${utilisateur.login == null}">
            <a class="mdl-navigation__link" type="button"  onclick="location.href='/connexion'">Se connecter</a>
            <a class="mdl-navigation__link" type="button"  onclick="location.href='/inscription'">S'inscrire</a>
        </c:if>
    </nav>
</div>


