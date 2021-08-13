create table if not exists polls
(
    id          int auto_increment
    primary key,
    name        varchar(60) not null,
    description varchar(60) null,
    start_date  date         not null,
    end_date    date         null
);

create table if not exists questions
(
    id           int auto_increment
    primary key,
    pool_id      int          null,
    questionType int          null,
    name         varchar(255) not null,
    constraint FK_questions
    foreign key (pool_id) references polls (id)
);

create table if not exists answer_type
(
    id          int auto_increment
    primary key,
    question_id int          null,
    text        varchar(255) not null,
    constraint FK_answer_type
    foreign key (question_id) references questions (id)
);