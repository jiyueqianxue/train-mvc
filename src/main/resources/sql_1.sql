-- --------------------------------------------------------
-- 主机:                           47.104.5.219
-- 服务器版本:                        5.7.21 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 xzz_server 的数据库结构
CREATE DATABASE IF NOT EXISTS `xzz_server` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `xzz_server`;

-- 导出  表 xzz_server.product_base 结构
CREATE TABLE IF NOT EXISTS `product_base` (
  `id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '产品ID主键',
  `product_name` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '产品名称',
  `product_img` varchar(200) CHARACTER SET utf8 NOT NULL COMMENT '产品图片',
  `product_desc` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '产品描述',
  `product_status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '产品状态',
  `category_id` bigint(50) NOT NULL COMMENT '类目ID',
  `type_desc` varchar(50) NOT NULL COMMENT '类型描述',
  `service_area` varchar(100) NOT NULL COMMENT '服务区域',
  `provider` varchar(50) NOT NULL COMMENT '服务提供者',
  `mobile_phone` char(11) NOT NULL COMMENT '手机号',
  `sale_price` decimal(50,0) NOT NULL DEFAULT '0' COMMENT '销售价',
  `active_price` decimal(50,0) NOT NULL DEFAULT '0' COMMENT '活动价',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- 数据导出被取消选择。
-- 导出  表 xzz_server.product_order 结构
CREATE TABLE IF NOT EXISTS `product_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '产品订单ID',
  `order_no` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '订单编号',
  `order_type` tinyint(3) NOT NULL COMMENT '订单状态',
  `amount` decimal(50,0) NOT NULL DEFAULT '0' COMMENT '交易金额',
  `order_status` tinyint(3) NOT NULL COMMENT '订单状态',
  `product_id` bigint(50) NOT NULL COMMENT '产品ID',
  `user_id` bigint(50) NOT NULL COMMENT '用户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 数据导出被取消选择。
-- 导出  表 xzz_server.user_base 结构
CREATE TABLE IF NOT EXISTS `user_base` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `real_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '真实姓名',
  `wechat_nick` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '昵称',
  `avatar_url` varchar(200) CHARACTER SET utf8 NOT NULL COMMENT '头像',
  `gender` tinyint(2) NOT NULL COMMENT '性别',
  `phone` char(11) CHARACTER SET utf8 DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '电子邮箱',
  `city` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '所在城市',
  `province` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '所在省份',
  `country` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '所在国家',
  `open_id` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '小程序ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `upate_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
