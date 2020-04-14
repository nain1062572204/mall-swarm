/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : mall

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 14/04/2020 21:53:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oms_cart_item
-- ----------------------------
DROP TABLE IF EXISTS `oms_cart_item`;
CREATE TABLE `oms_cart_item`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL DEFAULT 0,
  `product_sku_id` bigint(20) NOT NULL DEFAULT 0,
  `member_id` bigint(20) NOT NULL DEFAULT 0,
  `quantity` int(11) NOT NULL DEFAULT 1 COMMENT '购买数量',
  `price` decimal(10, 2) NOT NULL COMMENT '添加到购物车的价格',
  `product_pic` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品主图',
  `product_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品名',
  `product_sku_code` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品sku条码',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `delete_status` int(1) NOT NULL DEFAULT 1 COMMENT '删除状态',
  `product_sn` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `product_attr` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '销售属性',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '购物车表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oms_cart_item
-- ----------------------------
INSERT INTO `oms_cart_item` VALUES (5, 23, 146, 1, 1, 4299.00, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/pms_1581494131.03158032.jpg', '小米10', '202003020023001', '2020-03-13 15:35:56', 0, 'dw214', '冰海蓝 256G ');
INSERT INTO `oms_cart_item` VALUES (6, 1, 140, 1, 1, 3299.00, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb48f0aa21853fe648a29523231b736d_840_840.png', '一加7Pro', '202003020001001', '2020-03-13 18:18:29', 0, 'C001', '星雾蓝 128G ');
INSERT INTO `oms_cart_item` VALUES (7, 1, 141, 1, 1, 3699.00, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb48f0aa21853fe648a29523231b736d_840_840.png', '一加7Pro', '202003020001002', '2020-03-13 18:23:23', 0, 'C001', '星雾蓝 256G ');
INSERT INTO `oms_cart_item` VALUES (8, 26, 149, 1, 1, 1699.00, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-05/pms_1575881808.82863486.jpg', 'Redmi K30', '202003050026001', '2020-03-13 19:51:57', 0, 's854', '深海微光 128G ');
INSERT INTO `oms_cart_item` VALUES (9, 22, 145, 1, 1, 3199.00, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb4580c78e806b6aea3451c94c11fa0.png', '一加7T', '202003020022002', '2020-03-14 22:57:15', 0, 'dw5424', '冰川银 256G ');

-- ----------------------------
-- Table structure for oms_order
-- ----------------------------
DROP TABLE IF EXISTS `oms_order`;
CREATE TABLE `oms_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `member_id` bigint(20) NOT NULL DEFAULT 0,
  `coupon_id` bigint(20) NULL DEFAULT 0,
  `order_sn` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '订单编号',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '提交时间',
  `member_username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户帐号',
  `total_amount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '订单总金额',
  `pay_amount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '应付金额（实际支付金额）',
  `freight_amount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '运费金额',
  `promotion_amount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '促销优化金额（促销价、满减、阶梯价）',
  `integration_amount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '积分抵扣金额',
  `coupon_amount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '优惠券抵扣金额',
  `discount_amount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '管理员后台调整订单使用的折扣金额',
  `pay_type` int(1) NULL DEFAULT 0 COMMENT '支付方式：0->未支付；1->支付宝；2->微信',
  `source_type` int(1) NULL DEFAULT 0 COMMENT '订单来源：0->PC订单；1->app订单',
  `status` int(1) NULL DEFAULT 0 COMMENT '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单',
  `order_type` int(1) NULL DEFAULT 0 COMMENT '订单类型：0->正常订单；1->秒杀订单',
  `delivery_company` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '物流公司(配送方式)',
  `delivery_sn` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '物流单号',
  `auto_confirm_day` int(11) NULL DEFAULT 0 COMMENT '自动确认时间（天）',
  `integration` int(11) NULL DEFAULT 0 COMMENT '可以获得的积分',
  `growth` int(11) NULL DEFAULT 0 COMMENT '可以活动的成长值',
  `promotion_info` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '活动信息',
  `bill_type` int(1) NULL DEFAULT 0 COMMENT '发票类型：0->不开发票；1->电子发票；2->纸质发票',
  `bill_header` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '发票抬头',
  `bill_content` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '发票内容',
  `bill_receiver_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '收票人电话',
  `bill_receiver_email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '收票人邮箱',
  `receiver_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '收货人姓名',
  `receiver_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '收货人电话',
  `receiver_post_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '收货人邮编',
  `receiver_province` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '省份/直辖市',
  `receiver_city` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '城市',
  `receiver_region` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '区',
  `receiver_street` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '‘’' COMMENT '街道',
  `receiver_detail_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '详细地址',
  `note` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '订单备注',
  `confirm_status` int(1) NULL DEFAULT 0 COMMENT '确认收货状态：0->未确认；1->已确认',
  `delete_status` int(1) NULL DEFAULT 0 COMMENT '删除状态：0->未删除；1->已删除',
  `use_integration` int(11) NULL DEFAULT 0 COMMENT '下单时使用的积分',
  `payment_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  `delivery_time` datetime(0) NULL DEFAULT NULL COMMENT '发货时间',
  `receive_time` datetime(0) NULL DEFAULT NULL COMMENT '确认收货时间',
  `comment_time` datetime(0) NULL DEFAULT NULL COMMENT '评价时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oms_order
-- ----------------------------
INSERT INTO `oms_order` VALUES (1, 1, 0, '696451119496298496', '2020-04-05 20:08:06', 'rose', 16195.00, 16195.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0, 0, 4, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '王念', '18913570960', NULL, '江苏省', '苏州市', '张家港市', '杨舍镇', NULL, '这是订单备注', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oms_order` VALUES (2, 2, 0, '696749386985635840', '2020-04-06 15:53:18', 'wangnain', 7598.00, 7598.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0, 0, 4, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '王念', '18913570960', NULL, '江苏省', '常州市', '武进区', '牛塘镇', NULL, '这是订单备注', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oms_order` VALUES (3, 1, 0, '696776034313306112', '2020-04-06 17:39:12', 'rose', 16195.00, 16195.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0, 0, 3, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '王念', '18913570960', NULL, '江苏省', '徐州市', '邳州市', '议堂镇', NULL, '这是订单备注', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oms_order` VALUES (4, 1, 0, '697519805774692352', '2020-04-08 18:54:40', 'rose', 4299.00, 4299.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0, 0, 1, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '王念', '18913570960', NULL, '江苏省', '苏州市', '张家港市', '杨舍镇', NULL, '这是订单备注', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oms_order` VALUES (5, 1, 0, '697531709393993728', '2020-04-08 19:41:59', 'rose', 4998.00, 4998.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0, 0, 2, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '王念', '18913570960', NULL, '江苏省', '常州市', '武进区', '牛塘镇', NULL, '这是订单备注', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oms_order` VALUES (6, 1, 0, '697561255300300800', '2020-04-08 21:39:23', 'rose', 16195.00, 16195.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0, 0, 4, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '王念', '18913570960', NULL, '江苏省', '苏州市', '张家港市', '杨舍镇', NULL, '这是订单备注', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oms_order` VALUES (7, 1, 0, '697566555365441536', '2020-04-08 22:00:26', 'rose', 16195.00, 16195.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0, 0, 4, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '王念', '18913570960', NULL, '江苏省', '苏州市', '张家港市', '杨舍镇', NULL, '这是订单备注', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oms_order` VALUES (8, 1, 0, '697876731159117824', '2020-04-09 18:32:58', 'rose', 16195.00, 16195.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0, 0, 4, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '王念', '18913570960', NULL, '江苏省', '常州市', '武进区', '牛塘镇', NULL, '这是订单备注', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oms_order` VALUES (9, 1, 0, '20200412698968922333904896', '2020-04-12 18:52:57', 'rose', 3199.00, 3199.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0, 0, 4, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '王念', '18913570960', '221300', '江苏省', '苏州市', '张家港市', '杨舍镇', '江苏科技大学', '这是订单备注', NULL, 0, 0, NULL, NULL, NULL, NULL, '2020-04-12 18:56:09');
INSERT INTO `oms_order` VALUES (10, 1, 0, '20200412699006897511989248', '2020-04-12 21:23:51', 'rose', 3199.00, 3199.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0, 0, 4, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '王念', '18913570960', NULL, '江苏省', '常州市', '武进区', '牛塘镇', '牛塘工业园区创新力88号', '这是订单备注', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oms_order` VALUES (11, 1, 0, '20200413699359558090358784', '2020-04-13 20:45:12', 'rose', 16195.00, 16195.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0, 0, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '王念', '18913570960', NULL, '江苏省', '苏州市', '张家港市', '杨舍镇', '江苏科技大学', '这是订单备注', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for oms_order_item
-- ----------------------------
DROP TABLE IF EXISTS `oms_order_item`;
CREATE TABLE `oms_order_item`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NULL DEFAULT 0 COMMENT '订单id',
  `order_sn` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '‘’' COMMENT '订单编号',
  `product_id` bigint(20) NULL DEFAULT 0,
  `product_pic` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '‘’',
  `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '‘’',
  `product_sn` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '‘’',
  `product_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '销售价格',
  `product_quantity` int(11) NULL DEFAULT 0 COMMENT '购买数量',
  `product_sku_id` bigint(20) NULL DEFAULT 0 COMMENT '商品sku编号',
  `product_sku_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '‘’' COMMENT '商品sku条码',
  `product_category_id` bigint(20) NULL DEFAULT 0 COMMENT '商品分类id',
  `promotion_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '‘’' COMMENT '商品促销名称',
  `promotion_amount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '商品促销分解金额',
  `coupon_amount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '优惠券优惠分解金额',
  `integration_amount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '积分优惠分解金额',
  `real_amount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '该商品经过优惠后的分解金额',
  `gift_integration` int(11) NULL DEFAULT 0 COMMENT '商品赠送积分',
  `gift_growth` int(11) NULL DEFAULT 0 COMMENT '商品赠送成长值',
  `product_attr` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '‘’' COMMENT '商品销售属性:[{\"key\":\"颜色\",\"value\":\"颜色\"},{\"key\":\"容量\",\"value\":\"4G\"}]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单中的商品' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oms_order_item
-- ----------------------------
INSERT INTO `oms_order_item` VALUES (1, 1, '696451119496298496', 23, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/pms_1581494131.03158032.jpg', '小米10', 'dw214', 4299.00, 1, 146, '202003020023001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"冰海蓝\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `oms_order_item` VALUES (2, 1, '696451119496298496', 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb48f0aa21853fe648a29523231b736d_840_840.png', '一加7Pro', 'C001', 3299.00, 1, 140, '202003020001001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"星雾蓝\"},{\"key\":\"内存容量\",\"value\":\"128G\"}]');
INSERT INTO `oms_order_item` VALUES (3, 1, '696451119496298496', 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb48f0aa21853fe648a29523231b736d_840_840.png', '一加7Pro', 'C001', 3699.00, 1, 141, '202003020001002', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"星雾蓝\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `oms_order_item` VALUES (4, 1, '696451119496298496', 26, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-05/pms_1575881808.82863486.jpg', 'Redmi K30', 's854', 1699.00, 1, 149, '202003050026001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"深海微光\"},{\"key\":\"内存容量\",\"value\":\"128G\"}]');
INSERT INTO `oms_order_item` VALUES (5, 1, '696451119496298496', 22, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb4580c78e806b6aea3451c94c11fa0.png', '一加7T', 'dw5424', 3199.00, 1, 145, '202003020022002', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"冰川银\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `oms_order_item` VALUES (6, 2, '696749386985635840', 27, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/pms_1582886431.60597012.jpg', '腾讯黑鲨游戏手机3', 'heisha3', 3799.00, 2, 155, '202003110027004', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"铠甲灰\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `oms_order_item` VALUES (7, 3, '696776034313306112', 23, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/pms_1581494131.03158032.jpg', '小米10', 'dw214', 4299.00, 1, 146, '202003020023001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"冰海蓝\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `oms_order_item` VALUES (8, 3, '696776034313306112', 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb48f0aa21853fe648a29523231b736d_840_840.png', '一加7Pro', 'C001', 3299.00, 1, 140, '202003020001001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"星雾蓝\"},{\"key\":\"内存容量\",\"value\":\"128G\"}]');
INSERT INTO `oms_order_item` VALUES (9, 3, '696776034313306112', 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb48f0aa21853fe648a29523231b736d_840_840.png', '一加7Pro', 'C001', 3699.00, 1, 141, '202003020001002', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"星雾蓝\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `oms_order_item` VALUES (10, 3, '696776034313306112', 26, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-05/pms_1575881808.82863486.jpg', 'Redmi K30', 's854', 1699.00, 1, 149, '202003050026001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"深海微光\"},{\"key\":\"内存容量\",\"value\":\"128G\"}]');
INSERT INTO `oms_order_item` VALUES (11, 3, '696776034313306112', 22, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb4580c78e806b6aea3451c94c11fa0.png', '一加7T', 'dw5424', 3199.00, 1, 145, '202003020022002', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"冰川银\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `oms_order_item` VALUES (12, 4, '697519805774692352', 23, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/pms_1581494131.03158032.jpg', '小米10', 'dw214', 4299.00, 1, 146, '202003020023001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"冰海蓝\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `oms_order_item` VALUES (13, 5, '697531709393993728', 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb48f0aa21853fe648a29523231b736d_840_840.png', '一加7Pro', 'C001', 3299.00, 1, 140, '202003020001001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"星雾蓝\"},{\"key\":\"内存容量\",\"value\":\"128G\"}]');
INSERT INTO `oms_order_item` VALUES (14, 5, '697531709393993728', 26, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-05/pms_1575881808.82863486.jpg', 'Redmi K30', 's854', 1699.00, 1, 149, '202003050026001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"深海微光\"},{\"key\":\"内存容量\",\"value\":\"128G\"}]');
INSERT INTO `oms_order_item` VALUES (15, 6, '697561255300300800', 23, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/pms_1581494131.03158032.jpg', '小米10', 'dw214', 4299.00, 1, 146, '202003020023001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"冰海蓝\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `oms_order_item` VALUES (16, 6, '697561255300300800', 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb48f0aa21853fe648a29523231b736d_840_840.png', '一加7Pro', 'C001', 3299.00, 1, 140, '202003020001001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"星雾蓝\"},{\"key\":\"内存容量\",\"value\":\"128G\"}]');
INSERT INTO `oms_order_item` VALUES (17, 6, '697561255300300800', 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb48f0aa21853fe648a29523231b736d_840_840.png', '一加7Pro', 'C001', 3699.00, 1, 141, '202003020001002', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"星雾蓝\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `oms_order_item` VALUES (18, 6, '697561255300300800', 26, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-05/pms_1575881808.82863486.jpg', 'Redmi K30', 's854', 1699.00, 1, 149, '202003050026001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"深海微光\"},{\"key\":\"内存容量\",\"value\":\"128G\"}]');
INSERT INTO `oms_order_item` VALUES (19, 6, '697561255300300800', 22, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb4580c78e806b6aea3451c94c11fa0.png', '一加7T', 'dw5424', 3199.00, 1, 145, '202003020022002', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"冰川银\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `oms_order_item` VALUES (20, 7, '697566555365441536', 23, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/pms_1581494131.03158032.jpg', '小米10', 'dw214', 4299.00, 1, 146, '202003020023001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"冰海蓝\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `oms_order_item` VALUES (21, 7, '697566555365441536', 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb48f0aa21853fe648a29523231b736d_840_840.png', '一加7Pro', 'C001', 3299.00, 1, 140, '202003020001001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"星雾蓝\"},{\"key\":\"内存容量\",\"value\":\"128G\"}]');
INSERT INTO `oms_order_item` VALUES (22, 7, '697566555365441536', 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb48f0aa21853fe648a29523231b736d_840_840.png', '一加7Pro', 'C001', 3699.00, 1, 141, '202003020001002', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"星雾蓝\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `oms_order_item` VALUES (23, 7, '697566555365441536', 26, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-05/pms_1575881808.82863486.jpg', 'Redmi K30', 's854', 1699.00, 1, 149, '202003050026001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"深海微光\"},{\"key\":\"内存容量\",\"value\":\"128G\"}]');
INSERT INTO `oms_order_item` VALUES (24, 7, '697566555365441536', 22, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb4580c78e806b6aea3451c94c11fa0.png', '一加7T', 'dw5424', 3199.00, 1, 145, '202003020022002', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"冰川银\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `oms_order_item` VALUES (25, 8, '697876731159117824', 23, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/pms_1581494131.03158032.jpg', '小米10', 'dw214', 4299.00, 1, 146, '202003020023001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"冰海蓝\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `oms_order_item` VALUES (26, 8, '697876731159117824', 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb48f0aa21853fe648a29523231b736d_840_840.png', '一加7Pro', 'C001', 3299.00, 1, 140, '202003020001001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"星雾蓝\"},{\"key\":\"内存容量\",\"value\":\"128G\"}]');
INSERT INTO `oms_order_item` VALUES (27, 8, '697876731159117824', 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb48f0aa21853fe648a29523231b736d_840_840.png', '一加7Pro', 'C001', 3699.00, 1, 141, '202003020001002', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"星雾蓝\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `oms_order_item` VALUES (28, 8, '697876731159117824', 26, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-05/pms_1575881808.82863486.jpg', 'Redmi K30', 's854', 1699.00, 1, 149, '202003050026001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"深海微光\"},{\"key\":\"内存容量\",\"value\":\"128G\"}]');
INSERT INTO `oms_order_item` VALUES (29, 8, '697876731159117824', 22, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb4580c78e806b6aea3451c94c11fa0.png', '一加7T', 'dw5424', 3199.00, 1, 145, '202003020022002', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"冰川银\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `oms_order_item` VALUES (30, 9, '20200412698968922333904896', 22, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb4580c78e806b6aea3451c94c11fa0.png', '一加7T', 'dw5424', 3199.00, 1, 145, '202003020022002', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"冰川银\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `oms_order_item` VALUES (31, 10, '20200412699006897511989248', 22, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb4580c78e806b6aea3451c94c11fa0.png', '一加7T', 'dw5424', 3199.00, 1, 145, '202003020022002', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"冰川银\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `oms_order_item` VALUES (32, 11, '20200413699359558090358784', 23, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/pms_1581494131.03158032.jpg', '小米10', 'dw214', 4299.00, 1, 146, '202003020023001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"冰海蓝\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `oms_order_item` VALUES (33, 11, '20200413699359558090358784', 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb48f0aa21853fe648a29523231b736d_840_840.png', '一加7Pro', 'C001', 3299.00, 1, 140, '202003020001001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"星雾蓝\"},{\"key\":\"内存容量\",\"value\":\"128G\"}]');
INSERT INTO `oms_order_item` VALUES (34, 11, '20200413699359558090358784', 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb48f0aa21853fe648a29523231b736d_840_840.png', '一加7Pro', 'C001', 3699.00, 1, 141, '202003020001002', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"星雾蓝\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `oms_order_item` VALUES (35, 11, '20200413699359558090358784', 26, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-05/pms_1575881808.82863486.jpg', 'Redmi K30', 's854', 1699.00, 1, 149, '202003050026001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"深海微光\"},{\"key\":\"内存容量\",\"value\":\"128G\"}]');
INSERT INTO `oms_order_item` VALUES (36, 11, '20200413699359558090358784', 22, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb4580c78e806b6aea3451c94c11fa0.png', '一加7T', 'dw5424', 3199.00, 1, 145, '202003020022002', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"颜色\",\"value\":\"冰川银\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');

-- ----------------------------
-- Table structure for pms_product
-- ----------------------------
DROP TABLE IF EXISTS `pms_product`;
CREATE TABLE `pms_product`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `product_attribute_category_id` bigint(20) UNSIGNED NULL DEFAULT 0 COMMENT '属性id',
  `product_category_id` bigint(20) UNSIGNED NULL DEFAULT 0 COMMENT '分类id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '标题',
  `sub_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '‘’' COMMENT '副标题',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '商品轮播图片',
  `product_sn` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '货号',
  `delete_status` int(1) UNSIGNED NULL DEFAULT 0 COMMENT '删除状态：0->未删除；1->已删除',
  `publish_status` int(1) UNSIGNED NULL DEFAULT 0 COMMENT '上架状态：0->下架；1->上架',
  `new_status` int(1) UNSIGNED NULL DEFAULT 0 COMMENT '新品状态：0->不是新品；1->新品',
  `recommand_status` int(1) UNSIGNED NULL DEFAULT 0 COMMENT '推荐状态：0->不推荐；1->推荐',
  `verify_status` int(1) UNSIGNED NULL DEFAULT 0 COMMENT '审核状态：0->未审核；1->审核通过',
  `sort` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '排序，越小越靠前',
  `sale` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '销量',
  `price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '价格',
  `promotion_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '促销价',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '商品描述',
  `orginal_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '市场价',
  `stock` int(11) NULL DEFAULT 0 COMMENT '库存',
  `unit` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '单位',
  `weight` decimal(10, 2) NULL DEFAULT 0.00,
  `services_ids` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮',
  `keywords` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `album_pics` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '画册图片，连产品图片限制为5张，以逗号分割',
  `detail_html` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '产品详情',
  `product_category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '商品分类名称',
  `param_html` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '商品参数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_product
-- ----------------------------
INSERT INTO `pms_product` VALUES (1, 1, 2, '一加7Pro', '90Hz开启流畅新纪元', 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb48f0aa21853fe648a29523231b736d_840_840.png', 'C001', 0, 1, 1, 1, 1, 0, 0, 3999.00, 0.00, '前置指纹后置屏幕的一加', 0.00, 500, '部', 300.00, '1,2,3', '一加 ONEPLUS YIJIA 手机 shouji', 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/d7936369ff0b70395bad719ecccc4fe8_840_840.png', '', '手机', NULL);
INSERT INTO `pms_product` VALUES (2, 1, 2, '小米10', '2k 120Hz 骁龙865', 'https://cdn.cnbj0.fds.api.mi-img.com/b2c-shopapi-pms/pms_1581493329.10251213.jpg', '', 1, 1, 1, 1, 1, 0, 0, 3999.00, 0.00, '粗粮旗舰机', 0.00, 50, '部', 300.00, '', '小米10 XIAOMI 手机', '', '', '手机', NULL);
INSERT INTO `pms_product` VALUES (8, 1, 2, '粗粮10', '粗粮10 粗粮旗舰机', 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-17/把风景装进苹果.jpg', 'd251', 1, 1, 1, 1, 1, 0, 0, 3999.00, 0.00, '粗粮旗舰机', 0.00, 50, '部', 300.00, '', '粗粮 CULIANG 手机', '', '', '手机', NULL);
INSERT INTO `pms_product` VALUES (12, 1, 2, '一加5T', '前置指纹后置屏幕', 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-21/one-logo.png', 's5142', 1, 1, 1, 1, 1, 0, 0, 2999.00, 0.00, '前置指纹后置屏幕', 0.00, 50, '件', 500.00, '', '一加 ONEPLUS YIJIA 手机', '', '<p>xxxxx</p>', '手机', NULL);
INSERT INTO `pms_product` VALUES (16, 2, 10, '微星GE62-2QC', '散热缩水星', 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-22/OCDpIFhVVOagGMFi8v07Nw.jpg', 'GE62-2QC', 0, 1, 1, 1, 1, 0, 0, 6599.00, 0.00, '散热缩水星', 0.00, 50, '件', 3000.00, '', '微星 MSI GE62-2QC 电脑 笔记本', '', '', '笔记本', NULL);
INSERT INTO `pms_product` VALUES (20, 1, 2, '华为P30', '华为P30', 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/心.png', '', 1, 1, 1, 1, 1, 0, 0, 2695.00, 0.00, '', 0.00, 0, '', 0.00, '', '手机 华为 HUAWEI ', '', '', '手机', NULL);
INSERT INTO `pms_product` VALUES (22, 1, 2, '一加7T', '全系 90Hz，流畅新标杆。', 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/eb4580c78e806b6aea3451c94c11fa0.png', 'dw5424', 0, 1, 1, 1, 1, 0, 0, 4599.00, 0.00, '全系 90Hz，流畅新标杆', 0.00, 0, '', 0.00, '', '一加 ONEPLUS oneplus yijia 手机', '', '', '手机', NULL);
INSERT INTO `pms_product` VALUES (23, 1, 2, '小米10', '骁龙865/1亿像素相机', 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-26/pms_1581494131.03158032.jpg', 'dw214', 0, 1, 1, 1, 1, 0, 0, 3999.00, 0.00, '骁龙865处理器 / 1亿像素8K电影相机 / 双模5G / 新一代LPDDR5内存 / 对称式立体声 / 90Hz刷新率+180Hz采样率 / UFS 3.0高速存储 / 全面适配Wi-Fi 6 / 超强VC液冷散热 / 30W极速闪充+30W无线闪充+10W无线反充 / 4780mAh大电量 / 多功能NFC', 0.00, 0, '', 0.00, '', '小米10 小米 XIAOMI 手机 shouji', '', '<p><img class=\"wscnph\" src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-27/626aebdf48d130dab12d91c9892fe96e.jpg\" /></p>\n<p><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/mi10pro-02.jpg\" /></p>\n<p><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/db461be6e96ca37f36addee3d838cd78.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/mi10pro-05.jpg\" /></p>\n<p><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/07a79d52868544895383df5260fdea9c.jpg\" /></p>\n<p><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/e3d5d8e5e6ff59e7742e68b6dd64d96d.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/4a9b2ef5351f6d3e0e2dbd02d959313d.jpg\" /></p>\n<p><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/1a5f9f2feb7ec6c053762ea709dbfab9.jpg\" /></p>\n<p><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/3bf74a4e6e17e54ce632b6e28f7815b1.jpg\" /></p>\n<p><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/682a5caed871d50a15da40d0e687286c.jpg\" /></p>\n<p><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/742ea70d16de9683d28728b860fc0409.jpg\" /></p>\n<p><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/8807af27d0a5f7ce64ba0a0e5fbfd89e.jpg\" /></p>\n<p><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/b2bd952990dbd431c739dbf4c53c9db0.jpg\" /></p>\n<p><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/040e8283a4c376f264d8973e18976f21.jpg\" /></p>\n<p><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/065b77cc05a9a745f97205cd98f63c28.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/209ce1bedeca92236837901947f26856.jpg\" /></p>\n<p><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/06a3ec5b61896149702b0ed0f39d2ab1.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/b34abbd8404a5b70a60487339237ea7f.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/811c92d0bfba3b475980ec9e45c0d30b.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/dbcbe57a547408d1b41c9499b09f7c82.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/8163bdcb7e93c4a22178ccf9ff627efd.jpg\" /></p>', '手机', '<p><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/1a0e2040c579d0fa3ccfe001ea369308.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/1aa1a14eaa957f1f076c7af541e0d2c3.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/8844f12f6a5ce99371734845b5a9ccd2.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/6d0a73bfcaaa94dc193b2a77950504f0.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/b1b32c92744336712b09597cb6f03144.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/9473de23309cc4b7db5038061321270f.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/3f9b2db64463ecc7ff56f2fb98763040.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/6cb0459b82f85d712d0ca5d0f339a1a9.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/45db83540f0d12bc22e994d36dc040e2.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/5d527609218432e63e34cfd975fe5d2c.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-29/3f06741ede6a317408ce28ba4cb5112b.jpg\" /></p>');
INSERT INTO `pms_product` VALUES (26, 1, 2, 'Redmi K30', '120Hz流速屏，全速热爱', 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-05/pms_1575881808.82863486.jpg', 's854', 0, 1, 1, 1, 1, 0, 0, 1699.00, 0.00, '120Hz高帧率流速屏 / 索尼6400万前后六摄 / 6.67\'\'小孔径全面屏 / 最高可选8GB+256GB大存储 / 高通骁龙730G处理器 / 3D四曲面玻璃机身 / 4500mAh+27W快充 / 多功能NFC', 0.00, 50, '件', 0.00, '', '小米 XIAOMI SHOUJI k30 shouji 手机', '', '<p><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-09/www.mi.com_redmik30.png\" /></p>', '手机', NULL);
INSERT INTO `pms_product` VALUES (27, 1, 2, '腾讯黑鲨游戏手机3', '首款5G游戏手机', 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/pms_1582886431.60597012.jpg', 'heisha3', 0, 1, 1, 1, 1, 0, 0, 3499.00, 0.00, '骁龙865处理器 / 双模5G / 腾讯Solar Core游戏引擎 / 270Hz触控采样率+90Hz屏幕刷新率 / 最高65W极速闪充+背部磁吸充电 / 4720mAh大容量双电池 / UFS3.0闪存 / “三明治”液冷散热 / 屏幕压感3.0 / 游戏语音操控', 0.00, 50, '', 0.00, '', '腾讯手机 黑鲨 黑鲨3 游戏手机 手机 HEISHA TENGXUN', '', '<p><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/f6c83efa3469e278c723d00accae4dc6.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/3b89426fac85a815822b4072ec375939.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/949015805e5a3329f1d987006165e132.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/9628fba3b194d41a467cddb95bdf109d.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/383b283f59c75d8d21999f8e3127ddbe.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/afd450a2d885763c2507b724185b28bb.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/4b229721ab9812dc1ad6e7fc57c2dedf.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/4663b2431d795d1386939d20e3d54bb1.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/3219ec75cb05cea01373d2caa46a07c1.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/6838cb18b936c6cfc8ef42cc2a531d8f.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/41356307add5e0912dcaa4c822e4d1fc.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/39d768f6039763aa5702507e81bed380.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/72f1fa3d438ad932b7dee94e7c8a6b5a.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/037b4112a53d524836e6073e8f3cdb7d.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/2e4e7ef82c852440174e3ce91803fa3c.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/619d69538b074c146173e9942c5f9e9a.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/d49f3c2d25f1c9f27d34da82eb330791.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/a10cfec0173677179aa51b80b9c8db12.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/09d998cda70007c6bd99b7808ff8ecfe.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/fd2688441d7780aca3147ea6f751af27.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/3dfc41d432d70ecdaf9f2547186d4243.jpg\" /></p>', '手机', '<p><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/77b523d889b873ee1c2e2f5b7fa12de7.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/46b0ee64d63672e96b59c6e4f797f3fd.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/46b0ee64d63672e96b59c6e4f797f3fd\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/50ca73ae9aeeb16ad77c0902745f0ef6.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/a9ec8a26ae1ca4be2dc6d16f9dbeddc5.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/5dafbe0bc30a9037b9388bbe65b66d25.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/1a5576b9e5f3b5e1dc03042f4d98a75c.jpg\" /><img src=\"http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/e0dd6cce4c889c89ea49edca45693b4a.jpg\" /></p>');

-- ----------------------------
-- Table structure for pms_product_attribute
-- ----------------------------
DROP TABLE IF EXISTS `pms_product_attribute`;
CREATE TABLE `pms_product_attribute`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_attribute_category_id` bigint(20) NULL DEFAULT 0,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `select_type` int(1) NULL DEFAULT 0 COMMENT '属性选择类型：0->唯一；1->单选；2->多选',
  `input_type` int(1) NULL DEFAULT 0 COMMENT '属性录入方式：0->手工录入；1->从列表中选取',
  `input_list` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '可选值列表，以逗号隔开',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序字段：最高的可以单独上传图片',
  `filter_type` int(1) NULL DEFAULT 0 COMMENT '分类筛选样式：1->普通；1->颜色',
  `search_type` int(1) NULL DEFAULT 0 COMMENT '检索类型；0->不需要进行检索；1->关键字检索；2->范围检索',
  `related_status` int(1) NULL DEFAULT 0 COMMENT '相同属性产品是否关联；0->不关联；1->关联',
  `hand_add_status` int(1) NULL DEFAULT NULL COMMENT '是否支持手动新增；0->不支持；1->支持',
  `type` int(1) NULL DEFAULT 0 COMMENT '属性的类型；0->规格；1->参数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品属性参数表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_product_attribute
-- ----------------------------
INSERT INTO `pms_product_attribute` VALUES (2, 1, '网络', 1, 1, '4G,5G', 0, 0, 0, 0, 0, 1);
INSERT INTO `pms_product_attribute` VALUES (3, 1, '颜色', 0, 0, '', 0, 1, 0, 0, 1, 0);
INSERT INTO `pms_product_attribute` VALUES (4, 1, '内存容量', 0, 1, '32G,64G,128G,256G', 0, 0, 0, 0, 0, 0);
INSERT INTO `pms_product_attribute` VALUES (5, 1, '处理器', 0, 0, '', 0, 0, 0, 0, 1, 1);
INSERT INTO `pms_product_attribute` VALUES (6, 1, '屏幕', 0, 0, '', 0, 0, 0, 0, 1, 1);
INSERT INTO `pms_product_attribute` VALUES (7, 1, '后置相机', 0, 0, '', 0, 0, 0, 0, 1, 1);
INSERT INTO `pms_product_attribute` VALUES (8, 1, '前置相机', 0, 0, '', 0, 0, 0, 0, 1, 1);
INSERT INTO `pms_product_attribute` VALUES (9, 1, '电池与续航', 0, 0, '', 0, 0, 0, 0, 1, 1);
INSERT INTO `pms_product_attribute` VALUES (10, 1, '外观尺寸', 0, 0, '', 0, 0, 0, 0, 1, 1);

-- ----------------------------
-- Table structure for pms_product_attribute_category
-- ----------------------------
DROP TABLE IF EXISTS `pms_product_attribute_category`;
CREATE TABLE `pms_product_attribute_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `attribute_count` int(11) NULL DEFAULT 0 COMMENT '属性数量',
  `param_count` int(11) NULL DEFAULT 0 COMMENT '参数数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '产品属性分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_product_attribute_category
-- ----------------------------
INSERT INTO `pms_product_attribute_category` VALUES (1, '手机', 2, 7);
INSERT INTO `pms_product_attribute_category` VALUES (2, '电脑', 0, 0);

-- ----------------------------
-- Table structure for pms_product_attribute_value
-- ----------------------------
DROP TABLE IF EXISTS `pms_product_attribute_value`;
CREATE TABLE `pms_product_attribute_value`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL DEFAULT 0,
  `product_attribute_id` bigint(20) NOT NULL DEFAULT 0,
  `value` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手动添加规格或参数的值，参数单值，规格有多个时以逗号隔开',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 313 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '存储产品参数信息的表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_product_attribute_value
-- ----------------------------
INSERT INTO `pms_product_attribute_value` VALUES (10, 12, 2, '4G');
INSERT INTO `pms_product_attribute_value` VALUES (18, 21, 2, '5G');
INSERT INTO `pms_product_attribute_value` VALUES (20, 2, 2, '5G');
INSERT INTO `pms_product_attribute_value` VALUES (21, 8, 2, '5G');
INSERT INTO `pms_product_attribute_value` VALUES (170, 25, 2, '5G');
INSERT INTO `pms_product_attribute_value` VALUES (171, 25, 5, '00');
INSERT INTO `pms_product_attribute_value` VALUES (172, 25, 6, '00');
INSERT INTO `pms_product_attribute_value` VALUES (173, 25, 7, '00');
INSERT INTO `pms_product_attribute_value` VALUES (174, 25, 8, '00');
INSERT INTO `pms_product_attribute_value` VALUES (175, 25, 9, '00');
INSERT INTO `pms_product_attribute_value` VALUES (176, 25, 10, '00');
INSERT INTO `pms_product_attribute_value` VALUES (177, 24, 3, '白色');
INSERT INTO `pms_product_attribute_value` VALUES (178, 24, 2, '5G');
INSERT INTO `pms_product_attribute_value` VALUES (179, 24, 5, '1');
INSERT INTO `pms_product_attribute_value` VALUES (180, 24, 6, '1');
INSERT INTO `pms_product_attribute_value` VALUES (181, 24, 7, '1');
INSERT INTO `pms_product_attribute_value` VALUES (182, 24, 8, '1');
INSERT INTO `pms_product_attribute_value` VALUES (183, 24, 9, '1');
INSERT INTO `pms_product_attribute_value` VALUES (184, 24, 10, '1');
INSERT INTO `pms_product_attribute_value` VALUES (241, 1, 3, '星雾蓝,曜岩灰');
INSERT INTO `pms_product_attribute_value` VALUES (242, 1, 2, '5G');
INSERT INTO `pms_product_attribute_value` VALUES (243, 1, 5, '骁龙855 ');
INSERT INTO `pms_product_attribute_value` VALUES (244, 1, 6, '2k+90Hz');
INSERT INTO `pms_product_attribute_value` VALUES (245, 1, 7, '4800万三摄');
INSERT INTO `pms_product_attribute_value` VALUES (246, 1, 8, '2000万');
INSERT INTO `pms_product_attribute_value` VALUES (247, 1, 9, '4000mAh');
INSERT INTO `pms_product_attribute_value` VALUES (248, 1, 10, '162.6×75.9×8.8mm');
INSERT INTO `pms_product_attribute_value` VALUES (257, 26, 3, '深海微光,   紫玉幻境 ,花影惊鸿');
INSERT INTO `pms_product_attribute_value` VALUES (258, 26, 2, '5G');
INSERT INTO `pms_product_attribute_value` VALUES (259, 26, 5, '730G');
INSERT INTO `pms_product_attribute_value` VALUES (260, 26, 6, ' 6.67');
INSERT INTO `pms_product_attribute_value` VALUES (261, 26, 7, '6400万');
INSERT INTO `pms_product_attribute_value` VALUES (262, 26, 8, '2000万AI双摄');
INSERT INTO `pms_product_attribute_value` VALUES (263, 26, 9, '4500mAh');
INSERT INTO `pms_product_attribute_value` VALUES (264, 26, 10, '165.3mm x76.6mm x8.79mm');
INSERT INTO `pms_product_attribute_value` VALUES (289, 27, 3, '闪电黑,铠甲灰');
INSERT INTO `pms_product_attribute_value` VALUES (290, 27, 2, '5G');
INSERT INTO `pms_product_attribute_value` VALUES (291, 27, 5, '骁龙865');
INSERT INTO `pms_product_attribute_value` VALUES (292, 27, 6, '6.667');
INSERT INTO `pms_product_attribute_value` VALUES (293, 27, 7, '三摄');
INSERT INTO `pms_product_attribute_value` VALUES (294, 27, 8, '双摄');
INSERT INTO `pms_product_attribute_value` VALUES (295, 27, 9, '4800mAh');
INSERT INTO `pms_product_attribute_value` VALUES (296, 27, 10, '167*168*166');
INSERT INTO `pms_product_attribute_value` VALUES (297, 22, 3, '冰际蓝,冰川银');
INSERT INTO `pms_product_attribute_value` VALUES (298, 22, 2, '4G');
INSERT INTO `pms_product_attribute_value` VALUES (299, 22, 5, '骚龙855');
INSERT INTO `pms_product_attribute_value` VALUES (300, 22, 6, '90Hz 流体屏');
INSERT INTO `pms_product_attribute_value` VALUES (301, 22, 7, '4800万三摄');
INSERT INTO `pms_product_attribute_value` VALUES (302, 22, 8, '2000万超清前摄');
INSERT INTO `pms_product_attribute_value` VALUES (303, 22, 9, '4800mAh');
INSERT INTO `pms_product_attribute_value` VALUES (304, 22, 10, '6.667英寸');
INSERT INTO `pms_product_attribute_value` VALUES (305, 23, 3, '冰海蓝,钛银黑 ,蜜桃金');
INSERT INTO `pms_product_attribute_value` VALUES (306, 23, 2, '5G');
INSERT INTO `pms_product_attribute_value` VALUES (307, 23, 5, '骁龙865');
INSERT INTO `pms_product_attribute_value` VALUES (308, 23, 6, '6.667英寸AMOLED双面曲面屏,90Hz刷新率');
INSERT INTO `pms_product_attribute_value` VALUES (309, 23, 7, '1亿像素 四摄像');
INSERT INTO `pms_product_attribute_value` VALUES (310, 23, 8, '2000万超清前置');
INSERT INTO `pms_product_attribute_value` VALUES (311, 23, 9, '4780mAh,Type-Cs双面快充');
INSERT INTO `pms_product_attribute_value` VALUES (312, 23, 10, '高162mm,宽74mm,厚8.96mm');

-- ----------------------------
-- Table structure for pms_product_category
-- ----------------------------
DROP TABLE IF EXISTS `pms_product_category`;
CREATE TABLE `pms_product_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '上级别分类的编号：0表示一级分类',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `level` int(1) NULL DEFAULT 0 COMMENT '分类级别：0->1级；1->2级',
  `product_count` int(11) NULL DEFAULT 0,
  `nav_status` int(1) NULL DEFAULT 1 COMMENT '是否显示在导航栏：0->不显示；1->显示',
  `show_status` int(1) NULL DEFAULT 1 COMMENT '显示状态：0->不显示；1->显示',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '图标',
  `keywords` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `recommand_status` int(1) NULL DEFAULT 0 COMMENT '推荐状态：0->不推荐；1->推荐',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '分类信息\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_product_category
-- ----------------------------
INSERT INTO `pms_product_category` VALUES (1, 0, '手机 数码', 0, 0, 0, 1, 1, '', '', '', 0);
INSERT INTO `pms_product_category` VALUES (2, 1, '手机', 1, 0, 1, 1, 0, '', '手机', '', 1);
INSERT INTO `pms_product_category` VALUES (3, 1, '数码', 1, 0, 1, 1, 0, '', '', '', 1);
INSERT INTO `pms_product_category` VALUES (4, 0, '电脑 显示器 平板', 0, 0, 0, 1, 2, '', '', '', 0);
INSERT INTO `pms_product_category` VALUES (5, 0, '电源 配件', 0, 0, 0, 1, 3, '', '', '', 0);
INSERT INTO `pms_product_category` VALUES (6, 0, '健康 儿童', 0, 0, 0, 0, 4, '', '', '', 0);
INSERT INTO `pms_product_category` VALUES (7, 0, '家具 家饰 家纺', 0, 0, 0, 1, 5, '', '', '', 0);
INSERT INTO `pms_product_category` VALUES (8, 7, '沙发', 0, 0, 0, 1, 0, '', '', '', 0);
INSERT INTO `pms_product_category` VALUES (9, 7, '床上用品', 0, 0, 0, 0, 0, '', '', '', 0);
INSERT INTO `pms_product_category` VALUES (10, 4, '笔记本', 1, 0, 1, 1, 0, '', '', '', 1);
INSERT INTO `pms_product_category` VALUES (11, 4, '平板', 1, 0, 1, 1, 0, '', '', '', 0);
INSERT INTO `pms_product_category` VALUES (12, 0, '男装 女装 童装', 0, 0, 0, 1, 6, '', '', '', 0);
INSERT INTO `pms_product_category` VALUES (13, 12, '男装', 1, 0, 1, 1, 0, '', '', '', 0);
INSERT INTO `pms_product_category` VALUES (14, 0, '男鞋 运动 户外', 0, 0, 0, 1, 7, '', '', '', 0);
INSERT INTO `pms_product_category` VALUES (15, 14, '篮球鞋', 1, 0, 1, 1, 0, '', '', '', 0);
INSERT INTO `pms_product_category` VALUES (16, 0, '食品 烟酒', 0, 0, 0, 1, 8, '', '', '', 0);
INSERT INTO `pms_product_category` VALUES (17, 16, '休闲食品', 1, 0, 0, 1, 0, '', '', '', 0);
INSERT INTO `pms_product_category` VALUES (18, 0, '母婴 玩具乐器', 0, 0, 0, 1, 9, '', '', '', 0);
INSERT INTO `pms_product_category` VALUES (19, 18, '民谣吉他', 1, 0, 0, 1, 0, '', '', '', 0);
INSERT INTO `pms_product_category` VALUES (20, 0, '女鞋 箱包 珠宝', 0, 0, 0, 1, 10, '', '', '', 0);
INSERT INTO `pms_product_category` VALUES (21, 20, '女鞋', 1, 0, 1, 1, 0, '', '', '', 0);
INSERT INTO `pms_product_category` VALUES (22, 0, '图书 电子书 ', 0, 0, 0, 1, 11, '', '', '', 0);
INSERT INTO `pms_product_category` VALUES (23, 22, '图书', 1, 0, 1, 0, 0, '', '', '', 0);
INSERT INTO `pms_product_category` VALUES (24, 0, '家电 厨房用品', 0, 0, 0, 1, 13, '', '', '', 0);
INSERT INTO `pms_product_category` VALUES (25, 24, '家电', 1, 0, 1, 1, 0, '', '', '', 0);

-- ----------------------------
-- Table structure for pms_product_category_attribute_relation
-- ----------------------------
DROP TABLE IF EXISTS `pms_product_category_attribute_relation`;
CREATE TABLE `pms_product_category_attribute_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_category_id` bigint(20) NULL DEFAULT 0,
  `product_attribute_id` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pms_product_full_reduction
-- ----------------------------
DROP TABLE IF EXISTS `pms_product_full_reduction`;
CREATE TABLE `pms_product_full_reduction`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL DEFAULT 0,
  `full_price` decimal(10, 2) NULL DEFAULT 0.00,
  `reduce_price` decimal(10, 2) NULL DEFAULT 0.00,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 105 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品满减表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_product_full_reduction
-- ----------------------------
INSERT INTO `pms_product_full_reduction` VALUES (3, 3, 0.00, 0.00);
INSERT INTO `pms_product_full_reduction` VALUES (16, 13, 0.00, 0.00);
INSERT INTO `pms_product_full_reduction` VALUES (17, 14, 0.00, 0.00);
INSERT INTO `pms_product_full_reduction` VALUES (18, 15, 0.00, 0.00);
INSERT INTO `pms_product_full_reduction` VALUES (20, 17, 0.00, 0.00);
INSERT INTO `pms_product_full_reduction` VALUES (21, 18, 0.00, 0.00);
INSERT INTO `pms_product_full_reduction` VALUES (22, 19, 0.00, 0.00);
INSERT INTO `pms_product_full_reduction` VALUES (28, 12, 0.00, 0.00);
INSERT INTO `pms_product_full_reduction` VALUES (29, 16, 0.00, 0.00);
INSERT INTO `pms_product_full_reduction` VALUES (31, 20, 0.00, 0.00);
INSERT INTO `pms_product_full_reduction` VALUES (42, 21, 0.00, 0.00);
INSERT INTO `pms_product_full_reduction` VALUES (44, 8, 0.00, 0.00);
INSERT INTO `pms_product_full_reduction` VALUES (87, 25, 0.00, 0.00);
INSERT INTO `pms_product_full_reduction` VALUES (88, 24, 0.00, 0.00);
INSERT INTO `pms_product_full_reduction` VALUES (96, 1, 0.00, 0.00);
INSERT INTO `pms_product_full_reduction` VALUES (98, 26, 0.00, 0.00);
INSERT INTO `pms_product_full_reduction` VALUES (102, 27, 0.00, 0.00);
INSERT INTO `pms_product_full_reduction` VALUES (103, 22, 0.00, 0.00);
INSERT INTO `pms_product_full_reduction` VALUES (104, 23, 0.00, 0.00);

-- ----------------------------
-- Table structure for pms_product_ladder
-- ----------------------------
DROP TABLE IF EXISTS `pms_product_ladder`;
CREATE TABLE `pms_product_ladder`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL DEFAULT 0,
  `count` int(11) NULL DEFAULT 0 COMMENT '满足的商品数量',
  `discount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '折扣',
  `price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '折后价格',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 105 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '产品阶梯价格表(只针对同商品)' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_product_ladder
-- ----------------------------
INSERT INTO `pms_product_ladder` VALUES (3, 3, 0, 0.00, 0.00);
INSERT INTO `pms_product_ladder` VALUES (16, 13, 0, 0.00, 0.00);
INSERT INTO `pms_product_ladder` VALUES (17, 14, 0, 0.00, 0.00);
INSERT INTO `pms_product_ladder` VALUES (18, 15, 0, 0.00, 0.00);
INSERT INTO `pms_product_ladder` VALUES (20, 17, 0, 0.00, 0.00);
INSERT INTO `pms_product_ladder` VALUES (21, 18, 0, 0.00, 0.00);
INSERT INTO `pms_product_ladder` VALUES (22, 19, 0, 0.00, 0.00);
INSERT INTO `pms_product_ladder` VALUES (28, 12, 0, 0.00, 0.00);
INSERT INTO `pms_product_ladder` VALUES (29, 16, 0, 0.00, 0.00);
INSERT INTO `pms_product_ladder` VALUES (31, 20, 0, 0.00, 0.00);
INSERT INTO `pms_product_ladder` VALUES (42, 21, 0, 0.00, 0.00);
INSERT INTO `pms_product_ladder` VALUES (44, 8, 0, 0.00, 0.00);
INSERT INTO `pms_product_ladder` VALUES (87, 25, 0, 0.00, 0.00);
INSERT INTO `pms_product_ladder` VALUES (88, 24, 0, 0.00, 0.00);
INSERT INTO `pms_product_ladder` VALUES (96, 1, 0, 0.00, 0.00);
INSERT INTO `pms_product_ladder` VALUES (98, 26, 0, 0.00, 0.00);
INSERT INTO `pms_product_ladder` VALUES (102, 27, 0, 0.00, 0.00);
INSERT INTO `pms_product_ladder` VALUES (103, 22, 0, 0.00, 0.00);
INSERT INTO `pms_product_ladder` VALUES (104, 23, 0, 0.00, 0.00);

-- ----------------------------
-- Table structure for pms_product_verify_record
-- ----------------------------
DROP TABLE IF EXISTS `pms_product_verify_record`;
CREATE TABLE `pms_product_verify_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `verify_man` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '’‘' COMMENT '审核人',
  `status` int(1) NULL DEFAULT 0,
  `detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '反馈详情',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品审核日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_product_verify_record
-- ----------------------------
INSERT INTO `pms_product_verify_record` VALUES (1, 1, '2020-02-11 22:12:06', 'TEST', 1, '审核通过');

-- ----------------------------
-- Table structure for pms_sku_stock
-- ----------------------------
DROP TABLE IF EXISTS `pms_sku_stock`;
CREATE TABLE `pms_sku_stock`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL DEFAULT 0,
  `sku_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'sku编码',
  `price` decimal(10, 2) NULL DEFAULT 0.00,
  `stock` int(11) NULL DEFAULT 0 COMMENT '库存',
  `low_stock` int(11) NULL DEFAULT 0 COMMENT '预警库存',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '展示图片',
  `sale` int(11) NULL DEFAULT 0 COMMENT '销量',
  `promotion_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '单品促销价格',
  `lock_stock` int(11) NULL DEFAULT 0 COMMENT '锁定库存',
  `sp_data` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '商品销售属性',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 156 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'sku的库存' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_sku_stock
-- ----------------------------
INSERT INTO `pms_sku_stock` VALUES (140, 1, '202003020001001', 3299.00, 37, 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-02/eb48f0aa21853fe648a29523231b736d_840_840.png', NULL, 0.00, 1, '[{\"key\":\"颜色\",\"value\":\"星雾蓝\"},{\"key\":\"内存容量\",\"value\":\"128G\"}]');
INSERT INTO `pms_sku_stock` VALUES (141, 1, '202003020001002', 3699.00, 37, 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-02/eb48f0aa21853fe648a29523231b736d_840_840.png', NULL, 0.00, 1, '[{\"key\":\"颜色\",\"value\":\"星雾蓝\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `pms_sku_stock` VALUES (142, 1, '202003020001003', 3299.00, 50, 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-02/d7936369ff0b70395bad719ecccc4fe8_840_840.png', NULL, 0.00, 0, '[{\"key\":\"颜色\",\"value\":\"曜岩灰\"},{\"key\":\"内存容量\",\"value\":\"128G\"}]');
INSERT INTO `pms_sku_stock` VALUES (143, 1, '202003020001004', 3699.00, 50, 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-02/d7936369ff0b70395bad719ecccc4fe8_840_840.png', NULL, 0.00, 0, '[{\"key\":\"颜色\",\"value\":\"曜岩灰\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `pms_sku_stock` VALUES (144, 22, '202003020022001', 3199.00, 50, 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-02/3538de028ea6092e8e75827a99fba14d_840_840.png', NULL, 0.00, 0, '[{\"key\":\"颜色\",\"value\":\"冰际蓝\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `pms_sku_stock` VALUES (145, 22, '202003020022002', 3199.00, 30, 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-02/3695a8724ade6c096d06c4a1b5d3c9bd_840_840.png', NULL, 0.00, 0, '[{\"key\":\"颜色\",\"value\":\"冰川银\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `pms_sku_stock` VALUES (146, 23, '202003020023001', 4299.00, 34, 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-02/pms_1581494346.38493761.jpg', NULL, 0.00, 1, '[{\"key\":\"颜色\",\"value\":\"冰海蓝\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `pms_sku_stock` VALUES (147, 23, '202003020023002', 4299.00, 50, 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-02/pms_1581494382.43763430.jpg', NULL, 0.00, 0, '[{\"key\":\"颜色\",\"value\":\"钛银黑 \"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `pms_sku_stock` VALUES (148, 23, '202003020023003', 4299.00, 50, 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-02/pms_1581494249.30919595.jpg', NULL, 0.00, 0, '[{\"key\":\"颜色\",\"value\":\"蜜桃金\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `pms_sku_stock` VALUES (149, 26, '202003050026001', 1699.00, 37, 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-05/pms_1575881808.82912751.jpg', NULL, 0.00, 1, '[{\"key\":\"颜色\",\"value\":\"深海微光\"},{\"key\":\"内存容量\",\"value\":\"128G\"}]');
INSERT INTO `pms_sku_stock` VALUES (150, 26, '202003050026002', 1799.00, 50, 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-05/pms_1575881819.47835800.jpg', NULL, 0.00, 0, '[{\"key\":\"颜色\",\"value\":\"   紫玉幻境 \"},{\"key\":\"内存容量\",\"value\":\"128G\"}]');
INSERT INTO `pms_sku_stock` VALUES (151, 26, '202003050026003', 1899.00, 50, 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-05/pms_1575881829.44083676.jpg', NULL, 0.00, 0, '[{\"key\":\"颜色\",\"value\":\"花影惊鸿\"},{\"key\":\"内存容量\",\"value\":\"128G\"}]');
INSERT INTO `pms_sku_stock` VALUES (152, 27, '202003110027001', 3499.00, 50, 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/pms_1582886356.18027808.jpg', NULL, 0.00, 0, '[{\"key\":\"颜色\",\"value\":\"闪电黑\"},{\"key\":\"内存容量\",\"value\":\"128G\"}]');
INSERT INTO `pms_sku_stock` VALUES (153, 27, '202003110027002', 3799.00, 50, 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/pms_1582886356.18027808.jpg', NULL, 0.00, 0, '[{\"key\":\"颜色\",\"value\":\"闪电黑\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');
INSERT INTO `pms_sku_stock` VALUES (154, 27, '202003110027003', 3499.00, 50, 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/pms_1582886376.53617124.jpg', NULL, 0.00, 0, '[{\"key\":\"颜色\",\"value\":\"铠甲灰\"},{\"key\":\"内存容量\",\"value\":\"128G\"}]');
INSERT INTO `pms_sku_stock` VALUES (155, 27, '202003110027004', 3799.00, 50, 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-03-11/pms_1582886376.53617124.jpg', NULL, 0.00, 0, '[{\"key\":\"颜色\",\"value\":\"铠甲灰\"},{\"key\":\"内存容量\",\"value\":\"256G\"}]');

-- ----------------------------
-- Table structure for sms_flash_promotion_product_relation
-- ----------------------------
DROP TABLE IF EXISTS `sms_flash_promotion_product_relation`;
CREATE TABLE `sms_flash_promotion_product_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `flash_promotion_session_id` bigint(20) NOT NULL DEFAULT 0,
  `product_id` bigint(20) NOT NULL DEFAULT 0,
  `flash_promotion_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '闪购价格',
  `flash_promotion_count` int(11) NULL DEFAULT 0 COMMENT '闪购数量',
  `flash_promotion_limit` int(11) NULL DEFAULT 0 COMMENT '限购数量',
  `sort` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品限时购与商品关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sms_flash_promotion_product_relation
-- ----------------------------
INSERT INTO `sms_flash_promotion_product_relation` VALUES (1, 4, 1, 1999.00, 50, 2, 0);
INSERT INTO `sms_flash_promotion_product_relation` VALUES (2, 1, 8, 3499.00, 5, 1, 0);
INSERT INTO `sms_flash_promotion_product_relation` VALUES (3, 1, 12, 2599.00, 5, 1, 0);
INSERT INTO `sms_flash_promotion_product_relation` VALUES (4, 1, 2, 3799.00, 10, 1, 0);
INSERT INTO `sms_flash_promotion_product_relation` VALUES (5, 1, 1, 3599.00, 20, 1, 0);
INSERT INTO `sms_flash_promotion_product_relation` VALUES (6, 1, 16, 6000.00, 5, 1, 0);

-- ----------------------------
-- Table structure for sms_flash_promotion_session
-- ----------------------------
DROP TABLE IF EXISTS `sms_flash_promotion_session`;
CREATE TABLE `sms_flash_promotion_session`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `start_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '开始时间',
  `end_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '结束时间',
  `status` int(1) NOT NULL DEFAULT 1 COMMENT '启用状态：0->不启用；1->启用',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '场次名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '闪购列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sms_flash_promotion_session
-- ----------------------------
INSERT INTO `sms_flash_promotion_session` VALUES (1, '2020-02-14 00:00:00', '2020-04-30 23:00:00', 1, '2020-02-14 17:09:02', '18:00 场');
INSERT INTO `sms_flash_promotion_session` VALUES (2, '2020-02-14 00:00:00', '2020-02-14 23:00:00', 0, '2020-02-14 17:09:42', '');
INSERT INTO `sms_flash_promotion_session` VALUES (3, '2020-02-14 00:00:00', '2020-02-14 23:00:00', 1, '2020-02-14 17:09:43', '');
INSERT INTO `sms_flash_promotion_session` VALUES (4, '2020-02-14 17:09:59', '2020-02-14 17:09:59', 1, '2020-02-14 17:09:59', '17：00 场次');
INSERT INTO `sms_flash_promotion_session` VALUES (10, '2020-02-14 17:09:59', '2020-02-14 17:09:59', 1, '2020-02-14 17:14:53', '16:00 场');

-- ----------------------------
-- Table structure for sms_home_advertise
-- ----------------------------
DROP TABLE IF EXISTS `sms_home_advertise`;
CREATE TABLE `sms_home_advertise`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '名称',
  `type` int(1) NULL DEFAULT 0 COMMENT '广告位置：0->首页轮播；1->轮播下的促销；2->中间横向广告；3->搜索框广告',
  `pic` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `start_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '结束时间',
  `status` int(1) NULL DEFAULT 1 COMMENT '上下线状态：0->下线；1->上线',
  `url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '’‘' COMMENT '链接地址',
  `note` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '首页轮播列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sms_home_advertise
-- ----------------------------
INSERT INTO `sms_home_advertise` VALUES (1, '第一条轮播', 0, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-21/a4aa0cbfad7de34618c4bebdbdeee4e1.jpg', '2020-02-17 21:53:23', '2020-04-29 20:32:56', 1, 'http://www.baidu.com', '第一条轮播', 0);
INSERT INTO `sms_home_advertise` VALUES (2, '首页第二个轮播', 0, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-21/1947569266905afe6abfff3456751bc4.jpg', '2020-02-21 18:27:00', '2020-04-29 20:32:56', 1, '#', '首页第二个轮播', 1);
INSERT INTO `sms_home_advertise` VALUES (3, '第三条轮播', 0, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-21/755aca9487082e7698e16f17cfb70839.jpg', '2020-02-21 18:31:25', '2020-04-29 20:32:56', 1, '#', '小米手表', 2);
INSERT INTO `sms_home_advertise` VALUES (5, '第四条轮播', 0, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-21/2f424f55554befb780ee50a761b2a768.jpg', '2020-02-21 18:32:40', '2020-04-29 20:32:56', 1, '#', '米家洗衣机', 0);
INSERT INTO `sms_home_advertise` VALUES (6, '第一个促销', 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-21/promotion1.jpg', '2020-02-21 18:33:44', '2020-04-29 20:32:56', 1, '#', '第一个促销', 0);
INSERT INTO `sms_home_advertise` VALUES (7, '第二个促销', 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-21/94c74e01afe50a86c3a87ff030b85781.jpg', '2020-02-21 18:34:30', '2020-04-29 20:32:56', 1, '#', '第二个促销', 1);
INSERT INTO `sms_home_advertise` VALUES (8, '小米手环促销', 1, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-21/816a66edef10673b4768128b41804cae.jpg', '2020-02-21 18:35:02', '2020-04-29 20:32:56', 1, '#', '手环促销', 2);
INSERT INTO `sms_home_advertise` VALUES (9, '小米10', 2, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-21/042df22986759ffc1a62d3edf0474633.jpg', '2020-02-21 18:35:33', '2020-04-29 20:32:56', 1, 'http://localhost:8888/#/product?id=23', '粗粮10广告', 0);
INSERT INTO `sms_home_advertise` VALUES (10, '米家联网空调', 2, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-21/89c2a209b742fce9b10d9d196149d634.jpg', '2020-02-21 18:36:11', '2020-04-29 20:32:56', 1, '#', '米家空调', 1);
INSERT INTO `sms_home_advertise` VALUES (11, '横向广告3', 2, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-21/10fb0086cb21120c53248a3d8cc56dc5.webp', '2020-02-21 18:36:58', '2020-04-29 20:32:56', 1, '#', '电话手表', 3);
INSERT INTO `sms_home_advertise` VALUES (12, '第五个轮播', 0, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-21/6bd4174b8c5aad67a64864a5716ad152.webp', '2020-02-21 19:22:11', '2020-04-29 20:32:56', 1, '#', '米家联网洗衣机', 0);
INSERT INTO `sms_home_advertise` VALUES (13, '小米电视特惠', 2, 'http://wang-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/2020-02-22/41d16e66381cfeda7b6b39ab67678d5e.webp', '2020-02-22 15:02:47', '2020-04-29 20:32:56', 1, '#', '横向广告', 0);
INSERT INTO `sms_home_advertise` VALUES (14, '大米 10 5G', 3, NULL, '2020-02-22 17:21:43', '2020-04-29 20:32:56', 1, '#', '大米 9搜索框广告', 0);
INSERT INTO `sms_home_advertise` VALUES (15, 'OnePlus 8Pro ', 3, NULL, '2020-02-22 17:22:10', '2020-04-29 20:32:56', 1, '#', 'OnePlus 8Pro 搜索框广告', 0);
INSERT INTO `sms_home_advertise` VALUES (16, '搜索广告', 3, NULL, '2020-02-23 14:23:56', '2020-04-29 20:32:56', 1, '#', '搜索框下拉菜单广告', 0);
INSERT INTO `sms_home_advertise` VALUES (17, '一加云耳2', 3, NULL, '2020-02-23 14:24:19', '2020-04-29 20:32:56', 1, '#', '一加云耳2搜索框广告', 0);

-- ----------------------------
-- Table structure for ums_admin
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin`;
CREATE TABLE `ums_admin`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '头像',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '邮箱',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `note` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注信息',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `login_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后登陆时间',
  `status` int(1) NOT NULL DEFAULT 1 COMMENT '帐号启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台用户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_admin
-- ----------------------------
INSERT INTO `ums_admin` VALUES (1, 'productAdmin', '$2a$10$slVnm9nvqGY.WCTXNiSiiuFHIRmXQIChVdZKEFE.IF3Cxdh2n.hHC', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', '', '商品管理员', '商品管理员', '2020-02-18 22:32:20', '2020-02-18 22:32:20', 1);
INSERT INTO `ums_admin` VALUES (3, 'orderAdmin', '$2a$10$pjtidL43ql/nGr4vxANEBO4F10ps4yC6YvFXLaAuss7n/ojvFWbXy', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/170157_yIl3_1767531.jpg', 'admin@163.com', '订单管理员', '订单管理员', '2020-02-18 22:32:56', '2020-02-18 22:32:56', 1);
INSERT INTO `ums_admin` VALUES (4, 'superAdmin', '$2a$10$pjtidL43ql/nGr4vxANEBO4F10ps4yC6YvFXLaAuss7n/ojvFWbXy', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/170157_yIl3_1767531.jpg', '1062572204@qq.com', '竹轩听雨', '超级管理员', '2020-02-18 22:26:49', '2020-02-18 22:26:49', 1);
INSERT INTO `ums_admin` VALUES (5, 'test', '$2a$10$pjtidL43ql/nGr4vxANEBO4F10ps4yC6YvFXLaAuss7n/ojvFWbXy', '', '', '测试账号', '测试账号', '2020-02-18 22:34:47', '2020-02-18 22:34:47', 1);

-- ----------------------------
-- Table structure for ums_admin_login_log
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_login_log`;
CREATE TABLE `ums_admin_login_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) NOT NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `address` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `user_agent` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '浏览器登录类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台用户登录日志信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_admin_login_log
-- ----------------------------
INSERT INTO `ums_admin_login_log` VALUES (1, 3, '2020-02-11 21:56:26', '0:0:0:0:0:0:0:1', '', '');
INSERT INTO `ums_admin_login_log` VALUES (2, 3, '2020-02-12 20:36:32', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (3, 3, '2020-02-12 20:52:20', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (4, 3, '2020-02-12 20:52:34', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (5, 3, '2020-02-12 20:52:52', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (6, 3, '2020-02-12 20:59:10', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (7, 3, '2020-02-12 21:08:28', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (8, 3, '2020-02-12 21:08:37', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (9, 3, '2020-02-12 21:14:57', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (10, 3, '2020-02-12 21:16:17', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (11, 3, '2020-02-12 21:16:38', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (12, 3, '2020-02-12 21:24:01', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (13, 3, '2020-02-12 21:26:58', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (14, 3, '2020-02-12 21:51:53', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (15, 3, '2020-02-12 22:06:38', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (16, 3, '2020-02-12 22:07:44', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (17, 3, '2020-02-12 22:17:43', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (18, 3, '2020-02-12 22:21:06', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (19, 3, '2020-02-13 23:34:57', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (20, 3, '2020-02-13 23:55:18', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (21, 3, '2020-02-14 00:45:44', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (22, 3, '2020-02-14 13:31:09', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (23, 3, '2020-02-14 13:43:19', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (24, 3, '2020-02-14 14:30:00', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (25, 3, '2020-02-14 14:34:14', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (26, 3, '2020-02-14 17:07:45', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (27, 3, '2020-02-14 17:13:50', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (28, 3, '2020-02-14 17:32:59', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (29, 3, '2020-02-14 20:09:46', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (30, 3, '2020-02-14 21:05:08', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (31, 3, '2020-02-14 22:39:09', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (32, 3, '2020-02-14 23:26:09', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (33, 3, '2020-02-15 19:04:37', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (34, 3, '2020-02-15 19:14:28', '192.168.0.106', '', '');
INSERT INTO `ums_admin_login_log` VALUES (35, 3, '2020-02-17 21:08:59', '192.168.0.107', '', '');
INSERT INTO `ums_admin_login_log` VALUES (36, 3, '2020-02-17 21:16:35', '192.168.0.107', '', '');
INSERT INTO `ums_admin_login_log` VALUES (37, 3, '2020-02-17 21:18:18', '192.168.0.107', '', '');
INSERT INTO `ums_admin_login_log` VALUES (38, 3, '2020-02-18 14:45:59', '192.168.0.100', '', '');
INSERT INTO `ums_admin_login_log` VALUES (39, 3, '2020-02-18 15:08:36', '192.168.0.100', '', '');
INSERT INTO `ums_admin_login_log` VALUES (40, 3, '2020-02-18 15:43:17', '192.168.0.100', '', '');
INSERT INTO `ums_admin_login_log` VALUES (41, 3, '2020-02-18 16:20:13', '192.168.0.100', '', '');
INSERT INTO `ums_admin_login_log` VALUES (42, 3, '2020-02-18 16:40:28', '192.168.0.100', '', '');
INSERT INTO `ums_admin_login_log` VALUES (43, 3, '2020-02-18 16:40:36', '192.168.0.100', '', '');
INSERT INTO `ums_admin_login_log` VALUES (44, 3, '2020-02-18 18:56:29', '192.168.0.100', '', '');
INSERT INTO `ums_admin_login_log` VALUES (45, 3, '2020-02-18 21:17:15', '192.168.0.100', '', '');

-- ----------------------------
-- Table structure for ums_admin_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_permission_relation`;
CREATE TABLE `ums_admin_permission_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) NOT NULL DEFAULT 0,
  `permission_id` bigint(20) NOT NULL DEFAULT 0,
  `type` int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台用户和权限关系表(除角色中定义的权限以外的加减权限)' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_admin_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_role_relation`;
CREATE TABLE `ums_admin_role_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) NOT NULL DEFAULT 0,
  `role_id` bigint(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台用户和角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_admin_role_relation
-- ----------------------------
INSERT INTO `ums_admin_role_relation` VALUES (31, 4, 5);
INSERT INTO `ums_admin_role_relation` VALUES (32, 1, 1);
INSERT INTO `ums_admin_role_relation` VALUES (33, 3, 2);
INSERT INTO `ums_admin_role_relation` VALUES (34, 5, 6);

-- ----------------------------
-- Table structure for ums_member
-- ----------------------------
DROP TABLE IF EXISTS `ums_member`;
CREATE TABLE `ums_member`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_leve_idl` bigint(20) NULL DEFAULT 0,
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
  `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '昵称',
  `phone` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `status` int(1) NULL DEFAULT 1 COMMENT '帐号启用状态:0->禁用；1->启用',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '注册时间',
  `icon` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '’‘' COMMENT '头像',
  `gender` int(1) NULL DEFAULT 0 COMMENT '性别：0->未知；1->男；2->女',
  `birthday` date NULL DEFAULT '2020-01-01' COMMENT '生日',
  `city` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '所在城市',
  `job` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '‘’' COMMENT '职业',
  `personalized_signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '个性签名',
  `integration` int(11) NULL DEFAULT 0 COMMENT '积分',
  `growth` int(11) NULL DEFAULT 0 COMMENT '成长值',
  `history_integration` int(11) NULL DEFAULT 0 COMMENT '历史积分数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_member
-- ----------------------------
INSERT INTO `ums_member` VALUES (1, 0, 'rose', '$2a$10$kUajDyqXnwoNMx41F3hcs./rwOxBLX/nTPUnVway.LUbFJxS.VRAy', '', '', 1, '2020-03-01 22:25:09', '’‘', 0, '2020-01-01', '', '‘’', '', 0, 0, 0);
INSERT INTO `ums_member` VALUES (2, 0, 'wangnian', '$2a$10$kUajDyqXnwoNMx41F3hcs./rwOxBLX/nTPUnVway.LUbFJxS.VRAy', '', '', 1, '2020-04-06 15:49:00', '’‘', 0, '2020-01-01', '', '‘’', '', 0, 0, 0);

-- ----------------------------
-- Table structure for ums_member_receive_address
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_receive_address`;
CREATE TABLE `ums_member_receive_address`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) NOT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收货人名称',
  `phone_number` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号码',
  `default_status` int(1) NOT NULL DEFAULT 0 COMMENT '是否为默认:1->默认；0->非默认',
  `province` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '省份/直辖市',
  `city` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '城市',
  `region` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '区',
  `street` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '街道',
  `detail_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '详细地址（街道）',
  `tag` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户收货地址表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_member_receive_address
-- ----------------------------
INSERT INTO `ums_member_receive_address` VALUES (1, 1, '王念', '18913570960', 1, '江苏省', '徐州市', '邳州市', '议堂镇', '张单村东王55号', '家');
INSERT INTO `ums_member_receive_address` VALUES (2, 1, '王念', '18913570960', 0, '江苏省', '常州市', '武进区', '牛塘镇', '牛塘工业园区创新力88号', '公司');
INSERT INTO `ums_member_receive_address` VALUES (4, 1, '王念', '18913570960', 0, '江苏省', '苏州市', '张家港市', '杨舍镇', '江苏科技大学', '学校');

-- ----------------------------
-- Table structure for ums_menu
-- ----------------------------
DROP TABLE IF EXISTS `ums_menu`;
CREATE TABLE `ums_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父级ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `level` int(4) NULL DEFAULT NULL COMMENT '菜单级数',
  `sort` int(4) NULL DEFAULT NULL COMMENT '菜单排序',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '前端名称',
  `icon` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '前端图标',
  `hidden` int(1) NULL DEFAULT NULL COMMENT '前端隐藏',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_menu
-- ----------------------------
INSERT INTO `ums_menu` VALUES (1, 0, '2020-02-02 14:50:36', '商品', 0, 0, 'pms', 'product', 0);
INSERT INTO `ums_menu` VALUES (2, 1, '2020-02-02 14:51:50', '商品列表', 1, 0, 'product', 'product-list', 0);
INSERT INTO `ums_menu` VALUES (3, 1, '2020-02-02 14:52:44', '添加商品', 1, 0, 'addProduct', 'product-add', 0);
INSERT INTO `ums_menu` VALUES (4, 1, '2020-02-02 14:53:51', '商品分类', 1, 0, 'productCate', 'product-cate', 0);
INSERT INTO `ums_menu` VALUES (5, 1, '2020-02-02 14:54:51', '商品类型', 1, 0, 'productAttr', 'product-attr', 0);
INSERT INTO `ums_menu` VALUES (6, 1, '2020-02-02 14:56:29', '品牌管理', 1, 0, 'brand', 'product-brand', 0);
INSERT INTO `ums_menu` VALUES (7, 0, '2020-02-02 16:54:07', '订单', 0, 0, 'oms', 'order', 0);
INSERT INTO `ums_menu` VALUES (8, 7, '2020-02-02 16:55:18', '订单列表', 1, 0, 'order', 'product-list', 0);
INSERT INTO `ums_menu` VALUES (9, 7, '2020-02-02 16:56:46', '订单设置', 1, 0, 'orderSetting', 'order-setting', 0);
INSERT INTO `ums_menu` VALUES (10, 7, '2020-02-02 16:57:39', '退货申请处理', 1, 0, 'returnApply', 'order-return', 0);
INSERT INTO `ums_menu` VALUES (11, 7, '2020-02-02 16:59:40', '退货原因设置', 1, 0, 'returnReason', 'order-return-reason', 0);
INSERT INTO `ums_menu` VALUES (12, 0, '2020-02-04 16:18:00', '营销', 0, 0, 'sms', 'sms', 0);
INSERT INTO `ums_menu` VALUES (13, 12, '2020-02-04 16:19:22', '秒杀活动列表', 1, 0, 'flash', 'sms-flash', 0);
INSERT INTO `ums_menu` VALUES (14, 12, '2020-02-04 16:20:16', '优惠券列表', 1, 0, 'coupon', 'sms-coupon', 0);
INSERT INTO `ums_menu` VALUES (16, 12, '2020-02-07 16:22:38', '品牌推荐', 1, 0, 'homeBrand', 'product-brand', 0);
INSERT INTO `ums_menu` VALUES (17, 12, '2020-02-07 16:23:14', '新品推荐', 1, 0, 'homeNew', 'sms-new', 0);
INSERT INTO `ums_menu` VALUES (18, 12, '2020-02-07 16:26:38', '人气推荐', 1, 0, 'homeHot', 'sms-hot', 0);
INSERT INTO `ums_menu` VALUES (19, 12, '2020-02-07 16:28:16', '专题推荐', 1, 0, 'homeSubject', 'sms-subject', 0);
INSERT INTO `ums_menu` VALUES (20, 12, '2020-02-07 16:28:42', '广告列表', 1, 0, 'homeAdvertise', 'sms-ad', 0);
INSERT INTO `ums_menu` VALUES (21, 0, '2020-02-07 16:29:13', '权限', 0, 0, 'ums', 'ums', 0);
INSERT INTO `ums_menu` VALUES (22, 21, '2020-02-07 16:29:51', '用户列表', 1, 0, 'admin', 'ums-admin', 0);
INSERT INTO `ums_menu` VALUES (23, 21, '2020-02-07 16:30:13', '角色列表', 1, 0, 'role', 'ums-role', 0);
INSERT INTO `ums_menu` VALUES (24, 21, '2020-02-07 16:30:53', '菜单列表', 1, 0, 'menu', 'ums-menu', 0);
INSERT INTO `ums_menu` VALUES (25, 21, '2020-02-07 16:31:13', '资源列表', 1, 0, 'resource', 'ums-resource', 0);

-- ----------------------------
-- Table structure for ums_permission
-- ----------------------------
DROP TABLE IF EXISTS `ums_permission`;
CREATE TABLE `ums_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) NOT NULL DEFAULT 0 COMMENT '父级权限id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `value` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '‘’' COMMENT '权限值',
  `icon` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '图标',
  `type` int(1) NOT NULL DEFAULT 2 COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
  `uri` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '前端资源路径',
  `status` int(1) NOT NULL DEFAULT 1 COMMENT '启用状态；0->禁用；1->启用',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `sort` int(11) NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台用户权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_permission
-- ----------------------------
INSERT INTO `ums_permission` VALUES (1, 0, '商品', '', '', 0, '', 1, '2018-09-29 16:15:14', 0);
INSERT INTO `ums_permission` VALUES (2, 1, '商品列表', 'pms:product:read', '', 1, '/pms/product/index', 1, '2018-09-29 16:17:01', 0);
INSERT INTO `ums_permission` VALUES (3, 1, '添加商品', 'pms:product:create', '', 1, '/pms/product/add', 1, '2018-09-29 16:18:51', 0);
INSERT INTO `ums_permission` VALUES (4, 1, '商品分类', 'pms:productCategory:read', '', 1, '/pms/productCate/index', 1, '2018-09-29 16:23:07', 0);
INSERT INTO `ums_permission` VALUES (5, 1, '商品类型', 'pms:productAttribute:read', '', 1, '/pms/productAttr/index', 1, '2018-09-29 16:24:43', 0);
INSERT INTO `ums_permission` VALUES (6, 1, '品牌管理', 'pms:brand:read', '', 1, '/pms/brand/index', 1, '2018-09-29 16:25:45', 0);
INSERT INTO `ums_permission` VALUES (7, 2, '编辑商品', 'pms:product:update', '', 2, '/pms/product/updateProduct', 1, '2018-09-29 16:34:23', 0);
INSERT INTO `ums_permission` VALUES (8, 2, '删除商品', 'pms:product:delete', '', 2, '/pms/product/delete', 1, '2018-09-29 16:38:33', 0);
INSERT INTO `ums_permission` VALUES (9, 4, '添加商品分类', 'pms:productCategory:create', '', 2, '/pms/productCate/create', 1, '2018-09-29 16:43:23', 0);
INSERT INTO `ums_permission` VALUES (10, 4, '修改商品分类', 'pms:productCategory:update', '', 2, '/pms/productCate/update', 1, '2018-09-29 16:43:55', 0);
INSERT INTO `ums_permission` VALUES (11, 4, '删除商品分类', 'pms:productCategory:delete', '', 2, '/pms/productAttr/delete', 1, '2018-09-29 16:44:38', 0);
INSERT INTO `ums_permission` VALUES (12, 5, '添加商品类型', 'pms:productAttribute:create', '', 2, '/pms/productAttr/create', 1, '2018-09-29 16:45:25', 0);
INSERT INTO `ums_permission` VALUES (13, 5, '修改商品类型', 'pms:productAttribute:update', '', 2, '/pms/productAttr/update', 1, '2018-09-29 16:48:08', 0);
INSERT INTO `ums_permission` VALUES (14, 5, '删除商品类型', 'pms:productAttribute:delete', '', 2, '/pms/productAttr/delete', 1, '2018-09-29 16:48:44', 0);
INSERT INTO `ums_permission` VALUES (15, 6, '添加品牌', 'pms:brand:create', '', 2, '/pms/brand/add', 1, '2018-09-29 16:49:34', 0);
INSERT INTO `ums_permission` VALUES (16, 6, '修改品牌', 'pms:brand:update', '', 2, '/pms/brand/update', 1, '2018-09-29 16:50:55', 0);
INSERT INTO `ums_permission` VALUES (17, 6, '删除品牌', 'pms:brand:delete', '', 2, '/pms/brand/delete', 1, '2018-09-29 16:50:59', 0);
INSERT INTO `ums_permission` VALUES (18, 0, '首页', '', '', 0, '', 1, '2018-09-29 16:51:57', 0);

-- ----------------------------
-- Table structure for ums_resource
-- ----------------------------
DROP TABLE IF EXISTS `ums_resource`;
CREATE TABLE `ums_resource`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源URL',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '资源分类ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_resource
-- ----------------------------
INSERT INTO `ums_resource` VALUES (2, '2020-02-04 17:05:35', '商品属性分类管理', '/productAttribute/**', NULL, 1);
INSERT INTO `ums_resource` VALUES (3, '2020-02-04 17:06:13', '商品属性管理', '/productAttribute/**', NULL, 1);
INSERT INTO `ums_resource` VALUES (4, '2020-02-04 17:07:15', '商品分类管理', '/productCategory/**', NULL, 1);
INSERT INTO `ums_resource` VALUES (5, '2020-02-04 17:09:16', '商品管理', '/product/**', NULL, 1);
INSERT INTO `ums_resource` VALUES (6, '2020-02-04 17:09:53', '商品库存管理', '/sku/**', NULL, 1);
INSERT INTO `ums_resource` VALUES (8, '2020-02-05 14:43:37', '订单管理', '/order/**', '', 2);
INSERT INTO `ums_resource` VALUES (9, '2020-02-05 14:44:22', ' 订单退货申请管理', '/returnApply/**', '', 2);
INSERT INTO `ums_resource` VALUES (10, '2020-02-05 14:45:08', '退货原因管理', '/returnReason/**', '', 2);
INSERT INTO `ums_resource` VALUES (11, '2020-02-05 14:45:43', '订单设置管理', '/orderSetting/**', '', 2);
INSERT INTO `ums_resource` VALUES (12, '2020-02-05 14:46:23', '收货地址管理', '/companyAddress/**', '', 2);
INSERT INTO `ums_resource` VALUES (13, '2020-02-07 16:37:22', '优惠券管理', '/coupon/**', '', 3);
INSERT INTO `ums_resource` VALUES (14, '2020-02-07 16:37:59', '优惠券领取记录管理', '/couponHistory/**', '', 3);
INSERT INTO `ums_resource` VALUES (15, '2020-02-07 16:38:28', '限时购活动管理', '/flash/**', '', 3);
INSERT INTO `ums_resource` VALUES (16, '2020-02-07 16:38:59', '限时购商品关系管理', '/flashProductRelation/**', '', 3);
INSERT INTO `ums_resource` VALUES (17, '2020-02-07 16:39:22', '限时购场次管理', '/flashSession/**', '', 3);
INSERT INTO `ums_resource` VALUES (18, '2020-02-07 16:40:07', '首页轮播广告管理', '/home/advertise/**', '', 3);
INSERT INTO `ums_resource` VALUES (19, '2020-02-07 16:40:34', '首页品牌管理', '/home/brand/**', '', 3);
INSERT INTO `ums_resource` VALUES (20, '2020-02-07 16:41:06', '首页新品管理', '/home/newProduct/**', '', 3);
INSERT INTO `ums_resource` VALUES (21, '2020-02-07 16:42:16', '首页人气推荐管理', '/home/recommendProduct/**', '', 3);
INSERT INTO `ums_resource` VALUES (22, '2020-02-07 16:42:48', '首页专题推荐管理', '/home/recommendSubject/**', '', 3);
INSERT INTO `ums_resource` VALUES (23, '2020-02-07 16:44:56', ' 商品优选管理', '/prefrenceArea/**', '', 5);
INSERT INTO `ums_resource` VALUES (24, '2020-02-07 16:45:39', '商品专题管理', '/subject/**', '', 5);
INSERT INTO `ums_resource` VALUES (25, '2020-02-07 16:47:34', '后台用户管理', '/admin/**', '', 4);
INSERT INTO `ums_resource` VALUES (26, '2020-02-07 16:48:24', '后台用户角色管理', '/role/**', '', 4);
INSERT INTO `ums_resource` VALUES (27, '2020-02-07 16:48:48', '后台菜单管理', '/menu/**', '', 4);
INSERT INTO `ums_resource` VALUES (28, '2020-02-07 16:49:18', '后台资源分类管理', '/resourceCategory/**', '', 4);
INSERT INTO `ums_resource` VALUES (29, '2020-02-07 16:49:45', '后台资源管理', '/resource/**', '', 4);

-- ----------------------------
-- Table structure for ums_resource_category
-- ----------------------------
DROP TABLE IF EXISTS `ums_resource_category`;
CREATE TABLE `ums_resource_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `sort` int(4) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资源分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_resource_category
-- ----------------------------
INSERT INTO `ums_resource_category` VALUES (1, '2020-02-05 10:21:44', '商品模块', 0);
INSERT INTO `ums_resource_category` VALUES (2, '2020-02-05 10:22:34', '订单模块', 0);
INSERT INTO `ums_resource_category` VALUES (3, '2020-02-05 10:22:48', '营销模块', 0);
INSERT INTO `ums_resource_category` VALUES (4, '2020-02-05 10:23:04', '权限模块', 0);
INSERT INTO `ums_resource_category` VALUES (5, '2020-02-07 16:34:27', '内容模块', 0);
INSERT INTO `ums_resource_category` VALUES (6, '2020-02-07 16:35:49', '其他模块', 0);

-- ----------------------------
-- Table structure for ums_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '‘’' COMMENT '名称',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '描述',
  `admin_count` int(11) NOT NULL DEFAULT 0 COMMENT '拥有该权限的后台用户数量',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `status` int(1) NOT NULL DEFAULT 1 COMMENT '启用状态：0->禁用；1->启用',
  `sort` int(11) NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台用户角色信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_role
-- ----------------------------
INSERT INTO `ums_role` VALUES (1, '商品管理员', '只能查看及操作商品', 0, '2020-02-03 16:50:37', 1, 0);
INSERT INTO `ums_role` VALUES (2, '订单管理员', '只能查看及操作订单', 0, '2018-09-30 15:53:45', 1, 0);
INSERT INTO `ums_role` VALUES (5, '超级管理员', '拥有所有查看和操作功能', 0, '2020-02-02 15:11:05', 1, 0);
INSERT INTO `ums_role` VALUES (6, '测试角色', '仅供测试', 0, '2020-02-12 10:14:09', 1, 0);

-- ----------------------------
-- Table structure for ums_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_menu_relation`;
CREATE TABLE `ums_role_menu_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 113 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台角色菜单关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_role_menu_relation
-- ----------------------------
INSERT INTO `ums_role_menu_relation` VALUES (53, 2, 7);
INSERT INTO `ums_role_menu_relation` VALUES (54, 2, 8);
INSERT INTO `ums_role_menu_relation` VALUES (55, 2, 9);
INSERT INTO `ums_role_menu_relation` VALUES (56, 2, 10);
INSERT INTO `ums_role_menu_relation` VALUES (57, 2, 11);
INSERT INTO `ums_role_menu_relation` VALUES (72, 5, 1);
INSERT INTO `ums_role_menu_relation` VALUES (73, 5, 2);
INSERT INTO `ums_role_menu_relation` VALUES (74, 5, 3);
INSERT INTO `ums_role_menu_relation` VALUES (75, 5, 4);
INSERT INTO `ums_role_menu_relation` VALUES (76, 5, 5);
INSERT INTO `ums_role_menu_relation` VALUES (77, 5, 6);
INSERT INTO `ums_role_menu_relation` VALUES (78, 5, 7);
INSERT INTO `ums_role_menu_relation` VALUES (79, 5, 8);
INSERT INTO `ums_role_menu_relation` VALUES (80, 5, 9);
INSERT INTO `ums_role_menu_relation` VALUES (81, 5, 10);
INSERT INTO `ums_role_menu_relation` VALUES (82, 5, 11);
INSERT INTO `ums_role_menu_relation` VALUES (83, 5, 12);
INSERT INTO `ums_role_menu_relation` VALUES (84, 5, 13);
INSERT INTO `ums_role_menu_relation` VALUES (85, 5, 14);
INSERT INTO `ums_role_menu_relation` VALUES (86, 5, 16);
INSERT INTO `ums_role_menu_relation` VALUES (87, 5, 17);
INSERT INTO `ums_role_menu_relation` VALUES (88, 5, 18);
INSERT INTO `ums_role_menu_relation` VALUES (89, 5, 19);
INSERT INTO `ums_role_menu_relation` VALUES (90, 5, 20);
INSERT INTO `ums_role_menu_relation` VALUES (91, 5, 21);
INSERT INTO `ums_role_menu_relation` VALUES (92, 5, 22);
INSERT INTO `ums_role_menu_relation` VALUES (93, 5, 23);
INSERT INTO `ums_role_menu_relation` VALUES (94, 5, 24);
INSERT INTO `ums_role_menu_relation` VALUES (95, 5, 25);
INSERT INTO `ums_role_menu_relation` VALUES (107, 1, 1);
INSERT INTO `ums_role_menu_relation` VALUES (108, 1, 2);
INSERT INTO `ums_role_menu_relation` VALUES (109, 1, 3);
INSERT INTO `ums_role_menu_relation` VALUES (110, 1, 4);
INSERT INTO `ums_role_menu_relation` VALUES (111, 1, 5);
INSERT INTO `ums_role_menu_relation` VALUES (112, 1, 6);

-- ----------------------------
-- Table structure for ums_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_permission_relation`;
CREATE TABLE `ums_role_permission_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL DEFAULT 0,
  `permission_id` bigint(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台用户角色和权限关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_role_permission_relation
-- ----------------------------
INSERT INTO `ums_role_permission_relation` VALUES (1, 1, 1);
INSERT INTO `ums_role_permission_relation` VALUES (2, 1, 2);
INSERT INTO `ums_role_permission_relation` VALUES (3, 1, 3);
INSERT INTO `ums_role_permission_relation` VALUES (4, 1, 7);
INSERT INTO `ums_role_permission_relation` VALUES (5, 1, 8);
INSERT INTO `ums_role_permission_relation` VALUES (6, 2, 4);
INSERT INTO `ums_role_permission_relation` VALUES (7, 2, 9);
INSERT INTO `ums_role_permission_relation` VALUES (8, 2, 10);
INSERT INTO `ums_role_permission_relation` VALUES (9, 2, 11);
INSERT INTO `ums_role_permission_relation` VALUES (10, 3, 5);
INSERT INTO `ums_role_permission_relation` VALUES (11, 3, 12);
INSERT INTO `ums_role_permission_relation` VALUES (12, 3, 13);
INSERT INTO `ums_role_permission_relation` VALUES (13, 3, 14);
INSERT INTO `ums_role_permission_relation` VALUES (14, 4, 6);
INSERT INTO `ums_role_permission_relation` VALUES (15, 4, 15);
INSERT INTO `ums_role_permission_relation` VALUES (16, 4, 16);
INSERT INTO `ums_role_permission_relation` VALUES (17, 4, 17);

-- ----------------------------
-- Table structure for ums_role_resource_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_resource_relation`;
CREATE TABLE `ums_role_resource_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `resource_id` bigint(20) NULL DEFAULT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 178 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色资源关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_role_resource_relation
-- ----------------------------
INSERT INTO `ums_role_resource_relation` VALUES (103, 2, 8);
INSERT INTO `ums_role_resource_relation` VALUES (104, 2, 9);
INSERT INTO `ums_role_resource_relation` VALUES (105, 2, 10);
INSERT INTO `ums_role_resource_relation` VALUES (106, 2, 11);
INSERT INTO `ums_role_resource_relation` VALUES (107, 2, 12);
INSERT INTO `ums_role_resource_relation` VALUES (142, 5, 1);
INSERT INTO `ums_role_resource_relation` VALUES (143, 5, 2);
INSERT INTO `ums_role_resource_relation` VALUES (144, 5, 3);
INSERT INTO `ums_role_resource_relation` VALUES (145, 5, 4);
INSERT INTO `ums_role_resource_relation` VALUES (146, 5, 5);
INSERT INTO `ums_role_resource_relation` VALUES (147, 5, 6);
INSERT INTO `ums_role_resource_relation` VALUES (148, 5, 8);
INSERT INTO `ums_role_resource_relation` VALUES (149, 5, 9);
INSERT INTO `ums_role_resource_relation` VALUES (150, 5, 10);
INSERT INTO `ums_role_resource_relation` VALUES (151, 5, 11);
INSERT INTO `ums_role_resource_relation` VALUES (152, 5, 12);
INSERT INTO `ums_role_resource_relation` VALUES (153, 5, 13);
INSERT INTO `ums_role_resource_relation` VALUES (154, 5, 14);
INSERT INTO `ums_role_resource_relation` VALUES (155, 5, 15);
INSERT INTO `ums_role_resource_relation` VALUES (156, 5, 16);
INSERT INTO `ums_role_resource_relation` VALUES (157, 5, 17);
INSERT INTO `ums_role_resource_relation` VALUES (158, 5, 18);
INSERT INTO `ums_role_resource_relation` VALUES (159, 5, 19);
INSERT INTO `ums_role_resource_relation` VALUES (160, 5, 20);
INSERT INTO `ums_role_resource_relation` VALUES (161, 5, 21);
INSERT INTO `ums_role_resource_relation` VALUES (162, 5, 22);
INSERT INTO `ums_role_resource_relation` VALUES (163, 5, 23);
INSERT INTO `ums_role_resource_relation` VALUES (164, 5, 24);
INSERT INTO `ums_role_resource_relation` VALUES (165, 5, 25);
INSERT INTO `ums_role_resource_relation` VALUES (166, 5, 26);
INSERT INTO `ums_role_resource_relation` VALUES (167, 5, 27);
INSERT INTO `ums_role_resource_relation` VALUES (168, 5, 28);
INSERT INTO `ums_role_resource_relation` VALUES (169, 5, 29);
INSERT INTO `ums_role_resource_relation` VALUES (170, 1, 1);
INSERT INTO `ums_role_resource_relation` VALUES (171, 1, 2);
INSERT INTO `ums_role_resource_relation` VALUES (172, 1, 3);
INSERT INTO `ums_role_resource_relation` VALUES (173, 1, 4);
INSERT INTO `ums_role_resource_relation` VALUES (174, 1, 5);
INSERT INTO `ums_role_resource_relation` VALUES (175, 1, 6);
INSERT INTO `ums_role_resource_relation` VALUES (176, 1, 23);
INSERT INTO `ums_role_resource_relation` VALUES (177, 1, 24);

SET FOREIGN_KEY_CHECKS = 1;
