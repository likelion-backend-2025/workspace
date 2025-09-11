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

select * from emp where sal between 2000 and 3000;

select * from emp where hiredate between '1981-01-01' and '1981-12-31';

select * from emp where ename = 'FORD';

-- 이름이 F 로 시작하는 사람을 모두 구하고 싶어요. 
-- %  어떤 의미?   % 모든 (*)  0개도 포함됨. 
-- _ 한 자 

-- 이름의 두번째 글자가 O 인 사원을 조회하고 싶어요. 
select * from emp where ename like '_O%';

SELECT * FROM emp WHERE hiredate LIKE '1981%';

select * from emp where comm is not null;

select ename,sal, comm,  sal + comm 급여 from emp;

select comm,  ifnull(comm, 100) from emp;

select ename,sal, comm,  sal + ifnull(comm,100) 급여 from emp;

select ename, coalesce(comm,job,'몰라') from emp;

desc emp;
select empno, ename, job, mgr, sal from emp;
select empno, ename, job, mgr, sal from emp 
order by ename asc;
select empno, ename, job, mgr, sal from emp 
order by ename desc;
select empno, ename, job, mgr, sal from emp 
order by sal asc;
select empno, ename, job, mgr, sal,deptno from emp 
order by 5 asc,2;

select empno, ename, job, mgr, sal,deptno from emp 
order by deptno, 5 desc;

select empno, ename, mgr*18 연봉 from emp
order by 연봉;

select empno, ename, mgr*18 연봉 from emp
order by 3 limit 10;

desc emp;

select upper('hello'), lower('HELLO');
select * from emp;

select * from emp where lower(job)  = 'manager';

select lower(job) from emp;

SELECT SUBSTRING('Hello World', 1, 5);
select concat(ename, '사원님' ) from emp; 

SELECT ROUND(3.7), CEIL(3.2), FLOOR(3.9);

SELECT DATEDIFF('2024-12-31', NOW());

SELECT 
    DATE_ADD(NOW(), INTERVAL 100 DAY);
    
-- 오늘 날짜와 시간을 구하세요. 
SELECT NOW();       -- 현재 날짜와 시간
SELECT CURDATE();   -- 오늘 날짜만
SELECT CURTIME();   -- 현재 시간만

-- 올해가 몇 년도인지, 이번달이 몇 월인지, 오늘이 며칠인지 구해보세요. 
SELECT YEAR(NOW())   AS 올해,
       MONTH(NOW())  AS 이번달,
       DAY(NOW())    AS 오늘일자;

-- 오늘부터 200일 뒤의 날짜를 구하세요.  
SELECT 
    DATE_ADD(NOW(), INTERVAL 200 DAY) as "200일 후";

-- 2025년 8월 13일부터 며칠 지났는지 구하세요. 
SELECT DATEDIFF(CURDATE(), '2025-08-13') AS 수업시작후;

-- 오늘 날짜를 2025년 09월 10일 과 같은 형태로 출력하세요.  
SELECT DATE_FORMAT(CURDATE(), '%Y년 %m월 %d일') AS 오늘날짜;

select DATE_FORMAT(hiredate, '%Y년 %m월 %d일')from emp;

desc emp;
-- 단일행 함수.  
-- 그룹함수 
select count(ename)  from emp;  -- 그룹합수와 일반 컬럼은 같이 나올 수 없다. 
select count(comm) from emp; -- counting할때..  null 아닌것들만 한다. 
select count(*) from emp;

select sum(sal) from emp; 

-- 부서별 급여 평균을 구하고 싶다. 
select deptno, avg(sal) from emp 
group by deptno;  -- 그룹핑에 참여한 컬럼은 컬럽절이 나올 수 있다. 

-- 부서별, 직무별 평균을 구하고 싶다. 
select job, avg(sal) from emp 
group by job;

select deptno,job, avg(sal) from emp 
group by deptno, job
order by 1, 2;

-- 조건  where : 전체에 대한 조건  
-- 조건 having : 그룹핑 된 결과에 대한 조건 

-- 나는 10번 부서를 제외한 모든 사원의 부서별, 직무별로 평균 급여를 알고 싶다.  
-- 단 평균 급여가 3000 이상인 결과는 제외하고 보고 싶어요. 
select deptno,job, avg(sal) 평균급여 from emp 
where deptno != 10
group by deptno, job
having 평균급여 < 3000
order by 1, 2;

desc emp;
desc dept;
select * from emp;
-- 컬럼명 나열. 
select ename, sal, dname from emp, dept;
select ename, sal, dept.deptno, dname from emp, dept;

-- 명확하게 컬럼명 앞에 테이블을 명시하는게 좋다. 
select e.ename, e.sal, d.deptno, d.dname from emp e, dept d;

-- 결과가 어떤거 같은가요?? 

-- 조인 조건이 필요하다.  조인 조건이 없으면,  모든 가능한 쌍이 다 나온다.  Cross Join 
select e.ename, e.sal, d.deptno, d.dname 
from emp e, dept d
where e.deptno = d.deptno;

select * from dept;

select e.ename, e.sal, d.deptno, d.dname 
from emp e natural join dept d;

select e.ename, e.sal, d.deptno, d.dname 
from emp e  join dept d using(deptno);

select e.ename, e.sal, d.deptno, d.dname 
from emp e  join dept d on  e.deptno = d.deptno;

select e.ename, e.sal, d.deptno, d.dname 
from emp e, dept d
where e.deptno = d.deptno
and e.deptno = 20;  -- and를 이용해서 조인조건 외에 다른 조건들을 쓸 수 있다. 

select e.ename, e.sal, d.deptno, d.dname 
from emp e  join dept d on  e.deptno = d.deptno
where e.deptno = 20;