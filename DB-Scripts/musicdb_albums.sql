CREATE DATABASE  IF NOT EXISTS `musicdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `musicdb`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: musicdb
-- ------------------------------------------------------
-- Server version	5.6.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `albums`
--

DROP TABLE IF EXISTS `albums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `albums` (
  `idAlbums` int(11) NOT NULL AUTO_INCREMENT,
  `Artist` varchar(45) NOT NULL,
  `Song` varchar(45) NOT NULL,
  `Album` varchar(45) NOT NULL,
  `ReceivedDate` date NOT NULL,
  `InBasement` bit(1) NOT NULL DEFAULT b'0',
  `Consignor` int(11) NOT NULL,
  `Price` float NOT NULL,
  PRIMARY KEY (`idAlbums`),
  KEY `Consignor_idx` (`Consignor`),
  CONSTRAINT `Consignor-Album` FOREIGN KEY (`Consignor`) REFERENCES `consignor` (`idConsignor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `albums`
--

LOCK TABLES `albums` WRITE;
/*!40000 ALTER TABLE `albums` DISABLE KEYS */;
INSERT INTO `albums` VALUES (1,'Avicii','Hey Brother','True','2015-05-01','\0',1,8),(2,'Pitbull','Wild Wild Love','Globalization','2015-03-20','',1,1),(3,'Pitbull','International Love','Planet Pit','2015-04-25','',1,8),(5,'Luke Bryan','That\'s My Kind Of Night','Crash My Party','2015-04-10','',2,5),(6,'Luke Bryan','Crash My Party','Crash My Party','2015-04-01','',2,5),(7,'Little Big Town','Pontoon','Tornado','2015-03-12','',2,1),(8,'George Strait','Check Yes or No','Strait out of the box','2015-04-30','',2,5),(9,'Rammstein','Sonne','Mutter','2015-05-01','',3,1),(10,'Rammstein','Links 2 3 4','Mutter','2015-05-01','',3,1),(11,'Metallica','St. Anger','St. Anger','2015-05-01','\0',3,4),(12,'Within Temptation','Memories','TheSilent Force','2015-05-01','\0',3,6),(13,'In Extremo','Vollmond','Sieben','2015-05-12','\0',1,5),(17,'Rammstein','America','America','2015-05-12','\0',1,4),(18,'a','a','a','2015-05-12','\0',1,1);
/*!40000 ALTER TABLE `albums` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-12 21:22:35
