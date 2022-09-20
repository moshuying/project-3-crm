/*
 Navicat Premium Data Transfer

 Source Server         : crm-docker
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : crm3

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 20/09/2022 08:21:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for baobei
-- ----------------------------
DROP TABLE IF EXISTS `baobei`;
CREATE TABLE `baobei`  (
  `id` int NOT NULL,
  `product_id` int NULL DEFAULT NULL,
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `customer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `project_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述信息',
  `user_id` int NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_at` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of baobei
-- ----------------------------
INSERT INTO `baobei` VALUES (2, 2, '云盘', '心水电信公司', '云盘500个点，项目预计明年', 3, '李峰', '2022-08-29 23:46:48');
INSERT INTO `baobei` VALUES (3, 4, '摆渡机', '田园电动汽车', '70个点', 4, '凌风', '2022-08-29 23:48:46');

-- ----------------------------
-- Table structure for customer_follow_up_history
-- ----------------------------
DROP TABLE IF EXISTS `customer_follow_up_history`;
CREATE TABLE `customer_follow_up_history`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `traceTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '跟进时间',
  `traceDetails` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '跟进内容 计划的详细内容',
  `traceType` int NULL DEFAULT NULL COMMENT '跟进方式 计划采用如电话、邀约上门等  数据字典',
  `traceResult` int NULL DEFAULT NULL COMMENT '跟进效果 优----3、中----2、差----1',
  `customerID` int NULL DEFAULT NULL COMMENT '跟进客户 编辑时不可编辑 潜在客户对象/客户对象',
  `inputUser` int NULL DEFAULT NULL COMMENT '创建人 自动填入当前登录用户，用户不可更改 员工对象',
  `type` int NULL DEFAULT NULL COMMENT '跟进类型 0:潜在开发计划 1:客户跟进历史',
  `comment` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `customer_follow_up_history_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_follow_up_history
-- ----------------------------
INSERT INTO `customer_follow_up_history` VALUES (1, '2021-05-21 20:10:22', '还不错', 24, 3, 1, 5, 1, '阿迪斯发打发士大夫');
INSERT INTO `customer_follow_up_history` VALUES (2, '2021-05-21 21:05:46', '123', 24, 2, 1, 2, 0, '123123');
INSERT INTO `customer_follow_up_history` VALUES (3, '2021-05-21 21:06:46', '还可以', 24, 3, 7, 3, 0, '还不错');
INSERT INTO `customer_follow_up_history` VALUES (4, '2021-05-21 21:07:40', '123', 24, 2, 9, 4, 0, '13123');
INSERT INTO `customer_follow_up_history` VALUES (5, '2021-05-22 01:24:50', '还可以1', 24, 2, 1, 5, 0, '12313');
INSERT INTO `customer_follow_up_history` VALUES (6, '2021-05-01 08:00:17', '123123', 24, 1, 7, 1, 0, '拉了哭了');
INSERT INTO `customer_follow_up_history` VALUES (7, '2021-05-22 01:30:00', '1231', 24, 2, 7, 6, 1, '123123');
INSERT INTO `customer_follow_up_history` VALUES (8, '2021-05-22 01:31:11', '123132', 24, 2, 2, 1, 0, '123123');
INSERT INTO `customer_follow_up_history` VALUES (9, '2021-05-22 01:32:16', '1231', 24, 1, 4, 7, 0, '1231132');
INSERT INTO `customer_follow_up_history` VALUES (10, '2021-05-22 01:33:54', '123', 24, 1, 8, 9, 0, '123123');
INSERT INTO `customer_follow_up_history` VALUES (11, '2021-05-21 17:34:20', 'string', 0, 0, 0, 7, 0, 'string');
INSERT INTO `customer_follow_up_history` VALUES (12, '2021-05-22 01:36:53', '123123', 24, 1, 8, 6, 1, '德邦物流沟通不利');
INSERT INTO `customer_follow_up_history` VALUES (13, '2021-05-14 07:58:04', '哔哩哔哩八零八零八', 25, 3, 8, 2, 1, '叭叭叭粑粑');
INSERT INTO `customer_follow_up_history` VALUES (14, '2021-05-08 12:52:18', '234234', 26, 3, 3, 5, 1, '214143');
INSERT INTO `customer_follow_up_history` VALUES (15, '2021-05-23 02:37:17', '天天', 25, 2, 1, 1031, 0, '天天');
INSERT INTO `customer_follow_up_history` VALUES (16, '2021-05-23 02:47:09', '天天', 24, 2, 8, 1031, 0, 'UI');
INSERT INTO `customer_follow_up_history` VALUES (17, '2021-05-23 03:06:37', '天天', 24, 1, 3, 1031, 0, '21');

-- ----------------------------
-- Table structure for customer_handover
-- ----------------------------
DROP TABLE IF EXISTS `customer_handover`;
CREATE TABLE `customer_handover`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `customerID` int NULL DEFAULT NULL COMMENT '客户 客户对象',
  `transUser` int NULL DEFAULT NULL COMMENT '移交人员 实行移交操作的管理人员',
  `transTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `oldSeller` int NULL DEFAULT NULL COMMENT '老市场专员 客户上的原始市场人员',
  `newSeller` int NULL DEFAULT NULL COMMENT '新市场专员 由公司重新指派后的新市场人员',
  `transReason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '移交原因',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `customer_handover_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_handover
-- ----------------------------
INSERT INTO `customer_handover` VALUES (1, 7, 2, '2021-05-22 08:25:53', 2, 2, '下放任务');
INSERT INTO `customer_handover` VALUES (3, 1, 1, '2021-05-22 08:25:53', 5, 4, '123');
INSERT INTO `customer_handover` VALUES (4, 1, 1, '2021-05-22 08:25:53', 3, 2, '123');
INSERT INTO `customer_handover` VALUES (5, 1, 1, '2021-05-22 08:25:53', 4, 4, '123');
INSERT INTO `customer_handover` VALUES (6, 1, 1, '2021-05-22 08:25:53', 7, 1, '123');
INSERT INTO `customer_handover` VALUES (7, 1, 1, '2021-05-22 08:25:53', 9, 3, '123');
INSERT INTO `customer_handover` VALUES (8, 1, 1, '2021-05-22 08:25:53', 6, 3, '23423424');
INSERT INTO `customer_handover` VALUES (9, 1, 1, '2021-05-21 11:06:38', 3, 4, '123');
INSERT INTO `customer_handover` VALUES (10, 1, 1031, '2021-05-23 02:38:04', 4, 7, '天天天天');
INSERT INTO `customer_handover` VALUES (11, 1, 1031, '2021-05-23 02:38:14', 7, 3, '人员人员');
INSERT INTO `customer_handover` VALUES (12, 8, 1031, '2021-05-23 02:41:29', 7, 7, '让他');
INSERT INTO `customer_handover` VALUES (13, 12, 1031, '2021-05-23 02:43:15', 2, 7, '体验');
INSERT INTO `customer_handover` VALUES (14, 6, 5, '2021-05-23 02:44:34', 6, 9, '阿斯蒂芬');
INSERT INTO `customer_handover` VALUES (15, 15, 1031, '2021-05-23 02:46:45', 1031, 7, '一天');
INSERT INTO `customer_handover` VALUES (16, 3, 1, '2021-05-23 12:10:18', 3, 8, '任务');
INSERT INTO `customer_handover` VALUES (17, 4, 1092, '2021-05-24 08:33:16', 5, 1032, '123');

-- ----------------------------
-- Table structure for customer_manager
-- ----------------------------
DROP TABLE IF EXISTS `customer_manager`;
CREATE TABLE `customer_manager`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '客户姓名',
  `age` int NOT NULL COMMENT '客户年龄',
  `gender` int NOT NULL COMMENT '客户性别 页面为下拉框 1男 0女',
  `tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电话号码',
  `qq` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `job` int NOT NULL,
  `source` int NOT NULL COMMENT '客户来源',
  `seller` int NULL DEFAULT NULL COMMENT '负责人 填写为当前登录用户',
  `inputUser` int NOT NULL COMMENT ' 创建人 填写为当前登录用户',
  `inputTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `status` int NOT NULL DEFAULT 0 COMMENT '-2:流失 -1:开发失败 0:潜在客户 1:正式客户 2:资源池客户',
  `positiveTime` datetime(0) NULL DEFAULT NULL COMMENT '转正时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `customer_manager_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_manager
-- ----------------------------
INSERT INTO `customer_manager` VALUES (1, '秦农', 24, 1, '13766546213', '100001', 3, 17, 1, 1, '2021-05-24 07:15:25', 0, '2021-05-20 19:31:02');
INSERT INTO `customer_manager` VALUES (2, '马腾', 33, 1, '188888888', '100002', 3, 17, 2, 2, '2019-05-22 08:26:27', 2, '2021-05-20 19:31:02');
INSERT INTO `customer_manager` VALUES (3, '张云', 21, 1, '18888888888', '100001', 3, 17, 3, 3, '2021-05-23 03:07:02', 1, '2021-05-20 19:31:02');
INSERT INTO `customer_manager` VALUES (4, '权志龙', 18, 1, '18888888888', '100001', 3, 17, 5, 5, '2021-04-22 08:26:27', 2, '2021-05-20 19:31:02');
INSERT INTO `customer_manager` VALUES (5, '马钊', 23, 1, '16666666666', '100001', 3, 17, 4, 4, '2021-05-23 12:16:30', 1, '2021-05-20 19:31:02');
INSERT INTO `customer_manager` VALUES (6, '合理吗?🎃', 18, 0, '18888888888', '100001', 3, 17, 6, 6, '2021-05-21 03:56:13', -2, '2021-05-20 19:31:02');
INSERT INTO `customer_manager` VALUES (7, '酒剑仙🗡', 18, 1, '17777777777', '100001', 3, 17, 5, 5, '2021-05-22 08:26:27', 0, '2021-05-20 19:31:02');
INSERT INTO `customer_manager` VALUES (8, '赵', 21, 1, '18888888888', '100001', 3, 17, 7, 7, '2021-05-23 02:40:26', 1, '2021-05-20 19:31:02');
INSERT INTO `customer_manager` VALUES (9, '伊泽', 45, 0, '1999999999', '100001', 3, 17, 8, 8, '2021-05-23 08:26:27', 2, '2021-05-20 19:31:02');
INSERT INTO `customer_manager` VALUES (10, '阿斯顿', 18, 1, '18888888888', '100001', 3, 17, 9, 9, '2021-05-22 08:26:27', -1, '2021-05-20 19:31:02');
INSERT INTO `customer_manager` VALUES (11, '廖嘉积', 54, 0, '18888888888', '100001', 3, 17, 3, 3, '2021-05-22 08:26:27', -2, '2021-05-20 19:31:02');
INSERT INTO `customer_manager` VALUES (12, '郭晋安', 18, 1, '18888888888', '100001', 3, 17, 2, 2, '2021-05-22 08:26:27', 2, '2021-05-20 19:31:02');
INSERT INTO `customer_manager` VALUES (13, '埃里克森', 18, 1, '13333333333', '100002', 3, 17, 2, 2, '2021-05-22 08:26:27', 2, '2021-05-20 19:31:02');
INSERT INTO `customer_manager` VALUES (14, '张🗡男', 22, 1, '123123123', '123123', 28, 18, 2, 5, '2021-05-23 10:41:48', 0, NULL);
INSERT INTO `customer_manager` VALUES (15, '张华梁', 12, 1, '23444', '2123', 28, 20, 1, 1031, '2021-05-23 10:43:50', 1, NULL);
INSERT INTO `customer_manager` VALUES (16, '林阳露', 22, 0, '1234567', '12345678', 5, 21, 2, 1032, '2021-05-23 10:41:48', 0, NULL);
INSERT INTO `customer_manager` VALUES (17, '贵', 21, 1, '23444', '6532', 27, 18, 1, 1031, '2021-05-23 10:41:48', 0, NULL);
INSERT INTO `customer_manager` VALUES (18, '胡12', 123, 1, '123', '123', 28, 20, 1, 1, '2021-05-23 10:42:53', 0, NULL);

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `department_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (2, 'All Department Manager', '总经办');
INSERT INTO `department` VALUES (3, 'Human Resources Department', '人力资源部');
INSERT INTO `department` VALUES (5, 'Order Department', '采购部');
INSERT INTO `department` VALUES (6, 'Warehousing Department', '仓储部');
INSERT INTO `department` VALUES (7, 'Finance Department', '财务部');
INSERT INTO `department` VALUES (11, 'Publicity department', '宣传部门');
INSERT INTO `department` VALUES (17, 'Cultural Department🥼🧥👔👕', '文化部门');
INSERT INTO `department` VALUES (20, 'Testing department', '测试部门');
INSERT INTO `department` VALUES (31, 'Data center', '数据中心');
INSERT INTO `department` VALUES (32, 'Laboratory', '实验中心');
INSERT INTO `department` VALUES (33, '123333Quality inspection department', '质量校验部门');
INSERT INTO `department` VALUES (40, 'Financial Business Department', '金融事业部');
INSERT INTO `department` VALUES (41, 'Executive Department', '执行部');
INSERT INTO `department` VALUES (42, 'Training place', '培训部');
INSERT INTO `department` VALUES (43, 'Marketing Department', '市场部');
INSERT INTO `department` VALUES (44, 'Channel Location Division', '渠道选址事业部');
INSERT INTO `department` VALUES (45, 'Data Collection Department', '数据采集部');

-- ----------------------------
-- Table structure for dictionary_contents
-- ----------------------------
DROP TABLE IF EXISTS `dictionary_contents`;
CREATE TABLE `dictionary_contents`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典目录编号',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典目录名称',
  `intro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典目录简介',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `dictionary_contents_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dictionary_contents
-- ----------------------------
INSERT INTO `dictionary_contents` VALUES (1, 'job', '职业', '做什么的');
INSERT INTO `dictionary_contents` VALUES (2, 'source', '来源', '客户来源渠道');
INSERT INTO `dictionary_contents` VALUES (3, 'intentionDegree', '意向程度', '有多么想入坑');
INSERT INTO `dictionary_contents` VALUES (4, 'subject', '学科', '学科分类');
INSERT INTO `dictionary_contents` VALUES (5, 'Collection type', '收款类型', '学费收款方式');
INSERT INTO `dictionary_contents` VALUES (6, 'School nature', '办学性质', 'School nature');
INSERT INTO `dictionary_contents` VALUES (7, 'Customer importance', '客户重要程度', 'Customer importance');
INSERT INTO `dictionary_contents` VALUES (8, 'Foreign language proficiency', '外语水平', 'Foreign language proficiency');
INSERT INTO `dictionary_contents` VALUES (9, 'Career test', '职业测试', 'Career test');
INSERT INTO `dictionary_contents` VALUES (10, 'Follow-up method', '跟进方式', '客户跟进的方式');

-- ----------------------------
-- Table structure for dictionary_details
-- ----------------------------
DROP TABLE IF EXISTS `dictionary_details`;
CREATE TABLE `dictionary_details`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典明细名称',
  `sequence` int NULL DEFAULT NULL COMMENT '字典明细序号',
  `parentId` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX ` dictionary_details_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dictionary_details
-- ----------------------------
INSERT INTO `dictionary_details` VALUES (1, '教育学研究人员（GBM20104）', 2, 1);
INSERT INTO `dictionary_details` VALUES (2, '专业技术人员（GBM20000）', 1, 1);
INSERT INTO `dictionary_details` VALUES (3, '企业负责人（GBM10601）', 1, 1);
INSERT INTO `dictionary_details` VALUES (4, '党的机关、国家机关、群众团体和社会组织、企事业单位负责人（GBM10000）', 12, 1);
INSERT INTO `dictionary_details` VALUES (5, '教师', 3, 1);
INSERT INTO `dictionary_details` VALUES (8, '微信', 1, 2);
INSERT INTO `dictionary_details` VALUES (9, '抖音', 5, 2);
INSERT INTO `dictionary_details` VALUES (10, '微博', 7, 2);
INSERT INTO `dictionary_details` VALUES (11, '测试信息', 1, 3);
INSERT INTO `dictionary_details` VALUES (12, '支付宝', 1, 5);
INSERT INTO `dictionary_details` VALUES (13, '公立院校', 1, 6);
INSERT INTO `dictionary_details` VALUES (14, '重要', 1, 7);
INSERT INTO `dictionary_details` VALUES (15, '微信', 2, 5);
INSERT INTO `dictionary_details` VALUES (16, '私立院校', 2, 6);
INSERT INTO `dictionary_details` VALUES (17, 'QQ', 1, 2);
INSERT INTO `dictionary_details` VALUES (18, '街头小广告', 1, 2);
INSERT INTO `dictionary_details` VALUES (19, '头条号', 1, 2);
INSERT INTO `dictionary_details` VALUES (20, '微信公众号', 2, 2);
INSERT INTO `dictionary_details` VALUES (21, '报纸', 1, 2);
INSERT INTO `dictionary_details` VALUES (22, 'Bilibili', 4, 2);
INSERT INTO `dictionary_details` VALUES (23, '大学英语四级CET-4（四级）', 1, 8);
INSERT INTO `dictionary_details` VALUES (24, '营销QQ', 1, 10);
INSERT INTO `dictionary_details` VALUES (25, '营销微信', 1, 10);
INSERT INTO `dictionary_details` VALUES (26, '营销抖音', 1, 10);
INSERT INTO `dictionary_details` VALUES (27, '司机', 999, 1);
INSERT INTO `dictionary_details` VALUES (28, '编辑', 45, 1);
INSERT INTO `dictionary_details` VALUES (29, '办事人员和有关人员（GBM30000）', 999, 1);
INSERT INTO `dictionary_details` VALUES (30, '书信', 12, 10);
INSERT INTO `dictionary_details` VALUES (31, '中考英语分数', 2, 8);
INSERT INTO `dictionary_details` VALUES (32, '高考英语分数', 2, 8);
INSERT INTO `dictionary_details` VALUES (33, '大学英语六级CET-6（六级）', 2, 8);
INSERT INTO `dictionary_details` VALUES (34, '专业英语4级（专四）（TEM-4）', 3, 8);
INSERT INTO `dictionary_details` VALUES (35, '专业英语8级（专八）（TEM-8）', 2, 8);
INSERT INTO `dictionary_details` VALUES (36, '全国英语等级考试（PETS）', 3, 8);
INSERT INTO `dictionary_details` VALUES (37, '商务英语考试 (BEC)', 2, 8);
INSERT INTO `dictionary_details` VALUES (38, '翻译专业资格考试(CATTI)', 2, 8);
INSERT INTO `dictionary_details` VALUES (39, '上海外语口译证书', 2, 8);
INSERT INTO `dictionary_details` VALUES (40, '雅思(IELTS)', 1, 8);
INSERT INTO `dictionary_details` VALUES (41, '托福（TOEFL）', 1, 8);
INSERT INTO `dictionary_details` VALUES (42, '托业（TOEIC）', 1, 8);
INSERT INTO `dictionary_details` VALUES (43, '社会生产服务和生活服务人员（GBM40000）', 1, 1);
INSERT INTO `dictionary_details` VALUES (44, '其他批发与零售服务人员（GBM40199）', 1, 1);
INSERT INTO `dictionary_details` VALUES (45, '农、林、牧、渔业生产及辅助人员（GBM50000）', 1, 1);
INSERT INTO `dictionary_details` VALUES (46, '生产制造及有关人员（GBM60000）', 12, 1);
INSERT INTO `dictionary_details` VALUES (47, '军人GBM70000）', 1, 1);
INSERT INTO `dictionary_details` VALUES (48, '不便分类的其他从业人员（GBM80000）', 1, 1);
INSERT INTO `dictionary_details` VALUES (49, '宗教组织负责人（GBM10406）', 11, 1);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `age` int NULL DEFAULT NULL,
  `dept` int NOT NULL,
  `hireDate` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '入职时间',
  `state` int NOT NULL DEFAULT 1 COMMENT '状态 1正常 0离职',
  `admin` int NOT NULL DEFAULT 0 COMMENT '超级管理员身份 1超管 0普通',
  `login_time` datetime(0) NULL DEFAULT NULL,
  `register_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `employee_id_uindex`(`id`) USING BTREE,
  UNIQUE INDEX `employee_name_uindex`(`name`) USING BTREE,
  UNIQUE INDEX `employee_email_uindex`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1093 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 'admin', '$2a$10$OG1zaFHT2LUy4SGcQ4EnRu9sPQMjMGEE6jARz61aQwRQ3316N6ikG', '1623@163.com', 20, 2, '2021-05-14 00:28:00', 1, 1, '2021-05-26 14:00:55', '2021-05-21 08:46:19');
INSERT INTO `employee` VALUES (2, '肖总', '$2a$10$./YLhMGRhhqMwJOoxJGKYeuKsXehDyTt5C6Eq9CfAshnGWlPL8SNG', '163@163.com', 35, 43, '2021-05-16 01:19:51', 1, 1, '2021-05-21 16:46:24', '2021-05-21 08:46:25');
INSERT INTO `employee` VALUES (3, '赵一明', '$2a$10$/h22UTKprujOhSnaugy0/.dJHpNsox.OvPuzWCMMKoFm2FOrBurwO', 'g@gmail.com', 25, 3, '2021-05-16 01:22:38', 1, 1, '2021-05-21 16:46:27', '2021-05-21 08:46:27');
INSERT INTO `employee` VALUES (4, '刘九江', '$2a$10$4zNrZ/O1SsOcsFB6Hi9tPOGazrbU8dmV2igZaTxClNyQjONHDr3g2', 'msy@msy.plus', 14, 2, '2021-05-21 11:07:36', 1, 1, '2021-05-21 20:37:44', '2021-05-21 11:07:29');
INSERT INTO `employee` VALUES (5, '墨抒颖', '$2a$10$H5uwoLQIGQCmZpH98UCLbezAFKBcV6XxziDXH89JuAy2LBzspoGjO', 'msy.plus@qq.com', 101, 2, '2021-05-21 11:49:52', 1, 0, '2021-05-26 10:48:16', '2021-05-21 11:49:52');
INSERT INTO `employee` VALUES (6, 'Ralph V. Livengood', '123123', 'RalphVLivengood@gmail.com', 35, 6, '2021-05-18 06:49:32', 1, 0, '2021-05-21 16:46:28', '2021-05-21 08:46:29');
INSERT INTO `employee` VALUES (7, 'Lauren C. Young', '123333', 'LaurenCYoung@gmail.com', 33, 7, '2021-05-18 07:10:31', 1, 0, '2021-05-21 16:46:29', '2021-05-21 08:46:30');
INSERT INTO `employee` VALUES (8, '钟汉良', '123333', 'zhl@outlook.com', 35, 6, '2021-05-18 07:11:19', 1, 0, '2021-05-21 16:46:31', '2021-05-21 08:46:31');
INSERT INTO `employee` VALUES (9, '陈乔恩', '$2a$10$meRc5DPOldNhSMJ3O61bAejjYrh9.0RCA4C7v5Vtg8ws7/Tci10hu', 'c@qq.com', 23, 2, '2021-05-21 11:34:48', 1, 0, NULL, '2021-05-21 11:34:48');
INSERT INTO `employee` VALUES (1031, '宋佳鑫', '$2a$10$enbn9aSc32x8o4a3mMdI0eMY2S1DIO6f70NIVhJGV0qix5JQSKaUy', 'songjiaxin@qq.com', 18, 2, '2021-05-23 01:49:58', 1, 1, '2021-05-23 10:18:02', '2021-05-23 01:49:58');
INSERT INTO `employee` VALUES (1032, '沈瑞渊', '$2a$10$XCpj.stZ0YXnUjIyRRhzReVEX.XcPPr7fXnm0T3A0LjnWspRy6mcW', 'iosfgjksdkgkldsjfgl@qq.cp', 22, 2, '2021-05-23 02:07:37', 1, 0, '2021-05-23 10:27:10', '2021-05-23 02:07:37');
INSERT INTO `employee` VALUES (1091, '诺基亚', '$2a$10$99f1zjjDOwsaeGcP8Qn4Bu10zPsDEA1FTHC7nFpKSX81bht3fEwh2', '7231083332@qq.com', 18, 2, '2021-05-24 00:57:42', 1, 0, NULL, '2021-05-24 00:57:42');
INSERT INTO `employee` VALUES (1092, '马♥', '$2a$10$Er9G1wdLAv5CD9t0BGll8uaicn1TPuTKd1ALH88Yy9U/dLFN8wFSS', '1460234233332@qq.com', 18, 7, '2021-05-24 03:39:34', 1, 0, '2021-05-24 16:22:58', '2021-05-24 03:39:34');

-- ----------------------------
-- Table structure for employee_role
-- ----------------------------
DROP TABLE IF EXISTS `employee_role`;
CREATE TABLE `employee_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `employeeId` int NULL DEFAULT NULL,
  `roleId` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `employee_role_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 166 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee_role
-- ----------------------------
INSERT INTO `employee_role` VALUES (14, 1001, 4);
INSERT INTO `employee_role` VALUES (15, 1001, 5);
INSERT INTO `employee_role` VALUES (16, 1001, 8);
INSERT INTO `employee_role` VALUES (17, 1001, 9);
INSERT INTO `employee_role` VALUES (18, 1002, 4);
INSERT INTO `employee_role` VALUES (19, 1002, 5);
INSERT INTO `employee_role` VALUES (20, 1002, 8);
INSERT INTO `employee_role` VALUES (21, 1002, 9);
INSERT INTO `employee_role` VALUES (22, 1000, 4);
INSERT INTO `employee_role` VALUES (23, 1000, 5);
INSERT INTO `employee_role` VALUES (24, 1000, 8);
INSERT INTO `employee_role` VALUES (25, 1000, 9);
INSERT INTO `employee_role` VALUES (39, 1, 2);
INSERT INTO `employee_role` VALUES (40, 1, 4);
INSERT INTO `employee_role` VALUES (44, 1010, 8);
INSERT INTO `employee_role` VALUES (45, 1011, 5);
INSERT INTO `employee_role` VALUES (49, 1013, 5);
INSERT INTO `employee_role` VALUES (50, 1013, 4);
INSERT INTO `employee_role` VALUES (51, 1016, 5);
INSERT INTO `employee_role` VALUES (52, 1016, 4);
INSERT INTO `employee_role` VALUES (53, 1015, 8);
INSERT INTO `employee_role` VALUES (54, 1015, 5);
INSERT INTO `employee_role` VALUES (55, 1015, 4);
INSERT INTO `employee_role` VALUES (57, 4, 1);
INSERT INTO `employee_role` VALUES (58, 4, 2);
INSERT INTO `employee_role` VALUES (59, 4, 3);
INSERT INTO `employee_role` VALUES (60, NULL, 1);
INSERT INTO `employee_role` VALUES (61, NULL, 2);
INSERT INTO `employee_role` VALUES (62, NULL, 3);
INSERT INTO `employee_role` VALUES (63, NULL, 1);
INSERT INTO `employee_role` VALUES (64, NULL, 1);
INSERT INTO `employee_role` VALUES (65, NULL, 1);
INSERT INTO `employee_role` VALUES (66, NULL, 1);
INSERT INTO `employee_role` VALUES (67, NULL, 1);
INSERT INTO `employee_role` VALUES (68, 1018, 1);
INSERT INTO `employee_role` VALUES (69, 1030, 1);
INSERT INTO `employee_role` VALUES (70, 1030, 2);
INSERT INTO `employee_role` VALUES (71, 1030, 3);
INSERT INTO `employee_role` VALUES (76, 1031, 2);
INSERT INTO `employee_role` VALUES (77, 1031, 1);
INSERT INTO `employee_role` VALUES (78, 1031, 3);
INSERT INTO `employee_role` VALUES (79, 1032, 2);
INSERT INTO `employee_role` VALUES (84, 1031, 4);
INSERT INTO `employee_role` VALUES (85, 1031, 5);
INSERT INTO `employee_role` VALUES (86, 1031, 7);
INSERT INTO `employee_role` VALUES (87, 1031, 8);
INSERT INTO `employee_role` VALUES (88, 1031, 9);
INSERT INTO `employee_role` VALUES (89, 1031, 10);
INSERT INTO `employee_role` VALUES (90, 1031, 11);
INSERT INTO `employee_role` VALUES (91, 1031, 12);
INSERT INTO `employee_role` VALUES (92, 1031, 13);
INSERT INTO `employee_role` VALUES (93, 1031, 15);
INSERT INTO `employee_role` VALUES (94, 1031, 17);
INSERT INTO `employee_role` VALUES (95, 1031, 18);
INSERT INTO `employee_role` VALUES (98, 2, 7);
INSERT INTO `employee_role` VALUES (109, 9, 42);
INSERT INTO `employee_role` VALUES (110, 7, 37);
INSERT INTO `employee_role` VALUES (111, 6, 42);
INSERT INTO `employee_role` VALUES (112, 8, 42);
INSERT INTO `employee_role` VALUES (113, 3, 42);
INSERT INTO `employee_role` VALUES (118, 1032, 1);
INSERT INTO `employee_role` VALUES (160, 1092, 1);
INSERT INTO `employee_role` VALUES (161, 1091, 42);
INSERT INTO `employee_role` VALUES (165, 5, 42);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限名称',
  `expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '资源地址',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `permission_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '客户列表', 'customer:list');
INSERT INTO `permission` VALUES (2, '客户状态修改', 'customer:changeStatus');
INSERT INTO `permission` VALUES (3, '客户新增修改', 'customer:saveOrUpdate');
INSERT INTO `permission` VALUES (5, '客户池列表', 'customerPool:list');
INSERT INTO `permission` VALUES (6, '跟进历史列表', 'followHistory:list');
INSERT INTO `permission` VALUES (7, '跟进历史新增/修改', 'followHistory:saveOrUpdate');
INSERT INTO `permission` VALUES (8, '移交历史列表', 'transferHistory:list');
INSERT INTO `permission` VALUES (9, '移交历史新增/修改', 'transferHistory:saveOrUpdate');
INSERT INTO `permission` VALUES (10, '部门列表', 'department:list');
INSERT INTO `permission` VALUES (11, '部门删除', 'department:delete');
INSERT INTO `permission` VALUES (12, '部门新增/修改', 'department:addOrUpdate');
INSERT INTO `permission` VALUES (13, '员工删除', 'employee:delete');
INSERT INTO `permission` VALUES (14, '员工列表', 'employee:list');
INSERT INTO `permission` VALUES (15, '员工编辑', 'employee:edit');
INSERT INTO `permission` VALUES (16, '员工批量删除', 'employee:deleteMultiple');
INSERT INTO `permission` VALUES (21, '客户角色管理角色新增', '12');
INSERT INTO `permission` VALUES (23, '角色列表', 'role:list');
INSERT INTO `permission` VALUES (24, '角色删除', 'role:delete');
INSERT INTO `permission` VALUES (27, '角色新增/修改', 'role:addOrUpdate');
INSERT INTO `permission` VALUES (28, '数据字典列表', 'dictionaryContents:list');
INSERT INTO `permission` VALUES (29, '数据列表添加/修改', 'dictionaryContents:addOrUpdate');
INSERT INTO `permission` VALUES (30, '字典明细列表', 'dictionaryDetails:list');
INSERT INTO `permission` VALUES (31, '字典明细新增/修改', 'dictionaryDetails:addOrUpdate');
INSERT INTO `permission` VALUES (32, '客户管理列表', 'CM:list');
INSERT INTO `permission` VALUES (33, '客户管理新增/修改', 'CM:addOrUpdate');
INSERT INTO `permission` VALUES (34, '跟进历史新增/修改', 'CF:addOrUpdate');
INSERT INTO `permission` VALUES (35, '客户移交新增', 'CH:add');
INSERT INTO `permission` VALUES (36, '跟进历史列表', 'CH:list');
INSERT INTO `permission` VALUES (37, '统计分析列表', 'statisticalAnalysis:list');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色Id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `sn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '董事长', 'Chairman of the board');
INSERT INTO `role` VALUES (2, 'ADMIN', 'System administrator');
INSERT INTO `role` VALUES (3, '主席', 'Chairman');
INSERT INTO `role` VALUES (4, '高级主席', 'Senior Chairman');
INSERT INTO `role` VALUES (5, '副主席', 'Vice Chairman');
INSERT INTO `role` VALUES (7, '总裁', 'Chairman');
INSERT INTO `role` VALUES (8, '会长', 'President');
INSERT INTO `role` VALUES (9, '高级总裁', 'Senior President');
INSERT INTO `role` VALUES (10, '高级副总裁', 'Senior Vice President');
INSERT INTO `role` VALUES (11, '副总裁', 'Vice president');
INSERT INTO `role` VALUES (12, '总经理', 'General manager');
INSERT INTO `role` VALUES (13, '副总经理', 'Deputy General Manager');
INSERT INTO `role` VALUES (22, '总监', 'Director');
INSERT INTO `role` VALUES (27, '经理', 'Manager');
INSERT INTO `role` VALUES (28, '高级经理', 'Senior Manager');
INSERT INTO `role` VALUES (36, '副经理', 'Deputy manager');
INSERT INTO `role` VALUES (37, '主任', 'Director');
INSERT INTO `role` VALUES (38, '高级主任', 'Senior Director');
INSERT INTO `role` VALUES (39, '副主任', 'Deputy director');
INSERT INTO `role` VALUES (40, '组长', 'Group leader');
INSERT INTO `role` VALUES (41, '副组长', 'Deputy head');
INSERT INTO `role` VALUES (42, '普通员工', 'Worker');
INSERT INTO `role` VALUES (45, '人事专员', 'Personnel Specialist');
INSERT INTO `role` VALUES (46, '市场专员', 'Marketing Specialist');
INSERT INTO `role` VALUES (47, '市场主管', 'Marketing Director');
INSERT INTO `role` VALUES (48, '销售主管', 'Sales Executive');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL COMMENT '角色id',
  `permission_id` int NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_permission_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 415 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权限中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (188, 4, 5);
INSERT INTO `role_permission` VALUES (193, 4, 1);
INSERT INTO `role_permission` VALUES (194, 4, 2);
INSERT INTO `role_permission` VALUES (195, 4, 3);
INSERT INTO `role_permission` VALUES (213, 13, 1);
INSERT INTO `role_permission` VALUES (214, 13, 2);
INSERT INTO `role_permission` VALUES (215, 13, 3);
INSERT INTO `role_permission` VALUES (216, 13, 5);
INSERT INTO `role_permission` VALUES (217, 2, 1);
INSERT INTO `role_permission` VALUES (218, 2, 2);
INSERT INTO `role_permission` VALUES (219, 2, 3);
INSERT INTO `role_permission` VALUES (220, 2, 5);
INSERT INTO `role_permission` VALUES (221, 2, 6);
INSERT INTO `role_permission` VALUES (222, 2, 7);
INSERT INTO `role_permission` VALUES (223, 2, 8);
INSERT INTO `role_permission` VALUES (224, 2, 9);
INSERT INTO `role_permission` VALUES (225, 2, 10);
INSERT INTO `role_permission` VALUES (226, 2, 11);
INSERT INTO `role_permission` VALUES (227, 2, 12);
INSERT INTO `role_permission` VALUES (228, 2, 13);
INSERT INTO `role_permission` VALUES (229, 2, 14);
INSERT INTO `role_permission` VALUES (230, 2, 15);
INSERT INTO `role_permission` VALUES (231, 2, 16);
INSERT INTO `role_permission` VALUES (256, 36, 9);
INSERT INTO `role_permission` VALUES (257, 37, 8);
INSERT INTO `role_permission` VALUES (258, 1, 1);
INSERT INTO `role_permission` VALUES (259, 1, 2);
INSERT INTO `role_permission` VALUES (260, 1, 3);
INSERT INTO `role_permission` VALUES (261, 1, 5);
INSERT INTO `role_permission` VALUES (262, 1, 6);
INSERT INTO `role_permission` VALUES (263, 1, 7);
INSERT INTO `role_permission` VALUES (264, 1, 8);
INSERT INTO `role_permission` VALUES (265, 1, 9);
INSERT INTO `role_permission` VALUES (266, 1, 10);
INSERT INTO `role_permission` VALUES (267, 1, 11);
INSERT INTO `role_permission` VALUES (268, 1, 12);
INSERT INTO `role_permission` VALUES (269, 1, 13);
INSERT INTO `role_permission` VALUES (270, 1, 14);
INSERT INTO `role_permission` VALUES (271, 1, 15);
INSERT INTO `role_permission` VALUES (272, 1, 16);
INSERT INTO `role_permission` VALUES (273, 1, 21);
INSERT INTO `role_permission` VALUES (274, 2, 21);
INSERT INTO `role_permission` VALUES (275, 3, 1);
INSERT INTO `role_permission` VALUES (276, 3, 2);
INSERT INTO `role_permission` VALUES (277, 3, 3);
INSERT INTO `role_permission` VALUES (278, 3, 5);
INSERT INTO `role_permission` VALUES (279, 3, 6);
INSERT INTO `role_permission` VALUES (280, 3, 7);
INSERT INTO `role_permission` VALUES (281, 3, 8);
INSERT INTO `role_permission` VALUES (282, 3, 9);
INSERT INTO `role_permission` VALUES (283, 3, 10);
INSERT INTO `role_permission` VALUES (284, 3, 11);
INSERT INTO `role_permission` VALUES (285, 3, 12);
INSERT INTO `role_permission` VALUES (286, 3, 13);
INSERT INTO `role_permission` VALUES (287, 3, 14);
INSERT INTO `role_permission` VALUES (288, 3, 15);
INSERT INTO `role_permission` VALUES (289, 3, 16);
INSERT INTO `role_permission` VALUES (290, 3, 21);
INSERT INTO `role_permission` VALUES (291, 5, 1);
INSERT INTO `role_permission` VALUES (292, 5, 2);
INSERT INTO `role_permission` VALUES (293, 5, 3);
INSERT INTO `role_permission` VALUES (294, 5, 5);
INSERT INTO `role_permission` VALUES (295, 5, 6);
INSERT INTO `role_permission` VALUES (296, 4, 16);
INSERT INTO `role_permission` VALUES (297, 4, 21);
INSERT INTO `role_permission` VALUES (298, 4, 6);
INSERT INTO `role_permission` VALUES (299, 4, 7);
INSERT INTO `role_permission` VALUES (300, 4, 8);
INSERT INTO `role_permission` VALUES (301, 4, 9);
INSERT INTO `role_permission` VALUES (302, 4, 10);
INSERT INTO `role_permission` VALUES (303, 4, 11);
INSERT INTO `role_permission` VALUES (304, 4, 12);
INSERT INTO `role_permission` VALUES (305, 4, 13);
INSERT INTO `role_permission` VALUES (306, 4, 14);
INSERT INTO `role_permission` VALUES (307, 4, 15);
INSERT INTO `role_permission` VALUES (308, 5, 16);
INSERT INTO `role_permission` VALUES (309, 5, 21);
INSERT INTO `role_permission` VALUES (310, 5, 7);
INSERT INTO `role_permission` VALUES (311, 5, 8);
INSERT INTO `role_permission` VALUES (312, 5, 9);
INSERT INTO `role_permission` VALUES (313, 5, 10);
INSERT INTO `role_permission` VALUES (314, 5, 11);
INSERT INTO `role_permission` VALUES (315, 5, 12);
INSERT INTO `role_permission` VALUES (316, 5, 13);
INSERT INTO `role_permission` VALUES (317, 5, 14);
INSERT INTO `role_permission` VALUES (318, 5, 15);
INSERT INTO `role_permission` VALUES (319, 7, 1);
INSERT INTO `role_permission` VALUES (320, 7, 2);
INSERT INTO `role_permission` VALUES (321, 7, 3);
INSERT INTO `role_permission` VALUES (322, 7, 5);
INSERT INTO `role_permission` VALUES (323, 7, 6);
INSERT INTO `role_permission` VALUES (324, 7, 7);
INSERT INTO `role_permission` VALUES (325, 7, 8);
INSERT INTO `role_permission` VALUES (326, 7, 9);
INSERT INTO `role_permission` VALUES (327, 7, 10);
INSERT INTO `role_permission` VALUES (328, 7, 11);
INSERT INTO `role_permission` VALUES (329, 7, 12);
INSERT INTO `role_permission` VALUES (330, 7, 13);
INSERT INTO `role_permission` VALUES (331, 7, 14);
INSERT INTO `role_permission` VALUES (332, 7, 15);
INSERT INTO `role_permission` VALUES (333, 7, 16);
INSERT INTO `role_permission` VALUES (334, 7, 21);
INSERT INTO `role_permission` VALUES (335, 8, 1);
INSERT INTO `role_permission` VALUES (336, 8, 2);
INSERT INTO `role_permission` VALUES (337, 8, 3);
INSERT INTO `role_permission` VALUES (338, 8, 5);
INSERT INTO `role_permission` VALUES (339, 8, 6);
INSERT INTO `role_permission` VALUES (340, 8, 7);
INSERT INTO `role_permission` VALUES (341, 8, 8);
INSERT INTO `role_permission` VALUES (342, 8, 9);
INSERT INTO `role_permission` VALUES (343, 8, 10);
INSERT INTO `role_permission` VALUES (344, 8, 11);
INSERT INTO `role_permission` VALUES (345, 8, 12);
INSERT INTO `role_permission` VALUES (346, 8, 13);
INSERT INTO `role_permission` VALUES (347, 8, 14);
INSERT INTO `role_permission` VALUES (348, 8, 15);
INSERT INTO `role_permission` VALUES (349, 8, 16);
INSERT INTO `role_permission` VALUES (350, 8, 21);
INSERT INTO `role_permission` VALUES (351, 12, 1);
INSERT INTO `role_permission` VALUES (352, 12, 2);
INSERT INTO `role_permission` VALUES (353, 12, 3);
INSERT INTO `role_permission` VALUES (354, 12, 5);
INSERT INTO `role_permission` VALUES (355, 12, 6);
INSERT INTO `role_permission` VALUES (356, 12, 7);
INSERT INTO `role_permission` VALUES (357, 12, 8);
INSERT INTO `role_permission` VALUES (358, 12, 9);
INSERT INTO `role_permission` VALUES (359, 12, 10);
INSERT INTO `role_permission` VALUES (360, 12, 11);
INSERT INTO `role_permission` VALUES (361, 12, 12);
INSERT INTO `role_permission` VALUES (362, 12, 13);
INSERT INTO `role_permission` VALUES (363, 12, 14);
INSERT INTO `role_permission` VALUES (364, 12, 15);
INSERT INTO `role_permission` VALUES (365, 12, 16);
INSERT INTO `role_permission` VALUES (366, 12, 21);
INSERT INTO `role_permission` VALUES (367, 38, 9);
INSERT INTO `role_permission` VALUES (368, 39, 3);
INSERT INTO `role_permission` VALUES (369, 40, 9);
INSERT INTO `role_permission` VALUES (370, 40, 8);
INSERT INTO `role_permission` VALUES (371, 41, 9);
INSERT INTO `role_permission` VALUES (375, 45, 13);
INSERT INTO `role_permission` VALUES (376, 45, 14);
INSERT INTO `role_permission` VALUES (377, 45, 16);
INSERT INTO `role_permission` VALUES (378, 45, 15);
INSERT INTO `role_permission` VALUES (379, 42, 1);
INSERT INTO `role_permission` VALUES (380, 42, 2);
INSERT INTO `role_permission` VALUES (381, 42, 3);
INSERT INTO `role_permission` VALUES (382, 42, 5);
INSERT INTO `role_permission` VALUES (383, 42, 7);
INSERT INTO `role_permission` VALUES (384, 46, 3);
INSERT INTO `role_permission` VALUES (385, 46, 1);
INSERT INTO `role_permission` VALUES (386, 47, 1);
INSERT INTO `role_permission` VALUES (387, 47, 2);
INSERT INTO `role_permission` VALUES (388, 2, 32);
INSERT INTO `role_permission` VALUES (389, 2, 33);
INSERT INTO `role_permission` VALUES (390, 2, 34);
INSERT INTO `role_permission` VALUES (391, 2, 35);
INSERT INTO `role_permission` VALUES (392, 2, 36);
INSERT INTO `role_permission` VALUES (393, 2, 37);
INSERT INTO `role_permission` VALUES (394, 2, 23);
INSERT INTO `role_permission` VALUES (395, 2, 24);
INSERT INTO `role_permission` VALUES (396, 2, 27);
INSERT INTO `role_permission` VALUES (397, 2, 28);
INSERT INTO `role_permission` VALUES (398, 2, 29);
INSERT INTO `role_permission` VALUES (399, 2, 30);
INSERT INTO `role_permission` VALUES (400, 2, 31);
INSERT INTO `role_permission` VALUES (401, 1, 32);
INSERT INTO `role_permission` VALUES (402, 1, 33);
INSERT INTO `role_permission` VALUES (403, 1, 34);
INSERT INTO `role_permission` VALUES (404, 1, 35);
INSERT INTO `role_permission` VALUES (405, 1, 36);
INSERT INTO `role_permission` VALUES (406, 1, 37);
INSERT INTO `role_permission` VALUES (407, 1, 23);
INSERT INTO `role_permission` VALUES (408, 1, 24);
INSERT INTO `role_permission` VALUES (409, 1, 27);
INSERT INTO `role_permission` VALUES (410, 1, 28);
INSERT INTO `role_permission` VALUES (411, 1, 29);
INSERT INTO `role_permission` VALUES (412, 1, 30);
INSERT INTO `role_permission` VALUES (413, 1, 31);
INSERT INTO `role_permission` VALUES (414, 48, 36);

SET FOREIGN_KEY_CHECKS = 1;
