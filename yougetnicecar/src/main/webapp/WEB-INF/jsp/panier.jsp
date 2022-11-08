<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Panier</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.6.1.js" integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>
<script>
    function toSubmit(data,panierKey)
    {
        $("#formSoftware").attr("action", "/modifierpanier/"+panierKey+"/"+data);
        $("#formSoftware").submit();
    }

</script>

    <jsp:include page="navbar.jsp" />
    <table>
        <c:forEach var ="panier" items="${panier_courant}">
            <tr>
                <td><c:out value="${panier.key.modele}"/></td>
                <td><c:out value="${panier.key.marque.nom}"/></td>
                <td><c:out value="${panier.key.annee}"/></td>
                <td><c:out value="${panier.key.stock}"/></td>
                <td><c:out value="${panier.key.motorisation}"/></td>
                <td><c:out value="${panier.key.prix}"/></td>
                <td><form:form id="formSoftware" method="get" action="/modifierpanier/${panier.key.id}/"><select onchange="toSubmit(this.value,${panier.key.id})">
                    <option value="0">Supprimer</option>
                    <c:forEach var="i" begin="1" end="${panier.key.stock}">
                        <option value="${i}" <c:if test="${i.equals(panier.value)}"> selected </c:if>>${i}</option>
                    </c:forEach>
                </select>
                <!--<button type="submit">Modifier</button>-->
                </form:form></td>
            </tr>
        </c:forEach>
    </table>
<img src="test.png">
</body>
</html>
