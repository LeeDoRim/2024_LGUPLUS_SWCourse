SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
START TRANSACTION;
USE madangdb;
SELECT	*
FROM	Users
WHERE	id=1;

-- tx2 update

SELECT	*
FROM	Users
WHERE	id=1;	-- age : 21 <- uncommited

-- tx2 rollback 

SELECT	*
FROM	Users
WHERE	id=1;	-- age : 30

commit;