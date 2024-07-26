-- DELETE 
/*
delete from 테이블 
where 조건 
*/

-- 질의 3-49

delete from book
where bookid = 11;

select * from book;

-- 질의 3-50
-- customer, orders FK 관계 확인
-- 데이터 없으면 추가 
INSERT INTO Customer VALUES (1, '박지성', '영국 맨체스타', '000-5000-0001');
INSERT INTO Customer VALUES (2, '김연아', '대한민국 서울', '000-6000-0001');
INSERT INTO Customer VALUES (3, '김연경', '대한민국 경기도', '000-7000-0001');
INSERT INTO Customer VALUES (4, '추신수', '미국 클리블랜드', '000-8000-0001');
INSERT INTO Customer VALUES (5, '박세리', '대한민국 대전',  NULL);

-- mysql workbench alter table 메뉴를 이용해서 FK 추가

delete from customer;  -- safe update error

set sql_safe_updates = 0;

delete from customer; -- FK 오류 

select * from customer;

-- delete whith subquery
delete from book
where bookid in (select bookid from imported_book);


