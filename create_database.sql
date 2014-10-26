DROP TABLE BOOK;
DROP TABLE ORDER_ENTRY;
DROP TABLE LIB_USER;
DROP TABLE OE_BOOK;
DROP TABLE USER_OE;
DROP TABLE OE_STATUS;

CREATE TABLE BOOK( 
  id INTEGER PRIMARY KEY NOT NULL
    GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  title VARCHAR(40), 
  authors VARCHAR(60), 
  description LONG VARCHAR, 
  rating SMALLINT, 
  numberOfPages INT,
  category VARCHAR(30));


CREATE TABLE ORDER_ENTRY(
  id INTEGER PRIMARY KEY NOT NULL
    GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  userId INTEGER,
  waitingSince DATE,
  dueDate DATE,
  status INT
);


CREATE TABLE LIB_USER(
  id INTEGER PRIMARY KEY NOT NULL
  GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  isAdmin SMALLINT,
  login VARCHAR(15),
  password VARCHAR(15)
);
 

CREATE TABLE ENTRY_BOOKS(
  entryId INTEGER,
  bookId INTEGER
);


CREATE TABLE USER_OE (
  userId INTEGER,
  oeId INTEGER
);

CREATE TABLE OE_STATUS(
  id SMALLINT,
  name VARCHAR(20)
);