/*
 Navicat Premium Data Transfer

 Source Server         : Local
 Source Server Type    : MySQL
 Source Server Version : 50640
 Source Host           : localhost:3306
 Source Schema         : course

 Target Server Type    : MySQL
 Target Server Version : 50640
 File Encoding         : 65001

 Date: 02/08/2018 19:08:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `context` text NOT NULL,
  `score` int(11) NOT NULL DEFAULT '0',
  `userId` int(11) NOT NULL,
  `homeworkId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `answer_homeworkId_FK` (`homeworkId`),
  KEY `answer_userId_FK` (`userId`),
  CONSTRAINT `answer_homeworkId_FK` FOREIGN KEY (`homeworkId`) REFERENCES `homework` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `answer_userId_FK` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for barrage
-- ----------------------------
DROP TABLE IF EXISTS `barrage`;
CREATE TABLE `barrage` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `context` varchar(0) NOT NULL,
  `time` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `pointId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `barrage_userId_FK` (`userId`),
  KEY `barrage_pointId_FK` (`pointId`),
  CONSTRAINT `barrage_pointId_FK` FOREIGN KEY (`pointId`) REFERENCES `point` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `barrage_userId_FK` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for chapter
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `courseId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `chapter_courseId_FK` (`courseId`),
  CONSTRAINT `chapter_courseId_FK` FOREIGN KEY (`courseId`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `context` varchar(0) NOT NULL,
  `userId` int(11) NOT NULL,
  `courseIdId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `comment_userId_FK` (`userId`),
  KEY `comment_courseId_FK` (`courseIdId`),
  CONSTRAINT `comment_courseId_FK` FOREIGN KEY (`courseIdId`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_userId_FK` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` text,
  `creatorId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `course_creator_FK` (`creatorId`),
  CONSTRAINT `course_creator_FK` FOREIGN KEY (`creatorId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='è¯¾ç¨‹è¡¨';

-- ----------------------------
-- Table structure for homework
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `context` varchar(255) NOT NULL DEFAULT '',
  `courseId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `homework_courseId_FK` (`courseId`),
  CONSTRAINT `homework_courseId_FK` FOREIGN KEY (`courseId`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for point
-- ----------------------------
DROP TABLE IF EXISTS `point`;
CREATE TABLE `point` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` text,
  `chapterId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `point_chapterId_FK` (`chapterId`),
  CONSTRAINT `point_chapterId_FK` FOREIGN KEY (`chapterId`) REFERENCES `chapter` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for select
-- ----------------------------
DROP TABLE IF EXISTS `select`;
CREATE TABLE `select` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `courseId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `select_userId_FK` (`userId`),
  KEY `select_courseId_FK` (`courseId`),
  CONSTRAINT `select_courseId_FK` FOREIGN KEY (`courseId`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `select_userId_FK` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `passwordHash` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
