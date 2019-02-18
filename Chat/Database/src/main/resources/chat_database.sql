DROP DATABASE IF EXISTS `chat_database`;
CREATE DATABASE  IF NOT EXISTS `chat_database` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `chat_database`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: chat_database
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `group` (
  `groupId` int(15) NOT NULL AUTO_INCREMENT,
  `groupName` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `userPhoneNumber` varchar(17) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  CONSTRAINT `PH_NUM_G_FK` FOREIGN KEY (`userPhoneNumber`) REFERENCES `user` (`phoneNumber`),
  PRIMARY KEY (`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_friend`
--

DROP TABLE IF EXISTS `group_friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `group_friend` (
  `groupId` int(15) NOT NULL,
  `friendPhoneNumber` varchar(17) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`groupId`,`friendPhoneNumber`),
  KEY `PH_NUM_GU_FK` (`friendPhoneNumber`),
  CONSTRAINT `PH_NUM_GU_FK` FOREIGN KEY (`friendPhoneNumber`) REFERENCES `user` (`phoneNumber`),
  KEY `GI_GU_FK` (`groupId`),
  CONSTRAINT `GI_GU_FK` FOREIGN KEY (`groupId`) REFERENCES `group` (`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_friend`
--

LOCK TABLES `group_friend` WRITE;
/*!40000 ALTER TABLE `group_friend` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `phonenumber` varchar(17) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `country` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `changePassword` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'false',
  `statues` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `picture` blob,
  `bio` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `gender` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `birthofdate` date NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`phonenumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('+12345678956','mayada khaled','Albania','1003777776','false','OFFLINE',_binary 'null','hello !','F','3910-12-01','eee@gmail.com'),('+2060006660','mayada khaled','Albania','1003777776','false','OFFLINE',NULL,'hello !','F','3910-12-01','eee@gmail.com'),('+206106660','mayada khaled','Albania','1003777776','false','OFFLINE',NULL,'hello !','F','3910-12-01','eee@gmail.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_friend`
--

DROP TABLE IF EXISTS `user_friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_friend` (
  `userphone` varchar(11) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `friendphone` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `invitationstatues` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`userphone`,`friendphone`),
  CONSTRAINT `PH_NUM_U_FK` FOREIGN KEY (`userphone`) REFERENCES `user` (`phonenumber`),
  CONSTRAINT `PH_NUM_F_FK` FOREIGN KEY (`friendphone`) REFERENCES `user` (`phonenumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_friend`
--

LOCK TABLES `user_friend` WRITE;
/*!40000 ALTER TABLE `user_friend` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'chat_database'
--

--
-- Dumping routines for database 'chat_database'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-17 21:58:32
