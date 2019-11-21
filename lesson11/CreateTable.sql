drop table tasks;
drop table users;

create table users(user_id integer not null
                  ,user_name varchar2(200) not null
                  ) tablespace training;

alter table users add (
  constraint pk_users_id
    primary key (user_id) using index tablespace training);

begin
  insert into training.users(user_id, user_name)
  values(1, 'Иван');
  
  insert into training.users(user_id, user_name)
  values(2, 'Петя');
  
  insert into training.users(user_id, user_name)
  values(3, 'Митя');
  
  insert into training.users(user_id, user_name)
  values(4, 'Таня');
  
  insert into training.users(user_id, user_name)
  values(5, 'Ольга');
  
  commit;
end;
/

create table tasks (task_id integer not null
                   ,task_name varchar2(200) not null
                   ,author_id integer not null
                   ,executor_id integer not null
                   ,description varchar2(4000) not null
                   ,status varchar2(100) not null
                   ) tablespace training;

alter table tasks add (
  constraint pk_tasks_id
    primary key (task_id) using index tablespace training);

alter table tasks
   add constraint fk_tasks_author_id foreign key (author_id)
      references users (user_id);
      
alter table tasks
   add constraint fk_tasks_executor_id foreign key (executor_id)
      references users (user_id);
