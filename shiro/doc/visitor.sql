/*
 Navicat Premium Data Transfer

 Source Server         : visitor
 Source Server Type    : MySQL
 Source Server Version : 50641
 Source Host           : 211.149.213.54
 Source Database       : visitor

 Target Server Type    : MySQL
 Target Server Version : 50641
 File Encoding         : utf-8

 Date: 04/03/2019 15:45:41 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tb_company`
-- ----------------------------
DROP TABLE IF EXISTS `tb_company`;
CREATE TABLE `tb_company` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT 'id',
  `parent_id` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '上级单位id',
  `parent_ids` text COLLATE utf8mb4_bin NOT NULL COMMENT '父节点ids',
  `sort` decimal(38,0) NOT NULL COMMENT '序号',
  `name` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '单位名称',
  `app_id` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信APPID',
  `app_secret` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信appsecret',
  `visitor_tpl_id` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '访问者预约模板消息ID',
  `review_tpl_id` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '审核者预约模板消息ID',
  `visitor_end_tpl_id` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '访问者结果模板消息ID',
  `review_end_tpl_id` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '审核者结果模板消息ID',
  `picture` varchar(400) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '营业执照',
  `place_province` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所在省',
  `place_city` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所在市',
  `place_area` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所在区',
  `place_info` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所在详细地址',
  `show_attence` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '是否开启考勤',
  `create_by` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '修改者',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `remarks` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `del_flag` char(1) COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='单位管理';

-- ----------------------------
--  Table structure for `tb_company_config`
-- ----------------------------
DROP TABLE IF EXISTS `tb_company_config`;
CREATE TABLE `tb_company_config` (
  `company_id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '单位id',
  `screen_image` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客屏图像',
  `screen_welcome` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客屏欢迎语',
  `clear_time` datetime DEFAULT NULL COMMENT '清场时间',
  KEY `fk_reference_8` (`company_id`),
  CONSTRAINT `fk_reference_8` FOREIGN KEY (`company_id`) REFERENCES `tb_company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='单位基础配置';

-- ----------------------------
--  Table structure for `tb_department`
-- ----------------------------
DROP TABLE IF EXISTS `tb_department`;
CREATE TABLE `tb_department` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT 'id',
  `company_id` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所属单位',
  `parent_id` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '父节点id',
  `parent_ids` text COLLATE utf8mb4_bin NOT NULL COMMENT '父节点ids',
  `sort` decimal(38,0) NOT NULL COMMENT '序号',
  `name` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '部门名称',
  `create_by` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '修改者',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `remarks` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `del_flag` char(1) COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `fk_reference_9` (`company_id`),
  CONSTRAINT `fk_reference_9` FOREIGN KEY (`company_id`) REFERENCES `tb_company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='部门管理';

-- ----------------------------
--  Table structure for `tb_device`
-- ----------------------------
DROP TABLE IF EXISTS `tb_device`;
CREATE TABLE `tb_device` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT 'id',
  `company_id` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '单位id',
  `code` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '设备编号',
  `name` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '设备名称',
  `sim_no` varchar(30) COLLATE utf8mb4_bin NOT NULL COMMENT 'sim卡号',
  `address` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '设备地址',
  `status` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '设备状态',
  `supplier` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '生产厂商',
  `factory_date` datetime DEFAULT NULL COMMENT '生产日期',
  `create_by` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '修改者',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `remarks` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `del_flag` char(1) COLLATE utf8mb4_bin NOT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='设备管理';

-- ----------------------------
--  Table structure for `tb_person`
-- ----------------------------
DROP TABLE IF EXISTS `tb_person`;
CREATE TABLE `tb_person` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT 'id',
  `company_id` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所属单位',
  `department_id` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所属部门',
  `name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '姓名',
  `sex` varchar(6) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '性别',
  `identity_card` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份证号',
  `phone` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系电话',
  `wx_openid` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信openid',
  `picture` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图像',
  `place` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '位置',
  `picture_flag` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图像审核标记',
  `picture_remarks` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图像审核理由',
  `create_by` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '修改者',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `remarks` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `del_flag` char(1) COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `fk_reference_10` (`department_id`),
  CONSTRAINT `fk_reference_10` FOREIGN KEY (`department_id`) REFERENCES `tb_department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='单位人员管理';

-- ----------------------------
--  Table structure for `tb_person_attence`
-- ----------------------------
DROP TABLE IF EXISTS `tb_person_attence`;
CREATE TABLE `tb_person_attence` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT 'id',
  `person_id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '人员id',
  `device_id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '打卡设备id',
  `attence_time` datetime NOT NULL COMMENT '打卡时间',
  `create_by` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '修改者',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `remarks` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `del_flag` char(1) COLLATE utf8mb4_bin NOT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='员工考勤表';

-- ----------------------------
--  Table structure for `tb_schedule`
-- ----------------------------
DROP TABLE IF EXISTS `tb_schedule`;
CREATE TABLE `tb_schedule` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT 'id',
  `company_id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '单位ID',
  `name` varchar(40) COLLATE utf8mb4_bin NOT NULL COMMENT '班次名称',
  `start_date` datetime NOT NULL COMMENT '班次开始时间',
  `end_date` datetime NOT NULL COMMENT '班次结束时间',
  `create_by` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '修改者',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `remarks` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `del_flag` char(1) COLLATE utf8mb4_bin NOT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='排班管理表';

-- ----------------------------
--  Table structure for `tb_sys_app_version`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_app_version`;
CREATE TABLE `tb_sys_app_version` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT 'id',
  `app_name` varchar(40) COLLATE utf8mb4_bin NOT NULL COMMENT 'app文件名称',
  `version` varchar(40) COLLATE utf8mb4_bin NOT NULL COMMENT '版本号',
  `app_path` varchar(400) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'APP文件路径',
  `create_by` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '修改者',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `remarks` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `del_flag` char(1) COLLATE utf8mb4_bin NOT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='设备app版本管理';

-- ----------------------------
--  Table structure for `tb_sys_area`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_area`;
CREATE TABLE `tb_sys_area` (
  `code` varchar(10) COLLATE utf8mb4_bin NOT NULL COMMENT '地区编号',
  `name` varchar(80) COLLATE utf8mb4_bin NOT NULL COMMENT '地区名称',
  `parent_code` varchar(10) COLLATE utf8mb4_bin NOT NULL COMMENT '上级编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='省市区数据表';

-- ----------------------------
--  Table structure for `tb_sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_log`;
CREATE TABLE `tb_sys_log` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT 'id',
  `type` char(1) COLLATE utf8mb4_bin NOT NULL COMMENT '类型',
  `title` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标题',
  `create_by` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `remote_addr` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地址',
  `user_agent` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户带来',
  `request_uri` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求uri',
  `method` varchar(5) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '方法',
  `params` text COLLATE utf8mb4_bin COMMENT '参数',
  `exception` text COLLATE utf8mb4_bin COMMENT '异常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统日志';

-- ----------------------------
--  Table structure for `tb_sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_menu`;
CREATE TABLE `tb_sys_menu` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT 'id',
  `parent_id` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '父菜单id',
  `parent_ids` text COLLATE utf8mb4_bin COMMENT '父菜单ids',
  `name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜单名称',
  `sort` decimal(38,0) DEFAULT NULL COMMENT '序号',
  `href` text COLLATE utf8mb4_bin COMMENT '地址',
  `target` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '目标',
  `icon` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图标',
  `is_show` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否可见',
  `permission` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限许可',
  `create_by` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改者',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `remarks` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `del_flag` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `ak_key_1` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='菜单';

-- ----------------------------
--  Table structure for `tb_sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_role`;
CREATE TABLE `tb_sys_role` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT 'id',
  `name` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称',
  `enname` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '英文名称',
  `role_type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色类别',
  `data_scope` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '数据范围',
  `is_sys` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否系统默认',
  `useable` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否可用',
  `create_by` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '修改者',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `remarks` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `del_flag` char(1) COLLATE utf8mb4_bin NOT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `ak_key_1` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色';

-- ----------------------------
--  Table structure for `tb_sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_role_menu`;
CREATE TABLE `tb_sys_role_menu` (
  `role_id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '角色id',
  `menu_id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '菜单id',
  `is_show` char(1) COLLATE utf8mb4_bin NOT NULL COMMENT '是否显示',
  KEY `fk_reference_12` (`role_id`),
  KEY `fk_reference_13` (`menu_id`),
  CONSTRAINT `fk_reference_12` FOREIGN KEY (`role_id`) REFERENCES `tb_sys_role` (`id`),
  CONSTRAINT `fk_reference_13` FOREIGN KEY (`menu_id`) REFERENCES `tb_sys_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色菜单';

-- ----------------------------
--  Table structure for `tb_sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_user`;
CREATE TABLE `tb_sys_user` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT 'id',
  `login_name` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '账号',
  `password` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `name` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `email` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '电话',
  `wx_openid` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信openid',
  `login_ip` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '上次登录ip',
  `login_date` datetime DEFAULT NULL COMMENT '上次登录时间',
  `login_flag` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录标记',
  `create_by` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '修改者',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `remarks` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `del_flag` char(1) COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  `status` char(1) COLLATE utf8mb4_bin NOT NULL COMMENT '账号状态',
  `company_id` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '单位ID',
  `picture` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`),
  KEY `ak_key_1` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统用户';

-- ----------------------------
--  Table structure for `tb_sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_user_role`;
CREATE TABLE `tb_sys_user_role` (
  `user_id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '用户id',
  `role_id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '角色id',
  KEY `fk_reference_19` (`user_id`),
  KEY `fk_reference_20` (`role_id`),
  CONSTRAINT `fk_reference_19` FOREIGN KEY (`user_id`) REFERENCES `tb_sys_user` (`id`),
  CONSTRAINT `fk_reference_20` FOREIGN KEY (`role_id`) REFERENCES `tb_sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户角色';

-- ----------------------------
--  Table structure for `tb_visit_rec`
-- ----------------------------
DROP TABLE IF EXISTS `tb_visit_rec`;
CREATE TABLE `tb_visit_rec` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '编号',
  `visited_person_id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '被访问者id',
  `visited_company_id` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '被访问者id',
  `visit_date` datetime DEFAULT NULL COMMENT '访问时间',
  `end_date` datetime DEFAULT NULL COMMENT '访问结束时间',
  `visit_content` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '访问事由',
  `visit_status` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '到访状态',
  `review_status` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '审核状态',
  `submit_open_id` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '访问提交人员OPENID',
  `create_by` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='访问记录表';

-- ----------------------------
--  Table structure for `tb_visitor`
-- ----------------------------
DROP TABLE IF EXISTS `tb_visitor`;
CREATE TABLE `tb_visitor` (
  `record_id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '访问记录id',
  `visitor_name` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '访问者姓名',
  `visitor_phone` varchar(11) COLLATE utf8mb4_bin NOT NULL COMMENT '访问者电话',
  `visitor_identity` varchar(18) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '访问者身份证号',
  `visitor_image` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '访问者图像',
  `in_date` datetime DEFAULT NULL COMMENT '到访时间',
  `out_date` datetime DEFAULT NULL COMMENT '离开时间',
  `sort` int(11) DEFAULT NULL COMMENT '登记顺序',
  `check_image` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '对比图像',
  `in_type` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '进场类型',
  KEY `fk_reference_7` (`record_id`),
  CONSTRAINT `fk_reference_7` FOREIGN KEY (`record_id`) REFERENCES `tb_visit_rec` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='访问人员表';

-- ----------------------------
--  Table structure for `tb_visitor_blacklist`
-- ----------------------------
DROP TABLE IF EXISTS `tb_visitor_blacklist`;
CREATE TABLE `tb_visitor_blacklist` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '编号',
  `visitor_name` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '访问者姓名',
  `visitor_phone` varchar(11) COLLATE utf8mb4_bin NOT NULL COMMENT '访问者电话',
  `visitor_identity` varchar(18) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '访问者身份证号',
  `visitor_image` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '访问者图像',
  `status` char(1) COLLATE utf8mb4_bin NOT NULL COMMENT '黑名单状态',
  `cause` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '黑名单原因',
  `create_by` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  `company_id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '单位ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='黑名单管理';

SET FOREIGN_KEY_CHECKS = 1;
