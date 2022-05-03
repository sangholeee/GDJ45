DROP TABLE MEMBER;
CREATE TABLE MEMBER
(
	NO NUMBER PRIMARY KEY,
	ID VARCHAR2(20 BYTE) NOT NULL UNIQUE,
	NAME VARCHAR2(20 BYTE),
	GENDER VARCHAR2(6 BYTE),
	ADDRESS VARCHAR2(100 BYTE)
);

DROP SEQUENCE MEMBER_SEQ;
CREATE SEQUENCE MEMBER_SEQ NOCACHE;

INSERT INTO MEMBER VALUES(MEMBER_SEQ.NEXTVAL, 'user1', '사용자1', 'female', 'seoul');
INSERT INTO MEMBER VALUES(MEMBER_SEQ.NEXTVAL, 'user2', '사용자2', 'female', 'inchoen');
INSERT INTO MEMBER VALUES(MEMBER_SEQ.NEXTVAL, 'user3', '사용자3', 'male', 'jeju');
COMMIT


SELECT NO, ID, NAME, GENDER, ADDRESS FROM MEMBER;