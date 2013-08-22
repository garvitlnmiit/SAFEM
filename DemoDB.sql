-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 25, 2013 at 05:07 PM
-- Server version: 5.5.24-log
-- PHP Version: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `swe`
--

-- --------------------------------------------------------

--
-- Table structure for table `course_details`
--

CREATE TABLE IF NOT EXISTS `course_details` (
  `course_id` varchar(6) NOT NULL,
  `course_name` varchar(50) NOT NULL,
  `instructor_name` varchar(40) NOT NULL,
  `instructor_id` varchar(10) NOT NULL,
  `course_credit` int(2) NOT NULL,
  `eval_field_name` varchar(100) NOT NULL,
  `weightage` varchar(100) NOT NULL,
  `eval_field_type` varchar(100) NOT NULL,
  `max_marks` varchar(100) NOT NULL,
  `public_view` varchar(100) NOT NULL,
  `eval_status` int(2) NOT NULL,
  `eval_total` varchar(5) NOT NULL,
  `eval_grade` int(11) NOT NULL,
  `student_added` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course_details`
--

INSERT INTO `course_details` (`course_id`, `course_name`, `instructor_name`, `instructor_id`, `course_credit`, `eval_field_name`, `weightage`, `eval_field_type`, `max_marks`, `public_view`, `eval_status`, `eval_total`, `eval_grade`, `student_added`) VALUES
('cs1', 'SWE', 'Ravi P Gorthi', 'rpg', 4, 'quiz-quiz-', '', 'q1-q2-', '10-30-', 'y-y-n-', 0, '1', 0, 1),
('cs2', 'ASWE', 'Vikas Bijpai', 'vb', 4, 'quiz-', '', 'q1-', '25-', 'y-', 0, '0', 0, 1),
('cs3', 'JAVA', 'Ajit Tiwari', 'ajt', 4, 'quiz-', '', 'quiz1-', '10-', 'y-n-', 0, '1', 1, 1),
('cs4', 'SWE-Android', 'Ravi P Gorthi', 'rpg', 4, 'quiz-midsem-project-', '', 'Quiz1-MidSem-project-', '10-30-20-', 'y-y-n-n-', 0, '1', 1, 1),
('ec111', 'Electronics', 'Somitra Debnath', 'sdbn', 3, '', '', '', '', '', 0, '0', 0, 1),
('cse73', 'Networks', 'Parmesh ', 'parm', 7, '', '', '', '', '', 0, '0', 0, 0),
('cs90', 'Digital Processing', 'sonam', 'shrshr', 5, 'quiz-', '', 'q1-', '25-', 'n-', 0, '0', 0, 0),
('ec400', 'Electomagnetics', 'Subrat K Dask', 'skdn', 4, '', '', '', '', '', 0, '0', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ec111`
--

CREATE TABLE IF NOT EXISTS `ec111` (
  `sid` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ec111`
--

INSERT INTO `ec111` (`sid`) VALUES
('y10uc001'),
('y10uc002'),
('y10uc009');

-- --------------------------------------------------------

--
-- Table structure for table `ec400`
--

CREATE TABLE IF NOT EXISTS `ec400` (
  `sid` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ec400`
--

INSERT INTO `ec400` (`sid`) VALUES
('y10uc001'),
('y10uc002');

-- --------------------------------------------------------

--
-- Table structure for table `feedback_details`
--

CREATE TABLE IF NOT EXISTS `feedback_details` (
  `course_id` varchar(6) NOT NULL,
  `instructor_name` varchar(50) NOT NULL,
  `org_coherence` varchar(10) NOT NULL,
  `teaching` varchar(10) NOT NULL,
  `interaction` varchar(10) NOT NULL,
  `rating_course` varchar(10) NOT NULL,
  `rating_instructor` varchar(10) NOT NULL,
  `desc` varchar(1000) NOT NULL,
  `c_appeal` varchar(20) NOT NULL,
  `participation` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `feedback_details`
--

INSERT INTO `feedback_details` (`course_id`, `instructor_name`, `org_coherence`, `teaching`, `interaction`, `rating_course`, `rating_instructor`, `desc`, `c_appeal`, `participation`) VALUES
('cs1', 'Ravi P Gorthi', 'good', 'good', 'average', 'average', 'excellent', 'xcxc', 'logical', 5),
('cs1', 'Ravi P Gorthi', 'poor', 'poor', 'poor', 'excellent', 'excellent', 'yty', 'pure cramming', 4),
('cs1', 'Ravi P Gorthi', 'excellent', 'average', 'good', 'excellent', 'excellent', 'cool course. keep it up sir.', 'interesting', 5),
('cs1', 'Ravi P Gorthi', 'excellent', 'excellent', 'excellent', 'excellent', 'excellent', 'VERY GOOD COURSE', 'interesting', 5);

-- --------------------------------------------------------

--
-- Table structure for table `student_details`
--

CREATE TABLE IF NOT EXISTS `student_details` (
  `student_id` varchar(10) NOT NULL,
  `course_id` varchar(6) NOT NULL,
  `marks` varchar(100) NOT NULL,
  `total` int(4) NOT NULL,
  `grades` varchar(3) NOT NULL,
  `feedback` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_details`
--

INSERT INTO `student_details` (`student_id`, `course_id`, `marks`, `total`, `grades`, `feedback`) VALUES
('y10uc001', 'cs1', '10-0-', 25, '', 0),
('y10uc003', 'cs1', '3-0-', 8, '', 0),
('y10uc005', 'cs1', '6-0-', 15, '', 0),
('y10uc007', 'cs1', '4-0-', 10, '', 0),
('y10uc009', 'cs1', '8-0-', 20, '', 0),
('y10uc002', 'cs2', '12-', 0, '', 0),
('y10uc005', 'cs2', '23-', 0, '', 0),
('y10uc001', 'cs2', '12-', 0, '', 0),
('y10uc003', 'cs2', '3-', 0, '', 0),
('y10uc007', 'cs2', '18-', 0, '', 0),
('y10uc009', 'cs2', '19-', 0, '', 0),
('y10uc001', 'cs3', '9-', 90, 'A', 0),
('y10uc002', 'cs3', '9-', 90, 'A', 0),
('y10uc003', 'cs3', '4-', 40, 'D', 0),
('y10uc004', 'cs3', '2-', 20, 'F', 0),
('y10uc005', 'cs3', '7-', 70, 'BC', 0),
('y10uc006', 'cs3', '3-', 30, 'F', 0),
('y10uc007', 'cs3', '6-', 60, 'C', 0),
('y10uc008', 'cs3', '8-', 80, 'B', 0),
('y10uc009', 'cs3', '9-', 90, 'A', 0),
('y10uc001', 'cs4', '10-28-0-', 64, 'B', 0),
('y10uc002', 'cs4', '1-24-0-', 30, 'D', 0),
('y10uc003', 'cs4', '0-12-0-', 13, 'F', 0),
('y10uc004', 'cs4', '10-12-0-', 46, 'C', 0),
('y10uc005', 'cs4', '0-12-0-', 13, 'F', 0),
('y10uc006', 'cs4', '0-14-0-', 15, 'F', 0),
('y10uc007', 'cs4', '5-13-0-', 31, 'CD', 0),
('y10uc009', 'cs4', '2-29-0-', 39, 'CD', 0),
('y10uc010', 'cs4', '0-23-0-', 25, 'D', 0),
('y10uc001', 'ec111', '', 0, '', 0),
('y10uc002', 'ec111', '', 0, '', 0),
('y10uc009', 'ec111', '', 0, '', 0),
('y10uc001', 'ec111', '', 0, '', 0),
('y10uc002', 'ec111', '', 0, '', 0),
('y10uc009', 'ec111', '', 0, '', 0),
('y10uc001', 'ec400', '', 0, '', 0),
('y10uc002', 'ec400', '', 0, '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE IF NOT EXISTS `test` (
  `name` varchar(20) DEFAULT NULL,
  `rollno` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test`
--

INSERT INTO `test` (`name`, `rollno`) VALUES
('akhil', 1001);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `userid` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `category` varchar(10) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `emailid` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userid`, `password`, `category`, `user_name`, `emailid`) VALUES
('admin', 'admin', 'admin', '', ''),
('y10uc001', 'y10uc001', 'student', '', ''),
('y10uc002', 'y10uc002', 'student', '', ''),
('y10uc003', 'y10uc003', 'student', '', ''),
('y10uc004', 'y10uc004', 'student', '', ''),
('y10uc005', 'y10uc005', 'student', '', ''),
('y10uc006', 'y10uc006', 'student', '', ''),
('y10uc007', 'y10uc007', 'student', '', ''),
('y10uc008', 'y10uc008', 'student', '', ''),
('y10uc009', 'y10uc009', 'student', '', ''),
('y10uc010', 'y10uc010', 'student', '', ''),
('y10uc001', 'p_y10uc001', 'parent', '', ''),
('y10uc002', 'p_y10uc002', 'parent', '', ''),
('y10uc003', 'p_y10uc003', 'parent', '', ''),
('y10uc004', 'p_y10uc004', 'parent', '', ''),
('y10uc005', 'p_y10uc005', 'parent', '', ''),
('y10uc006', 'p_y10uc006', 'parent', '', ''),
('y10uc007', 'y10uc007', 'parent', '', ''),
('y10uc008', 'p_y10uc008', 'parent', '', ''),
('y10uc009', 'p_y10uc009', 'parent', '', ''),
('y10uc010', 'p_y10uc010', 'parent', '', ''),
('rpg', 'rpg', 'instructor', 'Ravi P Gorthi', 'rpg@lnmiit.ac.in'),
('vb', 'vb', 'instructor', 'Vikas Bajpai', 'vb@lnmiit.ac.in'),
('ajt', 'ajt', 'instructor', 'Ajit Tiwari', 'ajt@lnmiit.ac.in'),
('sdbn', 'sdbn', 'instructor', 'Somitra Debnath', 'sd@gmail.com'),
('null', 'null', 'instructor', 'null', 'null'),
('parm', 'parm', 'instructor', 'Parmesh ', 'par@lnmiit.ac.in'),
('shrshr', 'shrshr', 'instructor', 'sonam', 'shr@go.po'),
('skdn', 'skdn', 'instructor', 'Subrat K Dask', 'skdn@gmail.com');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
