create schema homework10 default character set utf8;

use homework10;

create table students
(
id bigint auto_increment primary key,
first_name varchar(255),
last_name varchar(255),
age int
);

create table groupss /*нормальное имя groups создать не дает*/
(
id bigint auto_increment primary key,
group_name varchar(255),
teacher varchar(255)
);

create table group_student
(
student_id bigint not null,
group_id bigint not null,
foreign key (student_id) references students(id),
foreign key (group_id) references groupss(id)
);