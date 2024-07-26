-- ALTER 

drop table newbook;
create table NewBook (
	bookid integer,
    bookname varchar(20),
    publisher varchar(20),
    price integer
);

alter table newbook add isbn varchar(130);
alter table newbook modify isbn integer;
alter table newbook drop column isbn; -- column 생략 가능 (mysql)
alter table newbook modify bookname varchar(20) not null;
desc newbook; -- bookname varchar(20) not null 확인
alter table newbook add primary key(bookid);

-- DROP
drop table NewBook;
drop table newcustomer;
-- Error Code: 3730. Cannot drop table 'newcustomer' referenced by a foreign key constraint 

-- delete 후 neworders에는 newcustomer를 참조하는 데이터가 없다. 
DELETE FROM neworders WHERE orderid = 3;

drop table newcustomer;
-- 참조하는 부모 데이터가 없어도 drop이 불가능하다. <- 데이터를 보고 그때그때 판단하는 것이 아니고, drop 명령어 자체에 연결되어 있는 오류 
-- 데이터와 상관없이 FK로 사용되고 있는 테이블은 삭제할 수 없다.

-- 결론적으로 drop할 때는 자식 테이블을 먼저 삭제하고 부모 테이블을 나중에 삭제해야 한다.

-- neworders drop
-- newcustomer truncate 성공

INSERT INTO newcustomer (custid, name, address, phone) VALUES (1, '홍길동', '주소1', '010-1111-1111');
INSERT INTO newcustomer (custid, name, address, phone) VALUES (2, '이길동', '주소2', '010-2222-2222');

create table NewOrders (
	orderid Integer, 
    custid Integer not null,
    bookid Integer not null,
    saleprice integer,
    orderdate date,
    primary key(orderid),
    foreign key(custid) references NewCustomer(custid)
);

INSERT INTO neworders (orderid, custid, bookid, saleprice, orderdate) VALUES (1, 1, 1, 1000, '2024-01-01');
INSERT INTO neworders (orderid, custid, bookid, saleprice, orderdate) VALUES (2, 1, 2, 2000, '2024-02-02');
INSERT INTO neworders (orderid, custid, bookid, saleprice, orderdate) VALUES (3, 2, 3, 3000, '2024-03-03');

-- neworders truncate 성공
-- newcustomer truncate 오류 발생

-- neworders에 테이블이 있고, 데이터가 없는 상태에서 newcustomer는 truncate가 안된다. 데이터 유무와 상관없이
-- neworders 테이블이 없으면 truncate된다. 
