CREATE TABLE `t_account` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `name` varchar(20) NOT NULL DEFAULT '',
    `money` double(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT INTO `t_account` VALUES (1, '张三', 1000.00);
INSERT INTO `t_account` VALUES (2, '王五', 1000.00);

CREATE TABLE `t_teacher` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `nickName` varchar(20) DEFAULT NULL,
    `remark` varchar(200) DEFAULT NULL,
    `imageName` varchar(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41400 DEFAULT CHARSET=utf8;