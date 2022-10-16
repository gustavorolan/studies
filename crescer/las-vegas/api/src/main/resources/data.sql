insert into attachment (id,file_name,file_type) values ('00dfa1d9-644c-4c76-a5c3-de6b98595a51','nome','image/png');
insert into attachment (id,file_name,file_type) values ('00dfa1d9-644c-4c76-a5c3-de6b98595a52','nome','image/png');


insert into user_account (assessment,email,full_name,password,permission,active,attachment_id,presentation) values (0,'gustavo@gmail.com','gustavo','$2a$10$iNnb0.ua8zzKivejcyrwc.MF/bHMqxgOq03Pxehl.cji7U5BQEtLa','MANAGER',true,'00766b0d-0f5d-481e-aaf0-d2fe79042be5','a');
insert into user_account (assessment,email,full_name,password,permission,active,attachment_id,presentation) values (0,'zeca@gmail.com','zeca','$2a$10$iNnb0.ua8zzKivejcyrwc.MF/bHMqxgOq03Pxehl.cji7U5BQEtLa','MANAGER',true,'00dfa1d9-644c-4c76-a5c3-de6b98595a51','a');

insert into video (description,end_point,link,name,score,type ,author_id)  values (1,'sim','/videos/705044966,https://player.vimeo.com/video/705044966?h=df4cf72a40','fafsafasfsa',4,'COURSE',2);
