insert into kts_nvt_test.geo_location 
values(1, 23.23, 23.23, "Novi Sad");


INSERT INTO kts_nvt_test.cultural_offer_category VALUES (1,'Manifestation');
INSERT INTO kts_nvt_test.cultural_offer_category VALUES (2,'Institution');

INSERT INTO kts_nvt_test.cultural_offer_type VALUES (1,'Festival',1);
INSERT INTO kts_nvt_test.cultural_offer_type VALUES (2,'Type2',1);
INSERT INTO kts_nvt_test.cultural_offer_type VALUES (3,'Type3',2);
INSERT INTO kts_nvt_test.cultural_offer_type VALUES (4,'Type4',2);

insert into  kts_nvt_test.registered_user values (1,'a@a','Vladimir','Vukovic','vukovic', true);
insert into  kts_nvt_test.registered_user values (2,'a2@a','FName2','LName2','123456789', true);
insert into  kts_nvt_test.registered_user values (3,'a3@a','FName3','LName3','123456789', true);
insert into  kts_nvt_test.registered_user values (4,'a4@a','FName4','LName4','123456789', true);

insert into kts_nvt_test.image values (1, 10);
insert into kts_nvt_test.image values (2, 10);

insert into kts_nvt_test.grade values(1, 1, 1, 1);
insert into kts_nvt_test.grade values(2, 5, 1, 2);
insert into kts_nvt_test.grade values(3, 1, 2, 1);
insert into kts_nvt_test.grade values(4, 4, 2, 2);

insert into kts_nvt_test.cultural_offer values(1, "Desc1", "Name1", 1, 1);
insert into kts_nvt_test.cultural_offer values(2, "Desc2", "Name2", 1, 1);
insert into kts_nvt_test.cultural_offer values(3, "Desc3", "Name3", 1, 2);
insert into kts_nvt_test.cultural_offer values(4, "Desc4", "Name4", 1, 2);