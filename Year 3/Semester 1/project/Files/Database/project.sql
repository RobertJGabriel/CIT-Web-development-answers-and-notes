-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 11, 2014 at 12:41 AM
-- Server version: 5.6.14
-- PHP Version: 5.5.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `project`
--

-- --------------------------------------------------------

--
-- Table structure for table `cardioInfo`
--

CREATE TABLE IF NOT EXISTS `cardioInfo` (
  `bookingId` int(11) NOT NULL,
  `distance` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  KEY `bookingId` (`bookingId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE IF NOT EXISTS `members` (
  `memberId` int(11) NOT NULL AUTO_INCREMENT,
  `fName` varchar(100) NOT NULL,
  `lName` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL,
  `phone` int(11) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` varchar(100) NOT NULL,
  `status` int(11) NOT NULL,
  `height` int(11) NOT NULL,
  `weight` int(11) NOT NULL,
  PRIMARY KEY (`memberId`),
  KEY `memberId` (`memberId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `schedules`
--

CREATE TABLE IF NOT EXISTS `schedules` (
  `bookingId` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  `studentId` int(11) NOT NULL,
  `staffId` int(11) NOT NULL,
  PRIMARY KEY (`bookingId`),
  KEY `bookingId` (`bookingId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `settings`
--

CREATE TABLE IF NOT EXISTS `settings` (
  `memberId` int(11) NOT NULL,
  `color` varchar(10) NOT NULL,
  `profilePic` varchar(20) NOT NULL,
  `backgroundPhoto` varchar(20) NOT NULL,
  KEY `memberId` (`memberId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `staffavailability`
--

CREATE TABLE IF NOT EXISTS `staffavailability` (
  `staffId` int(11) NOT NULL,
  `date` date NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  KEY `staffId` (`staffId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `workourInfoi`
--

CREATE TABLE IF NOT EXISTS `workourInfoi` (
  `bookingId` int(11) NOT NULL,
  `weight` int(11) NOT NULL,
  `repetitions` int(11) NOT NULL,
  `sets` int(11) NOT NULL,
  `type` varchar(50) NOT NULL,
  KEY `bookingId` (`bookingId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
