CREATE TABLE COMPANY (
  	COMPANY_ID TINYINT NOT NULL AUTO_INCREMENT
  , NAME VARCHAR(50)
  , LANGUAGE VARCHAR(20) 
  , DESCRIPTION VARCHAR(1000)
  , META_KEYWORD VARCHAR(1000)
  , META_DESCRIPTION VARCHAR(1000)
  , ADDRESS VARCHAR(64) NOT NULL
  , EMAIL VARCHAR(64) NOT NULL
  , PHONE VARCHAR(20)
  , LOGO VARCHAR(250)
  , FAVICON VARCHAR(250)
  , BACKGROUND_SLIDER VARCHAR(250)
  , BANNER_CONTENT_HEADER VARCHAR(4000)
  , BANNER_CONTENT_PARAGRAPH VARCHAR(250)
  , COPYRIGHT VARCHAR(250)
  , TWITTER_API VARCHAR(250)
  , GOOGLE_MAP VARCHAR(1000)
  , FACEBOOK_URL VARCHAR(250)
  , TWITTER_URL VARCHAR(250)
  , INSTAGRAM_URL VARCHAR(250)
  , FOOTER_PARAGRAPH1 VARCHAR(250)
  , FOOTER_PARAGRAPH2 VARCHAR(250)
  , FOOTER_PARAGRAPH3 VARCHAR(250)
  , CREATE_DATE TIMESTAMP NOT NULL DEFAULT NOW()
  , MOD_DATE TIMESTAMP NOT NULL DEFAULT NOW()
  , MOD_BY BIGINT NOT NULL DEFAULT 1
  , STATUS SMALLINT DEFAULT 0
  , PRIMARY KEY (COMPANY_ID)
);


CREATE TABLE USER_GROUP (
  USER_GROUP_ID BIGINT NOT NULL AUTO_INCREMENT
  , NAME VARCHAR(64) NOT NULL
  , PERMISSION TEXT
  , CREATE_DATE TIMESTAMP NOT NULL DEFAULT NOW()
  , MOD_DATE TIMESTAMP NOT NULL DEFAULT NOW()
  , MOD_BY BIGINT NOT NULL DEFAULT 1,
  
  PRIMARY KEY (USER_GROUP_ID)
);


CREATE TABLE USERS (
       USER_ID BIGINT NOT NULL AUTO_INCREMENT 
     , USER_GROUP_ID BIGINT NOT NULL 
     , USER_NAME VARCHAR(100)
     , PASSWORD VARCHAR(100) NOT NULL DEFAULT '1234'
     , FIRST_NAME VARCHAR(50) NOT NULL
     , LAST_NAME VARCHAR(50) NOT NULL
     , MIDDLE_NAME VARCHAR(50) 
     , PICTURE VARCHAR(150) DEFAULT 'user.jpg'  
     , EMAIL VARCHAR(100)
     , CURRENT_LOCALE VARCHAR(10)
     , CREATE_DATE TIMESTAMP NOT NULL DEFAULT NOW()
     , MOD_DATE TIMESTAMP NOT NULL  
     , BIRTH_DATE DATE NOT NULL
     , SEX CHAR(1) NOT NULL   
     , MOD_BY BIGINT NOT NULL DEFAULT 1
     , HOME_PHONE VARCHAR(20)
     , MOBILE_PHONE VARCHAR(20)
     , ADDRESS TEXT
     , CITY VARCHAR(150) 
     , COUNTRY VARCHAR(150) 
     , COUNTRY_ID BIGINT DEFAULT 215
     , ZIP_CODE  VARCHAR(10) 
     , STATUS SMALLINT DEFAULT 0
     , PRIMARY KEY (USER_ID) 
     , CONSTRAINT FK_USER_1 FOREIGN KEY (USER_GROUP_ID)
                  REFERENCES USER_GROUP (USER_GROUP_ID)
); 
ALTER TABLE USERS MODIFY COLUMN STATUS SMALLINT DEFAULT 1
      COMMENT '0-Pending Approval, 1- Approved/Active, 2- Rejected, 3- Inactive/Deactivated';
CREATE UNIQUE INDEX UNIQUE_USER ON USERS (EMAIL ASC);
CREATE UNIQUE INDEX UNIQUE_USER_NAME ON USERS (USER_NAME ASC);
  

CREATE TABLE SECTION (
	   SECTION_ID BIGINT NOT NULL AUTO_INCREMENT
	 , NAME VARCHAR(20) NOT NULL
	 , TITLE VARCHAR(20) NOT NULL
	 , DESCRIPTION VARCHAR(1000) NOT NULL
	 , PICTURE VARCHAR(150)
	 , LANGUAGE VARCHAR(20) 
	 , STATUS SMALLINT DEFAULT 0
	 , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY BIGINT NOT NULL
	 , PRIMARY KEY (SECTION_ID)
);


CREATE TABLE SECTION_ITEM (
	   SECTION_ITEM_ID BIGINT NOT NULL AUTO_INCREMENT
	 , SECTION_ID BIGINT NOT NULL
	 , TITLE VARCHAR(50) NOT NULL
	 , DESCRIPTION VARCHAR(1000) NOT NULL
	 , PICTURE VARCHAR(150)
	 , LANGUAGE VARCHAR(20) 
	 , STATUS SMALLINT DEFAULT 0
	 , CREATE_DATE TIMESTAMP NOT NULL
     , MOD_DATE TIMESTAMP NOT NULL
     , MOD_BY BIGINT NOT NULL
	 , PRIMARY KEY (SECTION_ITEM_ID)
	 , CONSTRAINT FK_SECTION_ITEM_1 FOREIGN KEY (SECTION_ID)
                  REFERENCES SECTION (SECTION_ID)
);


CREATE TABLE EMPLOYEE (
       EMPLOYEE_ID BIGINT NOT NULL AUTO_INCREMENT 
     , USER_ID BIGINT NOT NULL 
     , DESIGNATION VARCHAR(100)
     , RESUME TEXT
     , MANAGING SMALLINT DEFAULT 0
     , CREATE_DATE TIMESTAMP NOT NULL DEFAULT NOW()
	 , MOD_DATE TIMESTAMP NOT NULL DEFAULT NOW()
	 , MOD_BY BIGINT NOT NULL DEFAULT 1
	 , MANAGING SMALLINT DEFAULT 1
	 , STATUS SMALLINT DEFAULT 0
     , PRIMARY KEY (EMPLOYEE_ID)
     , CONSTRAINT FK_EMPLOYEE_1 FOREIGN KEY (USER_ID)
                  REFERENCES USERS (USER_ID)
);


CREATE TABLE CONTACT_US_MESSAGE (
       CONTACTUS_MESSAGE_ID BIGINT NOT NULL AUTO_INCREMENT 
     , NAME VARCHAR(100) NOT NULL
     , EMAIL VARCHAR(50) NOT NULL
     , PHONE VARCHAR(50)
     , MESSAGE TEXT NOT NULL
     , WAS_READ SMALLINT DEFAULT 0
     , CREATE_DATE TIMESTAMP NOT NULL DEFAULT NOW()
	 , MOD_DATE TIMESTAMP NOT NULL DEFAULT NOW()
	 , MOD_BY BIGINT NOT NULL DEFAULT 1
     , PRIMARY KEY (CONTACTUS_MESSAGE_ID)
);



commit;

