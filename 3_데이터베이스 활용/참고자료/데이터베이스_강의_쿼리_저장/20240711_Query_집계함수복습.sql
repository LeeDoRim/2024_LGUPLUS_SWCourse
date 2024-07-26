-- 1. country 테이블에서 유럽(Europe) 대륙에 속하는 모든 국가의 인구수(Population) 의 총합은? (결과 : 730074600)
select sum(Population) from country where Continent = 'Europe';

-- 2. country 테이블에서 대륙(Continent)별 건수, 최대인구수, 최소 Gnp, 최대 Gnp, 평균 기대수명을 구하시오. (row 수 : 7)
select Continent, count(*) cnt, max(Population), min(GNP), max(GNP), avg(LifeExpectancy) from country group by Continent;

-- 3. 위 결과를 더 세분화 해서 대륙(Continent)별, 지역(Region)별로 구하시오. 단, 결과를 대륙별, 지역별로 정렬(asc)하시오. (row 수 : 25)
select Continent, Region, count(*) cnt, max(Population), min(GNP), max(GNP), avg(LifeExpectancy) 
from country group by Continent, Region order by Continent, Region;

-- 4. 위 결과 중 평균 기대수명이 70세 이상인 결과만 보여주시오. (row 수 : 12)
select Continent, Region, count(*) cnt, max(Population), min(GNP), max(GNP), avg(LifeExpectancy) 
from country group by Continent, Region having avg(LifeExpectancy) >= 70 order by Continent, Region;

-- (row 수 : 10)
select Continent, Region, count(*) cnt, max(Population), min(GNP), max(GNP), avg(LifeExpectancy) 
from country group by Continent, Region having avg(LifeExpectancy) >= 70 and max(GNP) >= 100000
order by Continent, Region;

-- 5. country 테이블에서 전체 자료의 수와 GNP 건수, GNPOld 건수를 구하시오. (전체 자료의 수 : 239, GNP 건수 : 239, GNPOld 건수 : 178)
-- count(*)(row의 갯수)를 제외한 count(colnmu)는 null은 제외한다. 
select count(*), count(GNP), count(GNPOld) from country;
select count(*) from country where GNPOld is null; -- (row 수 : 61)

-- 6. 국가별 도시 수가 5 이상인 국가의 CountryCode 와 도시 수(cnt alias) 를 조회하세요.  
--    단, 조회 대상은 인구가 50만 이상인 도시에 한하고 도시 수 기준 내림차순 정렬하시오.
select CountryCode, count(*) cnt from city where Population >= 500000 group by CountryCode having cnt >= 5 order by cnt desc;

