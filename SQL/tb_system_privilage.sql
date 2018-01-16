/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50637
 Source Host           : localhost:3306
 Source Schema         : aqsiqdv

 Target Server Type    : MySQL
 Target Server Version : 50637
 File Encoding         : 65001

 Date: 24/12/2017 14:32:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_system_privilage
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_privilage`;
CREATE TABLE `tb_system_privilage` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '系统权限ID',
  `role_id` int(10) unsigned NOT NULL COMMENT '角色ID',
  `action_id` int(10) unsigned NOT NULL COMMENT '操作ID',
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='系统权限表';

-- ----------------------------
-- Records of tb_system_privilage
-- ----------------------------
BEGIN;
INSERT INTO `tb_system_privilage` VALUES (1, 1, 1);
INSERT INTO `tb_system_privilage` VALUES (2, 1, 2);
INSERT INTO `tb_system_privilage` VALUES (3, 1, 3);
INSERT INTO `tb_system_privilage` VALUES (4, 1, 20);
INSERT INTO `tb_system_privilage` VALUES (5, 1, 14);
INSERT INTO `tb_system_privilage` VALUES (6, 1, 15);
INSERT INTO `tb_system_privilage` VALUES (7, 1, 17);
INSERT INTO `tb_system_privilage` VALUES (8, 1, 19);
INSERT INTO `tb_system_privilage` VALUES (9, 1, 16);
INSERT INTO `tb_system_privilage` VALUES (10, 1, 18);
INSERT INTO `tb_system_privilage` VALUES (11, 1, 21);
INSERT INTO `tb_system_privilage` VALUES (12, 1, 22);
INSERT INTO `tb_system_privilage` VALUES (13, 1, 23);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
