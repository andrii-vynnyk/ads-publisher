create table statuses
(
    id   integer     not null,
    name varchar(45) not null,
    primary key (id),
    unique (name)
);

insert into statuses
values (0, 'planned');
insert into statuses
values (1, 'active');
insert into statuses
values (2, 'paused');
insert into statuses
values (3, 'finished');

create table platforms
(
    id   integer     not null,
    name varchar(45) not null,
    primary key (id),
    unique (name)
);

insert into platforms
values (0, 'web');
insert into platforms
values (1, 'android');
insert into platforms
values (2, 'ios');

create table campaigns
(
    id         integer      not null auto_increment,
    name       varchar(255) not null,
    status     integer default 0,
    start_date timestamp,
    end_date   timestamp,
    primary key (id),
    unique (name),
    foreign key (status) references statuses (id)
);

create table ads
(
    id        integer      not null auto_increment,
    name      varchar(255) not null,
    status    integer default 0,
    asset_url varchar(255),
    primary key (id),
    unique (name),
    foreign key (status) references statuses (id)
);

create table ads_campaigns
(
    ads_id      int,
    campaign_id int,
    primary key (ads_id, campaign_id),
    foreign key (ads_id) references ads (id) on delete cascade,
    foreign key (campaign_id) references campaigns (id)
);

create table ads_platforms
(
    ads_id      int,
    platform_id int,
    primary key (ads_id, platform_id),
    foreign key (ads_id) references ads (id) on delete cascade,
    foreign key (platform_id) references platforms (id)
);