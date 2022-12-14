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


<header style="background: rgb(60,60,60);" class="mdl-layout__header">
    <div class="mdl-layout__header-row">
        <!-- Title -->
        <div class="mdl-navigation__link mdl-layout-title" type="submit" onclick="location.href='/'"/>
        <img id="imgLogo" src="https://www.pngmart.com/files/22/Car-Logo-PNG-Image.png"
             style="height: 70%; filter: invert(100%) sepia(0%) saturate(0%) hue-rotate(253deg) brightness(108%) contrast(92%);"/>
        <div style="position: absolute; left: 5px; top: 10px; font-style: italic; cursor: default">you.getNiceCar()</div>
        <script>
            function imgPourFirefox() {
                let itgo = window.navigator.userAgent;
                if(itgo.includes("Firefox")){
                    document.getElementById("imgLogo").style.width="87px";
                }
            }
        </script>
    </div>
    <!-- Add spacer, to align navigation to the right -->
    <div class="mdl-layout-spacer"></div>
    <!-- Navigation -->
    <nav class="mdl-navigation">
        <c:if test="${utilisateur.login != null}">
            <button type="submit"
                    style="background-color: rgb(50,50,50); color: white; margin-right: 10px; border-radius: 15px;"
                    class="mdl-button" onclick="location.href='/compte'">
                <i style="color: white;" class="mdl-icon-toggle__label material-icons">person</i> ${utilisateur.login}
            </button>
            <button type="submit"
                    style="background-color: rgb(50,50,50); color: white; margin-right: 10px; border-radius: 15px;"
                    class="mdl-button" onclick="location.href='/deconnexion'">
                <i style="color: white;" class="mdl-icon-toggle__label material-icons">logout</i> Deconnexion
            </button>
            <c:if test="${utilisateur.role == 'ADMIN'}">
	            <button id="demo-menu-lower-right" class="mdl-button mdl-js-button mdl-button--icon"><i style="color: white;" class="material-icons">manage_accounts</i></button>
				<ul class="mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect" for="demo-menu-lower-right">
					<li>
						<button style="width: 100% !important;" type="submit" class="mdl-button" onclick="location.href='/produit/admin'">
							Produits
                		</button>
                		<button type="submit" class="mdl-button" onclick="location.href='/admin/users'">
                    		Utilisateurs
                		</button>
                	</li>
				</ul>
            </c:if>
        </c:if>
        <c:if test="${utilisateur.login == null}">
            <button type="submit"
                    style="background-color: rgb(50,50,50); color: white; margin-right: 10px; border-radius: 15px;"
                    class="mdl-button" onclick="location.href='/connexion'">
                <i style="color: white;" class="mdl-icon-toggle__label material-icons">login</i> Connexion
            </button>
            <button type="submit"
                    style="background-color: rgb(50,50,50); color: white; margin-left: 10px; border-radius: 15px;"
                    class="mdl-button" onclick="location.href='/inscription'">
                <i style="color: white;" class="mdl-icon-toggle__label material-icons">person_add</i> Inscription
            </button>
        </c:if>
    </nav>
    </div>
</header>

<div class="mdl-layout__drawer">
    <span class="mdl-layout-title" style="cursor: default">you.getNiceCar()</span>
    <nav class="mdl-navigation">
        <a class="mdl-navigation__link" type="button" onclick="location.href='/'">
            <i class="mdl-icon-toggle__label material-icons">home</i> <span style="cursor: pointer"> Accueil</span></a>
        <a class="mdl-navigation__link" type="button" onclick="location.href='/produit/boutique'">
            <i class="mdl-icon-toggle__label material-icons">shopping_cart</i> <span style="cursor: pointer"> Boutique</span></a>
        <a class="mdl-navigation__link" type="button" onclick="location.href='/panier'">
            <i class="mdl-icon-toggle__label material-icons">shopping_bag</i> <span style="cursor: pointer"> Panier</span></a>
        <a class="mdl-navigation__link" type="button" onclick="location.href='/compte'">
            <i class="mdl-icon-toggle__label material-icons">person</i> <span style="cursor: pointer"> Compte</span></a>
    </nav>
</div>


