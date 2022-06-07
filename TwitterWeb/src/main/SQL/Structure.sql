create database Twetter;
use Twetter;
create table users (
	usr varchar(255) primary key,
    mail varchar(255),
    pwd varchar(255)
);
drop table users;
select * from users;

DROP TABLE IF EXISTS `follows`;
CREATE TABLE `follows` (
  `usr` varchar(255) NOT NULL,
  `fid` int(11) NOT NULL,
  PRIMARY KEY (`uid`,`fid`),
  KEY `fid_users_fk` (`fid`),
  CONSTRAINT `fid_users_fk` FOREIGN KEY (`fid`) REFERENCES `users` (`usr`),
  CONSTRAINT `usr_users_fk` FOREIGN KEY (`usr`) REFERENCES `users` (`usr`)
);

DROP TABLE IF EXISTS `tweets`;
CREATE TABLE `tweets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) DEFAULT NULL,
  `postdatetime` timestamp NULL DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tweets_users_fk` (`usr`),
  KEY `tweets_tweets_fk` (`pid`),
  CONSTRAINT `tweets_tweets_fk` FOREIGN KEY (`pid`) REFERENCES `tweets` (`id`) ON DELETE CASCADE,
  CONSTRAINT `tweets_users_fk` FOREIGN KEY (`usr`) REFERENCES `users` (`usr`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;