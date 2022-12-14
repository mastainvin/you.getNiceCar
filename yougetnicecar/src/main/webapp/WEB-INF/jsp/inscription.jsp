<%--
  Created by IntelliJ IDEA.
  User: cytech
  Date: 02/11/2022
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Inscription</title>
</head>
<body onload="imgPourFirefox()">

<%--@elvariable id="utilisateurInscriptionDto" type="com.jee.yougetnicecar.dtos.UtilisateurInscriptionDto"--%>
<div class="mdl-layout mdl-js-layout mdl-color--grey-100">
    <jsp:include page="navbar.jsp"/>
    <div style="background-color: rgb(80,80,80);"
         class="mdl-card mdl-shadow--6dp mdl-cell mdl-cell--middle mdl-cell--4-col">
        <div style="background-color: rgb(60,60,60);" class="mdl-card__title mdl-color-text--white">
            <h2 class="mdl-card__title-text">Inscription</h2>
        </div>
        <div class="mdl-card__supporting-text mdl-cell mdl-cell--middle mdl-cell--8-col">
            <form:form action="/inscription" method="post" modelAttribute="utilisateurInscriptionDto">
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <form:label path="username" class="mdl-textfield__label">Nom d'utilisateur </form:label>
                <form:input type="text" path="username" class="mdl-textfield__input"/>
            </div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <form:label path="password" class="mdl-textfield__label">Mot de passe </form:label>
                <form:input type="password" path="password" class="mdl-textfield__input"/>
            </div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <form:label path="repeatPassword" class="mdl-textfield__label">R??petez le mot de passe </form:label>
                <form:input type="password" path="repeatPassword" class="mdl-textfield__input"/>
            </div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <form:label path="nom" class="mdl-textfield__label">Nom </form:label>
                <form:input type="text" path="nom" class="mdl-textfield__input"/>
            </div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <form:label path="prenom" class="mdl-textfield__label">Pr??nom</form:label>
                <form:input type="text" path="prenom" class="mdl-textfield__input"/>
            </div>
            <button style="background-color: rgb(50,50,50); color: white; border-radius: 15px;" type="submit"
                    class="mdl-button mdl-button--colored mdl-js-button">INSCRIPTION
            </button>
        </div>
        </form:form>
    </div>
</div>
</div>
</div>
</body>
</html>
