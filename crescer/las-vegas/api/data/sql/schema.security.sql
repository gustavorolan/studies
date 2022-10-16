CREATE TABLE SPRING_SESSION (
    PRIMARY_ID CHAR(36) NOT NULL,
    SESSION_ID CHAR(36) NOT NULL,
    CREATION_TIME BIGINT NOT NULL,
    LAST_ACCESS_TIME BIGINT NOT NULL,
    MAX_INACTIVE_INTERVAL INT NOT NULL,
    EXPIRY_TIME BIGINT NOT NULL,
    PRINCIPAL_NAME VARCHAR(100),
    CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
);

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE SPRING_SESSION_ATTRIBUTES (
   SESSION_PRIMARY_ID CHAR(36) NOT NULL,
   ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
   ATTRIBUTE_BYTES BYTEA NOT NULL,
   CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
   CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
);

insert into attachment (id,file_name,file_type) values ('00dfa1d9-644c-4c76-a5c3-de6b98595a51','nome','image/png');
insert into attachment (id,file_name,file_type) values ('00dfa1d9-644c-4c76-a5c3-de6b98595a52','nome','image/png');

insert into user_account (assessment,email,full_name,password,permission,active,attachment_id,presentation) values (0,'gustavo@gmail.com','gustavo','$2a$10$iNnb0.ua8zzKivejcyrwc.MF/bHMqxgOq03Pxehl.cji7U5BQEtLa','MANAGER',true,'00dfa1d9-644c-4c76-a5c3-de6b98595a51','a');
insert into user_account (assessment,email,full_name,password,permission,active,attachment_id,presentation) values (0,'zeca@gmail.com','zeca','$2a$10$iNnb0.ua8zzKivejcyrwc.MF/bHMqxgOq03Pxehl.cji7U5BQEtLa','MANAGER',true,'00dfa1d9-644c-4c76-a5c3-de6b98595a52','a');