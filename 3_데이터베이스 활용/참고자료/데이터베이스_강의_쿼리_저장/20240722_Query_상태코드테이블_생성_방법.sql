-- 쇼핑몰 구매 1 건에 대한 상태를 주문 상태 코드 테이블을 고려한다.
-- 주문 상태 코드 테이블을 이용하는 것과 주문 상태를 문자열 하드 코딩하는 것 비교

-- 테이블 하나 당 또 칼럼 하나 당 관견 코드 테이블을 만들면 갯수가 엄청나게 늘어난다.
-- 하나의 (실제로 2개) 통합된 코드 관리 테이블을 만들고 이를 이용 <= 공통코드 테이블alter

-- 부모 / 자식 구조
-- 부모 : 코드의 종류 / 구성
