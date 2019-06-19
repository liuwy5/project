CREATE TABLE bank (
 `id` int(4) NOT NULL AUTO_INCREMENT,
 money decimal(10,2) DEFAULT '0.00',
 create_time datetime DEFAULT now(),
 modify_time datetime DEFAULT now(),
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into bank (money) values (2.1), (3.2), (3.3);