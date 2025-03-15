/*==============================================================*/
/* DBMS name:      MYSQL                                        */
/* description:    USERS                                        */
/*==============================================================*/

CREATE TABLE USERS
(
    ID              BIGINT       NOT NULL,
    USERNAME        VARCHAR(255) NOT NULL,
    EMAIL           VARCHAR(255) NOT NULL,
    PASSWORD        VARCHAR(255) NOT NULL,
    CREATION_DATE   DATETIME     NOT NULL,
    CREATOR_USER_ID BIGINT       NOT NULL,
    LAST_UPDATE     DATETIME,
    UPDATER_USER_ID BIGINT,
    VERSION         INT          NOT NULL,
    PRIMARY KEY (ID),
    UNIQUE (USERNAME),
    UNIQUE (EMAIL)
);

/*==============================================================*/
/* DBMS name:      MYSQL                                        */
/* description:    Insert default user                          */
/* user:        admin                                           */
/* password:    password                                        */
/*==============================================================*/
INSERT INTO USERS (ID, USERNAME, EMAIL, PASSWORD, CREATION_DATE, CREATOR_USER_ID, LAST_UPDATE, UPDATER_USER_ID, VERSION)
VALUES (1, 'admin', 'admin@gmail.com', '03hZdiWlv6qG+2DJChcqLNmzKYGSCBUk3iW1AGXa/jNTI=', CURRENT_TIMESTAMP, 0, NULL, NULL, 0);

/*==============================================================*/
/* DBMS name:      MYSQL                                        */
/* description:    AVAILABLE_SLOTS                              */
/*==============================================================*/
CREATE TABLE AVAILABLE_SLOTS
(
    ID              BIGINT   NOT NULL,
    START_TIME      DATETIME NOT NULL,
    END_TIME        DATETIME NOT NULL,
    IS_RESERVED     TINYINT  NOT NULL,
    CREATION_DATE   DATETIME NOT NULL,
    CREATOR_USER_ID BIGINT   NOT NULL,
    LAST_UPDATE     DATETIME,
    UPDATER_USER_ID BIGINT,
    VERSION         INT      NOT NULL,
    PRIMARY KEY (ID)
);

CREATE INDEX IDX_AVLBL_SLTS_RSRVD ON AVAILABLE_SLOTS (IS_RESERVED);
CREATE INDEX IDX_AVLBL_SLTS_STRT_TIME ON AVAILABLE_SLOTS (START_TIME);

/*==============================================================*/
/* DBMS name:      MYSQL                                        */
/* description:    RESERVED_SLOTS                               */
/*==============================================================*/

CREATE TABLE RESERVED_SLOTS
(
    ID                BIGINT   NOT NULL,
    AVAILABLE_SLOT_ID BIGINT   NOT NULL,
    USER_ID           BIGINT   NOT NULL,
    CREATION_DATE     DATETIME NOT NULL,
    CREATOR_USER_ID   BIGINT   NOT NULL,
    LAST_UPDATE       DATETIME,
    UPDATER_USER_ID   BIGINT,
    VERSION           INT      NOT NULL,
    PRIMARY KEY (ID),
    UNIQUE (USER_ID, AVAILABLE_SLOT_ID),
    CONSTRAINT FK_AVAILABLE_SLOT_ID FOREIGN KEY (AVAILABLE_SLOT_ID) REFERENCES AVAILABLE_SLOTS (ID),
    CONSTRAINT FK_USER_ID FOREIGN KEY (USER_ID) REFERENCES USERS (ID)
);