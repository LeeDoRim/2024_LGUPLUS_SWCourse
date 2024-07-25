-- 타 은행 간 계좌 이체 (분산 DB, 이종 DB, 이종 네트워크...) <= 이체 등 다양한 은행업무를 위한 프로토콜 (국제 규약)

-- 은행 계화 이체 (동일 은행 동일 DB)
-- 마감 작업 (영업 시스템) 월 마감 예시 => 7.25 ㅇ;ㄹ닐 6.26 ~ 7.25까지 7월분 영업에 대한 마감
	-- 다양한 실적 테이블을 대상으로 전체 조회 --> 갭려 마감 관련 테이블에 등록 -> 개별 마감 관련 테이블에 등록 -> 실적, 수당 관련 테이블에 등록
	-- 위 모든 작업이 하나의 트랜잭션으로 처리 (되면 다 같이 되고, 일부 안되면 모두 안되어야 된다. (쉬소))
    
-- non transaction 작업
insert into customer values(1, '홍길동'); commit;
insert into customer values(2, '이길동'); commit;
insert into customer values(3, '삼길동'); commit;

rollback;
    
-- 트랜잭션 시작, 종료

select @@autocommit;  -- 1 : on, 0 : off
-- 1인 경우 쿼리 한 번 실행 시마다 자동으로 commit 된다. 

start transaction;
-- set autocommit = 0;

insert into customer values(1, '홍길동'); 
-- 점검, 확인
-- commit;
-- rollback;

savepoint p1;

insert into customer values(2, '이길동'); 
-- 점검, 확인
-- commit;
-- rollback;

savepoint p2;
-- 중간에 되돌아갈 지점 

insert into customer values(3, '삼길동'); 
-- 점검, 확인
-- commit;
-- rollback;
rollback to p2;

commit; -- 성공에 따른 종료

rollback; -- 실패에 따른 종료 