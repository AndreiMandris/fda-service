CREATE DATABASE fdadb;

CREATE ROLE fda password 'local-password' createdb login;

\connect fdadb;
SET search_path = public, pg_fda;

CREATE extension if not exists pgcrypto;
CREATE EXTENSION if not exists citext;

CREATE TABLE phinxlog (
    version BIGINT NOT NULL, 
    migration_name VARCHAR(100) NULL, 
    start_time TIMESTAMP NULL, 
    end_time TIMESTAMP NULL, 
    breakpoint boolean NOT NULL DEFAULT false, 
    PRIMARY KEY (version)
);

ALTER TABLE public.phinxlog OWNER TO fda;

ALTER DATABASE postgres SET timezone TO 'Europe/Bucharest';
SELECT pg_reload_conf();
