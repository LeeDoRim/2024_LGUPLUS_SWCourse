desc country;

-- char vs varchar
-- char : 길이가 고정되어 있다. (고정 너비)
-- varchar : 가변 길이 (가변 너비) 

select * from world.country
where name like '%korea%';

select * from world.city
where CountryCode = 'KOR';

select * from world.countrylanguage
where CountryCode = 'KOR';


-- 1. country table의 모든 자료를 조회하시오. (총 239 건, row 수 : 239)
select * from country;

-- 2. country table 의 전체 건수를 구하세요. (row 수 : 1)
select count(*) from country; -- 239 건

-- 3. country table에서 국가 code가 FRA인 자료를 조회하시오. (row 수 : 1)
select * from country where code = 'FRA';

-- 4. country table 에서 대륙(Continent) 이 Africa 또는 Europe 인 자료를 조회하시오. (row 수 : 104)
select * from country where Continent = 'Africa' or Continent = 'Europe';
select * from country where Continent in ('Africa', 'Europe');

-- 5. country table 에서 독립일(IndepYear) 이 없는 나라의 자료를 조회하시오. (row 수 : 47)
select * from country where IndepYear is null;
select * from country where IndepYear is not null;

-- 6. country table 에서 모든 독립일을 중복 없이 조회하시오.
select distinct IndepYear from country; -- null 포함 (row 수 : 89) : distinct는 null도 하나의 value로 처리
select distinct IndepYear from country where IndepYear is not null; -- (row 수 : 88)

-- 7. country table 에서 인구(Population) 이 1000000(백만) 보다 크고 
--    수명예상(LifeExpectancy) 이 70 살 이상인 자료를 조회하시오. (row 수 : 66)
select * from country where Population > 1000000 and LifeExpectancy >= 70;

-- 8. country table 에서 이전 gnp(GNPOld) 대비 gnp 가 1000 이상 증가한 국가의 이름과 gnp, GNPOld, 증가한 GNP 를 조회하시오.
--    이 때 증가한 GNP 를 GNPDiff 로 alias 를 주세요. (row 수 : 47)
select name, GNP, GNPOld, (GNP - GNPOld) GNPDiff from country where (GNP - GNPOld) >= 1000;

-- 9. 위 데이터를 GNPDiff 로 내림차순 정렬하세요. (row 수 : 47)
select name, GNP, GNPOld, (GNP - GNPOld) GNPDiff from country where (GNP - GNPOld) >= 1000 order by GNPDiff;

-- 10. country table 에서 GNP 가 100 미만 또는 100000 초과인 자료를 조회하세요. (row 수 : 68)
select * from country where GNP < 100 or GNP > 100000;

-- 11. country table 에서 GNP 가 100 초과하고 100000 미만인 자료를 조회하세요. (row 수 : 170)
select * from country where GNP > 100 and GNP < 100000;

-- 12. country table 에서 GNP 가 100 이상 100000 이하인 자료를 조회하세요. (row 수 : 171)
select * from country where GNP >= 100 and GNP <= 100000;
select * from country where GNP between 100 and 100000;

-- 13. 위 11, 12 차이를 만드는 나라의 데이터를 확인하세요. (row 수 : 1)
select * from country where GNP = 100 or GNP = 100000;


-- 14. country table 에서 독립년도(IndepYear) 가 1980 이후이면서 
--     대륙(Continent) 이 Asia 가 아닌 나라의 자료를 조회하세요. (row 수 : 23)
select * from country where IndepYear >= 1980 and Continent != 'Asia';
select * from country where IndepYear >= 1980 and not Continent = 'Asia';

select count(*) from country where IndepYear >= 1980; -- (row 수 : 1, 데이터 개수 32)
select count(*) from country where IndepYear >= 1980 and Continent = 'Asia'; -- (row 수 : 1, 데이터 개수 : 9)

-- 15. country table 에서 대륙(Continent) 이  'Europe', 'Asia', 'North America' 이 아닌 나라의 자료를, 
--     대륙(Continent) 기준으로 오름차순, GNP 기준 내림차순으로 국가명, 대륙명, GNP 를 조회하시오. (row 수 : 105)
select name, Continent, GNP from country where Continent not in ('Europe', 'Asia', 'North America') order by Continent, GNP desc;

-- 16. city table 에서 도시명에 'S' 로 시작하고 중간에 'aa' 가 포함되는 나라를 모두 조회하세요.
select * from city where name like 'S%aa%';

-- 17. country table 에서 가장 면적이 큰 5개 나라의 이름, 대륙, 면적을 조회하세요.
select name, Continent, SurfaceArea from country order by SurfaceArea desc limit 5; -- limit는 mysql에서만 사용하는 문법이다.

-- 18. 위 쿼리의 결과를 보고 5개 나라 뒤 10개 나라 (6 ~ 15 위) 를 조회하세요.
select name, Continent, SurfaceArea from country order by SurfaceArea desc limit 10 offset 5;
select name, Continent, SurfaceArea from country order by SurfaceArea desc limit 10, 5; -- 앞 숫자가 offset뒤 숫자가 limit
select name, Continent, SurfaceArea from country order by SurfaceArea desc limit 5, 10; -- 앞 숫자가 offset뒤 숫자가 limit