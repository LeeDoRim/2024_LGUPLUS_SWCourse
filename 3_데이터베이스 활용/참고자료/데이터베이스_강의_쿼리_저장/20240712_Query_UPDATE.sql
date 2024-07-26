-- UPDATE 
/*
update 테이블명
	set 컬럼1 = value1 (, 컬럼2 = value, ...)
where 대상 row 선택 
*/

select * from customer;

-- 질의 3-47
update customer
	set address = '대한민국 부산'
where custid = 5;

update customer
	set address = '대한민국 대전'
where name = '박세리';

select @@sql_safe_updates;  -- 0: off, 1: on -- @@가 붙은 것은 mysql의 환경변수 
set sql_safe_updates = 1;  -- update, delete 작업 시 반드시 key 컬럼에 조건을 줘야 한다. 

-- 질의 3-48
-- update with subquery
select * from book;
select * from imported_book;

update book
	set publisher = (select publisher from imported_book where bookid=21) -- single row, single col이므로 가능하다. 
where bookid = 14;

update book
	set publisher = (select publisher from imported_book where bookid=21) -- single row, single col이므로 가능하다. 
where bookid = 14;
-- multi row, single col -- subquery returns more than 1 rows 오류alter

update book
	set publisher = (select publisher from imported_book where bookid=40) -- single row, single col이므로 가능하다. 
where bookid = 14;  -- 없는 row, 오류는 발생하지 않지만 null로 변경

select publisher from imported_book where bookid=21;

update book
	set publisher='임시 출판사'
where bookid in (select bookid from imported_book);

