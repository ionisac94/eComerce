insert into `ecomerce`.`item` (`id`, `averageRating`, `description`, `title`) values ('1', '4.3', 'good item', 'laptop');
insert into `ecomerce`.`item` (`id`, `averageRating`, `description`, `title`) values ('2', '3.3', 'bad item', 'phone');

insert into `ecomerce`.`comment` (`id`, `content`, `version`, `itemId`) values ('1', 'comment1', '1', '1');
insert into `ecomerce`.`comment` (`id`, `content`, `version`, `itemId`) values ('2', 'comment2', '2', '1');
insert into `ecomerce`.`comment` (`id`, `content`, `version`, `itemId`) values ('3', 'comment3', '3', '2');
insert into `ecomerce`.`comment` (`id`, `content`, `version`, `itemId`) values ('4', 'comment4', '3', '2');

insert into `ecomerce`.`rating` (`id`, `value`, `itemId`) values ('1', '4.3', '1');
insert into `ecomerce`.`rating` (`id`, `value`, `itemId`) values ('2', '3.3', '1');
insert into `ecomerce`.`rating` (`id`, `value`, `itemId`) values ('3', '4.3', '2');

insert into `ecomerce`.`image` (`id`, `datePosted`, `name`, `thumbnail`, `itemId`) values

 (6, '2020-07-17 15:59:59', 'mac.jpg', false, 54);


select * from image;