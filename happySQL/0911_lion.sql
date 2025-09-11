desc emp;
show tables;
desc salgrade;
select * from salgrade;

-- Non-Equi Join
select e.ename, e.sal, s.grade
from emp e, salgrade s 
where e.sal between s.min_salary and s.max_salary; 

select e.ename, e.deptno, d.dname from emp e, dept d
where e.deptno = d.deptno;

select * from dept;



insert into emp values( 
1000, 'kang', 'PRESIDENT', null, STR_TO_DATE ('17-11-1981','%d-%m-%Y'), 10000, null, null);

delete from emp where empno = 1000;
commit;
select * from emp;

select e.ename, e.deptno, d.dname 
from emp e  right outer join   dept d
using(deptno);

select e.ename, e.deptno, d.dname 
from emp e  left outer join   dept d
using(deptno);


select e.ename, e.deptno, d.dname 
from emp e  right outer join   dept d
using(deptno)
union
select e.ename, e.deptno, d.dname 
from emp e  left outer join   dept d
using(deptno);

select * from emp;


select 
e.empno	사원번호, 
e.ename 사원이름,
m.empno 매니저사번,
m.ename  매니저이름
from emp e, emp m 
where e.mgr = m.empno;

-- 한번의 쿼리로 결과를 얻을 수 없을 때가 있다. 
-- smith 라는 사원이 속한 부서의 평균 급여를 알고 싶다.  

select deptno from emp where ename = 'smith';
select avg(sal) from emp where deptno = 20;

select avg(sal) 
from emp 
where deptno = (select deptno from emp where ename = 'smith');

select * from emp 
where sal < (select avg(sal) from emp) ;

SELECT ename, hiredate
FROM emp
WHERE hiredate = (
    SELECT MIN(hiredate)
    FROM emp
);
select avg(sal) from emp;

-- 부서이름이 SALES 인 사원의 정보를 출력 하세요. 
-- 부서이름이 SALES 인 부서 번호 
select * from emp 
where deptno = (select deptno from dept where dname = 'SALES');
-- test 는 가장 작은 단위부터 해봐야함. 
select deptno from dept where dname = 'SALES';

-- 부서별 평균급여는 어떻게 구하나요?
select avg(sal) from emp group by deptno;

-- 서브쿼리의 결과가 single row가 아니고 multi row 인경우!! 
-- = , > , >=, < , <=, <>     single row 
-- in, any, all  multi row  


select avg(sal) from emp group by deptno;

-- 부서별 평균급여보다 더 적게 받는 사원을 구하고 싶다. 
-- sal > 20, 30, 40   이렇게는 안된다.   deptno in (10, 20,30); 
select ename, sal from emp 
where sal < (select avg(sal) from emp group by deptno);
-- 왜 안될까요?? 

-- 서브쿼리의 결과값이 여러개 이므로, = > < 이런 연산자로 수행이 안된다.  
-- in, any ,  all   

-- in = or 의 결합이 된다.   sal = 첫번째row or sal = 두번째row or 
-- sal in (10000, 2850, 3000,5000,3000)
select ename, sal from emp 
where sal in (select max(sal) from emp group by deptno);

select max(sal) from emp group by deptno;

--  in   = or 결합. 
-- any    >any < any, =any <=any (10,20,30)   or 결합 
--   sal > any (1000,2000, 3000)  =  sal > 1000 or  sal > 2000 or sal > 3000 
-- all  sal > all (1000,2000, 3000)  =  sal > 1000 and  sal > 2000 and sal > 3000 

desc emp;
-- = , > , >=, < , <=, <> 
 -- -- =any , >any, >=any, <= any , < any,<>any  
 -- =any   = in 과 같다. 
 
 
 SELECT sal
    FROM emp
    WHERE deptno = 30;
    
    SELECT ename, sal
FROM emp
WHERE sal > ANY (
    SELECT sal
    FROM emp
    WHERE deptno = 30
);

    SELECT ename, sal
FROM emp
WHERE sal > ALL (
    SELECT sal
    FROM emp
    WHERE deptno = 30
);

select * from emp;

select ename, sal, deptno 
from emp 
where sal > (select avg(sal) from emp where deptno = 10);

-- 내가 속한 부서의 평균급여보다 많이 받는 사원 정보만 출력해줏에ㅛ. 
select o.ename, o.sal, o.deptno 
from emp o
where o.sal > (select avg(i.sal) from emp i where i.deptno = o.deptno);


SELECT deptno, empno, ename, sal
FROM emp
WHERE (deptno, sal) IN (
    SELECT deptno, MAX(sal)
    FROM emp
    GROUP BY deptno
);

SELECT deptno, MAX(sal)
    FROM emp
    GROUP BY deptno;
    
select e.deptno, e.empno, e.ename, e.sal 
from emp e
join (select deptno, max(sal) max_sal from emp group by deptno ) m
on e.deptno = m.deptno and e.sal = m.max_sal;

select deptno, max(sal) max_sal from emp group by deptno;

 select e.empno, e.ename
 from emp e
 where exists 
 (select 1 from emp s where s.mgr = e.empno);
 
 select * from emp;
 
 select 1 from emp s where s.mgr = 7499 ;
 
 
 select 1 from emp s where s.mgr = 1000;
 
 select 1;
 
  select e.empno, e.ename
 from emp e
 where not exists 
 (select 1 from emp s where s.mgr = e.empno);
    
-- 테스트용 테이블
CREATE TABLE A (name INT);
CREATE TABLE B (name INT);

INSERT INTO A VALUES (1), (2), (3);
INSERT INTO B VALUES (2), (3), (4);

select * from a;
select * from b;

select * from a
union
select * from b;

select * from a
union all
select * from b;

-- INTERSECT 대체
SELECT DISTINCT A.name 
FROM A 
INNER JOIN B ON A.name = B.name;
-- 결과: 2, 3

-- MINUS 대체
SELECT name FROM A
WHERE name NOT IN (
    SELECT name FROM B
);
-- 결과: 1

SELECT 
    sal,
    ename,
    RANK() OVER(ORDER BY sal DESC) AS ranking
FROM emp;

SELECT 
    deptno,
    ename,
    sal,
    RANK() OVER(PARTITION BY deptno ORDER BY sal DESC) AS dept_rank
FROM emp;

SELECT 
    ROW_NUMBER() OVER(ORDER BY sal DESC) AS row_num,
    ename,
    sal
FROM emp;

SELECT 
    sal,
    ename,
    DENSE_RANK() OVER(ORDER BY sal DESC) AS dense_ranking
FROM emp;