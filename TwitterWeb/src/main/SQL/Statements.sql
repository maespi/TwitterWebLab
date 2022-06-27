drop database if exists `Twitter`;
CREATE database `Twitter`;
USE `Twitter`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `usr` varchar(20) NOT NULL,
  `mail` varchar(40) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  `admin` boolean DEFAULT false,
  PRIMARY KEY (`usr`),
  UNIQUE KEY `mail` (`mail`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `follows`;
CREATE TABLE `follows` (
  `uid` varchar(20) NOT NULL,
  `fid` varchar(20) NOT NULL,
  PRIMARY KEY (`uid`,`fid`),
  KEY `fid_users_fk` (`fid`),
  CONSTRAINT `fid_users_fk` FOREIGN KEY (`fid`) REFERENCES `users` (`usr`),
  CONSTRAINT `uid_users_fk` FOREIGN KEY (`uid`) REFERENCES `users` (`usr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tweets`
--

DROP TABLE IF EXISTS `tweets`;
CREATE TABLE `tweets` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` varchar(20) NOT NULL,
  `postdatetime` timestamp NULL DEFAULT NULL,
  `content` TEXT DEFAULT NULL,
  `pid` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tweets_users_fk` (`uid`),
  KEY `tweets_tweets_fk` (`pid`),
  CONSTRAINT `tweets_tweets_fk` FOREIGN KEY (`pid`) REFERENCES `tweets` (`id`),
  CONSTRAINT `tweets_users_fk` FOREIGN KEY (`uid`) REFERENCES `users` (`usr`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tweets`
--

LOCK TABLES `tweets` WRITE;
/*!40000 ALTER TABLE `tweets` DISABLE KEYS */;
INSERT INTO `tweets` VALUES (77,1,'2021-06-02 20:15:49','hola',NULL);
/*!40000 ALTER TABLE `tweets` ENABLE KEYS */;
UNLOCK TABLES;
