-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: crm3
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
INSERT INTO `account` VALUES (1,'admin@qq.com','admin','$2a$10$OG1zaFHT2LUy4SGcQ4EnRu9sPQMjMGEE6jARz61aQwRQ3316N6ikG','2018-01-01 00:00:00','2021-05-21 12:10:00'),(2,'user@qq.com','user','$2a$10$yjfcoyNWgoUh3QQ3I6Lwmux57rCz3mZP1j8V4BK60EIVdwT3SkwFO','2018-01-01 00:00:00','2018-02-01 00:00:00'),(4,'12113@qq.com','admin2','$2a$10$LsaK.9kG0ptdxtqypSDv7uBM14CbQ/PLPJv1Sqrg7qA32GtsjQABe','2021-05-12 16:19:28',NULL);
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
-- Table structure for table `customer_follow_up_history`
--

DROP TABLE IF EXISTS `customer_follow_up_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_follow_up_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `traceTime` datetime DEFAULT NULL COMMENT '跟进时间',
  `traceDetails` varchar(255) DEFAULT NULL COMMENT '跟进内容 计划的详细内容',
  `traceType` int DEFAULT NULL COMMENT '跟进方式 计划采用如电话、邀约上门等  数据字典',
  `traceResult` int DEFAULT NULL COMMENT '跟进效果 优----3、中----2、差----1',
  `customerID` int DEFAULT NULL COMMENT '跟进客户 编辑时不可编辑 潜在客户对象/客户对象',
  `inputUser` int DEFAULT NULL COMMENT '创建人 自动填入当前登录用户，用户不可更改 员工对象',
  `type` int DEFAULT NULL COMMENT '跟进类型 0:潜在开发计划 1:客户跟进历史',
  PRIMARY KEY (`id`),
  UNIQUE KEY `customer_follow_up_history_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_follow_up_history`
--

LOCK TABLES `customer_follow_up_history` WRITE;
/*!40000 ALTER TABLE `customer_follow_up_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_follow_up_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_handover`
--

DROP TABLE IF EXISTS `customer_handover`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_handover` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customerID` int DEFAULT NULL COMMENT '客户 客户对象',
  `transUser` int DEFAULT NULL COMMENT '移交人员 实行移交操作的管理人员',
  `transTime` datetime DEFAULT NULL COMMENT '移交时间 ',
  `oldSeller` int DEFAULT NULL COMMENT '老市场专员 客户上的原始市场人员',
  `newSeller` int DEFAULT NULL COMMENT '新市场专员 由公司重新指派后的新市场人员',
  `transReason` varchar(255) DEFAULT NULL COMMENT '移交原因',
  PRIMARY KEY (`id`),
  UNIQUE KEY `customer_handover_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_handover`
--

LOCK TABLES `customer_handover` WRITE;
/*!40000 ALTER TABLE `customer_handover` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_handover` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_manager`
--

DROP TABLE IF EXISTS `customer_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_manager` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '客户姓名',
  `age` int NOT NULL COMMENT '客户年龄',
  `gender` int NOT NULL COMMENT '客户性别 页面为下拉框 1男 0女',
  `tel` varchar(255) NOT NULL COMMENT '电话号码',
  `qq` varchar(255) DEFAULT NULL,
  `job` int NOT NULL,
  `source` int NOT NULL COMMENT '客户来源',
  `seller` int DEFAULT NULL COMMENT '负责人 填写为当前登录用户',
  `inputUser` int DEFAULT NULL COMMENT ' 创建人 填写为当前登录用户',
  `inputTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int DEFAULT NULL COMMENT '-2:流失 -1:开发失败 0:潜在客户 1:正式客户 2:资源池客户',
  `positiveTime` datetime DEFAULT NULL COMMENT '转正时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `customer_manager_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_manager`
--

LOCK TABLES `customer_manager` WRITE;
/*!40000 ALTER TABLE `customer_manager` DISABLE KEYS */;
INSERT INTO `customer_manager` VALUES (1,'🐎❀🐍',18,1,'18888888888','100001',3,17,1,1,'2021-05-21 03:56:12',0,'2021-05-20 19:31:02'),(2,'马化腾',33,1,'18888888888','100002',3,17,1,1,'2021-05-20 13:21:56',2,'2021-05-20 19:31:02'),(3,'张云',18,1,'18888888888','100001',3,17,1,1,'2021-05-20 13:21:56',-1,'2021-05-20 19:31:02'),(4,'权志龙',18,1,'18888888888','100001',3,17,1,1,'2021-05-20 13:21:56',2,'2021-05-20 19:31:02'),(5,'马钊',23,1,'16666666666','100001',3,17,1,1,'2021-05-20 13:21:56',-1,'2021-05-20 19:31:02'),(6,'合理吗?🎃',18,0,'18888888888','100001',3,17,1,1,'2021-05-20 13:21:56',-2,'2021-05-20 19:31:02'),(7,'酒剑仙🗡',18,1,'17777777777','100001',3,17,1,1,'2021-05-21 03:53:03',1,'2021-05-20 19:31:02'),(8,'赵信',54,1,'18888888888','100001',3,17,1,1,'2021-05-20 12:55:08',1,'2021-05-20 19:31:02'),(9,'伊泽',45,0,'1999999999','100001',3,17,1,1,'2021-05-20 13:21:56',2,'2021-05-20 19:31:02'),(10,'阿斯顿',18,1,'18888888888','100001',3,17,1,1,'2021-05-20 13:21:56',-1,'2021-05-20 19:31:02'),(11,'廖嘉积',54,0,'18888888888','100001',3,17,1,1,'2021-05-20 13:21:56',-2,'2021-05-20 19:31:02'),(12,'郭晋安',18,1,'18888888888','100001',3,17,1,1,'2021-05-20 13:21:56',2,'2021-05-20 19:31:02');
/*!40000 ALTER TABLE `customer_manager` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (2,'All Department Manager','总经办'),(3,'Human Resources Department','人力资源部'),(5,'Order Department','采购部'),(6,'Warehousing Department','仓储部'),(7,'Finance Department','财务部'),(11,'test deptarment252354HJKHKjaf','宣传部门'),(17,'test asdf🎞🎠🎀🎨🥼🎫','文化部门'),(20,'t32424rment🎞🎠🎀🎨🥼🎫','测试部门'),(31,'测试数据','数据中心'),(32,'实验中心','实验中心'),(33,'123333','质量校验部门');
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dictionary_contents`
--

LOCK TABLES `dictionary_contents` WRITE;
/*!40000 ALTER TABLE `dictionary_contents` DISABLE KEYS */;
INSERT INTO `dictionary_contents` VALUES (1,'job','职业','做什么的'),(2,'source','来源','客户来源渠道'),(3,'intentionDegree','意向程度','有多么想入坑'),(4,'subject','学科','学科分类'),(5,'size','收款类型','学费收款方式'),(6,NULL,'办学性质',NULL),(7,NULL,'客户重要程度',NULL),(8,'1','外语水平','123132'),(9,'123','职业测试','123');
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dictionary_details`
--

LOCK TABLES `dictionary_details` WRITE;
/*!40000 ALTER TABLE `dictionary_details` DISABLE KEYS */;
INSERT INTO `dictionary_details` VALUES (1,'老师',2,1),(2,'司机',1,1),(3,'老板',1,1),(4,'学生',1,1),(5,'秘书2',3,1),(8,'微信',1,2),(9,'抖音',5,2),(10,'微博',7,2),(11,'测试信息',1,3),(12,'支付宝',1,5),(13,'公立院校',1,6),(14,'重要',1,7),(15,'微信',2,5),(16,'私立院校',2,6),(17,'QQ',1,2),(18,'街头小广告',1,2),(19,'头条号',1,2),(20,'微信公众号',2,2),(21,'报纸',1,2),(22,'Bilibili',4,2),(23,'微博2',123,8);
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
  `state` int NOT NULL DEFAULT '1' COMMENT '状态 1正常 0离职',
  `admin` int NOT NULL DEFAULT '0' COMMENT '超级管理员身份 1超管 0普通',
  PRIMARY KEY (`id`),
  UNIQUE KEY `employee_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1017 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'admin','test','admin@c.c',20,2,'2021-05-14 00:28:00',1,1),(2,'赵总','123','163@163.com',35,6,'2021-05-16 01:19:51',1,1),(3,'赵一明','123','g@gmail.com',25,1,'2021-05-16 01:22:38',1,1),(1009,'刘憨憨没有心','123123','moshuyingnode@gmail.com',35,6,'2021-05-18 06:49:32',1,0),(1010,'失败了的刘某','123333','moshuyingnode@gmail.com',33,7,'2021-05-18 07:10:31',1,0),(1011,'成功了的刘总','123333','fox-td@outlook.com',35,6,'2021-05-18 07:11:19',1,0),(1013,'123','123333','fox-td@outlook.com',11,3,'2021-05-18 08:47:13',1,0),(1014,'123','123','123',123,3,'2021-05-19 11:42:41',1,0),(1015,'admin',NULL,'admin@c.c',20,3,'2021-05-19 12:06:49',1,0),(1016,'123',NULL,'fox-td@outlook.com',11,3,'2021-05-19 12:06:49',1,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_role`
--

LOCK TABLES `employee_role` WRITE;
/*!40000 ALTER TABLE `employee_role` DISABLE KEYS */;
INSERT INTO `employee_role` VALUES (4,3,4),(5,3,4),(6,3,8),(14,1001,4),(15,1001,5),(16,1001,8),(17,1001,9),(18,1002,4),(19,1002,5),(20,1002,8),(21,1002,9),(22,1000,4),(23,1000,5),(24,1000,8),(25,1000,9),(39,1,5),(40,1,4),(44,1010,8),(45,1011,5),(49,1013,5),(50,1013,4),(51,1016,5),(52,1016,4),(53,1015,8),(54,1015,5),(55,1015,4),(56,2,3);
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'USER',NULL,NULL),(2,'ADMIN',NULL,NULL),(3,'TEST',NULL,NULL),(4,'市场经理','Market Manager',1),(5,'市场人员','123🎨🧵🎨',NULL),(7,'123123','123333🎨🖼🛒123123',NULL),(8,'测试部门','123123让士大夫',NULL),(9,'阿斯蒂芬','阿斯蒂芬2为人',NULL),(10,'却认为撒士大夫','as地方请问父亲为请问父亲为',NULL),(11,'全微分q\'d','qds发士大夫阿斯顿',NULL),(12,'啊手动阀手动阀as','f阿斯蒂芬阿斯顿发',NULL),(13,'测试客户角色','123123让士大夫🎟🎀🎡🎃',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=217 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色权限中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (188,4,5),(193,4,1),(194,4,2),(195,4,3),(213,13,1),(214,13,2),(215,13,3),(216,13,5);
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

-- Dump completed on 2021-05-21 12:51:27
