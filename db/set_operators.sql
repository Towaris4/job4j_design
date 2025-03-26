CREATE TABLE movie
(
    id       SERIAL PRIMARY KEY,
    name     text,
    director text
);

CREATE TABLE book
(
    id     SERIAL PRIMARY KEY,
    title  text,
    author text
);



SELECT name
FROM movie
intersect
SELECT title
FROM book;

SELECT title
FROM book
except
SELECT name
FROM movie;

(SELECT name FROM movie
except
SELECT title FROM book)
union
(SELECT titlem FROM book
except
SELECT name FROM movie);
