-- SET TRANSACTION ISOLATION LEVEL READ COMMITTED : 다른 트랜잭션에서 commit한 경우 commit한 결과를 읽는다. 
SET TRANSACTION ISOLATION LEVEL READ COMMITTED; -- 반복적으로 동일한 데이터 읽기 불가능 (non-repeatable read)

START TRANSACTION;

USE madangdb;

SELECT	*
FROM	Users;

-- tx2 insert & commit 

SELECT	*
FROM	Users;	-- age : 21


commit;