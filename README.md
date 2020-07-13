# SpringBoot and MySQL database application.

# Database structure.

![](eComerce/src/main/resources/db_structure.PNG)

# SQL Scripts.

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `comment` varchar(255) NOT NULL,
  `item_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `comment_fk_itemId` (`item_id`),
  CONSTRAINT `comment_fk_itemId` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `item` (
  `id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `rating` double DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `rating` (
  `id` int(11) NOT NULL,
  `rating` double NOT NULL,
  `item_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ItemId` (`item_id`),
  CONSTRAINT `rating_fk_ItemId` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `id_gen` (
  `GEN_NAME` varchar(45) NOT NULL,
  `GEN_VALUE` int(11) DEFAULT NULL,
  PRIMARY KEY (`GEN_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci