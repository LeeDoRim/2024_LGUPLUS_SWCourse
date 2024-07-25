-- 2 가지의 function
-- 제공되는 function, 사용자 정의 function
-- 제공되는 function(내장 함수)은 정말 DBMS마다 다 다르다 (mySQL과 Oracle이 완전 다르다)

-- 숫자 함수
-- 질의 4-1
select abs(-78), abs(78) from dual;
select abs(-78), abs(78);

--  질의 4-2
select round(4.875,1); -- 소수점 X 자리까지 반올림한다.

-- 질의 4-3
-- round(..., 음수)는 소수점 왼쪽(정수자리)를 의미한다.
-- 3번째 row custid = 3인 항목 체크 
select custid, round(sum(saleprice)/count(*), -2), avg(saleprice) from orders group by custid;

-- 질의 4-4
-- replace()는 일치하는 모든 문자열을 다 바꾼다. 
select bookid, bookname, replace(bookname, '야구', '농구') new_bookname, publisher, price from book;

-- 위 테스트를 위한 수정 
update book set bookname = '야구의 야구의 야구의 추억' where bookid = 7;
-- 원복 
update book set bookname = '야구의 추억' where bookid = 7;

-- 질의 4-5
-- length(), char_length()
select bookname '제목', char_length(bookname), length(bookname) from book;
-- char_length()는 char 수, length는 byte 수를 반환한다.
-- length 예시 : 축구의 역사 = 한글 5 x 3(byte) + space 1 = 16
-- utf-8 (3 byte로 유니코드 구현) utf-16 (4 byte로 유니코드 구현)
-- euc-kr (2 byte)
-- blob, clob

-- 질의 4-6
select * from customer;

-- group by 절에 alias를 사용할 때는 '성' 같은 문자열 alias 사용 안된다. 
select substr(name, 2, 1) last_name, count(*) '인원' from customer group by last_name;

-- 질의 4-7
-- date(날짜) vs datetime(날짜 + 시간)
select orderid, orderdate, adddate(orderdate, interval 20 day) from orders; -- 정확한 일별 계산 

-- 교재 227
select sysdate(), date_format(sysdate(), '%Y-%m-%d : %H:%i:%s');

-- 질의 4-8
select orderid, date_format(orderdate, '%Y/%m/%d') '주문일자', custid, bookid 
from orders where orderdate = '20240707';  -- mysql은 가능하지만 다른 DBMS는 일반적으로 type을 일치시켜야 한다.

select orderid, date_format(orderdate, '%Y/%m/%d') '주문일자', custid, bookid 
from orders where orderdate = str_to_date('20240707', '%Y%m%d');  -- 오른쪽 타입을 왼쪽 타입에 맞춘다.

select orderid, date_format(orderdate, '%Y/%m/%d') '주문일자', custid, bookid 
from orders where date_format(orderdate, '%Y%m%d') = '20240707'; -- 왼쪽 타입을 오른쪽 타입에 맞춘다.

-- 동일한 결과가 나오는 위 2개의 쿼리 중 어느 것이 더 좋은까? 위의 쿼리가 좋다.
-- 1. 위 쿼리는 str -> date로 1번 바꾸고 이후의 모든 orderdate와 비교한다.
-- 		orderdate 컬럼에 생성된 index에 영향을 주지 않는다. (index를 탄다.)
-- 2. 아래의 쿼리는 모든 row의 orderdate value를 date -> str하고 비교한다. (손해가 크다.)
-- 		orderdate 컬럼에 생성된 index를 타지 않는다. index에 저장된 값과 달리 변형되기 때문이다.

-- 질의 4-9
select sysdate();
select date_format(sysdate(), '%Y %m %d %a %H %i %s');

-- insert 시점 현재 시각
insert into orders(orderid, custid, bookid, saleprice, orderdate) values(20, 3, -1, 13000, sysdate());

insert into orders(orderid, custid, bookid, saleprice, orderdate) values(21, 3, -1, 13000, now());

insert into orders(orderid, custid, bookid, saleprice, orderdate) values(22, 3, -1, 13000, curdate());

-- sysdate() vs now()
-- sysdate()는 항상 실행 시점 시간이고, now()는 전체 스테이트먼트가 종료될 때까지 같은 값이 들어간다.
-- 1건은 거의 차이가 없지만, 스테이트, 트랜잭션에서 처음부터 종료까지가 하나로 들어가야 할 때는 now()를 사용한다. 

-- datetime type 의 default value  : current_timestamp
-- orders table 의 orderdate 를 datetime 으로 변경
 insert into orders (orderid, custid, bookid, saleprice)
   values (23, 3, 1, 13000);