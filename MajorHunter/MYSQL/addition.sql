CREATE DATABASE nostudy;


#学校信息 记录通过ImportSchoolInfoIntoDB导入
create table if not exists university(
  no                    MEDIUMINT      PRIMARY KEY   AUTO_INCREMENT,
  name                  VARCHAR(64)    NOT NULL      UNIQUE ,
  province              VARCHAR(32)    NOT NULL,
  city                  VARCHAR(32)    NOT NULL
)CHARACTER SET=utf8;

CREATE TABLE IF NOT EXISTS universityType(
  no         MEDIUMINT      PRIMARY KEY ,
  type       VARCHAR(8)     NOT NULL,#普通本科 高职高专 独立学院 中外合作……
  level      VARCHAR(8)     NOT NULL,#本科 专科
  property   VARCHAR(16)    NOT NULL,#理工类 综合类 医科类……
  authority  VARCHAR(32)    NOT NULL,#所属单位:北京市 工信部
  is985      BOOLEAN        NOT NULL,
  is211      BOOLEAN        NOT NULL,
  nature     VARCHAR(8)     NOT NULL,#国立 民办
  FOREIGN KEY (no) REFERENCES university(no) ON DELETE CASCADE ON UPDATE CASCADE
)CHARACTER SET =utf8;

create table if not exists universityIndex (
  no           int            primary key,
  name         varchar(64)    not null     unique,
  spell        varchar(64)    not null,
  abbr         varchar(32)    not null,
  foreign key (no) references university(no) ON DELETE CASCADE ON UPDATE CASCADE
  #foreign key (name) references university(name) ON DELETE CASCADE ON UPDATE CASCADE
)CHARACTER SET=utf8;

create table if not exists major (
  no      MEDIUMINT      PRIMARY KEY , #教育部的序号以code为准
  name    VARCHAR(64)    NOT NULL ,
  level   VARCHAR(8)     NOT NULL ,    #本科 专科
  type    VARCHAR(16)    NOT NULL ,    #理工、、
  rank    INT            NOT NULL ,    #一级学科、、二级、、三
  unique unique_MajorName_MajorType (name, type)
)CHARACTER SET=utf8;

create table if not exists majorIndex (
  no        varchar(8)     primary key,
  name      varchar(64)    not null,
  spell     varchar(64)    not null,
  abbr      varchar(32)    not null,
  #rank      int            ,
  foreign key (no) references major(no) ON DELETE CASCADE ON UPDATE CASCADE
)CHARACTER SET=utf8;

CREATE TABLE IF NOT EXISTS universityAndMajor(
  schlNo            VARCHAR(16)    NOT NULL ,
# name_university   VARCHAR(64)    NOT NULL ,
  majorNo           MEDIUMINT NOT NULL ,
  majorName         VARCHAR(64)    NOT NULL ,
  type_major        VARCHAR(16)    NOT NULL ,#理工 医科
# father_major      VARCHAR(32)    NOT NULL ,
  PRIMARY KEY (majorName,schlNo)
)CHARACTER SET =utf8,
  MAX_ROWS = 1000000;
