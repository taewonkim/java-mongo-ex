USE identity;

DROP TABLE IF EXISTS `user_in_group`;
DROP TABLE IF EXISTS `contact`;
DROP TABLE IF EXISTS `contact_type`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `group`;

CREATE TABLE IF NOT EXISTS `user` (
  user_uuid CHAR(36) NOT NULL,
  user_id VARCHAR(255) NOT NULL,
  user_password CHAR(41) NOT NULL,
  user_name VARCHAR(255) NOT NULL,
  user_in_user CHAR(36) NULL,
  user_created DATETIME NOT NULL,
  user_deleted DATETIME NULL,
  PRIMARY KEY (user_uuid)
) ENGINE=INNODB DEFAULT CHARACTER SET UTF8;

INSERT INTO `user` (user_uuid, user_id, user_name, user_password, user_in_user, user_created, user_deleted) VALUES (UUID(), 'admin', '관리자', password('1111'), NULL, NOW(), NULL);
INSERT INTO `user` (user_uuid, user_id, user_name, user_password, user_in_user, user_created, user_deleted) VALUES (UUID(), 'gildonghong', '홍길동', password('1111'), NULL, NOW(), NULL);

CREATE TABLE IF NOT EXISTS `group` (
  group_uuid CHAR(36) NOT NULL,
  group_name VARCHAR(255) NOT NULL,
  group_in_group CHAR(36) NULL,
  group_created DATETIME NOT NULL,
  group_deleted DATETIME NULL,
  PRIMARY KEY (group_uuid)
) ENGINE=INNODB DEFAULT CHARACTER SET UTF8;

INSERT INTO `group` (group_uuid, group_name, group_in_group, group_created, group_deleted) VALUES (UUID(), '전체', NULL, NOW(), NULL);

CREATE TABLE IF NOT EXISTS `user_in_group` (
  group_uuid CHAR(36) NOT NULL,
  user_uuid CHAR(36) NOT NULL,
  group_created DATETIME NOT NULL,
  group_deleted DATETIME NULL,
  FOREIGN KEY (group_uuid) REFERENCES `group` (group_uuid) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (user_uuid) REFERENCES `user` (user_uuid) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARACTER SET UTF8;

CREATE TABLE IF NOT EXISTS `contact_type` (
  contact_type_uuid CHAR(36) NOT NULL,
  contact_type_name VARCHAR(255) NOT NULL,
  contact_type_created DATETIME NOT NULL,
  contact_type_deleted DATETIME NULL,
  PRIMARY KEY (contact_type_uuid)
) ENGINE=INNODB DEFAULT CHARACTER SET UTF8;

INSERT INTO `contact_type` (contact_type_uuid, contact_type_name, contact_type_created, contact_type_deleted) VALUE (UUID(), '휴대전화', NOW(), NULL);
INSERT INTO `contact_type` (contact_type_uuid, contact_type_name, contact_type_created, contact_type_deleted) VALUE (UUID(), '사무실', NOW(), NULL);

CREATE TABLE IF NOT EXISTS `contact` (
  user_uuid CHAR(36) NOT NULL,
  contact_type_uuid CHAR(36) NOT NULL,
  contact_number VARCHAR(24) NOT NULL,
  contact_created DATETIME NOT NULL,
  contact_deleted DATETIME NULL,
  FOREIGN KEY (user_uuid) REFERENCES `user` (user_uuid) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (contact_type_uuid) REFERENCES `contact_type` (contact_type_uuid) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARACTER SET UTF8;

