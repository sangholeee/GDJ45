DROP TABLE BBS;

CREATE TABLE BBS
(
    BBS_NO NUMBER PRIMARY KEY,             -- �Խñ۹�ȣ, BBS_SEQ �̿�
    WRITER VARCHAR2(100 BYTE) NOT NULL,    -- �ۼ���
    TITLE VARCHAR2(100 BYTE) NOT NULL,     -- ����
    CONTENT VARCHAR2(4000 BYTE),           -- ����(�����ͻ�� : �±װ� db�� ����, �� ū ũ��� CLOB Ÿ������ ����
    CREATED VARCHAR2(10 BYTE),             -- �����ۼ��� 2022-05-17
    MODIFIED VARCHAR2(10 BYTE)             -- ���������� 2022-05-17
);
DROP SEQUENCE BBS_SEQ;
CREATE SEQUENCE BBS_SEQ NOCACHE;