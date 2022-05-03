/* INNER JOIN */

-- 1. 부서위치(LOCATION_ID)가 1700인 부서에 근무하는 사원들의 EMPLOYEE_ID, LAST_NAME, DEPARTMENT_NAME을 조회하시오.


-- 2. 부서명이 'Executive'인 부서에 근무하는 사원들의 EMPLOYEE_ID, LAST_NAME, DEPARTMENT_NAME을 조회하시오.


-- 3. 직업아이디(JOB_ID)가 변하지 않은 사원들의 EMPLOYEE_ID, LAST_NAME, JOB_ID를 조회하시오.
--    현재 JOB_ID(EMPLOYEES)와 과거 JOB_ID(JOB_HISTORY)가 일치하는 사원을 조회하시오.


-- 4. 부서별로 사원수와 평균연봉을 DEPARTMENT_NAME과 함께 조회하시오.
--    사원수의 오름차순 정렬하시오.


-- 5. CITY가 'S'로 시작하는 지역에 근무하는 사원들의 EMPLOYEE_ID, LAST_NAME, DEPARTMENT_NAME, CITY를 조회하시오.

    
-- 6. 모든 사원들의 EMPLOYEE_ID, LAST_NAME, DEPARTMENT_NAME, CITY, COUNTRY_NAME을 조회하시오.



/* OUTER JOIN */

-- 7. 모든 사원들의 EMPLOYEE_ID, LAST_NAME, DEPARTMENT_NAME을 조회하시오.
--    부서가 없는 사원도 조회하고, EMPLOYEE_ID순으로 오름차순 정렬하시오.


-- 8. 부서별 근무하는 사원수를 조회하시오.
--    단, 근무하는 사원이 없으면 0으로 조회하시오.



/* SELF JOIN */

-- 9. MANAGER보다 먼저 입사한 사원들의 EMPLOYEE_ID, LAST_NAME, HIRE_DATE과 MANAGER의 HIRE_DATE를 조회하시오.
--    사원의 HIRE_DATE가 MANAGER의 HIRE_DATE보다 작은 사원을 조회하시오.



-- 10. 같은 부서의 사원들 중에서 나보다 늦게 입사하였으나 연봉을 더 많이 받는 사원이 있는 사원들의
--     DEPARTMENT_ID, LAST_NAME, SALARY, HIRE_DATE와 높은 연봉을 받는 사원의 FIRST_NAME, SALARY, HIRE_DATE를 조회하시오.
