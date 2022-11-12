<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">

<html>
<head>
    <title>Panier</title>
</head>
<body style="font-family: 'Montserrat', sans-serif; background: rgb(40,40,40);">

<script src="https://code.jquery.com/jquery-3.6.1.js" integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="
        crossorigin="anonymous"></script>
<script>
    function toSubmit(data, panierKey) {
        $("#formSoftware").attr("action", "/modifierpanier/" + panierKey + "/" + data);
        $("#formSoftware").submit();
    }
</script>

<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <jsp:include page="navbar.jsp"/>
    <main class="mdl-layout__content ">
        <div class="page-content mdl-layout__container">
            <c:if test="${panier_courant.size() == 0}">
                <div style="text-align: center; color: white; font-size: xx-large; margin-top: 50px;">Votre panier est vide</div>
            </c:if>
            <c:if test="${panier_courant.size() > 0}">
                <div>
                    <div class="mdl-grid">
                        <div style="position: absolute; right: 10px; top: 7.5px; background: rgb(40,40,40); color: white; border-radius: 15px;" class="mdl-cell mdl-cell--4-col">
                            <div style="background: rgb(60,60,60); border-radius: 15px; padding: 10px;">
                                <c:forEach var="panier" items="${panier_courant}">
                                    <div style="margin-top: 10px; text-indent: 25px;">${panier.key.marque.nom} - ${panier.key.modele}</div>
                                    <div style="float: right; margin-top: -20px; margin-right: 20px; text-indent: 25px;">Qté : ${panier.value} - <fmt:setLocale value="fr_FR"/><fmt:formatNumber value="${panier.key.prix * panier.value}" type="currency" currencySymbol="€"/></div>
                                </c:forEach>
                                <hr style="color: white; width: 80%; margin-left: 10%; margin-right: 10%;"/>
                                <div style="float: left; text-indent: 25px; font-weight: bold; font-size: large;">Total</div>
                                <div style="float: right; margin-right: 23px; font-weight: bold; font-size: large;"><fmt:setLocale value="fr_FR"/><fmt:formatNumber value="${total}" type="currency" currencySymbol="€"/></div>
                                <div style="margin-top: 50px;">
                                    <form:form method="get" action="/paiement">
                                        <button style="background: white; margin-left: 30%; width: 40%; border-radius: 15px;" type="submit" class="mdl-button mdl-js-button">VALIDER MA COMMANDE</button>
                                    </form:form>
                                </div>
                            </div>
                            <div style="margin-top: 15px; background: rgb(60,60,60); height: 185px; border-radius: 15px; padding: 10px; font-size: large;">
                                    <div><i style="color: white; text-indent: 25px; margin-top: 10px; vertical-align: bottom;" class="material-icons">local_shipping</i> Expédié en moins de 1 semaine</div>
                                    <div><i style="color: white; text-indent: 25px; margin-top: 10px; vertical-align: bottom;" class="material-icons">redeem</i> Frais de port offerts à dès 1€</div>
                                    <div><i style="color: white; text-indent: 25px; margin-top: 10px; vertical-align: bottom;" class="material-icons">lock</i> Paiement en toute sécurité</div>
                                    <hr style="color: white; width: 80%; margin-left: 10%; margin-right: 10%;"/>
                                    <div style="text-align: center;">Une question ? Contactez nous au 01.01.01.01.01</div>
                            </div>
                        </div>

                        <c:forEach var="panier" items="${panier_courant}">
                            <div style="background: rgb(60,60,60); color: white; border-radius: 15px;" class="mdl-cell mdl-cell--7-col">
                                <div class="mdl-cell mdl-cell--4-col"><img style="padding-bottom: 10px; float: left; height: 15vh; width: 40vh; object-fit: cover;" src="${panier.key.imagePath}" alt=""></div>
                                <div style="text-indent: 25px; font-size: large;">${panier.key.marque.nom} - ${panier.key.modele}</div>
                                <div style="text-indent: 25px; font-size: large; font-style: italic; font-size: small;">${panier.key.motorisation}, ${panier.key.annee}</div>
                                <div style="text-indent: 25px; font-size: large; padding-top: 25px;"><fmt:setLocale value="fr_FR"/><fmt:formatNumber value="${panier.key.prix}" type="currency" currencySymbol="€"/></div>
                                <div style="float: right; margin-top: -75px; margin-right: 250px; font-size: large;" class="mdl-selectfield mdl-js-selectfield">
                                    <form:form id="formSoftware" method="get" action="/modifierpanier/${panier.key.id}/">
                                        <select onchange="toSubmit(this.value,${panier.key.id})" style="padding: 5px;" class="mdl-selectfield__select">
                                            <c:forEach var="i" begin="1" end="${panier.key.stock}">
                                                <option value="${i}" <c:if
                                                        test="${i.equals(panier.value)}"> selected </c:if>>${i}</option>
                                            </c:forEach>
                                        </select>
                                    </form:form>
                                    <span style="float: right; margin-top: -42.5px; margin-right: -125px;"><fmt:setLocale value="fr_FR"/><fmt:formatNumber value="${panier.key.prix * panier.value}" type="currency" currencySymbol="€"/></span>
                                </div>
                                <button type="submit" style="float: right; margin-top: -85px; background-color: rgb(50,50,50); color: white; margin-right: 10px; border-radius: 15px;" class="mdl-button mdl-js-button mdl-button--icon" onclick="toSubmit(0,${panier.key.id})">
                                    <i style="color: white;" class="material-icons">delete</i>
                                </button>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </div>
    </main>
</div>


</body>
</html>
