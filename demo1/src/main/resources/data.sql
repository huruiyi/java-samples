CREATE DATABASE searchapp CHARACTER SET utf8;

USE searchapp;

-- TABLES --
CREATE TABLE topic (
                       id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(80) NULL DEFAULT NULL,
                       description VARCHAR(5000) NULL DEFAULT NULL,
                       text_field1 VARCHAR(5000) NULL DEFAULT NULL,
                       text_field2 VARCHAR(5000) NULL DEFAULT NULL,
                       created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)
    ENGINE=InnoDB;


INSERT INTO topic (name, description, text_field1, text_field2)
VALUES ('Food', 'Topic containing words related to food', 'Hamburger', 'Pasta'),
       ('Computer Parts', 'Topic containing words related to computer parts', 'CPU', 'GPU'),
       ('Animals', 'Topic containing words related to animals', 'Tiger', 'Lion');