create database Twetter;
use Twetter;
DROP TABLE IF EXISTS users;
create table users (
	usr varchar(255) primary key,
    mail varchar(255),
    pwd varchar(255)
);

DROP TABLE IF EXISTS `follows`;
CREATE TABLE `follows` (
  `uid` varchar(255) NOT NULL,
  `fid` varchar(255) NOT NULL,
  PRIMARY KEY (`uid`,`fid`),
  KEY `fid_users_fk` (`fid`),
  CONSTRAINT `fid_users_fk` FOREIGN KEY (`fid`) REFERENCES `users` (`usr`),
  CONSTRAINT `uid_users_fk` FOREIGN KEY (`uid`) REFERENCES `users` (`usr`)
);

DROP TABLE IF EXISTS `tweets`;
CREATE TABLE `tweets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) DEFAULT NULL,
  `postdatetime` timestamp NULL DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `likes` int(11) DEFAULT 0,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tweets_users_fk` (`uid`),
  KEY `tweets_tweets_fk` (`pid`),
  CONSTRAINT `tweets_tweets_fk` FOREIGN KEY (`pid`) REFERENCES `tweets` (`id`) ON DELETE CASCADE,
  CONSTRAINT `tweets_users_fk` FOREIGN KEY (`uid`) REFERENCES `users` (`usr`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO users (usr,mail,pwd) VALUES ('admin', 'admin@a','admin');
