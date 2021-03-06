-- 테이블은 항상 외래키를 가진 테이블을 먼저 지워야 한다.
DROP TABLE PROCEEDING;
DROP TABLE EMPLOYEE;
DROP TABLE PROJECT;
DROP TABLE DEPARTMENT;

CREATE TABLE DEPARTMENT(
    DEPT_NO       VARCHAR2(15 BYTE),
    DEPT_NAME     VARCHAR2(30 BYTE),
    DEPT_LOCATION VARCHAR2(30 BYTE),
    CONSTRAINTS   DEPARTMENT_PK PRIMARY KEY(DEPT_NO)
);


CREATE TABLE EMPLOYEE(
    EMP_NO      NUMBER,
    DEPT_NO     VARCHAR2(15 BYTE),
    POSITION    CHAR(10 BYTE),
    NAME        VARCHAR2(15 BYTE),
    HIRE_DATE   DATE,
    SALARY      NUMBER,
    CONSTRAINTS EMPLOYEE_PK PRIMARY KEY(EMP_NO),
    CONSTRAINTS EMPLOYEE_DEPARTMENT_FK FOREIGN KEY(DEPT_NO) REFERENCES DEPARTMENT(DEPT_NO)
);

CREATE TABLE PROJECT(
    PJT_NO      NUMBER,
    PJT_NAME    VARCHAR2(30 BYTE),
    BEGIN_DATE  DATE,
    END_DATE    DATE,
    CONSTRAINTS PROJECT_PK PRIMARY KEY(PJT_NO)
);
    
CREATE TABLE PROCEEDING(
    PCD_NO      NUMBER,
    EMP_NO      NUMBER,
    PJT_NO      NUMBER,
    CONSTRAINTS PROCEEDING_PK PRIMARY KEY(PCD_NO),
    CONSTRAINTS PROCEEDING_EMPLOYEE_FK FOREIGN KEY(EMP_NO) REFERENCES EMPLOYEE(EMP_NO),
    CONSTRAINTS PROCEEDING_PROJECT_FK FOREIGN KEY(PJT_NO) REFERENCES PROJECT(PJT_NO)
);