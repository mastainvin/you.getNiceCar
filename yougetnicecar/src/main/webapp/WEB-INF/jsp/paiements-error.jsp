<jsp:useBean id="erreur" scope="request" type="java.lang.String"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <title>Paiement</title>
</head>
<body onload="imgPourFirefox()">

<div class="mdl-layout mdl-js-layout">
    <jsp:include page="navbar.jsp"/>
    <div style="margin-top: 50px; margin-right: 40%; margin-left: 40%; background: rgb(80,80,80); padding: 25px; width: 20%;">
        <div style="color: red;">
            ${erreur}
        </div>
        <div style="background: rgb(80,80,80); color: white;" class="demo-card-wide mdl-card mdl-grid center-items mdl-color-text--white">
            <%--@elvariable id="carteBleueDto" type="com.jee.yougetnicecar.dtos.CarteBleueDto"--%>
            <form:form action="/paiement/payer" method="post" modelAttribute="carteBleueDto">
                <div class="mdl-textfield mdl-js-textfield">
                    <form:label path="nom" class="mdl-textfield__label">Nom</form:label> <form:input
                        class="mdl-textfield__input" type="text" path="nom"/>
                </div>
                <div class="mdl-textfield mdl-js-textfield">
                    <form:label path="prenom" class="mdl-textfield__label">Prénom</form:label> <form:input
                        class="mdl-textfield__input" type="text" path="prenom"/>
                </div>
                <div class="mdl-textfield mdl-js-textfield">
                    <form:label path="numero" class="mdl-textfield__label">Numéro</form:label> <form:input
                        class="mdl-textfield__input" type="number" path="numero"/>
                </div>
                <div class="mdl-textfield mdl-js-textfield">
                    <form:label path="cryptogramme" class="mdl-textfield__label">CVC</form:label> <form:input
                        class="mdl-textfield__input" type="number" path="cryptogramme"/>
                </div>
                <div class="mdl-textfield mdl-js-textfield">
                    <form:label path="dateExpiration" class="mdl-textfield__label">Date d'expiration</form:label> <form:input
                        class="mdl-textfield__input" type="month" path="dateExpiration"/>
                </div>
                <div class="mdl-textfield mdl-js-textfield">
                    <span style="float: left;">Total</span><span style="float: right;"><fmt:setLocale value="fr_FR"/><fmt:formatNumber value="${montant}" type="currency" currencySymbol="€"/></span>
                </div>
                <button style="background-color: rgb(50,50,50); color: white; border-radius: 15px;" type="submit"
                        class="mdl-button mdl-js-button">PAYER
                </button>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
