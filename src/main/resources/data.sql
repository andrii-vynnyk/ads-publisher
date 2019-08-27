insert into campaigns(id, name, status, start_date, end_date)
values (1, 'Nice', 1, '2019-01-01 00:00:00', '2019-12-31 23:59:59');
insert into campaigns(id, name, status, start_date, end_date)
values (2, 'Adibas', 1, '2019-03-01 00:00:00', '2019-09-30 23:59:59');
insert into ads (id, name, status, asset_url)
values (1, 'Shoes', 1, 'https://www.nice.com/');
insert into ads (id, name, status, asset_url)
values (2, 'Accessories/Equipment', 2, 'https://www.nice.com/');
insert into ads_campaigns (ads_id, campaign_id)
values (1, 1);
insert into ads_campaigns (ads_id, campaign_id)
values (2, 1);
insert into ads_campaigns (ads_id, campaign_id)
values (1, 2);
insert into ads_platforms (ads_id, platform_id)
values (1, 0);
insert into ads_platforms (ads_id, platform_id)
values (2, 1);
insert into ads_platforms (ads_id, platform_id)
values (2, 2);