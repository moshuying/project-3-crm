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
-- Table structure for table `customer_follow_up_history`
--

DROP TABLE IF EXISTS `customer_follow_up_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_follow_up_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `traceTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '跟进时间',
  `traceDetails` text COMMENT '跟进内容 计划的详细内容',
  `traceType` int DEFAULT NULL COMMENT '跟进方式 计划采用如电话、邀约上门等  数据字典',
  `traceResult` int DEFAULT NULL COMMENT '跟进效果 优----3、中----2、差----1',
  `customerID` int DEFAULT NULL COMMENT '跟进客户 编辑时不可编辑 潜在客户对象/客户对象',
  `inputUser` int DEFAULT NULL COMMENT '创建人 自动填入当前登录用户，用户不可更改 员工对象',
  `type` int DEFAULT NULL COMMENT '跟进类型 0:潜在开发计划 1:客户跟进历史',
  `comment` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `customer_follow_up_history_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_follow_up_history`
--

LOCK TABLES `customer_follow_up_history` WRITE;
/*!40000 ALTER TABLE `customer_follow_up_history` DISABLE KEYS */;
INSERT INTO `customer_follow_up_history` VALUES (1,'2021-05-21 20:10:22','还不错',24,3,1,5,1,'阿迪斯发打发士大夫'),(2,'2021-05-21 21:05:46','123',24,2,1,2,0,'123123'),(3,'2021-05-21 21:06:46','还可以',24,3,7,3,0,'还不错'),(4,'2021-05-21 21:07:40','123',24,2,9,4,0,'13123'),(5,'2021-05-22 01:24:50','还可以1',24,2,1,5,0,'12313'),(6,'2021-05-01 08:00:17','123123',24,1,7,1,0,'拉了哭了'),(7,'2021-05-22 01:30:00','1231',24,2,7,6,1,'123123'),(8,'2021-05-22 01:31:11','123132',24,2,2,1,0,'123123'),(9,'2021-05-22 01:32:16','1231',24,1,4,7,0,'1231132'),(10,'2021-05-22 01:33:54','123',24,1,8,9,0,'123123'),(11,'2021-05-21 17:34:20','string',0,0,0,7,0,'string'),(12,'2021-05-22 01:36:53','123123',24,1,8,6,1,'德邦物流沟通不利'),(13,'2021-05-14 07:58:04','哔哩哔哩八零八零八',25,3,8,2,1,'叭叭叭粑粑'),(14,'2021-05-08 12:52:18','234234',26,3,3,5,1,'214143'),(15,'2021-05-23 02:37:17','天天',25,2,1,1031,0,'天天'),(16,'2021-05-23 02:47:09','天天',24,2,8,1031,0,'UI'),(17,'2021-05-23 03:06:37','天天',24,1,3,1031,0,'21');
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
  `transTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `oldSeller` int DEFAULT NULL COMMENT '老市场专员 客户上的原始市场人员',
  `newSeller` int DEFAULT NULL COMMENT '新市场专员 由公司重新指派后的新市场人员',
  `transReason` varchar(255) DEFAULT NULL COMMENT '移交原因',
  PRIMARY KEY (`id`),
  UNIQUE KEY `customer_handover_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_handover`
--

LOCK TABLES `customer_handover` WRITE;
/*!40000 ALTER TABLE `customer_handover` DISABLE KEYS */;
INSERT INTO `customer_handover` VALUES (1,7,2,'2021-05-22 08:25:53',2,2,'下放任务'),(3,1,1,'2021-05-22 08:25:53',5,4,'123'),(4,1,1,'2021-05-22 08:25:53',3,2,'123'),(5,1,1,'2021-05-22 08:25:53',4,4,'123'),(6,1,1,'2021-05-22 08:25:53',7,1,'123'),(7,1,1,'2021-05-22 08:25:53',9,3,'123'),(8,1,1,'2021-05-22 08:25:53',6,3,'23423424'),(9,1,1,'2021-05-21 11:06:38',3,4,'123'),(10,1,1031,'2021-05-23 02:38:04',4,7,'天天天天'),(11,1,1031,'2021-05-23 02:38:14',7,3,'人员人员'),(12,8,1031,'2021-05-23 02:41:29',7,7,'让他'),(13,12,1031,'2021-05-23 02:43:15',2,7,'体验'),(14,6,5,'2021-05-23 02:44:34',6,9,'阿斯蒂芬'),(15,15,1031,'2021-05-23 02:46:45',1031,7,'一天'),(16,3,1,'2021-05-23 12:10:18',3,8,'任务'),(17,4,1092,'2021-05-24 08:33:16',5,1032,'123');
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
  `inputUser` int NOT NULL COMMENT ' 创建人 填写为当前登录用户',
  `inputTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int NOT NULL DEFAULT '0' COMMENT '-2:流失 -1:开发失败 0:潜在客户 1:正式客户 2:资源池客户',
  `positiveTime` datetime DEFAULT NULL COMMENT '转正时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `customer_manager_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_manager`
--

LOCK TABLES `customer_manager` WRITE;
/*!40000 ALTER TABLE `customer_manager` DISABLE KEYS */;
INSERT INTO `customer_manager` VALUES (1,'秦农',24,1,'13766546213','100001',3,17,1,1,'2021-05-24 07:15:25',0,'2021-05-20 19:31:02'),(2,'马化腾',33,1,'18888888888','100002',3,17,2,2,'2019-05-22 08:26:27',2,'2021-05-20 19:31:02'),(3,'张云',21,1,'18888888888','100001',3,17,3,3,'2021-05-23 03:07:02',1,'2021-05-20 19:31:02'),(4,'权志龙',18,1,'18888888888','100001',3,17,5,5,'2021-04-22 08:26:27',2,'2021-05-20 19:31:02'),(5,'马钊',23,1,'16666666666','100001',3,17,4,4,'2021-05-23 12:16:30',1,'2021-05-20 19:31:02'),(6,'合理吗?🎃',18,0,'18888888888','100001',3,17,6,6,'2021-05-21 03:56:13',-2,'2021-05-20 19:31:02'),(7,'酒剑仙🗡',18,1,'17777777777','100001',3,17,5,5,'2021-05-22 08:26:27',0,'2021-05-20 19:31:02'),(8,'赵',21,1,'18888888888','100001',3,17,7,7,'2021-05-23 02:40:26',1,'2021-05-20 19:31:02'),(9,'伊泽',45,0,'1999999999','100001',3,17,8,8,'2021-05-23 08:26:27',2,'2021-05-20 19:31:02'),(10,'阿斯顿',18,1,'18888888888','100001',3,17,9,9,'2021-05-22 08:26:27',-1,'2021-05-20 19:31:02'),(11,'廖嘉积',54,0,'18888888888','100001',3,17,3,3,'2021-05-22 08:26:27',-2,'2021-05-20 19:31:02'),(12,'郭晋安',18,1,'18888888888','100001',3,17,2,2,'2021-05-22 08:26:27',2,'2021-05-20 19:31:02'),(13,'埃里克森',18,1,'13333333333','100002',3,17,2,2,'2021-05-22 08:26:27',2,'2021-05-20 19:31:02'),(14,'张🗡男',22,1,'123123123','123123',28,18,2,5,'2021-05-23 10:41:48',0,NULL),(15,'张华梁',12,1,'23444','2123',28,20,1,1031,'2021-05-23 10:43:50',1,NULL),(16,'林阳露',22,0,'1234567','12345678',5,21,2,1032,'2021-05-23 10:41:48',0,NULL),(17,'贵',21,1,'23444','6532',27,18,1,1031,'2021-05-23 10:41:48',0,NULL),(18,'胡12',123,1,'123','123',28,20,1,1,'2021-05-23 10:42:53',0,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (2,'All Department Manager','总经办'),(3,'Human Resources Department','人力资源部'),(5,'Order Department','采购部'),(6,'Warehousing Department','仓储部'),(7,'Finance Department','财务部'),(11,'Publicity department','宣传部门'),(17,'Cultural Department🥼🧥👔👕','文化部门'),(20,'Testing department','测试部门'),(31,'Data center','数据中心'),(32,'Laboratory','实验中心'),(33,'123333Quality inspection department','质量校验部门'),(40,'Financial Business Department','金融事业部'),(41,'Executive Department','执行部'),(42,'Training place','培训部'),(43,'Marketing Department','市场部'),(44,'Channel Location Division','渠道选址事业部'),(45,'Data Collection Department','数据采集部');
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dictionary_contents`
--

LOCK TABLES `dictionary_contents` WRITE;
/*!40000 ALTER TABLE `dictionary_contents` DISABLE KEYS */;
INSERT INTO `dictionary_contents` VALUES (1,'job','职业','做什么的'),(2,'source','来源','客户来源渠道'),(3,'intentionDegree','意向程度','有多么想入坑'),(4,'subject','学科','学科分类'),(5,'Collection type','收款类型','学费收款方式'),(6,'School nature','办学性质','School nature'),(7,'Customer importance','客户重要程度','Customer importance'),(8,'Foreign language proficiency','外语水平','Foreign language proficiency'),(9,'Career test','职业测试','Career test'),(10,'Follow-up method','跟进方式','客户跟进的方式');
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
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dictionary_details`
--

LOCK TABLES `dictionary_details` WRITE;
/*!40000 ALTER TABLE `dictionary_details` DISABLE KEYS */;
INSERT INTO `dictionary_details` VALUES (1,'教育学研究人员（GBM20104）',2,1),(2,'专业技术人员（GBM20000）',1,1),(3,'企业负责人（GBM10601）',1,1),(4,'党的机关、国家机关、群众团体和社会组织、企事业单位负责人（GBM10000）',12,1),(5,'教师',3,1),(8,'微信',1,2),(9,'抖音',5,2),(10,'微博',7,2),(11,'测试信息',1,3),(12,'支付宝',1,5),(13,'公立院校',1,6),(14,'重要',1,7),(15,'微信',2,5),(16,'私立院校',2,6),(17,'QQ',1,2),(18,'街头小广告',1,2),(19,'头条号',1,2),(20,'微信公众号',2,2),(21,'报纸',1,2),(22,'Bilibili',4,2),(23,'大学英语四级CET-4（四级）',1,8),(24,'营销QQ',1,10),(25,'营销微信',1,10),(26,'营销抖音',1,10),(27,'司机',999,1),(28,'编辑',45,1),(29,'办事人员和有关人员（GBM30000）',999,1),(30,'书信',12,10),(31,'中考英语分数',2,8),(32,'高考英语分数',2,8),(33,'大学英语六级CET-6（六级）',2,8),(34,'专业英语4级（专四）（TEM-4）',3,8),(35,'专业英语8级（专八）（TEM-8）',2,8),(36,'全国英语等级考试（PETS）',3,8),(37,'商务英语考试 (BEC)',2,8),(38,'翻译专业资格考试(CATTI)',2,8),(39,'上海外语口译证书',2,8),(40,'雅思(IELTS)',1,8),(41,'托福（TOEFL）',1,8),(42,'托业（TOEIC）',1,8),(43,'社会生产服务和生活服务人员（GBM40000）',1,1),(44,'其他批发与零售服务人员（GBM40199）',1,1),(45,'农、林、牧、渔业生产及辅助人员（GBM50000）',1,1),(46,'生产制造及有关人员（GBM60000）',12,1),(47,'军人GBM70000）',1,1),(48,'不便分类的其他从业人员（GBM80000）',1,1),(49,'宗教组织负责人（GBM10406）',11,1);
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
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `age` int DEFAULT NULL,
  `dept` int NOT NULL,
  `hireDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入职时间',
  `state` int NOT NULL DEFAULT '1' COMMENT '状态 1正常 0离职',
  `admin` int NOT NULL DEFAULT '0' COMMENT '超级管理员身份 1超管 0普通',
  `login_time` datetime DEFAULT NULL,
  `register_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `employee_id_uindex` (`id`),
  UNIQUE KEY `employee_name_uindex` (`name`),
  UNIQUE KEY `employee_email_uindex` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1093 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'admin','$2a$10$OG1zaFHT2LUy4SGcQ4EnRu9sPQMjMGEE6jARz61aQwRQ3316N6ikG','1623@163.com',20,2,'2021-05-14 00:28:00',1,1,'2021-05-26 14:00:55','2021-05-21 08:46:19'),(2,'肖总','$2a$10$./YLhMGRhhqMwJOoxJGKYeuKsXehDyTt5C6Eq9CfAshnGWlPL8SNG','163@163.com',35,43,'2021-05-16 01:19:51',1,1,'2021-05-21 16:46:24','2021-05-21 08:46:25'),(3,'赵一明','$2a$10$/h22UTKprujOhSnaugy0/.dJHpNsox.OvPuzWCMMKoFm2FOrBurwO','g@gmail.com',25,3,'2021-05-16 01:22:38',1,1,'2021-05-21 16:46:27','2021-05-21 08:46:27'),(4,'刘九江','$2a$10$4zNrZ/O1SsOcsFB6Hi9tPOGazrbU8dmV2igZaTxClNyQjONHDr3g2','msy@msy.plus',14,2,'2021-05-21 11:07:36',1,1,'2021-05-21 20:37:44','2021-05-21 11:07:29'),(5,'墨抒颖','$2a$10$H5uwoLQIGQCmZpH98UCLbezAFKBcV6XxziDXH89JuAy2LBzspoGjO','msy.plus@qq.com',101,2,'2021-05-21 11:49:52',1,0,'2021-05-26 10:48:16','2021-05-21 11:49:52'),(6,'Ralph V. Livengood','123123','RalphVLivengood@gmail.com',35,6,'2021-05-18 06:49:32',1,0,'2021-05-21 16:46:28','2021-05-21 08:46:29'),(7,'Lauren C. Young','123333','LaurenCYoung@gmail.com',33,7,'2021-05-18 07:10:31',1,0,'2021-05-21 16:46:29','2021-05-21 08:46:30'),(8,'钟汉良','123333','zhl@outlook.com',35,6,'2021-05-18 07:11:19',1,0,'2021-05-21 16:46:31','2021-05-21 08:46:31'),(9,'陈乔恩','$2a$10$meRc5DPOldNhSMJ3O61bAejjYrh9.0RCA4C7v5Vtg8ws7/Tci10hu','c@qq.com',23,2,'2021-05-21 11:34:48',1,0,NULL,'2021-05-21 11:34:48'),(1031,'宋佳鑫','$2a$10$enbn9aSc32x8o4a3mMdI0eMY2S1DIO6f70NIVhJGV0qix5JQSKaUy','songjiaxin@qq.com',18,2,'2021-05-23 01:49:58',1,1,'2021-05-23 10:18:02','2021-05-23 01:49:58'),(1032,'沈瑞渊','$2a$10$XCpj.stZ0YXnUjIyRRhzReVEX.XcPPr7fXnm0T3A0LjnWspRy6mcW','iosfgjksdkgkldsjfgl@qq.cp',22,2,'2021-05-23 02:07:37',1,0,'2021-05-23 10:27:10','2021-05-23 02:07:37'),(1091,'诺基亚','$2a$10$99f1zjjDOwsaeGcP8Qn4Bu10zPsDEA1FTHC7nFpKSX81bht3fEwh2','7231083332@qq.com',18,2,'2021-05-24 00:57:42',1,0,NULL,'2021-05-24 00:57:42'),(1092,'马♥','$2a$10$Er9G1wdLAv5CD9t0BGll8uaicn1TPuTKd1ALH88Yy9U/dLFN8wFSS','1460234233332@qq.com',18,7,'2021-05-24 03:39:34',1,0,'2021-05-24 16:22:58','2021-05-24 03:39:34');
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
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_role`
--

LOCK TABLES `employee_role` WRITE;
/*!40000 ALTER TABLE `employee_role` DISABLE KEYS */;
INSERT INTO `employee_role` VALUES (14,1001,4),(15,1001,5),(16,1001,8),(17,1001,9),(18,1002,4),(19,1002,5),(20,1002,8),(21,1002,9),(22,1000,4),(23,1000,5),(24,1000,8),(25,1000,9),(39,1,2),(40,1,4),(44,1010,8),(45,1011,5),(49,1013,5),(50,1013,4),(51,1016,5),(52,1016,4),(53,1015,8),(54,1015,5),(55,1015,4),(57,4,1),(58,4,2),(59,4,3),(60,NULL,1),(61,NULL,2),(62,NULL,3),(63,NULL,1),(64,NULL,1),(65,NULL,1),(66,NULL,1),(67,NULL,1),(68,1018,1),(69,1030,1),(70,1030,2),(71,1030,3),(76,1031,2),(77,1031,1),(78,1031,3),(79,1032,2),(84,1031,4),(85,1031,5),(86,1031,7),(87,1031,8),(88,1031,9),(89,1031,10),(90,1031,11),(91,1031,12),(92,1031,13),(93,1031,15),(94,1031,17),(95,1031,18),(98,2,7),(109,9,42),(110,7,37),(111,6,42),(112,8,42),(113,3,42),(118,1032,1),(160,1092,1),(161,1091,42),(165,5,42);
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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'客户列表','customer:list'),(2,'客户状态修改','customer:changeStatus'),(3,'客户新增修改','customer:saveOrUpdate'),(5,'客户池列表','customerPool:list'),(6,'跟进历史列表','followHistory:list'),(7,'跟进历史新增/修改','followHistory:saveOrUpdate'),(8,'移交历史列表','transferHistory:list'),(9,'移交历史新增/修改','transferHistory:saveOrUpdate'),(10,'部门列表','department:list'),(11,'部门删除','department:delete'),(12,'部门新增/修改','department:addOrUpdate'),(13,'员工删除','employee:delete'),(14,'员工列表','employee:list'),(15,'员工编辑','employee:edit'),(16,'员工批量删除','employee:deleteMultiple'),(21,'客户角色管理角色新增','12'),(23,'角色列表','role:list'),(24,'角色删除','role:delete'),(27,'角色新增/修改','role:addOrUpdate'),(28,'数据字典列表','dictionaryContents:list'),(29,'数据列表添加/修改','dictionaryContents:addOrUpdate'),(30,'字典明细列表','dictionaryDetails:list'),(31,'字典明细新增/修改','dictionaryDetails:addOrUpdate'),(32,'客户管理列表','CM:list'),(33,'客户管理新增/修改','CM:addOrUpdate'),(34,'跟进历史新增/修改','CF:addOrUpdate'),(35,'客户移交新增','CH:add'),(36,'跟进历史列表','CH:list'),(37,'统计分析列表','statisticalAnalysis:list');
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'董事长','Chairman of the board'),(2,'ADMIN','System administrator'),(3,'主席','Chairman'),(4,'高级主席','Senior Chairman'),(5,'副主席','Vice Chairman'),(7,'总裁','Chairman'),(8,'会长','President'),(9,'高级总裁','Senior President'),(10,'高级副总裁','Senior Vice President'),(11,'副总裁','Vice president'),(12,'总经理','General manager'),(13,'副总经理','Deputy General Manager'),(22,'总监','Director'),(27,'经理','Manager'),(28,'高级经理','Senior Manager'),(36,'副经理','Deputy manager'),(37,'主任','Director'),(38,'高级主任','Senior Director'),(39,'副主任','Deputy director'),(40,'组长','Group leader'),(41,'副组长','Deputy head'),(42,'普通员工','Worker'),(45,'人事专员','Personnel Specialist'),(46,'市场专员','Marketing Specialist'),(47,'市场主管','Marketing Director'),(48,'销售主管','Sales Executive');
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
) ENGINE=InnoDB AUTO_INCREMENT=415 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色权限中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (188,4,5),(193,4,1),(194,4,2),(195,4,3),(213,13,1),(214,13,2),(215,13,3),(216,13,5),(217,2,1),(218,2,2),(219,2,3),(220,2,5),(221,2,6),(222,2,7),(223,2,8),(224,2,9),(225,2,10),(226,2,11),(227,2,12),(228,2,13),(229,2,14),(230,2,15),(231,2,16),(256,36,9),(257,37,8),(258,1,1),(259,1,2),(260,1,3),(261,1,5),(262,1,6),(263,1,7),(264,1,8),(265,1,9),(266,1,10),(267,1,11),(268,1,12),(269,1,13),(270,1,14),(271,1,15),(272,1,16),(273,1,21),(274,2,21),(275,3,1),(276,3,2),(277,3,3),(278,3,5),(279,3,6),(280,3,7),(281,3,8),(282,3,9),(283,3,10),(284,3,11),(285,3,12),(286,3,13),(287,3,14),(288,3,15),(289,3,16),(290,3,21),(291,5,1),(292,5,2),(293,5,3),(294,5,5),(295,5,6),(296,4,16),(297,4,21),(298,4,6),(299,4,7),(300,4,8),(301,4,9),(302,4,10),(303,4,11),(304,4,12),(305,4,13),(306,4,14),(307,4,15),(308,5,16),(309,5,21),(310,5,7),(311,5,8),(312,5,9),(313,5,10),(314,5,11),(315,5,12),(316,5,13),(317,5,14),(318,5,15),(319,7,1),(320,7,2),(321,7,3),(322,7,5),(323,7,6),(324,7,7),(325,7,8),(326,7,9),(327,7,10),(328,7,11),(329,7,12),(330,7,13),(331,7,14),(332,7,15),(333,7,16),(334,7,21),(335,8,1),(336,8,2),(337,8,3),(338,8,5),(339,8,6),(340,8,7),(341,8,8),(342,8,9),(343,8,10),(344,8,11),(345,8,12),(346,8,13),(347,8,14),(348,8,15),(349,8,16),(350,8,21),(351,12,1),(352,12,2),(353,12,3),(354,12,5),(355,12,6),(356,12,7),(357,12,8),(358,12,9),(359,12,10),(360,12,11),(361,12,12),(362,12,13),(363,12,14),(364,12,15),(365,12,16),(366,12,21),(367,38,9),(368,39,3),(369,40,9),(370,40,8),(371,41,9),(375,45,13),(376,45,14),(377,45,16),(378,45,15),(379,42,1),(380,42,2),(381,42,3),(382,42,5),(383,42,7),(384,46,3),(385,46,1),(386,47,1),(387,47,2),(388,2,32),(389,2,33),(390,2,34),(391,2,35),(392,2,36),(393,2,37),(394,2,23),(395,2,24),(396,2,27),(397,2,28),(398,2,29),(399,2,30),(400,2,31),(401,1,32),(402,1,33),(403,1,34),(404,1,35),(405,1,36),(406,1,37),(407,1,23),(408,1,24),(409,1,27),(410,1,28),(411,1,29),(412,1,30),(413,1,31),(414,48,36);
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

-- Dump completed on 2021-05-26 19:23:20
