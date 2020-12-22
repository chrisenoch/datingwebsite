DROP TABLE IF EXISTS `Category`;
CREATE TABLE Category (
  id INTEGER NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)

);

INSERT INTO `category` VALUES (1,'Movies'),(18, 'Sports');

DROP TABLE IF EXISTS `user`;
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

INSERT INTO `user` VALUES (8,'2020-12-07','jane@yahoo.com','Jane','FEMALE','Enoch','TRIAL'),(7,'2020-12-07','lee@yahoo.com','Lee','MALE','Enoch','TRIAL'),(6,'2020-12-07','tj@yahoo.com','TJ','MALE','Enoch','TRIAL'),(5,'2020-12-07','molly@yahoo.com','Molly','FEMALE','Enoch','TRIAL'),(4,'2020-12-07','sarah@yahoo.com','Sarah','FEMALE','Enoch','TRIAL'),(3,'2020-12-07','pete@yahoo.com','Pete','MALE','Enoch','TRIAL'),(2,'2020-12-07','chris@yahoo.com','James','MALE','Enoch','TRIAL');

DROP TABLE IF EXISTS `submittedanswermultiimpl_answer`; 
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `submittedanswermultiimpl_answer` (
  `SubmittedAnswerMultiImpl_id` bigint NOT NULL,
  `selectedAnswers_id` bigint NOT NULL,
  PRIMARY KEY (`SubmittedAnswerMultiImpl_id`,`selectedAnswers_id`)
);

INSERT INTO `submittedanswermultiimpl_answer` VALUES (17,14),(17,15),(17,16);


DROP TABLE IF EXISTS `submittedanswermultiimpl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `submittedanswermultiimpl` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `question_id` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)

);
INSERT INTO `submittedanswermultiimpl` VALUES (17,13,2);

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

INSERT INTO `question` VALUES ('question_with_options',13,'How much do you like these sports?',1);

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
);

INSERT INTO `hibernate_sequence` VALUES (19);

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

INSERT INTO `answer` VALUES ('standard_answer',10,'Basketball', NULL ,13),('standard_answer',11,'Football', NULL,13),('standard_answer',12,'Swimming',NULL ,13),('answer_weighted',14,'Basketball','FIVE',NULL),('answer_weighted',15,'Football','FOUR',NULL),('answer_weighted',16,'Swimming','SIX',NULL);




