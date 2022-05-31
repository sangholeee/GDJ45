-- 첨부 게시판

-- 1. 단일 첨부
/*
    게시글1 - 첨부1
    게시판 : 제목, 내용, 작성자, 첨부파일명, 경로
*/

-- 2. 다중 첨부
/*
    게시글1 - 첨부1
            - 첨부2
            
    게시판 : 제목, 내용, 작성자, 첨부파일명1, 경로1
             제목, 내용, 작성자, 첨부파일명2, 경로2
             (X)
             ------------------------------------------
    게시판 : 번호(PK), 제목, 내용, 작성자
    첨부   : 첨부번호, 게시글번호(FK), 첨부파일명, 경로
*/

/* 
    동일 테이블 뿐만 아니라 DB 내에 같은 COLUMN명이 있으면 안된다. 

    첨부 파일명 바꾸는 이유
    1. 인코딩
    2. 중복 방지! 
*/

DROP TABLE FILE_ATTACH;
CREATE TABLE FILE_ATTACH
(
    FILE_ATTACH_NO NUMBER NOT NULL,    -- PK
    PATH VARCHAR2(300 BYTE),
    ORIGIN VARCHAR2(300 BYTE),
    SAVED VARCHAR2(40 BYTE),           -- UUID로 바뀐 id 길이 + 확장자 길이
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
            ON DELETE CASCADE;   -- 게시글 삭제하면 첨부 내역이 함께 삭제





