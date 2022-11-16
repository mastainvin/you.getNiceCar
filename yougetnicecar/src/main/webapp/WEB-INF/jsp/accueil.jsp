<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<mvc:resources mapping="/resources/**" location="/resources/"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<html>
<style>* {
    scroll-behavior: smooth !important;
}</style>
<head>
    <title>Accueil</title>
</head>

<body   onload="imgPourFirefox()" style="font-family: 'Montserrat'; background: rgb(40,40,40);">
<div class="mdl-layout mdl-js-layout">
    <jsp:include page="/WEB-INF/jsp/navbar.jsp"/>
    <main class="mdl-layout__content">
        <div style="background: url('https://www.hdcarwallpapers.com/walls/alpine_a110_r_2022_4k_8k-HD.jpg');
		width: 100%; height: 100vh; background-size: cover; background-position: center; position: relative;">
        </div>

        <div style="width: 50%; border: solid white 2px; border-radius: 25px; background-color: rgba(0, 0, 0, 0.5); position: absolute; left: 0; right: 0; margin-left: auto; margin-right: auto; top: 30%;  padding: 25px; text-align: center;">
            <div style="color: white; font-size: xxx-large;">Bienvenue sur you.getNiceCar()</div>
            </br></br>
            <div style="color: white; font-size: xx-large;">Achetez la voiture de vos rêves</div>
            </br></br>
            <div>
                <button style="color: white;" class="mdl-button" onclick="location.href='#about_us'">En Savoir Plus Sur
                    Nous<i style="color: white;" class="mdl-icon-toggle__label material-icons">arrow_downward</i>
                </button>
            </div>
        </div>
        <div>
            <div id="about_us" style="text-align: center; overflow-y: scroll;" class="mdl-grid">
                <div style="background: rgb(60,60,60);" class="mdl-card mdl-cell mdl-cell--middle mdl-cell--4-col">
                    <div style="background: rgb(80,80,80);" class="mdl-card__title mdl-color-text--white">
                        <h2 class="mdl-card__title-text">NOS VALEURS</h2>
                    </div>
                    <div class="mdl-card__supporting-text">
                        <div style="width: 35%; padding: 5px; border-radius: 10px; position: absolute; left: 30px; top: 80px; background-color: rgb(50,50,50); color: white; font-size: 30px; text-align: left;">
                            <i style="color: white;" class="mdl-icon-toggle__label material-icons">verified</i> Qualité
                        </div>
                        <div style="width: 35%; padding: 5px; border-radius: 10px; position: absolute; left: 30px; top: 140px; background-color: rgb(50,50,50); color: white; font-size: 30px; text-align: left;">
                            <i style="color: white;" class="mdl-icon-toggle__label material-icons">handshake</i> Respect
                        </div>
                        <div style="width: 35%; padding: 5px; border-radius: 10px; position: absolute; right: 30px; top: 80px; background-color: rgb(50,50,50); color: white; font-size: 30px; text-align: left;">
                            <i style="color: white;" class="mdl-icon-toggle__label material-icons">hearing</i> Ecoute
                        </div>
                        <div style="width: 35%; padding: 5px; border-radius: 10px; position: absolute; right: 30px; top: 140px; background-color: rgb(50,50,50); color: white; font-size: 30px; text-align: left;">
                            <i style="color: white;" class="mdl-icon-toggle__label material-icons">security</i> Sécurité
                        </div>
                    </div>
                </div>
                <div style="background: rgb(60,60,60);" class="mdl-card mdl-cell mdl-cell--middle mdl-cell--4-col">
                    <div style="background: rgb(80,80,80);" class="mdl-card__title mdl-color-text--white">
                        <h2 class="mdl-card__title-text">A PROPOS DE NOUS</h2>
                    </div>
                    <div style="text-align: justify; color: white;" class="mdl-card__supporting-text">
                        <div style="font-size: 16px;">
                            Il y a 3 semaines, nous avons décidé de nous lancer dans la création d'un site de vente de
                            voitures en ligne.
                            Aujourd'hui you.getNiceCar() est le site n°1 de vente de voitures en ligne. Nous comptons
                            déjà zéro
                            client et zéro ventes.
                        </div>
                    </div>
                </div>
                <div style="background: rgb(60,60,60);" class="mdl-card mdl-cell mdl-cell--middle mdl-cell--4-col">
                    <div style="background: rgb(80,80,80);" class="mdl-card__title mdl-color-text--white">
                        <h2 class="mdl-card__title-text">NOTRE MISSION</h2>
                    </div>
                    <div style="text-align: justify; color: white;" class="mdl-card__supporting-text">
                        <div style="font-size: 16px;">
                            Notre mission est d'aider nos clients à trouver la voiture de leur rêve en toute simplicité
                            et en toute sécurité.
                            Nous faisons tout notre possible pour rendre l'expérience you.getNiceCar() la plus simple et
                            la plus satisfaisante possible.
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <footer class="mdl-mega-footer">
        <div class="mdl-mega-footer__left-section">
            <span style="color: white; margin-right: 10px;">NOUS CONTACTER :</span>
            <button class="mdl-button">
                <span style="color: white; margin-left: 10px; margin-right: 10px;" class="fa fa-twitter"> Twitter</span>
            </button>
            <button class="mdl-button">
                <span style="color: white; margin-left: 10px; margin-right: 10px;"
                      class="fa fa-instagram"> Instagram</span></button>
            <button class="mdl-button">
                <span style="color: white; margin-left: 10px; margin-right: 10px;"
                      class="fa fa-facebook"> Facebook</span></button>
        </div>
        <div class="mdl-mega-footer__right-section">
            <span style="color: white;" class="mdl-layout-title">you.getNiceCar()</span>
        </div>
    </footer>
</div>

</body>
</html>
