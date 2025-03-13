/*==============================================================*/
/* DBMS name:      ORACLE                                       */
/* description:    AVAILABLE_SLOTS                              */
/*==============================================================*/
create table AVAILABLE_SLOTS
(
    ID              NUMBER(20) not null constraint PK_AVAILABLE_SLOTS primary key,
    START_TIME      DATE       not null,
    END_TIME        DATE       not null,
    IS_RESERVED     NUMBER(1)  not null,
    CREATION_DATE   DATE       not null,
    CREATOR_USER_ID NUMBER(8)  not null,
    LAST_UPDATE     DATE,
    UPDATER_USER_ID NUMBER(8),
    VERSION         NUMBER(10) not null
);

create index IDX_AVLBL_SLTS_RSRVD
    on AVAILABLE_SLOTS (IS_RESERVED);

create index IDX_AVLBL_SLTS_STRT_TIME
    on AVAILABLE_SLOTS (START_TIME);

/*==============================================================*/
/* DBMS name:      ORACLE                                       */
/* description:    USERS                                        */
/*==============================================================*/

create table USERS
(
    ID              NUMBER(20)    not null constraint PK_USERS primary key,
    USERNAME        VARCHAR2(255) not null constraint UK_USERS_USERNAME unique,
    EMAIL           VARCHAR2(255) not null constraint UK_USERS_EMAIL unique,
    PASSWORD        VARCHAR2(255) not null,
    CREATION_DATE   DATE          not null,
    CREATOR_USER_ID NUMBER(8)     not null,
    LAST_UPDATE     DATE,
    UPDATER_USER_ID NUMBER(8),
    VERSION         NUMBER(10)    not null
);

/*==============================================================*/
/* DBMS name:      ORACLE                                       */
/* description:    RESERVED_SLOTS                               */
/*==============================================================*/

create table RESERVED_SLOTS
(
    ID                NUMBER(20) not null constraint PK_RESERVED_SLOTS unique,
    AVAILABLE_SLOT_ID NUMBER(20) not null constraint FK_AVAILABLE_SLOT_ID references AVAILABLE_SLOTS,
    USER_ID           NUMBER(20) not null constraint FK_USER_ID references USERS,
    CREATION_DATE     DATE       not null,
    CREATOR_USER_ID   NUMBER(8)  not null,
    LAST_UPDATE       DATE,
    UPDATER_USER_ID   NUMBER(8),
    VERSION           NUMBER(10) not null,
    constraint AK_RESERVED_SLOTS unique (USER_ID, AVAILABLE_SLOT_ID)
);

/*==============================================================*/
/* DBMS name:      ORACLE                                       */
/* description:    Insert default user                          */
/* user:        admin                                           */
/* password:    password                                        */
/*==============================================================*/
insert into USERS (ID, USERNAME, EMAIL, PASSWORD, CREATION_DATE, CREATOR_USER_ID, LAST_UPDATE, UPDATER_USER_ID, VERSION)
VALUES (1, 'admin', 'admin@gmail.com', '03hZdiWlv6qG+2DJChcqLNmzKYGSCBUk3iW1AGXa/jNTI=', sysdate, 0, null, null, 0);

