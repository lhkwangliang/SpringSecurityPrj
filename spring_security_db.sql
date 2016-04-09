---------------------------------------------
------------------MySQL数据库------------------
---------------------------------------------
CREATE TABLE USERS
(
  USERNAME VARCHAR(64) NOT NULL COMMENT '用户名',
  PASSWORD VARCHAR(64) COMMENT '密码',
  ENABLED  NUMERIC COMMENT '0--不可用；1--可用'
);
ALTER TABLE USERS
  ADD CONSTRAINT PK_USERS PRIMARY KEY (USERNAME);


CREATE TABLE AUTHORITIES
(
  USERNAME  VARCHAR(64) NOT NULL,
  AUTHORITY VARCHAR(64) NOT NULL
);
ALTER TABLE AUTHORITIES
  ADD CONSTRAINT INDEX_USER_AUTH UNIQUE (USERNAME, AUTHORITY);
  
ALTER TABLE AUTHORITIES
  ADD CONSTRAINT USER_AUTHORITIES_FK FOREIGN KEY (USERNAME)
  REFERENCES USERS (USERNAME);


insert into users(username,password,enabled) values('admin','admin',1);
insert into users(username,password,enabled) values('liangw','liangw',1);
insert into authorities(username,authority) values('admin','ROLE_ADMIN');
insert into authorities(username,authority) values('admin','ROLE_USER');
insert into authorities(username,authority) values('liangw','ROLE_USER');
