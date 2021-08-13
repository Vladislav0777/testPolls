create table if not exists answer
(
    id          int auto_increment
    primary key,
    person_id   int not null,
    poll_id     int not null,
    question_id int not null,
    constraint FK_answer_questions
    foreign key (question_id) references questions (id),
    constraint FK_answer_polls
    foreign key (poll_id) references polls (id),
    constraint FK_answer_persons
    foreign key (person_id) references persons (id)
);

create table if not exists answers_answer_variants
(
    answer_id         int not null,
    answer_variant_id int not null,
    constraint FK_answers_answer_variants_answer
    foreign key (answer_id) references answer (id),
    constraint FK_answers_answer_variants_answer_variants
    foreign key (answer_variant_id) references answer_variants (id)
);
