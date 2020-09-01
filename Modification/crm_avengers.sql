-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 01 sep. 2020 à 09:35
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `crm_avengers`
--

-- --------------------------------------------------------

--
-- Structure de la table `appartenir`
--

DROP TABLE IF EXISTS `appartenir`;
CREATE TABLE IF NOT EXISTS `appartenir` (
  `idOrg` int(11) NOT NULL,
  `IdCiv` int(11) NOT NULL,
  PRIMARY KEY (`idOrg`,`IdCiv`),
  KEY `APPARTENIR_Civil1_FK` (`IdCiv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `civil`
--

DROP TABLE IF EXISTS `civil`;
CREATE TABLE IF NOT EXISTS `civil` (
  `IdCiv` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `Login` varchar(255) DEFAULT NULL,
  `civilite` varchar(50) NOT NULL,
  `coordonees` varchar(50) NOT NULL,
  `nationalite` varchar(50) NOT NULL,
  `dateNaissance` date NOT NULL,
  `dateDeces` date DEFAULT NULL,
  `commentaire` varchar(5) DEFAULT NULL,
  `dateAjout` date NOT NULL,
  `dateModification` date DEFAULT NULL,
  `motDePasse` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IdCiv`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `civil`
--

INSERT INTO `civil` (`IdCiv`, `nom`, `prenom`, `Login`, `civilite`, `coordonees`, `nationalite`, `dateNaissance`, `dateDeces`, `commentaire`, `dateAjout`, `dateModification`, `motDePasse`) VALUES
(2, 'Stark', 'Tony', 'Ironman', 'Monsieur', 'France', 'Français', '1997-01-21', NULL, NULL, '2020-06-11', '2020-08-10', 'test'),
(32, 'Fottorino', 'Simone', 'Superman', 'Monsieur', 'France', 'Français', '1997-01-05', NULL, NULL, '2020-08-07', '2020-08-17', 'test'),
(35, 'Fottorino', 'Pierre', NULL, 'test', 'test', 'test', '2020-08-17', NULL, NULL, '2020-08-17', NULL, 'test');

-- --------------------------------------------------------

--
-- Structure de la table `crise`
--

DROP TABLE IF EXISTS `crise`;
CREATE TABLE IF NOT EXISTS `crise` (
  `idCri` int(11) NOT NULL AUTO_INCREMENT,
  `sujet` varchar(50) NOT NULL,
  `IdCiv` int(11) NOT NULL,
  PRIMARY KEY (`idCri`),
  KEY `Crise_Civil0_FK` (`IdCiv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `droits`
--

DROP TABLE IF EXISTS `droits`;
CREATE TABLE IF NOT EXISTS `droits` (
  `IDdroits` int(11) NOT NULL AUTO_INCREMENT,
  `IDcivil` int(11) NOT NULL,
  `droit` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDdroits`),
  KEY `IDcivil` (`IDcivil`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `droits`
--

INSERT INTO `droits` (`IDdroits`, `IDcivil`, `droit`) VALUES
(1, 2, 1),
(8, 32, 3);

-- --------------------------------------------------------

--
-- Structure de la table `incident`
--

DROP TABLE IF EXISTS `incident`;
CREATE TABLE IF NOT EXISTS `incident` (
  `idInc` int(11) NOT NULL AUTO_INCREMENT,
  `personneDeclarant` char(5) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `complementAdresse` varchar(50) NOT NULL,
  `informationSupplementaire` varchar(50) NOT NULL,
  `idOrg` int(11) NOT NULL,
  `idMis` int(11) NOT NULL,
  PRIMARY KEY (`idInc`),
  UNIQUE KEY `Incident_Mission0_AK` (`idMis`),
  KEY `Incident_Organisation0_FK` (`idOrg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `litige`
--

DROP TABLE IF EXISTS `litige`;
CREATE TABLE IF NOT EXISTS `litige` (
  `idLit` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  `mission` varchar(50) NOT NULL,
  `descriptif` varchar(50) NOT NULL,
  `cout` varchar(50) NOT NULL,
  `photo` varchar(50) NOT NULL,
  `idCri` int(11) NOT NULL,
  PRIMARY KEY (`idLit`),
  KEY `Litige_Crise0_FK` (`idCri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `mission`
--

DROP TABLE IF EXISTS `mission`;
CREATE TABLE IF NOT EXISTS `mission` (
  `idMis` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(50) NOT NULL,
  `dateDebut` date NOT NULL,
  `dateFin` date NOT NULL,
  `itineraire` varchar(50) NOT NULL,
  `infoComplementaire` varchar(50) NOT NULL,
  `niveauGravite` int(11) NOT NULL,
  `niveauUrgence` int(11) NOT NULL,
  `idOrg` int(11) NOT NULL,
  `idInc` int(11) NOT NULL,
  `idRap` int(11) NOT NULL,
  PRIMARY KEY (`idMis`),
  UNIQUE KEY `Mission_Incident0_AK` (`idInc`),
  UNIQUE KEY `Mission_Rapport1_AK` (`idRap`),
  KEY `Mission_Organisation0_FK` (`idOrg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `organisation`
--

DROP TABLE IF EXISTS `organisation`;
CREATE TABLE IF NOT EXISTS `organisation` (
  `idOrg` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `siegeSocial` varchar(50) NOT NULL,
  `dirigeant` varchar(50) NOT NULL,
  `membres` int(12) NOT NULL,
  `commentaire` varchar(50) DEFAULT NULL,
  `dateAjout` date NOT NULL,
  PRIMARY KEY (`idOrg`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `organisation`
--

INSERT INTO `organisation` (`idOrg`, `nom`, `siegeSocial`, `dirigeant`, `membres`, `commentaire`, `dateAjout`) VALUES
(1, 'figeac aéro', 'figeac', 'maillard', 1500, 'test', '2020-08-06'),
(5, 'RatierFigeac', 'Figeac', 'Ferrey', 1500, NULL, '2020-08-07');

-- --------------------------------------------------------

--
-- Structure de la table `rapport`
--

DROP TABLE IF EXISTS `rapport`;
CREATE TABLE IF NOT EXISTS `rapport` (
  `idRap` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(50) NOT NULL,
  `succes` varchar(50) NOT NULL,
  `interlocuteurAvanger` varchar(50) NOT NULL,
  `interlocuteurCivil` varchar(50) NOT NULL,
  `degat` varchar(5) NOT NULL,
  `rapportCrise` varchar(50) NOT NULL,
  `idMis` int(11) NOT NULL,
  PRIMARY KEY (`idRap`),
  UNIQUE KEY `Rapport_Mission0_AK` (`idMis`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `satisfaction`
--

DROP TABLE IF EXISTS `satisfaction`;
CREATE TABLE IF NOT EXISTS `satisfaction` (
  `idSat` int(11) NOT NULL AUTO_INCREMENT,
  `courrier` char(5) NOT NULL,
  `type` varchar(50) NOT NULL,
  `smsMail` varchar(50) NOT NULL,
  `presse` varchar(50) NOT NULL,
  `photo` varchar(50) NOT NULL,
  `enqueteTerrain` varchar(50) NOT NULL,
  `sondageLigne` varchar(50) NOT NULL,
  `IdCiv` int(11) NOT NULL,
  PRIMARY KEY (`idSat`),
  KEY `Satisfaction_Civil0_FK` (`IdCiv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `superheros`
--

DROP TABLE IF EXISTS `superheros`;
CREATE TABLE IF NOT EXISTS `superheros` (
  `idSH` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `pouvoir` varchar(50) NOT NULL,
  `pointFaible` varchar(50) NOT NULL,
  `score` int(11) NOT NULL,
  `commentaire` varchar(50) DEFAULT NULL,
  `IdCiv` int(11) NOT NULL,
  PRIMARY KEY (`idSH`),
  UNIQUE KEY `SuperHeros_Civil0_AK` (`IdCiv`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `superheros`
--

INSERT INTO `superheros` (`idSH`, `nom`, `pouvoir`, `pointFaible`, `score`, `commentaire`, `IdCiv`) VALUES
(6, 'Superman', 'test', 'test', 5, NULL, 32);

-- --------------------------------------------------------

--
-- Structure de la table `supervilain`
--

DROP TABLE IF EXISTS `supervilain`;
CREATE TABLE IF NOT EXISTS `supervilain` (
  `idSV` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `pouvoir` varchar(50) NOT NULL,
  `pointFaible` varchar(50) NOT NULL,
  `commentaire` varchar(50) DEFAULT NULL,
  `degresMalveillance` int(11) NOT NULL,
  `IdCiv` int(11) DEFAULT NULL,
  PRIMARY KEY (`idSV`),
  KEY `IdCiv` (`IdCiv`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `supervilain`
--

INSERT INTO `supervilain` (`idSV`, `nom`, `pouvoir`, `pointFaible`, `commentaire`, `degresMalveillance`, `IdCiv`) VALUES
(2, 'Homme sable', 'sable', 'chaleur', NULL, 5, NULL),
(3, 'Le joker', 'Folie', 'Batman', NULL, 4, NULL),
(5, 'Le pingouin', 'test', 'test', NULL, 5, 35);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `appartenir`
--
ALTER TABLE `appartenir`
  ADD CONSTRAINT `APPARTENIR_Civil1_FK` FOREIGN KEY (`IdCiv`) REFERENCES `civil` (`IdCiv`),
  ADD CONSTRAINT `APPARTENIR_Organisation0_FK` FOREIGN KEY (`idOrg`) REFERENCES `organisation` (`idOrg`);

--
-- Contraintes pour la table `crise`
--
ALTER TABLE `crise`
  ADD CONSTRAINT `Crise_Civil0_FK` FOREIGN KEY (`IdCiv`) REFERENCES `civil` (`IdCiv`);

--
-- Contraintes pour la table `droits`
--
ALTER TABLE `droits`
  ADD CONSTRAINT `droits_ibfk_1` FOREIGN KEY (`IDcivil`) REFERENCES `civil` (`IdCiv`);

--
-- Contraintes pour la table `incident`
--
ALTER TABLE `incident`
  ADD CONSTRAINT `Incident_Mission1_FK` FOREIGN KEY (`idMis`) REFERENCES `mission` (`idMis`),
  ADD CONSTRAINT `Incident_Organisation0_FK` FOREIGN KEY (`idOrg`) REFERENCES `organisation` (`idOrg`);

--
-- Contraintes pour la table `litige`
--
ALTER TABLE `litige`
  ADD CONSTRAINT `Litige_Crise0_FK` FOREIGN KEY (`idCri`) REFERENCES `crise` (`idCri`);

--
-- Contraintes pour la table `mission`
--
ALTER TABLE `mission`
  ADD CONSTRAINT `Mission_Incident1_FK` FOREIGN KEY (`idInc`) REFERENCES `incident` (`idInc`),
  ADD CONSTRAINT `Mission_Organisation0_FK` FOREIGN KEY (`idOrg`) REFERENCES `organisation` (`idOrg`),
  ADD CONSTRAINT `Mission_Rapport2_FK` FOREIGN KEY (`idRap`) REFERENCES `rapport` (`idRap`);

--
-- Contraintes pour la table `rapport`
--
ALTER TABLE `rapport`
  ADD CONSTRAINT `Rapport_Mission0_FK` FOREIGN KEY (`idMis`) REFERENCES `mission` (`idMis`);

--
-- Contraintes pour la table `satisfaction`
--
ALTER TABLE `satisfaction`
  ADD CONSTRAINT `Satisfaction_Civil0_FK` FOREIGN KEY (`IdCiv`) REFERENCES `civil` (`IdCiv`);

--
-- Contraintes pour la table `superheros`
--
ALTER TABLE `superheros`
  ADD CONSTRAINT `SuperHeros_Civil0_FK` FOREIGN KEY (`IdCiv`) REFERENCES `civil` (`IdCiv`);

--
-- Contraintes pour la table `supervilain`
--
ALTER TABLE `supervilain`
  ADD CONSTRAINT `supervilain_ibfk_1` FOREIGN KEY (`IdCiv`) REFERENCES `civil` (`IdCiv`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
