CREATE TABLE `user` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(32) NOT NULL,
  `username` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE bank (
 `id` int(4) NOT NULL AUTO_INCREMENT,
 money decimal(10,2) DEFAULT '0.00',
 create_time datetime DEFAULT now(),
 modify_time datetime DEFAULT now(),
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE bank_b (
 `id` int(4) NOT NULL AUTO_INCREMENT,
 money decimal(10,2) DEFAULT '0.00',
 create_time datetime DEFAULT now(),
 modify_time datetime DEFAULT now(),
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;