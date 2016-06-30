-- phpMyAdmin SQL Dump
-- version 4.0.10.10
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 24, 2016 at 04:59 AM
-- Server version: 5.5.43
-- PHP Version: 5.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `inf226eg`
--

-- --------------------------------------------------------

--
-- Table structure for table `adherent`
--

CREATE TABLE IF NOT EXISTS `adherent` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `lastname` varchar(30) DEFAULT NULL,
  `dni` varchar(8) NOT NULL,
  `observation` varchar(100) DEFAULT NULL,
  `id_politicalParty` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_politicalParty` (`id_politicalParty`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `adherent`
--

-- --------------------------------------------------------

--
-- Table structure for table `adherentImage`
--

CREATE TABLE IF NOT EXISTS `adherentImage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nameSource` varchar(100) DEFAULT NULL,
  `lastnameSource` varchar(100) DEFAULT NULL,
  `dniSource` varchar(100) DEFAULT NULL,
  `fingerprintSource` varchar(100) DEFAULT NULL,
  `signatureSource` varchar(100) DEFAULT NULL,
  `id_politicalParty` bigint(20) DEFAULT NULL,
  `status` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_politicalParty` (`id_politicalParty`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `adherentImage`
--
-----------------------------------------------------

--
-- Table structure for table `electoralProcess`
--

CREATE TABLE IF NOT EXISTS `electoralProcess` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `startRegistrationDate` datetime DEFAULT NULL,
  `endRegistrationDate` datetime DEFAULT NULL,
  `startReceptionDate` datetime DEFAULT NULL,
  `endReceptionDate` datetime DEFAULT NULL,
  `startValidationDate` datetime DEFAULT NULL,
  `endValidationDate` datetime DEFAULT NULL,
  `minPercentage` double DEFAULT NULL,
  `status` varchar(11) DEFAULT NULL,
  `stage` int(11) DEFAULT NULL,
  `population` bigint(20) DEFAULT NULL,
  `id_processType` bigint(20) DEFAULT NULL,
  `id_user` bigint(20) DEFAULT NULL,
  `startExtraReceptionDate` datetime DEFAULT NULL,
  `endExtraReceptionDate` datetime DEFAULT NULL,
  `startExtraValidationDate` datetime DEFAULT NULL,
  `endExtraValidationDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_processType` (`id_processType`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `electoralProcess`
--

-- --------------------------------------------------------

--
-- Table structure for table `politicalParty`
--

CREATE TABLE IF NOT EXISTS `politicalParty` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `legalRepresentative` varchar(20) DEFAULT NULL,
  `telephone` varchar(11) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `id_electoralProcess` bigint(20) DEFAULT NULL,
  `id_worker` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_electoralProcess` (`id_electoralProcess`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `politicalParty`
--

-- --------------------------------------------------------

--
-- Table structure for table `processType`
--

CREATE TABLE IF NOT EXISTS `processType` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `processType`
--

INSERT INTO `processType` (`id`, `name`) VALUES
(1, 'Presidencial'),
(2, 'Regional'),
(3, 'Distrital'),
(4, 'Otros');

-- --------------------------------------------------------

--
-- Table structure for table `profile`
--

CREATE TABLE IF NOT EXISTS `profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `profile`
--

INSERT INTO `profile` (`id`, `name`) VALUES
(1, 'administrador'),
(2, 'colaborador');

-- --------------------------------------------------------

--
-- Table structure for table `rnv`
--

CREATE TABLE IF NOT EXISTS `rnv` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dni` varchar(8) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `lastname` varchar(30) DEFAULT NULL,
  `signature` varchar(100) DEFAULT NULL,
  `fingerprint` varchar(100) DEFAULT NULL,
  `ubigeo` varchar(6) DEFAULT NULL,
  `citizen` tinyint(1) DEFAULT NULL,
  `disabled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `rnv`
--

INSERT INTO `rnv` (`id`, `dni`, `name`, `lastname`, `signature`, `fingerprint`, `ubigeo`, `citizen`, `disabled`) VALUES
(1, '72333322', 'JUAN', 'NIEVES CASTRO', '../rnv/gfi001.jpg', '../rnv/ghu001.jpg', '313101', 1, 0),
(2, '72333323', 'JAIME', 'LANCASTER CARRION', '../rnv/gfi002.jpg', '../rnv/ghu002.jpg', '313101', 1, 0),
(3, '72333326', 'BRANDON', 'CONTRERAS AGUILAR', '../rnv/gfi003.jpg', '../rnv/ghu003.jpg', '313101', 1, 0),
(4, '72333328', 'STEFANIE', 'CEDRON PASOS', '../rnv/gfi004.jpg', '../rnv/ghu004.jpg', '313101', 1, 0),
(5, '73333332', 'REBECCA', 'SCOTT VENTURA', '../rnv/gfi005.jpg', '../rnv/ghu005.jpg', '313101', 1, 0),
(6, '73333333', 'HECTOR', 'NAVARRO GOMEZ', '../rnv/gfi006.jpg', '../rnv/ghu006.jpg', '313101', 1, 0),
(7, '73333336', 'WHITNEY', 'MOORE PAREDES', '../rnv/gfi007.jpg', '../rnv/ghu007.jpg', '313101', 1, 0),
(8, '73333338', 'JOSEFINA', 'ADAMS GARCIA', '../rnv/gfi008.jpg', '../rnv/ghu008.jpg', '313101', 1, 0),
(9, '77333322', 'AMELIA', 'GUZMAN RAMIREZ', '../rnv/gfi009.jpg', '../rnv/ghu009.jpg', '313101', 1, 0),
(10, '77333333', 'MATIAS', 'BOLTON MORENO', '../rnv/gfi010.jpg', '../rnv/ghu010.jpg', '313101', 1, 0),
(11, '77333336', 'SAMOEL', 'SARMIENTO DAVILA', '../rnv/gfi011.jpg', '../rnv/ghu011.jpg', '313101', 1, 0),
(12, '77333338', 'ANGELA', 'MERKEL VELARDE', '../rnv/gfi012.jpg', '../rnv/ghu012.jpg', '313101', 1, 0),
(13, '76333322', 'MANUEL', 'AREVALO TRINIDAD', '../rnv/gfi013.jpg', '../rnv/ghu013.jpg', '313101', 1, 0),
(14, '76333333', 'DIANA', 'CORAL SALVATIERRA', '../rnv/gfi014.jpg', '../rnv/ghu014.jpg', '313101', 1, 0),
(15, '76333336', 'JEAN', 'FLORES ALVA', '../rnv/gfi015.jpg', '../rnv/ghu015.jpg', '313101', 1, 0),
(16, '76333338', 'CHRISTIAN', 'PEREZ NORIEGA', '../rnv/gfi016.jpg', '../rnv/ghu016.jpg', '313101', 1, 0),
(17, '78333332', 'LUIS', 'ESPINOZA CORREA', '../rnv/gfi017.jpg', '../rnv/ghu017.jpg', '313101', 1, 0),
(18, '78333333', 'FERNANDO', 'RIOS ROSADO', '../rnv/gfi018.jpg', '../rnv/ghu018.jpg', '313101', 1, 0),
(19, '78440841', 'CESAR', 'FERNANDEZ VEGA', '../rnv/gfi019.jpg', '../rnv/ghu019.jpg', '313101', 1, 0),
(20, '78441842', 'EDDIE', 'CHUNGA CRISPIN', '../rnv/gfi020.jpg', '../rnv/ghu020.jpg', '313101', 1, 0),
(21, '78442743', 'VICTOR', 'GONZALES SAYAN', '../rnv/gfi021.jpg', '../rnv/ghu021.jpg', '313101', 1, 0),
(22, '78443544', 'FRANRE', 'SILVERA ORTIZ', '../rnv/gfi022.jpg', '../rnv/ghu022.jpg', '313101', 1, 0),
(23, '78441345', 'JUAN', 'SALAZAR QUEZADA', '../rnv/gfi023.jpg', '../rnv/ghu023.jpg', '313101', 1, 0),
(24, '78449046', 'TOMAS', 'CASTANEDA ROBLES', '../rnv/gfi024.jpg', '../rnv/ghu024.jpg', '313101', 1, 0),
(25, '78420447', 'PEDRO', 'RODRIGUEZ POLO', '../rnv/gfi025.jpg', '../rnv/ghu025.jpg', '313101', 1, 0),
(26, '78424948', 'KEILY', 'MENDIOLAZA HUANGAL', '../rnv/gfi026.jpg', '../rnv/ghu026.jpg', '313101', 1, 0),
(27, '78413449', 'CARLOS', 'DEL HIERRO RUIZ', '../rnv/gfi027.jpg', '../rnv/ghu027.jpg', '313101', 1, 0),
(28, '78402450', 'PEDRO', 'ANICAMA BLACIDO', '../rnv/gfi028.jpg', '../rnv/ghu028.jpg', '313101', 1, 0),
(29, '78437451', 'LUDGARDO', 'TORRES CARRASCO', '../rnv/gfi029.jpg', '../rnv/ghu029.jpg', '313101', 1, 0),
(30, '78489452', 'MARCO', 'RIVERA MOGOLLON', '../rnv/gfi030.jpg', '../rnv/ghu030.jpg', '313101', 1, 0),
(31, '78412453', 'ALBERTO', 'CRESPO GARCIA', '../rnv/gfi031.jpg', '../rnv/ghu031.jpg', '313101', 1, 0),
(32, '78476454', 'GLORIA', 'FIGUEROA GAMARRA', '../rnv/gfi032.jpg', '../rnv/ghu032.jpg', '313101', 1, 0),
(33, '78430455', 'FRANCISCO', 'HIDALGO MUNOZ', '../rnv/gfi033.jpg', '../rnv/ghu033.jpg', '313101', 1, 0),
(34, '78471456', 'FRANCISCO', 'FLORES MURRIETA', '../rnv/gfi034.jpg', '../rnv/ghu034.jpg', '313101', 1, 0),
(35, '78424457', 'CESAR', 'REYNA REYNA', '../rnv/gfi035.jpg', '../rnv/ghu035.jpg', '313101', 1, 0),
(36, '78412458', 'GERMAN', 'MARREROS DAZA', '../rnv/gfi036.jpg', '../rnv/ghu036.jpg', '313101', 1, 0),
(37, '78410459', 'JORDY', 'VILCACHAGUA GARCIA', '../rnv/gfi037.jpg', '../rnv/ghu037.jpg', '313101', 1, 0),
(38, '78493460', 'OSCAR', 'CALDAS VILLANUEVA', '../rnv/gfi038.jpg', '../rnv/ghu038.jpg', '313101', 1, 0),
(39, '78433461', 'PABLO', 'LOAYZA SORIANO', '../rnv/gfi039.jpg', '../rnv/ghu039.jpg', '313101', 1, 0),
(40, '78418462', 'ESTHER', 'FERNANDEZ OTERO', '../rnv/gfi040.jpg', '../rnv/ghu040.jpg', '313101', 1, 0),
(41, '70801922', 'MARIELA', 'NAZARIO LOPEZ', '../rnv/gfi041.jpg', '../rnv/ghu041.jpg', '313101', 1, 0),
(42, '70802020', 'DAVID', 'RISCO ARIAS', '../rnv/gfi042.jpg', '../rnv/ghu042.jpg', '313101', 1, 0),
(43, '72100199', 'LUCAS', 'FLORES CRUZ', '../rnv/gfi043.jpg', '../rnv/ghu043.jpg', '313101', 1, 0),
(44, '72100333', 'JORGE', 'CHIRINOS RIOS', '../rnv/gfi044.jpg', '../rnv/ghu044.jpg', '313101', 1, 0),
(45, '74391400', 'SUSAN', 'ORTIZ SANCHEZ', '../rnv/gfi045.jpg', '../rnv/ghu045.jpg', '313101', 1, 0),
(46, '74402299', 'MERCEDES', 'JIMENEZ PEREZ', '../rnv/gfi046.jpg', '../rnv/ghu046.jpg', '313101', 1, 0),
(47, '74809080', 'CAROL', 'MORENO FLORES', '../rnv/gfi047.jpg', '../rnv/ghu047.jpg', '313101', 1, 0),
(48, '78292900', 'ADRIAN', 'ALVA GUILLEN', '../rnv/gfi048.jpg', '../rnv/ghu048.jpg', '313101', 1, 0),
(49, '78303011', 'RENZO', 'MALCA CASTILLO', '../rnv/gfi049.jpg', '../rnv/ghu049.jpg', '313101', 1, 0),
(50, '78802030', 'PATRICIA', 'DURAND SANTOS', '../rnv/gfi050.jpg', '../rnv/ghu050.jpg', '313101', 1, 0),
(51, '78882020', 'FABIO', 'GUERRA CASTRO', '../rnv/gfi051.jpg', '../rnv/ghu051.jpg', '313101', 1, 0),
(52, '79220010', 'DANIEL', 'RAMOS ARANA', '../rnv/gfi052.jpg', '../rnv/ghu052.jpg', '313101', 1, 0),
(53, '79332211', 'CARLOS', 'MEJIA SUAREZ', '../rnv/gfi053.jpg', '../rnv/ghu053.jpg', '313101', 1, 0),
(54, '75357888', 'LOURDES', 'MONTALVO LUJAN', '../rnv/gfi065.jpg', '../rnv/ghu065.jpg', '313101', 1, 0),
(55, '70388311', 'JORGE ENRIQUE', 'ESPINOZA MUÑOZ', '../rnv/gfi062.jpg', '../rnv/ghu062.jpg', '313101', 1, 0),
(56, '72642632', 'CARLOS EDUARADO', 'AVALOS FERNANDEZ', '../rnv/gfi056.jpg', '../rnv/ghu056.jpg', '313101', 1, 0),
(57, '78558321', 'HERBERT SANTIAGO', 'VIDELA CALIXTO', '../rnv/gfi057.jpg', '../rnv/ghu057.jpg', '313101', 1, 0),
(58, '72832152', 'PEDRO ARMANDO', 'OLORTEGUI NUÑEZ', '../rnv/gfi058.jpg', '../rnv/ghu058.jpg', '313101', 1, 0),
(59, '77261875', 'PEDRO WILFREDO ', 'OLORTEGUI VERDE', '../rnv/gfi059.jpg', '../rnv/ghu059.jpg', '313101', 1, 0),
(60, '78001356', 'ALEJANDRO WILFREDO', 'REYES OLORTEGUI', '../rnv/gfi060.jpg', '../rnv/ghu060.jpg', '313101', 1, 0),
(61, '70800123', 'CAROLINA INES', 'BRAVO ROJAS', '../rnv/gfi061.jpg', '../rnv/ghu061.jpg', '313101', 1, 0),
(62, '77900146', 'WALTER SIGIFREDO', 'REYES ASCON', '../rnv/gfi055.jpg', '../rnv/ghu055.jpg', '313101', 1, 0),
(63, '77904884', 'GLORIA NATIVIDAD', 'OLORTEGUI NUÑEZ', '../rnv/gfi063.jpg', '../rnv/ghu063.jpg', '313101', 1, 0),
(64, '78131213', 'WALTER RICARDO', 'REYES OLORTEGUI', '../rnv/gfi064.jpg', '../rnv/ghu064.jpg', '313101', 1, 0),
(65, '72614241', 'ANDREA XIMENA', 'BRAVO ROJAS', '../rnv/gfi054.jpg', '../rnv/ghu054.jpg', '313101', 1, 0),
(66, '70200581', 'ANDREA MILAGROS', 'PÉREZ VELÁSQUEZ', '../rnv/gfi068.jpg', '../rnv/ghu068.jpg', '313101', 1, 0),
(67, '70067084', 'MANUEL MARINO', 'MONTOYA GAMIO', '../rnv/gfi067.jpg', '../rnv/ghu067.jpg', '313101', 1, 0),
(68, '70105127', 'SEGIO ALEJANDRO', 'CAMA CHAVEZ', '../rnv/gfi066.jpg', '../rnv/ghu066.jpg', '313101', 1, 0),
(69, '70110921', 'JOSE ALONSO', 'AMADOR ZANABRIA', '../rnv/gfi069.jpg', '../rnv/ghu069.jpg', '313101', 1, 0),
(70, '70121325', 'PAUL ', 'AQUINO VEGA', '../rnv/gfi070.jpg', '../rnv/ghu070.jpg', '313101', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `ubigeo`
--

CREATE TABLE IF NOT EXISTS `ubigeo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(6) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `description` varchar(80) DEFAULT NULL,
  `status` varchar(10) NOT NULL,
  `id_electoralProcess` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ubigeo_ibfk_1` (`id_electoralProcess`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `bornDay` datetime DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `docCode` varchar(11) DEFAULT NULL,
  `docType` varchar(3) DEFAULT NULL,
  `status` varchar(10) NOT NULL,
  `id_profile` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `docCode` (`docCode`),
  KEY `id_profile` (`id_profile`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `lastname`, `password`, `bornDay`, `phone`, `docCode`, `docType`, `status`, `id_profile`) VALUES
(1, 'Erick', 'Gonzales', 'erick', '2016-09-02 00:00:00', '373473487', '71844756', 'DNI', 'Activo', 1),
(3, 'Andrea', 'Bravo', 'peppa', '2016-05-31 00:00:00', '26336236', '72614241', 'DNI', 'Activo', 1),
(4, 'Miguel', 'Quirozz', 'peppa', '2015-09-19 00:00:00', '26336236', '46898966', 'DNI', 'Activo', 1),
(5, 'Claudia', 'Villanueva', 'peppa', '2016-07-28 00:00:00', '1234567', '74391422', 'DNI', 'Activo', 1),


--
-- Constraints for dumped tables
--

--
-- Constraints for table `adherent`
--
ALTER TABLE `adherent`
  ADD CONSTRAINT `id_politicalParty` FOREIGN KEY (`id_politicalParty`) REFERENCES `politicalParty` (`id`);

--
-- Constraints for table `adherentImage`
--
ALTER TABLE `adherentImage`
  ADD CONSTRAINT `adherentImage_ibfk_1` FOREIGN KEY (`id_politicalParty`) REFERENCES `politicalParty` (`id`);

--
-- Constraints for table `electoralProcess`
--
ALTER TABLE `electoralProcess`
  ADD CONSTRAINT `id_processType` FOREIGN KEY (`id_processType`) REFERENCES `processType` (`id`),
  ADD CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Constraints for table `politicalParty`
--
ALTER TABLE `politicalParty`
  ADD CONSTRAINT `id_electoralProcess` FOREIGN KEY (`id_electoralProcess`) REFERENCES `electoralProcess` (`id`);

--
-- Constraints for table `ubigeo`
--
ALTER TABLE `ubigeo`
  ADD CONSTRAINT `ubigeo_ibfk_1` FOREIGN KEY (`id_electoralProcess`) REFERENCES `electoralProcess` (`id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `id_profile` FOREIGN KEY (`id_profile`) REFERENCES `profile` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;