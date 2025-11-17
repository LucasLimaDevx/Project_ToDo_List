CREATE TABLE `tb_task` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(50) NOT NULL,
  `due_date` datetime(6) NOT NULL,
  `status` varchar(12) NOT NULL,
  `title` varchar(50) NOT NULL,
  `category_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgvf2uy07y4m78uly85eh2oxvb` (`category_id`),
  KEY `FKekk0jr4msqy626yiiiyrikprb` (`user_id`),
  CONSTRAINT `FKekk0jr4msqy626yiiiyrikprb` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `FKgvf2uy07y4m78uly85eh2oxvb` FOREIGN KEY (`category_id`) REFERENCES `tb_category` (`id`)
)