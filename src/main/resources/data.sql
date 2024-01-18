insert into user_details(id,name,birth_date)
values (101,'Zidane', current_date()), (202,'Ronaldhino', current_date()), (303,'Asprilla', current_date());

insert into post(id,description, user_id)
values (2001, 'Learn AWS', 101),
	(3001, 'Learn Google', 101),
	(4001, 'Learn Ruby', 202);