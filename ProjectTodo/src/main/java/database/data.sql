-- MySQL dump 10.13  Distrib 8.0.37, for Win64 (x86_64)
--
-- Host: localhost    Database: todo
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projects` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `userId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `projects_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES (1,'project11','2024-05-09 18:30:00',NULL),(2,'pr2','2024-05-09 18:30:00',NULL),(3,'pr3','2024-05-09 18:30:00',NULL),(4,'pr44','2024-05-09 18:30:00',NULL),(5,'pr43','2024-05-09 18:30:00',NULL),(6,'pr4','2024-05-09 18:30:00',NULL),(7,'pr5','2024-05-09 18:30:00',NULL),(8,'project8','2024-05-09 18:30:00',NULL),(9,'project1ann','2024-05-10 18:30:00',NULL),(10,'projectdemo','2024-05-10 18:30:00',1),(11,'singsong','2024-05-10 18:30:00',2),(12,'guitar cleaning','2024-05-10 18:30:00',2),(13,'Project1','2024-05-10 18:30:00',3),(14,'Project2','2024-05-10 18:30:00',3);
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `todos`
--

DROP TABLE IF EXISTS `todos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `todos` (
  `todo_id` int NOT NULL AUTO_INCREMENT,
  `project_id` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `due_date` date DEFAULT NULL,
  PRIMARY KEY (`todo_id`),
  KEY `project_id` (`project_id`),
  CONSTRAINT `todos_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `todos`
--

LOCK TABLES `todos` WRITE;
/*!40000 ALTER TABLE `todos` DISABLE KEYS */;
INSERT INTO `todos` VALUES (4,1,'todo1','pending','2024-05-09 18:30:00','2024-05-10 15:09:08','2024-05-11'),(5,1,'todo2','completed','2024-05-09 18:30:00','2024-05-11 02:48:14','2024-05-11'),(6,3,'todoo','pending','2024-05-09 18:30:00','2024-05-10 15:23:16','2024-05-15'),(7,8,'task','completed','2024-05-09 18:30:00','2024-05-11 02:32:40','2024-05-19'),(8,7,'task2','pending','2024-05-09 18:30:00','2024-05-10 17:21:34','2024-05-13'),(15,11,'write song','pending','2024-05-10 18:30:00','2024-05-11 06:03:47','2024-05-24'),(16,12,'new string','pending','2024-05-10 18:30:00','2024-05-11 06:41:55','2024-05-11'),(17,10,'todo1','pending','2024-05-10 18:30:00','2024-05-11 09:20:16','2024-06-08'),(18,13,'Task1','pending','2024-05-10 18:30:00','2024-05-11 09:57:03','2024-05-12'),(19,13,'Task2','completed','2024-05-10 18:30:00','2024-05-11 09:57:21','2024-05-11'),(20,13,'Task3','pending','2024-05-10 18:30:00','2024-05-11 09:57:42','2024-05-13'),(21,10,'todo2','completed','2024-05-10 18:30:00','2024-05-11 11:34:29','2024-05-11'),(22,13,'Task4','completed','2024-05-10 18:30:00','2024-05-11 13:01:27','2024-05-11');
/*!40000 ALTER TABLE `todos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Ann Paul','annpaul9902223@gmail.com','ann'),(2,'aleena','aleena@gmail.com','aleena'),(3,'user','user@gmail.com','user');
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

-- Dump completed on 2024-05-11 18:50:47
