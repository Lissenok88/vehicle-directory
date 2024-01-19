DROP TABLE IF EXISTS VEHICLE;
DROP SEQUENCE IF EXISTS VEHICLE_ID_SEQ;

create table VEHICLE
(
  ID bigserial primary key,
  STATE_NUMBER varchar(1024) not null,
  MAKE varchar(1024) not null,
  MODEL varchar(1024) not null,
  CATEGORY varchar(1024) not null,
  TYPE varchar(1024) not null,
  YEAR integer,
  TRAILER varchar(1024) not null
);