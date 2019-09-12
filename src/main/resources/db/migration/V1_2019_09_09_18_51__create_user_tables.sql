create table if not exists users
(
    id         bigserial primary key,
    email      text not null unique,
    username   text,
    password   text,
    first_name text,
    last_name  text,
    created    timestamp default now()
);

create table if not exists roles
(
    id      bigserial primary key,
    name    text not null unique,
    created timestamp default now()
);

create table if not exists user_roles
(
    id      bigserial primary key,
    user_id bigint references users (id),
    role_id bigint references roles (id)
);

insert into roles (name)
values ('ADMIN'),
       ('USER');

insert into users (email, username, password, first_name, last_name)
values ('sven.reinhardt06@googlemail.com', 'sven.reinhardt06@googlemail.com', '$2a$10$yTA.jPt1FMQaKudk4gPS0eYecsSrZi4vvN6H677PVc/x4.KwNDoh6', 'Sven', 'Reinhardt'),
       ('office@smact.group', 'office@smact.group', '$2a$10$yTA.jPt1FMQaKudk4gPS0eYecsSrZi4vvN6H677PVc/x4.KwNDoh6', 'Sebastian', 'Hausding');

insert into user_roles (user_id, role_id)
values (1,1),
       (1,2),
       (2,2);