-- 24.07.10

-- 질의 3-22 3-23
-- 이름 내림차순으로 정렬
select *
  from customer, orders
where customer.custid = orders.custid
order by customer.name desc;

-- 이름으로 오름차순 정렬, 동일한 경우 saleprice로 내림차순 정렬
select *
  from customer, orders
where customer.custid = orders.custid
order by customer.name, orders.saleprice desc;


-- table alias
select *
  from customer c, orders o
where c.custid = o.custid
order by c.name, o.saleprice desc;

-- 원하는 컬럼만 가져오는 경우
select c.name, o.saleprice
  from customer c, orders o
where c.custid = o.custid
order by c.name, o.saleprice desc;

-- table alias 생랷한 컬럼에 대해 유추하여 출력한다.
select c.name, saleprice
  from customer c, orders o
where c.custid = o.custid
order by c.name, o.saleprice desc;

-- ambiguous
select c.name, custid -- 오류 발생
  from customer c, orders o
where c.custid = o.custid
order by c.name, o.saleprice desc;

-- 질의 3-24
-- 동명이인이 있을 경우 고객이 중복 처리될 수 있다.
select c.name, sum(o.saleprice)
  from customer c, orders o
where c.custid = o.custid
group by c.name
order by c.name;

-- key 컬럼에 대한 group by 절은 가장 세분화된 group by가 되므로 select에 다른 컬럼이 와도 문제 없다.
select c.name, sum(o.saleprice) 	
  from customer c, orders o
where c.custid = o.custid
group by c.custid  	-- key :  custid는 키값이므로 그룹화하는데 방해되지 않는다.
order by c.name;

select c.name, sum(o.saleprice)	-- 오류 발생 : group by 절의 phone과 name이 맞지 않는다. phone이 와야 한다.
  from customer c, orders o
where c.custid = o.custid
group by c.phone	-- non key : 중복된 값이 들어갈 수 있는 등 그룹화가 어려울 수 있다.
order by c.phone;

-- 질의 3-25
-- 아래 두 쿼리는 결과가 같고 수행계획도 같다. 단, mysql 기준
-- DBMS가 달라지면 다른 결과가 나올 수 있다.
select c.name, b.bookname
  from customer c, book b, orders o
where c.custid = o.custid and b.bookid = o.bookid;

select c.name, b.bookname
  from orders o, customer c, book b
where o.custid = c.custid  and o.bookid = b.bookid;

-- ANSI SQL JOIN
select c.name, o.saleprice
  from customer c, orders o
where c.custid = o.custid;

select c.name, o.saleprice
  from customer c inner join orders o 
where c.custid = o.custid;

select c.name, o.saleprice
  from customer c inner join orders o on c.custid = o.custid;

select c.name, b.bookname
  from orders o inner join customer c on o.custid = c.custid 
		join book b on o.bookid = b.bookid;
        
-- self join
-- hr database / employees table 기준
-- first_name이 'Alexander'이고 last_name이 'Hunold'인 사람이 관리하는 사원들 목록
select staff.*
  from employees staff, employees manager
where manager.first_name = 'Alexander' and manager.last_name = 'Hunold'
	and staff.manager_id = manager.employee_id;
    
-- 질의 3-26
select c.name, b.bookname
  from customer c, book b, orders o
where c.custid = o.custid
	and b.bookid = o.bookid and b.price = 20000;

-- outer join
-- inner join 관계가 없는 데이터도 나오도록 쿼리를 수행한다.

-- 주문하지 않은 박세리도 나온다.
select * 
  from customer c, orders o;

-- 아래의 쿼리는 주문한 사람만 나온다.
select *
  from customer c, orders o
where c.custid = o.custid;

-- left outer join (왼쪽 테이블 기준으로 관계가 없어도 출력)
select c.name, o.saleprice
  from customer c left outer join orders o on c.custid = o.custid;

-- 휴가 안간 사원 목록
-- 휴가 테이블에 어떤 사원이 언제 신청했고, 얼마 기간의 휴가를 사용, 사용중 ... 
-- 휴가 가지 않은 사원 포함 전체 목록을 조회하려면 사원 테이블 left outer join 휴가 테이블 ...

-- 즐겨찾기 장소 관계에서 사용자가 즐겨찾기 등록한 장소를 그렇지 않은 장소와 함께 조회해서 별도로 표시할 경우
-- 장소 기준 left outer join 즐겨찾기 형태로 처리해야 원래 보여주려던 장소 목록을 출력할 수 있다.
-- 가령 장소가 100만개, 즐겨찾기 1000개
/*
select ...
  from 장소 left outer join 고객alter
where ...

select ... (화면에 보여줄 10개의 row만 먼저 추출)
  from 장소
where ...

select 즐겨찾기
  from 즐겨찾기
where 고객 = 로그인한 고객

각각 별도로 추출한 후 left outer join
*/

-- ---------------------------------------------
-- subquery : query의 일부가 별도의 완성적인 query

select max(price) -- 가장 비싼 도서의 가격 (35000)
  from book;

select bookname
  from book
where price = 35000;

select bookname
  from book
where price = (select max(price) from book);

-- subquery 부분만 고려
select max(price) from book; -- 단일행 단일열
select bookid, bookname from book; -- 다중행 다중열
select bookid from book; -- 다중행 단일열 
select bookid, bookname from book where bookid = 3; -- 단일행 다중열

-- Subquery returns more than 1 row
-- 해결 방안 : subquery가 복수개의 row를 return할 경우, 대응되는 왼쪽의 컬럼에 in을 사용한다.
-- 			단, 항상 1개의 row만 return될 경우에만 =를 사용한다. 

-- Operand should contain 1 column(s)
-- Operand should contain ~~~
-- 해결 방안 : subquery의 return column과 대응되는 왼쪽의 컬럼의 수가 맞지 않는 경우이므로, 갯수를 동일하게 맞춰준다. 
select bookname
  from book
where (bookid, bookname) in (select bookid, bookname from book);

-- 질의 3-29
-- 도서를 주문한 고객의 이름
select customer.name
  from customer
where custid in (select custid from orders);

-- 위 subquery를 join 형태로 변경
select customer.name -- 10명의 중복 포함 고객의 이름 
  from customer, orders
where customer.custid = orders.custid;

select distinct customer.name -- 10명의 중복 포함 고객의 이름 
  from customer, orders
where customer.custid = orders.custid;

select customer.name
  from customer
where custid in (select distinct custid from orders); -- 만약 subquery가 500 고객이 20000번 주문한 경우 <= subquery 자체에서 distinct를 사용
-- 중복이 가능한 subquery라면 distinct를 사용하는 것이 좋다. 

-- subquery는 메모리에 적재한 후 처리(io disk sqaping) 
-- subquery는 메모리에 적재되어 이후 처리가 되므로 매우 빠르게 처리된다. 동시에 메모리 자원을 낭비하게 된다.

-- 질의 3-30
-- 3 개의 테이블을 모두 사용
-- subquery
select name from customer where custid in (
	select custid from orders where bookid in (
		select bookid from book where publisher = '대한미디어'
	)
);

-- join 으로 1 : orders, book join
select name from customer where custid in (
    select custid
      from orders o, book b
	where o.bookid = b.bookid and b.publisher = '대한미디어'
);

-- join 으로 2 : customer, orders, book 3 테이블 모두 join
select c.name
  from customer c, orders o, book b 
where c.custid = o.custid and o.bookid = b.bookid and b.publisher = '대한미디어';

-- 3-31
-- 출판사별 평균 가격 테이블이 있으면 그걸 사용, 만약 없다.
select b1.*
  from book b1
where b1.price > (select avg(b2.price) from  book b2 where b2.publisher = b1.publisher);

-- 아래 join에 subquery가 사용  
select b1.*
  from book b1, (
				select publisher, avg(price) avg_price
				from book
				group by publisher
				) avg_book	-- 인라인 뷰
where b1.publisher = avg_book.publisher and b1.price > avg_book.avg_price;

create table avg_book 
				select publisher, avg(price) avg_price
				from book
				group by publisher;

select b1.*
  from book b1, avg_book	-- nomal table
where b1.publisher = avg_book.publisher and b1.price > avg_book.avg_price;

-- 질의 3-32
select name 
  from customer
where address like '대한민국%'

union 	-- 중복 제거한 후 합친다.

select name
  from customer
where custid in (select custid from orders);

select name 
  from customer
where address like '대한민국%'

union all 	-- 중복 제거 X

select name
  from customer
where custid in (select custid from orders);

-- union은 일반적으로 테이블 또는 쿼리의 결과가 비슷한데, 두 결과를 합쳐야 할 때 사용한다.
