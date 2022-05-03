-- 생성자 Oracle SQL Developer Data Modeler 20.4.0.374.0801
--   위치:        2022-02-22 13:05:00 KST
--   사이트:      Oracle Database 11g
--   유형:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE department (
    dept_no        VARCHAR2(15 BYTE) NOT NULL,
    dept_name      VARCHAR2(30 BYTE),
    dept_location  VARCHAR2(30 BYTE)
);

ALTER TABLE department ADD CONSTRAINT department_pk PRIMARY KEY ( dept_no );

CREATE TABLE employee (
    emp_no              NUMBER NOT NULL,
    dept_no             VARCHAR2(15 BYTE),
    position            CHAR(10 BYTE),
    name                VARCHAR2(15 BYTE),
    hire_date           DATE,
    salary              NUMBER,
    department_dept_no  VARCHAR2(15 BYTE) NOT NULL
);

ALTER TABLE employee ADD CONSTRAINT employee_pk PRIMARY KEY ( emp_no );

CREATE TABLE proceeding (
    pcd_no           NUMBER NOT NULL,
    emp_no           NUMBER,
    pjt_no           NUMBER,
    employee_emp_no  NUMBER NOT NULL,
    project_pjt_no   NUMBER NOT NULL
);

ALTER TABLE proceeding ADD CONSTRAINT proceeding_pk PRIMARY KEY ( pcd_no );

CREATE TABLE project (
    pjt_no      NUMBER NOT NULL,
    pjt_name    VARCHAR2(30 BYTE),
    begin_date  DATE,
    end_date    DATE
);

ALTER TABLE project ADD CONSTRAINT project_pk PRIMARY KEY ( pjt_no );

ALTER TABLE employee
    ADD CONSTRAINT employee_department_fk FOREIGN KEY ( department_dept_no )
        REFERENCES department ( dept_no );

ALTER TABLE proceeding
    ADD CONSTRAINT proceeding_employee_fk FOREIGN KEY ( employee_emp_no )
        REFERENCES employee ( emp_no );

ALTER TABLE proceeding
    ADD CONSTRAINT proceeding_project_fk FOREIGN KEY ( project_pjt_no )
        REFERENCES project ( pjt_no );



-- Oracle SQL Developer Data Modeler 요약 보고서: 
-- 
-- CREATE TABLE                             4
-- CREATE INDEX                             0
-- ALTER TABLE                              7
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
