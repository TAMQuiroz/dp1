-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 22, 2016 at 03:18 AM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.5.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dp1EP`
--

-- --------------------------------------------------------

--
-- Table structure for table `adherent`
--

CREATE TABLE `adherent` (
  `id` bigint(20) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `lastname` varchar(30) DEFAULT NULL,
  `observation` varchar(100) DEFAULT NULL,
  `id_politicalParty` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `electoralProcess`
--

CREATE TABLE `electoralProcess` (
  `id` bigint(20) NOT NULL,
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
  `population` bigint(20) DEFAULT NULL,
  `id_processType` bigint(20) DEFAULT NULL,
  `id_user` bigint(20) DEFAULT NULL,
  `startExtraReceptionDate` datetime DEFAULT NULL,
  `endExtraReceptionDate` datetime DEFAULT NULL,
  `startExtraValidationDate` datetime DEFAULT NULL,
  `endExtraValidationDate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `politicalParty`
--

CREATE TABLE `politicalParty` (
  `id` bigint(20) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `legalDepartment` varchar(20) DEFAULT NULL,
  `image` varchar(30) DEFAULT NULL,
  `telephone` varchar(11) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `id_electoralProcess` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `processType`
--

CREATE TABLE `processType` (
  `id` bigint(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `profile`
--

CREATE TABLE `profile` (
  `id` bigint(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `bornDay` datetime DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `docCode` varchar(11) DEFAULT NULL,
  `docType` varchar(2) DEFAULT NULL,
  `id_profile` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `zone`
--

CREATE TABLE `zone` (
  `id` bigint(20) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `description` varchar(80) DEFAULT NULL,
  `id_electoralProcess` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `adherent`
--
ALTER TABLE `adherent`
  ADD PRIMARY KEY (`id`),
  ADD KEY `adherent_ibfk_1` (`id_politicalParty`);

--
-- Indexes for table `electoralProcess`
--
ALTER TABLE `electoralProcess`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_processType` (`id_processType`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `politicalParty`
--
ALTER TABLE `politicalParty`
  ADD PRIMARY KEY (`id`),
  ADD KEY `politicalParty_ibfk_1` (`id_electoralProcess`);

--
-- Indexes for table `processType`
--
ALTER TABLE `processType`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `profile`
--
ALTER TABLE `profile`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_profile` (`id_profile`);

--
-- Indexes for table `zone`
--
ALTER TABLE `zone`
  ADD PRIMARY KEY (`id`),
  ADD KEY `zone_ibfk_1` (`id_electoralProcess`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `adherent`
--
ALTER TABLE `adherent`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `electoralProcess`
--
ALTER TABLE `electoralProcess`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `politicalParty`
--
ALTER TABLE `politicalParty`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `processType`
--
ALTER TABLE `processType`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `profile`
--
ALTER TABLE `profile`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `zone`
--
ALTER TABLE `zone`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;