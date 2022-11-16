<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="utilisateur" scope="request" type="com.jee.yougetnicecar.models.Utilisateur"/>
<jsp:useBean id="commandes" scope="request" type="java.util.List<com.jee.yougetnicecar.dtos.CommandeDto>"/>

<html>

<style>
    .mdl-textfield__input {
        color: white !important;
    }

    input:-webkit-autofill {
        -webkit-box-shadow: 0 0 0px 1000px rgb(80, 80, 80) inset;
        -webkit-text-fill-color: white !important;
    }

    .mdl-textfield__label {
        color: white !important;
    }

    .mdl-textfield__label:after {
        background-color: white !important;
    }
</style>

<head>
    <title>Compte</title>
</head>

<body style="background: rgb(40,40,40);" onload="imgPourFirefox()">

<div style="display: block; overflow: auto;" class="mdl-layout mdl-js-layout mdl-color--grey-100">
    <jsp:include page="navbar.jsp"/>
    <div style="background-color: rgb(80,80,80); margin: 25px auto 25px auto;" class="mdl-card mdl-cell mdl-cell--middle mdl-cell--4-col">
        <div style="background-color: rgb(60,60,60);" class="mdl-card__title mdl-color-text--white">
            <div style="font-size: xx-large;" class="mdl-card__title-text">Mon Compte</div>
        </div>
        <div style="color: white;" class="mdl-card__supporting-text mdl-cell mdl-cell--middle mdl-cell--8-col">
            <form action="/compte/modifier/${utilisateur.id}" method="post">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <label for="login" class="mdl-textfield__label">Nom d'utilisateur </label>
                    <input id="login" type="text" name="login" class="mdl-textfield__input" value="${utilisateur.login}"/>
                </div>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <label for="nom" class="mdl-textfield__label">Nom </label>
                    <input id="nom" type="text" name="nom" class="mdl-textfield__input" value="${utilisateur.nom}" />
                </div>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <label for="prenom" class="mdl-textfield__label">Prénom </label>
                    <input id="prenom" type="text" name="prenom" class="mdl-textfield__input" value="${utilisateur.prenom}" />
                </div>
                <button style="background-color: rgb(50,50,50); color: white; border-radius: 15px;" type="submit"
                        class="mdl-button mdl-js-button">MODIFIER
                </button>
            </form>
        </div>
    </div>
    <hr style="width: 80%; margin-left: 10%; margin-right: 10%;"/>
    <div style="background-color: rgb(80,80,80); margin: 25px auto 25px auto;" class="mdl-card mdl-cell mdl-cell--middle mdl-cell--4-col">
        <div style="background-color: rgb(60,60,60);" class="mdl-card__title mdl-color-text--white">
            <div style="font-size: xx-large;" class="mdl-card__title-text">Anciennes Commandes</div>
        </div>
        <div style="color: white;" class="mdl-card__supporting-text mdl-cell mdl-cell--middle mdl-cell--8-col mdl-color-text--while">
            <c:forEach items="${commandes}" var="commande">
                <div class="mdl-textfield mdl-js-textfield">Commande du ${commande.date}</div>
                <c:forEach items="${commande.produits}" var="ligne">
                    <div class="mdl-textfield mdl-js-textfield">
                        <span>${ligne.key.modele} - ${ligne.key.motorisation}</span><br/>
                        <span style="float: left;"><fmt:setLocale value="fr_FR"/><fmt:formatNumber value="${ligne.key.prix}" type="currency" currencySymbol="€"/> x ${ligne.value}</span><br/>
                        <span style="float: right;">Total <fmt:setLocale value="fr_FR"/><fmt:formatNumber value="${ligne.key.prix * ligne.value}" type="currency" currencySymbol="€"/></span>
                    </div>
                </c:forEach>
                <div class="mdl-textfield mdl-js-textfield">Total : <fmt:setLocale value="fr_FR"/><fmt:formatNumber value="${commande.total}" type="currency" currencySymbol="€"/></div>
                <hr style="color: white; width: 80%; margin-left: 10%; margin-right: 10%;"/>
            </c:forEach>
        </div>
    </div>
</div>

</body>
</html>

