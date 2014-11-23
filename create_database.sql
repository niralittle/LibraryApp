DROP TABLE BOOK;
DROP TABLE ORDER_ENTRY;
DROP TABLE LIB_USER;
DROP TABLE OE_BOOK;

CREATE TABLE BOOK(
  id INTEGER NOT NULL
  GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  title VARCHAR(40),
  authors VARCHAR(60),
  description LONG VARCHAR,
  rating SMALLINT,
  numberOfPages INT,
  category VARCHAR(30)
);

CREATE TABLE LIB_USER(
  id INTEGER NOT NULL
  GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  isAdmin SMALLINT,
  login VARCHAR(15),
  password VARCHAR(15)
);


CREATE TABLE ORDER_ENTRY(
  id INTEGER NOT NULL
  GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  userId INTEGER,
  waitingSince DATE
);

CREATE TABLE OE_BOOK (
  entryId INTEGER,
  bookId INTEGER
);

INSERT INTO BOOK(TITLE, AUTHORS, DESCRIPTION, RATING, NUMBEROFPAGES, CATEGORY)
  VALUES ('The Education of Henry Adams', 'Henry Adams', 'Printed privately in 1907 and published to wide acclaim shortly after the authors death', 10, 560, 'An Autobiography');
INSERT INTO BOOK(TITLE, AUTHORS, DESCRIPTION, RATING, NUMBEROFPAGES, CATEGORY)
  VALUES ('Life of Pi ', 'William James', 'The Varieties of Religious Experience was an immediate bestseller upon its publication in June 1902. ', 9, 640, 'A Study in Human Nature');
INSERT INTO BOOK(TITLE, AUTHORS, DESCRIPTION, RATING, NUMBEROFPAGES, CATEGORY)
  VALUES ('Up from Slavery', 'Wally Lamb', 'Description ', 9, 456, 'A Study in Human Nature');
INSERT INTO BOOK(TITLE, AUTHORS, DESCRIPTION, RATING, NUMBEROFPAGES, CATEGORY)
  VALUES ('The Civil War', 'Anita Diamant', 'Description ', 7, 124, 'A Study in Human Nature');
INSERT INTO BOOK(TITLE, AUTHORS, DESCRIPTION, RATING, NUMBEROFPAGES, CATEGORY)
  VALUES ('The Guns of August', 'Mitch Albom', 'Description ', 5, 345, 'A Study in Human Nature');
INSERT INTO BOOK(TITLE, AUTHORS, DESCRIPTION, RATING, NUMBEROFPAGES, CATEGORY)
  VALUES ('The Souls of Black Folk', 'Marion Zimmer Bradley', 'Description ', 8, 456, 'A Study in Human Nature');
INSERT INTO BOOK(TITLE, AUTHORS, DESCRIPTION, RATING, NUMBEROFPAGES, CATEGORY)
  VALUES ('Ideas And Opinions', 'William James', 'Description ', 6, 1546, 'A Study in Human Nature');
INSERT INTO BOOK(TITLE, AUTHORS, DESCRIPTION, RATING, NUMBEROFPAGES, CATEGORY)
  VALUES ('Eminent Victorians', 'Rowling', 'Description ', 7, 785, 'A Study in Human Nature');
INSERT INTO BOOK(TITLE, AUTHORS, DESCRIPTION, RATING, NUMBEROFPAGES, CATEGORY)
  VALUES ('Darkness Visible', 'John Irving', 'Description ', 10, 424, 'A Study in Human Nature');
INSERT INTO BOOK(TITLE, AUTHORS, DESCRIPTION, RATING, NUMBEROFPAGES, CATEGORY)
  VALUES ('The Gnostic Gospels', 'Mitch Albom', 'Description ', 5, 145, 'A Study in Human Nature');
INSERT INTO BOOK(TITLE, AUTHORS, DESCRIPTION, RATING, NUMBEROFPAGES, CATEGORY)
  VALUES ('The Hobbit', 'Tolkien', 'Description ', 3, 786, 'A Study in Human Nature');
INSERT INTO BOOK(TITLE, AUTHORS, DESCRIPTION, RATING, NUMBEROFPAGES, CATEGORY)
  VALUES ('Wuthering Heights', 'William James', 'Description ', 4, 754, 'A Study in Human Nature');
INSERT INTO BOOK(TITLE, AUTHORS, DESCRIPTION, RATING, NUMBEROFPAGES, CATEGORY)
  VALUES ('The Notebook', 'Nicholas Sparks', 'Description ', 3, 135, 'A Study in Human Nature');
INSERT INTO BOOK(TITLE, AUTHORS, DESCRIPTION, RATING, NUMBEROFPAGES, CATEGORY)
  VALUES ('The English Patient', 'Wally Lamb', 'Description ', 9, 456, 'A Study in Human Nature');
INSERT INTO BOOK(TITLE, AUTHORS, DESCRIPTION, RATING, NUMBEROFPAGES, CATEGORY)
  VALUES ('The Good Earth', 'Anita Diamant', 'Description ', 7, 124, 'A Study in Human Nature');
INSERT INTO BOOK(TITLE, AUTHORS, DESCRIPTION, RATING, NUMBEROFPAGES, CATEGORY)
  VALUES ('The Bourne Identity', 'Mitch Albom', 'Description ', 5, 345, 'A Study in Human Nature');
INSERT INTO BOOK(TITLE, AUTHORS, DESCRIPTION, RATING, NUMBEROFPAGES, CATEGORY)
  VALUES ('In The Skin Of A Lion', 'Marion Zimmer Bradley', 'Description ', 8, 456, 'A Study in Human Nature');
INSERT INTO BOOK(TITLE, AUTHORS, DESCRIPTION, RATING, NUMBEROFPAGES, CATEGORY)
  VALUES ('Of Mice And Men', 'William James', 'Description ', 6, 1546, 'A Study in Human Nature');
INSERT INTO BOOK(TITLE, AUTHORS, DESCRIPTION, RATING, NUMBEROFPAGES, CATEGORY)
  VALUES ('Not Wanted On The Voyage', 'Rowling', 'Description ', 7, 785, 'A Study in Human Nature');
INSERT INTO BOOK(TITLE, AUTHORS, DESCRIPTION, RATING, NUMBEROFPAGES, CATEGORY)
  VALUES ('The World According To Garp', 'John Irving', 'Description ', 10, 424, 'A Study in Human Nature');
INSERT INTO BOOK(TITLE, AUTHORS, DESCRIPTION, RATING, NUMBEROFPAGES, CATEGORY)
  VALUES ('Les Miserables', 'Mitch Albom', 'Description ', 5, 145, 'A Study in Human Nature');
INSERT INTO BOOK(TITLE, AUTHORS, DESCRIPTION, RATING, NUMBEROFPAGES, CATEGORY)
  VALUES ('War and Peace', 'Tolstoy', 'Description ', 3, 786, 'A Study in Human Nature');
INSERT INTO BOOK(TITLE, AUTHORS, DESCRIPTION, RATING, NUMBEROFPAGES, CATEGORY)
  VALUES ('The Fountainhead', 'William James', 'Description ', 4, 754, 'A Study in Human Nature');
INSERT INTO BOOK(TITLE, AUTHORS, DESCRIPTION, RATING, NUMBEROFPAGES, CATEGORY)
  VALUES ('The Thorn Birds', 'Nicholas Sparks', 'Description ', 3, 135, 'A Study in Human Nature');

