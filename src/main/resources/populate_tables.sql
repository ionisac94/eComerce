INSERT INTO `ecomerce`.`item` (`id`, `averageRating`, `description`, `title`) VALUES ('1', '4.3', 'good item', 'laptop');
INSERT INTO `ecomerce`.`item` (`id`, `averageRating`, `description`, `title`) VALUES ('2', '3.3', 'bad item', 'phone');

INSERT INTO `ecomerce`.`comment` (`id`, `content`, `version`, `itemId`) VALUES ('1', 'comment1', '1', '1');
INSERT INTO `ecomerce`.`comment` (`id`, `content`, `version`, `itemId`) VALUES ('2', 'comment2', '2', '1');
INSERT INTO `ecomerce`.`comment` (`id`, `content`, `version`, `itemId`) VALUES ('3', 'comment3', '3', '2');
INSERT INTO `ecomerce`.`comment` (`id`, `content`, `version`, `itemId`) VALUES ('4', 'comment4', '3', '2');

INSERT INTO `ecomerce`.`rating` (`id`, `value`, `itemId`) VALUES ('1', '4.3', '1');
INSERT INTO `ecomerce`.`rating` (`id`, `value`, `itemId`) VALUES ('2', '3.3', '1');
INSERT INTO `ecomerce`.`rating` (`id`, `value`, `itemId`) VALUES ('3', '4.3', '2');

