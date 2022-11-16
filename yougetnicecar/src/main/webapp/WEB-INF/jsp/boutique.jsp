<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<html>
<head>
    <title>Boutique</title>
    <base href="${pageContext.request.contextPath}">
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
</head>
<body style="font-family: 'Montserrat', sans-serif; background: rgb(40,40,40);" onload="imgPourFirefox()">

<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <jsp:include page="navbar.jsp"/>
    <main class="mdl-layout__content ">
        <div class="page-content mdl-layout__container">
            <div style="background-color: rgb(80,80,80); margin: 25px auto 25px auto;" class="mdl-card mdl-cell mdl-cell--4-col">
                <div style="background-color: rgb(60,60,60);" class="mdl-card__title mdl-color-text--white">
                    <div style="font-size: xx-large;" class="mdl-card__title-text">Filtrer</div>
                </div>
                <div style="color: white;" class="mdl-card__supporting-text mdl-cell mdl-cell--middle mdl-cell--8-col">
                    <form action="/produit/boutique" method="get" >
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <label for="marque" class="mdl-textfield__label">Marque </label>
                            <select id="marque" name="marque" class="mdl-textfield__input">
                                <option value="all" style="background: rgb(60,60,60)">Toutes</option>
                                <c:forEach items="${marques}" var="marqueIt">
                                    <option value="${marqueIt.nom}" style="background: rgb(60,60,60)" <c:if test="${marqueIt.nom.equals(marque)}"> selected </c:if> >${marqueIt.nom}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <label for="prix_min" class="mdl-textfield__label">Prix minimum </label>
                            <input id="prix_min" type="number" name="prix_min" class="mdl-textfield__input" value="${prix_min}" />
                        </div>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <label for="prix_max" class="mdl-textfield__label">Prix maximum </label>
                            <input id="prix_max" type="number" name="prix_max" class="mdl-textfield__input" value="${prix_max}" />
                        </div>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <label for="motorisation" class="mdl-textfield__label">Motorisation </label>
                            <select id="motorisation" name="motorisation" class="mdl-textfield__input">
                                <option value="all" style="background: rgb(60,60,60)">Toutes</option>
                                <c:forEach items="${motorisations}" var="motorisationIt">
                                    <option value="${motorisationIt}" style="background: rgb(60,60,60)" <c:if test="${motorisationIt.name().equals(motorisation)}"> selected </c:if> >${motorisationIt}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <label for="annee" class="mdl-textfield__label">Année </label>
                            <select id="annee" name="annee" class="mdl-textfield__input">
                                <option value="0" style="background: rgb(60,60,60)">Toutes</option>
                                <c:forEach items="${annees}" var="anneeIt">
                                    <option value="${anneeIt}" style="background: rgb(60,60,60)" <c:if test="${anneeIt.equals(annee)}"> selected </c:if> >${anneeIt}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">Filtrer</button>
                    </form>
                </div>
            </div>
            <div>
                <div class="mdl-grid mdl-grid--no-spacing">
                    <c:forEach var="produit" items="${produits}">
                        <c:if test="${(produit.marque.nom.equals(marque) || marque.equals('all')) && (produit.motorisation.name().equals(motorisation) || motorisation.equals('all')) && (produit.prix >= prix_min) && (produit.prix <= prix_max) && (produit.annee.equals(annee) || annee == 0)}">
                            <div class="mdl-card mdl-cell mdl-cell--middle mdl-cell--6-col"
                                 style="background-image: url(${produit.imagePath}); background-repeat:no-repeat; background-size: cover; height: 50vh">
                                <div class="mdl-card__title mdl-color-text--white" style="background: rgba(0,0,0,0.5)">
                                    <h2 class="mdl-card__title-text">${produit.modele}
                                        (${produit.annee}) ${produit.motorisation}</h2>
                                    <h2 style="position: absolute; right: 20px;"
                                        class="mdl-card__title-text">${produit.marque.nom}</h2>
                                </div>
                                <div class="mdl-card__supporting-text mdl-color-text--white" style="background: rgba(0,0,0,0.5); position: absolute; bottom: 0; width: 97%; height: 7.5px;">
                                    <div style="position: absolute; bottom: 10px; left: 15px; width: 30%; text-align: left">
                                        <c:if test="${produit.stock >= 1}">
                                            <div>En stock (${produit.stock})</div>
                                        </c:if>
                                        <c:if test="${produit.stock < 1}">
                                            <div>Rupture de stock</div>
                                        </c:if>
                                    </div>
                                    <div style="position: absolute; bottom: 0; left: 0; right: 0; margin-left: auto; margin-right: auto; text-align: center;">
                                        <c:if test="${produit.stock >= 1}">
                                            <form:form method="get" action="/panier/ajouter/${produit.id}">
                                                <button style="color: white; position: absolute; top: 50%; left: 50%; transform: translate(-50%, -85%); border-radius: 15px;" name="${produit.modele}" value="${produit.id}"
                                                        class="mdl-button mdl-js-button">AJOUTER AU PANIER
                                                </button>
                                            </form:form>
                                        </c:if>
                                    </div>
                                    <div style="position: absolute; bottom: 10px; right: 15px; width: 25%; text-align: right;">
                                        <span><fmt:setLocale value="fr_FR"/><fmt:formatNumber value="${produit.prix}" type="currency" currencySymbol="€"/></span>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>
    </main>
</div>
</body>
</html>
