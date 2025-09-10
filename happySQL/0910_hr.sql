show tables;
select user();
select * from countries;

desc employees;


SELECT 
    first_name AS 이름,
    hire_date AS 입사일,
    salary AS 급여
FROM employees;

select concat("abc", "def") ;  -- concat() 함수 

SELECT CONCAT(first_name, ' ', last_name) AS full_name
FROM employees;

SELECT 
    first_name,
    salary,
    salary * 12 AS annual_salary
FROM employees;SELECT 
    first_name,
    salary,
    salary * 12 AS annual_salary
FROM employees;

SELECT DISTINCT department_id, job_id
FROM employees;


-- and 가 or 보다 우선순위가 높다. 
SELECT * FROM employees 
WHERE (department_id = 90 OR department_id = 100)
  AND salary >= 10000;
  
 desc employees;
 
 select department_id, count(*) as 부서별직원수, avg(salary) as 평균급여
from employees
group by department_id
having count(*) >=5;

use hr;