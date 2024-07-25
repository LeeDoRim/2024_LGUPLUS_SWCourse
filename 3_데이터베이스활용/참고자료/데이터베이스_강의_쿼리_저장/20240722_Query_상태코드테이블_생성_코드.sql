create table group_code(  -- 어떤 코드를 공통으로 관리할 것인가? '회원 구분', '주문 상태'
	group_code char(3) not null,
    group_code_name varchar(50) not null,
    primary key(group_code)
);

create table code(
	group_code char(3) not null,
    code char(3) not null,
    code_name varchar(50) not null,
    use_yn char(1) null,
    primary key (group_code, code)
);


-- group_code
insert into group_code values ('001', '회원 구분');
insert into group_code values ('002', '주문 상태');

-- code
-- 회원 구분 
insert into code values ('001', '010', '일반 회원', 'Y');
insert into code values ('001', '020', '14세 미만 회원', 'Y');
insert into code values ('001', '030', '외국인 회원', 'Y');

-- 주문 상태
insert into code values ('002', '010', '주문 완료', 'Y');
insert into code values ('002', '020', '결재 완료', 'Y');
insert into code values ('002', '030', '배송중', 'Y');
insert into code values ('002', '040', '배송 완료', 'Y');

-- 만약 공통코드로 사용하지 않고 회원가입을 구현하는 경우
-- front-end : 회원가입 html 중 화면 구분에 대한 화면 구성을 하드 코딩했을 수도 있다. <= 변화에 대응 

-- 만약 공통코드를 사용해서, 회원가입 구현
-- front-end : 회원가입 html 중 회원 구분에 대한 화면 구성을 back-end에 요청 (회원구분코드, 회원구분명) 리스트 

select c.code, c.code_name
  from group_code g, code c 
where g.group_code = c.group_code and g.group_code = '001';


-- 회원 테이블
create table user(
	user_id int not null auto_increment,
    user_name varchar(50) not null,
    user_clsf char(3) not null,
    primary key (user_id)
);

-- 회원 가입
insert into user (user_name, user_clsf) values ('홍길동', '010');
insert into user (user_name, user_clsf) values ('이길동', '020');
insert into user (user_name, user_clsf) values ('삼길동', '030');

-- 회원 상세 정보 조회 화면을 위한 front-end 요청 처리 
select user_id, user_name, user_clsf, 
		(select code_name from code where group_code = '001' and code = user_clsf) user_clsf_nm
  from user 
where user_id = 1; -- 홍길동 로그인한 경우 

-- 정책 변경
-- 14세 미만 회원 -> 어린이 회원 변경

-- 공통코드를 사용하지 않은 경우 (화면, 코드 내 하드 코딩) 모두 찾아서 일일이 수정
-- 공통코드를 사용한 경우 : 코드 테이블의 코드명만 변경하면 됨.
update code
  set code_name = '어린이 회원'
where group_code = '001' and code = '020';

insert into code values ('002', '025', '배송 준비중', 'Y');


-- function 추가
SET GLOBAL log_bin_trust_function_creators = 1; -- function 생성 권한 허가 

/*
CREATE FUNCTION `fun_code` (
    p_group_code char(3),
    p_code char(3)
)
RETURNS varchar(50)
BEGIN
    declare r_code_name varchar(50);
    
    select code_name 
      into r_code_name 
      from code 
     where group_code = p_group_code 
       and code = p_code;
       
RETURN r_code_name;
END
*/

-- 회원 상세 정보 조회 화면을 위한 front-end 요청 처리 
select user_id, user_name, user_clsf, 
		fun_code('001', user_clsf) user_clsf_nm
  from user 
where user_id = 1; -- 홍길동 로그인한 경우 
