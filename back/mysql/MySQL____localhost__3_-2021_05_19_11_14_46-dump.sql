-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: seedling_dev
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '账户Id',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '邮箱',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账户名',
  `password` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `register_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `login_time` datetime DEFAULT NULL COMMENT '上一次登录时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_account_name` (`name`),
  UNIQUE KEY `idx_account_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'admin@qq.com','admin','$2a$10$OG1zaFHT2LUy4SGcQ4EnRu9sPQMjMGEE6jARz61aQwRQ3316N6ikG','2018-01-01 00:00:00','2021-05-19 11:09:36'),(2,'user@qq.com','user','$2a$10$yjfcoyNWgoUh3QQ3I6Lwmux57rCz3mZP1j8V4BK60EIVdwT3SkwFO','2018-01-01 00:00:00','2018-02-01 00:00:00'),(4,'12113@qq.com','admin2','$2a$10$LsaK.9kG0ptdxtqypSDv7uBM14CbQ/PLPJv1Sqrg7qA32GtsjQABe','2021-05-12 16:19:28',NULL);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_role`
--

DROP TABLE IF EXISTS `account_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_role` (
  `account_id` bigint unsigned NOT NULL COMMENT '账户Id',
  `role_id` bigint unsigned NOT NULL COMMENT '角色Id',
  PRIMARY KEY (`account_id`,`role_id`),
  KEY `fk_ref_role` (`role_id`),
  CONSTRAINT `fk_ref_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ref_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_role`
--

LOCK TABLES `account_role` WRITE;
/*!40000 ALTER TABLE `account_role` DISABLE KEYS */;
INSERT INTO `account_role` VALUES (2,1),(4,1),(1,2),(1,3);
/*!40000 ALTER TABLE `account_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `department_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (2,'All Department Manager','总经办'),(3,'Human Resources Department','人力资源部'),(5,'Order Department','采购部'),(6,'Warehousing Department','仓储部'),(7,'Finance Department','财务部'),(11,'test deptarment252354HJKHKjaf','宣传部门'),(17,'test asdf🎞🎠🎀🎨🥼🎫','文化部门'),(20,'t32424rment🎞🎠🎀🎨🥼🎫','测试部门'),(31,'测试数据','数据中心'),(32,'实验中心','实验中心'),(33,'123333🎨🖼🛒','质量校验部门');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dictionary_contents`
--

DROP TABLE IF EXISTS `dictionary_contents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dictionary_contents` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) DEFAULT NULL COMMENT '字典目录编号',
  `title` varchar(255) DEFAULT NULL COMMENT '字典目录名称',
  `intro` varchar(255) DEFAULT NULL COMMENT '字典目录简介',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dictionary_contents_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dictionary_contents`
--

LOCK TABLES `dictionary_contents` WRITE;
/*!40000 ALTER TABLE `dictionary_contents` DISABLE KEYS */;
INSERT INTO `dictionary_contents` VALUES (1,'job','职业','做什么的'),(2,'source','来源','客户来源渠道'),(3,'intentionDegree','意向程度','有多么想入坑'),(4,'subject','学科','学科分类'),(5,'size','收款类型','学费收款方式'),(6,NULL,'办学性质',NULL),(7,NULL,'客户重要程度',NULL),(8,NULL,'外语水平',NULL);
/*!40000 ALTER TABLE `dictionary_contents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dictionary_details`
--

DROP TABLE IF EXISTS `dictionary_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dictionary_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '字典明细名称',
  `sequence` int DEFAULT NULL COMMENT '字典明细序号',
  `parentId` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY ` dictionary_details_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dictionary_details`
--

LOCK TABLES `dictionary_details` WRITE;
/*!40000 ALTER TABLE `dictionary_details` DISABLE KEYS */;
INSERT INTO `dictionary_details` VALUES (1,'老师',2,1),(2,'司机',1,1),(3,'老板',1,1),(4,'学生',1,1),(5,'秘书',3,1);
/*!40000 ALTER TABLE `dictionary_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `dept` int NOT NULL,
  `hireDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入职时间',
  `state` int DEFAULT NULL COMMENT '状态 1正常 0离职',
  `admin` int DEFAULT NULL COMMENT '超级管理员身份 1超管 0普通',
  PRIMARY KEY (`id`),
  UNIQUE KEY `employee_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1014 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'admin','test','admin@c.c',20,3,'2021-05-14 00:28:00',1,1),(2,'赵总','123','163@163.com',35,1,'2021-05-16 01:19:51',1,1),(3,'赵一明','123','g@gmail.com',25,1,'2021-05-16 01:22:38',1,1),(1009,'刘憨憨没有心',NULL,'moshuyingnode@gmail.com',35,6,'2021-05-18 06:49:32',NULL,NULL),(1010,'失败了的刘某','123333','moshuyingnode@gmail.com',33,7,'2021-05-18 07:10:31',NULL,NULL),(1011,'成功了的刘总','123333','fox-td@outlook.com',35,6,'2021-05-18 07:11:19',NULL,NULL),(1013,'123','123333','fox-td@outlook.com',11,3,'2021-05-18 08:47:13',NULL,NULL);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_role`
--

DROP TABLE IF EXISTS `employee_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employeeId` int DEFAULT NULL,
  `roleId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `employee_role_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_role`
--

LOCK TABLES `employee_role` WRITE;
/*!40000 ALTER TABLE `employee_role` DISABLE KEYS */;
INSERT INTO `employee_role` VALUES (4,3,4),(5,3,4),(6,3,8),(14,1001,4),(15,1001,5),(16,1001,8),(17,1001,9),(18,1002,4),(19,1002,5),(20,1002,8),(21,1002,9),(22,1000,4),(23,1000,5),(24,1000,8),(25,1000,9),(37,1,8),(39,1,5),(40,1,4),(41,1009,10),(42,1009,11),(43,1009,9),(44,1010,8),(45,1011,5),(49,1013,5),(50,1013,4);
/*!40000 ALTER TABLE `employee_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `expression` varchar(255) DEFAULT NULL COMMENT '资源地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `permission_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'客户列表','customer:list'),(2,'客户状态修改','customer:changeStatus'),(3,'客户新增修改','customer:saveOrUpdate'),(5,'客户池列表','customerPool:list'),(6,'跟进历史列表','followHistory:list'),(7,'跟进历史新增/修改','followHistory:saveOrUpdate'),(8,'移交历史列表','transferHistory:list'),(9,'移交历史新增/修改','transferHistory:saveOrUpdate'),(10,'部门列表','department:list'),(11,'部门删除','department:delete'),(12,'部门新增/修改','department:addOrUpdate'),(13,'员工删除','employee:delete'),(14,'员工列表','employee:list'),(15,'员工编辑','employee:edit'),(16,'员工批量删除','employee:deleteMultiple');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '角色Id',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `sn` varchar(255) DEFAULT NULL COMMENT '角色编号',
  `permission` int DEFAULT NULL COMMENT '相关权限 多对多关系 为权限对象',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'USER',NULL,NULL),(2,'ADMIN',NULL,NULL),(3,'TEST',NULL,NULL),(4,'市场经理','Market Manager',1),(5,'市场人员','123🎨🧵🎨',NULL),(7,'123123','123333🎨🖼🛒123123',NULL),(8,'测试部门','123123让士大夫',NULL),(9,'阿斯蒂芬','阿斯蒂芬2为人',NULL),(10,'却认为撒士大夫','as地方请问父亲为请问父亲为',NULL),(11,'全微分q\'d','qds发士大夫阿斯顿',NULL),(12,'啊手动阀手动阀as','f阿斯蒂芬阿斯顿发',NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL COMMENT '角色id',
  `permission_id` int NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_permission_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=213 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色权限中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (188,4,5),(190,4,7),(193,4,1),(194,4,2),(195,4,3),(196,4,6),(197,4,8),(199,4,9);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-19 11:14:47
