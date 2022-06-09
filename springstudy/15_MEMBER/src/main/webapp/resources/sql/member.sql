DROP TABLE MEMBER_LOG;    -- ���� ������ �Ѵ�.(���̺� ���� ����)
DROP TABLE MEMBER;

CREATE TABLE MEMBER
(
    MEMBER_NO NUMBER NOT NULL,
    ID VARCHAR2(32 BYTE) NOT NULL UNIQUE,       -- UNIQUE�� �־�߸� MEMBER_LOG�� ���� ����(PK�� �ƴ� ��)
    PW VARCHAR2(64 BYTE) NOT NULL,              -- ��ȣȭ�� ��� �ִ� 64����Ʈ
    NAME VARCHAR2(100 BYTE),
    EMAIL VARCHAR2(200 BYTE) NOT NULL UNIQUE,   -- EMAIL ���� �� UNIQUE �ʿ���
    AGREE_STATE NUMBER,                         -- ���� ����, �� ���� �׸� 1, 2, 4, 8 �� �ְ� ���� ���� ��Ÿ�� ���� ����. ���� ����� ���ܾ� ��.
                                                -- (1: �ʼ���, 2: �ʼ�+��ġ, 3: �ʼ�+���θ��, 4:�ʼ�+��ġ+���θ��)
    -- ******* �ּ� �˻� API!********
    SIGN_IN DATE,                               -- ������    
    PW_MODIFIED DATE,                           -- ��й�ȣ ������
    INFO_MODIFIED DATE,                         -- ȸ������ ������
    SESSION_ID VARCHAR2(50 BYTE),               -- ���� ���̵�
    SESSION_LIMIT DATE                          -- ���� ������
);

CREATE TABLE MEMBER_LOG
(
    MEMBER_LOG_NO NUMBER NOT NULL,
    ID VARCHAR2(32 BYTE) NOT NULL,
    SIGN_UP DATE                                 -- �α��� �Ͻ�

);

-- MEMBER �⺻Ű
ALTER TABLE MEMBER
    ADD CONSTRAINT MEMBER_PK
        PRIMARY KEY(MEMBER_NO);

-- MEMBER_LOG �⺻Ű
ALTER TABLE MEMBER_LOG
    ADD CONSTRAINT MEMBER_LOG_PK
        PRIMARY KEY(MEMBER_LOG_NO);

-- MEMBER_LOG �ܷ�Ű
ALTER TABLE MEMBER_LOG
    ADD CONSTRAINT MEMBER_LOG_MEMBER_FK
        FOREIGN KEY(ID) REFERENCES MEMBER(ID)
         ON DELETE CASCADE;

-- ������
DROP SEQUENCE MEMBER_SEQ;
DROP SEQUENCE MEMBER_LOG_SEQ;
CREATE SEQUENCE MEMBER_SEQ NOCACHE;
CREATE SEQUENCE MEMBER_LOG_SEQ NOCACHE;         

-- Ʈ���� ����

-- Ż���� ���̺�
DROP TABLE SIGN_OUT_MEMBER;
CREATE TABLE SIGN_OUT_MEMBER
(
    SIGN_OUT_NO NUMBER NOT NULL,
    MEMBER_NO NUMBER,
    ID VARCHAR2(32 BYTE) UNIQUE,
    NAME VARCHAR2(100 BYTE),
    EMAIL VARCHAR2(200 BYTE),
    AGREE_STATE NUMBER,
    SIGN_OUT DATE DEFAULT SYSDATE
);

--    AS (SELECT MEMBER_NO, ID, PW, NAME, EMAIL
--          FROM MEMBER
--         WHERE 1 = 2);                              -- DB�� PK ������ �����ϰ� ���̺��� ���� �ȴ�.

-- PK�� ����� Į�� �߰�
--ALTER TABLE SIGN_OUT_MEMBER
--    ADD SIGN_OUT_NO NUMBER NOT NULL;

-- Ż���Ϸ� ����� Į�� �߰�        
--ALTER TABLE SIGN_OUT_MEMBER
--    ADD SIGN_OUT DATE DEFAULT SYSDATE NOT NULL;    -- mybatis mapper��� ���⼭ DEFAULT SYSDATE ��� ����

-- �⺻Ű
ALTER TABLE SIGN_OUT_MEMBER
    ADD CONSTRAINT SIGN_OUT_MEMBER_PK
        PRIMARY KEY(SIGN_OUT_NO);

-- ������
DROP SEQUENCE SIGN_OUT_MEMBER_SEQ;
CREATE SEQUENCE SIGN_OUT_MEMBER_SEQ NOCACHE;



-- MEMBER ���̺��� ������ �����Ǹ� SIGN_OUT_MEMBER ���̺�� �ش� ������ ����Ǵ� Ʈ����
CREATE OR REPLACE TRIGGER SIGN_OUT_TRIGGER
    AFTER DELETE
    ON MEMBER
    FOR EACH ROW
BEGIN
    INSERT INTO SIGN_OUT_MEMBER
        (SIGN_OUT_NO, MEMBER_NO, ID, NAME, EMAIL, AGREE_STATE)
    VALUES
        (SIGN_OUT_MEMBER_SEQ.NEXTVAL, :OLD.MEMBER_NO, :OLD.ID, :OLD.NAME, :OLD.EMAIL, :OLD.AGREE_STATE);
END SIGN_OUT_TRIGGER;





















