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
-- Table structure for table `consignor`
--

DROP TABLE IF EXISTS `consignor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consignor` (
  `idConsignor` int(11) NOT NULL AUTO_INCREMENT,
  `ConsignorFirst` varchar(45) NOT NULL,
  `ConsignorLast` varchar(45) NOT NULL,
  `Phone` varchar(10) NOT NULL,
  `E-Mail` varchar(45) NOT NULL,
  `MoneyOwned` float DEFAULT NULL,
  `MoneyPaied` float DEFAULT NULL,
  PRIMARY KEY (`idConsignor`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consignor`
--

LOCK TABLES `consignor` WRITE;
/*!40000 ALTER TABLE `consignor` DISABLE KEYS */;
INSERT INTO `consignor` VALUES (1,'Andre','Degel','6124562589','Andre@email.com',6.8,0),(2,'Bob','Smith','6128521596','Bob@gmail.com',6.4,3.2),(3,'Jane','Johnson','6127532648','J-Johnson@yahoo.com',4.8,10);
/*!40000 ALTER TABLE `consignor` ENABLE KEYS */;
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
