-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.7.24 - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para cocherabbdd
CREATE DATABASE IF NOT EXISTS `cocherabbdd` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cocherabbdd`;

-- Volcando estructura para tabla cocherabbdd.cliente
DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `apellidos` varchar(255) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `dni` varchar(15) NOT NULL,
  `email` varchar(255) NOT NULL,
  `presupuesto` int(11) NOT NULL DEFAULT '0',
  `fechaRegistro` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `descripcionVehiculo` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla cocherabbdd.cliente: ~50 rows (aproximadamente)
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` (`id`, `nombre`, `apellidos`, `telefono`, `dni`, `email`, `presupuesto`, `fechaRegistro`, `descripcionVehiculo`) VALUES
	(1, 'Cahra', 'Swannie', '943 945 7634', '80695379-D', 'cswannie0@ibm.com', 1956, '2020-11-04 22:49:12', 'Descripcion Editada'),
	(2, 'Nealson', 'Harroll', '827 995 0539', '66701078-T', 'nharroll1@theglobeandmail.com', 1257, '2020-11-04 22:49:12', NULL),
	(3, 'Cesar', 'Gleeton', '317 496 1767', '65176089-A', 'cgleeton2@w3.org', 112, '2020-11-04 22:49:12', NULL),
	(4, 'Marcela', 'Lundberg', '170 692 6021', '48626259-J', 'mlundberg3@spotify.com', 477, '2020-11-04 22:49:12', NULL),
	(5, 'Kit', 'Spraberry', '687 262 6074', '96529312-E', 'kspraberry4@miitbeian.gov.cn', 470, '2020-11-04 22:49:12', NULL),
	(6, 'Amara', 'Gosnall', '128 397 0767', '68270988-M', 'agosnall5@elpais.com', 1481, '2020-11-04 22:49:12', NULL),
	(7, 'Korney', 'Comber', '782 736 2482', '26035145-G', 'kcomber6@rambler.ru', 1106, '2020-11-04 22:49:12', NULL),
	(8, 'Corinne', 'Vears', '180 531 0179', '66070749-M', 'cvears7@apple.com', 1832, '2020-11-04 22:49:12', NULL),
	(9, 'Teri', 'MacCaull', '203 459 8160', '75504914-T', 'tmaccaull8@unblog.fr', 743, '2020-11-04 22:49:12', NULL),
	(10, 'Ginni', 'Stickels', '688 719 3401', '02207878-T', 'gstickels9@multiply.com', 129, '2020-11-04 22:49:12', NULL),
	(11, 'Byrle', 'Linwood', '859 199 0452', '85462236-A', 'blinwooda@alibaba.com', 659, '2020-11-04 22:49:12', NULL),
	(12, 'Constancy', 'Hasley', '935 206 8218', '43302214-R', 'chasleyb@yandex.ru', 554, '2020-11-04 22:49:12', NULL),
	(13, 'Valene', 'Rizzello', '786 154 3464', '91419439-M', 'vrizzelloc@opera.com', 800, '2020-11-04 22:49:12', NULL),
	(14, 'Jo-anne', 'Berford', '552 139 0478', '78424955-R', 'jberfordd@godaddy.com', 1037, '2020-11-04 22:49:12', NULL),
	(15, 'Eileen', 'Pinniger', '604 301 7774', '80487049-J', 'epinnigere@over-blog.com', 275, '2020-11-04 22:49:12', NULL),
	(16, 'Jodee', 'Raynor', '109 713 1992', '85484193-X', 'jraynorf@bloglovin.com', 843, '2020-11-04 22:49:12', NULL),
	(17, 'Kevyn', 'Vahey', '638 871 0829', '00138368-H', 'kvaheyg@delicious.com', 711, '2020-11-04 22:49:12', NULL),
	(18, 'Bibbie', 'Nurny', '461 627 1230', '24371276-K', 'bnurnyh@go.com', 231, '2020-11-04 22:49:12', NULL),
	(19, 'Cesare', 'Admans', '281 251 0244', '91249615-P', 'cadmansi@nsw.gov.au', 112, '2020-11-04 22:49:12', NULL),
	(20, 'Herbert', 'Dignum', '880 379 6027', '79855625-Y', 'hdignumj@list-manage.com', 1146, '2020-11-04 22:49:12', NULL),
	(21, 'Gaspar', 'Fleckness', '406 121 1710', '19486443-N', 'gflecknessk@reverbnation.com', 507, '2020-11-04 22:49:12', NULL),
	(22, 'Roberta', 'Boucher', '796 931 3974', '22230149-C', 'rboucherl@marketwatch.com', 975, '2020-11-04 22:49:12', NULL),
	(23, 'Donnell', 'Gaynsford', '323 598 2795', '35292623-S', 'dgaynsfordm@symantec.com', 458, '2020-11-04 22:49:12', NULL),
	(24, 'Bab', 'Smythin', '831 445 6130', '53646367-Z', 'bsmythinn@google.fr', 1310, '2020-11-04 22:49:12', NULL),
	(25, 'Octavia', 'Jans', '684 322 8739', '25275378-W', 'ojanso@cbsnews.com', 1109, '2020-11-04 22:49:12', NULL),
	(26, 'Kimberly', 'Virgoe', '539 489 8541', '38613349-E', 'kvirgoep@wordpress.org', 523, '2020-11-04 22:49:12', NULL),
	(27, 'Olivette', 'Tutin', '197 157 8439', '08311991-M', 'otutinq@hugedomains.com', 961, '2020-11-04 22:49:12', NULL),
	(28, 'Dave', 'Adamsky', '136 982 2506', '31472014-C', 'dadamskyr@engadget.com', 478, '2020-11-04 22:49:12', NULL),
	(29, 'Quintus', 'Moresby', '616 455 9272', '72718533-T', 'qmoresbys@wisc.edu', 1727, '2020-11-04 22:49:12', NULL),
	(30, 'Malorie', 'Huygens', '648 880 9892', '24362791-Q', 'mhuygenst@jigsy.com', 1940, '2020-11-04 22:49:12', NULL),
	(31, 'Merwyn', 'Duval', '693 469 6392', '77813596-X', 'mduvalu@europa.eu', 1561, '2020-11-04 22:49:12', NULL),
	(32, 'Ruthy', 'Boatswain', '872 558 9687', '82099719-S', 'rboatswainv@comcast.net', 960, '2020-11-04 22:49:12', NULL),
	(33, 'Katheryn', 'Allchorn', '117 425 1550', '33517300-F', 'kallchornw@joomla.org', 825, '2020-11-04 22:49:12', NULL),
	(34, 'Courtnay', 'Curwen', '113 626 1985', '53295513-V', 'ccurwenx@hexun.com', 1107, '2020-11-04 22:49:12', NULL),
	(35, 'Bernadette', 'McGloin', '353 194 7322', '23570957-K', 'bmcgloiny@miibeian.gov.cn', 703, '2020-11-04 22:49:12', NULL),
	(36, 'Millard', 'Kuhle', '418 513 7461', '58975926-F', 'mkuhlez@cnbc.com', 851, '2020-11-04 22:49:12', NULL),
	(37, 'Arlyn', 'Clarycott', '493 649 2680', '58641747-B', 'aclarycott10@seesaa.net', 846, '2020-11-04 22:49:12', NULL),
	(38, 'Scott', 'Sarfatti', '338 547 8524', '06433948-W', 'ssarfatti11@bing.com', 636, '2020-11-04 22:49:12', NULL),
	(39, 'Orly', 'Pruce', '954 103 0104', '33636253-C', 'opruce12@rakuten.co.jp', 430, '2020-11-04 22:49:12', NULL),
	(40, 'Jesse', 'Haithwaite', '534 299 3829', '53118928-J', 'jhaithwaite13@reuters.com', 1163, '2020-11-04 22:49:12', NULL),
	(41, 'Bevon', 'Strooband', '129 422 5823', '38461009-D', 'bstrooband14@discuz.net', 1421, '2020-11-04 22:49:12', NULL),
	(42, 'Germaine', 'Pyzer', '722 487 5032', '15752484-X', 'gpyzer15@abc.net.au', 140, '2020-11-04 22:49:12', NULL),
	(43, 'Alon', 'McGrady', '160 194 6799', '92652940-R', 'amcgrady16@disqus.com', 1603, '2020-11-04 22:49:12', NULL),
	(44, 'Eilis', 'Bolens', '645 829 1967', '51007659-S', 'ebolens17@dmoz.org', 996, '2020-11-04 22:49:12', NULL),
	(45, 'Marketa', 'Canby', '859 831 8923', '06089714-B', 'mcanby18@tinyurl.com', 815, '2020-11-04 22:49:12', NULL),
	(46, 'Gail', 'Balhatchet', '640 838 7703', '96173128-Z', 'gbalhatchet19@psu.edu', 683, '2020-11-04 22:49:12', NULL),
	(47, 'Maura', 'Frostdicke', '519 732 0159', '69533604-M', 'mfrostdicke1a@answers.com', 1624, '2020-11-04 22:49:12', NULL),
	(48, 'Kendrick', 'Starrs', '293 701 1086', '25612608-F', 'kstarrs1b@tinyurl.com', 705, '2020-11-04 22:49:12', NULL),
	(49, 'Courtney', 'Smallpeice', '977 955 4079', '01604345-Z', 'csmallpeice1c@ycombinator.com', 520, '2020-11-04 22:49:12', NULL),
	(50, 'Melodee', 'Klimes', '635 866 6922', '56332650-Y', 'mklimes1d@scribd.com', 899, '2020-11-04 22:49:12', NULL);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;

-- Volcando estructura para tabla cocherabbdd.concesionarios
DROP TABLE IF EXISTS `concesionarios`;
CREATE TABLE IF NOT EXISTS `concesionarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ciudad` varchar(45) NOT NULL,
  `provincia` varchar(45) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `cp` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla cocherabbdd.concesionarios: ~10 rows (aproximadamente)
/*!40000 ALTER TABLE `concesionarios` DISABLE KEYS */;
INSERT INTO `concesionarios` (`id`, `ciudad`, `provincia`, `direccion`, `cp`) VALUES
	(1, 'Jacksonville', 'Florida', '28164 Goodland Pass', '32209'),
	(2, 'Raleigh', 'North Carolina', '256 North Trail', '27615'),
	(3, 'San Jose', 'California', '339 Manitowish Junction', '95173'),
	(4, 'Austin', 'Texas', '75065 Glacier Hill Road', '78759'),
	(5, 'Hot Springs National Park', 'Arkansas', '19 American Park', '71914'),
	(6, 'New Orleans', 'Louisiana', '16 Pankratz Center', '70179'),
	(7, 'Chicago', 'Illinois', '0503 Butternut Center', '60604'),
	(8, 'Albuquerque', 'New Mexico', '30146 Swallow Street', '87110'),
	(9, 'Columbus', 'Ohio', '22 Stang Drive', '43204'),
	(10, 'Minneapolis', 'Minnesota', '919 Waywood Street', '55412');
/*!40000 ALTER TABLE `concesionarios` ENABLE KEYS */;

-- Volcando estructura para tabla cocherabbdd.especialidades
DROP TABLE IF EXISTS `especialidades`;
CREATE TABLE IF NOT EXISTS `especialidades` (
  `tipoID` int(11) NOT NULL,
  `mecanicoID` int(11) NOT NULL,
  PRIMARY KEY (`tipoID`,`mecanicoID`),
  KEY `fk_Tipos_Vehiculos_has_Mecanicos_Mecanicos1_idx` (`mecanicoID`),
  KEY `fk_Tipos_Vehiculos_has_Mecanicos_Tipos_Vehiculos1_idx` (`tipoID`),
  CONSTRAINT `fk_especialidades_mecanicos` FOREIGN KEY (`mecanicoID`) REFERENCES `mecanicos` (`id`),
  CONSTRAINT `fk_especialidades_tipos` FOREIGN KEY (`tipoID`) REFERENCES `tipos_vehiculos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla cocherabbdd.especialidades: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `especialidades` DISABLE KEYS */;
/*!40000 ALTER TABLE `especialidades` ENABLE KEYS */;

-- Volcando estructura para tabla cocherabbdd.jefe
DROP TABLE IF EXISTS `jefe`;
CREATE TABLE IF NOT EXISTS `jefe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuarioID` int(11) NOT NULL,
  PRIMARY KEY (`id`,`usuarioID`),
  KEY `fk_Jefe_usuarios1_idx` (`usuarioID`),
  CONSTRAINT `fk_jefe_usuarios` FOREIGN KEY (`usuarioID`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla cocherabbdd.jefe: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `jefe` DISABLE KEYS */;
INSERT INTO `jefe` (`id`, `usuarioID`) VALUES
	(1, 41);
/*!40000 ALTER TABLE `jefe` ENABLE KEYS */;

-- Volcando estructura para tabla cocherabbdd.mecanicos
DROP TABLE IF EXISTS `mecanicos`;
CREATE TABLE IF NOT EXISTS `mecanicos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuarioID` int(11) NOT NULL,
  `esJefe` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`,`usuarioID`),
  KEY `fk_mecanicos_usuarios1_idx` (`usuarioID`),
  CONSTRAINT `fk_mecanicos_usuarios1` FOREIGN KEY (`usuarioID`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla cocherabbdd.mecanicos: ~20 rows (aproximadamente)
/*!40000 ALTER TABLE `mecanicos` DISABLE KEYS */;
INSERT INTO `mecanicos` (`id`, `usuarioID`, `esJefe`) VALUES
	(1, 3, 1),
	(2, 5, 1),
	(3, 7, 0),
	(4, 14, 1),
	(5, 16, 0),
	(6, 18, 0),
	(7, 21, 0),
	(8, 23, 0),
	(9, 24, 0),
	(10, 25, 0),
	(11, 26, 1),
	(12, 29, 0),
	(13, 30, 0),
	(14, 31, 1),
	(15, 32, 1),
	(16, 33, 1),
	(17, 34, 0),
	(18, 36, 1),
	(19, 38, 0),
	(20, 40, 1);
/*!40000 ALTER TABLE `mecanicos` ENABLE KEYS */;

-- Volcando estructura para tabla cocherabbdd.propuesta_venta
DROP TABLE IF EXISTS `propuesta_venta`;
CREATE TABLE IF NOT EXISTS `propuesta_venta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clienteID` int(11) NOT NULL,
  `vendedorID` int(11) NOT NULL,
  `vehiculoVenderID` int(11) NOT NULL,
  `estado` enum('aceptada','pendiente','rechazada') NOT NULL DEFAULT 'pendiente',
  `fechaInicio` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fechaFin` datetime DEFAULT NULL,
  `fechaLimite` date NOT NULL,
  `presupuesto` int(11) NOT NULL,
  PRIMARY KEY (`id`,`vendedorID`,`vehiculoVenderID`,`clienteID`),
  KEY `fk_propuesta_venta_vendedores1_idx` (`vendedorID`),
  KEY `fk_propuesta_venta_vehiculos_Vender1_idx` (`vehiculoVenderID`),
  KEY `fk_propuesta_venta_cliente1_idx` (`clienteID`),
  CONSTRAINT `fk_propuesta_venta_cliente1` FOREIGN KEY (`clienteID`) REFERENCES `cliente` (`id`),
  CONSTRAINT `fk_propuesta_venta_vehiculos_Vender1` FOREIGN KEY (`vehiculoVenderID`) REFERENCES `vehiculos_vender` (`id`),
  CONSTRAINT `fk_propuesta_venta_vendedores1` FOREIGN KEY (`vendedorID`) REFERENCES `vendedores` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla cocherabbdd.propuesta_venta: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `propuesta_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `propuesta_venta` ENABLE KEYS */;

-- Volcando estructura para tabla cocherabbdd.reparaciones
DROP TABLE IF EXISTS `reparaciones`;
CREATE TABLE IF NOT EXISTS `reparaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mecanicoID` int(11) NOT NULL,
  `clienteID` int(11) NOT NULL,
  `vehiculoRepararID` int(11) NOT NULL,
  `tiempoEstimado` time NOT NULL,
  `tiempoReal` time DEFAULT NULL,
  `presupuestoEstimado` int(11) NOT NULL,
  `presupuestoReal` int(11) DEFAULT NULL,
  `componentes` text,
  `fechaInicio` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fechaFin` date DEFAULT NULL,
  `estado` enum('finalizado','proceso','pendiente') NOT NULL DEFAULT 'pendiente',
  PRIMARY KEY (`id`,`mecanicoID`,`vehiculoRepararID`,`clienteID`),
  KEY `fk_reparaciones_mecanicos1_idx` (`mecanicoID`),
  KEY `fk_reparaciones_vehiculos_reparar1_idx` (`vehiculoRepararID`),
  KEY `fk_reparaciones_cliente1_idx` (`clienteID`),
  CONSTRAINT `fk_reparaciones_cliente1` FOREIGN KEY (`clienteID`) REFERENCES `cliente` (`id`),
  CONSTRAINT `fk_reparaciones_mecanicos1` FOREIGN KEY (`mecanicoID`) REFERENCES `mecanicos` (`id`),
  CONSTRAINT `fk_reparaciones_vehiculos_reparar1` FOREIGN KEY (`vehiculoRepararID`) REFERENCES `vehiculos_reparar` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla cocherabbdd.reparaciones: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `reparaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `reparaciones` ENABLE KEYS */;

-- Volcando estructura para tabla cocherabbdd.tipos_vehiculos
DROP TABLE IF EXISTS `tipos_vehiculos`;
CREATE TABLE IF NOT EXISTS `tipos_vehiculos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla cocherabbdd.tipos_vehiculos: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `tipos_vehiculos` DISABLE KEYS */;
INSERT INTO `tipos_vehiculos` (`id`, `descripcion`) VALUES
	(1, 'Coche'),
	(2, 'Motocicleta'),
	(3, 'Ciclomotor');
/*!40000 ALTER TABLE `tipos_vehiculos` ENABLE KEYS */;

-- Volcando estructura para tabla cocherabbdd.usuarios
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL,
  `concesionarioID` int(11) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `tipo` enum('vendedor','mecanico','jefe') NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellidos` varchar(255) NOT NULL,
  `dni` varchar(15) NOT NULL,
  `email` varchar(255) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `salario` int(11) NOT NULL,
  PRIMARY KEY (`id`,`concesionarioID`),
  KEY `fk_usuarios_concesionarios1_idx` (`concesionarioID`),
  CONSTRAINT `fk_usuarios_concesionarios1` FOREIGN KEY (`concesionarioID`) REFERENCES `concesionarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla cocherabbdd.usuarios: ~41 rows (aproximadamente)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`id`, `concesionarioID`, `login`, `password`, `tipo`, `nombre`, `apellidos`, `dni`, `email`, `telefono`, `salario`) VALUES
	(1, 7, 'Svetogorsk', 'YeRP8hZ431', 'vendedor', 'Powell', 'Spittall', '44677249-T', 'pspittall0@washingtonpost.com', '933-260-7969', 2873),
	(2, 2, 'Kopashnovo', 'acLzQJQ4Cfd', 'vendedor', 'Annora', 'Morilla', '87769790-Z', 'amorilla1@usgs.gov', '421-705-5013', 6234),
	(3, 5, 'Joliet', 'LNy0qSHT', 'mecanico', 'Cyril', 'Tunniclisse', '49325243-Q', 'ctunniclisse2@cocolog-nifty.com', '815-609-1235', 1457),
	(4, 10, 'Dankunku', 'vBByLklt', 'vendedor', 'Kassi', 'Gyorgy', '11612692-A', 'kgyorgy3@ezinearticles.com', '996-685-5700', 7212),
	(5, 6, 'Lanot', '6l0DJWvI7EN', 'mecanico', 'Mathew', 'O\'Fielly', '66136091-W', 'mofielly4@mit.edu', '535-123-3180', 1952),
	(6, 1, 'Margara', 'hDbZNx5', 'vendedor', 'Faunie', 'Kerswell', '70116824-W', 'fkerswell5@altervista.org', '300-682-4090', 1445),
	(7, 5, 'Štítná nad Vláří', '4E3Urp0YvZfV', 'mecanico', 'Kit', 'MacKey', '54522713-R', 'kmackey6@patch.com', '646-456-5748', 8430),
	(8, 7, 'Miguelópolis', 'tHe0rE2', 'vendedor', 'Jens', 'Nottle', '95381669-K', 'jnottle7@springer.com', '319-106-3659', 4324),
	(9, 7, 'Xieshui', '3c3Ay08', 'vendedor', 'Martynne', 'Aldham', '57993354-W', 'maldham8@wikispaces.com', '102-831-0171', 5317),
	(10, 10, 'Örnsköldsvik', 'jZNmGUCfFMhp', 'vendedor', 'Lavina', 'Swanborough', '95522817-U', 'lswanborough9@adobe.com', '102-208-4831', 4437),
	(11, 10, 'Xinjian', 'nIoiJrDsI', 'vendedor', 'Justinian', 'Vaughan-Hughes', '40182340-F', 'jvaughanhughesa@sciencedirect.com', '238-413-4342', 9861),
	(12, 6, 'Songyuan', 's3uppz5b', 'vendedor', 'Ulberto', 'Sear', '70698114-K', 'usearb@hugedomains.com', '469-301-8838', 4225),
	(13, 1, 'Puzi', 's8KBUS', 'vendedor', 'Jermayne', 'Tuck', '61561444-X', 'jtuckc@surveymonkey.com', '983-488-4252', 7956),
	(14, 7, 'Harrison', 'wekgpjc4lK', 'mecanico', 'Tiffani', 'MacDuffie', '90701962-G', 'tmacduffied@epa.gov', '358-911-3658', 8636),
	(15, 6, 'Loei', 'qKusUm', 'vendedor', 'Teriann', 'Pohls', '94290112-S', 'tpohlse@dyndns.org', '957-598-6726', 7472),
	(16, 7, 'Kang', 'CdCgieNZ', 'mecanico', 'Talbot', 'Skullet', '91087569-M', 'tskulletf@bravesites.com', '849-300-6776', 2345),
	(17, 1, 'Zhongshan Donglu', 'HEqg42FHEJE', 'vendedor', 'Nina', 'Robertsson', '25643360-D', 'nrobertssong@columbia.edu', '667-745-7923', 9508),
	(18, 2, 'Ciherang', 'G532Xz8sV', 'mecanico', 'Charmane', 'Filisov', '84257430-C', 'cfilisovh@soundcloud.com', '367-368-9671', 7262),
	(19, 3, 'Fort Wayne', 'KidM3ATbU', 'vendedor', 'Katy', 'Aymer', '47695094-P', 'kaymeri@comsenz.com', '260-538-9788', 9494),
	(20, 1, 'Xinzheng', 'hl6xeZP', 'vendedor', 'Ulrich', 'Chapiro', '18989229-X', 'uchapiroj@wsj.com', '891-532-7017', 7990),
	(21, 2, 'Le Lamentin', 'qPRdn6ny', 'mecanico', 'Adelheid', 'Dovinson', '68254080-A', 'adovinsonk@blogger.com', '374-371-2712', 5832),
	(22, 8, 'Sakai', 'oBriFXi', 'vendedor', 'Wayland', 'Bruty', '24652666-J', 'wbrutyl@army.mil', '229-256-4775', 6826),
	(23, 1, 'Huizhai', 'T3TLwfm9rxZs', 'mecanico', 'Pietro', 'Quiddington', '11021181-X', 'pquiddingtonm@cafepress.com', '349-167-7383', 7579),
	(24, 6, 'Balucawi', 'UucjSRoAng', 'mecanico', 'Roxanne', 'Gillhespy', '47320102-W', 'rgillhespyn@google.ca', '622-918-0873', 6789),
	(25, 6, 'Niaojin', '5aWvZP', 'mecanico', 'Jyoti', 'McWhorter', '14900404-M', 'jmcwhortero@intel.com', '864-926-3882', 5320),
	(26, 8, 'Nagai', '42Wd2emq', 'mecanico', 'Filia', 'Ricker', '85102949-L', 'frickerp@imgur.com', '137-938-7164', 5771),
	(27, 9, 'Santa Rosa', 'DCgbKaxbi', 'vendedor', 'Therese', 'Sneden', '03606610-D', 'tsnedenq@slideshare.net', '251-485-7834', 1761),
	(28, 3, 'Dongxin', '8umpxBNEwl5P', 'vendedor', 'Jandy', 'Dobsons', '34419380-P', 'jdobsonsr@sbwire.com', '856-141-3511', 7866),
	(29, 3, 'Sarvaš', 'gLBCtHzZJB', 'mecanico', 'Johnnie', 'Pawelec', '89955361-B', 'jpawelecs@discuz.net', '923-149-3415', 1783),
	(30, 2, 'Taihe', 'vKfFFE0Ahs', 'mecanico', 'Mattie', 'Muscat', '30956274-K', 'mmuscatt@adobe.com', '970-994-2513', 1411),
	(31, 3, 'Strömsund', 'RcXBNyWV', 'mecanico', 'Cindi', 'Kynastone', '77575163-O', 'ckynastoneu@scribd.com', '388-216-7058', 7336),
	(32, 10, 'Anabar', 'UALAW6AS3', 'mecanico', 'Valentine', 'Ivison', '68709344-B', 'vivisonv@go.com', '355-997-3436', 2690),
	(33, 9, 'Dasuk', '0j4Y1F2QgTtM', 'mecanico', 'Elianora', 'Bish', '91012470-Z', 'ebishw@artisteer.com', '685-735-2185', 6954),
	(34, 6, 'Am Djarass', '3uy7TyNyFIo', 'mecanico', 'Viviyan', 'Treadger', '52687625-Q', 'vtreadgerx@tinyurl.com', '867-218-9499', 2405),
	(35, 5, 'Bokino', 'CBGR6N', 'vendedor', 'Pinchas', 'Hosburn', '45995556-R', 'phosburny@nymag.com', '224-865-2164', 1016),
	(36, 2, 'Lunec', 'YRkCYd', 'mecanico', 'Josiah', 'Mannock', '17387253-V', 'jmannockz@hexun.com', '890-263-6542', 5494),
	(37, 6, 'Kassala', 'CBbqrSx', 'vendedor', 'Bret', 'Earles', '05542660-S', 'bearles10@ucla.edu', '430-527-6716', 3433),
	(38, 6, 'Bellevue', 'YLFc5Dxb', 'mecanico', 'Dimitri', 'Marshallsay', '87804743-N', 'dmarshallsay11@altervista.org', '206-686-1229', 3542),
	(39, 8, 'Buy', 'GLSspiuFUNi7', 'vendedor', 'Liane', 'Laycock', '86384251-D', 'llaycock12@hubpages.com', '450-825-4348', 5605),
	(40, 1, 'Tozkhurmato', 'S06GUpr', 'mecanico', 'Lela', 'Formigli', '32984420-A', 'lformigli13@wiley.com', '119-634-2011', 7334),
	(41, 1, 'JEFE', '1234', 'vendedor', 'Hector', 'Canas Moreno', '11001100-J', 'hector@gmail.com', '656-582-709', 9999);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

-- Volcando estructura para tabla cocherabbdd.vehiculos
DROP TABLE IF EXISTS `vehiculos`;
CREATE TABLE IF NOT EXISTS `vehiculos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipoID` int(11) NOT NULL,
  `concesionarioID` int(11) NOT NULL,
  `potencia` varchar(45) NOT NULL,
  `marca` varchar(45) NOT NULL,
  `modelo` varchar(45) NOT NULL,
  `fechaRegistro` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`,`tipoID`,`concesionarioID`),
  KEY `fk_vehiculos_Tipos_Vehiculos1_idx` (`tipoID`),
  KEY `fk_vehiculos_concesionarios1_idx` (`concesionarioID`),
  CONSTRAINT `fk_vehiculos_Tipos_Vehiculos1` FOREIGN KEY (`tipoID`) REFERENCES `tipos_vehiculos` (`id`),
  CONSTRAINT `fk_vehiculos_concesionarios1` FOREIGN KEY (`concesionarioID`) REFERENCES `concesionarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla cocherabbdd.vehiculos: ~51 rows (aproximadamente)
/*!40000 ALTER TABLE `vehiculos` DISABLE KEYS */;
INSERT INTO `vehiculos` (`id`, `tipoID`, `concesionarioID`, `potencia`, `marca`, `modelo`, `fechaRegistro`) VALUES
	(1, 1, 1, '42-cv', 'Dodge', 'Ram 2500', '2020-02-06 08:23:03'),
	(2, 3, 9, '67-cv', 'Ford', 'GT500', '2020-01-28 14:18:27'),
	(3, 3, 7, '03-cv', 'Honda', 'Civic', '2020-07-13 21:06:26'),
	(4, 2, 10, '17-cv', 'Kia', 'Sportage', '2019-11-15 17:09:13'),
	(5, 1, 1, '94-cv', 'Buick', 'Rendezvous', '2020-10-21 07:02:10'),
	(6, 3, 10, '97-cv', 'Bentley', 'Azure', '2020-04-28 00:26:23'),
	(7, 3, 5, '96-cv', 'Volkswagen', 'CC', '2020-05-31 07:26:29'),
	(8, 2, 3, '31-cv', 'Chevrolet', '1500', '2020-07-01 21:09:02'),
	(9, 2, 8, '79-cv', 'Suzuki', 'Sidekick', '2020-10-23 07:00:42'),
	(10, 3, 1, '39-cv', 'Honda', 'Civic', '2020-09-02 10:45:15'),
	(11, 3, 9, '49-cv', 'Infiniti', 'G37', '2020-03-28 15:30:51'),
	(12, 3, 8, '25-cv', 'BMW', '6 Series', '2020-02-10 08:09:47'),
	(13, 3, 5, '85-cv', 'Chevrolet', 'Express 2500', '2019-11-13 10:35:27'),
	(14, 2, 9, '75-cv', 'GMC', 'Sierra 3500HD', '2019-12-12 21:27:52'),
	(15, 3, 9, '86-cv', 'Buick', 'LeSabre', '2019-12-20 20:51:44'),
	(16, 3, 7, '85-cv', 'Dodge', 'Ram Van 3500', '2020-10-14 06:52:50'),
	(17, 2, 4, '20-cv', 'Mercedes-Benz', 'M-Class', '2019-12-21 02:04:11'),
	(18, 1, 1, '81-cv', 'Jeep', 'Comanche', '2019-12-16 17:03:10'),
	(19, 3, 3, '65-cv', 'Nissan', 'Pathfinder', '2020-05-08 16:13:55'),
	(20, 3, 2, '53-cv', 'Mercedes-Benz', 'CLS-Class', '2020-01-30 07:53:28'),
	(21, 1, 3, '47-cv', 'Acura2', 'NSX', '2020-01-11 02:05:52'),
	(22, 3, 9, '23-cv', 'Toyota', 'Tacoma', '2020-10-19 13:08:30'),
	(23, 1, 5, '70-cv', 'Mercedes-Benz', 'E-Class', '2020-06-08 10:02:07'),
	(24, 1, 4, '40-cv', 'Honda', 'Civic', '2020-06-13 12:09:36'),
	(25, 3, 5, '53-cv', 'Suzuki', 'Grand Vitara', '2020-09-10 16:53:52'),
	(26, 3, 7, '76-cv', 'Ram', '2500', '2020-04-03 17:53:59'),
	(27, 2, 5, '56-cv', 'Mercury', 'Milan', '2020-01-29 14:20:49'),
	(28, 3, 8, '12-cv', 'Acura', 'CL', '2020-10-08 15:38:47'),
	(29, 1, 1, '68-cv', 'Toyota', '4Runner', '2020-09-10 02:50:41'),
	(30, 3, 3, '41-cv', 'BMW', '530', '2019-12-15 08:44:24'),
	(31, 3, 8, '40-cv', 'Dodge', 'Omni', '2020-02-09 15:37:00'),
	(32, 3, 10, '76-cv', 'Porsche', 'Cayman', '2020-03-14 10:51:55'),
	(33, 1, 9, '78-cv', 'Mitsubishi', 'Lancer Evolution', '2019-12-11 04:40:13'),
	(34, 3, 3, '01-cv', 'Ford', 'F250', '2019-11-28 01:28:06'),
	(35, 2, 1, '94-cv', 'Ford', 'Mustang', '2020-09-12 11:59:24'),
	(36, 2, 5, '72-cv', 'Chevrolet', 'Corsica', '2020-01-14 07:46:38'),
	(37, 3, 2, '64-cv', 'Plymouth', 'Neon', '2019-11-01 04:50:26'),
	(38, 3, 9, '99-cv', 'Volkswagen', 'Touareg', '2019-11-14 21:20:35'),
	(39, 2, 10, '82-cv', 'Mitsubishi', 'Lancer Evolution', '2020-05-31 00:50:04'),
	(40, 1, 10, '33-cv', 'GMC', 'Sonoma Club Coupe', '2020-09-06 14:17:21'),
	(41, 3, 3, '38-cv', 'Dodge', 'Intrepid', '2020-06-14 00:22:43'),
	(42, 3, 2, '82-cv', 'Volvo', 'S90', '2020-03-09 17:41:35'),
	(43, 2, 5, '29-cv', 'Mazda', '626', '2020-01-17 14:48:09'),
	(44, 3, 4, '53-cv', 'Hyundai', 'Tiburon', '2019-12-04 00:28:16'),
	(45, 2, 7, '89-cv', 'Mitsubishi', 'Montero', '2019-12-08 23:14:58'),
	(46, 2, 9, '17-cv', 'Pontiac', 'Fiero', '2020-10-10 03:01:45'),
	(47, 3, 8, '58-cv', 'Chevrolet', 'Astro', '2020-01-03 07:30:45'),
	(48, 2, 4, '51-cv', 'Cadillac', 'CTS', '2020-08-15 17:19:59'),
	(49, 3, 5, '85-cv', 'Ford', 'Fairlane', '2019-11-05 05:01:16'),
	(50, 3, 3, '95-cv', 'Infiniti', 'FX', '2020-10-21 06:30:33'),
	(56, 1, 1, '45-CV', 'Marca', 'Modelo', '2020-11-02 18:44:38');
/*!40000 ALTER TABLE `vehiculos` ENABLE KEYS */;

-- Volcando estructura para tabla cocherabbdd.vehiculos_reparar
DROP TABLE IF EXISTS `vehiculos_reparar`;
CREATE TABLE IF NOT EXISTS `vehiculos_reparar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vehiculoID` int(11) NOT NULL,
  `descripcion` text NOT NULL,
  `matricula` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`,`vehiculoID`),
  KEY `fk_vehiculos_reparar_vehiculos1_idx` (`vehiculoID`),
  CONSTRAINT `fk_vehiculos_reparar_vehiculos1` FOREIGN KEY (`vehiculoID`) REFERENCES `vehiculos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla cocherabbdd.vehiculos_reparar: ~25 rows (aproximadamente)
/*!40000 ALTER TABLE `vehiculos_reparar` DISABLE KEYS */;
INSERT INTO `vehiculos_reparar` (`id`, `vehiculoID`, `descripcion`, `matricula`) VALUES
	(1, 26, 'bujias', NULL),
	(2, 27, 'bateria', NULL),
	(3, 28, 'gomillas', NULL),
	(4, 29, 'bujias', NULL),
	(5, 30, 'baterias', NULL),
	(6, 31, 'embrague', NULL),
	(7, 32, 'gomillas', NULL),
	(8, 33, 'recambio ruedas', NULL),
	(9, 34, 'techo', NULL),
	(10, 35, 'ventana delantera izquierda', NULL),
	(11, 36, 'parabrisas', NULL),
	(12, 37, 'espejo izquierdo', NULL),
	(13, 38, 'pulir faros', NULL),
	(14, 39, 'bujias', NULL),
	(15, 40, 'liquido frenos', NULL),
	(16, 41, 'bateria', NULL),
	(17, 42, 'bujia', NULL),
	(18, 43, 'bateria', NULL),
	(19, 44, 'volante', NULL),
	(20, 45, 'liquido embrague', NULL),
	(21, 46, 'motor', NULL),
	(22, 47, 'bateria', NULL),
	(23, 48, 'gomillas', NULL),
	(24, 49, 'espejo derecha', NULL),
	(25, 50, 'recambio ruedas', NULL);
/*!40000 ALTER TABLE `vehiculos_reparar` ENABLE KEYS */;

-- Volcando estructura para tabla cocherabbdd.vehiculos_vender
DROP TABLE IF EXISTS `vehiculos_vender`;
CREATE TABLE IF NOT EXISTS `vehiculos_vender` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vehiculoID` int(11) NOT NULL,
  `precio` decimal(9,2) NOT NULL DEFAULT '0.00',
  `vendido` tinyint(1) NOT NULL DEFAULT '0',
  `segundaMano` tinyint(1) NOT NULL DEFAULT '0',
  `tiempoUsado` varchar(45) DEFAULT NULL,
  `imagen` mediumtext,
  PRIMARY KEY (`id`,`vehiculoID`),
  KEY `fk_vehiculos_Vender_vehiculos1_idx` (`vehiculoID`),
  CONSTRAINT `fk_vehiculos_Vender_vehiculos1` FOREIGN KEY (`vehiculoID`) REFERENCES `vehiculos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla cocherabbdd.vehiculos_vender: ~25 rows (aproximadamente)
/*!40000 ALTER TABLE `vehiculos_vender` DISABLE KEYS */;
INSERT INTO `vehiculos_vender` (`id`, `vehiculoID`, `precio`, `vendido`, `segundaMano`, `tiempoUsado`, `imagen`) VALUES
	(1, 1, 5954.78, 0, 1, '2 años', 'data:image/png;base64,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'),
	(2, 2, 18401.48, 1, 1, '10', 'Sin imagen'),
	(3, 3, 11780.18, 0, 0, '0', 'Sin imagen'),
	(4, 4, 16357.53, 0, 0, '0', 'Sin imagen'),
	(5, 5, 11439.57, 0, 0, NULL, 'Sin imagen'),
	(6, 6, 426.94, 0, 0, '0', 'Sin imagen'),
	(7, 7, 17756.66, 1, 1, '11', 'Sin imagen'),
	(8, 8, 4419.84, 0, 1, '12', 'Sin imagen'),
	(9, 9, 13899.12, 0, 1, '13', 'Sin imagen'),
	(10, 10, 8119.04, 0, 1, '17', 'Sin imagen'),
	(11, 11, 15799.90, 1, 0, '0', 'Sin imagen'),
	(12, 12, 774.06, 0, 0, '0', 'Sin imagen'),
	(13, 13, 1190.68, 0, 1, '10', 'Sin imagen'),
	(14, 14, 16008.00, 1, 0, '0', 'Sin imagen'),
	(15, 15, 19563.89, 0, 0, '0', 'Sin imagen'),
	(16, 16, 3520.53, 1, 0, '0', 'Sin imagen'),
	(17, 17, 5665.07, 1, 0, '0', 'Sin imagen'),
	(18, 18, 11433.44, 0, 0, NULL, 'Sin imagen'),
	(19, 19, 13129.77, 0, 0, NULL, 'Sin imagen'),
	(20, 20, 12040.86, 0, 1, '19', 'Sin imagen'),
	(21, 21, 14665.27, 0, 0, NULL, 'Sin imagen'),
	(22, 22, 19819.30, 1, 0, '0', 'Sin imagen'),
	(23, 23, 12050.88, 0, 0, '0', 'Sin imagen'),
	(24, 24, 13997.52, 1, 1, '11', 'Sin imagen'),
	(25, 25, 2611.31, 1, 0, '0', 'Sin imagen');
/*!40000 ALTER TABLE `vehiculos_vender` ENABLE KEYS */;

-- Volcando estructura para tabla cocherabbdd.vendedores
DROP TABLE IF EXISTS `vendedores`;
CREATE TABLE IF NOT EXISTS `vendedores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuarioID` int(11) NOT NULL,
  `numVentas` int(5) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`,`usuarioID`),
  KEY `fk_vendedores_usuarios1_idx` (`usuarioID`),
  CONSTRAINT `fk_vendedores_usuarios1` FOREIGN KEY (`usuarioID`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla cocherabbdd.vendedores: ~19 rows (aproximadamente)
/*!40000 ALTER TABLE `vendedores` DISABLE KEYS */;
INSERT INTO `vendedores` (`id`, `usuarioID`, `numVentas`) VALUES
	(1, 1, 40),
	(2, 4, 15),
	(3, 6, 3),
	(4, 8, 19),
	(5, 9, 18),
	(6, 10, 11),
	(7, 11, 13),
	(8, 12, 16),
	(9, 13, 14),
	(10, 15, 4),
	(11, 17, 23),
	(12, 19, 24),
	(13, 20, 25),
	(14, 22, 21),
	(15, 27, 22),
	(16, 28, 23),
	(17, 35, 10),
	(18, 37, 35),
	(19, 39, 15);
/*!40000 ALTER TABLE `vendedores` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
