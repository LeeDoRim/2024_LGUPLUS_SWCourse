start transaction;

select * from book where bookid = 1;

update book set price = 8000 where bookid = 1;

select * from book where bookid = 1;

commit;