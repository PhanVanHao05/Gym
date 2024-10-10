-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: gym_managementdb
-- ------------------------------------------------------
-- Server version	9.0.0

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
-- Table structure for table `gym_customer_detail`
--

DROP TABLE IF EXISTS `gym_customer_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gym_customer_detail` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `weight` double NOT NULL,
  `height` double NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `gym_user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `gym_customer_detail_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `gym_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gym_customer_detail`
--

LOCK TABLES `gym_customer_detail` WRITE;
/*!40000 ALTER TABLE `gym_customer_detail` DISABLE KEYS */;
INSERT INTO `gym_customer_detail` VALUES (1,70.5,175),(4,70.5,175);
/*!40000 ALTER TABLE `gym_customer_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gym_membership_tiers`
--

DROP TABLE IF EXISTS `gym_membership_tiers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gym_membership_tiers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `benefits` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `price` double NOT NULL,
  `duration` int DEFAULT NULL COMMENT 'Thời hạn tính theo tháng',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gym_membership_tiers`
--

LOCK TABLES `gym_membership_tiers` WRITE;
/*!40000 ALTER TABLE `gym_membership_tiers` DISABLE KEYS */;
INSERT INTO `gym_membership_tiers` VALUES (1,'Premium','Access to all gym facilities, personal training sessions, and spa services',2000000,2),(2,'Platinum','All-access membership, personal trainer sessions, spa services, and priority booking',2500000,4),(3,'Gold','Unlimited access to all gym facilities, group classes, and free nutrition counseling',4000000,6);
/*!40000 ALTER TABLE `gym_membership_tiers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gym_payment_methods`
--

DROP TABLE IF EXISTS `gym_payment_methods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gym_payment_methods` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gym_payment_methods`
--

LOCK TABLES `gym_payment_methods` WRITE;
/*!40000 ALTER TABLE `gym_payment_methods` DISABLE KEYS */;
INSERT INTO `gym_payment_methods` VALUES (1,'VNPAY');
/*!40000 ALTER TABLE `gym_payment_methods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gym_pt_comments`
--

DROP TABLE IF EXISTS `gym_pt_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gym_pt_comments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `schedule_id` int DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `rating` int NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `schedule_id` (`schedule_id`),
  KEY `gym_pt_comments_ibfk_2` (`customer_id`),
  CONSTRAINT `FK1r0xw3tj64eimv433jb8lqej4` FOREIGN KEY (`customer_id`) REFERENCES `gym_user` (`id`),
  CONSTRAINT `gym_pt_comments_ibfk_1` FOREIGN KEY (`schedule_id`) REFERENCES `gym_work_schedule` (`id`),
  CONSTRAINT `gym_pt_comments_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `gym_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gym_pt_comments`
--

LOCK TABLES `gym_pt_comments` WRITE;
/*!40000 ALTER TABLE `gym_pt_comments` DISABLE KEYS */;
INSERT INTO `gym_pt_comments` VALUES (1,6,2,'Buổi tập rất hữu ích, PT nhiệt tình hướng dẫn!',5,NULL),(2,6,2,'Buổi tập rất hữu ích, PT nhiệt tình hướng dẫn!',4,NULL);
/*!40000 ALTER TABLE `gym_pt_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gym_pt_detail`
--

DROP TABLE IF EXISTS `gym_pt_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gym_pt_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `salary` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gym_pt_detail_ibfk_1` (`user_id`),
  CONSTRAINT `gym_pt_detail_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `gym_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gym_pt_detail`
--

LOCK TABLES `gym_pt_detail` WRITE;
/*!40000 ALTER TABLE `gym_pt_detail` DISABLE KEYS */;
INSERT INTO `gym_pt_detail` VALUES (1,1,15000000),(2,4,16000000);
/*!40000 ALTER TABLE `gym_pt_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gym_receipts`
--

DROP TABLE IF EXISTS `gym_receipts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gym_receipts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `membership_tier_id` int DEFAULT NULL,
  `payment_method_id` int DEFAULT NULL,
  `receipt_date` datetime(6) DEFAULT NULL,
  `amount` double NOT NULL,
  `payment_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `membership_tier_id` (`membership_tier_id`),
  KEY `payment_method_id` (`payment_method_id`),
  KEY `gym_receipts_ibfk_1` (`user_id`),
  KEY `gym_receipts_ibfk_4` (`payment_id`),
  CONSTRAINT `gym_receipts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `gym_user` (`id`),
  CONSTRAINT `gym_receipts_ibfk_2` FOREIGN KEY (`membership_tier_id`) REFERENCES `gym_membership_tiers` (`id`),
  CONSTRAINT `gym_receipts_ibfk_3` FOREIGN KEY (`payment_method_id`) REFERENCES `gym_payment_methods` (`id`),
  CONSTRAINT `gym_receipts_ibfk_4` FOREIGN KEY (`payment_id`) REFERENCES `online_payment_result` (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gym_receipts`
--

LOCK TABLES `gym_receipts` WRITE;
/*!40000 ALTER TABLE `gym_receipts` DISABLE KEYS */;
INSERT INTO `gym_receipts` VALUES (1,NULL,NULL,NULL,'2024-10-10 19:00:00.000000',500,NULL),(2,NULL,NULL,NULL,'2024-10-10 19:00:00.000000',4000000,NULL),(3,NULL,NULL,NULL,'2024-10-10 19:00:00.000000',4000000,NULL),(4,NULL,NULL,NULL,'2024-10-10 19:00:00.000000',4000000,NULL),(5,7,3,1,'2024-10-10 19:00:00.000000',4000000,1),(6,7,3,1,'2024-10-10 10:42:32.168000',4000000,1);
/*!40000 ALTER TABLE `gym_receipts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gym_role`
--

DROP TABLE IF EXISTS `gym_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gym_role` (
  `id` int NOT NULL,
  `name` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `authorities` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gym_role`
--

LOCK TABLES `gym_role` WRITE;
/*!40000 ALTER TABLE `gym_role` DISABLE KEYS */;
INSERT INTO `gym_role` VALUES (1,'USER',NULL),(2,'ADMIN',NULL),(3,'PT',NULL);
/*!40000 ALTER TABLE `gym_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gym_user`
--

DROP TABLE IF EXISTS `gym_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gym_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT NULL,
  `user_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `first_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gender` tinyint(1) DEFAULT NULL,
  `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_role_id` (`role_id`),
  CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `gym_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gym_user`
--

LOCK TABLES `gym_user` WRITE;
/*!40000 ALTER TABLE `gym_user` DISABLE KEYS */;
INSERT INTO `gym_user` VALUES (1,3,1,'johndoe1','password123','John','Doe','hungmt2426@gmail.com','1234567890','123 Main Street',1,'https://example.com/avatar.jpg'),(2,1,1,'johndoe','$2a$10$bi3ycxSyL.q/E1zIh00qbO3eke.w3EjssGE/3rdBX38bVSVJTuHiW','Hung','Nguyen Quoc','haophanjdjd@gmail.com','0329183328','Thủ Đức',1,'http://res.cloudinary.com/dvcawiqmq/image/upload/v1728442194/nl0cpgli6ptg6ytchho7.jpg'),(3,1,1,'johndoe','$2a$10$vBZuOVDM6Ka8BkQBWEU4kOclp6hsMg60gijx8NAATTFI9K6i9Lu3.','John','Doe','johndoe2@example.com','1234567890','123 Main Street',1,'https://example.com/avatar.jpg'),(4,3,1,'johndoe','$2a$10$GCWLhC7yJgpPrzTUfOTOVOEnT9z5kXWBt8lBEm.4ZpT/Qh1UWbNjq','John','Doe','johndoe3@example.com','1234567890','123 Main Street',1,'https://example.com/avatar.jpg'),(5,1,1,'johndoe','$2a$10$/KQgn9mZBhH7rEvwhXWnsOnPCEVRwuyUBy8w1nMDDV2ww9EfwcQRi','John','Doe','johndoe4@example.com','1234567890','123 Main Street',1,'https://example.com/avatar.jpg'),(6,1,1,'johndoe','$2a$10$5UkCxt.hHfwcohTQcX9oDOjiacYwXttkdgsxRL1YYkFPe8YQ88ehW','John','Doe','johndoe5@example.com','1234567890','123 Main Street',1,'https://example.com/avatar.jpg'),(7,1,1,'hungpro01','$2a$10$kIYIKu0/CMzS3Gz39SA7LuZpcNOfC0NcQly8sW0KLMzXGdS6cQ35a','John','Doe','johndoe5@example.com','1234567890','123 Main Street',1,'https://example.com/avatar.jpg');
/*!40000 ALTER TABLE `gym_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gym_user_memberships`
--

DROP TABLE IF EXISTS `gym_user_memberships`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gym_user_memberships` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `tier_id` int DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tier_id` (`tier_id`),
  KEY `gym_user_memberships_ibfk_1` (`user_id`),
  CONSTRAINT `gym_user_memberships_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `gym_user` (`id`),
  CONSTRAINT `gym_user_memberships_ibfk_2` FOREIGN KEY (`tier_id`) REFERENCES `gym_membership_tiers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gym_user_memberships`
--

LOCK TABLES `gym_user_memberships` WRITE;
/*!40000 ALTER TABLE `gym_user_memberships` DISABLE KEYS */;
INSERT INTO `gym_user_memberships` VALUES (1,7,3,'2024-10-09 22:28:06.524000','2025-04-09 22:28:06.524000','ACTIVE'),(2,6,2,'2024-10-09 22:33:36.560000','2025-02-09 22:33:36.560000','ACTIVE'),(3,5,1,'2024-10-09 22:34:54.832000','2024-12-09 22:34:54.832000','ACTIVE');
/*!40000 ALTER TABLE `gym_user_memberships` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gym_work_schedule`
--

DROP TABLE IF EXISTS `gym_work_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gym_work_schedule` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pt_id` int DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  `work_day` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pt_user_id` (`pt_id`),
  KEY `fk_customer_user_id` (`customer_id`),
  CONSTRAINT `fk_customer_user_id` FOREIGN KEY (`customer_id`) REFERENCES `gym_user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_pt_user_id` FOREIGN KEY (`pt_id`) REFERENCES `gym_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gym_work_schedule`
--

LOCK TABLES `gym_work_schedule` WRITE;
/*!40000 ALTER TABLE `gym_work_schedule` DISABLE KEYS */;
INSERT INTO `gym_work_schedule` VALUES (6,1,2,'2024-10-10','14:00:00','16:00:00'),(7,4,2,'2024-10-10','14:00:00','16:00:00');
/*!40000 ALTER TABLE `gym_work_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `online_payment_result`
--

DROP TABLE IF EXISTS `online_payment_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `online_payment_result` (
  `payment_code` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `payment_id` bigint NOT NULL AUTO_INCREMENT,
  `bank_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `bank_transaction_no` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `card_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `confirm_at` datetime(6) DEFAULT NULL,
  `transaction_no` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `online_payment_result`
--

LOCK TABLES `online_payment_result` WRITE;
/*!40000 ALTER TABLE `online_payment_result` DISABLE KEYS */;
INSERT INTO `online_payment_result` VALUES ('PAY123456','2024-10-10 19:00:00.000000',1,'VCB','BANK123456','Credit','2024-10-10 19:30:00.000000','TRANS123456'),('YIWM3W6K','2024-10-10 09:58:36.305000',2,'NCB','VNP14608613','ATM','2024-10-10 09:58:36.305000','14608613'),('YIWM3W6K','2024-10-10 10:20:18.729000',3,'NCB','VNP14608669','ATM','2024-10-10 10:20:18.729000','14608669');
/*!40000 ALTER TABLE `online_payment_result` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-10 12:03:00
