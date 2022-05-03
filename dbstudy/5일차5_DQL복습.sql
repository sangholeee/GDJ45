-- 1. employees 테이블에서 salary가 10000~20000 사이가 아닌 사원들의 last_name, salary를 조회하시오.
SELECT LAST_NAME, SALARY
    FROM EMPLOYEES
    WHERE SALARY < 10000 OR SALARY > 20000;
--  WHERE SALARY NOT BETWEEN 10000 AND 10000;


-- 2. employees 테이블에서 employee_id가 150인 사원의 employee_id, first_name, last_name을 조회하시오.
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME
    FROM EMPLOYEES
    WHERE EMPLOYEE_ID = 150;
    

-- 3. employees 테이블에서 commission_pct가 없는 사원들의 employee_id, commission_pct를 조회하시오.
SELECT EMPLOYEE_ID, COMMISSION_PCT
    FROM EMPLOYEES
    WHERE COMMISSION_PCT IS NULL;
    

-- 4. employees 테이블에서 commission_pct가 있는 사원들의 employee_id, commission_pct를 조회하시오.
SELECT EMPLOYEE_ID, COMMISSION_PCT
    FROM EMPLOYEES
    WHERE COMMISSION_PCT IS NOT NULL;
    

-- 5. employees 테이블에서 모든 사원들의 employee_id와 커미션(salary * commission_pct)을 조회하시오.
--    커미션이 없는 경우 0으로 조회하시오.
SELECT EMPLOYEE_ID, SALARY * NVL(COMMISSION_PCT, 0) AS 커미션
    FROM EMPLOYEES;
    
    
-- 6. employees 테이블에서 모든 사원들의 employee_id와 commission_pct를 10% 인상시킨 결과를 조회하시오.
--    commission_pct가 없는 경우에는 인상시키지 않도록 처리하시오.

--SELECT EMPLOYEE_ID, NVL(COMMISSION_PCT, 0) * 1.1
--    FROM EMPLOYEES;

SELECT EMPLOYEE_ID, NVL2(commission_pct, commission_pct * 1.1, 0) AS 커미션
    FROM EMPLOYEES;

-- 7. employees 테이블에서 모든 사원들을 salary 기준으로 오름차순 정렬하여 조회하시오.
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID
    FROM EMPLOYEES
    ORDER BY SALARY;


-- 8. employees 테이블에서 모든 사원들을 salary 기준으로 내림차순 정렬하여 조회하시오.
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID
    FROM EMPLOYEES
    ORDER BY SALARY DESC;
    

-- 9. employees 테이블에서 같은 department_id를 가진 사원끼리 모아서 조회하되, 높은 salary를 가진 사원을 먼저 조회하시오.
--     (부서별로 정렬하되, 급여가 높은 사원을 먼저 조회하시오.)
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID
    FROM EMPLOYEES
    ORDER BY DEPARTMENT_ID, SALARY DESC;



-- 10. employees 테이블에서 commission_pct가 없는 사원들을 높은 salary 순으로 조회하시오.
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID
    FROM EMPLOYEES
    WHERE COMMISSION_PCT IS NULL
    ORDER BY SALARY DESC;
    
    
