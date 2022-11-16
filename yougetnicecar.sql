-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 16 nov. 2022 à 11:23
-- Version du serveur : 5.7.36
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `yougetnicecar`
--
DROP DATABASE IF EXISTS `yougetnicecar`;
CREATE DATABASE `yougetnicecar`;

-- --------------------------------------------------------

--
-- Structure de la table `carte_bleue`
--

DROP TABLE IF EXISTS `carte_bleue`;
CREATE TABLE IF NOT EXISTS `carte_bleue` (
  `id` bigint(20) NOT NULL,
  `cryptogramme` varchar(255) NOT NULL,
  `date_expiration` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `numero` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `solde` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `carte_bleue`
--

INSERT INTO `carte_bleue` (`id`, `cryptogramme`, `date_expiration`, `nom`, `numero`, `prenom`, `solde`) VALUES
(0, '7', '2023-07', 'TEST', '0000', 'TEST', 1174999);

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(24);

-- --------------------------------------------------------

--
-- Structure de la table `marque`
--

DROP TABLE IF EXISTS `marque`;
CREATE TABLE IF NOT EXISTS `marque` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `marque`
--

INSERT INTO `marque` (`id`, `nom`) VALUES
(3, 'TESLA'),
(4, 'AUDI'),
(5, 'PORSHE'),
(6, 'ALPINE'),
(10, 'PROUT'),
(11, 'MCLAREN'),
(13, 'FERRARI');

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

DROP TABLE IF EXISTS `panier`;
CREATE TABLE IF NOT EXISTS `panier` (
  `id` bigint(20) NOT NULL,
  `date` date DEFAULT NULL,
  `etat_panier` int(11) DEFAULT NULL,
  `utilisateur_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9cdicxkotj5he5ruwy9m03idx` (`utilisateur_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `panier`
--

INSERT INTO `panier` (`id`, `date`, `etat_panier`, `utilisateur_id`) VALUES
(1, NULL, NULL, NULL),
(15, '2022-11-12', 1, 16),
(17, '2022-11-12', 1, 18),
(19, '2022-11-12', 1, 16),
(20, '2022-11-12', 1, 16),
(21, NULL, 0, 16),
(22, NULL, 0, 18);

-- --------------------------------------------------------

--
-- Structure de la table `panier_produits`
--

DROP TABLE IF EXISTS `panier_produits`;
CREATE TABLE IF NOT EXISTS `panier_produits` (
  `panier_id` bigint(20) NOT NULL,
  `produits_id` bigint(20) NOT NULL,
  KEY `FKfjasdb4739uxsen9tgmjspff2` (`produits_id`),
  KEY `FKp3rh9kl2t0mrnysa1x4d0ubh` (`panier_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `panier_produits`
--

INSERT INTO `panier_produits` (`panier_id`, `produits_id`) VALUES
(15, 8),
(15, 8),
(19, 7),
(17, 9),
(20, 7),
(20, 8),
(17, 7),
(21, 12),
(22, 7);

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `id` bigint(20) NOT NULL,
  `annee` int(11) DEFAULT NULL,
  `image_path` varchar(2000) DEFAULT NULL,
  `modele` varchar(255) DEFAULT NULL,
  `motorisation` int(11) DEFAULT NULL,
  `prix` int(11) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `marque_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgrc9olfwgr43vy1uibmcrpfyq` (`marque_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id`, `annee`, `image_path`, `modele`, `motorisation`, `prix`, `stock`, `marque_id`) VALUES
(7, 2022, 'https://tesla-cdn.thron.com/delivery/public/image/tesla/8410774a-2d2c-4867-805d-9a549b9eac30/bvlatuR/std/4096x2561/Model-X-Range-Hero-Desktop-LHD', 'Model X', 3, 125000, 2, 3),
(8, 2022, 'https://cdn.motor1.com/images/mgl/M72zm/s3/audi-rs4-by-abt-lead-image.jpg', 'RS4', 1, 150000, 2, 4),
(9, 1, 'https://d36qbsb0kq4ix7.cloudfront.net/images/top/boule-verte-ok.jpg', 'TEST', 1, 1, 0, 10),
(12, 2022, 'https://www.turbo.fr/sites/default/files/2021-03/swae-mclaren-720s.jpg', '720S', 1, 275000, 1, 11),
(14, 2019, 'https://www.tuningblog.eu/wp-content/uploads/2021/12/Ferrari-SF90-Stradale-Velos-Designwerks-RDB-LA-5-e1639052483460.jpg', 'SF90', 2, 400000, 0, 13);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` bigint(20) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `panier_courant_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm7u1gt4pvl88gpyc02u0m4g0q` (`panier_courant_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `login`, `nom`, `password`, `prenom`, `role`, `panier_courant_id`) VALUES
(2, 'admin', 'admin', 'admin', 'admin', 0, 1),
(16, 'user', 'user', 'user', 'user', 1, 21),
(18, 'user2', 'user2', 'user2', 'user2', 1, 22);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
