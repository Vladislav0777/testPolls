INSERT INTO users (username, password, enabled)
VALUES ('admin@email.ru', '{bcrypt}$2y$10$tN6dueZYZX5gJ.hpIr/HpelwAs3YN47KnNpKb5V.RmQ5/D/9p7/f2', 1);

INSERT INTO authorities (username, authority)
VALUES ('admin@email.ru', 'ROLE_ADMIN');

INSERT INTO users (username, password, enabled)
VALUES ('alex@email.ru', '{bcrypt}$2y$10$K2ppkuGhnt0dXW2ZqJ2OQOEOFeWJlRIdNFBv.VV2.YU2.6vGUKcXC', 1);

INSERT INTO authorities (username, authority)
VALUES ('alex@email.ru', 'ROLE_USER');

INSERT INTO users (username, password, enabled)
VALUES ('fifo@email.ru', '{bcrypt}$2y$10$1zP4iYy6nCdl8j5ZwUhqxefb.LEdCh/bPseKL45XZ2tqUlXvRXt6K', 1);

INSERT INTO authorities (username, authority)
VALUES ('fifo@email.ru', 'ROLE_USER');

INSERT INTO users (username, password, enabled)
VALUES ('alex2@email.com', '{bcrypt}$2y$10$K2ppkuGhnt0dXW2ZqJ2OQOEOFeWJlRIdNFBv.VV2.YU2.6vGUKcXC', 1);

INSERT INTO authorities (username, authority)
VALUES ('alex2@email.com', 'ROLE_USER');

INSERT INTO users (username, password, enabled)
VALUES ('fifo3@email.rt', '{bcrypt}$2y$10$1zP4iYy6nCdl8j5ZwUhqxefb.LEdCh/bPseKL45XZ2tqUlXvRXt6K', 1);

INSERT INTO authorities (username, authority)
VALUES ('fifo3@email.rt', 'ROLE_USER');