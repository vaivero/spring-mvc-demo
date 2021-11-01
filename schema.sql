DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;

CREATE TABLE authors(
                        id BIGINT GENERATED ALWAYS AS IDENTITY,
                        name VARCHAR (80) NOT NULL,
                        PRIMARY KEY(id)
);

CREATE TABLE books(
                      name VARCHAR(120) NOT NULL,
                      isbn VARCHAR(20) NOT NULL,
                      author_id BIGINT NOT NULL,
                      PRIMARY KEY(isbn),
                      CONSTRAINT fk_author
                          FOREIGN KEY(author_id)
                              REFERENCES authors(id)
);

ALTER TABLE books ADD CONSTRAINT uq_books_isbn UNIQUE (isbn);

INSERT INTO authors (name) values ('Frank Herbert');
INSERT INTO authors (name) values ('Jane Austin');
INSERT INTO authors (name) values ('Marry Shelley');
INSERT INTO authors (name) values ('John Tolkien');
INSERT INTO authors (name) values ('Robert Sheckley');
INSERT INTO authors (name) values ('Aldous Huxley');
INSERT INTO authors (name) values ('William Golding');
INSERT INTO authors (name) values ('Anton Chekhov');

INSERT INTO books (name, isbn, author_id) VALUES ('Dune', '9780441013593', 1);
INSERT INTO books (name, isbn, author_id) VALUES ('Dune Messiah', '9788020503831', 1);
INSERT INTO books (name, isbn, author_id) VALUES ('Children of Dune', '9780575021907', 1);

INSERT INTO books (name, isbn, author_id) VALUES ('Pride and Prejudice', '9780141439518', 2);
INSERT INTO books (name, isbn, author_id) VALUES ('Sense and Sensibility', '9780198807452', 2);
INSERT INTO books (name, isbn, author_id) VALUES ('Emma', '9780140430103', 2);

INSERT INTO books (name, isbn, author_id) VALUES ('Frankenstein', '9780194216425', 3);

INSERT INTO books (name, isbn, author_id) VALUES ('Lord of The Rings', '9780261103689', 4);
INSERT INTO books (name, isbn, author_id) VALUES ('The Silmarillion', '9780007136605', 4);

INSERT INTO books (name, isbn, author_id) VALUES ('Immortality inc', '9780140471380', 5);
INSERT INTO books (name, isbn, author_id) VALUES ('A Collection of Robert Sheckley Science Fiction', '9781612039831', 5);

INSERT INTO books (name, isbn, author_id) VALUES ('Brave New World', '9780060120375', 6);
INSERT INTO books (name, isbn, author_id) VALUES ('Island', '0-06-008549-5', 6);

INSERT INTO books (name, isbn, author_id) VALUES ('The Lord of The Flies', '9780399529207', 7);

INSERT INTO books (name, isbn, author_id) VALUES ('Три Сестры', '9780812452679', 8);
INSERT INTO books (name, isbn, author_id) VALUES ('Вишнёвый Сад', '9780393338164', 8);
INSERT INTO books (name, isbn, author_id) VALUES ('Дядя Ваня', '9780802131515', 8);
INSERT INTO books (name, isbn, author_id) VALUES ('Чайка', '9781421807041', 8);
INSERT INTO books (name, isbn, author_id) VALUES ('Безотцовщина', '9780910278935', 8);
INSERT INTO books (name, isbn, author_id) VALUES ('Шалость', '9785040173754', 8);