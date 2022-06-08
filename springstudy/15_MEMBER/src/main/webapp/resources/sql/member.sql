DROP TABLE MEMBER_LOG;    -- 먼저 지워야 한다.(테이블 생성 역순)
DROP TABLE MEMBER;

CREATE TABLE MEMBER
(
    MEMBER_NO NUMBER NOT NULL,
    ID VARCHAR2(32 BYTE) NOT NULL UNIQUE,       -- UNIQUE가 있어야만 MEMBER_LOG와 연결 가능(PK가 아닐 때)
    PW VARCHAR2(64 BYTE) NOT NULL,              -- 암호화된 비번 최대 64바이트
    NAME VARCHAR2(100 BYTE),
    EMAIL VARCHAR2(200 BYTE) NOT NULL UNIQUE,   -- EMAIL 인증 시 UNIQUE 필요함
    AGREE_STATE NUMBER,                         -- 동의 여부, 각 동의 항목에 1, 2, 4, 8 을 주고 합을 통해 나타낼 수도 있음. 동의 기록을 남겨야 함.
                                                -- (1: 필수만, 2: 필수+위치, 3: 필수+프로모션, 4:필수+위치+프로모션)
    -- ******* 주소 검색 API!********
    SIGN_IN DATE,                               -- 가입일    
    PW_MODIFIED DATE,                           -- 비밀번호 수정일
    INFO_MODIFIED DATE,                         -- 회원정보 수정일
    SESSION_ID VARCHAR2(50 BYTE),               -- 세션 아이디
    SESSION_LIMIT DATE                          -- 세션 만료일
);

CREATE TABLE MEMBER_LOG
(
    MEMBER_LOG_NO NUMBER NOT NULL,
    ID VARCHAR2(32 BYTE) NOT NULL,
    SIGN_UP DATE                                 -- 로그인 일시

);

-- MEMBER 기본키
ALTER TABLE MEMBER
    ADD CONSTRAINT MEMBER_PK
        PRIMARY KEY(MEMBER_NO);

-- MEMBER_LOG 기본키
ALTER TABLE MEMBER_LOG
    ADD CONSTRAINT MEMBER_LOG_PK
        PRIMARY KEY(MEMBER_LOG_NO);

-- MEMBER_LOG 외래키
ALTER TABLE MEMBER_LOG
    ADD CONSTRAINT MEMBER_LOG_MEMBER_FK
        FOREIGN KEY(ID) REFERENCES MEMBER(ID)
         ON DELETE CASCADE;

-- 트리거 구성

-- 탈퇴자 테이블
DROP TABLE SIGN_OUT_MEMBER;
CREATE TABLE SIGN_OUT_MEMBER
    AS (SELECT MEMBER_NO, ID, PW, NAME, EMAIL
          FROM MEMBER
         WHERE 1 = 2);                              -- DB와 PK 정보는 제외하고 테이블이 복사 된다.

-- PK로 사용할 칼럼 추가
ALTER TABLE SIGN_OUT_MEMBER
    ADD SIGN_OUT_NO NUMBER NOT NULL;

-- 탈퇴일로 사용할 칼럼 추가        
ALTER TABLE SIGN_OUT_MEMBER
    ADD SIGN_OUT DATE DEFAULT SYSDATE NOT NULL;    -- mybatis mapper대신 여기서 DEFAULT SYSDATE 사용 가능

-- 기본키
ALTER TABLE SIGN_OUT_MEMBER
    ADD CONSTRAINT SIGN_OUT_MEMBER_PK
        PRIMARY KEY(SIGN_OUT_NO);

-- 시퀀스
DROP SEQUENCE SIGN_OUT_MEMBER_SEQ;
CREATE SEQUENCE SIGN_OUT_MEMBER_SEQ NOCACHE;



-- MEMBER 테이블의 정보가 삭제되면 SIGN_OUT_MEMBER 테이블로 해당 정보가 저장되는 트리거
CREATE OR REPLACE TRIGGER SIGN_OUT_TRIGGER
    AFTER DELETE
    ON MEMBER
    FOR EACH ROW
BEGIN
    INSERT INTO SIGN_OUT_MEMBER
        (MEMBER_NO, ID, PW, NAME, EMAIL, SIGN_OUT_NO)
    VALUES
        (:OLD.MEMBER_NO, :OLD.ID, :OLD.PW, :OLD.NAME, :OLD.EMAIL, SIGN_OUT_MEMBER_SEQ.NEXTVAL);
END SIGN_OUT_TRIGGER;



















