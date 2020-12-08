
CREATE TABLE Category (
  id INTEGER NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)

);

INSERT INTO `category` VALUES (42,'Movies');

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dateOfBirth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `membershipType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `user` VALUES (50,'2020-12-07','jane@yahoo.com','Jane','FEMALE','Enoch','TRIAL'),(49,'2020-12-07','lee@yahoo.com','Lee','MALE','Enoch','TRIAL'),(48,'2020-12-07','tj@yahoo.com','TJ','MALE','Enoch','TRIAL'),(47,'2020-12-07','molly@yahoo.com','Molly','FEMALE','Enoch','TRIAL'),(46,'2020-12-07','sarah@yahoo.com','Sarah','FEMALE','Enoch','TRIAL'),(45,'2020-12-07','pete@yahoo.com','Pete','MALE','Enoch','TRIAL'),(44,'2020-12-07','chris@yahoo.com','James','MALE','Enoch','TRIAL'),(43,'2020-12-07','chris@yahoo.com','Chris','MALE','Enoch','TRIAL');

DROP TABLE IF EXISTS `submittedanswermultiimpl_answer`; 
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `submittedanswermultiimpl_answer` (
  `SubmittedAnswerMultiImpl_id` bigint NOT NULL,
  `selectedAnswers_id` bigint NOT NULL,
  UNIQUE KEY `UK_s4um8aslw2bkha2k234kr7q9b` (`selectedAnswers_id`)
);

INSERT INTO `submittedanswermultiimpl_answer` VALUES (66,63),(66,64),(66,65);


DROP TABLE IF EXISTS `submittedanswermultiimpl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `submittedanswermultiimpl` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `question_id` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)

);
INSERT INTO `submittedanswermultiimpl` VALUES (66,56,50);

DROP TABLE IF EXISTS `submittedanswer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `submittedanswer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `question_id` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `question_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question_answer` (
  `QuestionWithOptionsImpl_id` int NOT NULL,
  `possibleAnswers_id` bigint NOT NULL,
  UNIQUE KEY `UK_odyhg2vjxfknjwe909ba3xjph` (`possibleAnswers_id`)
);

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `Question_Type` varchar(31) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `questionText` varchar(255) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `question` VALUES ('question_with_options',56,'How much do you like these sports?',42);

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
);

INSERT INTO `hibernate_sequence` VALUES (67);

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answer` (
  `Answer_Type` varchar(31) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `answerText` varchar(255) DEFAULT NULL,
  `answerWeight` varchar(20) DEFAULT NULL,
  `question_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
 
);

INSERT INTO `answer` VALUES ('answer_weighted',51,'Basketball','NULL',56),('answer_weighted',52,'Football','NULL',56),('answer_weighted',53,'Swimming','NULL',56),('answer_weighted',64,'Football','FOUR',NULL),('answer_weighted',63,'Basketball','FIVE',NULL),('answer_weighted',65,'Swimming','SIX',NULL);




