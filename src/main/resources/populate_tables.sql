INSERT INTO `ecomerce`.`user` (`id`, `dateCreated`, `email`, `firstName`, `lastName`, `password`) VALUES ('1', '2019-09-01T00:00', 'hello', 'ion', 'isac', '1234');
INSERT INTO `ecomerce`.`user` (`id`, `dateCreated`, `email`, `firstName`, `lastName`, `password`) VALUES ('2', '2019-09-01T00:00', 'hello1', 'ion', 'isac', '12234');

INSERT INTO `ecomerce`.`item` (`id`, `averageRating`, `datePosted`, `description`, `title`, `userId`) VALUES ('1', '2', '2019-09-01T00:00', 'good', 'laptop', '1');
INSERT INTO `ecomerce`.`item` (`id`, `averageRating`, `datePosted`, `description`, `title`, `userId`) VALUES ('2', '2', '2019-09-01T00:00', 'bad', 'phone', '2');

INSERT INTO `ecomerce`.`comment` (`id`, `content`, `datePosted`, `version`, `itemId`) VALUES ('1', 'good content', '2019-09-01T00:00', '1', '1');
INSERT INTO `ecomerce`.`comment` (`id`, `content`, `datePosted`, `version`, `itemId`) VALUES ('2', 'bad content', '2019-09-01T00:00', '2', '1');
INSERT INTO `ecomerce`.`comment` (`id`, `content`, `datePosted`, `version`, `itemId`) VALUES ('3', 'good content', '2019-09-01T00:00', '1', '2');
INSERT INTO `ecomerce`.`comment` (`id`, `content`, `datePosted`, `version`, `itemId`) VALUES ('4', 'bad content', '2019-09-01T00:00', '2', '2');

INSERT INTO `ecomerce`.`rating` (`id`, `datePosted`, `value`, `itemId`) VALUES ('1', '2019-09-01T00:00', '3.2', '1');
INSERT INTO `ecomerce`.`rating` (`id`, `datePosted`, `value`, `itemId`) VALUES ('2', '2019-09-01T00:00', '3.0', '1');
INSERT INTO `ecomerce`.`rating` (`id`, `datePosted`, `value`, `itemId`) VALUES ('3', '2019-09-01T00:00', '3.2', '2');
INSERT INTO `ecomerce`.`rating` (`id`, `datePosted`, `value`, `itemId`) VALUES ('4', '2019-09-01T00:00', '3.0', '2');

INSERT INTO `ecomerce`.`image` (`id`, `datePosted`, `name`, `itemId`) VALUES ('1', '2019-09-01T00:00', 'laptop', '1');
INSERT INTO `ecomerce`.`image` (`id`, `datePosted`, `name`, `itemId`) VALUES ('2', '2019-09-01T00:00', 'laptop2', '1');
INSERT INTO `ecomerce`.`image` (`id`, `datePosted`, `name`, `itemId`) VALUES ('3', '2019-09-01T00:00', 'laptop', '2');
INSERT INTO `ecomerce`.`image` (`id`, `datePosted`, `name`, `itemId`) VALUES ('4', '2019-09-01T00:00', 'laptop2', '2');