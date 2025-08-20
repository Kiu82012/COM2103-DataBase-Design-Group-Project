-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2023-04-14 17:26:40
-- 伺服器版本： 10.4.27-MariaDB
-- PHP 版本： 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `rms`
--

-- --------------------------------------------------------

--
-- 資料表結構 `applicant`
--

CREATE TABLE `applicant` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `experience` varchar(1000) NOT NULL,
  `education` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 傾印資料表的資料 `applicant`
--

INSERT INTO `applicant` (`id`, `name`, `email`, `phone`, `experience`, `education`) VALUES
(1, 'Chan Tai Man', 'chantaiman@gmail.com', '5464 6589', '2 years of experience as a software engineer at a startup', 'Bachelor degree in Computer Science from the University of Hong Kong'),
(2, 'Wong Siu Ming', 'wongsm@gmail.com', '6935 6145', '3 years of experience as a customer service representative at a telecommunications company', 'Diploma in Business Administration from the Hong Kong Polytechnic University '),
(3, 'Kwok Yee Ting', 'kwokytee@outlook.com', '9885 3541', '1 year of experience as a financial analyst at an investment bank', 'Bachelor degree in Finance from the City University of Hong Kong'),
(4, 'Cheung Ka Wai', 'cheungkw@gmail.com', '5678 9012', '4 years of experience as a hotel receptionist at a luxury hotel', 'Diploma in Hospitality Management from the Hong Kong Institute of Vocational Education'),
(5, 'Ho Chun Fai', 'hochunfai@hotmail.com', '6789 0123', '2 years of experience as a reporter at a newspaper', 'Bachelor degree in Journalism from the Hong Kong Baptist University'),
(6, 'Yau Mei Ling', 'yaumeiling@gmail.com', '5890 1234', '6 years of experience as a clinical psychologist at a private practice', 'Master degree in Psychology from the University of Hong Kong'),
(7, 'Lam Chi Chung', 'lamchichung@gmail.com', '5901 2345', '3 years of experience as a web developer at a digital agency', 'Diploma in Information Technology from the Hong Kong Institute of Vocational Education'),
(8, 'Ng Siu Fung', 'ngsiufung@yahoo.com', '9012 3456', '1 year of experience as an architect at a design firm', 'Bachelor degree in Architecture from the Chinese University of Hong Kong'),
(9, 'Lai Ka Yee', 'laikayee@gmail.com', '5123 4567', '4 years of experience as a teacher at a local primary school', 'Master degree in Education from the University of Hong Kong');

-- --------------------------------------------------------

--
-- 資料表結構 `application`
--

CREATE TABLE `application` (
  `id` int(11) NOT NULL,
  `date_of_application` datetime NOT NULL,
  `applicant_id1` int(11) NOT NULL,
  `job_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 傾印資料表的資料 `application`
--

INSERT INTO `application` (`id`, `date_of_application`, `applicant_id1`, `job_id`) VALUES
(901, '2023-04-13 17:35:51', 1, 1002),
(902, '2023-04-13 22:45:20', 2, 1001),
(903, '2023-04-12 23:46:20', 3, 1003),
(904, '2023-03-14 21:47:02', 4, 1009),
(905, '2023-04-11 23:48:07', 5, 1008),
(906, '2023-04-13 17:48:38', 6, 1004),
(907, '2023-03-09 14:45:55', 7, 1005),
(908, '2023-04-13 17:49:25', 8, 1006),
(909, '2023-03-26 15:29:44', 9, 1007),
(910, '2023-01-12 13:20:30', 10, 1010);

-- --------------------------------------------------------

--
-- 資料表結構 `employer`
--

CREATE TABLE `employer` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `Contact` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 傾印資料表的資料 `employer`
--

INSERT INTO `employer` (`id`, `name`, `Contact`, `Email`) VALUES
(11, 'Chan Ah Man', '6060 6606', 'achan@gmail.com'),
(12, 'Chan Ka Kai', '5664 4452', 'kakai221@gmail.com'),
(13, 'Cheung Bo Jai', '5673 4456', 'priate3344@yahoo.com'),
(14, 'Chim Shi Bon', '5556 6600', 'jamesbond@gmail.com'),
(15, 'Wong Ka Hin', '9393 3920', 'stuipdwong555@yahoo.com'),
(16, 'Mak Lo Yan', '9743 8292', 'invisbleman@yahoo.com'),
(17, 'Cheng So Ying', '6779 3204', 'handsome3339@gmail.com'),
(18, 'So To Po', '6077 9400', 'sohungry880@gmail.com'),
(19, 'Wong Fai Hung', '5577 7033', 'kungfu117@gmail.com'),
(20, 'Chan Ka Mao', '5566 7033', 'chanbadcat@yahoo.com');

-- --------------------------------------------------------

--
-- 資料表結構 `job`
--

CREATE TABLE `job` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(100) NOT NULL,
  `date_published` date NOT NULL,
  `Salary` text NOT NULL,
  `Academic requirements` text NOT NULL,
  `company` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 傾印資料表的資料 `job`
--

INSERT INTO `job` (`id`, `name`, `description`, `date_published`, `Salary`, `Academic requirements`, `company`) VALUES
(1001, 'Financial Analyst', 'Financial Skill', '2022-04-15', 'HKD 40,000-60,000 per month', 'Bachelor degree in finance or related field', 'ABC Bank'),
(1002, 'Marketing Manager', 'Marketing skills', '2023-03-17', 'HKD 50,000-70,000 per month', 'Bachelor degree in marketing or related field', 'XYZ Corporation'),
(1003, 'Sales Associate', 'Customer Service', '2022-04-05', 'HKD 12,000-15,000 per month', 'High school diploma or equivalent', 'QRS Retail'),
(1004, ' Human Resources Specialist', 'HR', '2022-05-04', 'HKD 35,000-50,000 per month', 'Bachelor degree in human resources or related field', 'DEF Company'),
(1005, 'Graphic Designer', 'Design Skill', '2023-05-12', 'HKD 25,000-40,000 per month', 'Bachelor degree in graphic design or related field', 'GHI Agency'),
(1006, 'Software Engineer', 'Java', '2022-06-17', 'HKD 50,000-80,000 per month', 'Bachelor degree in computer science or related field', 'JKL Technology'),
(1007, 'Customer Service Representative', 'Customer Service', '2022-07-17', 'HKD 13,000-16,000 per month', 'High school diploma or equivalent', 'MNO Services'),
(1008, 'Project Manager', 'Management', '2022-07-27', 'HKD 60,000-90,000 per month', 'Bachelor degree in business administration or related field', 'PQR Consulting'),
(1009, 'Web Developer', 'Java Script', '2023-08-10', 'HKD 45,000-65,000 per month', 'Bachelor degree in computer science or related field', 'STU Technology'),
(1010, 'Administrative Assistant', 'Supporting', '2023-08-24', ' HKD 20,000-30,000 per month', 'High school diploma or equivalent, associate or bachelor degree preferred.', 'VWX Enterprises');

-- --------------------------------------------------------

--
-- 資料表結構 `system`
--

CREATE TABLE `system` (
  `SYSTEM_DATE` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 傾印資料表的資料 `system`
--

INSERT INTO `system` (`SYSTEM_DATE`) VALUES
('2023-04-14');

-- --------------------------------------------------------

--
-- 資料表結構 `user_detail`
--

CREATE TABLE `user_detail` (
  `USER_ID` int(11) NOT NULL,
  `USERNAME` varchar(30) NOT NULL,
  `PASSWORD` varchar(30) NOT NULL,
  `ADMIN` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 傾印資料表的資料 `user_detail`
--

INSERT INTO `user_detail` (`USER_ID`, `USERNAME`, `PASSWORD`, `ADMIN`) VALUES
(1, 'USERNAME', 'PASSWORD', 1),
(3, 'username', '123', 1),
(4, 'Sam', '456', 0);

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `applicant`
--
ALTER TABLE `applicant`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `application`
--
ALTER TABLE `application`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_application_applicant1_idx` (`applicant_id1`),
  ADD KEY `fk_application_job1_idx` (`job_id`);

--
-- 資料表索引 `employer`
--
ALTER TABLE `employer`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `job`
--
ALTER TABLE `job`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `user_detail`
--
ALTER TABLE `user_detail`
  ADD PRIMARY KEY (`USER_ID`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `job`
--
ALTER TABLE `job`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1012;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `user_detail`
--
ALTER TABLE `user_detail`
  MODIFY `USER_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
