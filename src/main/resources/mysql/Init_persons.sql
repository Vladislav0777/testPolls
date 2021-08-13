create table if not exists persons
(
    id       int auto_increment
    primary key,
    email varchar(60) null,
    constraint FK_persons_username
    foreign key (email) references users (username)
);