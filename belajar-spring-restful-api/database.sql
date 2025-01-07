CREATE DATABASE belajar_spring_restful_api;

USE belajar_spring_restful_api;

CREATE TABLE users (
  username VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  name VARCHAR(100) NOT NULL,
  token VARCHAR(100),
  token_expired_at BIGINT,
  PRIMARY KEY (username),
  UNIQUE (token)
) ENGINE INNODB;

SELECT * FROM users;

DESC users;

CREATE TABLE contacts (
  id VARCHAR(100) NOT NULL,
  username VARCHAR(100) NOT NULL,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100),
  email VARCHAR(100),
  phone VARCHAR(100),
  PRIMARY KEY (id),
  FOREIGN KEY fk_contacts_users (username) REFERENCES users(username)
)


SELECT * FROM contacts;

DESC contacts;

CREATE TABLE addresses (
  id VARCHAR(100) NOT NULL,
  contact_id VARCHAR(100) NOT NULL,
  street VARCHAR(100),
  city VARCHAR(100),
  province VARCHAR(100),
  country VARCHAR(100) NOT NULL,
  postal_code VARCHAR(100),
  PRIMARY KEY (id),
  FOREIGN KEY fk_addresses_contacts (contact_id) REFERENCES contacts(id)
) ENGINE INNODB;

SELECT * FROM addresses;

DESC addresses;