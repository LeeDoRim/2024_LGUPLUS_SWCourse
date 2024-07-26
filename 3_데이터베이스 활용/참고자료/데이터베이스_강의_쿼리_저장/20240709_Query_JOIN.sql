-- JOIN vs SubQuery
-- 어느 방법이 더 빠르냐

-- 데이터 1 건만 남기고 삭제
-- JOIN
select * from customer; -- 1 건

select * from  customer, orders; -- 1 건 : customer 1 건 X orders 1 건

-- customer 2 insert, book 2 insert 후 
select * from  customer, orders, book; -- 4 건 : customer 2 건 X orders 1 건 X book 2 건

select * 
  from customer, orders, book
where orders.custid = customer.custid; -- 2 건 : customer 1 건 X orders 1 건 X book 2 건

select *
  from customer, orders, book 
 where orders.custid = customer.custid
   and orders.bookid = book.bookid; -- 1 건 : customer 1 건 X orders 1 건 X book 1 건 
 
select customer.custid, customer.name, orders.saleprice, book.bookname
  from customer, orders, book 
 where orders.custid = customer.custid
   and orders.bookid = book.bookid; -- 1 건 : customer 1 건 X orders 1 건 X book 1 건  
   
select customer.custid, customer.name, orders.saleprice, book.bookname
  from customer, orders, book 
 where customer.custid > 1
   and orders.custid = customer.custid
   and orders.bookid = book.bookid; -- 0 건 : customer 1 건 X orders 1 건 처리 중 orders 에는 custid 가 1 만 존재 
   
-- 데이터 원복

-- 질의 3-21
select * 
  from customer, orders; -- 5 x 10 = 50 건 수
  
select * 
  from customer, orders
where customer.custid = orders.custid; -- 1 x 10 = 10 건