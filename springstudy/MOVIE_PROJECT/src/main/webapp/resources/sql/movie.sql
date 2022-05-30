DROP TABLE MOVIE;
CREATE TABLE MOVIE
(
    NO NUMBER NOT NULL PRIMARY KEY,
    TITLE VARCHAR2(100 BYTE),
    GENRE VARCHAR2(100 BYTE),
    DESCRIPTION VARCHAR2(500 BYTE),
    STAR NUMBER
);
DROP SEQUENCE MOVIE_SEQ;
CREATE SEQUENCE MOVIE_SEQ NOCACHE;

INSERT INTO MOVIE VALUES(MOVIE_SEQ.NEXTVAL, '터미네이터', 'SF', '인간과 기계의 대전쟁', 5);
INSERT INTO MOVIE VALUES(MOVIE_SEQ.NEXTVAL, '아바타', 'SF', '판도라 행성으로 향한 인류', 5);
INSERT INTO MOVIE VALUES(MOVIE_SEQ.NEXTVAL, '에일리언', '공포', '에일리언과의 사투', 4.5);
INSERT INTO MOVIE VALUES(MOVIE_SEQ.NEXTVAL, '엑소시스트', '공포', '악마의 말은 듣지 말게', 3.5);
INSERT INTO MOVIE VALUES(MOVIE_SEQ.NEXTVAL, '쇼생크탈출', '드라마', '희망은 너를 자유롭게 하리라', 1);
INSERT INTO MOVIE VALUES(MOVIE_SEQ.NEXTVAL, '집으로', '드라마', '할머니 저 왔어요', 2.5);
INSERT INTO MOVIE VALUES(MOVIE_SEQ.NEXTVAL, '월-E', '애니메이션', '월-E와 매력적인 이브', 1.5);
INSERT INTO MOVIE VALUES(MOVIE_SEQ.NEXTVAL, '센과 치히로의 행방불명', '애니메이션', '금지된 신들의 세계로 온 치히로', 2);
INSERT INTO MOVIE VALUES(MOVIE_SEQ.NEXTVAL, '과속스캔들', '코미디', '스물두살 딸 여섯살 손자', 3);
INSERT INTO MOVIE VALUES(MOVIE_SEQ.NEXTVAL, '엽기적인 그녀', '코미디', '견우야 미안해', 4);
COMMIT;

