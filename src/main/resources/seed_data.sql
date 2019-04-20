INSERT INTO USER_GROUP(CREATE_DATE, MOD_DATE, MOD_BY, USER_GROUP_ID, NAME) VALUES (now(), now(), 1, 1, 'Admin');
INSERT INTO USER_GROUP(CREATE_DATE, MOD_DATE, MOD_BY, USER_GROUP_ID, NAME) VALUES (now(), now(), 1, 2, 'User');

INSERT INTO USERS (USER_GROUP_ID, USER_NAME, PASSWORD, FIRST_NAME, LAST_NAME, SEX) VALUES (1, 'admin', '$2a$10$TpGAfZgpYQWWA1/b08miJ.tZs06jIz60LeKO5PVU1ogVB2snOf2A2', 'Admin', 'Admin', 'M');


INSERT INTO SLIDER(CREATE_DATE, MOD_DATE, MOD_BY, SLIDER_ID, NAME, PICTURE) VALUES (now(), now(), 1, 1, 'Slider1', '1.jpg');
INSERT INTO SLIDER(CREATE_DATE, MOD_DATE, MOD_BY, SLIDER_ID, NAME, PICTURE) VALUES (now(), now(), 1, 2, 'Slider2', '2.jpg');
INSERT INTO SLIDER(CREATE_DATE, MOD_DATE, MOD_BY, SLIDER_ID, NAME, PICTURE) VALUES (now(), now(), 1, 3, 'Slider3', '3.jpg');
INSERT INTO SLIDER(CREATE_DATE, MOD_DATE, MOD_BY, SLIDER_ID, NAME, PICTURE) VALUES (now(), now(), 1, 4, 'Slider4', '4.jpg');

COMMIT;
