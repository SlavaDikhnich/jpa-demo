insert into course(id, name, created_Date, last_Updated_Date, is_deleted) values (10001, 'Jpa in many steps', sysdate(), sysdate(), false);
insert into course(id, name, created_Date, last_Updated_Date, is_deleted) values (10002, 'Jpa in 50 steps', sysdate(), sysdate(), false);
insert into course(id, name, created_Date, last_Updated_Date, is_deleted) values (10003, 'Jpa in 100 steps', sysdate(), sysdate(), false);
insert into course(id, name, created_date, last_updated_date, is_deleted)
values(10004,'Jpa in 100 Steps', sysdate(), sysdate(), false);
insert into course(id, name, created_date, last_updated_date, is_deleted)
values(10005,'Dummy2', sysdate(), sysdate(), false);
insert into course(id, name, created_date, last_updated_date, is_deleted)
values(10006,'Dummy3', sysdate(), sysdate(), false);
insert into course(id, name, created_date, last_updated_date, is_deleted)
values(10007,'Dummy4', sysdate(), sysdate(), false);
insert into course(id, name, created_date, last_updated_date, is_deleted)
values(10008,'Dummy5', sysdate(), sysdate(), false);


insert into passport(id,number)
values(40001,'E123456');
insert into passport(id,number)
values(40002,'N123457');
insert into passport(id,number)
values(40003,'L123890');

insert into student(id,name,passport_id)
values(20001,'Ranga',40001);
insert into student(id,name,passport_id)
values(20002,'Adam',40002);
insert into student(id,name,passport_id)
values(20003,'Jane',40003);

insert into review(id, rating, description, course_id)
values(50001, 'ZERO', 'Great Course', 10001);
insert into review(id, rating, description, course_id)
values(50002, 'FOUR', 'Wonderful Course', 10001);
insert into review(id, rating, description, course_id)
values(50003, 'FIVE', 'Awesome Course', 10003);

insert into student_course(student_id,course_id)
values(20001,10001);
insert into student_course(student_id,course_id)
values(20002,10001);
insert into student_course(student_id,course_id)
values(20003,10001);
insert into student_course(student_id,course_id)
values(20001,10003);
