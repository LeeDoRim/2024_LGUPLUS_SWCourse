use hr;

-- ctrl + enter : 현재 커서가 있는 한 줄만 실행

desc book;

-- select * 	-- 모든 컬럼
select name, address -- 구분한 컬럼명
  from customer 	-- 어떤 테이블
 where name = '김연아';	-- 어떤 조건

-- select 문법
/*
select [*, distinct, 컬럼명, 컬럼명]
from 
[where and]
[group by]
[having]
[order by]
*/

-- 질의 3-1
select bookname, price
  from book;

-- 질의 3-2
-- select bookid, bookname, publisher, price
select * 
  from book;

-- 질의 3-3
-- select publisher
select distinct publisher -- 중복 제거
  from book;
  
-- where bookname like '축구의 역사' <= where bookname = '축구의 역사' [%] 사용
-- null <- 해당 컬럼에 값이 없는 != ''
-- check null <= is null or is not null 사용 ( = null X)
-- 조건이 1개이면 where만 사용, 조건이 2개 이상이면 and, or 사용 가능
-- 부정 not
  
-- 질의 3-4
select * 
  from book
where price < 20000;

-- 질의 3-5
select *
  from book
-- where price >= 10000 and price <= 20000;
where price between 10000 and 20000; -- 10000, 20000 포함

-- 질의 3-6
select *
  from book
-- where publisher = '굿스포츠' or publisher = '대한미디어';
where publisher in ('굿스포츠', '대한미디어'); -- subquery 사용 가능

-- 출판사가 '굿스포츠', '대한미디어'가 아닌 도서 조회 (부정)
select *
  from book
-- where publisher != '굿스포츠' and publisher != '대한미디어'; -- or 사용하면 모든 값가 조회된다.
where publisher not in ('굿스포츠', '대한미디어'); -- subquery 사용 가능

-- 질의 3-7 3-8
select *
  from book 
where bookname like '축구%';

select *
  from book 
where bookname like '%이야기';

select *
  from book 
where bookname like '%의%';

select * 
  from customer
where name like '%연%';

-- 질의 3-9
select * 
  from book
where bookname like '_구%';
-- where bookname like '__의%';

-- 질의 3-10
select * 
  from book
where bookname like '%축구%' and price >= 20000;

-- 질의 3-11
select *
  from book
-- where publisher = '굿스포츠' or publisher = '대한미디어';
where publisher in ('굿스포츠', '대한미디어');

-- ORDER BY
-- order by <= select 가 확정된 후 처리된다.

-- 질의 3-12
select *
  from book
order by bookname; -- defalut : asc
-- order by bookname desc;
-- order by price desc;

-- 질의 3-13, 3-14
select *
  from book
-- order by price, bookname;
-- order by price desc, bookname desc;
order by price desc, publisher;

select * 
  from book
where price >= 10000
order by price desc, bookname desc;

select *
from orders;

-- customer 테이블
-- customer를 이름의 내림차순으로 출력하세요. 
select * 
  from customer
order by name desc;

-- '야구'와 '배구'가 적혀있는 'bookname'을 높은 가격순으로 정렬
select * 
from book
where bookname like '%야구%' or bookname like '%배구%'
order by price;

-- customer를 주소의 내림차순으로 검색하세요
select * 
  from customer
order by address desc;

-- 전화번호에 7000이 들어간 고객이 주문한 도서 주문 내역을 높은 가격순으로 검색하고, 
-- 가격이 같으면 주문 일자순으로 검색하시오.
select o.orderid, o.custid, o.bookid, o.saleprice, o.orderdate
  from customer c join orders o on c.custid = o.custid 
		-- join book b on b.bookid = o.bookid
where c.phone like '%7000%' 
order by saleprice desc, orderdate;

-- 집계 함수
-- 5 가지 기본 함수 sum(), avg(), count(), max(), min()
-- 조건의 전체 또는 group by로 묶어서 처리
-- 질의 3-15
select sum(saleprice) as 총매출
  from orders;
  
-- alias, as
select orderid as orderId, custid 'cust Id', bookid bookId, 
	   saleprice salePrice, orderdate orderDate
  from orders;
  
-- 질의 3-16
select sum(saleprice) as 총매출
  from orders
where custid = 2;

-- 질의 3-17
select sum(saleprice) as total,
		avg(saleprice) as average,
        min(saleprice) as min,
        max(saleprice) as max
  from orders;
  
-- 질의 3-18
select count(*) as totalCnt
  from orders;

select count(*) as yunaCnt
  from orders
where custid = 2;

-- GROUP BY
-- 질의 3-19
-- group by : 전체 row를 쪼갠다. 어떻게? group by 뒤에 오는 조건으로

-- select count(*) -- 10 건을 custid로 쪼개서 각각 count(*)
-- select custid, bookid, count(*) -- group by 절에 사용하지 않은 컬럼은 select 사용 불가 (bookid)
select custid, count(*) as 도서수량, sum(saleprice) as 총액
  from orders
group by custid;

-- 연습
select publisher as 출판사, count(*) as 도서수
  from book
group by publisher;

-- 질의 3-20
select custid, count(*)	 -- group by 절의 select 항목을 추가로 조건 처리
  from orders
where custid in (1, 2)
group by custid;
-- having custid in (1, 2); 
-- 이미 알고 있는 것은 group by 연산까지 처리 후 필터링 하지 말고 where절에서 미리 처리한다.

select custid, count(*)	 -- group by 절의 select 항목을 추가로 조건 처리
  from orders
group by custid
having count(*) > 2;
