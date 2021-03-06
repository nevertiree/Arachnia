##############首先设置mysql服务器的编码@配置文件################

#[mysqld]
#character-set-server = utf8
#collation-server = utf8_general_ci
#default-storage-engine = INNODB

##############确认设置mysql服务器的编码################

show variables like 'char%';

##############OK设置mysql服务器的编码OK################

create database if not exists nostudy CHARACTER SET utf8;

use Spider;

#学校信息 记录通过ImportSchoolInfoIntoDB导入
create table if not exists university(
  no          MEDIUMINT      PRIMARY KEY   AUTO_INCREMENT,
  name        VARCHAR(64)    NOT NULL      UNIQUE ,
  province    VARCHAR(32)    NOT NULL,
  city        VARCHAR(32)    NOT NULL
)CHARACTER SET=utf8;

CREATE TABLE IF NOT EXISTS universityType(
  no         MEDIUMINT      PRIMARY KEY ,
  type       VARCHAR(8)     NOT NULL,     #普通本科 高职高专 独立学院 中外合作……
  level      VARCHAR(8)     NOT NULL,     #本科 专科
  property   VARCHAR(16)    NOT NULL,     #理工类 综合类 医科类……
  authority  VARCHAR(32)    NOT NULL,     #所属单位:北京市 工信部
  is985      BOOLEAN        NOT NULL,
  is211      BOOLEAN        NOT NULL,
  nature     VARCHAR(8)     NOT NULL,     #国立 民办
  FOREIGN KEY (no) REFERENCES university(no) ON DELETE CASCADE ON UPDATE CASCADE
)CHARACTER SET =utf8;

CREATE TABLE IF NOT EXISTS universityIndex (
  no           MEDIUMINT      PRIMARY KEY ,
  name         VARCHAR(64)    NOT NULL      UNIQUE ,
  spell        VARCHAR(64)    NOT NULL  ,
  abbr         VARCHAR(32)    NOT NULL  ,
  FOREIGN KEY (no) references university(no) ON DELETE CASCADE ON UPDATE CASCADE
  #foreign key (name) references university(name) ON DELETE CASCADE ON UPDATE CASCADE
)CHARACTER SET=utf8;

#学校详细信息表
CREATE TABLE IF NOT EXISTS universityDetail(
  no       MEDIUMINT    PRIMARY KEY ,
  name     VARCHAR(64)  NOT NULL  UNIQUE ,
  tel      VARCHAR(64)  NOT NULL,         #招生办公室电话
  address  VARCHAR(128) NOT NULL,         #学校地址
  email    VARCHAR(64)  NOT NULL,         #email
  website  VARCHAR(64)  NOT NULL,         #官方网站
  FOREIGN KEY (no) REFERENCES university(no) ON DELETE CASCADE ON UPDATE CASCADE
)CHARACTER SET =utf8;

#专业信息，记录通过ImportSchoolInfoIntoDB导入
create table if not exists major (
	id_major       VARCHAR(8)     PRIMARY KEY ,   #以code为准
	name_major     VARCHAR(64)    NOT NULL ,
	level_major    VARCHAR(8)     NOT NULL ,
  type_major     VARCHAR(16)    NOT NULL ,
  rank_major     INT            NOT NULL
	#unique unique_nameMajor_rankMajor (name, rank)
)CHARACTER SET=utf8;

create table if not exists majorIndex (
   no           varchar(8)     primary key,
   name         varchar(64)    not null,
   pinyin       varchar(64)    not null,
   abbrevation  varchar(32)    not null,
   rank         int,
   foreign key (no) references major(no) ON DELETE CASCADE ON UPDATE CASCADE
)CHARACTER SET=utf8;


CREATE TABLE IF NOT EXISTS universityAndMajor(
  schlNo      MEDIUMINT    NOT NULL ,
  majorNo     VARCHAR(10)  NOT NULL ,
  majorName   VARCHAR(64)  NOT NULL ,
  majorType   VARCHAR(16)  NOT NULL ,#理工 医科
  PRIMARY KEY (majorName,schlNo)
)CHARACTER SET =utf8;

#学期｜校历信息,
########univNO 可能需要修改，改为学校名字
CREATE TABLE if not exists terms(
	no     	int        PRIMARY KEY        AUTO_INCREMENT,
	univNO 	int,
	year 	int, month int, day int, 
	weekNum int, 
	CONSTRAINT unique_termInOneUniv UNIQUE (univNO, year, month, day),
	FOREIGN KEY (univNO) references University(no) ON DELETE CASCADE ON UPDATE CASCADE
)CHARACTER SET=utf8;

#学生所在学校、专业以及入学年份信息
CREATE TABLE universityMajorInfo (
  no INTEGER PRIMARY KEY AUTO_INCREMENT,
  uuid varchar(48)   UNIQUE,
  univNo   int,
--   univName varchar(64),
  majorNo   int,
--   majName varchar(64),
  entYear int
)CHARACTER SET=utf8;

#个人设置的信息，用于聊天、博客
CREATE TABLE userInfo (
   no int PRIMARY  KEY   AUTO_INCREMENT, 
   uuid varchar(40)   UNIQUE,
   name varchar(32),
   isFemale tinyint(1),
   province varchar(32),
   city varchar(32),
   area varchar(32),
   signature varchar(48)
)CHARACTER SET=utf8;
#考虑删除此表：原来用于openfire，现丢弃

#个人设置的信息，用于聊天、博客
CREATE TABLE nouser (
  no int(11) NOT NULL AUTO_INCREMENT,
  uuid varchar(40) NOT NULL,
  pwd varchar(32) NOT NULL,
  email varchar(40) NOT NULL,
  token char(8)  NOT NULL,
  emailOk   boolean  DEFAULT  FALSE ,
  PRIMARY KEY (no),
  UNIQUE KEY uuid (uuid),
  UNIQUE KEY cellphone (email)
)CHARACTER SET=utf8;


#验证用户email使用
CREATE TABLE userEmails (
  no int(11) NOT NULL AUTO_INCREMENT  PRIMARY  KEY ,
  email varchar(40) NOT NULL  UNIQUE ,
  captcha   char(10)  NOT NULL,
  logDate   datetime DEFAULT now()
);

#重置用户密码使用
CREATE TABLE userPwds (
  no int(11) NOT NULL AUTO_INCREMENT  PRIMARY  KEY ,
  email varchar(40) NOT NULL  UNIQUE ,
  captcha   char(10)  NOT NULL,
  logDate   datetime DEFAULT now()
);
-- #用户行为记录表
-- CREATE TABLE userActions (
--   userNo int(11) NOT NULL ,
--   action varchar(40) NOT NULL,
--   year   int,
--   mo
-- );

