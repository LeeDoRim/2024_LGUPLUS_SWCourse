start transaction;

select * from book where bookid in (1, 2);

update book set price = 8000 where bookid = 1;

-- 2번 트랜잭션 bookid=2 수정 실행 

update book set price = 8000 where bookid = 2;

commit;