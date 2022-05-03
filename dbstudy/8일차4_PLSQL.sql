-- HR 계정의 EMPLOYEES 테이블을 SCOTT 계정으로 복사하기
CREATE TABLE EMPLOYEES
    AS (SELECT *
          FROM HR.EMPLOYEES);

-- 기본키는 추가로 생성
ALTER TABLE EMPLOYEES
    ADD CONSTRAINT EMPLOYEES_PK PRIMARY KEY(EMPLOYEE_ID);



-- 서버메시지 출력을 할 수 있도록 SERVEROUTPUT 값을 ON으로 세팅한다.
-- 디폴트 상태는 SET SERVEROUTPUT OFF이다.
SET SERVEROUTPUT ON;



-- PL/SQL의 프로그래밍 기본 형식
/*
[DECLARE]
    변수 선언
BEGIN
    작업 수행
END;
*/



-- 'Hello World' 서버메시지 출력하기
-- DBMS_OUTPUT.PUT_LINE('메시지') : '메시지' 출력 후 줄 바꿈을 진행한다.
-- DBMS_OUTPUT.PUT('메시지')      : '메시지' 만 출력한다.
BEGIN
    DBMS_OUTPUT.PUT_LINE('Hello World');
END;




-- 변수 선언하기1
-- 스칼라 변수 (타입을 직접 명시하는 방법)
-- 대입연산자는 '등호(=)'가 아니라 '콜론등호(:=)'이다.
DECLARE
    my_name VARCHAR2(20 BYTE);
    my_age  NUMBER(3);
BEGIN
    my_name := '민경태';
    my_age := 45;
    DBMS_OUTPUT.PUT_LINE('My name is ' || my_name);
    DBMS_OUTPUT.PUT_LINE('I am ' || my_age || ' years old.');
END;




-- 변수 선언하기2
-- 참조 변수 (기존 테이블의 특정 칼럼과 같은 타입으로 선언)
-- 참조 변수 선언 방식 : 테이블명.칼럼%TYPE

DECLARE
    my_name   EMPLOYEES.LAST_NAME%TYPE;
    my_salary EMPLOYEES.SALARY%TYPE;
BEGIN
    my_name := '민경태';
    my_salary := 1000;
    DBMS_OUTPUT.PUT_LINE(my_name);
    DBMS_OUTPUT.PUT_LINE(my_salary);
END;



-- 참조 타입은 주로 테이블에 저장된 데이터를 변수에 저장할 때 사용
-- 테이블의 데이터를 변수에 저장하는 방법
-- SELECT 칼럼 INTO 변수 : 칼럼의 값을 변수에 저장

DECLARE
    var_last_name  EMPLOYEES.LAST_NAME%TYPE;
    var_salary     EMPLOYEES.SALARY%TYPE;
BEGIN
    SELECT LAST_NAME, SALARY
      INTO var_last_name, var_salary
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = 100;
    DBMS_OUTPUT.PUT_LINE('LAST_NAME : ' || var_last_name);
    DBMS_OUTPUT.PUT_LINE('SALARY : ' || var_salary);
END;


-- 변수 선언하기3
-- 레코드 타입 : 여러 칼럼을 하나의 레코드로 저장할 수 있는 변수의 타입이다.
-- 레코드 타입을 먼저 정의하고, 레코드 타입의 변수를 선언해서 사용하낟.
-- DECLARE에서 레코드 타입 정의와 변수 선언을 수행한다.

DECLARE

    -- 레코드 타입 정의
    -- 새로 정의하는 타입의 이름은 임의로 정한다.
    -- 여기서는 employee_type
    
    TYPE employee_type IS RECORD(
        var_employee_id EMPLOYEES.EMPLOYEE_ID%TYPE,
        var_first_name  EMPLOYEES.FIRST_NAME%TYPE,
        var_last_name   EMPLOYEES.LAST_NAME%TYPE
    );
    
    -- 레코드 타입(employee_type)의 변수(var_record) 선언
    var_record employee_type;
    
BEGIN

    -- EMPLOYEE_ID가 100인 사원의 정보(EMPLOYEE_ID, FIRST_NAME, LAST_NAME)를 var_record에 저장하기
    SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME
      INTO var_record
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = 100;
     
     -- var_record에는 EMPLOYEE_ID, FIRST_NAME, LAST_NAME이 모두 저장된 상태이다.
     -- 각 항목은 마침표(.)로 호출할 수 있다.
     DBMS_OUTPUT.PUT_LINE('사원번호' || var_record.var_employee_id);
     DBMS_OUTPUT.PUT_LINE('이름' || var_record.var_first_name);
     DBMS_OUTPUT.PUT_LINE('성' || var_record.var_last_name);
     
END;



-- 변수 선언하기4
-- 행 타입 : 특정 행(ROW) 전체를 저장할 수 있는 타입이다.
-- 행 타입 선언 방식 : 테이블%ROWTYPE

DECLARE

    var_row  EMPLOYEES%ROWTYPE;

BEGIN

    SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID
      INTO var_row
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = 100;
    
    DBMS_OUTPUT.PUT_LINE('이메일 ' || var_row.email);
    DBMS_OUTPUT.PUT_LINE('전화 ' || var_row.phone_number);

END;




-- IF문
/*
    IF 조건식 THEN
        실행문
    ELSIF 조건식 THEN
        실행문
    ELSE
        실행문
    END IF;
*/

-- 성인/미성년자
DECLARE
    my_age NUMBER;
BEGIN
    my_age := 5;
    IF my_age >= 20 THEN
        DBMS_OUTPUT.PUT_LINE('성인');
    ELSE
        DBMS_OUTPUT.PUT_LINE('미성년자');
    END IF;
END;

-- 점수에 따른 학점 출력하기
DECLARE
    score NUMBER(3);
    grade VARCHAR2(1 BYTE);
BEGIN
    score := 100;
    IF score >= 90 THEN
        grade := 'A';
    ELSIF score >= 80 THEN
        grade := 'B';
    ELSIF score >= 70 THEN
        grade := 'C';
    ELSIF score >= 60 THEN
        grade := 'D';
    ELSE
        grade := 'F';
    END IF;
    DBMS_OUTPUT.PUT_LINE('점수는 ' || score || '점이고 학점은 ' || grade || '학점이다.');
END;

-- EMPLOYEES 테이블에서 EMPLOYEE_ID가 200인 사원의 연봉(SALARY)을 가져와서
-- 15000 이상이면 '고연봉', 10000 이상이면 '중연봉', 나머지는 '저연봉'으로 출력하시오.
DECLARE
    var_salary EMPLOYEES.SALARY%TYPE;
    var_result VARCHAR2(10 BYTE);
BEGIN
    SELECT SALARY
      INTO var_salary
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = 200;
    IF var_salary >= 15000 THEN
        var_result := '고연봉';
    ELSIF var_salary >= 10000 THEN
        var_result := '중연봉';
    ELSE
        var_result := '저연봉';
    END IF;
    DBMS_OUTPUT.PUT_LINE('연봉은 ' || var_salary || '이고 ' || var_result || '입니다.');
END;




-- CASE문
/*
    CASE
        WHEN 조건식 THEN
            실행문
        WHEN 조건식 THEN
            실행문
        ELSE
            실행문
    END CASE;
*/
-- 점수에 따른 학점 출력하기
DECLARE
    score NUMBER;
    grade VARCHAR2(1 BYTE);
BEGIN
    score := 75;
    CASE
        WHEN score >= 90 THEN
            grade := 'A';
        WHEN score >= 80 THEN
            grade := 'B';
        WHEN score >= 70 THEN
            grade := 'C';
        WHEN score >= 60 THEN
            grade := 'D';
        ELSE
            grade := 'F';
    END CASE;
    DBMS_OUTPUT.PUT_LINE('점수는 ' || score || '점이고 학점은 ' || grade || '학점입니다.');
END;









