alter table noticeboards add suspended timestamp null

create table reportboards(
	id int unsigned not null primary key auto_increment,
	boardid int unsigned not null,
	reason text,
	created timestamp DEFAULT CURRENT_TIMESTAMP,
	accepted tinyint unsigned,
	judgement_day timestamp null,
	judgement text,
	adminid int unsigned,
	FOREIGN KEY (boardid) REFERENCES boards(id)
);




alter table player add suspended timestamp null;

create table reportusers(
	id int(10) unsigned not null auto_increment primary key ,
	userid int(10) unsigned not null ,
	reason text ,
	created timestamp not null default CURRENT_TIMESTAMP ,
	accepted tinyint(3) unsigned , 
	judgement_day timestamp , 
	judgement text ,
	adminid int(10) unsigned,
	FOREIGN KEY (userid) REFERENCES player(id)
);

alter table player modify email varchar(100) not null;

/*Above all ran on live 16/8*/

drop table track_views;
alter table trk_ad_action add client int(3) not null default 0;
alter table trk_ad_saves add client int(3) not null default 0;
/*alter table trk_ad_serves add client int(3)*/
alter table trk_ad_views add client int(3) not null default 0;
alter table trk_board_views add client int(3) not null default 0;
alter table trk_login add client int(3) not null default 0;
alter table trk_privileges add client int(3) not null default 0;

alter table player add pkey varchar(100);
alter table player modify pkey text;
/*Above all ran on live 24/12*/

alter table mail_msg add sysType int(3) not null default 0;
alter table mail_msg add intx int(3) not null default 0;

alter table player add biog text;
alter table noticeboards add category int(3);
alter table noticeboards modify category varchar(8);

create table board_category(
	category varchar(8) primary key,
	description varchar(124)
)  ENGINE=InnoDB;
;
insert into board_category values ('DFLT','default category');
insert into board_category values ('ENT','entertainment');
insert into board_category values ('SPORT','Sport');
insert into board_category values ('FOOD_DRK','Food & Drink');
insert into board_category values ('BARCLUB','Bar, club');
insert into board_category values ('TRANS','Station, stop');
insert into board_category values ('EDU','Education');
insert into board_category values ('EVENT','Event');
insert into board_category values ('POI','Place of Interest');
insert into board_category values ('RETAIL','Retail');
insert into board_category values ('SOCIAL','Social');

update noticeboards set category = 'DFLT'

ALTER TABLE noticeboards 
ADD FOREIGN KEY noticeboards(category) REFERENCES board_category(category);


insert into board_category values ('TOUR','tours');
insert into board_category values ('HOTEL','Hotel/hostel');




update noticeboards set creatorid = 2 where brandid is null;

update notices set image = '1.png' where id = 1;
update noticeboards set active = 1;


--created 26/5
alter table player modify token varchar(100);

CREATE TABLE IF NOT EXISTS `authtoken` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(10) unsigned NOT NULL,
  `token` varchar(100) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `expires` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `token` (`token`),
  KEY `userid` (`userid`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=340 ;


alter table trk_ad_action modify id  int(10) unsigned NOT NULL AUTO_INCREMENT;
alter table trk_ad_saves modify id  int(10) unsigned NOT NULL AUTO_INCREMENT;
alter table trk_ad_serves modify id  int(10) unsigned NOT NULL AUTO_INCREMENT;
alter table trk_ad_views modify id  int(10) unsigned NOT NULL AUTO_INCREMENT;
alter table trk_board_views modify id  int(10) unsigned NOT NULL AUTO_INCREMENT;
alter table trk_login modify id  int(10) unsigned NOT NULL AUTO_INCREMENT;
alter table trk_ad_action modify id  int(10) unsigned NOT NULL AUTO_INCREMENT;
alter table trk_privileges modify id  int(10) unsigned NOT NULL AUTO_INCREMENT;


27/6/13
alter table noticeboards add lastupdate timestamp;
update `noticeboards` set lastupdate = created

create table password_reset_token(
	id int unsigned not null primary key auto_increment,
	userid int(10) unsigned not null,
	token text,
	created timestamp DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (userid) REFERENCES player(id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=340;