<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="total" scope="request" type="java.lang.Integer"/>
<html>
<head>
    <title>Panier</title>
</head>
<body>
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
                <h1>Votre panier est vide</h1>
            </c:if>
            <c:if test="${panier_courant.size() > 0}">

                <table>
                        <c:forEach var="panier" items="${panier_courant}">
                            <tr>
                                <td><c:out value="${panier.key.modele}"/></td>
                                <td><c:out value="${panier.key.marque.nom}"/></td>
                                <td><c:out value="${panier.key.annee}"/></td>
                                <td><c:out value="${panier.key.stock}"/></td>
                                <td><c:out value="${panier.key.motorisation}"/></td>
                                <td><c:out value="${panier.key.prix}"/></td>
                                <td><form:form id="formSoftware" method="get" action="/modifierpanier/${panier.key.id}/">
                                    <select
                                        onchange="toSubmit(this.value,${panier.key.id})">
                                    <option value="0">Supprimer</option>
                                    <c:forEach var="i" begin="1" end="${panier.key.stock}">
                                        <option value="${i}" <c:if
                                                test="${i.equals(panier.value)}"> selected </c:if>>${i}</option>
                                    </c:forEach>
                                </select>
                                </form:form></td>
                            </tr>
                        </c:forEach>
                </table>
                <h1>Total : ${total}</h1>
                <form:form method="get" action="/paiement">
                    <button type="submit" class="mdl-button mdl-button--colored mdl-js-button">PAYER</button>
                </form:form>
            </c:if>


        </div>
    </main>
</div>


</body>
</html>
