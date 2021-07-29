INSERT INTO PAYMENT_TYPE (PAYMENT_TYPE_ID, NAME, MOD_BY) VALUES (100, 'Salaire', 1);


INSERT INTO MONTH (MONTH_ID, NAME, DESCRIPTION, STATUS, CREATE_DATE, MOD_DATE, MOD_BY) VALUES (1, 'Janvier', 'Janvier', 0, NOW(), NOW(), 1);
INSERT INTO MONTH (MONTH_ID, NAME, DESCRIPTION, STATUS, CREATE_DATE, MOD_DATE, MOD_BY) VALUES (2, 'Fevrier', 'Fevrier', 0, NOW(), NOW(), 1);
INSERT INTO MONTH (MONTH_ID, NAME, DESCRIPTION, STATUS, CREATE_DATE, MOD_DATE, MOD_BY) VALUES (3, 'Mars', 'Mars', 0, NOW(), NOW(), 1);
INSERT INTO MONTH (MONTH_ID, NAME, DESCRIPTION, STATUS, CREATE_DATE, MOD_DATE, MOD_BY) VALUES (4, 'Avril', 'Avril', 0, NOW(), NOW(), 1);
INSERT INTO MONTH (MONTH_ID, NAME, DESCRIPTION, STATUS, CREATE_DATE, MOD_DATE, MOD_BY) VALUES (5, 'Mai', 'Mai', 0, NOW(), NOW(), 1);
INSERT INTO MONTH (MONTH_ID, NAME, DESCRIPTION, STATUS, CREATE_DATE, MOD_DATE, MOD_BY) VALUES (6, 'Juin', 'Juin', 0, NOW(), NOW(), 1);
INSERT INTO MONTH (MONTH_ID, NAME, DESCRIPTION, STATUS, CREATE_DATE, MOD_DATE, MOD_BY) VALUES (7, 'Juillet', 'Juillet', 0, NOW(), NOW(), 1);
INSERT INTO MONTH (MONTH_ID, NAME, DESCRIPTION, STATUS, CREATE_DATE, MOD_DATE, MOD_BY) VALUES (8, 'Aout', 'Aout', 0, NOW(), NOW(), 1);
INSERT INTO MONTH (MONTH_ID, NAME, DESCRIPTION, STATUS, CREATE_DATE, MOD_DATE, MOD_BY) VALUES (9, 'Septembre', 'Septembre', 0, NOW(), NOW(), 1);
INSERT INTO MONTH (MONTH_ID, NAME, DESCRIPTION, STATUS, CREATE_DATE, MOD_DATE, MOD_BY) VALUES (10, 'Octobre', 'Octobre', 0, NOW(), NOW(), 1);
INSERT INTO MONTH (MONTH_ID, NAME, DESCRIPTION, STATUS, CREATE_DATE, MOD_DATE, MOD_BY) VALUES (11, 'Novembre', 'Novembre', 0, NOW(), NOW(), 1);
INSERT INTO MONTH (MONTH_ID, NAME, DESCRIPTION, STATUS, CREATE_DATE, MOD_DATE, MOD_BY) VALUES (12, 'Decembre', 'Decembre', 0, NOW(), NOW(), 1);

ALTER TABLE PAYMENT MODIFY SALARY_MONTH BIGINT;

ALTER TABLE PAYMENT MODIFY CONTRACT_LABOR_ID BIGINT;


ALTER TABLE CONTRACT_LABOR ADD QUOTE_ID BIGINT NOT NULL;
SET FOREIGN_KEY_CHECKS=0;
ALTER TABLE CONTRACT_LABOR ADD CONSTRAINT FK_CONTRACT_LABOR_2 FOREIGN KEY (QUOTE_ID)
                  REFERENCES QUOTE (QUOTE_ID);
SET FOREIGN_KEY_CHECKS=1;
ALTER TABLE QUOTE ADD TOTAL_AMOUNT DOUBLE NOT NULL;

CREATE TABLE MATERIAL (
	   MATERIAL_ID BIGINT NOT NULL AUTO_INCREMENT
     , PRODUCT_ID BIGINT NOT NULL
     , QUOTE_ID BIGINT NOT NULL
     , UNIT_PRICE DOUBLE
     , QUANTITY BIGINT
     , TOTAL_AMOUNT DOUBLE NOT NULL
     , DESCRIPTION VARCHAR(2000) 
     , STATUS TINYINT DEFAULT 0
     , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY BIGINT NOT NULL
	 , PRIMARY KEY (MATERIAL_ID)
     , CONSTRAINT FK_MATERIAL_1 FOREIGN KEY (PRODUCT_ID)
                  REFERENCES PRODUCT (PRODUCT_ID)
     , CONSTRAINT FK_MATERIAL_2 FOREIGN KEY (QUOTE_ID)
                  REFERENCES QUOTE (QUOTE_ID)
);

-- 05/03/2020;
ALTER TABLE COMPANY ADD COLUMN HOURS_OF_OPERATION VARCHAR(50);
ALTER TABLE COMPANY ADD COLUMN LONGITUDE DOUBLE;
ALTER TABLE COMPANY ADD COLUMN LATITUDE DOUBLE;
ALTER TABLE COMPANY ADD COLUMN HOME_IMAGE VARCHAR(100);


CREATE TABLE CLIENT (
	   CLIENT_ID BIGINT NOT NULL AUTO_INCREMENT
     , NAME VARCHAR(100) NOT NULL
     , ADDRESS VARCHAR(100) NOT NULL
     , PICTURE VARCHAR(250) NOT NULL
     , EMAIL VARCHAR(50)
     , PHONE VARCHAR(50)
     , FACEBOOK VARCHAR(250) 
     , TWITTER VARCHAR(250) 
     , LINKEDIN VARCHAR(250) 
     , INSTAGRAM VARCHAR(250) 
     , WEBSITE VARCHAR(250) 
     , STATUS TINYINT DEFAULT 0
     , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY BIGINT NOT NULL
	 , PRIMARY KEY (CLIENT_ID)
);


CREATE TABLE TESTIMONY (
	   TESTIMONY_ID BIGINT NOT NULL AUTO_INCREMENT
     , CLIENT_ID BIGINT NOT NULL
     , LANGUAGE VARCHAR(20) 
     , AUTHOR VARCHAR(100) NOT NULL
     , POSITION VARCHAR(50) NOT NULL
     , PICTURE VARCHAR(250) NOT NULL
     , EMAIL VARCHAR(50)
     , PHONE VARCHAR(50)
     , FACEBOOK VARCHAR(250) 
     , TWITTER VARCHAR(250) 
     , LINKEDIN VARCHAR(250) 
     , INSTAGRAM VARCHAR(250) 
     , WEBSITE VARCHAR(250) 
     , STATUS TINYINT DEFAULT 0
     , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY BIGINT NOT NULL
	 , PRIMARY KEY (TESTIMONY_ID)
	 , CONSTRAINT FK_TESTIMONY_1 FOREIGN KEY (CLIENT_ID)
                  REFERENCES CLIENT (CLIENT_ID)
);


CREATE TABLE MAILING_LIST (
	   MAILING_LIST_ID BIGINT NOT NULL AUTO_INCREMENT
     , EMAIL VARCHAR(50) NOT NULL
     , STATUS TINYINT DEFAULT 0
     , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY BIGINT NOT NULL
	 , PRIMARY KEY (MAILING_LIST_ID)
);


ALTER TABLE USERS ADD COLUMN COMPANY_NAME VARCHAR(100);

ALTER TABLE EMPLOYEE ADD COLUMN SHOW_ON_SITE TINYINT DEFAULT 0;
ALTER TABLE EMPLOYEE ADD COLUMN SITE_RANK SMALLINT;
ALTER TABLE EMPLOYEE ADD COLUMN SHORT_RESUME VARCHAR(500);

UPDATE EMPLOYEE SET SITE_RANK = 0 WHERE EMPLOYEE_ID > 0 AND SITE_RANK IS NULL;
UPDATE EMPLOYEE SET SHOW_ON_SITE = 0 WHERE EMPLOYEE_ID > 0 AND SHOW_ON_SITE IS NULL;

CREATE TABLE FAQ (
	   FAQ_ID BIGINT NOT NULL AUTO_INCREMENT
	 , LANGUAGE VARCHAR(20) 
     , QUESTION VARCHAR(250) NOT NULL
     , ANSWER TEXT NOT NULL
     , NO_COUNT BIGINT NOT NULL DEFAULT 0
     , YES_COUNT BIGINT NOT NULL DEFAULT 0
     , STATUS TINYINT DEFAULT 0
     , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY BIGINT NOT NULL
	 , PRIMARY KEY (FAQ_ID)
);


CREATE TABLE CATEGORY (
  CATEGORY_ID BIGINT NOT NULL AUTO_INCREMENT
  , PARENT_CATEGORY_ID BIGINT
  , NAME VARCHAR(64) NOT NULL
  , LANGUAGE VARCHAR(20) 
  , DESCRIPTION TEXT
  , STATUS SMALLINT DEFAULT 0
  , CREATE_DATE TIMESTAMP NOT NULL DEFAULT NOW()
  , MOD_DATE TIMESTAMP NOT NULL DEFAULT NOW()
  , MOD_BY BIGINT NOT NULL DEFAULT 1
  , PRIMARY KEY (CATEGORY_ID)
);


CREATE TABLE NEWS (
	   NEWS_ID BIGINT NOT NULL AUTO_INCREMENT
	 , AUTHOR_ID BIGINT NOT NULL
	 , LANGUAGE VARCHAR(20) 
     , TITLE VARCHAR(250) NOT NULL
     , CONTENT TEXT NOT NULL
     , PUBLICATION_DATETIME TIMESTAMP NOT NULL
     , VIEW_COUNT BIGINT NOT NULL DEFAULT 0
     , RATING SMALLINT NOT NULL
     , PICTURE VARCHAR(250) NOT NULL DEFAULT 'default.jpeg'
     , STATUS TINYINT DEFAULT 0
     , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY BIGINT NOT NULL
	 , PRIMARY KEY (NEWS_ID)
	 , CONSTRAINT FK_NEWS_1 FOREIGN KEY (AUTHOR_ID)
                  REFERENCES USERS (USER_ID)
);


CREATE TABLE CATEGORY_NEWS (
	   CATEGORY_NEWS_ID BIGINT NOT NULL AUTO_INCREMENT
     , CATEGORY_ID BIGINT NOT NULL
     , NEWS_ID BIGINT NOT NULL
     , STATUS TINYINT DEFAULT 0
     , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY BIGINT NOT NULL
	 , PRIMARY KEY (CATEGORY_NEWS_ID)
	 , CONSTRAINT FK_CATEGORY_NEWS_1 FOREIGN KEY (NEWS_ID)
                  REFERENCES NEWS (NEWS_ID)
);


CREATE TABLE FEEDBACK (
	   FEEDBACK_ID BIGINT NOT NULL AUTO_INCREMENT
	 , NEWS_ID BIGINT 
     , EMAIL VARCHAR(50) NOT NULL
     , MESSAGE VARCHAR(2500) NOT NULL
     , STATUS TINYINT DEFAULT 0
     , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY BIGINT NOT NULL
	 , PRIMARY KEY (FEEDBACK_ID)
	 , CONSTRAINT FK_FEEDBACK_1 FOREIGN KEY (NEWS_ID)
                  REFERENCES NEWS (NEWS_ID)
);


CREATE TABLE COMMENT (
	   COMMENT_ID BIGINT NOT NULL AUTO_INCREMENT
	 , NEWS_ID BIGINT 
     , RATING SMALLINT NOT NULL
     , MESSAGE TEXT NOT NULL
     , AUTHOR VARCHAR(100) NOT NULL
     , AUTHOR_EMAIL VARCHAR(100) NOT NULL
     , STATUS TINYINT DEFAULT 0
     , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY BIGINT NOT NULL
	 , PRIMARY KEY (COMMENT_ID)
	 , CONSTRAINT FK_COMMENT_1 FOREIGN KEY (NEWS_ID)
                  REFERENCES NEWS (NEWS_ID)
);


CREATE TABLE SETTING (
	 FIXE_MENU TINYINT DEFAULT 0
	 , LEFT_TO_RIGHT TINYINT DEFAULT 0
	 , HEADER_TEXT_POSITION TINYINT DEFAULT 1
	 , HEADER_IMAGE_TYPE VARCHAR(25) NOT NULL
	 , THEME_COLOR VARCHAR(25) NOT NULL
     , STATUS TINYINT DEFAULT 0
     , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY BIGINT NOT NULL
);


ALTER TABLE USERS ADD COLUMN FACEBOOK VARCHAR(250);
ALTER TABLE USERS ADD COLUMN TWITTER VARCHAR(250);
ALTER TABLE USERS ADD COLUMN LINKEDIN VARCHAR(250);
ALTER TABLE USERS ADD COLUMN INSTAGRAM VARCHAR(250);
ALTER TABLE USERS ADD COLUMN WEBSITE VARCHAR(250);


-- 05/16/2020;
ALTER TABLE USERS ADD COLUMN RECEIVE_NEWSLETTER BOOLEAN;
CREATE TABLE ROLE (
  	ROLE_ID BIGINT NOT NULL AUTO_INCREMENT
  , NAME VARCHAR(20) NOT NULL
  , DESCRIPTION VARCHAR(50)
  , STATUS SMALLINT DEFAULT 0
  , CREATE_DATE TIMESTAMP NOT NULL DEFAULT NOW()
  , MOD_DATE TIMESTAMP NOT NULL DEFAULT NOW()
  , MOD_BY BIGINT NOT NULL DEFAULT 1
  , PRIMARY KEY (ROLE_ID)
);

CREATE TABLE USER_ROLE (
  	USER_ROLE_ID BIGINT NOT NULL AUTO_INCREMENT
  , USER_ID BIGINT NOT NULL
  , ROLE_ID BIGINT NOT NULL
  , STATUS SMALLINT DEFAULT 0
  , CREATE_DATE TIMESTAMP NOT NULL DEFAULT NOW()
  , MOD_DATE TIMESTAMP NOT NULL DEFAULT NOW()
  , MOD_BY BIGINT NOT NULL DEFAULT 1
  , PRIMARY KEY (USER_ROLE_ID)
  , CONSTRAINT FK_USER_ROLE_1 FOREIGN KEY (USER_ID)
                  REFERENCES USERS (USER_ID)
  , CONSTRAINT FK_USER_ROLE_2 FOREIGN KEY (ROLE_ID)
                  REFERENCES ROLE (ROLE_ID)
);


CREATE TABLE RESOURCE (
  	RESOURCE_ID BIGINT NOT NULL AUTO_INCREMENT
  , PARENT_ID BIGINT 
  , NAME VARCHAR(30) NOT NULL
  , URL_PATH VARCHAR(50)
  , DESCRIPTION VARCHAR(50)
  , STATUS SMALLINT DEFAULT 0
  , CREATE_DATE TIMESTAMP NOT NULL DEFAULT NOW()
  , MOD_DATE TIMESTAMP NOT NULL DEFAULT NOW()
  , MOD_BY BIGINT NOT NULL DEFAULT 1
  , PRIMARY KEY (RESOURCE_ID)
);

CREATE TABLE MENU_ITEM (
  	MENU_ITEM_ID BIGINT NOT NULL AUTO_INCREMENT
  , PARENT_ID BIGINT 
  , RESOURCE_ID BIGINT
  , LANGUAGE VARCHAR(2) NOT NULL
  , LABEL VARCHAR(40) NOT NULL
  , ICON VARCHAR(40) 
  , LEVEL TINYINT NOT NULL
  , MI_ORDER SMALLINT NOT NULL
  , DESCRIPTION VARCHAR(100)
  , STATUS SMALLINT DEFAULT 0
  , CREATE_DATE TIMESTAMP NOT NULL DEFAULT NOW()
  , MOD_DATE TIMESTAMP NOT NULL DEFAULT NOW()
  , MOD_BY BIGINT NOT NULL DEFAULT 1
  , PRIMARY KEY (MENU_ITEM_ID)
);


CREATE TABLE PERMISSION (
  	PERMISSION_ID BIGINT NOT NULL AUTO_INCREMENT
  , ROLE_ID BIGINT NOT NULL
  , RESOURCE_ID BIGINT NOT NULL
  , CAN_ADD VARCHAR(1) NOT NULL DEFAULT 'N'
  , CAN_VIEW VARCHAR(1) NOT NULL DEFAULT 'N'
  , CAN_EDIT VARCHAR(1) NOT NULL DEFAULT 'N'
  , CAN_DELETE VARCHAR(1) NOT NULL DEFAULT 'N'
  , DESCRIPTION VARCHAR(100)
  , STATUS SMALLINT DEFAULT 0
  , CREATE_DATE TIMESTAMP NOT NULL DEFAULT NOW()
  , MOD_DATE TIMESTAMP NOT NULL DEFAULT NOW()
  , MOD_BY BIGINT NOT NULL DEFAULT 1
  , PRIMARY KEY (PERMISSION_ID)
  , CONSTRAINT FK_PERMISSION_1 FOREIGN KEY (ROLE_ID)
                  REFERENCES ROLE (ROLE_ID)
  , CONSTRAINT FK_PERMISSION_2 FOREIGN KEY (RESOURCE_ID)
                  REFERENCES RESOURCE (RESOURCE_ID)
);
 
--ALTER TABLE RESOURCE ADD   PARENT_ID BIGINT ; 
ALTER TABLE MENU_ITEM MODIFY LEVEL TINYINT NOT NULL DEFAULT 0;
ALTER TABLE MENU_ITEM MODIFY MI_ORDER SMALLINT NOT NULL DEFAULT 0; 
ALTER TABLE ROLE ADD COLUMN RESOURCE_ID BIGINT;
ALTER TABLE USERS ADD COLUMN ROLE_ID BIGINT NULL;
INSERT INTO ROLE (ROLE_ID, NAME, DESCRIPTION, STATUS, CREATE_DATE, MOD_DATE, MOD_BY) 
VALUES (1, 'ADMIN', 'Administrator', 0, NOW(), NOW(), 1),
(2, 'User', 'User', 0, NOW(), NOW(), 1);
COMMIT;

--05/16/2020;
ALTER TABLE COMPANY ADD FIXED_MENU TINYINT DEFAULT 0;
ALTER TABLE COMPANY ADD LEFT_TO_RIGHT TINYINT DEFAULT 0;
ALTER TABLE COMPANY ADD HEADER_TEXT_POSITION TINYINT DEFAULT 1;
ALTER TABLE COMPANY ADD HEADER_IMAGE_TYPE VARCHAR(25) DEFAULT 'carousel';
ALTER TABLE COMPANY ADD THEME_COLOR VARCHAR(25) DEFAULT 'orange-dark';

-- 05/17/2020;
ALTER TABLE NEWS MODIFY PICTURE VARCHAR(250) NOT NULL DEFAULT 'default.jpeg';

CREATE TABLE NEWS_VIDEO (
	   NEWS_VIDEO_ID BIGINT NOT NULL AUTO_INCREMENT
	 , NEWS_ID BIGINT NOT NULL
     , NAME VARCHAR(100) NOT NULL
     , LINK VARCHAR(200) NOT NULL
     , STATUS TINYINT DEFAULT 0
     , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY BIGINT NOT NULL
	 , PRIMARY KEY (NEWS_VIDEO_ID)
	 , CONSTRAINT FK_NEWS_VIDEO_1 FOREIGN KEY (NEWS_ID)
                  REFERENCES NEWS (NEWS_ID)
);

-- 05/18/2020;
ALTER TABLE COMPANY MODIFY DESCRIPTION BLOB;
ALTER TABLE NEWS MODIFY CONTENT BLOB;

-- 05/19/2020;
CREATE TABLE COUNTRY (
       COUNTRY_ID BIGINT NOT NULL AUTO_INCREMENT
     , NAME VARCHAR(100) NOT NULL
     , DOMAIN CHAR(2)
     , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY BIGINT NOT NULL
     , PRIMARY KEY (COUNTRY_ID)
); 

-- 05/19/2020 ;
UPDATE SECTION SET SHOW_IN_MENU='0';
UPDATE SECTION_ITEM SET SHOW_IN_MENU='0';
ALTER TABLE SECTION MODIFY SHOW_IN_MENU TINYINT DEFAULT 0;
ALTER TABLE SECTION_ITEM MODIFY SHOW_IN_MENU TINYINT DEFAULT 0;

--05/20/2020;
ALTER TABLE SECTION MODIFY DESCRIPTION BLOB;
ALTER TABLE SECTION_ITEM MODIFY DESCRIPTION BLOB;

--05/24/2020;
ALTER TABLE SECTION MODIFY TITLE VARCHAR(100);
ALTER TABLE SECTION_ITEM MODIFY TITLE VARCHAR(100);
INSERT INTO USER_GROUP(CREATE_DATE, MOD_DATE, MOD_BY, USER_GROUP_ID, NAME) VALUES (now(), now(), 1, 4, 'Client');
INSERT INTO USER_GROUP(CREATE_DATE, MOD_DATE, MOD_BY, USER_GROUP_ID, NAME) VALUES (now(), now(), 1, 5, 'Editor');
INSERT INTO USER_GROUP(CREATE_DATE, MOD_DATE, MOD_BY, USER_GROUP_ID, NAME) VALUES (now(), now(), 1, 100, 'Other');

--05/25/2020;
ALTER TABLE USERS MODIFY COLUMN RECEIVE_NEWSLETTER BOOLEAN DEFAULT FALSE;
UPDATE USERS SET RECEIVE_NEWSLETTER=FALSE;

--05/26/2020;
ALTER TABLE NEWS ADD COLUMN RATING_COUNT  SMALLINT DEFAULT 0;

--05/30/2020;
ALTER TABLE NEWS MODIFY CONTENT MEDIUMTEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
ALTER TABLE COMPANY MODIFY DESCRIPTION MEDIUMTEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
ALTER TABLE SECTION MODIFY DESCRIPTION  MEDIUMTEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
ALTER TABLE SECTION_ITEM MODIFY DESCRIPTION  MEDIUMTEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;

--5/31/2020;
ALTER TABLE EMPLOYEE MODIFY RESUME  MEDIUMTEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
ALTER TABLE EMPLOYEE MODIFY SHORT_RESUME  MEDIUMTEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;

--5/31/2020;
ALTER TABLE COMPANY ADD COLUMN DISPLAY_MISSION BOOLEAN;
ALTER TABLE COMPANY ADD COLUMN DISPLAY_TESTIMONIAL BOOLEAN;
ALTER TABLE COMPANY ADD COLUMN DISPLAY_SERVICES BOOLEAN;
ALTER TABLE COMPANY ADD COLUMN DISPLAY_CLIENT_LOGOS BOOLEAN;
ALTER TABLE COMPANY ADD COLUMN DISPLAY_TEAMS BOOLEAN;
ALTER TABLE COMPANY ADD COLUMN DISPLAY_EXPERTISE BOOLEAN;
ALTER TABLE COMPANY ADD COLUMN DISPLAY_AUTHOR BOOLEAN;
ALTER TABLE COMPANY ADD COLUMN DISPLAY_FEATURED BOOLEAN;
ALTER TABLE COMPANY ADD COLUMN DISPLAY_BLOGS BOOLEAN;
ALTER TABLE COMPANY ADD COLUMN DISPLAY_SHORT_LANG BOOLEAN DEFAULT false;
ALTER TABLE COMPANY ADD COLUMN DISPLAY_TOOLBAR BOOLEAN DEFAULT false;

UPDATE COMPANY SET DISPLAY_MISSION=false, DISPLAY_TESTIMONIAL=false, DISPLAY_SERVICES=false, DISPLAY_CLIENT_LOGOS=false,DISPLAY_TEAMS=false, DISPLAY_EXPERTISE=false,
DISPLAY_AUTHOR=false, DISPLAY_FEATURED=false, DISPLAY_BLOGS=false, DISPLAY_SHORT_LANG=false;

--6/4/2020;
ALTER TABLE SECTION_ITEM ADD COLUMN SUMMARY MEDIUMTEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
ALTER TABLE COMPANY ADD COLUMN DISPLAY_FOOTER_CONTACT BOOLEAN DEFAULT false;
ALTER TABLE COMPANY ADD COLUMN DISPLAY_FEATURED_BLOGS BOOLEAN DEFAULT false;
ALTER TABLE NEWS ADD COLUMN FEATURED TINYINT DEFAULT 0;
ALTER TABLE NEWS ADD COLUMN AUTHOR_TEXT VARCHAR(150);
ALTER TABLE SECTION ADD COLUMN MENU VARCHAR(100);
ALTER TABLE SECTION MODIFY COLUMN NAME VARCHAR(100);
ALTER TABLE SECTION_ITEM ADD COLUMN NAME VARCHAR(100);

ALTER TABLE COMPANY ADD COLUMN GOOGLE_PLUS_URL VARCHAR(250);

--06/21/2020;
ALTER TABLE COMPANY ADD COLUMN LINKED_IN_URL VARCHAR(250);
CREATE TABLE SECTION_ITEM_B AS SELECT * FROM SECTION_ITEM;
UPDATE SECTION_ITEM A SET NAME=(SELECT NAME FROM SECTION_ITEM_B B WHERE A.TITLE=B.TITLE AND B.NAME IS NOT NULL LIMIT 1) WHERE A.NAME IS NULL;
DROP TABLE SECTION_ITEM_B;

--06/27/2020;
SET sql_mode = '';
ALTER TABLE SECTION ADD COLUMN `RANK` MEDIUMINT DEFAULT 0;
ALTER TABLE SECTION_ITEM ADD COLUMN `RANK` MEDIUMINT DEFAULT 0;
ALTER TABLE COMPANY ADD COLUMN FOOTER_TYPE VARCHAR(15) DEFAULT 'Footer-1';
DROP TABLE LOCATION; 
CREATE TABLE LOCATION (
  LOCATION_ID INT(11) NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(32) NOT NULL,
  `RANK` MEDIUMINT DEFAULT 0,
  ADDRESS TEXT NOT NULL,
  TELEPHONE VARCHAR(32) NOT NULL,
  FAX VARCHAR(32),
  GEOCODE VARCHAR(32),
  IMAGE VARCHAR(255) DEFAULT NULL,
  OPEN TEXT NULL,
  COMMENT TEXT NULL,
  CREATE_DATE DATETIME NOT NULL,
  MOD_DATE DATETIME NOT NULL, MOD_BY INT(11),
  PRIMARY KEY (LOCATION_ID),
  KEY NAME (NAME)
)  DEFAULT CHARSET=UTF8;



--- 03/20/2021

ALTER TABLE SLIDER_TEXT MODIFY COLUMN TEXT1 VARCHAR(150);
ALTER TABLE SLIDER_TEXT MODIFY COLUMN TEXT2 VARCHAR(150);
ALTER TABLE SLIDER_TEXT MODIFY COLUMN TEXT3 VARCHAR(150);



--- 03/22/2021 

ALTER TABLE TESTIMONY MODIFY COLUMN CLIENT_ID BIGINT NULL;
ALTER TABLE TESTIMONY MODIFY COLUMN POSITION VARCHAR(100) NULL;
ALTER TABLE TESTIMONY MODIFY COLUMN PICTURE VARCHAR(100) NULL;
ALTER TABLE TESTIMONY ADD COLUMN RANK SMALLINT NOT NULL;
ALTER TABLE TESTIMONY ADD COLUMN COMMENTS VARCHAR(1000) NOT NULL;

ALTER TABLE EMPLOYEE ADD COLUMN DISPLAY_RANK SMALLINT NULL;


CREATE TABLE COMPANY_HISTORY (
  COMPANY_HISTORY_ID INT(11) NOT NULL AUTO_INCREMENT,
  LANGUAGE VARCHAR(20) NOT NULL,
  YEAR MEDIUMINT NOT NULL,
  TITLE TEXT NOT NULL,
  DESCRIPTION TEXT NULL,
  PICTURE VARCHAR(250) NULL,
  STATUS SMALLINT NOT NULL DEFAULT 0,
  CREATE_DATE DATETIME NOT NULL,
  MOD_DATE DATETIME NOT NULL, MOD_BY INT(11),
  PRIMARY KEY (COMPANY_HISTORY_ID)
)  DEFAULT CHARSET=UTF8;


--- 07/25/2021  ---

ALTER TABLE USERS ADD COLUMN USER_GROUP_ID BIGINT NOT NULL;
UPDATE USERS SET USER_GROUP_ID = ROLE WHERE ROLE > 0;
UPDATE USERS SET USER_GROUP_ID = 2 WHERE ROLE = NULL;
ALTER TABLE USERS ADD COLUMN WEBSITE VARCHAR(100);
ALTER TABLE USERS MODIFY PASSWORD VARCHAR(100);

ALTER TABLE COMPANY ADD COLUMN WEBSITE VARCHAR(100);


CREATE TABLE MAIL (
  MAIL_ID BIGINT NOT NULL AUTO_INCREMENT
  , SUBJECT VARCHAR(250) NOT NULL
  , SENDER_ID BIGINT
  , BODY TEXT
  , SEND_EMAIL TINYINT
  , SEND_SMS TINYINT
  , STATUS TINYINT DEFAULT 1
  , CREATE_DATE TIMESTAMP NOT NULL DEFAULT NOW()
  , MOD_DATE TIMESTAMP NOT NULL DEFAULT NOW()
  , MOD_BY BIGINT NOT NULL DEFAULT 1,
  
  PRIMARY KEY (MAIL_ID)
);


CREATE TABLE SMPP (
       SMPP_ID INT(11) NOT NULL AUTO_INCREMENT 
     , NAME VARCHAR(50)
     , URL VARCHAR (500)
     , STATUS TINYINT DEFAULT 1
     , USER_NAME VARCHAR(50)
     , PASSWORD VARCHAR(50)
     , INTER TINYINT DEFAULT 0
     , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY INT(11) NOT NULL
     , PRIMARY KEY (SMPP_ID)     
);

CREATE TABLE SMS (
       SMS_ID BIGINT NOT NULL AUTO_INCREMENT 
     , BODY TEXT NOT NULL 
     , PHONE VARCHAR(50)
     , RESPONSE VARCHAR (250)
     , USER_ID INT(11)
     , SMPP_ID INT(11)
     , STORE_ID  INT(11)
     , STATUS TINYINT DEFAULT 1
     , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY INT(11) NOT NULL
     , PRIMARY KEY (SMS_ID)  
);

INSERT INTO SMPP (SMPP_ID, NAME,PASSWORD,URL,USER_NAME,STATUS, INTER, CREATE_DATE,MOD_DATE,MOD_BY) VALUES 
(1,'BULKSMS',HEX(AES_ENCRYPT('Softenza123', UNHEX(SHA2('Softenza forever',512)))),
'https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0','softenza',1,1,NOW(),NOW(),1);


CREATE TABLE STATUS_TEXT (
       STATUS_TEXT_ID BIGINT NOT NULL AUTO_INCREMENT 
     , LANGUAGE VARCHAR(20) NOT NULL
     , CONTENT TEXT NOT NULL 
     , STATUS TINYINT DEFAULT 1
     , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY INT(11) NOT NULL
     , PRIMARY KEY (STATUS_TEXT_ID)  
);

CREATE TABLE REGULATION (
       REGULATION_ID BIGINT NOT NULL AUTO_INCREMENT 
     , LANGUAGE VARCHAR(20) NOT NULL
     , CONTENT TEXT NOT NULL 
     , STATUS TINYINT DEFAULT 1
     , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY INT(11) NOT NULL
     , PRIMARY KEY (REGULATION_ID)  
);


-- 07/28/2021 

ALTER TABLE POSITION DROP COLUMN NAME;

CREATE TABLE POSITION_DESC (
  POSITION_DESC_ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  POSITION_ID BIGINT(20) NOT NULL,
  LANGUAGE  VARCHAR(20) NOT NULL,
  NAME VARCHAR(255) NOT NULL,
  DESCRIPTION TEXT,
  STATUS TINYINT DEFAULT 1,
  CREATE_DATE DATETIME NOT NULL,
  MOD_DATE DATETIME NOT NULL, 
  MOD_BY INT(11),
  PRIMARY KEY (POSITION_DESC_ID),
  KEY NAME (NAME)
) ENGINE=MYISAM DEFAULT CHARSET=UTF8;

ALTER TABLE POSITION ADD COLUMN STATUS TINYINT;
