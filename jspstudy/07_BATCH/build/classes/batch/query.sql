-- 1. ROWNUM Į�� & ROW_NUMBER() �Լ�
-- 1) ���� ��� ������ �����Ѵ�.
-- 2) ���ĵ� ����� 1,2,3... ���ȣ�� ���δ�.
-- 3) ���ȣ�� 1~3�� ���ڵ带 �����´�.

-- 1) ROWNUM Į��
SELECT B.RN, B.STU_NO, B.NAME, B.AVG
  FROM (SELECT ROWNUM AS RN, A.STU_NO, A.NAME, A.AVG
          FROM (SELECT STU_NO, NAME, AVG
                  FROM STUDENT
                 ORDER BY AVG DESC) A) B
 WHERE B.RN BETWEEN 1 AND 3;

-- 2) ROW_NUMBER() OVER(ORDER BY Į��)
SELECT S.RN, S.STU_NO, S.NAME, S.AVG
  FROM (SELECT ROW_NUMBER() OVER(ORDER BY AVG DESC) AS RN, STU_NO, NAME, AVG
          FROM STUDENT) S
 WHERE S.RN BETWEEN 1 AND 3;



-- 2. RANK() �Լ�
-- 1) ���� ��� ������ ������ �ű��.
-- 2) ������ 1~3�� ���ڵ带 �����´�.

-- 3) RANK() OVER(ORDER BY Į��)
SELECT S.����, S.STU_NO, S.NAME, S.AVG
  FROM (SELECT RANK() OVER(ORDER BY AVG DESC) AS ����, STU_NO, NAME, AVG
          FROM STUDENT) S
 WHERE S.���� BETWEEN 1 AND 3;