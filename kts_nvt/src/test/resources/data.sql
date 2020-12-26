//AUTHORITY
insert into kts_nvt_test.authority values(1,"ROLE_USER");
insert into kts_nvt_test.authority values(2,"ROLE_ADMIN");

//REGISTERED_USER
insert into  kts_nvt_test.registered_user values (1,'a2@a','FName2','LName2','$2a$10$QYQDwyFUTdiSPTUY0wMheeQw8E8DvBd387QmHgjbiAZyyM5CPbTTW', true);
insert into  kts_nvt_test.registered_user values (3,'a3@a','FName3','LName3','$2a$10$QYQDwyFUTdiSPTUY0wMheeQw8E8DvBd387QmHgjbiAZyyM5CPbTTW', true);
insert into  kts_nvt_test.registered_user values (4,'a4@a','FName4','LName4','$2a$10$QYQDwyFUTdiSPTUY0wMheeQw8E8DvBd387QmHgjbiAZyyM5CPbTTW', false);

//ADMIN
insert into  kts_nvt_test.admin values (2,'vlado@gmail.com','Vladimir','Vukovic','$2a$10$hwp/pRfzpBKCtGOloBrge.kbGfARHdXi7h7NQQVJRiJPeGtm3I9da');
insert into  kts_nvt_test.admin values (5,'ad@ad','Dusan','Madzarevic','$2a$10$C5iDhmUWdPbc/iphyGUsGeeJgTyzHxeCKRroYRnr7LpwcHqyJvvva');
insert into  kts_nvt_test.admin values (6,'ad2@ad','FName2','LName2','$2a$10$QYQDwyFUTdiSPTUY0wMheeQw8E8DvBd387QmHgjbiAZyyM5CPbTTW');
insert into  kts_nvt_test.admin values (7,'ad3@ad','FName3','LName3','$2a$10$QYQDwyFUTdiSPTUY0wMheeQw8E8DvBd387QmHgjbiAZyyM5CPbTTW');
insert into  kts_nvt_test.admin values (8,'ad4@ad','FName4','LName4','$2a$10$QYQDwyFUTdiSPTUY0wMheeQw8E8DvBd387QmHgjbiAZyyM5CPbTTW');

//USER_AUTHORITY
insert into kts_nvt_test.user_authority values(2, 2);
insert into kts_nvt_test.user_authority values(1, 1);
insert into kts_nvt_test.user_authority values(3, 1);
insert into kts_nvt_test.user_authority values(4, 1);
INSERT INTO kts_nvt_test.user_authority VALUES (5,2);
INSERT INTO kts_nvt_test.user_authority VALUES (6,2);
INSERT INTO kts_nvt_test.user_authority VALUES (7,2);
INSERT INTO kts_nvt_test.user_authority VALUES (8,2);

//VERIFICATION_CODE
insert into kts_nvt_test.verification_code values (1,"qwre",1);
insert into kts_nvt_test.verification_code values (2,"qwre2",3);

//GEO_LOCATION
insert into kts_nvt_test.geo_location values(1, 23.23, 23.23, "Novi Sad");

//COMMENT
insert into kts_nvt_test.comment values(1, true, 'This is comment', 1, null);
INSERT INTO kts_nvt_test.comment VALUES (2, false, 'Unapproved comment',4, null);

//POST
insert into kts_nvt_test.post values(1,'Post content',"2020-12-23 14:02:03.972330",'This is post',1);
insert into kts_nvt_test.post values(2,'Post2 content',"2020-12-23 14:02:03.972330",'This is post2',1);
insert into kts_nvt_test.post values(5,'Post5 content',"2020-12-23 14:02:03.972330",'This is post5',1);
insert into kts_nvt_test.post values(6, "This is post.", "2020-12-24 19:14:37.517150", 'Post1', 1);


//CATEGROY
INSERT INTO kts_nvt_test.cultural_offer_category VALUES (1,'Manifestation');
INSERT INTO kts_nvt_test.cultural_offer_category VALUES (2,'Institution');

//TYPE
INSERT INTO kts_nvt_test.cultural_offer_type VALUES (1,'Festival',1);
INSERT INTO kts_nvt_test.cultural_offer_type VALUES (5,'Type2',1);
INSERT INTO kts_nvt_test.cultural_offer_type VALUES (3,'Type3',2);
INSERT INTO kts_nvt_test.cultural_offer_type VALUES (4,'Type4',2);



//IMAGE
insert into kts_nvt_test.image values (1, 10);
insert into kts_nvt_test.image values (2, 10);


//GRADE
insert into kts_nvt_test.grade values(1, 1, 1, 1);
insert into kts_nvt_test.grade values(2, 5, 1, 2);
insert into kts_nvt_test.grade values(3, 1, 2, 1);
insert into kts_nvt_test.grade values(4, 4, 2, 2);

//OFFER
insert into kts_nvt_test.cultural_offer values(1, "Desc1", "Name1", 1, 1);
insert into kts_nvt_test.cultural_offer values(2, "Desc2", "Name2", 1, 1);
insert into kts_nvt_test.cultural_offer values(3, "Desc3", "Name3", 1, 2);
insert into kts_nvt_test.cultural_offer values(4, "Desc4", "Name4", 1, 2);

