INSERT INTO polls (name, description, start_date, end_date)
VALUES ('Опрос', 'Вы что-то знаете?', '2021-08-13', '2021-08-15');

INSERT INTO questions (pool_id, questionType, name)
VALUES ('1', '0', 'Как дела?');

INSERT INTO answer_type (question_id, text)
VALUES ('1', 'отлично');

INSERT INTO answer_type (question_id, text)
VALUES ('1', 'хорошо');

INSERT INTO answer_type (question_id, text)
VALUES ('1', 'нормально');

INSERT INTO questions (pool_id, questionType, name)
VALUES ('2', '1', 'Чем хотите перекусить?');

INSERT INTO answer_type (question_id, text)
VALUES ('2', 'суп');

INSERT INTO answer_type (question_id, text)
VALUES ('2', 'пельмени');

INSERT INTO answer_type (question_id, text)
VALUES ('2', 'пирог');

INSERT INTO answer_type (question_id, text)
VALUES ('2', 'курица');

INSERT INTO questions (pool_id, questionType, name)
VALUES ('3', '2', 'Как вам приложение?');