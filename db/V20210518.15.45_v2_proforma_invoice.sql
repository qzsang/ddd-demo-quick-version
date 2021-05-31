


CREATE TABLE `t_proforma_invoice` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `invoice_no` varchar(50) NOT NULL COMMENT '发票编号，作废或者删除的发票改为由原编号+序号',
  `invoice_dt` datetime NOT NULL COMMENT '发票时间',
  `invoice_opr_dt` datetime NOT NULL COMMENT '发票创建时间',
  `invoice_price_total` decimal(12,2) NOT NULL COMMENT '发票合计金额',
  `invoice_status` tinyint(4) NOT NULL COMMENT '发票状态，0：待提交  1：待审核  2：已开票  -1：已作废',
  `invoice_result_url` varchar(500) NOT NULL DEFAULT '' COMMENT '结果url,目前是pdf文件地址',
  `accessory_list` varchar(1000) NOT NULL DEFAULT '' COMMENT '附件列表，’,‘作为分隔符',
  `currency` char(5) NOT NULL COMMENT '币种',
  `supplier` varchar(250) NOT NULL COMMENT '供应商',
  `trade_condition` char(5) NOT NULL COMMENT '贸易条件',
  `consumer_name` varchar(255) NOT NULL COMMENT '购方-机构',
  `consumer_addr` varchar(255) NOT NULL COMMENT '购方-地址',
  `consumer_mobile` varchar(50) DEFAULT NULL COMMENT '购方-电话',
  `consumer_fax` varchar(50) DEFAULT NULL COMMENT '购方-传真',
  `is_show_container_no` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否显示柜号 0：不显示；1：显示',
  `container_no_list` varchar(500) NOT NULL COMMENT '柜号列表，"/"为分割标识',
  `apply_uid` bigint(11) NOT NULL COMMENT '申请人-id',
  `apply_name` varchar(50) NOT NULL DEFAULT '' COMMENT '申请人-名字',
  `opr_uid` bigint(11) NOT NULL DEFAULT '0' COMMENT '审核人-id',
  `opr_name` varchar(50) NOT NULL DEFAULT '' COMMENT '审核人-名字',
  `opr_reason` varchar(350) DEFAULT NULL COMMENT '审核人-理由',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_invoiceno` (`invoice_no`) USING BTREE,
  KEY `idx_search` (`invoice_opr_dt`,`invoice_status`,`supplier`,`opr_name`,`container_no_list`,`invoice_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='形式发票';


CREATE TABLE `t_proforma_invoice_goods` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `invoice_id` bigint(11) NOT NULL COMMENT '发票id',
  `goods_name` varchar(50) NOT NULL COMMENT '商品名字',
  `category` varchar(50) NOT NULL DEFAULT '' COMMENT '品种',
  `wrapper` varchar(50) NOT NULL DEFAULT '' COMMENT '包装',
  `spec` varchar(50) NOT NULL DEFAULT '' COMMENT '规格',
  `quantity` decimal(12,2) NOT NULL COMMENT '数量',
  `unit_price` decimal(12,2) NOT NULL COMMENT '单价',
  `price_total` decimal(12,2) NOT NULL COMMENT '合计',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `uk_invoiceid_linenumber` (`invoice_id`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='形式发票商品';



CREATE TABLE `t_proforma_invoice_status_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `invoice_id` bigint(11) NOT NULL COMMENT '发票id',
  `invoice_status` tinyint(4) NOT NULL COMMENT '操作状态',
  `opr_uid` bigint(11) NOT NULL DEFAULT '0' COMMENT '操作-id',
  `opr_name` varchar(50) NOT NULL DEFAULT '' COMMENT '操作-名字',
  `opr_reason` varchar(350) NOT NULL DEFAULT '' COMMENT '操作-理由',
  `opr_dt` datetime NOT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_invoiceid` (`invoice_id`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='形式发票状态日志';


-- 购方机构信息
CREATE TABLE `t_proforma_invoice_company` (
	`id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`company_name` VARCHAR(255) NULL DEFAULT NULL COMMENT '名称',
	`company_addr` VARCHAR(255) NULL DEFAULT NULL COMMENT '地址',
	`company_phone` VARCHAR(50) NULL DEFAULT NULL COMMENT '电话',
	`company_fax` VARCHAR(50) NULL DEFAULT NULL COMMENT '传真',
	`is_delete` TINYINT(4) NULL DEFAULT '0' COMMENT '是否删除，0正常，1已删除',
	`opr_dt` DATETIME NULL DEFAULT NULL COMMENT '创建时间',
	`opr_uid` BIGINT(20) NULL DEFAULT NULL COMMENT '创建人id',
	`opr_name` VARCHAR(50) NULL DEFAULT NULL COMMENT '创建人姓名',
	`upt_dt` DATETIME NULL DEFAULT NULL COMMENT '最近一次更新时间',
	`upt_uid` BIGINT(20) NULL DEFAULT NULL COMMENT '最近一次更新人id',
	`upt_name` VARCHAR(50) NULL DEFAULT NULL COMMENT '最近一次更新人姓名',
	PRIMARY KEY (`id`),
	INDEX `company_name` (`company_name`(191))
)
COMMENT='形式发票购方公司信息'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;
