-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: proje
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `identity_no` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `persons_persid` int DEFAULT NULL,
  PRIMARY KEY (`identity_no`),
  KEY `FKrnyqqtq2n0e8wyiaj094bar3a` (`persons_persid`),
  CONSTRAINT `FKrnyqqtq2n0e8wyiaj094bar3a` FOREIGN KEY (`persons_persid`) REFERENCES `persons` (`persid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES ('01020304051','bong@bing.com','Chanandler','Other','Bong','01020304050',4),('02020202022','vonneumann@harvard.com','John','Male','Von Neumann','01010101011',5),('09876123450','arwen@rivendell.com','Arwen','Female','Evanstar','87654321000',9),('09876543222','robin@canada.com','Robin','Female','Scherbatsky','09876543211',4),('10002000333','joey@it.com','Joey','Male','Tribianni','90870860800',8),('10203040500','lucas34@gs.com','Lucas','Male','Torreira','05425424242',6),('10230450678','phebes@perk.com','Phoebe','Female','Buffay','0000000001',4),('10809040501','zahahah@gs.com','Wilfried','Male','Zaha','05345343434',8),('10920930955','lindsay@hippi.com','Lindsay','Female','Weir','10920930922',4),('11118007055','yorgo@oly.com','Yorgos','Male','Printezis','09008007066',9),('11223344552','lily@lily.com','Lily','Female','Aldrin','11223344551',8),('12345665432','frodo@shire.com','Frodo','Other','Baggins','12345543210',4),('12345678911','ted@mosby.com','Ted','Other','Mosby','12345678910',8),('19328457066','gandalf@middleearth.com','Gandalf','Other','The grey','09008005001',4),('20202020202','marshall@lilypad.com','Marshall','Male','Eriksen','10101010101',5),('21474834444','samwise@shire.com','Samwise','Male','Gamgee','21474836475',9),('32345615544','legolas@elf.com','Legolas','Male','Green Leaf','12345671111',5),('40404040402','sammy@myspace.com','Sam','Other','Weir','50505005051',8),('50060030011','rossgeller@nyu.com','Ross','Male','Geller','10020030044',4),('50060070030','rachgreen@ck.com','Rachel','Female','Green','90080070060',5),('51029021333','mon@chefs.com','Monica','Female','Geller','67857887800',6),('99119911991','mauro@gs.com.tr','Mauro','Male','Icardı','99099909999',9),('9999999995','aragorn@gondor.com','Aragorn','Male','Son of Arathorn','0000000000',8);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-23 17:01:27
