-- MariaDB dump 10.19  Distrib 10.11.6-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: katalog_igara
-- ------------------------------------------------------
-- Server version	10.11.6-MariaDB-0+deb12u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `kategorija_video_igre`
--

DROP TABLE IF EXISTS `kategorija_video_igre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kategorija_video_igre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategorija_video_igre`
--

LOCK TABLES `kategorija_video_igre` WRITE;
/*!40000 ALTER TABLE `kategorija_video_igre` DISABLE KEYS */;
INSERT INTO `kategorija_video_igre` VALUES
(1,'Sandbox'),
(2,'Adventure'),
(3,'RPG');
/*!40000 ALTER TABLE `kategorija_video_igre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kategorije_igara`
--

DROP TABLE IF EXISTS `kategorije_igara`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kategorije_igara` (
  `igra_id` int(11) NOT NULL,
  `kategorija_id` int(11) NOT NULL,
  PRIMARY KEY (`igra_id`,`kategorija_id`),
  KEY `FKj9b6ofpvgjdbkkqqmlf5rkc60` (`kategorija_id`),
  CONSTRAINT `FK8t58grel7xkj5fglhwm3919r3` FOREIGN KEY (`igra_id`) REFERENCES `video_igra` (`id`),
  CONSTRAINT `FKj9b6ofpvgjdbkkqqmlf5rkc60` FOREIGN KEY (`kategorija_id`) REFERENCES `kategorija_video_igre` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategorije_igara`
--

LOCK TABLES `kategorije_igara` WRITE;
/*!40000 ALTER TABLE `kategorije_igara` DISABLE KEYS */;
INSERT INTO `kategorije_igara` VALUES
(2,1),
(2,2),
(3,1),
(3,3);
/*!40000 ALTER TABLE `kategorije_igara` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `komentar`
--

DROP TABLE IF EXISTS `komentar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `komentar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `igra_id` int(11) DEFAULT NULL,
  `ocena` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `tekst` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `komentar`
--

LOCK TABLES `komentar` WRITE;
/*!40000 ALTER TABLE `komentar` DISABLE KEYS */;
/*!40000 ALTER TABLE `komentar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `platforma`
--

DROP TABLE IF EXISTS `platforma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `platforma` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) DEFAULT NULL,
  `proizvodjac` varchar(255) DEFAULT NULL,
  `tip_platforme` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `platforma`
--

LOCK TABLES `platforma` WRITE;
/*!40000 ALTER TABLE `platforma` DISABLE KEYS */;
INSERT INTO `platforma` VALUES
(1,'Genesis','Saga','konzola'),
(2,'SNAS','Nontendo','konzola');
/*!40000 ALTER TABLE `platforma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `platforme_za_igru`
--

DROP TABLE IF EXISTS `platforme_za_igru`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `platforme_za_igru` (
  `igra_id` int(11) NOT NULL,
  `platforma_id` int(11) NOT NULL,
  PRIMARY KEY (`igra_id`,`platforma_id`),
  KEY `FKicr72413da0urfte7p95hhhnu` (`platforma_id`),
  CONSTRAINT `FKicr72413da0urfte7p95hhhnu` FOREIGN KEY (`platforma_id`) REFERENCES `platforma` (`id`),
  CONSTRAINT `FKjq6sf1gl6nj0cio0s7nttnnts` FOREIGN KEY (`igra_id`) REFERENCES `video_igra` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `platforme_za_igru`
--

LOCK TABLES `platforme_za_igru` WRITE;
/*!40000 ALTER TABLE `platforme_za_igru` DISABLE KEYS */;
INSERT INTO `platforme_za_igru` VALUES
(2,1),
(2,2),
(3,1),
(3,2);
/*!40000 ALTER TABLE `platforme_za_igru` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video_igra`
--

DROP TABLE IF EXISTS `video_igra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `video_igra` (
  `godina_izdanja` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `izdavacka_kuca` varchar(255) DEFAULT NULL,
  `naziv` varchar(255) DEFAULT NULL,
  `opis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video_igra`
--

LOCK TABLES `video_igra` WRITE;
/*!40000 ALTER TABLE `video_igra` DISABLE KEYS */;
INSERT INTO `video_igra` VALUES
(2002,2,'kuca1','Igra2','novi_Opis_1'),
(2005,3,'kuca1','Igra1','novi_Opis_1');
/*!40000 ALTER TABLE `video_igra` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-03 23:30:12
