start transaction;

select * from book where bookid = 1;

-- update book
-- set price = 9000 대신 현재 price를 읽어서 + 1000
-- update book set price = price + 1000 where bookid = 1;

-- 다른 컬럼도 lock이 걸린다. 
-- update book set publisher = concat(publisher, '_2') where bookid = 1;

-- lock은 row 단위로 처리된다. 
update book set price = price + 1000 where bookid = 2;

commit;