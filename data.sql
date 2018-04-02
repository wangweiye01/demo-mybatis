DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastpasswordresetdate` datetime NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `created_at` TIMESTAMP DEFAULT current_timestamp,
  `updated_at` TIMESTAMP DEFAULT current_timestamp ON UPDATE current_timestamp NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `created_at` TIMESTAMP DEFAULT current_timestamp,
  `updated_at` TIMESTAMP DEFAULT current_timestamp ON UPDATE current_timestamp NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='权限表';

DROP TABLE IF EXISTS `user_authority`;
CREATE TABLE `user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_id` bigint(20) NOT NULL,
  `created_at` TIMESTAMP DEFAULT current_timestamp,
  `updated_at` TIMESTAMP DEFAULT current_timestamp ON UPDATE current_timestamp NULL
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户权限关联表';

INSERT INTO `authority`(id,name) VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');

INSERT INTO user(id,username,password,firstname,lastname,email,enabled,lastpasswordresetdate) VALUES (1,'admin','$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi','admin','admin','admin1@admin.com',1,'2017-01-01'),(3,'disabled','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','user','user','disabled@user.com',0,'2017-01-01'),(2,'user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','user','user','enabled@user.com',1,'2017-01-01');

INSERT INTO `user_authority`(user_id, authority_id) VALUES (1,1),(1,2),(2,1),(3,1);