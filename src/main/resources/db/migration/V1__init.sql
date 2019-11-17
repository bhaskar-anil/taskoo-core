
CREATE TABLE IF NOT EXISTS `m_category` (
  `category_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `commission` BIGINT(10) NOT NULL,
  `commission_type` INT(2) NOT NULL,
  `description` TEXT NOT NULL,
  `sys_create_datetime` DATETIME DEFAULT NULL,
  `sys_create_program` VARCHAR(255) DEFAULT NULL,
  `sys_create_user` VARCHAR(255) DEFAULT NULL,
  `sys_update_datetime` DATETIME DEFAULT NULL,
  `sys_update_program` VARCHAR(255) DEFAULT NULL,
  `sys_update_user` VARCHAR(255) DEFAULT NULL,
  `sys_delete_flag` BIT(1) DEFAULT NULL,
  `sys_update_count` INT(10) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `m_category_hist` (
  `category_hist_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `category_id` BIGINT(20) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `commission` BIGINT(10) NOT NULL,
  `commission_type` INT(2) NOT NULL,
  `description` TEXT NOT NULL,
  `sys_create_datetime` DATETIME DEFAULT NULL,
  `sys_create_program` VARCHAR(255) DEFAULT NULL,
  `sys_create_user` VARCHAR(255) DEFAULT NULL,
  `sys_update_datetime` DATETIME DEFAULT NULL,
  `sys_update_program` VARCHAR(255) DEFAULT NULL,
  `sys_update_user` VARCHAR(255) DEFAULT NULL,
  `sys_delete_flag` BIT(1) DEFAULT NULL,
  `sys_update_count` INT(10) DEFAULT NULL,
  PRIMARY KEY (`category_hist_id`)
) ENGINE=InnoDB;


CREATE TABLE IF NOT EXISTS `t_task` (
  `task_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `location` VARCHAR(255)  DEFAULT NULL,
  `task_date_time` DATETIME NULL,
  `task_due_date_time` DATETIME NOT NULL,
  `description` TEXT NOT NULL,
  `estimate_amount` INT(10) DEFAULT NULL,
  `estimate_type` INT(2) NOT NULL,
  `task_type` INT(2) NOT NULL,
  `category_id` BIGINT(3) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `seeker_id` BIGINT(20) NOT NULL,
  `task_status` INT(2) NOT NULL,
  `sys_create_datetime` DATETIME DEFAULT NULL,
  `sys_create_program` VARCHAR(255) DEFAULT NULL,
  `sys_create_user` VARCHAR(255) DEFAULT NULL,
  `sys_update_datetime` DATETIME DEFAULT NULL,
  `sys_update_program` VARCHAR(255) DEFAULT NULL,
  `sys_update_user` VARCHAR(255) DEFAULT NULL,
  `sys_delete_flag` BIT(1) DEFAULT NULL,
  `sys_update_count` INT(10) DEFAULT NULL,
  PRIMARY KEY (`task_id`),
  CONSTRAINT `fk_task_category` FOREIGN KEY (`category_id`)
  REFERENCES `m_category`(`category_id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `t_task_hist` (
  `task_hist_id` BIGINT(20) NOT NULL AUTO_INCREMENT, 
  `task_id` BIGINT(20) NOT NULL,
  `location` VARCHAR(255)  DEFAULT NULL,
  `task_due_date_time` DATETIME NOT NULL,
  `task_date_time` DATETIME NOT NULL,
  `description` TEXT NOT NULL,
  `estimate_amount` INT(10) DEFAULT NULL,
  `estimate_type` INT(2) NOT NULL,
  `task_type` INT(2) NOT NULL,
  `category_id` BIGINT(3) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `seeker_id` BIGINT(20) NOT NULL,
  `task_status` INT(2) NOT NULL,
  `sys_create_datetime` DATETIME DEFAULT NULL,
  `sys_create_program` VARCHAR(255) DEFAULT NULL,
  `sys_create_user` VARCHAR(255) DEFAULT NULL,
  `sys_update_datetime` DATETIME DEFAULT NULL,
  `sys_update_program` VARCHAR(255) DEFAULT NULL,
  `sys_update_user` VARCHAR(255) DEFAULT NULL,
  `sys_delete_flag` BIT(1) DEFAULT NULL,
  `sys_update_count` INT(10) DEFAULT NULL,
  PRIMARY KEY (`task_hist_id`)
) ENGINE=InnoDB;


CREATE TABLE IF NOT EXISTS `t_offer` (
  `offer_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `task_id` BIGINT(20)  NOT NULL,
  `tasker_id` BIGINT(20)  NOT NULL,
  `offer_amount` INT(10) DEFAULT NULL,
  `status` INT(2) NOT NULL,
  `note` TEXT NOT NULL,
  `offer_date_time` DATETIME NOT NULL,
  `sys_create_datetime` DATETIME DEFAULT NULL,
  `sys_create_program` VARCHAR(255) DEFAULT NULL,
  `sys_create_user` VARCHAR(255) DEFAULT NULL,
  `sys_update_datetime` DATETIME DEFAULT NULL,
  `sys_update_program` VARCHAR(255) DEFAULT NULL,
  `sys_update_user` VARCHAR(255) DEFAULT NULL,
  `sys_delete_flag` BIT(1) DEFAULT NULL,
  `sys_update_count` INT(10) DEFAULT NULL,
  PRIMARY KEY (`offer_id`),
  CONSTRAINT `fk_offer_task` FOREIGN KEY (`task_id`)
  REFERENCES `t_task`(`task_id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `t_offer_hist` (
  `offer_hist_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `offer_id` BIGINT(20) NOT NULL,
  `task_id` BIGINT(20)  NOT NULL,
  `offer_amount` INT(10) DEFAULT NULL,
  `tasker_id` BIGINT(20)  NOT NULL,
  `status` INT(2) NOT NULL,
  `note` TEXT NOT NULL,
  `offer_date_time` DATETIME NOT NULL,
  `sys_create_datetime` DATETIME DEFAULT NULL,
  `sys_create_program` VARCHAR(255) DEFAULT NULL,
  `sys_create_user` VARCHAR(255) DEFAULT NULL,
  `sys_update_datetime` DATETIME DEFAULT NULL,
  `sys_update_program` VARCHAR(255) DEFAULT NULL,
  `sys_update_user` VARCHAR(255) DEFAULT NULL,
  `sys_delete_flag` BIT(1) DEFAULT NULL,
  `sys_update_count` INT(10) DEFAULT NULL,
  PRIMARY KEY (`offer_hist_id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `t_offer_comment` (
  `offer_comment_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `offer_id` BIGINT(20)  NOT NULL,
  `commenter_id` BIGINT(20)  NOT NULL,
  `comment` TEXT NOT NULL,
  `comment_date_time` DATETIME NOT NULL,
  `sys_create_datetime` DATETIME DEFAULT NULL,
  `sys_create_program` VARCHAR(255) DEFAULT NULL,
  `sys_create_user` VARCHAR(255) DEFAULT NULL,
  `sys_update_datetime` DATETIME DEFAULT NULL,
  `sys_update_program` VARCHAR(255) DEFAULT NULL,
  `sys_update_user` VARCHAR(255) DEFAULT NULL,
  `sys_delete_flag` BIT(1) DEFAULT NULL,
  `sys_update_count` INT(10) DEFAULT NULL,
  PRIMARY KEY (`offer_comment_id`),
  CONSTRAINT `fk_offer_comment_task` FOREIGN KEY (`offer_id`)
  REFERENCES `t_offer`(`offer_id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `t_task_question` (
  `task_question_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `parent_id` BIGINT(20) NULL,
  `user_id` BIGINT(20)  NOT NULL,
  `task_id` BIGINT(20) NOT NULL,
  `statement` TEXT NOT NULL,
  `create_date_time` DATETIME NOT NULL,
  `sys_create_datetime` DATETIME DEFAULT NULL,
  `sys_create_program` VARCHAR(255) DEFAULT NULL,
  `sys_create_user` VARCHAR(255) DEFAULT NULL,
  `sys_update_datetime` DATETIME DEFAULT NULL,
  `sys_update_program` VARCHAR(255) DEFAULT NULL,
  `sys_update_user` VARCHAR(255) DEFAULT NULL,
  `sys_delete_flag` BIT(1) DEFAULT NULL,
  `sys_update_count` INT(10) DEFAULT NULL,
  PRIMARY KEY (`task_question_id`),
  CONSTRAINT `fk_question_self` FOREIGN KEY (`parent_id`)
  REFERENCES `t_task_question`(`task_question_id`),
   CONSTRAINT `fk_question_task` FOREIGN KEY (`task_id`)
  REFERENCES `t_task`(`task_id`)
) ENGINE=InnoDB;