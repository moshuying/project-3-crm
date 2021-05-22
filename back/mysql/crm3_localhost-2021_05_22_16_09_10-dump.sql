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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_follow_up_history`
--

LOCK TABLES `customer_follow_up_history` WRITE;
/*!40000 ALTER TABLE `customer_follow_up_history` DISABLE KEYS */;
INSERT INTO `customer_follow_up_history` VALUES (1,'2021-05-21 20:10:22','还不错',24,3,1,5,1,'阿迪斯发打发士大夫'),(2,'2021-05-21 21:05:46','123',24,2,1,1,0,'123123'),(3,'2021-05-21 21:06:46','还可以',24,3,7,1,0,'还不错'),(4,'2021-05-21 21:07:40','123',24,2,9,1,0,'13123'),(5,'2021-05-22 01:24:50','还可以1',24,2,1,1,0,'12313'),(6,'2021-05-01 08:00:17','123123',24,1,7,1,0,'拉了哭了'),(7,'2021-05-22 01:30:00','1231',24,2,7,1,1,'123123'),(8,'2021-05-22 01:31:11','123132',24,2,2,1,0,'123123'),(9,'2021-05-22 01:32:16','1231',24,1,4,1,0,'1231132'),(10,'2021-05-22 01:33:54','123',24,1,8,1,0,'123123'),(11,'2021-05-21 17:34:20','string',0,0,0,1,0,'string'),(12,'2021-05-22 01:36:53','123123',24,1,8,1,1,'德邦物流沟通不利'),(13,'2021-05-14 07:58:04','哔哩哔哩八零八零八',25,3,8,5,1,'叭叭叭粑粑'),(14,'2021-05-08 12:52:18','234234',26,3,3,5,1,'214143');
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_handover`
--

LOCK TABLES `customer_handover` WRITE;
/*!40000 ALTER TABLE `customer_handover` DISABLE KEYS */;
INSERT INTO `customer_handover` VALUES (1,7,2,'2021-05-21 05:18:31',1,2,'下放任务'),(3,1,1,'2021-05-21 09:25:06',1,4,'123'),(4,1,1,'2021-05-21 09:25:06',1,2,'123'),(5,1,1,'2021-05-21 09:25:06',1,4,'123'),(6,1,1,'2021-05-21 09:25:06',1,1,'123'),(7,1,1,'2021-05-21 09:25:06',1,3,'123'),(8,1,1,'2021-05-21 09:25:33',1,3,'23423424'),(9,1,1,'2021-05-21 11:06:38',3,4,'123');
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
  `status` int NOT NULL COMMENT '-2:流失 -1:开发失败 0:潜在客户 1:正式客户 2:资源池客户',
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
INSERT INTO `customer_manager` VALUES (1,'🐎❀🐍',18,1,'18888888888','100001',3,17,1,1,'2021-05-21 03:56:12',0,'2021-05-20 19:31:02'),(2,'马化腾',33,1,'18888888888','100002',3,17,1,1,'2021-05-20 13:21:56',2,'2021-05-20 19:31:02'),(3,'张云',18,1,'18888888888','100001',3,17,1,1,'2021-05-20 13:21:56',-1,'2021-05-20 19:31:02'),(4,'权志龙',18,1,'18888888888','100001',3,17,1,1,'2021-05-20 13:21:56',2,'2021-05-20 19:31:02'),(5,'马钊',23,1,'16666666666','100001',3,17,1,1,'2021-05-20 13:21:56',-1,'2021-05-20 19:31:02'),(6,'合理吗?🎃',18,0,'18888888888','100001',3,17,1,1,'2021-05-20 13:21:56',-2,'2021-05-20 19:31:02'),(7,'酒剑仙🗡',18,1,'17777777777','100001',3,17,1,1,'2021-05-21 05:59:39',0,'2021-05-20 19:31:02'),(8,'赵信',54,1,'18888888888','100001',3,17,1,1,'2021-05-20 12:55:08',1,'2021-05-20 19:31:02'),(9,'伊泽',45,0,'1999999999','100001',3,17,1,1,'2021-05-20 13:21:56',2,'2021-05-20 19:31:02'),(10,'阿斯顿',18,1,'18888888888','100001',3,17,1,1,'2021-05-20 13:21:56',-1,'2021-05-20 19:31:02'),(11,'廖嘉积',54,0,'18888888888','100001',3,17,1,1,'2021-05-20 13:21:56',-2,'2021-05-20 19:31:02'),(12,'郭晋安',18,1,'18888888888','100001',3,17,1,1,'2021-05-20 13:21:56',2,'2021-05-20 19:31:02');
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dictionary_contents`
--

LOCK TABLES `dictionary_contents` WRITE;
/*!40000 ALTER TABLE `dictionary_contents` DISABLE KEYS */;
INSERT INTO `dictionary_contents` VALUES (1,'job','职业','做什么的'),(2,'source','来源','客户来源渠道'),(3,'intentionDegree','意向程度','有多么想入坑'),(4,'subject','学科','学科分类'),(5,'size','收款类型','学费收款方式'),(6,NULL,'办学性质',NULL),(7,NULL,'客户重要程度',NULL),(8,'1','外语水平','123132'),(9,'123','职业测试','123'),(10,'123','跟进方式','客户跟进的方式');
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dictionary_details`
--

LOCK TABLES `dictionary_details` WRITE;
/*!40000 ALTER TABLE `dictionary_details` DISABLE KEYS */;
INSERT INTO `dictionary_details` VALUES (1,'老师',2,1),(2,'司机',1,1),(3,'老板',1,1),(4,'学生',1,1),(5,'秘书2',3,1),(8,'微信',1,2),(9,'抖音',5,2),(10,'微博',7,2),(11,'测试信息',1,3),(12,'支付宝',1,5),(13,'公立院校',1,6),(14,'重要',1,7),(15,'微信',2,5),(16,'私立院校',2,6),(17,'QQ',1,2),(18,'街头小广告',1,2),(19,'头条号',1,2),(20,'微信公众号',2,2),(21,'报纸',1,2),(22,'Bilibili',4,2),(23,'微博2',123,8),(24,'营销QQ',1,10),(25,'营销微信',1,10),(26,'营销抖音',1,10);
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
) ENGINE=InnoDB AUTO_INCREMENT=1031 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'admin','$2a$10$OG1zaFHT2LUy4SGcQ4EnRu9sPQMjMGEE6jARz61aQwRQ3316N6ikG','1623@163.com',20,2,'2021-05-14 00:28:00',1,1,'2021-05-22 10:37:56','2021-05-21 08:46:19'),(2,'赵总','123','163@163.com',35,6,'2021-05-16 01:19:51',1,1,'2021-05-21 16:46:24','2021-05-21 08:46:25'),(3,'赵一明','123','g@gmail.com',25,1,'2021-05-16 01:22:38',1,1,'2021-05-21 16:46:27','2021-05-21 08:46:27'),(4,'刘九江','$2a$10$4zNrZ/O1SsOcsFB6Hi9tPOGazrbU8dmV2igZaTxClNyQjONHDr3g2','msy@msy.plus',999,2,'2021-05-21 11:07:36',1,1,'2021-05-21 20:37:44','2021-05-21 11:07:29'),(5,'墨抒颖','$2a$10$H5uwoLQIGQCmZpH98UCLbezAFKBcV6XxziDXH89JuAy2LBzspoGjO','msy.plus@qq.com',23,2,'2021-05-21 11:49:52',1,0,'2021-05-22 15:44:58','2021-05-21 11:49:52'),(6,'刘憨憨没有心','123123','moshuyingnod2e@gmail.com',35,6,'2021-05-18 06:49:32',1,0,'2021-05-21 16:46:28','2021-05-21 08:46:29'),(7,'失败了的刘某','123333','moshuyingnode@gmail.com',33,7,'2021-05-18 07:10:31',1,0,'2021-05-21 16:46:29','2021-05-21 08:46:30'),(8,'成功了的刘总','123333','fox-td@outlook.com',35,6,'2021-05-18 07:11:19',1,0,'2021-05-21 16:46:31','2021-05-21 08:46:31'),(9,'123','$2a$10$meRc5DPOldNhSMJ3O61bAejjYrh9.0RCA4C7v5Vtg8ws7/Tci10hu','1460083332@qq.com',23,2,'2021-05-21 11:34:48',1,0,NULL,'2021-05-21 11:34:48');
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
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_role`
--

LOCK TABLES `employee_role` WRITE;
/*!40000 ALTER TABLE `employee_role` DISABLE KEYS */;
INSERT INTO `employee_role` VALUES (4,3,4),(5,3,4),(6,3,8),(14,1001,4),(15,1001,5),(16,1001,8),(17,1001,9),(18,1002,4),(19,1002,5),(20,1002,8),(21,1002,9),(22,1000,4),(23,1000,5),(24,1000,8),(25,1000,9),(39,1,2),(40,1,4),(44,1010,8),(45,1011,5),(49,1013,5),(50,1013,4),(51,1016,5),(52,1016,4),(53,1015,8),(54,1015,5),(55,1015,4),(56,2,3),(57,4,1),(58,4,2),(59,4,3),(60,NULL,1),(61,NULL,2),(62,NULL,3),(63,NULL,1),(64,NULL,1),(65,NULL,1),(66,NULL,1),(67,NULL,1),(68,1018,1),(69,1030,1),(70,1030,2),(71,1030,3),(72,5,1),(73,5,2),(74,5,3),(75,5,4);
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
INSERT INTO `role` VALUES (1,'USER','342',2),(2,'ADMIN','234',23),(3,'TEST','234',234),(4,'市场经理','Market Manager',1),(5,'市场人员','123🎨🧵🎨',324),(7,'123123','123333🎨🖼🛒123123',234),(8,'测试部门','123123让士大夫',234),(9,'阿斯蒂芬','阿斯蒂芬2为人',234),(10,'却认为撒士大夫','as地方请问父亲为请问父亲为',234),(11,'全微分q\'d','qds发士大夫阿斯顿',234),(12,'啊手动阀手动阀as','f阿斯蒂芬阿斯顿发',234),(13,'测试客户角色','123123让士大夫🎟🎀🎡🎃',234);
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

-- Dump completed on 2021-05-22 16:09:10
