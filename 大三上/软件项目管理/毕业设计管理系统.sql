--
-- ER/Studio 8.0 SQL Code Generation
-- Company :      1
-- Project :      Model1.DM1
-- Author :       Lenovo
--
-- Date Created : Wednesday, November 29, 2023 15:20:42
-- Target DBMS : MySQL 5.x
--

-- 
-- TABLE: class 
--

CREATE TABLE class(
    cNo       CHAR(10)       NOT NULL,
    prNo      VARCHAR(10)    NOT NULL,
    cName     CHAR(10),
    prName    VARCHAR(80),
    PRIMARY KEY (cNo, prNo)
)ENGINE=MYISAM
;



-- 
-- TABLE: dText 
--

CREATE TABLE dText(
    dTno           CHAR(10)       NOT NULL,
    sName          CHAR(10),
    code           TEXT,
    instruction    TEXT,
    tno            CHAR(10)       NOT NULL,
    sNo            CHAR(11)       NOT NULL,
    cNo            CHAR(10)       NOT NULL,
    prNo           VARCHAR(10)    NOT NULL,
    PRIMARY KEY (dTno)
)ENGINE=MYISAM
;



-- 
-- TABLE: problem 
--

CREATE TABLE problem(
    pNo            BIGINT          NOT NULL,
    tno            CHAR(10)        NOT NULL,
    pName          VARCHAR(20),
    content        CHAR(100),
    pRequest       CHAR(100),
    isoutschool    VARCHAR(10),
    reference      VARCHAR(100),
    statusl        CHAR(5),
    spNo           CHAR(5),
    PRIMARY KEY (pNo, tno)
)ENGINE=MYISAM
;



-- 
-- TABLE: profession 
--

CREATE TABLE profession(
    prNo         VARCHAR(10)    NOT NULL,
    prName       CHAR(50),
    principal    CHAR(10),
    PRIMARY KEY (prNo)
)ENGINE=MYISAM
;



-- 
-- TABLE: sp 
--

CREATE TABLE sp(
    spNo    CHAR(5)        NOT NULL,
    sNo     CHAR(11),
    pNo     BIGINT,
    cNo     CHAR(10),
    prNo    VARCHAR(10),
    PRIMARY KEY (spNo)
)ENGINE=MYISAM
;



-- 
-- TABLE: teacher 
--

CREATE TABLE teacher(
    tno         CHAR(10)       NOT NULL,
    tRoom       VARCHAR(20),
    tName       CHAR(20)       NOT NULL,
    tSex        CHAR(10),
    position    VARCHAR(40),
    research    VARCHAR(80),
    mTel        CHAR(20),
    PRIMARY KEY (tno)
)ENGINE=MYISAM
;



-- 
-- TABLE: `学生表` 
--

CREATE TABLE `学生表`(
    sNo      CHAR(11)       NOT NULL,
    cNo      CHAR(10)       NOT NULL,
    prNo     VARCHAR(10)    NOT NULL,
    sName    CHAR(20)       NOT NULL,
    sSex     CHAR(10),
    sAge     INT,
    sTel     CHAR(20),
    year     CHAR(4),
    PRIMARY KEY (sNo, cNo, prNo)
)ENGINE=MYISAM
;



-- 
-- TABLE: `周总结表` 
--

CREATE TABLE `周总结表`(
    Wno             CHAR(10)    NOT NULL,
    sNo             CHAR(10),
    thisweek        TEXT,
    nextweek        CHAR(10),
    `需要支持说明`  CHAR(10),
    tno             CHAR(10)    NOT NULL,
    PRIMARY KEY (Wno)
)ENGINE=MYISAM
;



-- 
-- INDEX: Ref56 
--

CREATE INDEX Ref56 ON class(prNo)
;
-- 
-- INDEX: Ref114 
--

CREATE INDEX Ref114 ON dText(tno)
;
-- 
-- INDEX: Ref218 
--

CREATE INDEX Ref218 ON dText(sNo, cNo, prNo)
;
-- 
-- INDEX: Ref410 
--

CREATE INDEX Ref410 ON problem(spNo)
;
-- 
-- INDEX: Ref112 
--

CREATE INDEX Ref112 ON problem(tno)
;
-- 
-- INDEX: Ref29 
--

CREATE INDEX Ref29 ON sp(sNo, cNo, prNo)
;
-- 
-- INDEX: Ref68 
--

CREATE INDEX Ref68 ON `学生表`(cNo, prNo)
;
-- 
-- INDEX: Ref113 
--

CREATE INDEX Ref113 ON `周总结表`(tno)
;
-- 
-- TABLE: class 
--

ALTER TABLE class ADD CONSTRAINT Refprofession61 
    FOREIGN KEY (prNo)
    REFERENCES profession(prNo)
;


-- 
-- TABLE: dText 
--

ALTER TABLE dText ADD CONSTRAINT Refteacher141 
    FOREIGN KEY (tno)
    REFERENCES teacher(tno)
;

ALTER TABLE dText ADD CONSTRAINT `Ref学生表181` 
    FOREIGN KEY (sNo, cNo, prNo)
    REFERENCES `学生表`(sNo, cNo, prNo)
;


-- 
-- TABLE: problem 
--

ALTER TABLE problem ADD CONSTRAINT Refsp101 
    FOREIGN KEY (spNo)
    REFERENCES sp(spNo)
;

ALTER TABLE problem ADD CONSTRAINT Refteacher121 
    FOREIGN KEY (tno)
    REFERENCES teacher(tno)
;


-- 
-- TABLE: sp 
--

ALTER TABLE sp ADD CONSTRAINT `Ref学生表91` 
    FOREIGN KEY (sNo, cNo, prNo)
    REFERENCES `学生表`(sNo, cNo, prNo)
;


-- 
-- TABLE: `学生表` 
--

ALTER TABLE `学生表` ADD CONSTRAINT Refclass81 
    FOREIGN KEY (cNo, prNo)
    REFERENCES class(cNo, prNo)
;


-- 
-- TABLE: `周总结表` 
--

ALTER TABLE `周总结表` ADD CONSTRAINT Refteacher131 
    FOREIGN KEY (tno)
    REFERENCES teacher(tno)
;


