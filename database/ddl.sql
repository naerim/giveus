DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `admin_no` int NOT NULL AUTO_INCREMENT,
  `email` varchar(30) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT (curdate()),
  `provider` varchar(10) NOT NULL,
  `sns_key` varchar(255) NOT NULL,
  PRIMARY KEY (`admin_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_funding`
--

DROP TABLE IF EXISTS `admin_funding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_funding` (
  `admin_funding_no` int NOT NULL AUTO_INCREMENT,
  `admin_no` int NOT NULL,
  `funding_no` int NOT NULL,
  PRIMARY KEY (`admin_funding_no`),
  KEY `FK_admin_TO_admin-funding_1` (`admin_no`),
  KEY `FK_funding_TO_admin-funding_1` (`funding_no`),
  CONSTRAINT `FK_admin_TO_admin-funding_1` FOREIGN KEY (`admin_no`) REFERENCES `admin` (`admin_no`),
  CONSTRAINT `FK_funding_TO_admin-funding_1` FOREIGN KEY (`funding_no`) REFERENCES `funding` (`funding_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `member_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_device` (
  `member_device_no` int NOT NULL AUTO_INCREMENT,
  `member_no` int NOT NULL,
  `device_token` varchar(255) NOT NULL,
  `last_login_at` datetime NOT NULL,
  PRIMARY KEY (`member_device_no`),
  KEY `FK_member_TO_member_device_1` (`member_no`),
  CONSTRAINT `FK_member_TO_member_device_1` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `file`
--

-- DROP TABLE IF EXISTS `file`;
-- /*!40101 SET @saved_cs_client     = @@character_set_client */;
-- /*!50503 SET character_set_client = utf8mb4 */;
-- CREATE TABLE `file` (
--   `file_no` int NOT NULL AUTO_INCREMENT,
--   `url` varchar(255) NOT NULL,
--   PRIMARY KEY (`file_no`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- /*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `funding`
--

DROP TABLE IF EXISTS `funding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funding` (
  `funding_no` int NOT NULL AUTO_INCREMENT,
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
  PRIMARY KEY (`funding_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `funding_detail`
--

DROP TABLE IF EXISTS `funding_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funding_detail` (
  `funding_detail_no` int NOT NULL AUTO_INCREMENT,
  `funding_no` int DEFAULT NULL,
  `content` text,
  `thumbnail_url` varchar(255) NOT NULL,
  PRIMARY KEY (`funding_detail_no`),
  KEY `FK_funding_TO_funding_detail_1` (`funding_no`),
  CONSTRAINT `FK_funding_TO_funding_detail_1` FOREIGN KEY (`funding_no`) REFERENCES `funding` (`funding_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`funding_status_history_no`,`funding_no`),
  KEY `FK_funding_TO_funding_status_history_1` (`funding_no`),
  CONSTRAINT `FK_funding_TO_funding_status_history_1` FOREIGN KEY (`funding_no`) REFERENCES `funding` (`funding_no`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  KEY `FK_member_TO_member-funding_1` (`member_no`),
  KEY `FK_funding_TO_member-funding_1` (`funding_no`),
  KEY `FK_payment_TO_member-funding_1` (`payment_no`),
  KEY `FK_point_usage_TO_member-funding_1` (`point_no`),
  CONSTRAINT `FK_funding_TO_member-funding_1` FOREIGN KEY (`funding_no`) REFERENCES `funding` (`funding_no`),
  CONSTRAINT `FK_member_TO_member-funding_1` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`),
  CONSTRAINT `FK_payment_TO_member-funding_1` FOREIGN KEY (`payment_no`) REFERENCES `payment` (`payment_no`),
  CONSTRAINT `FK_point_usage_TO_member-funding_1` FOREIGN KEY (`point_no`) REFERENCES `point_usage` (`point_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `created_at` datetime NOT NULL DEFAULT (curdate()),
  `is_read` tinyint(1) NOT NULL DEFAULT FALSE,
  `funding_no` int NOT NULL,
  PRIMARY KEY (`notification_no`),
  KEY `FK_member_TO_notification_1` (`member_no`),
  CONSTRAINT `FK_member_TO_notification_1` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notification_setting`
--

DROP TABLE IF EXISTS `member_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_setting` (
  `member_setting_no` int NOT NULL AUTO_INCREMENT,
  `member_no` int NOT NULL,
  `funding_review` tinyint(1) NOT NULL DEFAULT TRUE,
  `usage_history` tinyint(1) NOT NULL DEFAULT TRUE,
  `regular_payment` tinyint(1) NOT NULL DEFAULT TRUE,
  `recommend_funding` tinyint(1) NOT NULL DEFAULT TRUE,
  PRIMARY KEY (`member_setting_no`),
  CONSTRAINT `FK_member_TO_member_setting_1` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
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
-- Table structure for table `payment_token`
--

DROP TABLE IF EXISTS `payment_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_token` (
  `member_no` int NOT NULL,
  `sid` varchar(255) DEFAULT NULL,
  `billing_key` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`member_no`),
  CONSTRAINT `FK_member_TO_payment_token_1` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `point_ recharge`
--

DROP TABLE IF EXISTS `point_ recharge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `point_ recharge` (
  `point_no` int NOT NULL AUTO_INCREMENT,
  `member_no` int NOT NULL,
  `amount` int NOT NULL,
  `created_at` datetime NOT NULL DEFAULT (curdate()),
  `content` varchar(20) NOT NULL,
  `payment_type` varchar(10) NOT NULL,
  PRIMARY KEY (`point_no`),
  KEY `FK_member_TO_point_ recharge_1` (`member_no`),
  CONSTRAINT `FK_member_TO_point_ recharge_1` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
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
-- Table structure for table `regular_payment`
--

DROP TABLE IF EXISTS `regular_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `regular_payment` (
  `regular_payment_no` int NOT NULL AUTO_INCREMENT,
  `member_no` int NOT NULL,
  `created_at` datetime NOT NULL DEFAULT (curdate()),
  `method` varchar(20) NOT NULL,
  `amount` int NOT NULL,
  PRIMARY KEY (`regular_payment_no`),
  KEY `FK_member_TO_regular_payment_1` (`member_no`),
  CONSTRAINT `FK_member_TO_regular_payment_1` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
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
  `created_at` datetime NOT NULL DEFAULT (curdate()),
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`review_no`),
  KEY `FK_funding_TO_review_1` (`funding_no`),
  CONSTRAINT `FK_funding_TO_review_1` FOREIGN KEY (`funding_no`) REFERENCES `funding` (`funding_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `review_file`
--

-- DROP TABLE IF EXISTS `review_file`;
-- /*!40101 SET @saved_cs_client     = @@character_set_client */;
-- /*!50503 SET character_set_client = utf8mb4 */;
-- CREATE TABLE `review_file` (
--   `review_file_no` int NOT NULL AUTO_INCREMENT,
--   `review_no` int NOT NULL,
--   `file_no` int NOT NULL,
--   PRIMARY KEY (`review_file_no`),
--   KEY `FK_review_TO_review_file_1` (`review_no`),
--   KEY `FK_file_TO_review_file_1` (`file_no`),
--   CONSTRAINT `FK_file_TO_review_file_1` FOREIGN KEY (`file_no`) REFERENCES `file` (`file_no`),
--   CONSTRAINT `FK_review_TO_review_file_1` FOREIGN KEY (`review_no`) REFERENCES `review` (`review_no`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- /*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usage_history`
--

DROP TABLE IF EXISTS `usage_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usage_history` (
  `usage_history_no` int NOT NULL AUTO_INCREMENT,
  `admin_funding_no` int NOT NULL,
  `category` varchar(50) NOT NULL,
  `content` varchar(255) NOT NULL,
  `amount` int NOT NULL,
  `count` int NOT NULL,
  PRIMARY KEY (`usage_history_no`),
  KEY `FK_admin-funding_TO_usage_history_1` (`admin_funding_no`),
  CONSTRAINT `FK_admin-funding_TO_usage_history_1` FOREIGN KEY (`admin_funding_no`) REFERENCES `admin_funding` (`admin_funding_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

