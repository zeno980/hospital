/*
 Navicat Premium Data Transfer

 Source Server         : zakary
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : hospital

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 01/04/2019 10:07:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for doctorDao
-- ----------------------------
DROP TABLE IF EXISTS `doctorDao`;
CREATE TABLE `doctorDao`  (
  `doctorId` int(11) NOT NULL,
  `doctorName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `doctorDepartment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `doctorPosition` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `doctorGender` enum('男','女') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` int(1) NOT NULL,
  `doctorTel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`doctorId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doctorDao
-- ----------------------------
INSERT INTO `doctorDao` VALUES (1, 'root', 'root', 'root', '男', 0, '', '123456');
INSERT INTO `doctorDao` VALUES (2, 'd2', 'tooth', 'Attending doctorDao', '男', 1, '100', '123456');
INSERT INTO `doctorDao` VALUES (3, 'd3', 'tooth', 'Attending doctorDao', '男', 1, NULL, '123456');
INSERT INTO `doctorDao` VALUES (4, 'd4', 'tooth', 'Attending doctorDao', '男', 1, NULL, '123456');
INSERT INTO `doctorDao` VALUES (5, 'zakary', 'fff', 'ggg', '男', 1, '123456', '12345678');
INSERT INTO `doctorDao` VALUES (6, 'test', 'test', 'test', '女', 1, '1357', '12345');
INSERT INTO `doctorDao` VALUES (7, 'szx', 'szx', 'szx', '男', 1, '123456789', '123456789');
INSERT INTO `doctorDao` VALUES (8, 'qe', 'qe', 'qe', '男', 1, '123', '13345');
INSERT INTO `doctorDao` VALUES (9, 'dsf', 'fsd', 'sf', '女', 1, '123324', '123456');

-- ----------------------------
-- Table structure for drug
-- ----------------------------
DROP TABLE IF EXISTS `drug`;
CREATE TABLE `drug`  (
  `drugId` int(11) NOT NULL,
  `drugName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `drugPrice` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`drugId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of drug
-- ----------------------------
INSERT INTO `drug` VALUES (1, 'drug1', 1.00);
INSERT INTO `drug` VALUES (2, 'drug2', 2.00);
INSERT INTO `drug` VALUES (3, 'drug3', 3.00);

-- ----------------------------
-- Table structure for hlist
-- ----------------------------
DROP TABLE IF EXISTS `hlist`;
CREATE TABLE `hlist`  (
  `hlistId` int(10) NOT NULL,
  `doctorId` int(10) NULL DEFAULT NULL,
  `patientId` int(10) NULL DEFAULT NULL,
  `sickroomId` int(10) NULL DEFAULT NULL,
  `sickbedId` int(10) NULL DEFAULT NULL,
  `inhTime` datetime(6) NULL DEFAULT NULL,
  `outhTime` datetime(6) NULL DEFAULT NULL,
  `treatmentName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `hlistFee` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`hlistId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hlist
-- ----------------------------
INSERT INTO `hlist` VALUES (1, 1, 1, 1, 1, '2019-03-15 09:38:45.000000', '2019-03-29 09:38:51.000000', 'tooth', '100');
INSERT INTO `hlist` VALUES (2, 2, 2, 1, 2, '2019-03-28 09:39:16.000000', '2019-03-30 09:39:20.000000', 'tooth', '200');
INSERT INTO `hlist` VALUES (3, 3, 3, 1, 3, '2019-03-28 09:39:43.000000', '2019-03-29 09:39:47.000000', 'tooth', '300');

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient`  (
  `patientId` int(11) NOT NULL,
  `patientName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `patientGender` enum('男','女') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `patientAge` int(11) NOT NULL,
  `patientTel` int(11) NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限类型',
  PRIMARY KEY (`patientId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of patient
-- ----------------------------
INSERT INTO `patient` VALUES (1, 'p1', '男', 20, NULL, '2');
INSERT INTO `patient` VALUES (2, 'p2', '男', 20, NULL, '2');
INSERT INTO `patient` VALUES (3, 'p3', '男', 20, NULL, '2');

-- ----------------------------
-- Table structure for prescription
-- ----------------------------
DROP TABLE IF EXISTS `prescription`;
CREATE TABLE `prescription`  (
  `prescriptionId` int(11) NOT NULL,
  `doctorId` int(11) NOT NULL,
  `patientId` int(11) NOT NULL,
  `drugId` int(11) NOT NULL,
  `drugNum` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`prescriptionId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sickbed
-- ----------------------------
DROP TABLE IF EXISTS `sickbed`;
CREATE TABLE `sickbed`  (
  `sickbedId` int(11) NOT NULL,
  `sickroomId` int(11) NULL DEFAULT NULL,
  `sickbedState` enum('empty','full') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sickbedId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sickbed
-- ----------------------------
INSERT INTO `sickbed` VALUES (1, 1, 'full');
INSERT INTO `sickbed` VALUES (2, 1, 'full');
INSERT INTO `sickbed` VALUES (3, 1, 'full');
INSERT INTO `sickbed` VALUES (4, 1, 'empty');

-- ----------------------------
-- Table structure for sickroom
-- ----------------------------
DROP TABLE IF EXISTS `sickroom`;
CREATE TABLE `sickroom`  (
  `sickroomId` int(11) NOT NULL,
  `sickrommFee` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`sickroomId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sickroom
-- ----------------------------
INSERT INTO `sickroom` VALUES (1, 100.00);
INSERT INTO `sickroom` VALUES (2, 200.00);
INSERT INTO `sickroom` VALUES (3, 300.00);

-- ----------------------------
-- Table structure for treatment
-- ----------------------------
DROP TABLE IF EXISTS `treatment`;
CREATE TABLE `treatment`  (
  `doctorid` int(11) NOT NULL,
  `patientid` int(11) NOT NULL,
  `treatmentName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `treatmentTime` datetime(6) NULL,
  `treatmentFee` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`doctorid`, `patientid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of treatment
-- ----------------------------
INSERT INTO `treatment` VALUES (1, 1, 'tooth', '2019-03-13 09:41:58.000000', 100.00);
INSERT INTO `treatment` VALUES (2, 2, 'tooth', '2019-03-22 09:42:18.000000', 200.00);
INSERT INTO `treatment` VALUES (3, 3, 'tooth', '2019-03-30 09:42:34.000000', 300.00);

SET FOREIGN_KEY_CHECKS = 1;
