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

-- 7/28/2021
ALTER TABLE POLL_CHOICE ADD URL VARCHAR(500);



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


CREATE TABLE MEETING_REPORT (
  MEETING_REPORT_ID bigint(20) NOT NULL AUTO_INCREMENT,
  MEETING_DATE date NOT NULL,
  AUTHOR_ID bigint(20) DEFAULT NULL,
  STATUS tinyint DEFAULT 1,
  CREATE_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  MOD_DATE timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  MOD_BY bigint(20) NOT NULL,
  PRIMARY KEY (MEETING_REPORT_ID)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

CREATE TABLE MEETING_REPORT_DESC (
  MEETING_REPORT_DESC_ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  MEETING_REPORT_ID BIGINT(20) NOT NULL,
  LANGUAGE  VARCHAR(20) NOT NULL,
  TITLE VARCHAR(255) NOT NULL,
  MESSAGE TEXT,
  STATUS TINYINT DEFAULT 1,
  CREATE_DATE DATETIME NOT NULL,
  MOD_DATE DATETIME NOT NULL, 
  MOD_BY INT(11),
  PRIMARY KEY (MEETING_REPORT_DESC_ID)
) ENGINE=MYISAM DEFAULT CHARSET=UTF8;

CREATE TABLE PUBLICITY (
  PUBLICITY_ID bigint(20) NOT NULL AUTO_INCREMENT,
  DESCRIPTION varchar(150) NOT NULL,
  LINK varchar(150) NOT NULL,
  BEGIN_DATE date DEFAULT NULL,
  END_DATE date DEFAULT NULL,
  PIC varchar(50) DEFAULT 'pub.jpg',
  STATUS tinyint(4) DEFAULT '1',
  RANK bigint(20) DEFAULT NULL,
  COST double DEFAULT NULL,
  CREATE_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  MOD_DATE timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  MOD_BY bigint(20) NOT NULL,
  PRIMARY KEY (PUBLICITY_ID)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


CREATE TABLE VIDEO (
  VIDEO_ID bigint(20) NOT NULL AUTO_INCREMENT,
  LINK varchar(200) NOT NULL,
  VIDEO_DATE date NOT NULL,
  DESCRIPTION text NOT NULL,
  RANK bigint(20) DEFAULT NULL,
  STATUS tinyint(4) DEFAULT '1',
  CREATE_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  MOD_DATE timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  MOD_BY bigint(20) NOT NULL,
  VOTE tinyint(4) DEFAULT NULL,
  VOTE_COUNT bigint(20) DEFAULT NULL,
  NAME varchar(50) DEFAULT NULL,
  PRIMARY KEY (VIDEO_ID)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;


CREATE TABLE IMAGE (
  IMAGE_ID bigint(20) NOT NULL AUTO_INCREMENT,
  PIC varchar(50) DEFAULT 'campusimage.jpg',
  TITLE varchar(50) DEFAULT NULL,
  DESCRIPTION text NOT NULL,
  RANK bigint(20) DEFAULT NULL,
  STATUS tinyint(4) DEFAULT '1',
  CREATE_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  MOD_DATE timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  MOD_BY bigint(20) NOT NULL,
  PRIMARY KEY (IMAGE_ID)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;


ALTER TABLE POLL_TYPE ADD COLUMN STATUS tinyint(4) DEFAULT '1';
ALTER TABLE POLL_TYPE DROP COLUMN NAME;

CREATE TABLE POLL_TYPE_DESC (
  POLL_TYPE_DESC_ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  POLL_TYPE_ID BIGINT(20) NOT NULL,
  LANGUAGE  VARCHAR(20) NOT NULL,
  NAME VARCHAR(255) NOT NULL,
  DESCRIPTION TEXT,
  STATUS TINYINT DEFAULT 1,
  CREATE_DATE DATETIME NOT NULL,
  MOD_DATE DATETIME NOT NULL, 
  MOD_BY INT(11),
  PRIMARY KEY (POLL_TYPE_DESC_ID),
  KEY NAME (NAME)
) ENGINE=MYISAM DEFAULT CHARSET=UTF8;



ALTER TABLE POLL DROP COLUMN TITLE;
ALTER TABLE POLL DROP COLUMN DESCRIPTION;
ALTER TABLE POLL DROP COLUMN END_NOTE;

CREATE TABLE POLL_DESC (
  POLL_DESC_ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  POLL_ID BIGINT(20) NOT NULL,
  LANGUAGE  VARCHAR(20) NOT NULL,
  TITLE varchar(250) DEFAULT NULL,
  END_NOTE text,
  DESCRIPTION TEXT,
  CREATE_DATE DATETIME NOT NULL,
  MOD_DATE DATETIME NOT NULL, 
  MOD_BY INT(11),
  PRIMARY KEY (POLL_DESC_ID)
) ENGINE=MYISAM DEFAULT CHARSET=UTF8;


--- 08/03/2021


ALTER TABLE POLL_QUESTION DROP COLUMN DESCRIPTION;

CREATE TABLE POLL_QUESTION_DESC (
  POLL_QUESTION_DESC_ID bigint(20) NOT NULL AUTO_INCREMENT,
  POLL_QUESTION_ID bigint(20) NOT NULL,
  LANGUAGE  VARCHAR(20) NOT NULL,
  DESCRIPTION text,
  CREATE_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  MOD_DATE timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  MOD_BY bigint(20) NOT NULL,
  PRIMARY KEY (POLL_QUESTION_DESC_ID),
  KEY FK_PQD_1 (POLL_QUESTION_ID),
  CONSTRAINT FK_PQD_1 FOREIGN KEY (POLL_QUESTION_ID) REFERENCES POLL_QUESTION (POLL_QUESTION_ID)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;


ALTER TABLE POLL_CHOICE DROP COLUMN DESCRIPTION;

CREATE TABLE POLL_CHOICE_DESC (
  POLL_CHOICE_DESC_ID bigint(20) NOT NULL AUTO_INCREMENT,
  POLL_CHOICE_ID bigint(20) DEFAULT NULL,
  LANGUAGE VARCHAR(20) NOT NULL,
  DESCRIPTION text,
  CREATE_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  MOD_DATE timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  MOD_BY bigint(20) NOT NULL,
  PRIMARY KEY (POLL_CHOICE_DESC_ID),
  KEY FK_PCD_1 (POLL_CHOICE_ID),
  CONSTRAINT FK_PCD_1 FOREIGN KEY (POLL_CHOICE_ID) REFERENCES POLL_CHOICE (POLL_CHOICE_ID)
) ENGINE=InnoDB AUTO_INCREMENT=186 DEFAULT CHARSET=latin1;


-- 8/8/21
DELETE FROM USER_GROUP;
INSERT INTO USER_GROUP VALUES(1,'Admin',null,NOW(), NOW(),1);
INSERT INTO USER_GROUP VALUES(10,'Editeur',null,NOW(), NOW(),1);
INSERT INTO USER_GROUP VALUES(20,'Finance',null,NOW(), NOW(),1);
INSERT INTO USER_GROUP VALUES(30,'Membre',null,NOW(), NOW(),1);

DELETE FROM USER_ROLE;
INSERT INTO ROLE VALUES(1,'Admin','Admin',0,NOW(), NOW(),1,null);
INSERT INTO ROLE VALUES(10,'Editeur','Editeur',0,NOW(), NOW(),1,null);
INSERT INTO ROLE VALUES(20,'Finance','Finance',0,NOW(), NOW(),1,null);
INSERT INTO ROLE VALUES(30,'Membre','Membre',0,NOW(), NOW(),1,null);

--08/09
ALTER TABLE PROJECT ADD	USER_ID BIGINT;
ALTER TABLE PROJECT ADD	OBJECTIF TEXT;
ALTER TABLE PROJECT ADD	INOVATION TEXT;
ALTER TABLE PROJECT ADD	EXISTANT TEXT;
ALTER TABLE PROJECT ADD	RESOURCE TEXT;
ALTER TABLE PROJECT ADD	EXECUTION TEXT;
ALTER TABLE PROJECT ADD	CONSTRAINTS TEXT;
ALTER TABLE PROJECT ADD	FEASIBILITY TEXT;
ALTER TABLE PROJECT ADD	BUDGET_LINE TEXT;
ALTER TABLE PROJECT ADD	RESULT TEXT;
ALTER TABLE PROJECT ADD	DURATION VARCHAR(150);
ALTER TABLE VIDEO ADD NEWS_ID BIGINT;
ALTER TABLE VIDEO ADD PROJECT_ID BIGINT; 
ALTER TABLE COMMENT ADD PROJECT_ID BIGINT; 

ALTER TABLE PROJECT ADD	VIEW_COUNT BIGINT NOT NULL DEFAULT 0;
ALTER TABLE PROJECT ADD	RATING_COUNT BIGINT NOT NULL DEFAULT 0;
ALTER TABLE PROJECT ADD	RATING SMALLINT NOT NULL DEFAULT 0;
ALTER TABLE PROJECT ADD	PICTURE VARCHAR(250);
ALTER TABLE PROJECT ADD COLUMN FEATURED TINYINT DEFAULT 0;
ALTER TABLE VIDEO MODIFY DESCRIPTION text NULL;
ALTER TABLE VIDEO MODIFY VIDEO_DATE date NULL;

ALTER TABLE PROJECT MODIFY	OBJECTIF BLOB;
ALTER TABLE PROJECT MODIFY	INOVATION BLOB;
ALTER TABLE PROJECT MODIFY	EXISTANT BLOB;
ALTER TABLE PROJECT MODIFY	RESOURCE BLOB;
ALTER TABLE PROJECT MODIFY	EXECUTION BLOB;
ALTER TABLE PROJECT MODIFY	CONSTRAINTS BLOB;
ALTER TABLE PROJECT MODIFY	FEASIBILITY BLOB;
ALTER TABLE PROJECT MODIFY	BUDGET_LINE BLOB;
ALTER TABLE PROJECT MODIFY	RESULT BLOB;

ALTER TABLE CONTACT_US_MESSAGE ADD PROJECT_ID BIGINT;
ALTER TABLE CONTACT_US_MESSAGE ADD NEWS_ID BIGINT;

-- 08/17/2021
ALTER TABLE TRANSACTION ADD COLUMN PHONE VARCHAR(20);
ALTER TABLE TRANSACTION ADD COLUMN PAYMENT_METHOD VARCHAR(20);
ALTER TABLE TRANSACTION ADD COLUMN STATUS SMALLINT DEFAULT 0;
ALTER TABLE TRANSACTION ADD COLUMN FAILURE_REASON VARCHAR(1000);


-- 01/04/2022
ALTER TABLE SLIDER ADD COLUMN RANK MEDIUMINT DEFAULT 0;

ALTER TABLE USERS MODIFY RESUME  BLOB ;
ALTER TABLE VIDEO ADD COLUMN USER_ID BIGINT;
ALTER TABLE COMPANY ADD COLUMN SUPPORT_MULTI_LANG BOOLEAN DEFAULT true;
ALTER TABLE COMPANY ADD COLUMN SUPPORT_MULTI_LANG BOOLEAN DEFAULT true;


-- 03/26/2022

DROP TABLE JOB_POSITION_DESC;
DROP TABLE JOB_POSITION;

CREATE TABLE JOB_POSITION (
       ID BIGINT NOT NULL AUTO_INCREMENT
     , RANK MEDIUMINT DEFAULT 0
     , LOCATION VARCHAR(250) NOT NULL
     , STATUS INT(1) NOT NULL
     , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY BIGINT NOT NULL
     , PRIMARY KEY (ID)
);

CREATE TABLE JOB_POSITION_DESC (
       ID BIGINT NOT NULL AUTO_INCREMENT
	 , JOB_POSITION_ID BIGINT NOT NULL
	 , LANGUAGE VARCHAR(20) NOT NULL
     , TITLE VARCHAR(250) NOT NULL
     , DESCRIPTION TEXT 
     , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY BIGINT NOT NULL
     , PRIMARY KEY (ID)
     , CONSTRAINT FK_JOB_POSITION_DESC_1 FOREIGN KEY (JOB_POSITION_ID)
                  REFERENCES JOB_POSITION (ID)
);

ALTER TABLE COMPANY ADD COLUMN CAREER_BANNER_TITLE VARCHAR(250);
ALTER TABLE COMPANY ADD COLUMN CAREER_BANNER_TEXT VARCHAR(1000);


CREATE TABLE JOB_APPLI (
       ID BIGINT NOT NULL AUTO_INCREMENT
     , JOB_POSITION_ID BIGINT NOT NULL
     , APPLICANT_ID BIGINT 
     , FIRST_NAME VARCHAR(50) NOT NULL
     , LAST_NAME VARCHAR(50) NOT NULL
     , MIDDLE_NAME VARCHAR(50) NOT NULL
     , PHONE VARCHAR(20) NULL
     , EMAIL VARCHAR(250) NULL
     , NOTES TEXT NULL
     , DOC VARCHAR(250) NULL
     , STATUS INT(1) NOT NULL
     , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY BIGINT NOT NULL
     , PRIMARY KEY (ID)
     , CONSTRAINT FK_JOB_APPLI_1 FOREIGN KEY (JOB_POSITION_ID)
                  REFERENCES JOB_POSITION (ID)
	, CONSTRAINT FK_JOB_APPLI_2 FOREIGN KEY (APPLICANT_ID)
                  REFERENCES USERS (USER_ID)
);

-- 04/03/2022

ALTER TABLE PROJECT ADD CONTRIBUTION DOUBLE NOT NULL DEFAULT 0;

INSERT INTO POLL_TYPE (POLL_TYPE_ID, STATUS, CREATE_DATE, MOD_DATE, MOD_BY) VALUES (1, 1,  NOW(), NOW(), 1);
INSERT INTO POLL_TYPE_DESC (POLL_TYPE_DESC_ID, POLL_TYPE_ID, LANGUAGE, NAME, DESCRIPTION, STATUS, CREATE_DATE, MOD_DATE, MOD_BY) VALUES (1, 1, 'en', 'Election', 'Election', 1, NOW(), NOW(), 1);
INSERT INTO POLL_TYPE_DESC (POLL_TYPE_DESC_ID, POLL_TYPE_ID, LANGUAGE, NAME, DESCRIPTION, STATUS, CREATE_DATE, MOD_DATE, MOD_BY) VALUES (2, 1, 'fr', 'Election', 'Election', 1, NOW(), NOW(), 1);

ALTER TABLE VOTE ADD COLUMN STATUS SMALLINT(1) NOT NULL DEFAULT 1;

ALTER TABLE COMPANY ADD COLUMN DISPLAY_MENU_CAREER BOOLEAN DEFAULT false;
ALTER TABLE COMPANY ADD COLUMN DISPLAY_MENU_POLL BOOLEAN DEFAULT false;
ALTER TABLE COMPANY ADD COLUMN DISPLAY_MENU_BLOG BOOLEAN DEFAULT false;
ALTER TABLE COMPANY ADD COLUMN DISPLAY_MENU_PROJECT BOOLEAN DEFAULT false;
ALTER TABLE COMPANY ADD COLUMN DISPLAY_MENU_EVENT BOOLEAN DEFAULT false;
ALTER TABLE COMPANY ADD COLUMN DISPLAY_PROJECTS BOOLEAN DEFAULT false;
ALTER TABLE COMPANY ADD COLUMN DISPLAY_POLLS BOOLEAN DEFAULT false;


-- 04/23/2022

CREATE TABLE EVENT (
       ID BIGINT NOT NULL AUTO_INCREMENT
     , RANK MEDIUMINT DEFAULT 0
     , LOCATION VARCHAR(250) NOT NULL
     , PICTURE VARCHAR(50) DEFAULT 'default.jpg'
     , STATUS INT(1) NOT NULL
     , BEGIN_DATE DATE NOT NULL
     , END_DATE DATE NOT NULL
     , COORDINATOR_ID BIGINT 
     , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY BIGINT NOT NULL
     , PRIMARY KEY (ID)
     , CONSTRAINT FK_EVENT_1 FOREIGN KEY (COORDINATOR_ID)
                  REFERENCES USERS (USER_ID)
);

CREATE TABLE EVENT_DESC (
       ID BIGINT NOT NULL AUTO_INCREMENT
	 , EVENT_ID BIGINT NOT NULL
	 , LANGUAGE VARCHAR(20) NOT NULL
     , TITLE VARCHAR(250) NOT NULL
     , DESCRIPTION TEXT 
     , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY BIGINT NOT NULL
     , PRIMARY KEY (ID)
     , CONSTRAINT FK_EVENT_DESC_1 FOREIGN KEY (EVENT_ID)
                  REFERENCES EVENT (ID)
);

ALTER TABLE COMPANY ADD COLUMN ACCEPT_CARD_PAYMENT BOOLEAN DEFAULT false;
ALTER TABLE COMPANY ADD COLUMN ACCEPT_TMONEY_PAYMENT BOOLEAN DEFAULT false;
ALTER TABLE COMPANY ADD COLUMN ACCEPT_FLOOZ_PAYMENT BOOLEAN DEFAULT false;

-- 06/12/2022
ALTER TABLE FUND ADD COLUMN PROJECT_ID BIGINT NOT NULL;
ALTER TABLE QUOTE ADD COLUMN PROJECT_ID BIGINT NOT NULL;
ALTER TABLE CONTRACT_LABOR ADD COLUMN PROJECT_ID BIGINT NOT NULL;
ALTER TABLE PAYMENT ADD COLUMN PROJECT_ID BIGINT NOT NULL;
ALTER TABLE PURCHASE ADD COLUMN PROJECT_ID BIGINT NOT NULL;

ALTER TABLE USERS ADD COLUMN RESTRICT_EMP_TAB BOOLEAN DEFAULT false;
ALTER TABLE USERS ADD COLUMN RESTRICT_SAL_TAB BOOLEAN DEFAULT false;
ALTER TABLE USERS ADD COLUMN RESTRICT_FUND_TAB BOOLEAN DEFAULT false;

ALTER TABLE USERS ADD COLUMN COMPANY_ID BIGINT;
ALTER TABLE COMPANY ADD COLUMN OWNER_ID BIGINT;
ALTER TABLE USERS ADD COLUMN USER_TYPE SMALLINT(1) NOT NULL;
ALTER TABLE PROJECT ADD COLUMN COMPANY_ID BIGINT;

CREATE TABLE PROJECT_USER (
  PROJECT_USER_ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  PROJECT_ID BIGINT(20) NOT NULL,
  USER_ID BIGINT(20) NOT NULL,
  STATUS TINYINT DEFAULT 1,
  CREATE_DATE DATETIME NOT NULL,
  MOD_DATE DATETIME NOT NULL, 
  MOD_BY INT(11),
  PRIMARY KEY (PROJECT_USER_ID)
) ENGINE=MYISAM DEFAULT CHARSET=UTF8;


ALTER TABLE COMPANY ADD COLUMN MAIN_COMPANY BOOLEAN DEFAULT false;

ALTER TABLE PROJECT ADD COLUMN COUNTRY_ID BIGINT;


--  07/24/2002
ALTER TABLE TRANSACTION MODIFY COLUMN USER_ID BIGINT;
ALTER TABLE TRANSACTION ADD COLUMN CONTRIBUTOR_NAME VARCHAR(250);


CREATE TABLE CURRENCY (
  CURRENCY_ID INT(11) NOT NULL AUTO_INCREMENT,
  TITLE VARCHAR(32) NOT NULL,
  CODE VARCHAR(3) NOT NULL,
  SYMBOL_LEFT VARCHAR(12),
  SYMBOL_RIGHT VARCHAR(12),
  DECIMAL_PLACE  TINYINT(1),
  VALUE DOUBLE(15,8),
  STATUS TINYINT(1) NOT NULL,
  CREATE_DATE DATETIME NOT NULL,
  MOD_DATE DATETIME NOT NULL, MOD_BY INT(11),
  PRIMARY KEY (CURRENCY_ID)
) ;


ALTER TABLE TRANSACTION ADD COLUMN CURRENCY_ID INT(11);
ALTER TABLE TRANSACTION ADD CONSTRAINT FK_TRANSACTION_11 FOREIGN KEY (CURRENCY_ID)
                  REFERENCES CURRENCY (CURRENCY_ID);


INSERT INTO CURRENCY (CURRENCY_ID, TITLE, CODE, SYMBOL_LEFT, SYMBOL_RIGHT, DECIMAL_PLACE, 
VALUE, STATUS, CREATE_DATE, MOD_DATE, MOD_BY) VALUES
(1, 'POUND STERLING', 'GBP', '£', '', '2', 0.61250001, 1, NOW(), NOW(), 1),
(2, 'US DOLLAR', 'USD', '$', '', '2', 1.00000000, 1, NOW(), NOW(), 1),
(3, 'EURO', 'EUR', '', '€', '2', 0.78460002, 1, NOW(), NOW(), 1),
(4, 'CFA', 'XOF', '', 'CFA', '0', 550, 1, NOW(), NOW(), 1);


--- 08/04/2022
CREATE TABLE EXPENSE_TYPE (
	   EXPENSE_TYPE_ID BIGINT NOT NULL AUTO_INCREMENT
	 , NAME VARCHAR(100) NOT NULL
     , DESCRIPTION VARCHAR(1000) 
     , STATUS TINYINT DEFAULT 0
     , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY BIGINT NOT NULL
	 , PRIMARY KEY (EXPENSE_TYPE_ID)
);

ALTER TABLE PAYMENT ADD COLUMN EXPENSE_TYPE_ID BIGINT;
ALTER TABLE PAYMENT ADD CONSTRAINT FK_PAYMENT_100 FOREIGN KEY (EXPENSE_TYPE_ID)
                  REFERENCES EXPENSE_TYPE (EXPENSE_TYPE_ID);
ALTER TABLE PAYMENT MODIFY COLUMN EXPENSE_TYPE_ID BIGINT NOT NULL;


CREATE TABLE PRD_CATEGORY (
  PRD_CATEGORY_ID BIGINT NOT NULL AUTO_INCREMENT,
  IMAGE VARCHAR(255) DEFAULT NULL,
  PARENT_ID BIGINT,
  SORT_ORDER INT(1),
  STATUS TINYINT(1),
  CREATE_DATE DATETIME NOT NULL,
  MOD_DATE DATETIME NOT NULL, 
  MOD_BY BIGINT NOT NULL,
  PRIMARY KEY (PRD_CATEGORY_ID),
  KEY PARENT_ID (PARENT_ID)
) ENGINE=MYISAM DEFAULT CHARSET=UTF8;

CREATE TABLE PRD_CATEGORY_DESC (
  PRD_CATEGORY_DESC_ID BIGINT NOT NULL AUTO_INCREMENT,
  PRD_CATEGORY_ID INT(11) NOT NULL,
  LANGUAGE VARCHAR(20) NOT NULL,
  NAME VARCHAR(100) NOT NULL,
  DESCRIPTION VARCHAR(255),
  CREATE_DATE DATETIME NOT NULL,
  MOD_DATE DATETIME NOT NULL, 
  MOD_BY BIGINT NOT NULL,
  PRIMARY KEY (PRD_CATEGORY_DESC_ID),
  KEY NAME (NAME),
  CONSTRAINT FK_PRD_CATEGORY_DESC_1 FOREIGN KEY (PRD_CATEGORY_ID)
                  REFERENCES PRD_CATEGORY (PRD_CATEGORY_ID)
) ENGINE=MYISAM DEFAULT CHARSET=UTF8;


CREATE TABLE PRODUCT_DESC (
  PRODUCT_DESC_ID BIGINT NOT NULL AUTO_INCREMENT,
  PRODUCT_ID BIGINT NOT NULL,
  LANGUAGE VARCHAR(20) NOT NULL,
  NAME VARCHAR(100) NOT NULL,
  DESCRIPTION VARCHAR(255),
  CREATE_DATE DATETIME NOT NULL,
  MOD_DATE DATETIME NOT NULL, 
  MOD_BY BIGINT NOT NULL,
  PRIMARY KEY (PRODUCT_DESC_ID),
  KEY NAME (NAME),
  CONSTRAINT FK_PRODUCT_DESC_1 FOREIGN KEY (PRODUCT_ID)
                  REFERENCES PRODUCT (PRODUCT_ID)
) ENGINE=MYISAM DEFAULT CHARSET=UTF8;

INSERT INTO PRODUCT_DESC (PRODUCT_ID, LANGUAGE, NAME, DESCRIPTION, CREATE_DATE, MOD_DATE, MOD_BY)
SELECT PRODUCT_ID, 'en', NAME, DESCRIPTION, NOW(), NOW(), 1 FROM PRODUCT ;

INSERT INTO PRODUCT_DESC (PRODUCT_ID, LANGUAGE, NAME, DESCRIPTION, CREATE_DATE, MOD_DATE, MOD_BY)
SELECT PRODUCT_ID, 'fr', NAME, DESCRIPTION, NOW(), NOW(), 1 FROM PRODUCT ;

ALTER TABLE PRODUCT DROP COLUMN NAME;
ALTER TABLE PRODUCT DROP COLUMN DESCRIPTION;

ALTER TABLE PRODUCT ADD COLUMN PRD_CATEGORY_ID BIGINT NOT NULL;

ALTER TABLE SECTION ADD COLUMN SUMMARY MEDIUMTEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
ALTER TABLE USERS ADD COLUMN USE_COMPANY_CONTACT BOOLEAN DEFAULT false;
ALTER TABLE USERS ADD COLUMN SHORT_RESUME VARCHAR(500);



CREATE TABLE PROJECT_DESC (
       ID BIGINT NOT NULL AUTO_INCREMENT
	 , PROJECT_ID BIGINT NOT NULL
	 , LANGUAGE VARCHAR(20) NOT NULL
	 , TITLE VARCHAR(100) NOT NULL
	 , DESCRIPTION TEXT 
     , SPONSORS TEXT 
     , BUDGET TEXT 
     , CONTRIBUTION TEXT 
     , OBJECTIF TEXT 
     , INOVATION TEXT 
     , EXISTANT TEXT 
     , RESOURCE TEXT 
     , EXECUTION TEXT 
     , CONSTRAINTS TEXT 
     , FEASIBILITY TEXT 
     , BUDGET_LINE TEXT 
     , RESULT TEXT 
     , DURATION VARCHAR(150) 
     , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY BIGINT NOT NULL
     , PRIMARY KEY (ID)
     , CONSTRAINT FK_PROJECT_DESC_1 FOREIGN KEY (PROJECT_ID)
                  REFERENCES PROJECT (PROJECT_ID)
);


 ALTER TABLE PROJECT DROP COLUMN DESCRIPTION; 
 ALTER TABLE PROJECT DROP COLUMN SPONSORS; 
 ALTER TABLE PROJECT DROP COLUMN CONTRIBUTION; 
 ALTER TABLE PROJECT DROP COLUMN OBJECTIF; 
 ALTER TABLE PROJECT DROP COLUMN INOVATION; 
 ALTER TABLE PROJECT DROP COLUMN EXISTANT; 
 ALTER TABLE PROJECT DROP COLUMN RESOURCE; 
 ALTER TABLE PROJECT DROP COLUMN EXECUTION; 
 ALTER TABLE PROJECT DROP COLUMN CONSTRAINTS; 
 ALTER TABLE PROJECT DROP COLUMN FEASIBILITY; 
 ALTER TABLE PROJECT DROP COLUMN BUDGET_LINE; 
 ALTER TABLE PROJECT DROP COLUMN RESULT; 
 ALTER TABLE PROJECT DROP COLUMN DURATION; 
 ALTER TABLE PROJECT DROP COLUMN TITLE; 
  
 CREATE TABLE USER_DESC (
       ID BIGINT NOT NULL AUTO_INCREMENT
	 , USER_ID BIGINT NOT NULL
	 , LANGUAGE VARCHAR(20) NOT NULL
	 , SHORT_RESUME VARCHAR(500)
	 , RESUME  BLOB
     , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY BIGINT NOT NULL
     , PRIMARY KEY (ID)
     , CONSTRAINT FK_USER_DESC_1 FOREIGN KEY (USER_ID)
                  REFERENCES USERS (USER_ID)
);


insert into USER_DESC (USER_ID, LANGUAGE, SHORT_RESUME, RESUME, CREATE_DATE, MOD_DATE, MOD_BY) 
select user_id, 'fr', short_resume, resume, NOW(), NOW(), 1 FROM USERS ;


insert into USER_DESC (USER_ID, LANGUAGE, SHORT_RESUME, RESUME, CREATE_DATE, MOD_DATE, MOD_BY) 
select user_id, 'en', short_resume, resume, NOW(), NOW(), 1 FROM USERS ;


ALTER TABLE TRANSACTION MODIFY COLUMN MOD_DATE TIMESTAMP;
ALTER TABLE TRANSACTION ADD COLUMN PAYMENT_INTENT_ID VARCHAR(50);
ALTER TABLE TRANSACTION MODIFY COLUMN MOD_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE TRANSACTION MODIFY COLUMN USER_ID 
