-- select

create table NewBook (
	bookid integer,
    bookname varchar(20),
    publisher varchar(20),
    price integer
);

-- GUI 생성 코드 가져온 코드
CREATE TABLE newbook2 (
  bookid INT NULL,		-- integer의 mysql 표현
  booknamel VARCHAR(20) NULL,
  publisher VARCHAR(20) NULL,
  price INT NULL
);
  
-- 일반적으로 개발자가 테이블을 직접 생성하는 경우는 거의 없다.
-- DBMS 관리 부서 또는 담당자(DBA)가 별도로 존재하고 이 조직 또는 담당자에게 의뢰해야 한다. 
-- DB가 시스템 전체 중 최후의 보루(이것이 망가지면 복구 x)

-- truncate table (rollback 없이 전체 삭제), delete * from ~~~ 테이블에 100 건의 데이터 1 건 1건 삭제 rollback에 대응 
-- 1억 건 table truncate 한다. (순식간) 1억 건 table에 대해 delete ...(한참 걸린다.)

CREATE TABLE `newbook2` (
 `bookid` int DEFAULT NULL,
 `bookname` varchar(20) DEFAULT NULL,
 `publisher` varchar(20) DEFAULT NULL,
 `price` int DEFAULT NULL
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 
 -- R&R 현업, 개발자(코드), DBA(DBMS 운영관리), 시스템 엔지니어(서버, 네트워크, ... 장비 OS)
 
 -- PK 설정 : 별도 마지막 라인
 create table NewBook (
	bookid integer, -- PK이므로 not null이 기본 설정
    bookname varchar(20),
    publisher varchar(20),
    price integer,
    primary key (bookid)
);

 -- PK 설정 : 별도 마지막 라인
 create table NewBook (
	bookid integer primary key, -- PK이므로 not null이 기본 설정
    bookname varchar(20),
    publisher varchar(20),
    price integer
);

-- PK 설정 : 복합키
 create table NewBook (
	bookname varchar(20), 
    publisher varchar(20),
    price integer,
    primary key (bookname, publisher)
);

-- not null 컬럼에 insert, update할 때 null이 들어오면 오류 발생 
-- unique 동일테이블에 중복된 값을 가진 row X <= PK를 대체하는 용도 
--        <= 회원, 고객 테이블에 고객번호가 PK이지만, 이메일 컬럼을 unique로 설정하고 로그인시 이 컬럼을 사용
-- default : 값이 안들어 오면 (null) 기본값을 가지도록 미리 지정 

create table NewBook (
    bookname varchar(20) not null,
    publisher varchar(20) unique,
    price integer default 10000 check (price >= 1000),
    primary key (bookname, publisher)
);

INSERT INTO newbook (bookname, publisher) VALUES ('111', '222');  -- default 10000 사용 
INSERT INTO newbook (bookname, publisher, price) VALUES ('111', '333', 20000);  -- 값이 존재해서 20000 사용 
INSERT INTO newbook (bookname, publisher, price) VALUES ('111', '444', 200);  -- check의 1000보다 작아서 오류 (Check constraint...)

-- auto increment
-- test table 생성(GUI)
CREATE TABLE test_table (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NULL,
  PRIMARY KEY (id));
  
insert into test_table (name) values ('aaa');
insert into test_table (name) values ('bbb');
insert into test_table (name) values ('ccc');
insert into test_table (name) values ('ddd');
insert into test_table (name) values ('eee');
  
-- ---------- Foreign Key -------------
create table NewCustomer(
	custid Integer Primary key,
    name varchar(40),
    address varchar(40),
    phone varchar(30)
);

create table NewOrders (
	orderid Integer, 
    custid Integer not null,
    bookid Integer not null,
    saleprice integer,
    orderdate date,
    primary key(orderid),
    foreign key(custid) references NewCustomer(custid)
);

-- 관계속에서 데이터의 무결성을 지키기 위해 Foreign Key를 이용 
INSERT INTO newcustomer (custid, name, address, phone) VALUES (1, '홍길동', '주소1', '010-1111-1111');
INSERT INTO newcustomer (custid, name, address, phone) VALUES (2, '이길동', '주소2', '010-2222-2222');

INSERT INTO neworders (orderid, custid, bookid, saleprice, orderdate) VALUES (1, 1, 1, 1000, '2024-01-01');
INSERT INTO neworders (orderid, custid, bookid, saleprice, orderdate) VALUES (2, 1, 2, 2000, '2024-02-02');
INSERT INTO neworders (orderid, custid, bookid, saleprice, orderdate) VALUES (3, 2, 3, 3000, '2024-03-03');

-- 자식쪽
-- 없는 custid를 사용해서 neworders에 insert할 때 
INSERT INTO neworders (orderid, custid, bookid, saleprice, orderdate) VALUES (4, 10, 3, 3000, '2024-03-03');
-- Error Code: 1452. Cannot add or update a child row

-- 있는  custid 를 없는 custid 로 update 하려 할 때
update neworders set custid = 10 where orderid = 3;
-- Error Code: 1452. Cannot add or update a child row

-- 부모쪽
-- FK 사용되고 있는 부모를 삭제하려고 할 때
delete from newcustomer where custid = 1;
-- Error Code: 1451. Cannot delete or update a parent row

-- drop하고 새로 NewOrders 테이블 생성
drop table NewOrders;
create table NewOrders (
	orderid Integer, 
    custid Integer not null,
    bookid Integer not null,
    saleprice integer,
    orderdate date,
    primary key(orderid),
    foreign key(custid) references NewCustomer(custid) on delete cascade -- 부모가 row가 삭제되면 함께 삭제 
);

INSERT INTO neworders (orderid, custid, bookid, saleprice, orderdate) VALUES (1, 1, 1, 1000, '2024-01-01');
INSERT INTO neworders (orderid, custid, bookid, saleprice, orderdate) VALUES (2, 1, 2, 2000, '2024-02-02');
INSERT INTO neworders (orderid, custid, bookid, saleprice, orderdate) VALUES (3, 2, 3, 3000, '2024-03-03');

-- FK 사용되고 있는 부모를 삭제하려고 할 때 
delete from newcustomer where custid = 1;

-- on delete set null
-- FK에 위배되면 해당 외래키 칼럼은 null로 바꾼다.
-- drop하고 새로 NewOrders 테이블 생성
drop table NewOrders;
create table NewOrders (
	orderid Integer, 
    custid Integer,
    bookid Integer not null,
    saleprice integer,
    orderdate date,
    primary key(orderid),
    foreign key(custid) references NewCustomer(custid) on delete set null -- 부모가 row가 삭제되면 함께 삭제 
);

INSERT INTO newcustomer (custid, name, address, phone) VALUES (1, '홍길동', '주소1', '010-1111-1111'); -- 삭제된 홍길동 추가

INSERT INTO neworders (orderid, custid, bookid, saleprice, orderdate) VALUES (1, 1, 1, 1000, '2024-01-01');
INSERT INTO neworders (orderid, custid, bookid, saleprice, orderdate) VALUES (2, 1, 2, 2000, '2024-02-02');
INSERT INTO neworders (orderid, custid, bookid, saleprice, orderdate) VALUES (3, 2, 3, 3000, '2024-03-03');

-- FK 사용되고 있는 부모를 삭제하려고 할 때 
delete from newcustomer where custid = 1;
-- neworders의 데이터가 삭제되지 않고 custid 컬럼의 값이 null로 변경된다.
