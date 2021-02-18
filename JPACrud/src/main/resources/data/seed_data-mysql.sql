
SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

INSERT INTO Employee (name, department)
VALUES ('Adam', 'HR');
INSERT INTO Employee (name, department)
VALUES ('Angie', 'Administration');
INSERT INTO Employee (name, department)
VALUES ('Angela', 'Security');
       INSERT INTO Employee (name, department)
       VALUES('Amelia', 'HR');
       INSERT INTO Employee (name, department)
       VALUES
       ('Archie', 'Sales');


INSERT INTO users (username, password, enabled)
VALUES ('jack', 'jack', true);
INSERT INTO users (username, password, enabled)
VALUES ('peter', 'peter', true);

INSERT INTO user_roles (username, role)
VALUES ('jack', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('jack', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('peter', 'ROLE_USER');


INSERT INTO User (First_Name, Last_Name, Username, Email, Password)
VALUES
 ('John', 'Doe', 'jdoe', 'jdoe@jdoe.com', 'jdoe'),
 ('Tammy', 'Baldwin', 'tbaldwin', 'tbaldwin@jdoe.com', 'jdoe'),
 ('Michael', 'Bennet', 'mbennet', 'mbennet@jdoe.com', 'jdoe'),
 ('Marsha', 'Blackburn', 'mblackburn', 'mblackburn@jdoe.com', 'jdoe'),
 ('Richard', 'Blumenthal', 'rblumenthal', 'rblumenthal@jdoe.com', 'jdoe'),
 ('John', 'Barasso', 'jbarasso', 'jbarasso@jdoe.com', 'jdoe');