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
<nav>
    <ul>
        <c:if test="${utilisateur.login != null}">
            <li>${utilisateur.login}</li>
            <li><input type="button"  onclick="location.href='/deconnexion'" value="Se deconnecter" ></li>
        </c:if>
        <c:if test="${utilisateur.login == null}">
            <li><input type="button"  onclick="location.href='/connexion'" value="Se connecter" ></li>
            <li><input type="button"  onclick="location.href='/inscription'" value="S'inscrire" ></li>
        </c:if>
    </ul>
</nav>
