CREATE DATABASE  IF NOT EXISTS `imdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `imdb`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: imdb
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
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actor` (
  `actor_id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `bio` text,
  `poster` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`actor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
INSERT INTO `actor` VALUES (1,'Christian','Bale','1974-01-30','Christian Charles Philip Bale was born in Pembrokeshire, Wales, UK on January 30, 1974, to English parents Jennifer \"Jenny\" (James) and David Bale.','https://www.themoviedb.org/t/p/w300_and_h450_bestv2/AcfW3p5D6ov573fABLyGqwYdolD.jpg'),(2,'Hugh','Jackman','1968-10-12','Hugh Michael Jackman is an Australian actor, singer, multi-instrumentalist, dancer and ','https://www.themoviedb.org/t/p/w300_and_h450_bestv2/4Xujtewxqt6aU0Y81tsS9gkjizk.jpg'),(3,'Leonardo','DiCaprio','1974-11-11','Leo','https://www.themoviedb.org/t/p/w300_and_h450_bestv2/wo2hJpn04vbtmh0B9utCFdsQhxM.jpg'),(4,'Cillian','Murphy','1976-05-25','Cillian Murphy','https://www.themoviedb.org/t/p/w300_and_h450_bestv2/3DZAf9CwXmfV2HZOEdqeZAGTndV.jpg'),(5,'Matthew','McConaughey','1969-11-04','Matthew','https://www.themoviedb.org/t/p/w300_and_h450_bestv2/e9ZHRY5toiBZCIPEEyvOG9A8ers.jpg'),(6,'Keanu','Reeves','1964-09-02','Keanu','https://www.themoviedb.org/t/p/w300_and_h450_bestv2/tgWjOflzUXikIu3jZrWneFeDOCA.jpg'),(7,'Brad','Pitt','1963-12-18','William Bradley \"Brad\" Pitt was born on December 18, 1963 in Shawnee, Oklahoma and raised in Springfield, Missouri to Jane Etta Pitt (née Hillhouse), a school counselor & William Alvin \"Bill\" Pitt, a truck company manager. At Kickapoo High School, Pitt wa','https://www.themoviedb.org/t/p/w300_and_h450_bestv2/oos8Gh9L9zcadx1awXRwTMHGl7V.jpg'),(8,'Morgan','Freeman','1931-06-01','With an authoritative voice and calm demeanor, this ever popular American actor has grown into one of the most respected figures in modern US cinema. Morgan was born on June 1, 1937 in Memphis, Tennessee, to Mayme Edna (Revere), a teacher, and Morgan Port','https://www.themoviedb.org/t/p/w300_and_h450_bestv2/jPsLqiYGSofU4s6BjrxnefMfabb.jpg'),(9,'Tom','Hanks','1956-07-09','Thomas Jeffrey Hanks was born in Concord, California, to Janet Marylyn (Frager), a hospital worker, and Amos Mefford Hanks, an itinerant cook. His mother\'s family, originally surnamed \"Fraga\", was entirely Portuguese, while his father was of mostly Englis','https://www.themoviedb.org/t/p/w300_and_h450_bestv2/xndWFsBlClOJFRdhSt4NBwiPq2o.jpg'),(10,'Matt','Damon','1970-10-08','Matthew Paige Damon was born on October 8, 1970, in Boston, Massachusetts, to Kent Damon, a stockbroker, realtor and tax preparer, and Nancy Carlsson-Paige, an early childhood education professor at Lesley University. Matt has an older brother, Kyle, a sc','https://www.themoviedb.org/t/p/w300_and_h450_bestv2/At3JgvaNeEN4Z4ESKlhhes85Xo3.jpg'),(11,'Arnold','Schwarzenegger','1947-07-30','With an almost unpronounceable surname and a thick Austrian accent, who would have ever believed that a brash, quick talking bodybuilder from a small European village would become one of Hollywood\'s biggest stars, marry into the prestigious Kennedy family, amass a fortune via shrewd investments and one day b','https://www.themoviedb.org/t/p/w300_and_h450_bestv2/zEMhugsgXIpnQqO31GpAJYMUZZ1.jpg');
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Drama'),(2,'Comedy'),(3,'Animated'),(4,'Adventure'),(5,'Crime'),(6,'Horror'),(7,'Action'),(8,'Fantasy'),(9,'Romance'),(10,'Musical'),(11,'Noir'),(12,'Science Fiction'),(13,'Thriller'),(14,'History');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `movie_id` int NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `release_date` date DEFAULT NULL,
  `runtime` int DEFAULT NULL,
  `plot` varchar(255) DEFAULT NULL,
  `poster` varchar(255) DEFAULT NULL,
  `mpa_rating_id` int DEFAULT NULL,
  `trailer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`movie_id`),
  KEY `fk_movie_mpa_rating` (`mpa_rating_id`),
  CONSTRAINT `fk_movie_mpa_rating` FOREIGN KEY (`mpa_rating_id`) REFERENCES `movie_mpa_film_ratings` (`MPA_rating_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'The Dark Knight','2008-07-25',154,'When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.','https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qJ2tW6WMUDux911r6m7haRef0WH.jpg',3,'https://www.youtube.com/watch?v=_PZpmTj1Q8Q'),(2,'Inception','2010-07-30',148,'A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.,','https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oYuLEt3zVCKq57qu2F8dT7NIa6f.jpg',3,'https://www.youtube.com/watch?v=YoHD9XEInc0'),(3,'The Prestige','2006-12-22',130,'After a tragic accident, two stage magicians in 1890s London engage in a battle to create the ultimate illusion while sacrificing everything they have to outwit each other.,','https://www.themoviedb.org/t/p/w600_and_h900_bestv2/tRNlZbgNCNOpLpbPEz5L8G8A0JN.jpg',3,'https://www.youtube.com/watch?v=ELq7V8vkekI'),(4,'Interstellar','2014-11-07',169,'When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.','https://www.themoviedb.org/t/p/w600_and_h900_bestv2/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg',3,'https://www.youtube.com/watch?v=2LqzF5WauAw'),(5,'The Matrix','1999-09-03',136,'When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.','https://www.themoviedb.org/t/p/w600_and_h900_bestv2/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg',3,'https://www.youtube.com/watch?v=tGgCqGm_6Hs'),(6,'Se7en','1995-09-22',127,'Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his motives.','https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6yoghtyTpznpBik8EngEmJskVUO.jpg',3,'https://www.youtube.com/watch?v=9CiW_DgxCnQ'),(7,'Saving Private Ryan','1998-09-11',169,'Following the Normandy Landings, a group of U.S. soldiers go behind enemy lines to retrieve a paratrooper whose brothers have been killed in action.','https://www.themoviedb.org/t/p/w600_and_h900_bestv2/uqx37cS8cpHg8U35f9U5IBlrCV3.jpg',3,'https://www.youtube.com/watch?v=9CiW_DgxCnQ'),(8,'The Green Mile','1999-12-10',189,'A tale set on death row in a Southern jail, where gentle giant John possesses the mysterious power to heal people\'s ailments. When the lead guard, Paul, recognizes John\'s gift, he tries to help stave off the condemned man\'s execution.','https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8VG8fDNiy50H4FedGwdSVUPoaJe.jpg',3,'https://www.youtube.com/watch?v=Ki4haFrqSrw'),(9,'Oppenheimer','2023-07-21',180,'The story of American scientist, J. Robert Oppenheimer, and his role in the development of the atomic bomb.','https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg',3,NULL),(10,'The Terminator','1984-10-26',107,'A human soldier is sent from 2029 to 1984 to stop an almost indestructible cyborg killing machine, sent from the same year, which has been programmed to execute a young woman whose unborn son is the key to humanity\'s future salvation.','https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hzXSE66v6KthZ8nPoLZmsi2G05j.jpg',3,NULL);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_actor`
--

DROP TABLE IF EXISTS `movie_actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_actor` (
  `movies_movie_id` int NOT NULL,
  `actors_actor_id` int NOT NULL,
  KEY `fk_movie_actor_actor_id` (`actors_actor_id`),
  KEY `fk_movie_actor_movie_id` (`movies_movie_id`),
  CONSTRAINT `fk_movie_actor_actor_id` FOREIGN KEY (`actors_actor_id`) REFERENCES `actor` (`actor_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_movie_actor_movie_id` FOREIGN KEY (`movies_movie_id`) REFERENCES `movie` (`movie_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_actor`
--

LOCK TABLES `movie_actor` WRITE;
/*!40000 ALTER TABLE `movie_actor` DISABLE KEYS */;
INSERT INTO `movie_actor` VALUES (1,1),(1,8),(2,3),(2,4),(3,2),(3,1),(4,5),(4,10),(5,6),(6,7),(6,8),(7,9),(7,10),(8,9),(9,4),(9,10),(10,11);
/*!40000 ALTER TABLE `movie_actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_category`
--

DROP TABLE IF EXISTS `movie_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_category` (
  `movies_movie_id` int NOT NULL,
  `categories_category_id` int NOT NULL,
  KEY `fk_movie_category_category_id` (`categories_category_id`),
  KEY `fk_movie_category_movie_id` (`movies_movie_id`),
  CONSTRAINT `fk_movie_category_category_id` FOREIGN KEY (`categories_category_id`) REFERENCES `category` (`category_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_movie_category_movie_id` FOREIGN KEY (`movies_movie_id`) REFERENCES `movie` (`movie_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_category`
--

LOCK TABLES `movie_category` WRITE;
/*!40000 ALTER TABLE `movie_category` DISABLE KEYS */;
INSERT INTO `movie_category` VALUES (1,1),(3,1),(4,1),(6,1),(7,1),(8,1),(9,1),(2,4),(4,4),(1,5),(6,5),(8,5),(1,7),(2,7),(5,7),(10,7),(8,8),(2,12),(3,12),(4,12),(5,12),(10,12),(9,14);
/*!40000 ALTER TABLE `movie_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_mpa_film_ratings`
--

DROP TABLE IF EXISTS `movie_mpa_film_ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_mpa_film_ratings` (
  `MPA_rating_id` int NOT NULL,
  `mpa_rating_description` varchar(255) DEFAULT NULL,
  `mpa_rating_abbreviation` varchar(255) DEFAULT NULL,
  `mpa_rating_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`MPA_rating_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_mpa_film_ratings`
--

LOCK TABLES `movie_mpa_film_ratings` WRITE;
/*!40000 ALTER TABLE `movie_mpa_film_ratings` DISABLE KEYS */;
INSERT INTO `movie_mpa_film_ratings` VALUES (1,'All ages admitted. Nothing that would offend parents for viewing by children.','G','General Audiences'),(2,'Some material may not be suitable for children. Parents urged to give \"parental guidance\". May contain some material parents might not like for their young children.','PG','Parental Guidance Suggested'),(3,'Some material may be inappropriate for children under 13. Parents are urged to be cautious. Some material may be inappropriate for pre-teenagers.','PG-13','Parents Strongly Cautioned'),(4,'Under 17 requires accompanying parent or adult guardian. Contains some adult material. Parents are urged to learn more about the film before taking their young children with them.','R','Restricted'),(5,'No one 17 and under admitted. Clearly adult. Children are not admitted.','NC-17','Adults Only');
/*!40000 ALTER TABLE `movie_mpa_film_ratings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT 'USER',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'xxx@gmail.com','xxx@gmail.com','$2a$10$u2FTEdiVHkWTK9QPFYnj.OGUILQG3q2NFzyYHAc2sYe.JNt9pjWwS','USER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_seq`
--

DROP TABLE IF EXISTS `user_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_seq`
--

LOCK TABLES `user_seq` WRITE;
/*!40000 ALTER TABLE `user_seq` DISABLE KEYS */;
INSERT INTO `user_seq` VALUES (1);
/*!40000 ALTER TABLE `user_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-07 15:25:23
