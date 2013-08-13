--
-- Database: `roperty-ui`
--
CREATE DATABASE IF NOT EXISTS `roperty-ui` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `roperty-ui`;

-- --------------------------------------------------------

--
-- Table: `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varbinary(64) NOT NULL,
  `salt` varbinary(64) NOT NULL,
  `name` varchar(64) NOT NULL,
  `firstname` varchar(64) NOT NULL,
  `email` varchar(128) NOT NULL,
  `permission` text,
  `active` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;