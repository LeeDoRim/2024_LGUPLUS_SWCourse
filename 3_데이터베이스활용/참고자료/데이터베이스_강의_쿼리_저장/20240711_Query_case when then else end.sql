-- sum 
select sum(salary) from employees;

-- 부서별 (department_id) sum(salary)
select department_id, sum(salary) 
  from employees 
group by department_id;

-- 특정 부서(들)의 부서별 sum(salary)
-- 2개 부서의 sum만 필요한데 전체 부서의 sum을 계산한다. 
select department_id, sub_salary
  from (select department_id, sum(salary) sub_salary from employees group by department_id) sub
where sub.department_id in (60, 100);

-- case when then else end 를 이용해서 보다 효율적인 코드 작성
select sum(case when department_id = 60 then salary else 0 end) sum_60,
	   sum(case when department_id = 100 then salary else 0 end) sum_100 
  from employees
where department_id in (60, 100);

-- 아래 코드를 개선
select
(select sum(salary) from employees where department_id = 50) sum50,
(select avg(salary) from employees where department_id = 60) avg60,
(select max(salary) from employees where department_id = 90) max90,
(select min(salary) from employees where department_id = 90) min90;

select sum(case when department_id = 50 then salary else null end) sum50,
	   avg(case when department_id = 60 then salary else null end) avg60,
       max(case when department_id = 90 then salary else null end) max90,
       min(case when department_id = 90 then salary else null end) min90 -- else가 0이면 min이 0이 된다. null은 계산하지 않는다.
  from employees
where department_id in (50, 60, 90);
