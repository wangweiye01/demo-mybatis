INSERT INTO `authority` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');

INSERT INTO user(id,username,password,firstname,lastname,email,enabled,lastpasswordresetdate) VALUES (1,'admin','$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi','admin','admin','admin1@admin.com',1,'2017-01-01'),(3,'disabled','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','user','user','disabled@user.com',0,'2017-01-01'),(2,'user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','user','user','enabled@user.com',1,'2017-01-01');

INSERT INTO `user_authority` VALUES (1,1),(1,2),(2,1),(3,1);