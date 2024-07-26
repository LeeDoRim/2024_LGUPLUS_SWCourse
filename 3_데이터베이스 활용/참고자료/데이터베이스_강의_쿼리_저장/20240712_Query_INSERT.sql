-- INSERT 
-- 질의 3-44
insert into book (bookid, bookname, publisher, price) values (11, '스포츠 의학', '한솔의학서적', 90000);

-- 전체 컬럼에 대한 입력(values)이면 컬럼명 생략
insert into book values (11, '스포츠 의학', '한솔의학서적', 90000);
-- Error Code: 1062. Duplicate entry '11' for key 'book.PRIMARY'

insert into book values (12, '스포츠 의학', '한솔의학서적', 90000);  -- 모든 컬럼에 대한 value, 순서 중요.

insert into book (bookid, bookname, price, publisher) values (13, '스포츠 의학', 90000, '한솔의학서적');

-- 질의 3-45
insert into book (bookid, bookname, publisher) values (14, '스포츠 의학', '한솔의학서적');

-- 질의 3-46 다른 테이블의 데이터를 select해서 insert
insert into book (bookid, bookname, publisher, price) 
	select bookid, bookname, publisher, price from imported_book;

-- 아래 2개도 모두 가능 ( 이유 : 테이블의 구조가 동일하기 때문에 가능하다.)
insert into book (bookid, bookname, publisher, price) 
	select * from imported_book;
    
insert into book 
	select * from imported_book;
    
-- imported_book에 컬럼 추가
ALTER TABLE imported_book
ADD COLUMN isbn VARCHAR(45) NULL AFTER publisher;

insert into book select * from imported_book;  -- column doesn't match 오류

insert into book (bookid, bookname, publisher, price) 	-- column doesn't match 오류
	select * from imported_book;
    
insert into book (bookid, bookname, publisher, price)  -- 문제 없다. 테이블 구조가 달라도 select 항목과 insert 항목이 맞으면 된다. 
	select bookid, bookname, publisher, price from imported_book;
    
-- create as select : 테이블 복사 또는 임시 테이블을 만드는 경우
create table book_temp as select * from book;

select * from book_temp;

desc book;
desc book_temp; -- pk 없다. create select는 단순 데이터 복사 

drop table book_temp;
create table book_temp select bookname, price from book limit 10;  -- 컬럼 선택, row 선택 가능

select * from book_temp;