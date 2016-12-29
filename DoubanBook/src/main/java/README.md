```sql

CREATE database doubanBook;

USE doubanBook;

CREATE TABLE book(
  isbn CHAR(14) NOT NULL PRIMARY KEY ,
  name VARCHAR(20) NOT NULL ,
  vote INTEGER(6) NOT NULL ,
  score DOUBLE(3,1) NOT NULL ,
  rank5 DOUBLE(3,1) NOT NULL ,
  rank4 DOUBLE(3,1) NOT NULL ,
  rank3 DOUBLE(3,1) NOT NULL ,
  rank2 DOUBLE(3,1) NOT NULL ,
  rank1 DOUBLE(3,1) NOT NULL 
);

```