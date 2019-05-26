-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 26, 2019 at 04:49 PM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bank-system`
--

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `id` int(11) UNSIGNED NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`id`, `address`, `district`, `city`, `postal_code`, `country`) VALUES
(1, '61 Preah Monivong Blvd (93)', 'Toul Kork', 'Phnom Penh', '12102', 'Cambodia'),
(2, 'Mao Tse Toung Boulevard (245)', 'Resey Keo', 'Phnom Penh', '120102', 'Cambodia');

-- --------------------------------------------------------

--
-- Table structure for table `cards`
--

CREATE TABLE `cards` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `card_number` varchar(255) DEFAULT NULL,
  `card_type` varchar(191) DEFAULT NULL,
  `expired_date` date DEFAULT NULL,
  `csv` varchar(3) DEFAULT NULL,
  `daily_limit` decimal(10,0) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '1',
  `holder_name` varchar(191) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cards`
--

INSERT INTO `cards` (`id`, `user_id`, `card_number`, `card_type`, `expired_date`, `csv`, `daily_limit`, `status`, `holder_name`, `address_id`) VALUES
(1, 1, '4111 1111 1111 1111', 'visa', '2020-01-01', '222', '1000', 1, 'Phuong Phally', 1),
(2, 1, '5500 0000 0000 0004', 'masterCard', '2020-01-01', '111', '1000', 1, 'Phuong Phally', 1),
(3, 2, '4222 2222 2222 2222 ', 'visa', '2020-02-02', '111', '500', 0, 'Dara Penhchet', 2),
(4, 2, '3400 0000 0000 009', 'masterCard', '2020-02-02', '999', '500', 1, 'Dara Penhchet', 2);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(191) DEFAULT 'user',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `phone_number`, `password`, `role`, `created_at`, `status`) VALUES
(1, '093883292', '$2a$10$or9jKJtcnRpIjcTj62aczesC99ZFpw4G61zOOTii4HecjbKADb67u', 'consumer', '2019-05-26 12:25:46', '1'),
(2, '086503225', '$2a$10$ynJXDlrrcz9qLRR4dHtdFeJve9xJ2gDwQsVNcpLfe8BvZ.u/KXIwW', 'consumer', '2019-05-26 12:51:04', '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cards`
--
ALTER TABLE `cards`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `cards`
--
ALTER TABLE `cards`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
