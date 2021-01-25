
INSERT INTO `admin` VALUES (2,'vlado@gmail.com','Vladimir','Vukovic','$2a$10$nMZfWfEBE2qY1BZ.4Z25ye/LKwRD.wNzgtxre.8MUArJWGwQOqE2e'),(5,'ad@ad','Dusan','Madzarevic','$2a$10$C5iDhmUWdPbc/iphyGUsGeeJgTyzHxeCKRroYRnr7LpwcHqyJvvva'),(6,'ad2@ad','FName2','LName2','$2a$10$QYQDwyFUTdiSPTUY0wMheeQw8E8DvBd387QmHgjbiAZyyM5CPbTTW'),(7,'ad3@ad','FName3','LName3','$2a$10$QYQDwyFUTdiSPTUY0wMheeQw8E8DvBd387QmHgjbiAZyyM5CPbTTW'),(8,'ad4@ad','Update','UpdateLast','$2a$10$QYQDwyFUTdiSPTUY0wMheeQw8E8DvBd387QmHgjbiAZyyM5CPbTTW');

INSERT INTO `authority` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');

INSERT INTO `comment` VALUES (1,_binary '','This is comment',1,NULL),(2,_binary '\0','Unapproved comment',3,NULL);

INSERT INTO `cultural_offer` VALUES (1,'Description','Name1',1,2),(2,'Desc2','Name2',1,2),(3,'Desc3','Name3',1,1),(4,'Desc4','Name4',1,1);

INSERT INTO `cultural_offer_category` VALUES (1,'Manifestation'),(2,'Institution');

INSERT INTO `cultural_offer_category_types` VALUES (1,1),(1,2),(2,3),(2,4);

INSERT INTO `cultural_offer_comment` VALUES (1,1),(1,2);

INSERT INTO `cultural_offer_grade` VALUES (1,1),(1,2),(2,3),(2,4);

INSERT INTO `cultural_offer_post` VALUES (1,1),(1,2),(1,5),(1,6);

INSERT INTO `cultural_offer_type` VALUES (1,'Type1',1),(2,'Festival',1),(3,'Type3',2),(4,'Type4',2);

INSERT INTO `geo_location` VALUES (1,23.23,23.23,'Novi Sad');

INSERT INTO `grade` VALUES (1,5,1,1),(2,5,1,3),(3,1,2,1),(4,4,2,3);

INSERT INTO `hibernate_sequences` VALUES ('default',114);

INSERT INTO `image` VALUES (1,_binary '5'),(2,_binary '10');

INSERT INTO `post` VALUES (1,'Post content','2020-12-23 14:02:03.972330','This is post',1),(2,'Post2 content','2020-12-23 14:02:03.972330','This is post2',1),(5,'Post5 content','2020-12-23 14:02:03.972330','This is post5',1),(6,'This is post.','2020-12-24 19:14:37.517150','Post1',1);

INSERT INTO `registered_user` VALUES (1,'vladimirvukovic98@maildrop.cc','Vladimir','Vukovic','$2a$10$nMZfWfEBE2qY1BZ.4Z25ye/LKwRD.wNzgtxre.8MUArJWGwQOqE2e',_binary ''),(3,'a3@a','FName3','LName3','$2a$10$nMZfWfEBE2qY1BZ.4Z25ye/LKwRD.wNzgtxre.8MUArJWGwQOqE2e',_binary ''),(4,'a4@a','Update','UpdateLast','$2a$10$nMZfWfEBE2qY1BZ.4Z25ye/LKwRD.wNzgtxre.8MUArJWGwQOqE2e',_binary '');

INSERT INTO `registered_user_comments` VALUES (1,1),(3,2);

INSERT INTO `user_authority` VALUES (3,1),(4,1),(5,2),(6,2),(7,2),(1,1),(2,2),(8,2);

INSERT INTO `verification_code` VALUES (1,'qwre',1),(2,'qwre2',3);