drop table if exists  gift_certificate cascade;
drop table if exists  tag cascade;
drop table if exists certificate_tag cascade;
drop table if exists contract cascade;
drop table if exists users cascade;
drop table if exists roles cascade;

create table tag (

                     t_id serial,
                     t_name varchar (30),
                     primary key (t_id)

);


create table gift_certificate (

                                  g_id serial,
                                  g_name varchar(100),
                                  g_description varchar (200),
                                  g_price decimal,
                                  g_duration int,
                                  g_create_date date,
                                  g_last_update date,


                                  primary key(g_id)

);

create table roles (
                       r_id serial,
                       r_name varchar(100) not null,
                       primary key (r_id)
);

create table users(
                      u_id    serial,
                      u_name varchar (50),
                      password varchar(255),
                      u_email varchar(50),
                      active boolean,
                      role_id serial,
                      primary key (u_id),
                      foreign key (role_id) references roles (r_id)
);



create table certificate_tag (

                                 c_t_id serial,
                                 certificate_id int,
                                 tag_id int,

                                 primary key(c_t_id),
                                 foreign key(certificate_id) references gift_certificate(g_id),
                                 foreign key(tag_id) references tag (t_id)



);

create table contract (

                          c_id serial,
                          c_price decimal,
                          c_date date,
                          gift_id int,
                          tag_id int,
                          user_id int,

                          primary key (c_id),
                          foreign key (gift_id) references gift_certificate(g_id),
                          foreign key (user_id) references users(u_id),
                          foreign key (tag_id) references tag(t_id)

);



with gift_certificate_insert as (
    insert into gift_certificate (g_name, g_description, g_price, g_duration, g_create_date, g_last_update)
        values ('gym', 'sport gym', 100.0 , 30, '5.12.2020', '7.12.2020')
        returning g_id as g_id
),

     tag_insert as (
         insert into tag (t_name)
             values ('Good ivent')
             returning t_id as t_id
     )

insert into certificate_tag (certificate_id, tag_id)
values (
           (select g_id from gift_certificate_insert), (select t_id from tag_insert)
       );


with gift_certificate_insert as (
    insert into gift_certificate (g_name, g_description, g_price, g_duration, g_create_date, g_last_update)
        values ('airtube', 'airtube flying', 85.0 , 30, '6.12.2020', '5.12.2021')
        returning g_id as g_id
),

     tag_insert as (
         insert into tag (t_name)
             values ('Ivent consult')
             returning t_id as t_id
     )

insert into certificate_tag (certificate_id, tag_id)
values (
           (select g_id from gift_certificate_insert), (select t_id from tag_insert)
       );


with gift_certificate_insert as (
    insert into gift_certificate (g_name, g_description, g_price, g_duration, g_create_date, g_last_update)
        values ('horse riding', 'horse riding ith instructor', 100.0 , 30, '7.10.2021', '5.12.2021')
        returning g_id as g_id
),

     tag_insert as (
         insert into tag (t_name)
             values ('Fun zone company')
             returning t_id as t_id
     )

insert into certificate_tag (certificate_id, tag_id)
values (
           (select g_id from gift_certificate_insert), (select t_id from tag_insert)
       );

with gift_certificate_insert as (
    insert into gift_certificate (g_name, g_description, g_price, g_duration, g_create_date, g_last_update)
        values ('swimming pool', 'swimming pool', 98.0 , 25, '8.11.2021', '5.12.2021')
        returning g_id as g_id
),

     tag_insert as (
         insert into tag (t_name)
             values ('Super celebrate')
             returning t_id as t_id
     )

insert into certificate_tag (certificate_id, tag_id)
values (
           (select g_id from gift_certificate_insert), (select t_id from tag_insert)
       );

with gift_certificate_insert as (
    insert into gift_certificate (g_name, g_description, g_price, g_duration, g_create_date, g_last_update)
        values ('skyserfing', 'skyserfing', 111.0 , 25, '24.11.2021', '5.12.2021')
        returning g_id as g_id
),

     tag_insert as (
         insert into tag (t_name)
             values ('Wiz Orn prod')
             returning t_id as t_id
     )

insert into certificate_tag (certificate_id, tag_id)
values (
           (select g_id from gift_certificate_insert), (select t_id from tag_insert)
       );


insert into roles (r_name) values ('ROLE_ADMIN'), ('ROLE_USER');

insert into users (u_name, password, u_email, active,role_id)
values ('anton', '$2a$12$cKav5rRq4F5XYTnAxTSvneZRzMktGkYr8Nf6Wx04hKY94LJvFfQ0O' , 'anton.reqfor@gmail.com', true, 1),
       ('vasa', '$2a$12$cKav5rRq4F5XYTnAxTSvneZRzMktGkYr8Nf6Wx04hKY94LJvFfQ0O','vasa@tut.by', true, 2);

insert into contract (c_price, c_date, gift_id, tag_id, user_id) values
(200, '2020-10-10', 1, 1, 1),
(200, '2020-10-10', 1, 3, 1),
(200, '2020-10-10', 2, 1, 1),
(200, '2020-10-10', 2, 3, 1),
(300, '2020-10-10', 1, 4, 1),
(500, '2020-10-10', 1, 4, 1);


insert into certificate_tag (certificate_id, tag_id) values
(4, 1),
(4, 3);