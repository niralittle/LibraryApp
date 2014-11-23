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

