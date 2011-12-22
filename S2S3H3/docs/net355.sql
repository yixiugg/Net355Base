create table syscode(
sys_id	varchar(32) primary key,
sys_type	varchar(2),
sys_class	varchar(2),
sys_father	varchar(32),
sys_name	varchar(200),
sys_desc	varchar(500),
sys_sign	varchar(50),
state	varchar(2)
);

create table log(
log_id	varchar(32) primary key,
log_type	varchar(2),
log_op_type	varchar(2),
log_class	varchar(2),
log_op_id	varchar(32),
log_target	varchar(32),
log_desc	varchar(400),
add_time	datetime,
state	varchar(2)
);

create table admin(
admin_id	varchar(32) primary key,
admin_acc	varchar (20),
admin_pwd	varchar(32),
admin_type	varchar(2),
admin_name	varchar(20),
admin_email	varchar(100),
add_time	datetime,
state	varchar(2)
);


create table module(
mod_id	varchar(32) primary key,
mod_name	varchar(50),
mod_href	varchar(200),
mod_type	varchar(2),
sys_id	varchar(32),
add_time	datetime,
state	varchar(2),
CONSTRAINT `mod_fk_1` FOREIGN KEY (`sys_id`) REFERENCES `syscode` (`sys_id`)
);

create table privilege(
priv_id	varchar(32) primary key,
admin_id	varchar(32),
mod_id	varchar(32),
priv_level	varchar(2),
add_time	datetime,
state	varchar(2),
CONSTRAINT `priv_fk_1` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`),
CONSTRAINT `priv_fk_2` FOREIGN KEY (`mod_id`) REFERENCES `module` (`mod_id`)
);

create table ad(
ad_id	varchar(32) primary key,
sys_id	varchar(32),
ad_type	varchar(2),
ad_path	varchar(200),
ad_title	varchar(100),
belong_type	varchar(32),
belong_id	varchar(2),
ad_level	int,
ad_desc	varchar(500),
ad_href	varchar(200),
ad_op_id	varchar(32),
add_time	datetime,
state	varchar (2),
CONSTRAINT `ad_fk_1` FOREIGN KEY (`sys_id`) REFERENCES `syscode` (`sys_id`),
CONSTRAINT `ad_fk_2` FOREIGN KEY (`ad_op_id`) REFERENCES `admin` (`admin_id`)
);

create table notice(
notice_id	varchar(32) primary key,
notice_type	varchar(2),
notice_desc	longtext,
notice_href	varchar(200),
notice_op_id	varchar(32),
notice_end	datetime,
add_time	datetime,
state	varchar (2),
CONSTRAINT `notice_fk_1` FOREIGN KEY (`notice_op_id`) REFERENCES `admin` (`admin_id`)
);

create table user(
user_id	varchar(32) primary key,
user_acc	varchar(30),
user_pwd	varchar(32),
user_type	varchar(2),
sys_id	varchar(32),
user_pwd_que	varchar(100),
user_pwd_ans	varchar(100),
user_email	varchar(100),
user_name	varchar(50),
country	varchar(32),
province	varchar(32),
city	varchar(32),
dist	varchar(32),
user_address	varchar(200),
zipcode	varchar(6),
qq	varchar(15),
msn	varchar(50),
user_phone	varchar(20),
user_cell	varchar(12),
user_fax	varchar(20),
user_domain	varchar(50),
acc_balance	decimal(10,2),
acc_score		decimal(10,2),
acc_space	decimal(10,2),
acc_pwd	varchar(32),
edit_time	datetime,
edit_ip	varchar (20),
add_time	datetime,
state	varchar (2),
CONSTRAINT `user_fk_1` FOREIGN KEY (`sys_id`) REFERENCES `syscode` (`sys_id`)
);

create table recharge(
recharge_id	varchar(32) primary key,
user_id	varchar(32),
recharge_ip	varchar(20),
recharge_amount	decimal(10,2),
recharge_score	decimal(10,2),
recharge_type	varchar(2),
recharge_md5	varchar(200),
add_time	datetime,
state	varchar (2),
CONSTRAINT `rec_fk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);

create table expense(
expense_id	varchar(32) primary key,
user_id	varchar(32),
expense_amount	decimal(10,2),
expense_score	decimal(10,2),
expense_type	varchar(2),
expense_target	varchar(32),
add_time	datetime,
state	varchar (2),
CONSTRAINT `exp_fk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);

create table folder(
folder_id	varchar(32) primary key,
user_id	varchar(20),
folder_name	varchar(32),
folder_desc	varchar(500),
folder_type	varchar(2),
folder_amount	int,
folder_pwd	varchar(32),
edit_time	datetime,
edit_ip	varchar (20),
add_time	datetime,
state	varchar (2),
CONSTRAINT `folder_fk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);

create table file(
file_id	varchar(32) primary key,
folder_id	varchar(32),
user_id	varchar(32),
file_name	varchar(32),
file_type	varchar(2),
file_ip	varchar(20),
file_href	varchar(200),
file_size	decimal(10,2),
add_time	datetime,
state	varchar (2),
CONSTRAINT `file_fk_1` FOREIGN KEY (`folder_id`) REFERENCES `folder` (`folder_id`),
CONSTRAINT `file_fk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);

create table space(
space_id	varchar(32) primary key,
user_id	varchar(32),
space_size	decimal(10,2),
end_time	datetime,
add_time	datetime,
state	varchar (2),
CONSTRAINT `space_fk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);

create table brand(
brand_id	varchar(32) primary key,
user_id	varchar(32),
brand_name	varchar(50),
brand_desc	varchar(500),
edit_time	datetime,
edit_ip	varchar (20),
add_time	datetime,
state	varchar (2),
CONSTRAINT `brand_fk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);

create table product(
prod_id	varchar(32) primary key,
user_id	varchar(32),
prod_name	varchar(50),
prod_model	varchar(50),
prod_desc	text,
sys_id	varchar(32),
brand_id	varchar(32),
prod_price	decimal(10,2),
prod_unit	varchar(20),
prod_tag	varchar(100),
edit_time	datetime,
edit_ip	varchar (20),
add_time	datetime,
state	varchar (2),
CONSTRAINT `prod_fk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
CONSTRAINT `prod_fk_2` FOREIGN KEY (`sys_id`) REFERENCES `syscode` (`sys_id`),
CONSTRAINT `prod_fk_3` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`brand_id`)
);

create table attachment(
att_id	varchar(32) primary key,
belong_id	varchar(32),
file_id	varchar(32),
att_type	varchar(2),
add_time	datetime,
att_path	varchar(500),
state	varchar (2),
CONSTRAINT `att_fk_1` FOREIGN KEY (`file_id`) REFERENCES `file` (`file_id`)
);

create table viewlog(
vlog_id	varchar(32) primary key,
user_id	varchar(32),
vlog_num	int,
vlog_type	varchar(2),
vlog_target	varchar(32),
add_time	datetime,
state	varchar (2),
CONSTRAINT `vlog_fk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);

create table news(
news_id	varchar(32) primary key,
user_id	varchar(32),
news_type	varchar(2),
sys_id	varchar(32),
news_title	varchar(200),
news_content	longtext,
news_tag	varchar(100),
news_pub_time	datetime,
admin_id	varchar(32),
news_check_time	datetime,
add_time	datetime,
state	varchar (2),
CONSTRAINT `news_fk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
CONSTRAINT `news_fk_2` FOREIGN KEY (`sys_id`) REFERENCES `syscode` (`sys_id`),
CONSTRAINT `news_fk_3` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`)
);

create table message(
msg_id	varchar(32) primary key,
msg_title	varchar(200),
msg_content	longtext,
msg_type	varchar(2),
sender_id	varchar(32),
receiver_id	varchar(32),
msg_send_state	varchar(2),
msg_recv_state	varchar(2),
add_time	datetime,
state	varchar (2),
CONSTRAINT `msg_fk_1` FOREIGN KEY (`sender_id`) REFERENCES `user` (`user_id`),
CONSTRAINT `msg_fk_2` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`user_id`)
);

