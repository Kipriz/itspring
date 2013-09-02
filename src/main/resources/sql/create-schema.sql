GRANT USAGE ON *.* TO `itspring`@`localhost`;
DROP USER `itspring-user`@`localhost`;

DROP SCHEMA IF EXISTS `itspring`;

CREATE SCHEMA `itspring` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

CREATE USER `itspring-user`@`localhost` IDENTIFIED BY 'itspringpass';
GRANT ALL PRIVILEGES ON itspring.* TO `itspring-user`@`localhost`;