-- EXISTS
-- in vs exists, not in not exists 비교
-- 1 건
select customer_nm from customer 
where customer_id in (select customer_id from customer_order);

-- 2 건
-- customer 건 수만큼 exists 쪽 서브쿼리를 수행하고 결과 row 가 존재하면 선택 
select customer_nm from customer 
where exists (select customer_id from customer_order);

-- 수정 1 건
select customer_nm from customer c
where exists (select co.customer_id from customer_order co where c.customer_id = co.customer_id);

-- not in 문제 X
select customer_nm
  from customer
 where customer_id not in (select customer_id from customer_order);
 
-- not exist 문제 X 
select c.customer_nm
  from customer c
 where not exists (select co.customer_id from customer_order co where c.customer_id = co.customer_id);  
-- not in 문제 발생 0 건
-- not in () 은 집합 연산을 수행하는 데 null 비교에서 최종적으로 false 가 된다.
select customer_nm
  from customer
 where customer_id not in (select customer_id from blacklist); -- (2, null)
 
 -- 홍길동의 경우 select 1 != 2 && 1 != null 부분이 true 가 되어야 하는데
 select 1 != null; -- null --> 전체가 false 이므로 선택 X
 
 -- not in 수행시 subquery 에 is not null 추가
select customer_nm
  from customer
 where customer_id not in (select customer_id from blacklist where customer_id is not null); -- (2, null)  
-- not exists 는 row 끼리 join 으로 처리하므로 문제 X 
select c.customer_nm
  from customer c
 where not exists (select b.customer_id from blacklist b where c.customer_id = b.customer_id); 

