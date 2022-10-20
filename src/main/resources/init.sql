
drop table if exists USERS;
drop table if exists ROLE;

create table ROLE
(
    id       BIGINT  NOT NULL AUTO_INCREMENT,
    roleName VARCHAR(50) NOT NULL UNIQUE ,
    PRIMARY KEY (id)
);

create table USERS
(
    user_id   BIGINT       NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(256) NOT NULL,
    lastName  VARCHAR(256) NOT NULL,
    username  VARCHAR(256) NOT NULL UNIQUE,
    email     VARCHAR(256) NOT NULL UNIQUE,
    password  VARCHAR(256) NOT NULL,
    birthday  DATE         NOT NULL,
    role_id   BIGINT       NOT NULL,
    PRIMARY KEY(user_id), FOREIGN KEY (role_id) REFERENCES ROLE (id)
);

INSERT INTO ROLE (roleName) VALUES('admin');
INSERT INTO ROLE (roleName) VALUES('user');

insert into USERS (firstName, lastName, username, email, password, birthday, role_id) values ('admin', 'admin', 'admin', 'admin@gmail.com', '$2a$10$AJcM2ZlYGWSXLlyqKrC2YOcgv.JgxMDitm2lX4oonfxj3ZTZ1E6J6', '2001-07-09', 1);
insert into USERS (firstName, lastName, username, email, password, birthday, role_id) values ('Ada', 'Lovelace', 'user', 'user@gmail.com', '$2a$10$AJcM2ZlYGWSXLlyqKrC2YOcgv.JgxMDitm2lX4oonfxj3ZTZ1E6J6', '1815-12-10', 2);
insert into USERS (firstName, lastName, username, email, password, birthday, role_id) values ('Francklin', 'Masurel', 'fmasurel0', 'fmasurel0@ocn.ne.jp', 'e18138872ebfaf24e6de9e76044807902b9f8c02dc27d50f5a1d45ec4382ce58', '2006-09-15', 2);
insert into USERS (firstName, lastName, username, email, password, birthday, role_id) values ('Sibilla', 'Kleinerman', 'skleinerman1', 'skleinerman1@nbcnews.com', '4de1a4cd7b91df42c953b2d7b2be554ea669e40d85174f5406da5b0ec415f72b', '1980-12-28', 2);
insert into USERS (firstName, lastName, username, email, password, birthday, role_id) values ('Craggie', 'Melsom', 'cmelsom2', 'cmelsom2@archive.org', 'e856efc5cd00fdd209bc896039f67c35f5e18e2dff3ae10ea3dc5606c0f0f480', '1997-07-22', 2);
insert into USERS (firstName, lastName, username, email, password, birthday, role_id) values ('Cody', 'Laybourn', 'claybourn3', 'claybourn3@fda.gov', 'a8e52a044ff7b4d6db0854dfc509c6a66002e4af2ac8543d2331fffea3351feb', '2004-06-25', 2);
insert into USERS (firstName, lastName, username, email, password, birthday, role_id) values ('Jakob', 'Goodbarne', 'jgoodbarne4', 'jgoodbarne4@cbc.ca', '28c8efe86c85ea06fe35ff7cf99170838065601c7608aae8b1fd7db374562dd7', '1981-08-10', 2);
insert into USERS (firstName, lastName, username, email, password, birthday, role_id) values ('Chickie', 'Mattingley', 'cmattingley5', 'cmattingley5@unblog.fr', '855b27c4d591647fc7bb1731fdc1ba29055162029c0de28f9077167377ee57d3', '1990-12-22', 2);
insert into USERS (firstName, lastName, username, email, password, birthday, role_id) values ('Maddy', 'Polin', 'mpolin6', 'mpolin6@jalbum.net', '036e8d7745f716f58ed5ec40586d61b255d18820f507cb720194de32f584628c', '1994-04-28', 2);
insert into USERS (firstName, lastName, username, email, password, birthday, role_id) values ('Antonina', 'Ceccoli', 'aceccoli7', 'aceccoli7@boston.com', 'd1c9b0dae8d1a12a43be8e434049bacaf03151c7eb3e257343ed5bf1856c3762', '1985-02-24', 2);
insert into USERS (firstName, lastName, username, email, password, birthday, role_id) values ('Cassandra', 'Domerq', 'cdomerq8', 'cdomerq8@ow.ly', 'b927897a310c5bbbc49faccc2aa08d811acc23ee5373835f81b318e67a43b530', '1990-11-30', 2);
insert into USERS (firstName, lastName, username, email, password, birthday, role_id) values ('Gerty', 'Wheadon', 'gwheadon9', 'gwheadon9@google.com', '5fd0b845256ebff0ef15f4b9618cd70fcb133133bbbe0363ff7410bd58ede0ae', '1991-08-21', 2);
insert into USERS (firstName, lastName, username, email, password, birthday, role_id) values ('Jordon', 'Gercke', 'jgerckea', 'jgerckea@ftc.gov', '8ae5199171d06a5766868d119a4837a3c8bb2ade8feef13febfeb303e6a272b7', '2008-02-26', 2);
insert into USERS (firstName, lastName, username, email, password, birthday, role_id) values ('Nessy', 'Chancellor', 'nchancellorb', 'nchancellorb@berkeley.edu', 'c2cb9331b5f9cdce18197e09e06f1319e2f2de09e5a0d57a8b99fd0c8507319c', '2005-07-12', 2);
insert into USERS (firstName, lastName, username, email, password, birthday, role_id) values ('Amandie', 'Plaice', 'aplaicec', 'aplaicec@google.co.uk', 'c176ed51f7a2309c17eadb38c514e8963512238b66a1b044aa82cdd9dcc931a0', '1993-08-21', 2);
insert into USERS (firstName, lastName, username, email, password, birthday, role_id) values ('Waite', 'Janaud', 'wjanaudd', 'wjanaudd@upenn.edu', '322bc1c0e984458c1d7a5c7de2fddfd603fd085b6d616bb42db9410193e22682', '1992-11-03', 2);
insert into USERS (firstName, lastName, username, email, password, birthday, role_id) values ('Parsifal', 'Postin', 'ppostine', 'ppostine@yahoo.co.jp', '141d0685af6cc9f3e5ee9f542eeb06d4b3920bf39f955462c6202ef1a640cb30', '2009-12-16', 2);
insert into USERS (firstName, lastName, username, email, password, birthday, role_id) values ('Willa', 'Isgar', 'wisgarf', 'wisgarf@examiner.com', '40f566ec2526e2bd39b294ad6d07b32cef77804be3d9126619e16dc612a9df4c', '1996-11-25', 2);
insert into USERS (firstName, lastName, username, email, password, birthday, role_id) values ('Joelie', 'Dast', 'jdastg', 'jdastg@opensource.org', 'd1029dba68546ce4e5a16d6073aac6cd67b515693c491bc5a28f445bc956a49a', '2005-02-07', 2);
insert into USERS (firstName, lastName, username, email, password, birthday, role_id) values ('Dan', 'Giblin', 'dgiblinh', 'dgiblinh@loc.gov', 'fbe4c5d24d684f59b3764c3a679dc78110938d802bfdc36489ac1f73fd1de04b', '2006-02-07', 2);
insert into USERS (firstName, lastName, username, email, password, birthday, role_id) values ('Deirdre', 'Poag', 'dpoagi', 'dpoagi@storify.com', '1bcf27d6385c14867c3bb63054951b7da4b66cd4b908424d73022a13207e5b9f', '1988-02-04', 2);
insert into USERS (firstName, lastName, username, email, password, birthday, role_id) values ('Timi', 'Casella', 'tcasellaj', 'tcasellaj@dell.com', 'b22e496d0de0fc7afa7af26ba63377e62673c66d83b7f4d8ca8a5c17c3ff05d6', '2008-06-28', 2);
