SELECT @@AUTOCOMMIT;

select * from a;
SET AUTOCOMMIT = 0;

insert into a values(50);
commit;


-- 오류없이 클라이언트가 종료될때.. commit 자동 실행 
-- DML 외에 다른 명령이 수행되면, commit 되더라.. 

-- 트랜잭션 개념..  
alter table a 
add column updated_date timestamp null;

select * from a;

desc dept;

insert into dept(deptno, dname, loc) values(5,'like','seoul');
select * from dept;
commit;