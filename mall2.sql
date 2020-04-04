/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50561
Source Host           : localhost:3306
Source Database       : mall2

Target Server Type    : MYSQL
Target Server Version : 50561
File Encoding         : 65001

Date: 2020-04-04 17:51:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES ('1', 'admin', 'admin');
INSERT INTO `admin_user` VALUES ('2', 'hfb', 'hfb');
INSERT INTO `admin_user` VALUES ('3', 'admin1', 'admin1');

-- ----------------------------
-- Table structure for classification
-- ----------------------------
DROP TABLE IF EXISTS `classification`;
CREATE TABLE `classification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classification
-- ----------------------------
INSERT INTO `classification` VALUES ('1', '滋补调养', '0', '1');
INSERT INTO `classification` VALUES ('2', '风湿骨科', '0', '1');
INSERT INTO `classification` VALUES ('3', '肠胃用药', '0', '1');
INSERT INTO `classification` VALUES ('4', '皮肤用药', '0', '1');
INSERT INTO `classification` VALUES ('5', '补肾', '2', '2');
INSERT INTO `classification` VALUES ('6', '补血补气', '1', '2');
INSERT INTO `classification` VALUES ('7', '跌打损伤', '2', '2');
INSERT INTO `classification` VALUES ('10', '关节炎', '2', '2');
INSERT INTO `classification` VALUES ('11', '胃炎胃痛', '3', '2');
INSERT INTO `classification` VALUES ('12', '保健品', '0', '1');
INSERT INTO `classification` VALUES ('13', '维生素', '12', '2');
INSERT INTO `classification` VALUES ('14', '便秘', '3', '2');
INSERT INTO `classification` VALUES ('15', '皮炎湿疹', '4', '2');
INSERT INTO `classification` VALUES ('16', '脚气', '4', '2');
INSERT INTO `classification` VALUES ('17', '补贴补锌', '12', '2');

-- ----------------------------
-- Table structure for date_count
-- ----------------------------
DROP TABLE IF EXISTS `date_count`;
CREATE TABLE `date_count` (
  `Id` int(10) NOT NULL,
  `date` date NOT NULL,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of date_count
-- ----------------------------
INSERT INTO `date_count` VALUES ('9', '2020-03-30', '4');
INSERT INTO `date_count` VALUES ('10', '2020-03-31', '1');
INSERT INTO `date_count` VALUES ('11', '2020-02-25', '2');
INSERT INTO `date_count` VALUES ('12', '2020-01-31', '5');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `order_time` datetime DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('6', '测试', '拉拉拉', '2020-03-23 13:44:12', '1212121', '4', '5999', '4');
INSERT INTO `order` VALUES ('7', 'hziee', '出来', '2020-03-24 12:52:51', '12345678901', '2', '640', '5');
INSERT INTO `order` VALUES ('8', 'hziee', '出来', '2020-03-24 12:53:38', '12345678901', '2', '640', '5');

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `sub_total` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES ('1', '1', '1', '10', '8888');
INSERT INTO `order_item` VALUES ('2', '2', '2', '9', '17998');
INSERT INTO `order_item` VALUES ('3', '2', '3', '11', '78');
INSERT INTO `order_item` VALUES ('4', '1', '3', '13', '5999');
INSERT INTO `order_item` VALUES ('5', '1', '4', '9', '8999');
INSERT INTO `order_item` VALUES ('6', '1', '5', '13', '5999');
INSERT INTO `order_item` VALUES ('7', '1', '6', '13', '5999');
INSERT INTO `order_item` VALUES ('8', '2', '7', '19', '640');
INSERT INTO `order_item` VALUES ('9', '2', '8', '19', '640');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `csid` int(11) DEFAULT NULL,
  `desc` varchar(1000) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `is_hot` int(11) DEFAULT NULL,
  `market_price` double DEFAULT NULL,
  `pdate` datetime DEFAULT NULL,
  `shop_price` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('15', '13', '维生素c', '/mall/admin/product/img/ADBC547BEB8808B2EF85FA21B75F61.jpg', '1', '100', '2020-03-24 12:37:47', '50', '维生素c1');
INSERT INTO `product` VALUES ('16', '13', '恒健维生素C片VC 维C100片补充维生素C妊娠呕吐发少发黄vc片药用', '/mall/admin/product/img/527E80636987DE66913B2352681776.jpg', '1', '12', '2020-03-24 12:31:16', '10', '恒健维生素C片VC 维C100片补充维生素C妊娠呕吐发少发黄vc片药用');
INSERT INTO `product` VALUES ('17', '13', '21金维他多维元素100片成人男女补充矿物质氨基酸维生素C', '/mall/admin/product/img/988BB6D8A49C5F08EA1D0D1A3D94F0.jpg', '0', '218', '2020-03-24 12:34:54', '173', '21金维他多维元素100片成人男女补充矿物质氨基酸维生素C');
INSERT INTO `product` VALUES ('18', '5', '北京同仁堂六味地黄丸360丸水蜜丸补肾虚亏六位地黄地丸', '/mall/admin/product/img/3C16DE1AD2DF0B0AF9BCBAE9F36A1F.jpg', '1', '60', '2020-03-24 12:37:32', '55', '北京同仁堂六味地黄丸360丸水蜜丸补肾虚亏六位地黄地丸');
INSERT INTO `product` VALUES ('19', '5', '汇仁肾宝片126片补肾虚肾阳虚汇仁牌肾保片汇源贤宝片正品', '/mall/admin/product/img/BD577C815C5592C26AE6B53DAB9D4E.jpg', '1', '360', '2020-03-24 12:38:53', '320', '汇仁肾宝片126片补肾虚肾阳虚汇仁牌肾保片汇源贤宝片正品');
INSERT INTO `product` VALUES ('21', '6', '太太静心口服液10支改善睡眠安神更年期神经衰弱治失眠药', '/mall/admin/product/img/F5AC94724EAEB29AB051C0DEDA2FE6.jpg', '0', '360', '2020-03-24 12:43:44', '329', '太太静心口服液10支改善睡眠安神更年期神经衰弱治失眠药');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addr` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('4', '拉拉拉啊啦啦', 'sjfksjfsjf@11.com', 'lc`', '123456', '18369024922', 'chenchenchen');
INSERT INTO `user` VALUES ('5', '嘻嘻嘻', '923849234@qq.com', 'test', '123456', '18369024922', 'test');
