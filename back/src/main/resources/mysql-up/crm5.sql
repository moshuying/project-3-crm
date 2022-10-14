/*
 Navicat Premium Data Transfer

 Source Server         : crm
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : 127.0.0.1:3306
 Source Schema         : crm4

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 12/10/2022 18:51:13
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
  `create_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '作废，测试用的表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of baobei
-- ----------------------------
INSERT INTO `baobei` VALUES (2, 2, '云盘', '心水电信公司', '云盘500个点，项目预计明年', 3, '李峰', '2022-08-29 23:46:48');
INSERT INTO `baobei` VALUES (3, 4, '摆渡机', '田园电动汽车', '70个点', 4, '凌风', '2022-08-29 23:48:46');

-- ----------------------------
-- Table structure for bizs
-- ----------------------------
DROP TABLE IF EXISTS `bizs`;
CREATE TABLE `bizs`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `contact_id` int NULL DEFAULT NULL COMMENT '联系人id,微信第二次进来通过openid获取',
  `contact_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人名字',
  `contact_ent_id` int NULL DEFAULT NULL COMMENT '联系人所属企业id，关联后才有',
  `contact_tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报备人手机号码',
  `customer_ent_id` int NULL DEFAULT NULL COMMENT '如果报备客户存在显示',
  `biz_ent_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '最终报备客户名称',
  `biz_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户商机描述, 根据这个配备资源',
  `biz_stage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商机阶段，消息提醒判断',
  `product_id` int NULL DEFAULT NULL COMMENT '报备产品',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '冗余产品名称（冗余）',
  `product_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品详情描述',
  `employee_id` int NULL DEFAULT NULL COMMENT '对口业务经理',
  `employee_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '业务经理姓名（冗余，用于查询）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商机管理表，管理商机报备，内外报备，提醒跟进等' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bizs
-- ----------------------------
INSERT INTO `bizs` VALUES (1, NULL, NULL, NULL, NULL, NULL, '中国的公司', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-10-10 20:17:24');
INSERT INTO `bizs` VALUES (2, 5, '王君子', NULL, NULL, NULL, '中国的公司', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-10-10 20:19:33');
INSERT INTO `bizs` VALUES (3, 5, '王君子', NULL, NULL, NULL, '中国的公司', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-10-10 20:21:50');

-- ----------------------------
-- Table structure for customer_enterprise
-- ----------------------------
DROP TABLE IF EXISTS `customer_enterprise`;
CREATE TABLE `customer_enterprise`  (
  `ent_id` int NOT NULL AUTO_INCREMENT,
  `ent_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '企业名称',
  `ent_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '企业地址',
  `ent_verify` int NULL DEFAULT NULL COMMENT '企业验证状态：业务人员验证',
  `ent_cooperation_type` int NULL DEFAULT NULL COMMENT '企业与厂家合同类型：1、直接客户、2、渠道代理、3、供应商、4、战略合作、5、其他合作形式',
  `ent_website` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '企业官网',
  `ent_belong_bizer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '该企业属于哪个销售人员负责',
  `ent_info_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '企业工商信息截图',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人名字',
  PRIMARY KEY (`ent_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of customer_enterprise
-- ----------------------------

-- ----------------------------
-- Table structure for customer_follow_up_history
-- ----------------------------
DROP TABLE IF EXISTS `customer_follow_up_history`;
CREATE TABLE `customer_follow_up_history`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `traceTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '跟进时间',
  `traceDetails` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '跟进内容 计划的详细内容',
  `traceType` int NULL DEFAULT NULL COMMENT '跟进方式 计划采用如电话、邀约上门等  数据字典',
  `traceResult` int NULL DEFAULT NULL COMMENT '跟进效果 优----3、中----2、差----1',
  `customerID` int NULL DEFAULT NULL COMMENT '跟进客户 编辑时不可编辑 潜在客户对象/客户对象',
  `inputUser` int NULL DEFAULT NULL COMMENT '创建人 自动填入当前登录用户，用户不可更改 员工对象',
  `type` int NULL DEFAULT NULL COMMENT '跟进类型 0:潜在开发计划 1:客户跟进历史',
  `comment` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `trace_biz_mode` int NULL DEFAULT NULL COMMENT '\'跟进业务模块:(业务、技术、财务、商务、其他)\'',
  `customer_ent_id` int NULL DEFAULT NULL COMMENT '\'跟进属于哪个企业',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `customer_follow_up_history_id_uindex`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of customer_follow_up_history
-- ----------------------------
INSERT INTO `customer_follow_up_history` VALUES (1, '2021-05-21 20:10:22', '还不错', 24, 3, 1, 5, 1, '阿迪斯发打发士大夫', NULL, NULL);
INSERT INTO `customer_follow_up_history` VALUES (2, '2021-05-21 21:05:46', '123', 24, 2, 1, 2, 0, '123123', NULL, NULL);
INSERT INTO `customer_follow_up_history` VALUES (3, '2021-05-21 21:06:46', '还可以', 24, 3, 7, 3, 0, '还不错', NULL, NULL);
INSERT INTO `customer_follow_up_history` VALUES (4, '2021-05-21 21:07:40', '123', 24, 2, 9, 4, 0, '13123', NULL, NULL);
INSERT INTO `customer_follow_up_history` VALUES (5, '2021-05-22 01:24:50', '还可以1', 24, 2, 1, 5, 0, '12313', NULL, NULL);
INSERT INTO `customer_follow_up_history` VALUES (6, '2021-05-01 08:00:17', '123123', 24, 1, 7, 1, 0, '拉了哭了', NULL, NULL);
INSERT INTO `customer_follow_up_history` VALUES (7, '2021-05-22 01:30:00', '1231', 24, 2, 7, 6, 1, '123123', NULL, NULL);
INSERT INTO `customer_follow_up_history` VALUES (8, '2021-05-22 01:31:11', '123132', 24, 2, 2, 1, 0, '123123', NULL, NULL);
INSERT INTO `customer_follow_up_history` VALUES (9, '2021-05-22 01:32:16', '1231', 24, 1, 4, 7, 0, '1231132', NULL, NULL);
INSERT INTO `customer_follow_up_history` VALUES (10, '2021-05-22 01:33:54', '123', 24, 1, 8, 9, 0, '123123', NULL, NULL);
INSERT INTO `customer_follow_up_history` VALUES (11, '2021-05-21 17:34:20', 'string', 0, 0, 0, 7, 0, 'string', NULL, NULL);
INSERT INTO `customer_follow_up_history` VALUES (12, '2021-05-22 01:36:53', '123123', 24, 1, 8, 6, 1, '德邦物流沟通不利', NULL, NULL);
INSERT INTO `customer_follow_up_history` VALUES (13, '2021-05-14 07:58:04', '哔哩哔哩八零八零八', 25, 3, 8, 2, 1, '叭叭叭粑粑', NULL, NULL);
INSERT INTO `customer_follow_up_history` VALUES (14, '2021-05-08 12:52:18', '234234', 26, 3, 3, 5, 1, '214143', NULL, NULL);
INSERT INTO `customer_follow_up_history` VALUES (15, '2021-05-23 02:37:17', '天天', 25, 2, 1, 1031, 0, '天天', NULL, NULL);
INSERT INTO `customer_follow_up_history` VALUES (16, '2021-05-23 02:47:09', '天天', 24, 2, 8, 1031, 0, 'UI', NULL, NULL);
INSERT INTO `customer_follow_up_history` VALUES (17, '2021-05-23 03:06:37', '天天', 24, 1, 3, 1031, 0, '21', NULL, NULL);

-- ----------------------------
-- Table structure for customer_handover
-- ----------------------------
DROP TABLE IF EXISTS `customer_handover`;
CREATE TABLE `customer_handover`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `customerID` int NULL DEFAULT NULL COMMENT '客户 客户对象',
  `transUser` int NULL DEFAULT NULL COMMENT '移交人员 实行移交操作的管理人员',
  `transTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `oldSeller` int NULL DEFAULT NULL COMMENT '老市场专员 客户上的原始市场人员',
  `newSeller` int NULL DEFAULT NULL COMMENT '新市场专员 由公司重新指派后的新市场人员',
  `transReason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '移交原因',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `customer_handover_id_uindex`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

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
  `inputTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int NOT NULL DEFAULT 0 COMMENT '-2:流失 -1:开发失败 0:潜在客户 1:正式客户 2:资源池客户',
  `positiveTime` datetime NULL DEFAULT NULL COMMENT '转正时间',
  `customer_ent_id` int NULL DEFAULT NULL COMMENT '客户企业id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `customer_manager_id_uindex`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of customer_manager
-- ----------------------------
INSERT INTO `customer_manager` VALUES (1, '秦农', 24, 1, '13766546213', '100001', 3, 17, 1, 1, '2021-05-24 07:15:25', 0, '2021-05-20 19:31:02', NULL);
INSERT INTO `customer_manager` VALUES (2, '马腾', 33, 1, '188888888', '100002', 3, 17, 2, 2, '2019-05-22 08:26:27', 2, '2021-05-20 19:31:02', NULL);
INSERT INTO `customer_manager` VALUES (3, '张云', 21, 1, '18888888888', '100001', 3, 17, 3, 3, '2021-05-23 03:07:02', 1, '2021-05-20 19:31:02', NULL);
INSERT INTO `customer_manager` VALUES (4, '权志龙', 18, 1, '18888888888', '100001', 3, 17, 5, 5, '2021-04-22 08:26:27', 2, '2021-05-20 19:31:02', NULL);
INSERT INTO `customer_manager` VALUES (5, '马钊', 23, 1, '16666666666', '100001', 3, 17, 4, 4, '2021-05-23 12:16:30', 1, '2021-05-20 19:31:02', NULL);
INSERT INTO `customer_manager` VALUES (6, '合理吗?🎃', 18, 0, '18888888888', '100001', 3, 17, 6, 6, '2021-05-21 03:56:13', -2, '2021-05-20 19:31:02', NULL);
INSERT INTO `customer_manager` VALUES (7, '酒剑仙🗡', 18, 1, '17777777777', '100001', 3, 17, 5, 5, '2021-05-22 08:26:27', 0, '2021-05-20 19:31:02', NULL);
INSERT INTO `customer_manager` VALUES (8, '赵', 21, 1, '18888888888', '100001', 3, 17, 7, 7, '2021-05-23 02:40:26', 1, '2021-05-20 19:31:02', NULL);
INSERT INTO `customer_manager` VALUES (9, '伊泽', 45, 0, '1999999999', '100001', 3, 17, 8, 8, '2021-05-23 08:26:27', 2, '2021-05-20 19:31:02', NULL);
INSERT INTO `customer_manager` VALUES (10, '阿斯顿', 18, 1, '18888888888', '100001', 3, 17, 9, 9, '2021-05-22 08:26:27', -1, '2021-05-20 19:31:02', NULL);
INSERT INTO `customer_manager` VALUES (11, '廖嘉积', 54, 0, '18888888888', '100001', 3, 17, 3, 3, '2021-05-22 08:26:27', -2, '2021-05-20 19:31:02', NULL);
INSERT INTO `customer_manager` VALUES (12, '郭晋安', 18, 1, '18888888888', '100001', 3, 17, 2, 2, '2021-05-22 08:26:27', 2, '2021-05-20 19:31:02', NULL);
INSERT INTO `customer_manager` VALUES (13, '埃里克森', 18, 1, '13333333333', '100002', 3, 17, 2, 2, '2021-05-22 08:26:27', 2, '2021-05-20 19:31:02', NULL);
INSERT INTO `customer_manager` VALUES (14, '张🗡男', 22, 1, '123123123', '123123', 28, 18, 2, 5, '2021-05-23 10:41:48', 0, NULL, NULL);
INSERT INTO `customer_manager` VALUES (15, '张华梁', 12, 1, '23444', '2123', 28, 20, 1, 1031, '2021-05-23 10:43:50', 1, NULL, NULL);
INSERT INTO `customer_manager` VALUES (16, '林阳露', 22, 0, '1234567', '12345678', 5, 21, 2, 1032, '2021-05-23 10:41:48', 0, NULL, NULL);
INSERT INTO `customer_manager` VALUES (17, '贵', 21, 1, '23444', '6532', 27, 18, 1, 1031, '2021-05-23 10:41:48', 0, NULL, NULL);
INSERT INTO `customer_manager` VALUES (18, '胡12', 123, 1, '123', '123', 28, 20, 1, 1, '2021-05-23 10:42:53', 0, NULL, NULL);

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `parent_id` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '父部门id，默认顶级部门为0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `department_id_uindex`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (2, 'All Department Manager', '总经办', NULL);
INSERT INTO `department` VALUES (3, 'Human Resources Department', '人力资源部', NULL);
INSERT INTO `department` VALUES (5, 'Order Department', '采购部', NULL);
INSERT INTO `department` VALUES (6, 'Warehousing Department', '仓储部', NULL);
INSERT INTO `department` VALUES (7, 'Finance Department', '财务部', NULL);
INSERT INTO `department` VALUES (11, 'Publicity department', '宣传部门', NULL);
INSERT INTO `department` VALUES (17, 'Cultural Department🥼🧥👔👕', '文化部门', NULL);
INSERT INTO `department` VALUES (20, 'Testing department', '测试部门', NULL);
INSERT INTO `department` VALUES (31, 'Data center', '数据中心', NULL);
INSERT INTO `department` VALUES (32, 'Laboratory', '实验中心', NULL);
INSERT INTO `department` VALUES (33, '123333Quality inspection department', '质量校验部门', NULL);
INSERT INTO `department` VALUES (40, 'Financial Business Department', '金融事业部', NULL);
INSERT INTO `department` VALUES (41, 'Executive Department', '执行部', NULL);
INSERT INTO `department` VALUES (42, 'Training place', '培训部', NULL);
INSERT INTO `department` VALUES (43, 'Marketing Department', '市场部', NULL);
INSERT INTO `department` VALUES (44, 'Channel Location Division', '渠道选址事业部', NULL);
INSERT INTO `department` VALUES (45, 'Data Collection Department', '数据采集部', NULL);
INSERT INTO `department` VALUES (46, '123', '1233333', NULL);

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
  UNIQUE INDEX `dictionary_contents_id_uindex`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dictionary_contents
-- ----------------------------
INSERT INTO `dictionary_contents` VALUES (1, 'job', '职业', '做什么的12');
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
  UNIQUE INDEX ` dictionary_details_id_uindex`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

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
INSERT INTO `dictionary_details` VALUES (35, '专业英语8级（专八）（TEM-8）', 5, 8);
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
  `hireDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入职时间',
  `state` int NOT NULL DEFAULT 1 COMMENT '状态 1正常 0离职',
  `admin` int NOT NULL DEFAULT 0 COMMENT '超级管理员身份 1超管 0普通',
  `login_time` datetime NULL DEFAULT NULL,
  `register_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `employee_id_uindex`(`id` ASC) USING BTREE,
  UNIQUE INDEX `employee_name_uindex`(`name` ASC) USING BTREE,
  UNIQUE INDEX `employee_email_uindex`(`email` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1094 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 'admin', '$2a$10$OG1zaFHT2LUy4SGcQ4EnRu9sPQMjMGEE6jARz61aQwRQ3316N6ikG', '1623@163.com', 20, 2, '2021-05-14 00:28:00', 1, 1, '2022-10-12 06:10:08', '2021-05-21 08:46:19');
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
INSERT INTO `employee` VALUES (1093, 'ttttt', '$2a$10$Oi1BFwBSZN2bAIdgMkLFf.6USHPBCFVlQqga2RlTeZLJq1ibBZ4xm', 'dd@ddd.com', 23, 5, '2022-09-22 06:07:52', 1, 0, NULL, '2022-09-22 06:07:52');

-- ----------------------------
-- Table structure for employee_role
-- ----------------------------
DROP TABLE IF EXISTS `employee_role`;
CREATE TABLE `employee_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `employeeId` int NULL DEFAULT NULL,
  `roleId` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `employee_role_id_uindex`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 171 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

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
INSERT INTO `employee_role` VALUES (166, 1093, 3);
INSERT INTO `employee_role` VALUES (167, 1093, 4);
INSERT INTO `employee_role` VALUES (168, 1093, 1);
INSERT INTO `employee_role` VALUES (169, 1093, 2);
INSERT INTO `employee_role` VALUES (170, 1093, 5);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限名称',
  `expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '资源地址',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `permission_id_uindex`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

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
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产品名称',
  `product_unit` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产品的单位',
  `product_unit_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '产品单价',
  `product_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品描述（简述）',
  `product_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品图片',
  `product_status` int NULL DEFAULT NULL COMMENT '产品状态，正常，锁定，过期，',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES (1, '121212', '套', 20.00, '阿斯顿发射点', NULL, NULL, '2022-10-12 18:16:59', NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色Id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `sn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

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
INSERT INTO `role` VALUES (49, '小lolo', '2565');
INSERT INTO `role` VALUES (50, '0', '123');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL COMMENT '角色id',
  `permission_id` int NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_permission_id_uindex`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 426 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权限中间表' ROW_FORMAT = DYNAMIC;

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
INSERT INTO `role_permission` VALUES (415, 49, 12);
INSERT INTO `role_permission` VALUES (416, 49, 13);
INSERT INTO `role_permission` VALUES (417, 49, 14);
INSERT INTO `role_permission` VALUES (418, 49, 15);
INSERT INTO `role_permission` VALUES (419, 49, 16);
INSERT INTO `role_permission` VALUES (420, 49, 21);
INSERT INTO `role_permission` VALUES (421, 49, 23);
INSERT INTO `role_permission` VALUES (422, 49, 24);
INSERT INTO `role_permission` VALUES (423, 49, 27);
INSERT INTO `role_permission` VALUES (424, 49, 28);
INSERT INTO `role_permission` VALUES (425, 50, 1);

-- ----------------------------
-- Table structure for welcome
-- ----------------------------
DROP TABLE IF EXISTS `welcome`;
CREATE TABLE `welcome`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `wise` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名人名言',
  `frequency` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '展示频率',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 711 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of welcome
-- ----------------------------
INSERT INTO `welcome` VALUES (1, '人家帮我，永志不忘;我帮人家，莫记心上。——华罗庚', 0000000000);
INSERT INTO `welcome` VALUES (3, '2、珍藏心中过往的甜蜜回忆，此刻，我们放飞青春的梦想。', NULL);
INSERT INTO `welcome` VALUES (5, '3、时间如流水，不知合理利用，剩下的只有后悔。', NULL);
INSERT INTO `welcome` VALUES (7, '4、生活可能并非我们期待后舞台，但既然来了，我们就要翩翩起舞。', NULL);
INSERT INTO `welcome` VALUES (9, '　　5、事实上，成功仅代表了你工作的1%，成功是99%失败的结果。', NULL);
INSERT INTO `welcome` VALUES (11, '　　6、其实路并没有错，错的是选择，爱并没有错，错的是缘分，所以无论何地，一路的风景总是有限的，终究会有美好的。', NULL);
INSERT INTO `welcome` VALUES (13, '　　7、如果不醒来，那么现实和梦境又有何分别。', NULL);
INSERT INTO `welcome` VALUES (15, '　　8、每一次努力都是最优的亲近，每一滴汗水都是机遇的滋润。', NULL);
INSERT INTO `welcome` VALUES (17, '　　9、我不怕自己努力了不成功，我只怕比我成功的人，比我更努力。', NULL);
INSERT INTO `welcome` VALUES (19, '　　10、成大事不在于力量有多大，而在于能坚持多久。', NULL);
INSERT INTO `welcome` VALUES (21, '　　11、对于最有能力的领航人风浪总是格外的汹涌。', NULL);
INSERT INTO `welcome` VALUES (23, '　　12、白白的过一天，无所事事，就像犯了偷盗罪一样。', NULL);
INSERT INTO `welcome` VALUES (25, '　　13、永远坚信这一点：太阳落了还会升起，不幸的日子总会有尽头，过去是这样，将来也是这样。', NULL);
INSERT INTO `welcome` VALUES (27, '　　14、人生是无止境的，只有积极、勇敢、乐观地面对生活、成败、困难，才会像大树一样枝繁叶茂。', NULL);
INSERT INTO `welcome` VALUES (29, '　　15、人生中，我们有着快乐与悲伤，所以不断努力就能获得成功。', NULL);
INSERT INTO `welcome` VALUES (31, '　　16、天将降大任于是人也；必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行弗乱其所为，所以动心忍性，增益其所不能。', NULL);
INSERT INTO `welcome` VALUES (33, '　　17、那些出类拔萃的人，正是在生活的早期就清楚地辨明了自己的方向，并且始终如一瞄准这一目标的人。', NULL);
INSERT INTO `welcome` VALUES (35, '　　18、无一事不学，无一时不学，无一处不学，成功之路也。', NULL);
INSERT INTO `welcome` VALUES (37, '　　19、人生是船，自信是帆，要想船走，必须升帆。', NULL);
INSERT INTO `welcome` VALUES (39, '　　20、放弃等于0，坚持就有希望，有希望就可能成功。', NULL);
INSERT INTO `welcome` VALUES (41, '　　21、要想赢，就一定不能怕输。不怕输，结果未必能赢。但是怕输，结果则一定是输。', NULL);
INSERT INTO `welcome` VALUES (43, '　　22、每一回失败都附带着等量的成功种子。', NULL);
INSERT INTO `welcome` VALUES (45, '　　23、不要后悔自己所做的事情，不要为各种错误找借口。', NULL);
INSERT INTO `welcome` VALUES (47, '　　24、学习不是那么容易，只要用心就会学好。', NULL);
INSERT INTO `welcome` VALUES (49, '　　25、如果奇迹没有出现，那就去创造一个。', NULL);
INSERT INTO `welcome` VALUES (51, '　　26、相信认真就是能力，规范就是成绩，犯错就是不会。', NULL);
INSERT INTO `welcome` VALUES (53, '　　27、努力就是光，成功就是影，没有光哪来的影？', NULL);
INSERT INTO `welcome` VALUES (55, '　　28、我们应当吧求知作为船桨，将自信化为船身，鼓起谦虚的航帆，载着活力与激情驶向青春的海洋。', NULL);
INSERT INTO `welcome` VALUES (57, '　　29、远大抱负始于高中，辉煌人生起于今日。', NULL);
INSERT INTO `welcome` VALUES (59, '　　30、努力与刻苦可能不会取得成功，但不努力、不刻苦，一定不会成功。趁着自己还年轻，为了梦想放手去干吧！', NULL);
INSERT INTO `welcome` VALUES (61, '　　31、也许付出了暂没有收获，但有收获的人必定付出过。', NULL);
INSERT INTO `welcome` VALUES (63, '　　32、前方的路平坦，是因为你的努力得到了验证；前方的路凹凸不平，是让你用汗水去填满坑坑洼洼的小路。', NULL);
INSERT INTO `welcome` VALUES (65, '　　33、尹栋：太阳照亮人生的路，月亮照亮心灵的路。', NULL);
INSERT INTO `welcome` VALUES (67, '　　34、笑对人生，把一切都看淡，但却又放在了心里整理。', NULL);
INSERT INTO `welcome` VALUES (69, '　　35、无论学习还是做事，习惯存一颗郑重的心，举重若轻者，搬山如摘花；举轻若重者，摘花如搬山。', NULL);
INSERT INTO `welcome` VALUES (71, '　　36、汗水能浇出成功的鲜花，拼搏能赢来胜利的喜悦。', NULL);
INSERT INTO `welcome` VALUES (73, '　　37、傻瓜的心在嘴里，智者的嘴在心里。', NULL);
INSERT INTO `welcome` VALUES (75, '　　38、有志者，事竟成，破釜沉舟，百二秦关终属楚；苦心人天不负卧薪尝胆，三千越甲可吞吴。', NULL);
INSERT INTO `welcome` VALUES (77, '　　39、如果我们没有才华，那努力就足以使我们幸福了。', NULL);
INSERT INTO `welcome` VALUES (79, '　　40、除了我自己以外，没有什么人能贬低我，如果我足够坚强，就没有什么可以打败我。', NULL);
INSERT INTO `welcome` VALUES (81, '　　41、前进的路也许会很迷茫，但请不要在迷茫中失去自我，失去前进的信念。', NULL);
INSERT INTO `welcome` VALUES (83, '　　42、知识是一把钥匙，我们只有不断学习，才能造出一把钥匙。', NULL);
INSERT INTO `welcome` VALUES (85, '　　43、我说浪费的今天，是所有死去的人们都渴望的明天。', NULL);
INSERT INTO `welcome` VALUES (87, '　　44、梦想是给予世间万物的，理想是给予人类的。', NULL);
INSERT INTO `welcome` VALUES (89, '　　45、只要有一丝希望，我就要做出百分之百的努力。', NULL);
INSERT INTO `welcome` VALUES (91, '　　46、人不能样样顺利，但可以事事努力。', NULL);
INSERT INTO `welcome` VALUES (93, '　　47、生命要不然是一场大冒险，要不然就是一无所有。', NULL);
INSERT INTO `welcome` VALUES (95, '　　48、要有最朴素的生活，与最遥远的梦想，即使明天天寒地冻，路远马亡。', NULL);
INSERT INTO `welcome` VALUES (97, '　　49、今天洒下的滴滴汗水，是为了明天的眼睛中不会出现泪光。', NULL);
INSERT INTO `welcome` VALUES (99, '　　50、不向前走，不知路远；不努力学习，不明白道理。', NULL);
INSERT INTO `welcome` VALUES (101, '　　51、悲观面对成功，乐观面对失败，轻松面对自己，宽容面对。', NULL);
INSERT INTO `welcome` VALUES (103, '　　52、少为失败找理由，多为成功找方法，大多数人想要改革这个世界，却不晓得即时从小事做起。', NULL);
INSERT INTO `welcome` VALUES (105, '　　53、勇士脚下没有绝路，懦夫眼前尽是悬崖。', NULL);
INSERT INTO `welcome` VALUES (107, '　　54、生活是多姿多彩的，有时要比糖甜，有时要比药苦，味道迥异，只因你我心情不同。', NULL);
INSERT INTO `welcome` VALUES (109, '　　55、忍人之所不能忍，方能为人之所不能为——勤奋、坚持、成功。', NULL);
INSERT INTO `welcome` VALUES (111, '　　56、吾自问吾所为何？吾为吾之理想，吾之未来。吾以何搏之？吾以今之汗水铸人生之路。', NULL);
INSERT INTO `welcome` VALUES (113, '　　57、不要让青春留下遗憾，因为它如流星般消逝不见。', NULL);
INSERT INTO `welcome` VALUES (115, '　　58、做事不管你起啊你前面做的如何好，只要你半途而废，那么你就是一个。', NULL);
INSERT INTO `welcome` VALUES (117, '　　59、笑是自信的开始，相信自己，就能笑得最美丽、最灿烂、最长久。', NULL);
INSERT INTO `welcome` VALUES (119, '　　60、正确利用时间，你要理解什么，不要舍近求远。', NULL);
INSERT INTO `welcome` VALUES (121, '　　61、好心情不是捡来的，而是争取的。', NULL);
INSERT INTO `welcome` VALUES (123, '　　62、想和所有人做朋友的人，不是任何人的朋友。', NULL);
INSERT INTO `welcome` VALUES (125, '　　63、无论今天发生的事有多坏，别难过，别悲伤，因为在这一天，你是你余生中最年轻的时候了。', NULL);
INSERT INTO `welcome` VALUES (127, '　　64、只要努力就会成功，不成天才，可成地才。', NULL);
INSERT INTO `welcome` VALUES (129, '　　65、成功需要细心、专心、耐心、信心、真心，抓住这“五心”你就会成功。', NULL);
INSERT INTO `welcome` VALUES (131, '　　66、没有激流称不上前进，没有山峰称不上攀登。', NULL);
INSERT INTO `welcome` VALUES (133, '　　67、泪水不代表谁的失败，微笑不意味着谁的成功。', NULL);
INSERT INTO `welcome` VALUES (135, '　　68、你不能预知明天，但可以把握今天。', NULL);
INSERT INTO `welcome` VALUES (137, '　　69、天空宽不过雄鹰的翅膀，草原打不过牧人的脚步。', NULL);
INSERT INTO `welcome` VALUES (139, '　　70、我们是青春的主宰，世界是我们的舞台，不要害怕，相信自己，我们能行。', NULL);
INSERT INTO `welcome` VALUES (141, '　　经典语录励志名言2', NULL);
INSERT INTO `welcome` VALUES (142, '　　1、成功，往往住在失败的隔壁！', NULL);
INSERT INTO `welcome` VALUES (144, '　　2、我以为挫折磨难是锻炼意志增强能力的好机会。——邹韬奋', NULL);
INSERT INTO `welcome` VALUES (146, '　　3、成功不是将来才有的，而是从决定去做的那一刻起，持续累积而成。', NULL);
INSERT INTO `welcome` VALUES (148, '　　4、现在站在什么地方不重要，重要的是你往什么方向移动。', NULL);
INSERT INTO `welcome` VALUES (150, '　　5、成功是什么？就是走过了所有通向失败的路，只剩下一条路，那就是成功的路。', NULL);
INSERT INTO `welcome` VALUES (152, '　　6、目标的坚定是性格中最必要的力量源泉之一，也是成功的利器之一。没有它，天才也会在矛盾无定的迷径中徒劳无功。', NULL);
INSERT INTO `welcome` VALUES (154, '　　7、凡事不要说“我不会”或“不可能”，因为你根本还没有去做！', NULL);
INSERT INTO `welcome` VALUES (156, '　　8、伟大的事业不是靠力气速度和身体的敏捷完成的，而是靠性格意志和知识的力量完成的。', NULL);
INSERT INTO `welcome` VALUES (158, '　　9、世上最累人的事，莫过于虚伪的过日子。', NULL);
INSERT INTO `welcome` VALUES (160, '　　10、自己打败自己是最可悲的失败，自己战胜自己是最可贵的胜利。', NULL);
INSERT INTO `welcome` VALUES (162, '　　11、立志欲坚不欲锐，成功在久不在速。——张孝祥', NULL);
INSERT INTO `welcome` VALUES (164, '　　12、天空黑暗到一定程度，星辰就会熠熠生辉。', NULL);
INSERT INTO `welcome` VALUES (166, '　　13、人生最大的错误是不断担心会犯错。', NULL);
INSERT INTO `welcome` VALUES (168, '　　14、人若软弱就是自己最大的敌人；人若勇敢就是自己最好的朋友。', NULL);
INSERT INTO `welcome` VALUES (170, '　　15、如果你想得到，你就会得到，你所需要付出的只是行动。', NULL);
INSERT INTO `welcome` VALUES (172, '　　经典语录励志名言3', NULL);
INSERT INTO `welcome` VALUES (173, '　　1、头脑醒目一点，处世低调一点，为人谦逊一点，做事积极一点，待人和善一点。', NULL);
INSERT INTO `welcome` VALUES (175, '　　2、人生或如一轮红日，划破黎明的沉寂，炽热的梦想在喷勃或如一段段插曲在生命的华章里熠熠激荡迸溅起最璀璨的激昂。', NULL);
INSERT INTO `welcome` VALUES (177, '　　3、如果你过分珍爱自己的羽毛，不使它受一点损伤，那么你将失去两只翅膀，永远不再能够凌空飞翔。这句话是雪莱写的，是啊如果我么', NULL);
INSERT INTO `welcome` VALUES (179, '　　4、给自己找茬是诊断，给自己找事是磨练，给自己剖析是知己，给自己嘲笑是激励！', NULL);
INSERT INTO `welcome` VALUES (181, '　　5、不要生气要争气，不要看破要突破，不要嫉妒要欣赏，不要托延要积极，不要心动要行动。', NULL);
INSERT INTO `welcome` VALUES (183, '　　6、障碍与失败，是通往成功最稳靠的踏脚石，肯研究、利用它们，便能从失败中培养出成功。', NULL);
INSERT INTO `welcome` VALUES (185, '　　7、只要瞄准了大方向，坚持不懈地做下去，才能够扫除挡在梦想前面的障碍，实现美好的人生蓝图。', NULL);
INSERT INTO `welcome` VALUES (187, '　　8、穷人缺什么：表面缺资金，本质缺野心，脑子缺观念，机会缺了解，骨子缺勇气，改变缺行动，事业缺毅力。', NULL);
INSERT INTO `welcome` VALUES (189, '　　9、命运给予我们的不是失望之酒，而是机会之杯。因此，让我们毫无畏惧，满心愉悦地把握命运。', NULL);
INSERT INTO `welcome` VALUES (191, '　　10、生命，需要我们去努力。年轻时，我们要努力锻炼自己的能力，掌握知识、掌握技能、掌握必要的社会经验。', NULL);
INSERT INTO `welcome` VALUES (193, '　　11、你既然期望辉煌伟大的一生，那么就应该从今天起，以毫不动摇的决心和坚定不移的信念，凭自己的智慧和毅力，去创造你和人类的', NULL);
INSERT INTO `welcome` VALUES (195, '　　12、不求与人相比，但求超越自己，要哭就哭出激动的泪水，要笑就笑出成长的性格！', NULL);
INSERT INTO `welcome` VALUES (197, '　　13、生活的风雨不会阻止我前进的步伐，地面的崎岖不会耽误我计划中的时间，心情的好坏也不会影响我对世界的看法。相信我就是我，', NULL);
INSERT INTO `welcome` VALUES (199, '　　14、人一旦不存希望，生命也就休止。希望的具体表现就是欲，它形成具体的目标和实践的动力，而令自己乐于完成它，乐于实现它，从', NULL);
INSERT INTO `welcome` VALUES (201, '　　15、在别人藐视的事中获得成功，是一件了不起的事，因为它证明不但战胜了自己，也战胜了别人。', NULL);
INSERT INTO `welcome` VALUES (203, '　　16、时间是最公开合理的，它从不多给谁一份，勤劳者能叫时间留给串串的果实，懒惰者时间给予他们一头白发，两手空空。', NULL);
INSERT INTO `welcome` VALUES (205, '　　17、把握今天，展望未来，人生的路途中总有一些风景会被错过，有时，错过那些风景，只是为了看到更美的风景。', NULL);
INSERT INTO `welcome` VALUES (207, '　　18、是人都有惰性，这是与生俱来的，但是我们后天可以改变这种惰性，谁改变的越多，谁就越成功。', NULL);
INSERT INTO `welcome` VALUES (209, '　　19、生活是一面最认真不过的镜子，你对它笑，它报之以笑，你对它哭，它也会回以泪水。', NULL);
INSERT INTO `welcome` VALUES (211, '　　20、人生，最宝贵的莫过于光阴；人生，最璀璨的莫过于事业；人生，最快乐的莫过于奋斗。', NULL);
INSERT INTO `welcome` VALUES (213, '　　21、想象困难做出的反应，不是逃避或绕开它们，而是面对它们，同它们打交道，以一种进取的和明智的方式同它们奋斗。', NULL);
INSERT INTO `welcome` VALUES (215, '　　22、船停在港湾是最安全的，但这不是造船的目的；人待在家里最舒服，但这不是做人的目的；只有不断自我挑战，自我突破，一定能实', NULL);
INSERT INTO `welcome` VALUES (217, '　　23、不管从什么时候开始，重要的是开始以后不要停止；不管在什么时候结束，重要的是结束以后不要后悔。', NULL);
INSERT INTO `welcome` VALUES (219, '　　24、昨天如影记住你昨天的挫折和失败的教训；今天如画美好的生活、快乐和幸福的人生要靠你自己去描绘；明天如梦珍惜今天，选择好', NULL);
INSERT INTO `welcome` VALUES (221, '　　25、我们谁都有自己的生活，谁都离不开生活，谁都不能鄙视和逃避生活，人生有很多事情是不如人意的，生活中，十之有八九也都是如', NULL);
INSERT INTO `welcome` VALUES (223, '　　26、一个人的人生幸福，只靠道德方面的努力是不够的，我们必须经常描绘自己将来的幸福形象，并依靠万能的潜意识来帮忙实现。潜意', NULL);
INSERT INTO `welcome` VALUES (225, '　　27、在我们的生活中，如果没有了书籍，就好像小鸟在天空中飞翔时断了翅膀一样，永远不能前进。', NULL);
INSERT INTO `welcome` VALUES (227, '　　28、工作是工作，生活是生活。工作上，自己扮演的是职务角色；生活里，自己扮演的是自我。工作只是生命中一小部分，切勿把谋生工', NULL);
INSERT INTO `welcome` VALUES (229, '　　29、严于自律：不能成为自己本身之主人者，将永远成不了他周围任何事物的主人。自律是完全拥有自己的内心并将其导向他所希望的目', NULL);
INSERT INTO `welcome` VALUES (231, '　　30、人生需要耐心！在困难和挫折面前，更要耐心，沉着，冷静，耐心让你积极思维，发挥你的聪明才智，克服困难，度过挫折。', NULL);
INSERT INTO `welcome` VALUES (233, '　　经典语录励志名言4', NULL);
INSERT INTO `welcome` VALUES (234, '　　1、少了灯光的黑夜，我便会陷入一片胡思乱想之中，每当这时便要勇敢的睁开双眼，告诉自己：这世界还在！', NULL);
INSERT INTO `welcome` VALUES (236, '　　2、没有人陪伴，和勇敢无关。只是刚好在人生最自由的时光。没有人陪伴也可以习惯，单身美好，我自己很好。', NULL);
INSERT INTO `welcome` VALUES (238, '　　3、竟然来到这个世界就好好的勇敢的闯一回反正我们也没有打算活着回去。人生是场持久战，是精彩的历险记。', NULL);
INSERT INTO `welcome` VALUES (240, '　　4、勇敢的高墙，清冷的长剑。守护长久不曾说话的表情。错过忽而如猫一般的盛大。歌力与驯服正精雕着自己。', NULL);
INSERT INTO `welcome` VALUES (242, '　　5、一人勇敢而率真的灵魂，能用自己的眼睛去观照，用自己的心去爱，用自己的理智去判断。不做影子，而做人。', NULL);
INSERT INTO `welcome` VALUES (244, '　　1、不管怎样的事情，都请安静地愉快吧！这是人生。我们要依样地接受人生，勇敢地大胆地，而且永远地微笑着。', NULL);
INSERT INTO `welcome` VALUES (246, '　　2、酒喝的再多，醒来之后该解决的问题还是没解决。再怎么折腾胃，心也不会好过一分。所以，勇敢的面对一切吧。', NULL);
INSERT INTO `welcome` VALUES (248, '　　3、人的一生中，最光辉的一天并非是功成名就那天，而是从悲叹与绝望中产生对人生的挑战，以勇敢迈向意志那天。', NULL);
INSERT INTO `welcome` VALUES (250, '　　4、没有危机感，不知道恐惧，鲁莽前冲的只是血性；而心里害怕，却还能战胜恐惧的面对危险的，才是真正的勇敢。', NULL);
INSERT INTO `welcome` VALUES (252, '　　5、我在等那么一天，当我足够优秀足够勇敢，我能坚定的从你面前走过，且带着一份从容，那将会是你从没见过的我。', NULL);
INSERT INTO `welcome` VALUES (254, '　　6、一个真正伟大、骄傲而又勇敢的民族宁可面对的任何灾难，也不愿在牺牲其民族尊严的情况下换得卑贱的繁荣。', NULL);
INSERT INTO `welcome` VALUES (256, '　　7、谁知道我们该梦归何处，谁明白尊严已沦为何物。是否找个理由随波逐流，或是勇敢前行挣脱牢笼。我该如何存在。', NULL);
INSERT INTO `welcome` VALUES (258, '　　8、真正点亮生命的，不是明天的景色，而是美好的希望。跌倒了再爬起，失败了就再努力，怀抱著希望，勇敢的走下去！', NULL);
INSERT INTO `welcome` VALUES (260, '　　9、如果没有如果，曾经已是曾经，过去早已过去，做自己的自己。谁是谁的谁，谁为谁哭泣，我若不勇敢，谁替我坚强。', NULL);
INSERT INTO `welcome` VALUES (262, '　　10、以为自己已经够勇敢，却还是畏首畏尾，不敢太迷恋，不敢不顾一切的爱，用一层盔甲把自己保护起来，怕再一次受伤。', NULL);
INSERT INTO `welcome` VALUES (264, '　　经典语录励志名言5', NULL);
INSERT INTO `welcome` VALUES (265, '　　1、当你能飞的时候就不要放弃飞；当你有梦的时候就不要放弃梦。', NULL);
INSERT INTO `welcome` VALUES (267, '　　2、只要有信心，人永远不会挫败。', NULL);
INSERT INTO `welcome` VALUES (269, '　　3、漫无目的的生活就像出海航行而没有指南针。', NULL);
INSERT INTO `welcome` VALUES (271, '　　4、成功的秘诀，是在养成迅速去做的习惯，要趁着潮水涨得最高的一刹那，不但没有阻力，而且能帮助你迅速地成功。——劳伦斯', NULL);
INSERT INTO `welcome` VALUES (273, '　　5、人只要不失去方向，就不会失去自己。', NULL);
INSERT INTO `welcome` VALUES (275, '　　6、不论你在什么时候开始，重要的是开始之后就不要停止；不论你在什么时候结束，重要的是结束之后就不要悔恨。', NULL);
INSERT INTO `welcome` VALUES (277, '　　7、天下绝无不热烈勇敢地追求成功，而能取得成功的人。——拿破仑一世', NULL);
INSERT INTO `welcome` VALUES (279, '　　8、乐观者在灾祸中看到机会，悲观者在机会中看到灾祸。', NULL);
INSERT INTO `welcome` VALUES (281, '　　9、有勇气并不表示恐惧不存在，而是敢面对恐惧克服恐惧。', NULL);
INSERT INTO `welcome` VALUES (283, '　　10、用最少的悔恨面对过去；用最少的浪费面对现在；用最多的梦想面对未来。', NULL);
INSERT INTO `welcome` VALUES (285, '　　11、考验人的才能不在于他是否拿到一副好牌，而在他能打好一副坏牌。', NULL);
INSERT INTO `welcome` VALUES (287, '　　12、挫折其实就是迈向成功所应缴的学费。', NULL);
INSERT INTO `welcome` VALUES (289, '　　13、如果你问一个善于溜冰的人怎样获得成功时，他会告诉你：“跌倒了，爬起来。”这就是成功。——牛顿', NULL);
INSERT INTO `welcome` VALUES (291, '　　14、奋斗令我们的生活充满生机，责任让我们的生命充满意义，常遇困境说明你在进步，常有压力，说明你有目标。', NULL);
INSERT INTO `welcome` VALUES (293, '　　15、一个人失败的原因，在于本身性格的缺点，与环境无关。', NULL);
INSERT INTO `welcome` VALUES (295, '　　经典语录励志名言6', NULL);
INSERT INTO `welcome` VALUES (296, '　　1、不管多么险峻的高山，总是为不畏艰难的人留下一条攀登的路。', NULL);
INSERT INTO `welcome` VALUES (298, '　　2、好高骛远的一无所得，埋头苦干的获得知识。', NULL);
INSERT INTO `welcome` VALUES (300, '　　3、失败是不会死人的，可是失望会。', NULL);
INSERT INTO `welcome` VALUES (302, '　　4、如果要飞得高，就该把地平线忘掉。', NULL);
INSERT INTO `welcome` VALUES (304, '　　5、我不怕千万人阻挡，只怕自己投降。', NULL);
INSERT INTO `welcome` VALUES (306, '　　6、给自己一个目标，让生命为它燃烧。', NULL);
INSERT INTO `welcome` VALUES (308, '　　7、对的那条路，往往不是最好走的。', NULL);
INSERT INTO `welcome` VALUES (310, '　　8、人必须有自信，这是成功的秘密。', NULL);
INSERT INTO `welcome` VALUES (312, '　　9、成功的人做别人不愿做的事，做别人不敢做的事，做别人做不到的事。', NULL);
INSERT INTO `welcome` VALUES (314, '　　10、好好管教自己，不要管别人。', NULL);
INSERT INTO `welcome` VALUES (316, '　　经典语录励志名言7', NULL);
INSERT INTO `welcome` VALUES (317, '　　1、牛不训不会耕，马不练不能骑。', NULL);
INSERT INTO `welcome` VALUES (319, '　　2、勤奋和智慧是双胞胎，懒惰和愚蠢是亲兄弟。', NULL);
INSERT INTO `welcome` VALUES (321, '　　3、谁知盘中餐，粒粒皆辛苦。', NULL);
INSERT INTO `welcome` VALUES (323, '　　4、要得会，天天累；要得精，用命拼。', NULL);
INSERT INTO `welcome` VALUES (325, '　　5、人勤地生宝，人懒地生草。', NULL);
INSERT INTO `welcome` VALUES (327, '　　6、日日行，不怕千万里；常常做，不怕千万事。', NULL);
INSERT INTO `welcome` VALUES (329, '　　7、天道酬勤。', NULL);
INSERT INTO `welcome` VALUES (331, '　　8、勤奋是成功之母，懒惰乃万恶之源。', NULL);
INSERT INTO `welcome` VALUES (333, '　　9、只要功夫深，铁杆磨成针。', NULL);
INSERT INTO `welcome` VALUES (335, '　　10、水滴集多成大海，读书集多成学问。', NULL);
INSERT INTO `welcome` VALUES (337, '　　11、业精于勤而荒于嬉，行成于思而毁于随。——韩愈', NULL);
INSERT INTO `welcome` VALUES (339, '　　12、骐骥一跃，不能十步；驽马十驾，功在不舍；锲而舍之，朽木不折；锲而不舍，金石可镂。', NULL);
INSERT INTO `welcome` VALUES (341, '　　13、勤能补拙是良训，一分耕耘一分才。——华罗庚', NULL);
INSERT INTO `welcome` VALUES (343, '　　14、聪明出于勤奋，天才在于积累。——华罗庚', NULL);
INSERT INTO `welcome` VALUES (345, '　　15、不经历风雨，怎能见彩虹？', NULL);
INSERT INTO `welcome` VALUES (347, '　　16、勤勉是幸运的右手，世俭是幸运的左手。', NULL);
INSERT INTO `welcome` VALUES (349, '　　17、智慧是勤劳的结晶，成就是劳动的化身。——卡莱尔', NULL);
INSERT INTO `welcome` VALUES (351, '　　18、才华是血汗的结晶。', NULL);
INSERT INTO `welcome` VALUES (353, '　　19、才华是刀刃，辛苦是磨刀石。', NULL);
INSERT INTO `welcome` VALUES (355, '　　20、不经过琢磨，宝石也不会发光。', NULL);
INSERT INTO `welcome` VALUES (357, '　　经典语录励志名言8', NULL);
INSERT INTO `welcome` VALUES (358, '　　1、你的假装努力，欺骗的只有你自己，永远不要用战术上的勤奋，来掩饰战略上的懒惰。', NULL);
INSERT INTO `welcome` VALUES (360, '　　2、成长是一场和自己的比赛，不要担心别人会做得比你好，你只需要每天都做得比前一天好就可以了。', NULL);
INSERT INTO `welcome` VALUES (362, '　　3、你没那么多观众，别那么累。做一个简单的人，踏实而务实。不沉溺幻想，更不庸人自扰。', NULL);
INSERT INTO `welcome` VALUES (364, '　　4、奋斗的路上，时间总是过得很快，目前的困难和麻烦是很多，但是只要不忘初心，脚踏实地一步一步的朝着目标前进，最后的结局交给', NULL);
INSERT INTO `welcome` VALUES (366, '　　5、你心里最崇拜谁，不必变成那个人，而是用那个人的精神和方法，去变成你自己。', NULL);
INSERT INTO `welcome` VALUES (368, '　　6、运气是努力的附属品。没有经过实力的原始积累，给你运气你也抓不住。上天给予每个人的都一样，但每个人的准备却不一样。不要羡', NULL);
INSERT INTO `welcome` VALUES (370, '　　7、时间只是过客，自己才是主人，人生的路无需苛求，只要你迈步，路就在你的脚下延伸，只要你扬帆，便会有八面来风，启程了，人的', NULL);
INSERT INTO `welcome` VALUES (372, '　　8、每个人身上都有惰性和消极情绪，成功的人都是懂得管理自己的情绪和克服自己的惰性，并像太阳一样照亮身边的人，激励身边的人。', NULL);
INSERT INTO `welcome` VALUES (374, '　　9、最终你相信什么就能成为什么。因为世界上最可怕的二个词，一个叫执着，一个叫认真，认真的人改变自己，执着的人改变命运。只要', NULL);
INSERT INTO `welcome` VALUES (376, '　　10、人生，就要活得漂亮，走得铿锵。自己不奋斗，终归是摆设。无论你是谁，宁可做拼搏的失败者，也不要做安于现状的平凡人。', NULL);
INSERT INTO `welcome` VALUES (378, '　　11、不管做什么都不要急于回报，因为播种和收获不在同一个季节，中间隔着的一段时间，我们叫它为坚持。', NULL);
INSERT INTO `welcome` VALUES (380, '　　12、过自己喜欢的生活，成为自己喜欢的样子，其实很简单，就是把无数个“今天”过好，这就意味着不辜负不蹉跎时光，以饱满的热情', NULL);
INSERT INTO `welcome` VALUES (382, '　　13、不要觉得全心全意去做看起来微不足道的事，是一种浪费，小事做的得心应手了，大事自然水到渠成。', NULL);
INSERT INTO `welcome` VALUES (384, '　　14、别着急要结果，先问自己够不够格，付出要配得上结果，工夫到位了，结果自然就出来了。', NULL);
INSERT INTO `welcome` VALUES (386, '　　15、上天不会亏待努力的人，也不会同情假勤奋的人，你有多努力时光它知道。', NULL);
INSERT INTO `welcome` VALUES (388, '　　16、你今天必须做别人不愿做的事，好让你明天可以拥有别人不能拥有的东西。', NULL);
INSERT INTO `welcome` VALUES (390, '　　17、你想过普通的生活，就会遇到普通的挫折。你想过最好的生活，就一定会遇上最强的伤害。这个世界很公平，想要最好，就一定会给', NULL);
INSERT INTO `welcome` VALUES (392, '　　18、你若坚持，定会发光，时间是所向披靡的武器，它能集腋成裘，也能聚沙成塔，将人生的不可能都变成可能。', NULL);
INSERT INTO `welcome` VALUES (394, '　　19、别人对你好，你要争气，图日后有能力有所报答，别人对你不好，你更要争气望有朝一日，能够扬眉吐气。', NULL);
INSERT INTO `welcome` VALUES (396, '　　20、成长这一路就是懂得闭嘴努力，知道低调谦逊，学会强大自己，在每一个值得珍惜的日子里，拼命去成为自己想成为的人。', NULL);
INSERT INTO `welcome` VALUES (398, '　　经典语录励志名言9', NULL);
INSERT INTO `welcome` VALUES (399, '　　1、宝剑锋从磨砺出，梅花香自苦寒来。今天工作不努力，明天努力找工作生当做人杰，死亦为鬼雄。', NULL);
INSERT INTO `welcome` VALUES (401, '　　2、努力去做自己该做的，但是不要期待回报，不是付出了就会有回报的，做了就不要后悔，不做才后悔。', NULL);
INSERT INTO `welcome` VALUES (403, '　　3、今天的成功是因为昨天的积累，明天的成功则依赖于今天的努力。成功需要一个过程。', NULL);
INSERT INTO `welcome` VALUES (405, '　　4、一个能从别人的观念来看事情，能了解别人心灵活动的人，永远不必为自己的前途担心。', NULL);
INSERT INTO `welcome` VALUES (407, '　　5、青春是一场无知的奔忙，总会留下颠沛流离的伤，我多么希望明天有太阳，灼烧我那腐烂的梦想。', NULL);
INSERT INTO `welcome` VALUES (409, '　　经典语录励志名言10', NULL);
INSERT INTO `welcome` VALUES (410, '　　1.伟人之所以伟大，是因为他与别人共处逆境时，别人失去了信心，他却下决心实现自己的目标。', NULL);
INSERT INTO `welcome` VALUES (412, '　　2.不求与人相比，但求超越自己，要哭就哭出激动的泪水，要笑就笑出成长的性格!', NULL);
INSERT INTO `welcome` VALUES (414, '　　3.生命不是要超越别人，而是要超越自己。', NULL);
INSERT INTO `welcome` VALUES (416, '　　4.成大事不在于力量多少，而在能坚持多久。', NULL);
INSERT INTO `welcome` VALUES (418, '　　5.上帝从不抱怨人们的愚昧，人们却抱怨上帝的不公平。', NULL);
INSERT INTO `welcome` VALUES (420, '　　6.学习时的苦痛是暂时的，未学到的痛苦是终生的。', NULL);
INSERT INTO `welcome` VALUES (422, '　　7.环境永远不会十全十美，消极的人受环境控制，积极的人却控制环境。', NULL);
INSERT INTO `welcome` VALUES (424, '　　8.只有不断找寻机会的人才会及时把握机会，越努力，越幸运。', NULL);
INSERT INTO `welcome` VALUES (426, '　　9.世上只有想不通的人，没有走不通的路。', NULL);
INSERT INTO `welcome` VALUES (428, '　　10.生活不是林黛玉，不会因为忧伤而风情万种。', NULL);
INSERT INTO `welcome` VALUES (430, '　　11.人不怕走在黑夜里，就怕心中没有阳光。', NULL);
INSERT INTO `welcome` VALUES (432, '　　12.幸运之神的降临，往往只是因为你多看了一眼，多想了一下，多走了一步。', NULL);
INSERT INTO `welcome` VALUES (434, '　　13.积极的人在每一次忧患中都看到一个机会，而消极的人则在每个机会都看到某种忧患。', NULL);
INSERT INTO `welcome` VALUES (436, '　　14.任何的限制，都是从自己的内心开始的。', NULL);
INSERT INTO `welcome` VALUES (438, '　　15.在一个崇高的目标支持下，不停地工作，即使慢，也一定会获得成功。', NULL);
INSERT INTO `welcome` VALUES (440, '　　16.卓越的人一大优点是：在不利与艰难的遭遇里百折不挠。', NULL);
INSERT INTO `welcome` VALUES (442, '　　17.伟人与常人最大的差别就在于珍惜时间。', NULL);
INSERT INTO `welcome` VALUES (444, '　　18.成功的关键在于我们对失败的反应。', NULL);
INSERT INTO `welcome` VALUES (446, '　　19.生命对某些人来说是美丽的，这些人的一生都为某个目标而奋斗。', NULL);
INSERT INTO `welcome` VALUES (448, '　　20.即使行动导致错误，却也带来了学习与成长；不行动则是停滞与萎缩。', NULL);
INSERT INTO `welcome` VALUES (450, '　　经典语录励志名言11', NULL);
INSERT INTO `welcome` VALUES (451, '　　1.光说不干，事事落空；又说又干，马到成功。', NULL);
INSERT INTO `welcome` VALUES (453, '　　2.对于每一个不利条件，都会存在与之相对应的有利条件。', NULL);
INSERT INTO `welcome` VALUES (455, '　　3.勤奋，是步入成功之门的通行证。', NULL);
INSERT INTO `welcome` VALUES (457, '　　4.失败的历程也是成功的历程。', NULL);
INSERT INTO `welcome` VALUES (459, '　　5.成功=艰苦劳动+正确方法+少说空话。', NULL);
INSERT INTO `welcome` VALUES (461, '　　6.世间成事，不求其绝对圆满，留一份不足，可得无限美好。', NULL);
INSERT INTO `welcome` VALUES (463, '　　7.记住：你是你生命的船长，走自己的路，何必在乎其它。', NULL);
INSERT INTO `welcome` VALUES (465, '　　8.你要做多大的事情，就该承受多大的压力。', NULL);
INSERT INTO `welcome` VALUES (467, '　　9.生活充满了选择，而生活的.态度就是一切。', NULL);
INSERT INTO `welcome` VALUES (469, '　　10.死亡教会人一切，如同考试之后公布的结果――虽然恍然大悟，但为时晚矣!', NULL);
INSERT INTO `welcome` VALUES (471, '　　11.年轻是我们唯一拥有权利去编织梦想的时光。', NULL);
INSERT INTO `welcome` VALUES (473, '　　12.自信是成功的第一诀窍。', NULL);
INSERT INTO `welcome` VALUES (475, '　　13.不论成功还是失败，都是系于自己。——朗费罗', NULL);
INSERT INTO `welcome` VALUES (477, '　　14.一个不注意小事情的人，永远不会成功大事业。——戴尔·卡耐基', NULL);
INSERT INTO `welcome` VALUES (479, '　　15.不是每一次努力都有收获，但是，每一次收获都必须努力。', NULL);
INSERT INTO `welcome` VALUES (481, '　　经典语录励志名言12', NULL);
INSERT INTO `welcome` VALUES (482, '　　1.不相信奇迹的人永远都不会创造奇迹。', NULL);
INSERT INTO `welcome` VALUES (484, '　　2.不要祈求别人遭遇灾难，最好的方法就是不断自我超越。', NULL);
INSERT INTO `welcome` VALUES (486, '　　3.不要做爱情弱智，关注自己的幸福不等于将就别人，与有希望与你共命运的人在一起才理智。', NULL);
INSERT INTO `welcome` VALUES (488, '　　4.不管你去往何方，不管将来迎接你的是什么，请你带着阳光般的心情启程。', NULL);
INSERT INTO `welcome` VALUES (490, '　　5.长大和老去，也就意味着那些你曾经以为不能接受和无法承受的东西，而今居然都可以微笑着接受，也都可以承受了。', NULL);
INSERT INTO `welcome` VALUES (492, '　　6.父母更年期陪他一起度过，且宽容的看待一切，你也会有这么一天。', NULL);
INSERT INTO `welcome` VALUES (494, '　　7.与其临渊羡鱼，不如退而结网。', NULL);
INSERT INTO `welcome` VALUES (496, '　　8.与跟生命有关的人耗着，少与生命无关的事耗。', NULL);
INSERT INTO `welcome` VALUES (498, '　　9.小鸟只有展翅才能飞翔，孩子多动脑的同时还要多动手。', NULL);
INSERT INTO `welcome` VALUES (500, '　　10.山顶对我们半山腰的人来说并不遥远。', NULL);
INSERT INTO `welcome` VALUES (502, '　　11.山河在，不愁草木不生；壮志在，不愁事业不成!', NULL);
INSERT INTO `welcome` VALUES (504, '　　12.山涧的泉水经过一路曲折,才唱出一支美妙的歌。', NULL);
INSERT INTO `welcome` VALUES (506, '　　13.你若将过去抱的太紧，怎么能腾出手来拥抱现在?', NULL);
INSERT INTO `welcome` VALUES (508, '　　14.天若赐我辉煌，我定比天张狂。', NULL);
INSERT INTO `welcome` VALUES (510, '　　15.天塌下来，有个高的人帮你扛着，可是你能保证，天塌下来的时候，个儿高的人没在弯腰吗?之后，还不是得靠自己!', NULL);
INSERT INTO `welcome` VALUES (512, '　　16.无论结局怎样：总比懒惰要强。', NULL);
INSERT INTO `welcome` VALUES (514, '　　17.不自重者，取辱。不自长者，取祸。不自满者，受益。不自足者，博闻。', NULL);
INSERT INTO `welcome` VALUES (516, '　　18.不向前走，不知路远；不努力学习，不明白真理。', NULL);
INSERT INTO `welcome` VALUES (518, '　　19.不好问别人为你做了什么，而要问你为别人做了什么。', NULL);
INSERT INTO `welcome` VALUES (520, '　　20.不怕别人看不起，只怕自己不争气!', NULL);
INSERT INTO `welcome` VALUES (522, '　　21.为了对得起来未来的自己，你不能贪图安逸。努力不是为了现在得到别人的夸耀，而是为了将来活得有底气的尊严。', NULL);
INSERT INTO `welcome` VALUES (524, '　　22.计较的太多就成了一种羁绊，迷失的太久便成了一种痛苦。过多的在乎会减少人生的乐趣，看淡了一切也就多了生命的释然。', NULL);
INSERT INTO `welcome` VALUES (526, '　　23.未遭拒绝的成功决不会长久。', NULL);
INSERT INTO `welcome` VALUES (528, '　　24.世上没有绝望的处境，只有对处境绝望的人。', NULL);
INSERT INTO `welcome` VALUES (530, '　　25.世界上有两种东西是怎样都无法挽回的，一种是过去的时间；另一种就是说过的话。', NULL);
INSERT INTO `welcome` VALUES (532, '　　26.世界上最美的相遇是擦肩，最美的誓言是谎言，最美的爱都在昨天，最美的思念是永不相见。', NULL);
INSERT INTO `welcome` VALUES (534, '　　27.业绩辉煌全力以赴。', NULL);
INSERT INTO `welcome` VALUES (536, '　　28.只有当全体居民都参加管理工作时，才能彻底进行反官僚主义的斗争，才能完全战胜官僚主义。', NULL);
INSERT INTO `welcome` VALUES (538, '　　29.只有知道如何停止的人，才知道如何加快速度。', NULL);
INSERT INTO `welcome` VALUES (540, '　　30.生命之舟将面对险滩，面对激流，弱者会选择逃避和放弃，而强者则会选择面对和挑战。人生中无限的乐趣都在于对人生的挑战之中', NULL);
INSERT INTO `welcome` VALUES (542, '　　31、只有智者，才会从平时的细节发现答案，而不是等答案。', NULL);
INSERT INTO `welcome` VALUES (544, '　　32、人生最大的敬佩是早上起得来，生命最大的安慰是晚上睡得香。', NULL);
INSERT INTO `welcome` VALUES (546, '　　33、松驰的琴弦，永远奏不出美妙的乐曲。', NULL);
INSERT INTO `welcome` VALUES (548, '　　34、生命并不是要超越他人，而是要超越自我。', NULL);
INSERT INTO `welcome` VALUES (550, '　　35、人生应该树立目标，否则你的精力会白白浪费。', NULL);
INSERT INTO `welcome` VALUES (552, '　　36、用时间和心看人，而并不是用眼晴。', NULL);
INSERT INTO `welcome` VALUES (554, '　　37、很多时候不是命运的不幸，而是自己还不够努力。', NULL);
INSERT INTO `welcome` VALUES (556, '　　38、改变自己，你才有自信，梦想才会慢慢的实现。', NULL);
INSERT INTO `welcome` VALUES (558, '　　39、一心朝着自己目标前进的人，整个世界都会给他让路。', NULL);
INSERT INTO `welcome` VALUES (560, '　　40、永远不要对任何事感到后悔，因为它曾经一度就是你想要的。', NULL);
INSERT INTO `welcome` VALUES (562, '　　41、设立目标，然后把目标细化为每一步的实际行动。', NULL);
INSERT INTO `welcome` VALUES (564, '　　42、相信自己，只要你足够勇敢，没有熬不过的艰难。', NULL);
INSERT INTO `welcome` VALUES (566, '　　43、贪图省力的船夫，目标永远下游。', NULL);
INSERT INTO `welcome` VALUES (568, '　　44、遇到困难时不要抱怨，既然改变不了过去，那么就努力改变未来。', NULL);
INSERT INTO `welcome` VALUES (570, '　　45、一旦开始讨厌某人，无论其做什么都会让你厌烦。', NULL);
INSERT INTO `welcome` VALUES (572, '　　46、今天应做的事没有做，明天再早也是耽误了。', NULL);
INSERT INTO `welcome` VALUES (574, '　　47、不要沉沦，在任何环境中你都可以选择奋起。', NULL);
INSERT INTO `welcome` VALUES (576, '　　48、在比夜更深的地方，一定有比夜更黑的眼睛。', NULL);
INSERT INTO `welcome` VALUES (578, '　　49、所有人都说我很坚强，唯独你告诉我别逞强。', NULL);
INSERT INTO `welcome` VALUES (580, '　　50、求人不如求己，真正能够解忧的，只有我们自己。', NULL);
INSERT INTO `welcome` VALUES (582, '　　51、成功不是因为聪明，而是因为敢于接受与尝试!想干的人永远在找方法，不想干的人永远在找理由!世界上没有走不通的路，只有想', NULL);
INSERT INTO `welcome` VALUES (584, '　　52、实现自己既定的目标，必须能耐得住寂寞单干。', NULL);
INSERT INTO `welcome` VALUES (586, '　　53、岸边的奇花异草，是拘留不住奔腾向前的江水的。', NULL);
INSERT INTO `welcome` VALUES (588, '　　54、没有天生的信心，只有不断培养的信心。', NULL);
INSERT INTO `welcome` VALUES (590, '　　55、不登高山不知天之高也，不临深溪不知地之厚也。', NULL);
INSERT INTO `welcome` VALUES (592, '　　56、酸甜苦辣都是营养，成功失败都是经验。', NULL);
INSERT INTO `welcome` VALUES (594, '　　57、失败并不意味你浪费了时间和生命，失败表明你有理由重新开始。', NULL);
INSERT INTO `welcome` VALUES (596, '　　58、要沉着地着手去做一件事，但一旦开端，就要坚持究竟。', NULL);
INSERT INTO `welcome` VALUES (598, '　　59、只有相信自已，才能让别人肯定的相信你。', NULL);
INSERT INTO `welcome` VALUES (600, '　　60、用心者相信只有推动自我才能推动世界，只要推动自我就能推动世界。', NULL);
INSERT INTO `welcome` VALUES (602, '　　61、待人退一步，爱人宽一寸，人生自然活得很快乐。', NULL);
INSERT INTO `welcome` VALUES (604, '　　62、一个人害怕的事，往往是他应该做的事。', NULL);
INSERT INTO `welcome` VALUES (606, '　　63、要用行动控制情绪，不要让情绪控制行动；要让心灵启迪智慧，不能让耳朵支配心灵。人与人之间的差别，主要差在两耳之间的那块', NULL);
INSERT INTO `welcome` VALUES (608, '　　64、最重要的就是不要去看远方模糊的，而要做手边清楚的事。', NULL);
INSERT INTO `welcome` VALUES (610, '　　65、不是世界选择了你，是你选择了这个世界。既然没有净土，不如静心；既然没有如愿，不如释然。', NULL);
INSERT INTO `welcome` VALUES (612, '　　66、不从泥泞不堪的小道上迈步，就踏不上铺满鲜花的大路。', NULL);
INSERT INTO `welcome` VALUES (614, '　　67、努力吧，直到你的账户余额，看起来像身份证号码一样长。', NULL);
INSERT INTO `welcome` VALUES (616, '　　68、成功的门往往虚掩着，只要你勇敢去推，它就会豁然洞开。', NULL);
INSERT INTO `welcome` VALUES (618, '　　69、你不能上传爱，你没法下载时间，你也搜索不到所有关于生活的答案。有一部分生活，只能真实地过。', NULL);
INSERT INTO `welcome` VALUES (620, '　　70、你怎样生活，完全由你自己决定。自己的选择，自己承担后果。', NULL);
INSERT INTO `welcome` VALUES (622, '　　71、遇到困难时不要放弃，要记住，坚持到底就是胜利。', NULL);
INSERT INTO `welcome` VALUES (624, '　　72、得不到的就是伤，忘不了的就是痛，过不去的就是坎，没行动的就是梦。', NULL);
INSERT INTO `welcome` VALUES (626, '　　73、无论今天发生多么糟糕的事，都不应该感到悲伤，因为今天是你往后日子里最年轻的一天了。', NULL);
INSERT INTO `welcome` VALUES (628, '　　74、当我们失去的时候，才知道自己曾经拥有。', NULL);
INSERT INTO `welcome` VALUES (630, '　　75、别想一下造出大海，必须先由小河川开始。', NULL);
INSERT INTO `welcome` VALUES (632, '　　76、生命的道路上永远没有捷径可言，只有脚踏实地走下去。', NULL);
INSERT INTO `welcome` VALUES (634, '　　77、不是人人都能活的低调，可以低调的基础是随时都能高调。', NULL);
INSERT INTO `welcome` VALUES (636, '　　78、梦想是一个人奋斗的动力，梦想是一个人动力的源泉。', NULL);
INSERT INTO `welcome` VALUES (638, '　　79、永远别放弃自己，哪怕所有人都放弃了你。', NULL);
INSERT INTO `welcome` VALUES (640, '　　80、把困难踩在脚下，你才会站得更高。生命就是一次次蜕变的过程。人生往往只有经历各种艰难和折磨，才能真正增加生命的厚度，悟', NULL);
INSERT INTO `welcome` VALUES (642, '　1、人生最大的无知是怨尤：无知就是不明白、不明理。因为不明理，所以遇到不如意时就怨天尤人，上怨苍天不保佑，不尤世人不帮忙。对', NULL);
INSERT INTO `welcome` VALUES (644, '　　2、人生最大的希望是平安：财富、名位是大家所热衷追求的。但是如果求得了财富、名位，却失去了平安，这样的人生没有希望，没有意', NULL);
INSERT INTO `welcome` VALUES (646, '　　3、人生最大的毛病是自私：人是血肉之躯，躯体上难免会有老、病、死。其实心理上的毛病更大，心理上的毛病是什么?是自私。人由于', NULL);
INSERT INTO `welcome` VALUES (648, '　　4、人生最大的悲哀是无知：人生最大的悲哀，不是没有钱，没有势，也不是没有地位，没有职业等等。最大的悲哀是无知、不明理，不能', NULL);
INSERT INTO `welcome` VALUES (650, '　　5、人生最大的能源是信仰：我们常说要开发能源，能源不一定是指山中的矿产、海底的珍宝;也不一定是天然气、太阳能，人生最大的\'', NULL);
INSERT INTO `welcome` VALUES (652, '　　6、人生最大的拥有是感恩：什么人最富有?什么人最贫穷?贫穷的人是随时想从别人那里获得，富有的人则是心存感谢，时时想要施与别', NULL);
INSERT INTO `welcome` VALUES (654, '　　7、人生最大的失败是矣慢：所谓“谦受益，满招损”，一个人如果自高自大、贡高我慢，不管走到哪里，都不会受欢迎，所以骄傲、矣慢', NULL);
INSERT INTO `welcome` VALUES (656, '　　8、人生最大的过失是侵犯：我们最大的过失，就是侵犯。侵犯人家的生命、财富，侵犯人焉。', NULL);
INSERT INTO `welcome` VALUES (658, '　　9、人生最大的困扰是是非：有人说：有人的地方就有是非。是非让我们感到苦恼不已，但是所谓“是非朝朝有，不听自然无”，只要我们', NULL);
INSERT INTO `welcome` VALUES (660, '　　10、人生最大的烦恼是欲望：有人说，世间充满忧悲苦恼，因为娑婆世界本来就是堪忍的世界，所以充满烦恼、痛苦。譬如我们对于钱财', NULL);
INSERT INTO `welcome` VALUES (662, '　　11、人生最大的错误是邪见：一般人犯了错误，如果是事情上的错误，还可以改正。最大的错误就是邪见，也就是思想上有了误解，认识', NULL);
INSERT INTO `welcome` VALUES (664, '　　12、人生最大的发心是利众：我们经常听到佛教徒劲人要发心。到底要发什么心呢?要发善心、好心，也就是利益大众。譬如我说一句话', NULL);
INSERT INTO `welcome` VALUES (666, '　　13、什么是最大的修养呢?就是宽容。所谓“严以律已，宽以待人”，我们待人要宽厚、要包容，不管别人待你与不好，都能包容，这才', NULL);
INSERT INTO `welcome` VALUES (668, '　　14、人生最大的美德是慈悲：一个人最大的美德，不是长得很漂亮，也不是拥有很多的财富，很多的才能，人生最大的美德是慈悲。所以', NULL);
INSERT INTO `welcome` VALUES (670, '　　15、人生最大的收获是满足：每个人都希望自己有所得，有所成就，有所收获。什么是最大的收获呢?要能满足，你不满足，就是睡在天', NULL);
INSERT INTO `welcome` VALUES (672, '　　16、人生最大的本钱是尊严：人之所以为人，就是因为尊严，为此什么都可以牺牲，但是在牺牲折磨的最后关头还是要保留最后的一点尊', NULL);
INSERT INTO `welcome` VALUES (674, '　　17、人生最大勇气是认错：人要有勇气，勇气不是跟人有打架、殴斗，也不是跟人家争执、计较，最大的勇气是自我认错。觉得我不应该', NULL);
INSERT INTO `welcome` VALUES (676, '　　18、人生最大的敌人是自己：一般人总是把对自己不友好的人，当成是自己的敌人，其实人生最大的敌人不是别人，是我们自己。因为外', NULL);
INSERT INTO `welcome` VALUES (678, '　　19、人生最大的欢喜是法乐：一般人总是从外在物欲感官上寻求快乐。譬如一句赞美的话，就欢喜半天，但是赞美的欢喜一下就过去了;', NULL);
INSERT INTO `welcome` VALUES (680, '　　20、人生最大的忧虑是生死：生死是人生的两件大事，也是最大的忧虑。生时争名夺利，尔虞我诈;一旦无常来临，又恐事业、爱情、财', NULL);
INSERT INTO `welcome` VALUES (682, '　　21、 不当家不知柴米贵，不养儿，不知报母恩。', NULL);
INSERT INTO `welcome` VALUES (684, '　　22、 恩欲报，怨欲忘;报怨短，报恩长。', NULL);
INSERT INTO `welcome` VALUES (686, '　　23、 感恩是精神上的一种宝藏。——洛克', NULL);
INSERT INTO `welcome` VALUES (688, '　　24、 感恩即是灵魂上的健康。——尼采', NULL);
INSERT INTO `welcome` VALUES (690, '　　25、 没有感恩就没有真正的美德。——卢梭', NULL);
INSERT INTO `welcome` VALUES (692, '　　26、 人世间最美丽的情景是出现在当我们怀念到母亲的时候。——莫泊桑', NULL);
INSERT INTO `welcome` VALUES (694, '　　27、 家庭之所以重要，主要是因为它能使父母获得情感。——罗素', NULL);
INSERT INTO `welcome` VALUES (696, '　　28、 父母的美德是一笔巨大的财富。——贺拉斯', NULL);
INSERT INTO `welcome` VALUES (698, '　　29、 全世界的母亲是多么的相象!她们的心始终一样，都有一颗极为纯真的赤子之心。——惠特曼，火不能灭。——前苏联谚语', NULL);
INSERT INTO `welcome` VALUES (700, '　　30、 养儿方知娘艰辛，养女方知谢娘恩。——日本谚语', NULL);
INSERT INTO `welcome` VALUES (702, '　　31、 做人就像蜡烛一样，有一分热，发一分光，给人以光明，给以温暖。——肖楚女', NULL);
INSERT INTO `welcome` VALUES (704, '　　32、 生活需要一颗感恩的心来创造，一颗感恩的心需要生活来滋养。——王符', NULL);
INSERT INTO `welcome` VALUES (706, '　　33、 蜜蜂从花中啜蜜，离开时营营的道谢。浮夸的蝴蝶却相信花是应该向他道谢的。——泰戈尔', NULL);
INSERT INTO `welcome` VALUES (708, '　　34、 人家帮我，永志不忘;我帮人家，莫记心上。——华罗庚', NULL);
INSERT INTO `welcome` VALUES (710, '　　35、 每一种恩惠都有一枚倒钩，它将钩住吞食那份恩惠的嘴巴，施恩者想把他拖到哪里就得到那里。——堂恩', NULL);

SET FOREIGN_KEY_CHECKS = 1;
