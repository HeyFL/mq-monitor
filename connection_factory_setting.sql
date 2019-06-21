/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 80012
Source Host           : 127.0.0.1:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-06-21 15:50:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for connection_factory_setting
-- ----------------------------
DROP TABLE IF EXISTS `connection_factory_setting`;
CREATE TABLE `connection_factory_setting` (
  `factory_name` varchar(128) NOT NULL,
  `mq_address` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `virtual_host` varchar(255) NOT NULL,
  PRIMARY KEY (`factory_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of connection_factory_setting
-- ----------------------------
INSERT INTO `connection_factory_setting` VALUES ('gds-wms', '127.0.0.1:5672', 'mq_pds', 'pds_dev', 'gds-wms');
INSERT INTO `connection_factory_setting` VALUES ('gds-wos-aupera', '127.0.0.1:5672', 'mq_pds', 'pds_dev', 'gds-wos-aupera');
INSERT INTO `connection_factory_setting` VALUES ('gds-wos-uspdxa', '127.0.0.1:5672', 'mq_pds', 'pds_dev', 'gds-wos-uspdxa');

-- ----------------------------
-- Table structure for monitor_queue
-- ----------------------------
DROP TABLE IF EXISTS `monitor_queue`;
CREATE TABLE `monitor_queue` (
  `queue_name` varchar(128) NOT NULL,
  `factory_name` varchar(128) NOT NULL,
  `warn_msg_format` varchar(255) DEFAULT NULL,
  `warn_msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `warn_who` varchar(255) NOT NULL,
  `warn_way` varchar(64) NOT NULL,
  `warn_threshold` mediumint(6) NOT NULL,
  UNIQUE KEY `queue_name` (`queue_name`,`factory_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of monitor_queue
-- ----------------------------
INSERT INTO `monitor_queue` VALUES ('GDS_WMS_DISPATCH_Q_CALLBACK_DEAD', 'gds-wms', null, '', '1', '1', '0');
INSERT INTO `monitor_queue` VALUES ('GDS_WMS_WMS_Q_TASK_ASYNC_CONSUME_DEAD', 'gds-wms', null, '', '1', '1', '0');
INSERT INTO `monitor_queue` VALUES ('GDS_WMS_WOS_Q_JOB_FORECAST_DEAD', 'gds-wos-aupera', null, '', '1', '1', '0');
INSERT INTO `monitor_queue` VALUES ('GDS_WMS_WOS_Q_JOB_FORECAST_DEAD', 'gds-wos-uspdxa', null, '', '1', '1', '0');
INSERT INTO `monitor_queue` VALUES ('GDS_WOS_WMS_Q_CALLBACK_DEAD', 'gds-wms', null, '', '1', '1', '0');
