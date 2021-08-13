create table if not exists users
(
    username varchar(60) not null
    primary key,
    password varchar(60) not null
);


create table if not exists authorities
(
    username  varchar(60) not null
    primary key,
    authority varchar(60) not null
);

create table if not exists users_authorities
(
    user_username      varchar(60) not null,
    authority_username varchar(60) not null,
    constraint FK_users_authorities
    foreign key (user_username) references users (username),
    constraint FK_users_authorities
    foreign key (authority_username) references authorities (username)
);