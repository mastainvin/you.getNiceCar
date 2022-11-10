<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Paiement</title>
</head>
<body>
<jsp:include page="navbar.jsp" />

<div class="demo-card-wide mdl-card mdl-shadow--2dp mdl-grid center-items">
    <%--@elvariable id="carteBleueDto" type="com.jee.yougetnicecar.dtos.CarteBleueDto"--%>
    <form:form action="/paiement/payer" method="post" modelAttribute="carteBleueDto">
        <div class="mdl-textfield mdl-js-textfield">
            <form:label path="nom" class="mdl-textfield__label" >Nom</form:label> <form:input class="mdl-textfield__input" type="text" path="nom"/>
        </div>
        <div class="mdl-textfield mdl-js-textfield">
            <form:label path="prenom" class="mdl-textfield__label" >Prénom</form:label> <form:input class="mdl-textfield__input" type="text" path="prenom"/>
        </div>
        <div class="mdl-textfield mdl-js-textfield">
            <form:label path="numero" class="mdl-textfield__label" >Numéro</form:label> <form:input class="mdl-textfield__input" type="number" path="numero"/>
        </div>
        <div class="mdl-textfield mdl-js-textfield">
            <form:label path="cryptogramme" class="mdl-textfield__label" >CVC</form:label> <form:input class="mdl-textfield__input" type="number" path="cryptogramme"/>
        </div>
        <div class="mdl-textfield mdl-js-textfield">
            <form:label path="dateExpiration" class="mdl-textfield__label" >Date d'expiration</form:label> <form:input class="mdl-textfield__input" type="month" path="dateExpiration"/>
        </div>
        <input type="submit" value="Payer" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored"/>
    </form:form>
</div>
</body>
</html>
