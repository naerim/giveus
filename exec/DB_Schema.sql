-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: giveus
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `admin_no` int NOT NULL AUTO_INCREMENT,
  `email` varchar(30) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `provider` varchar(10) NOT NULL,
  `sns_key` varchar(255) NOT NULL,
  `eth_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`admin_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `funding`
--

DROP TABLE IF EXISTS `funding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funding` (
  `funding_no` int NOT NULL AUTO_INCREMENT,
  `admin_no` int NOT NULL,
  `issue_number` varchar(100) NOT NULL,
  `registration_number` varchar(100) NOT NULL,
  `patient_name` varchar(20) NOT NULL,
  `birth` date NOT NULL,
  `gender` char(1) NOT NULL,
  `disease_name` varchar(30) NOT NULL,
  `disease_code` varchar(100) NOT NULL,
  `diagnosis_date` date NOT NULL,
  `opinion` text NOT NULL,
  `title` varchar(30) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `target_amount` int NOT NULL,
  `created_at` date NOT NULL DEFAULT (curdate()),
  `phone` varchar(11) NOT NULL,
  `reg_id` char(16) NOT NULL,
  PRIMARY KEY (`funding_no`),
  KEY `FK_admin_TO_funding_1` (`admin_no`),
  CONSTRAINT `FK_admin_TO_funding_1` FOREIGN KEY (`admin_no`) REFERENCES `admin` (`admin_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `funding_detail`
--

DROP TABLE IF EXISTS `funding_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funding_detail` (
  `funding_detail_no` int NOT NULL AUTO_INCREMENT,
  `funding_no` int NOT NULL,
  `content` text NOT NULL,
  `thumbnail_url` varchar(255) NOT NULL,
  PRIMARY KEY (`funding_detail_no`),
  KEY `FK_funding_TO_funding_detail_1` (`funding_no`),
  CONSTRAINT `FK_funding_TO_funding_detail_1` FOREIGN KEY (`funding_no`) REFERENCES `funding` (`funding_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `funding_status_history`
--

DROP TABLE IF EXISTS `funding_status_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funding_status_history` (
  `funding_status_history_no` int NOT NULL AUTO_INCREMENT,
  `funding_no` int NOT NULL,
  `status` varchar(5) NOT NULL,
  `created_at` date NOT NULL DEFAULT (curdate()),
  PRIMARY KEY (`funding_status_history_no`,`funding_no`),
  KEY `FK_funding_TO_funding_status_history_1` (`funding_no`),
  CONSTRAINT `FK_funding_TO_funding_status_history_1` FOREIGN KEY (`funding_no`) REFERENCES `funding` (`funding_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_no` int NOT NULL AUTO_INCREMENT,
  `email` varchar(30) NOT NULL,
  `name` varchar(5) DEFAULT NULL,
  `nickname` varchar(10) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT (curdate()),
  `image_url` varchar(255) DEFAULT NULL,
  `provider` varchar(10) NOT NULL,
  `sns_key` varchar(255) NOT NULL,
  PRIMARY KEY (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `member_device`
--

DROP TABLE IF EXISTS `member_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_device` (
  `member_device_no` int NOT NULL AUTO_INCREMENT,
  `member_no` int NOT NULL,
  `device_token` varchar(255) NOT NULL,
  `last_login_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`member_device_no`),
  KEY `FK_member_TO_member_device_1` (`member_no`),
  CONSTRAINT `FK_member_TO_member_device_1` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `member_funding`
--

DROP TABLE IF EXISTS `member_funding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_funding` (
  `member_funding_no` int NOT NULL AUTO_INCREMENT,
  `member_no` int NOT NULL,
  `funding_no` int NOT NULL,
  `payment_no` int DEFAULT NULL,
  `point_no` int DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `amount` int NOT NULL,
  `is_public` tinyint(1) NOT NULL,
  PRIMARY KEY (`member_funding_no`),
  KEY `FK_member_TO_member_funding_1` (`member_no`),
  KEY `FK_funding_TO_member_funding_1` (`funding_no`),
  KEY `FK_payment_TO_member_funding_1` (`payment_no`),
  KEY `FK_point_usage_TO_member_funding_1` (`point_no`),
  CONSTRAINT `FK_funding_TO_member_funding_1` FOREIGN KEY (`funding_no`) REFERENCES `funding` (`funding_no`),
  CONSTRAINT `FK_member_TO_member_funding_1` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`),
  CONSTRAINT `FK_payment_TO_member_funding_1` FOREIGN KEY (`payment_no`) REFERENCES `payment` (`payment_no`),
  CONSTRAINT `FK_point_usage_TO_member_funding_1` FOREIGN KEY (`point_no`) REFERENCES `point_usage` (`point_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `member_setting`
--

DROP TABLE IF EXISTS `member_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_setting` (
  `member_setting_no` int NOT NULL AUTO_INCREMENT,
  `member_no` int NOT NULL,
  `funding_review` tinyint(1) NOT NULL,
  `usage_history` tinyint(1) NOT NULL,
  `regular_funding` tinyint(1) NOT NULL,
  `recommend_funding` tinyint(1) NOT NULL,
  PRIMARY KEY (`member_setting_no`),
  KEY `FK_member_TO_member_setting_1` (`member_no`),
  CONSTRAINT `FK_member_TO_member_setting_1` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `notification_no` int NOT NULL AUTO_INCREMENT,
  `member_no` int NOT NULL,
  `category` varchar(10) NOT NULL,
  `content` varchar(30) NOT NULL,
  `detail` varchar(30) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_read` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`notification_no`),
  KEY `FK_member_TO_notification_1` (`member_no`),
  CONSTRAINT `FK_member_TO_notification_1` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_no` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `method` varchar(20) NOT NULL,
  `amount` int NOT NULL,
  PRIMARY KEY (`payment_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `point_recharge`
--

DROP TABLE IF EXISTS `point_recharge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `point_recharge` (
  `point_no` int NOT NULL AUTO_INCREMENT,
  `member_no` int NOT NULL,
  `amount` int NOT NULL,
  `created_at` datetime NOT NULL,
  `content` varchar(20) NOT NULL,
  `payment_type` varchar(10) NOT NULL,
  PRIMARY KEY (`point_no`),
  KEY `FK_member_TO_point_recharge_1` (`member_no`),
  CONSTRAINT `FK_member_TO_point_recharge_1` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `point_usage`
--

DROP TABLE IF EXISTS `point_usage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `point_usage` (
  `point_no` int NOT NULL AUTO_INCREMENT,
  `member_no` int NOT NULL,
  `amount` int NOT NULL,
  `created_at` datetime NOT NULL DEFAULT (curdate()),
  PRIMARY KEY (`point_no`),
  KEY `FK_member_TO_point_usage_1` (`member_no`),
  CONSTRAINT `FK_member_TO_point_usage_1` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `review_no` int NOT NULL AUTO_INCREMENT,
  `funding_no` int NOT NULL,
  `title` varchar(30) NOT NULL,
  `content` varchar(500) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`review_no`),
  KEY `FK_funding_TO_review_1` (`funding_no`),
  CONSTRAINT `FK_funding_TO_review_1` FOREIGN KEY (`funding_no`) REFERENCES `funding` (`funding_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usage_history`
--

DROP TABLE IF EXISTS `usage_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usage_history` (
  `usage_history_no` int NOT NULL AUTO_INCREMENT,
  `funding_no` int NOT NULL,
  `category` varchar(50) NOT NULL,
  `content` varchar(255) NOT NULL,
  `amount` int NOT NULL,
  `count` int NOT NULL,
  PRIMARY KEY (`usage_history_no`),
  KEY `FK_funding_TO_usage_history_1` (`funding_no`),
  CONSTRAINT `FK_funding_TO_usage_history_1` FOREIGN KEY (`funding_no`) REFERENCES `funding` (`funding_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-02 11:50:59
