--회원 테이블 생성 
CREATE TABLE tbl_member(
memId varchar2(10) primary key,
memPw varchar2(20) not null,
memName varchar2(50) not null,
memAge number not null,
memPnum varchar2(20) not null,
memAddr varchar2(30) not null,
memHeight number not null,
memWeight number not null
);

select *
from tbl_member;

commit;

-- 강사 테이블
create table tbl_teacher(
tId varchar2(10) primary key,
tPw varchar2(20) not null,
tName varchar2(50) not null,
tPnum varchar2(20) not null,
hire_date DATE
);

select *
from tbl_teacher;

commit;

CREATE TABLE tbl_courses (
    course_id NUMBER PRIMARY KEY,--수업아이디
    course_name VARCHAR2(100) NOT NULL,--수업명
    t_id VARCHAR2(10),--강사아이디
    schedule VARCHAR2(100), -- 수업 일정
    capacity NUMBER, 
    FOREIGN KEY (t_id) REFERENCES tbl_teacher(tId) -- 강사 아이디 외래키로 설정
);

ALTER TABLE tbl_courses
ADD time VARCHAR2(10);

ALTER TABLE tbl_courses
ADD target VARCHAR2(50);

select *
from tbl_courses;

--관리자
create  table tbl_admin(
admin_id varchar2(10) primary key,
admin_pw varchar2(10) not null);

ALTER TABLE tbl_admin
ADD admin_name VARCHAR2(20);

select *
from tbl_admin;

commit;

ALTER TABLE tbl_courses MODIFY time VARCHAR2(30);
--
insert into tbl_teacher
values('Ggil01','gildong01','김길동','124-124','18-12-01');

insert into tbl_teacher
values('Ghong01','Ghong01','홍길동','384-7139','25-03-25');

insert into tbl_courses
values(12,'초급수영반','Ggil01','수,목,금',30,'13:50~14:50','초급');

insert into tbl_courses
values(7,'고급수영반','Ggil01','토,일',30,'08:30~09:30','고급');


SELECT c.course_id, c.course_name,c.schedule,c.time,c.target,c.capacity
FROM tbl_courses c
JOIN tbl_teacher t ON c.t_id = t.tId
WHERE t.tName = '?';

select *
from tbl_courses;


--수강신청 테이블
create table enrollments(
 enrollment_id varchar2(50) primary key,
 MEM_ID VARCHAR2(10),
 course_id    NUMBER,
 enrollment_date TIMESTAMP,
 status VARCHAR(50),
 FOREIGN KEY (MEM_ID) REFERENCES tbl_member(MEMID),
 FOREIGN KEY (course_id) REFERENCES tbl_courses(course_id)
);

--장바구니 테이블
CREATE TABLE CART (
    CART_ID NUMBER  PRIMARY KEY, -- 장바구니 ID
    MEMBER_ID VARCHAR2(50) NOT NULL, -- 회원 ID (Foreign Key)
    COURSE_ID NUMBER NOT NULL, -- 수업 ID (Foreign Key)
    ADDED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 추가된 날짜,
    FOREIGN KEY (MEMBER_ID) REFERENCES tbl_member(MEMID),
    FOREIGN KEY (COURSE_ID) REFERENCES tbl_courses(course_id)
);

drop table tbl_course;

--회원 테이블 생성 
CREATE TABLE tbl_member(
memId varchar2(10) primary key,
memPw varchar2(20) not null,
memName varchar2(50) not null,
memAge number not null,
memPnum varchar2(20) not null,
memAddr varchar2(30) not null,
memHeight number not null,
memWeight number not null
);

select *
from tbl_member;

commit;

-- 강사 테이블
create table tbl_teacher(
tId varchar2(10) primary key,
tPw varchar2(20) not null,
tName varchar2(50) not null,
tPnum varchar2(20) not null,
hire_date DATE
);

select *
from tbl_teacher;

commit;

CREATE TABLE tbl_courses (
    course_id NUMBER PRIMARY KEY,--수업아이디
    course_name VARCHAR2(100) NOT NULL,--수업명
    t_id VARCHAR2(10),--강사아이디
    schedule VARCHAR2(100), -- 수업 일정
    capacity NUMBER, 
    FOREIGN KEY (t_id) REFERENCES tbl_teacher(tId) -- 강사 아이디 외래키로 설정
);
select *
from tbl_courses;
ALTER TABLE tbl_courses
ADD time VARCHAR2(10);

ALTER TABLE tbl_courses
ADD target VARCHAR2(50);

select *
from tbl_courses;

drop table tbl_adminer;
--관리자
create  table tbl_admin(
admin_id varchar2(10) primary key,
admin_pw varchar2(10) not null);

ALTER TABLE tbl_admin
ADD admin_name VARCHAR2(20);

select *
from tbl_admin;

commit;

ALTER TABLE tbl_courses MODIFY time VARCHAR2(30);
--
insert into tbl_teacher
values('Ggil01','gildong01','김길동','124-124','18-12-01');

insert into tbl_teacher
values('Ghong01','Ghong01','홍길동','384-7139','25-03-25');

insert into tbl_courses
values(12,'초급수영반','Ggil01','수,목,금',30,'13:50~14:50','초급');

insert into tbl_courses
values(7,'고급수영반','Ggil01','토,일',30,'08:30~09:30','고급');


SELECT c.course_id, c.course_name,c.schedule,c.time,c.target,c.capacity
FROM tbl_courses c
JOIN tbl_teacher t ON c.t_id = t.tId
WHERE t.tName = '?';

select *
from tbl_courses;

ALTER TABLE tbl_teacher
ADD Age INT;