-- ÷�� �Խ���

-- 1. ���� ÷��
/*
    �Խñ�1 - ÷��1
    �Խ��� : ����, ����, �ۼ���, ÷�����ϸ�, ���
*/

-- 2. ���� ÷��
/*
    �Խñ�1 - ÷��1
            - ÷��2
            
    �Խ��� : ����, ����, �ۼ���, ÷�����ϸ�1, ���1
             ����, ����, �ۼ���, ÷�����ϸ�2, ���2
             (X)
             ------------------------------------------
    �Խ��� : ��ȣ(PK), ����, ����, �ۼ���
    ÷��   : ÷�ι�ȣ, �Խñ۹�ȣ(FK), ÷�����ϸ�, ���
*/

/* 
    ���� ���̺� �Ӹ� �ƴ϶� DB ���� ���� COLUMN���� ������ �ȵȴ�. 

    ÷�� ���ϸ� �ٲٴ� ����
    1. ���ڵ�
    2. �ߺ� ����! 
*/

DROP TABLE FILE_ATTACH;
CREATE TABLE FILE_ATTACH
(
    FILE_ATTACH_NO NUMBER NOT NULL,    -- PK
    PATH VARCHAR2(300 BYTE),
    ORIGIN VARCHAR2(300 BYTE),
    SAVED VARCHAR2(40 BYTE),           -- UUID�� �ٲ� id ���� + Ȯ���� ����
    GALLERY_NO NUMBER                  -- FK
);

DROP TABLE GALLERY;
CREATE TABLE GALLERY
(
    GALLERY_NO NUMBER NOT NULL,        -- PK
    WRITER VARCHAR2(50 BYTE),
    TITLE VARCHAR2(100 BYTE),
    CONTENT VARCHAR2(100 BYTE),
    IP VARCHAR2(30 BYTE),
    HIT NUMBER,
    CREATED DATE,
    MODIFIED DATE
);

ALTER TABLE FILE_ATTACH 
    ADD CONSTRAINT FILE_ATTACH_PK 
        PRIMARY KEY(FILE_ATTACH_NO);

ALTER TABLE GALLERY
    ADD CONSTRAINT GALLERY_PK 
        PRIMARY KEY(GALLERY_NO);

ALTER TABLE FILE_ATTACH
    ADD CONSTRAINT FILE_ATTACH_GALLERY_FK FOREIGN KEY(GALLERY_NO)
        REFERENCES GALLERY(GALLERY_NO)
            ON DELETE CASCADE;   -- �Խñ� �����ϸ� ÷�� ������ �Բ� ����





