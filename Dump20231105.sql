-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: ppc
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `asset`
--

DROP TABLE IF EXISTS `asset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asset` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  `creation_date` date NOT NULL,
  `depreciation_method` enum('reduced balance','straight method') NOT NULL,
  `useful_life` int NOT NULL,
  `department_id` int DEFAULT NULL,
  `asset_category_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `acq_date` varchar(255) DEFAULT NULL,
  `desription` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `department_idx` (`id`,`department_id`),
  KEY `dept_idx` (`department_id`),
  KEY `asset_cat_idx` (`asset_category_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `asset_cat` FOREIGN KEY (`asset_category_id`) REFERENCES `assetcategory` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `dept` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset`
--

LOCK TABLES `asset` WRITE;
/*!40000 ALTER TABLE `asset` DISABLE KEYS */;
INSERT INTO `asset` VALUES (6,'Desk Dest','some description','2013-11-23','reduced balance',57,2,1,3,NULL,NULL),(7,'Chalkboard','Forward to you','2008-08-20','reduced balance',77,2,1,3,NULL,NULL),(11,'Laptop Bag','hello there','2012-11-20','reduced balance',3,2,1,3,NULL,NULL),(13,'Honda Motorcyclee','hello there5','2012-12-20','reduced balance',55,2,2,3,NULL,NULL);
/*!40000 ALTER TABLE `asset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assetcategory`
--

DROP TABLE IF EXISTS `assetcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assetcategory` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `code` varchar(45) NOT NULL,
  `description` varchar(100) NOT NULL,
  `state` enum('active','inactive') NOT NULL DEFAULT 'active',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assetcategory`
--

LOCK TABLES `assetcategory` WRITE;
/*!40000 ALTER TABLE `assetcategory` DISABLE KEYS */;
INSERT INTO `assetcategory` VALUES (1,'Computer','COO21','COO2','active'),(2,'Laptop','C0009','Lapto','active'),(3,'Carr','Some carr','HoouGG','inactive'),(4,'Printer','POO78','Printing printer','active'),(5,'Microwavee','some descg','M9087','active'),(7,'Chair','78678','The fisrt chair','inactive'),(8,'SomeName','4566','hello','active'),(9,'Router','Router here','Router here1','active'),(10,'Switch1','12341','SWQE1','inactive'),(12,'www','ww','wwww','active'),(13,'2226','222','22222','inactive');
/*!40000 ALTER TABLE `assetcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `inst_id` int DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `hj_idx` (`inst_id`),
  CONSTRAINT `hj` FOREIGN KEY (`inst_id`) REFERENCES `institition` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Transport',1,NULL),(2,'Maintenance',2,NULL),(3,'Human Resource',1,NULL),(4,'Administration',2,NULL),(5,'Marketing',2,'hjhjh');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (24);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `institition`
--

DROP TABLE IF EXISTS `institition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `institition` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `location` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `state` enum('active','inactive') DEFAULT 'active',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institition`
--

LOCK TABLES `institition` WRITE;
/*!40000 ALTER TABLE `institition` DISABLE KEYS */;
INSERT INTO `institition` VALUES (1,'Food Malawi','Lilongwe','food@malawi.mw','P.O.Box 345','01234567','0000-00-00 00:00:00',NULL,'erere','active'),(2,'MUBAS','Blantyre','registrar@mubas.mw','Private Bag 303','01287654',NULL,NULL,'rerer','active'),(3,'wqe','uiui','iuiui','iiuiui','iuiuiu','2023-11-05 02:36:06','2023-11-05 02:36:06','iuiuiui','inactive'),(4,'Malawi Post Service','Lilongwe','postsadmin@malawipostservices.com','Private Bag 3022','011232233','2023-11-05 02:36:26','2023-11-05 07:37:53','Some description here','active'),(5,'Red cros','Lilongwe','redcrossmalawi@redcross.mw','P.O.Box 665657','0999876543','2023-11-05 07:33:38','2023-11-05 07:33:38','A good non govt organisation	','active');
/*!40000 ALTER TABLE `institition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` int NOT NULL,
  `fname` varchar(255) DEFAULT NULL,
  `sname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (0,'daviee','Munthalie'),(1,'daviee','Munthalie'),(2,'daviee','Munthalie'),(3,'daviee','Munthalie'),(4,'daviee','Munthalie'),(5,'daviee','Munthalie'),(6,'daviee','Munthalie'),(8,'daviee','Munthalie'),(9,'daviee','Munthalie'),(11,'daviee','Munthalie'),(14,'daviee','Munthalie'),(15,'daviee','Munthalie'),(16,'daviee','Munthalie'),(17,'daviee','Munthalie'),(18,'daviee','Munthalie'),(19,'daviee','Munthalie'),(20,'daviee','Munthalie'),(22,'daviee','Munthalie'),(23,'daviee','Munthalie');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sname` varchar(45) NOT NULL,
  `fname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(200) NOT NULL,
  `role` enum('sysadmin','instadmin','deptadmin') NOT NULL,
  `dept` int DEFAULT NULL,
  `last_login` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `inst_id` int DEFAULT '1',
  `state` enum('active','inactive') DEFAULT 'active',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `dept_id_idx` (`dept`),
  KEY `dd_idx` (`inst_id`),
  CONSTRAINT `dd` FOREIGN KEY (`inst_id`) REFERENCES `institition` (`id`),
  CONSTRAINT `dept_id` FOREIGN KEY (`dept`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Munthali','Davie','d@gmail.com','$2a$08$aScUSswh3FzMS9n3EfO67eHsqNN26J2ScwEm.yqwLEyb4lJbUJSmK','sysadmin',2,'2023-11-05 14:05:54',1,'active'),(2,'Doh','Joe','j@gmail.com','$2a$08$aScUSswh3FzMS9n3EfO67eHsqNN26J2ScwEm.yqwLEyb4lJbUJSmK','instadmin',2,'2023-11-05 17:05:36',1,'active'),(3,'Jah','Moe','m@gmail.com','$2a$08$aScUSswh3FzMS9n3EfO67eHsqNN26J2ScwEm.yqwLEyb4lJbUJSmK','deptadmin',2,'2023-11-05 16:17:48',1,'active'),(4,'Botomani','Martha','martha@mubas.ac.mw','$2a$08$LTYPvqiqqjZv7t6PHNHGMO0mVnltzqSAlzO9U.xGci8h3Fxv/uiw2','sysadmin',1,'2023-11-05 07:53:08',2,'active'),(5,'Frankolo','Donald','donald@mubas.ac.mw','$2a$08$r8QU576GD/nYBFo8FeAcjeauVX3t.bkqcc/ET53gyP7zqH/0t4PKa','sysadmin',1,'2023-11-05 07:57:42',2,'active'),(6,'Matindi','Jerome','jerome@foodmalawi.mw','$2a$08$QUwo7ANy0p1d7yxwGx1JouH7Zcg5F9AgWOn0oE9cgZ3hCep.Or732','sysadmin',1,'2023-11-05 08:39:07',1,'inactive'),(7,'Dobabab','jamison','dobadoba@gmail.com','$2a$08$KAEkW3Cz7hoI6miMugXfJ.plc809r7WoBz88gXZb6DwCKVx9frjiC','deptadmin',1,'2023-11-05 11:30:05',1,'inactive');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-05 19:15:54
