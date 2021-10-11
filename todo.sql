create database todo;
use ToDo;
select * from tasks;

create table Tasks (
   id INT NOT NULL auto_increment,
   task_name VARCHAR(40) default NULL,
   task_desc  VARCHAR(100) default NULL,
   due_date    datetime default GETDATE() NULL,
   PRIMARY KEY (id)
);

alter table tasks
alter column due_date
set default GETDATE();

      
insert into tasks (task_name,task_desc,due_date)
select 'Get Dressed','Put your pants on!','2021/1/2';
insert into tasks (task_name,task_desc,due_date)
select 'Wake up','Turn off the alarm','2021/1/1';
insert into tasks (task_name,task_desc,due_date)
select 'Take a shower','use the soap','2021/1/3';