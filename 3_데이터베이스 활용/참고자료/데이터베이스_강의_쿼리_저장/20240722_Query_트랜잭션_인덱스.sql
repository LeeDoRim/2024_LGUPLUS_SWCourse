select * from jdbc_big where col_1=200; -- execution plan : PK

select count(*) from jdbc_big where col_2='강주상'; -- execution plan : full table scan

-- 데이터 건 수가 충분히 더 큰 테이블을 만들자
create table jdbc_big_2 as select * from jdbc_big;
insert into jdbc_big (col_2, col_3, col_4) select col_2, col_3, col_4 from jdbc_big_2;

select count(*) from jdbc_big;

select count(*) from jdbc_big where col_2 like '%주상';  -- index 안탄다. 
select count(*) from jdbc_big where col_2 like '강주%';

-- index가 있는 칼럼에 함수를 사용하면 index가 동작하지 않는다. 따라서 함수는 오른쪽에 사용하는 것이 좋다. 
select count(*) from jdbc_big where upper(col_2) = '강주상'; -- index 안탄다. 
select count(*) from jdbc_big where col_2 = upper('강주상');
