start transaction;

select * from book where bookid in (1, 2);

update book set price = 8000 where bookid = 2;

update book set price = 8000 where bookid = 1;

commit;