/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80028 (8.0.28)
 Source Host           : localhost:3306
 Source Schema         : android_exam1

 Target Server Type    : MySQL
 Target Server Version : 80028 (8.0.28)
 File Encoding         : 65001

 Date: 12/07/2023 14:26:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t6
-- ----------------------------
DROP TABLE IF EXISTS `t6`;
CREATE TABLE `t6`  (
  `id` int NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uploadTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t6
-- ----------------------------
INSERT INTO `t6` VALUES (1, '发挥法治作用保障生态文明高地建设', '法制日报', '2023-06-09', '1.jpeg');
INSERT INTO `t6` VALUES (2, '打击证劵领域新型诈骗犯罪', '经济日报', '2023-06-09', '2.jpeg');
INSERT INTO `t6` VALUES (3, '法治让人与海洋更和谐', '法制日报', '2023-06-08', '3.jpeg');

SET FOREIGN_KEY_CHECKS = 1;
