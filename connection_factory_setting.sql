/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 80012
Source Host           : 127.0.0.1:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-08-16 10:59:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for connection_factory_setting
-- ----------------------------
DROP TABLE IF EXISTS `connection_factory_setting`;
CREATE TABLE `connection_factory_setting` (
  `factory_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT ''本连接的名称'',
  `mq_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT ''MQ IP地址'',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT ''用户名'',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT ''密码'',
  `virtual_host` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT ''MQ的VH'',
  PRIMARY KEY (`factory_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for monitor_queue
-- ----------------------------
DROP TABLE IF EXISTS `monitor_queue`;
CREATE TABLE `monitor_queue` (
  `queue_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT ''需要监控的队列名称'',
  `factory_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT ''VH名称'',
  `warn_msg_format` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT ''告警消息模板(默认为 VH：{0} 队列：{1} 消息当前消息条数{2}  超过阈值：{3} 请及时排查问题)'',
  `warn_msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT ''告警消息'',
  `warn_who` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT ''告警谁(如 UserId等)'',
  `warn_way` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT ''告警方式'',
  `warn_threshold` mediumint(6) unsigned zerofill NOT NULL COMMENT ''告警阈值'',
  UNIQUE KEY `queue_name` (`queue_name`,`factory_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
