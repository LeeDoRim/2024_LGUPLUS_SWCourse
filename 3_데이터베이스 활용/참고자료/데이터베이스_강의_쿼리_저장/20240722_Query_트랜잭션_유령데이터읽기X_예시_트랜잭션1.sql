-- SET TRANSACTION ISOLATION LEVEL READ COMMITTED : 다른 트랜잭션에서 commit한 경우 commit한 결과를 읽는다. 
SET TRANSACTION ISOLATION LEVEL repeatable read; -- 반복적으로 동일한 데이터 읽기 
-- 내 트랜잭션이 시작하고 끝나기 전까지는 다른 트랜잭션에 의해 row에 변화(COMMIT 포함)가 생기더라도 동일한 row를 조회한다.
-- repeatable read가 default이다.

START TRANSACTION;

USE madangdb;

SELECT	*
FROM	Users;

-- tx2 insert & commit 

SELECT	*
FROM	Users;	-- age : 21


commit;