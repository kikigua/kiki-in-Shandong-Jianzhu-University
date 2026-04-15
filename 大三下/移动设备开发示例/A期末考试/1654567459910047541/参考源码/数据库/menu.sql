/*
Navicat MySQL Data Transfer

Source Server         : mangoConn1
Source Server Version : 50730
Source Host           : localhost:3306
Source Database       : foodbase

Target Server Type    : MYSQL
Target Server Version : 50730
File Encoding         : 65001

Date: 2021-06-26 16:59:41
*/

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `cname` varchar(50) DEFAULT NULL,
  `uid` varchar(10) DEFAULT NULL,
  `primarypic` varchar(50) DEFAULT NULL,
  `food` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)    
) ENGINE=InnoDB AUTO_INCREMENT=1039 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1021', '唯美黑森林', '张三', '1432555680984.jpeg', '可可戚蛋糕|一个%淡奶油|600克%黑巧克力|100克');
INSERT INTO `menu` VALUES ('1022', '秘汁菠菜', '张三', '1432556874283.jpeg', '菠菜|一小捆%植物油|少半碗');
INSERT INTO `menu` VALUES ('1023', '泰式虾尖', '张三', '1432558038419.jpeg', '虾尖|适量%生菜|适量%鱼露|适量%芫荽|一棵%小辣椒|适量');
INSERT INTO `menu` VALUES ('1024', '凉拌青丝瓜', '张三', '1432559076962.jpeg','青瓜|1条%莴笋|100克%红萝卜|100克%豆腐皮|1块%盐|4块%酱油|30ml%香油|10ml%辣椒油|15ml');
INSERT INTO `menu` VALUES ('1030', '红油猪儿', '张三', '1432562899389.jpeg', '猪耳朵|2个%五花肉|1块%黄瓜|2个');
INSERT INTO `menu` VALUES ('1031', '洋葱虾仁', '张三', '1432647418980.jpeg', '明虾|250克%洋葱|1个');
INSERT INTO `menu` VALUES ('1032', '橄榄油拌樱桃小胡萝卜', '张三', '1432648049953.jpeg', '樱桃萝卜|150g%橄榄油|5g');
INSERT INTO `menu` VALUES ('1033', '红烧素鸡', '张三', '1432648803037.jpeg', '素鸡|350克');
INSERT INTO `menu` VALUES ('1034', '凉拌木耳', '张三', '1432649604819.jpeg', '干木耳|50g%橄榄油|一勺');
INSERT INTO `menu` VALUES ('1035', '京味儿烤羊肉', '张三', '1432650729708.jpeg', '羊肉|300克');
INSERT INTO `menu` VALUES ('1036', '红烧小鱼野', '张三', '1432651493599.jpeg', '小白鱼|5个%泡椒|2勺');
INSERT INTO `menu` VALUES ('1037', '黑芝麻蛋白薄脆饼', 'angle', '1432654677144.jpeg', '低筋面粉|50g%黄油|50g%蛋清|一个');
INSERT INTO `menu` VALUES ('1038', '川氏烤鱼', 'angle', '1432657309290.jpeg', '草鱼|900克%香芹|50克%青笋|50克%鲜香菇|20克%黄豆芽|20克%洋葱|10克%香菜|适量');
