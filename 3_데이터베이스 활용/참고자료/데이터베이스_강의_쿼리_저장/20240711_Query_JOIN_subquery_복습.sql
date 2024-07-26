-- 1. 도시명 Seoul 속한 국가의 이름, 인구수, GDP 를 조회하시오.
-- 표준 SQL
select country.name, country.population, country.GNP 
from country join city on country.code = city.countrycode where city.name = 'Seoul';

-- 일반 join
select co.name, co.population, co.GNP from country co, city ci
where co.code = ci.countryCode and ci.name = 'Seoul';

-- subquery
select name, population, GNP from country
where code in (select countrycode from city where name = 'Seoul'); -- 단일행이지만, 다중행일 수 있는 sql, 단일열

-- 2. 아시아에 있는 국가의 도시 중 인구가 가장 많은 도시 10개를 조회하시오. 조회 항목은 국가명, 도시명, 도시 인구이다
select country.name, city.name, city.Population from country join city on country.code = city.countrycode 
where country.Continent = 'Asia' order by city.Population desc limit 10;

select co.name, ci.name, ci.Population from country co, city ci 
where co.code = ci.CountryCode and co.Continent = 'Asia' order by ci.Population desc limit 10;

-- subquery 연습

select name, population from city
where countrycode in (select code from country where continent = 'Asia')
order by Population desc limit 10;

SELECT country.name AS country_name, city.name AS city_name, city.Population FROM city
JOIN (SELECT code, name FROM country WHERE Continent = 'Asia') country ON city.countrycode = country.code
ORDER BY city.Population DESC LIMIT 10;

-- subquery 답안
-- city를 기준으로 subquery를 이용하여 where 조건에 Asia 부분은 처리 OK
-- select에 나라이름은 별도로 scalar subquery로 처리를 해야 한다. <- join이 더 간결하다.
select name, Population, (select name from country where code = countrycode) as countryName -- select (scalar sebquery - 반드시 1 건)
from city  -- (inline view)
where CountryCode in (select code from country where Continent = 'Asia')  -- 조건 (nested subquery)
order by Population desc limit 10;

-- scalar sebquery는 생각보다 자주 사용한다. 
-- 예시 a와 b 테이블을 join 시 key로 찾아지는 조건이 없을 때 카티션 프로덕트가 너무 많아지므로
-- 하나의 테이블을 조회하고 그에 대한 다른 테이블에서 필요한 값을 scalar sebquery로 가져온다.
-- scalar sebquery는 하나의 칼럼에 매칭되므로 sebquery에서 리턴되는 결과가 무조건 하나여야 한다.


-- 3. 공식언어의 사용율이 50% 가 넘는 국가의 이름, 공식언어, 사용율을 조회하시오.
-- 일반 join
select co.name, cl.Language, cl.Percentage
from country co, countrylanguage cl
where co.code = cl.CountryCode and cl.IsOfficial = 'T' and cl.Percentage > 50.0;

-- 표준 SQL
select country.name, countrylanguage.Language, countrylanguage.Percentage 
from country join countrylanguage on country.code = countrylanguage.CountryCode
where countrylanguage.IsOfficial = 'T' and countrylanguage.Percentage > 50;

-- subquery
-- 위 2번 쿼리와 다른 점 : where 절 filtering 조건으로 country가 사용되지 않는다. -> select절 에만 subquery를 사용하면 된다. 
select (select name from country where code = CountryCode), Language, Percentage 
from countrylanguage where IsOfficial = 'T' and Percentage > 50;

-- 4. 유럽의 도시 중 인구수가 가장 많은 도시의 인구수를 가진 도시명, 인구수를 조회하시오.
select city.name, city.Population from country join city on country.code = city.CountryCode
where country.Continent = 'Europe' order by city.Population desc limit 1;

select ci.name, ci.Population from country co, city ci
where co.code = ci.CountryCode and co.Continent = 'Europe' 
order by ci.Population desc limit 1;

select * 
  from city
where Population = (select max(population) from city where countrycode in (
						select code from country where continent = 'Europe'
						)
					);

select * 
  from city ci, (select max(population) maxPop from city where countrycode in (
						select code from country where continent = 'Europe'
						)
					) m
where ci.Population = m.maxPop;
