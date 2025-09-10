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
 
 show tables;
 
 desc emp;   --  테이블의 구조를 확인함. 
 
 select * from emp;
 select job from emp;
 select distinct job from emp;
select distinct job, empno from emp;
  
select job, empno from emp;
select empno 사번, ename as "사원 이름", mgr as 매니저 from emp;

select ename, sal * 18 "연봉" from emp;
  
select ename, sal * 18 from emp;
  
select ename from emp;

-- 조건에 만족하는 데이터만 보고 싶다면??  WHERE  
select * from emp where deptno = 10; 
select * from emp where job='manager'; -- ''안에 들어와 있는 것들..  값 에 대해서 다른 DB는 대소문자 구분함. 
select * from emp where sal > 2500;
select * from emp where sal > 2500 and job = 'manager';


-- in  은 or 결합 .
select * from emp 
where deptno = 10 or deptno = 20 or deptno = 30;

select * from emp 
where deptno in (10, 20, 30) ;

select * from emp 
where deptno not in (10,40);




