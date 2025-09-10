show tables;
select * from emp;

-- SELECT 컬럼명(, 컬럼명) FROM 테이블명(, 테이블명) ;
-- SELECT 2+3 FROM dual;   오라클 

select 4+5;  
select version();   -- ()  함수 DBMS 들이 편하게 사용하라고 함수를 미리 제공 하고 있다. 

-- SQL  표준이 정해져 있음.   DBMS 들 마다 조금씩 다른 부분이 있더라.  
select user();

-- 여러줄에 쿼리를 사용하는 것도 문제는 없다.  문장과 문장은 ;  으로 구분된다. 
select 
3
 +
 5 
 ;
 
 select current_date;

