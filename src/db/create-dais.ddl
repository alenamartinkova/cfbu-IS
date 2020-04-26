-- Generated by Oracle SQL Developer Data Modeler 19.1.0.081.0911
--   at:        2020-03-22 10:45:46 CET
--   site:      Oracle9i
--   type:      Oracle9i



CREATE TABLE address (
    address_id     INTEGER NOT NULL,
    city           VARCHAR2(30) NOT NULL,
    country        VARCHAR2(30) NOT NULL,
    address_line   VARCHAR2(30) NOT NULL
)
LOGGING;

ALTER TABLE address ADD CONSTRAINT address_pk PRIMARY KEY ( address_id );

CREATE TABLE league (
    league_id   INTEGER NOT NULL,
    division    INTEGER NOT NULL,
    name        VARCHAR2(30) NOT NULL
)
LOGGING;

ALTER TABLE league ADD CONSTRAINT league_pk PRIMARY KEY ( league_id );

CREATE TABLE player (
    player_id    INTEGER NOT NULL,
    first_name   VARCHAR2(30) NOT NULL,
    last_name    VARCHAR2(30) NOT NULL,
    assists      INTEGER,
    goals        INTEGER,
    team_id      INTEGER NOT NULL,
    address_id   INTEGER NOT NULL,
    year_born    INTEGER NOT NULL
)
LOGGING;

ALTER TABLE player ADD CONSTRAINT player_pk PRIMARY KEY ( player_id );

CREATE TABLE repre (
    repre_id    INTEGER NOT NULL,
    team_name   VARCHAR2(30) NOT NULL
)
LOGGING;

ALTER TABLE repre ADD CONSTRAINT repre_pk PRIMARY KEY ( repre_id );

CREATE TABLE repre_player (
    year        INTEGER NOT NULL,
    player_id   INTEGER NOT NULL,
    repre_id    INTEGER NOT NULL
)
LOGGING;

CREATE TABLE team (
    team_id     INTEGER NOT NULL,
    rank        INTEGER NOT NULL,
    name        VARCHAR2(30) NOT NULL,
    league_id   INTEGER NOT NULL
)
LOGGING;

ALTER TABLE team ADD CONSTRAINT team_pk PRIMARY KEY ( team_id );

CREATE TABLE wfc (
    wfc_id       INTEGER NOT NULL,
    year         INTEGER NOT NULL,
    address_id   INTEGER NOT NULL
)
LOGGING;

ALTER TABLE wfc ADD CONSTRAINT wfc_pk PRIMARY KEY ( wfc_id );

CREATE TABLE wfc_repre (
    wfc_id           INTEGER NOT NULL,
    rank             INTEGER,
    repre_repre_id   INTEGER NOT NULL
)
LOGGING;

ALTER TABLE wfc_repre ADD CONSTRAINT "WFC-_Repre_PK" PRIMARY KEY ( wfc_id );

ALTER TABLE player
    ADD CONSTRAINT player_address_fk FOREIGN KEY ( address_id )
        REFERENCES address ( address_id );

ALTER TABLE player
    ADD CONSTRAINT player_team_fk FOREIGN KEY ( team_id )
        REFERENCES team ( team_id );

ALTER TABLE repre_player
    ADD CONSTRAINT repre_player_player_fk FOREIGN KEY ( player_id )
        REFERENCES player ( player_id );

ALTER TABLE repre_player
    ADD CONSTRAINT repre_player_repre_fk FOREIGN KEY ( repre_id )
        REFERENCES repre ( repre_id );

ALTER TABLE team
    ADD CONSTRAINT team_league_fk FOREIGN KEY ( league_id )
        REFERENCES league ( league_id );

ALTER TABLE wfc
    ADD CONSTRAINT wfc_address_fk FOREIGN KEY ( address_id )
        REFERENCES address ( address_id );

ALTER TABLE wfc_repre
    ADD CONSTRAINT "WFC-_Repre_Repre_FK" FOREIGN KEY ( repre_repre_id )
        REFERENCES repre ( repre_id );

ALTER TABLE wfc_repre
    ADD CONSTRAINT "WFC-_Repre_WFC_FK" FOREIGN KEY ( wfc_id )
        REFERENCES wfc ( wfc_id );



-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                             8
-- CREATE INDEX                             0
-- ALTER TABLE                             15
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0