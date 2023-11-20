DROP DATABASE IF EXISTS ssafy_project;

CREATE DATABASE ssafy_project;

use ssafy_project;

CREATE TABLE `user` (
	`user_num` INT AUTO_INCREMENT,
    `user_id` VARCHAR(30),
    `user_pw` VARCHAR(30),
    `user_name` VARCHAR(30),
	`user_birth` VARCHAR(30),
    `user_grade` VARCHAR(30) default '브론즈',
    `user_boardCnt` INT default 0,
    `user_commentCnt` INT default 0,    
    `user_joinDate` TIMESTAMP,
    PRIMARY KEY (user_num),
    UNIQUE (user_id)
);

CREATE TABLE `freeBoard` (
	`fb_num` INT AUTO_INCREMENT,
    `fb_userId` VARCHAR(30),
    `fb_title` VARCHAR(30),
    `fb_writer` VARCHAR(30),
    `fb_content` VARCHAR(100),
    `fb_viewCnt` INT,
    `fb_rcm` INT,
    `fb_regDate` TIMESTAMP,
    PRIMARY KEY (fb_num),
    FOREIGN KEY (fb_userId) REFERENCES user (user_id) on delete cascade
);

CREATE TABLE `freeComment` (
	`fc_num` INT AUTO_INCREMENT,
    `fc_boardNum` INT,
    `fc_userId` VARCHAR(30),
    `fc_userName` VARCHAR(30),
    `fc_content` VARCHAR(30),
    `fc_regDate` TIMESTAMP,
    PRIMARY KEY (fc_num),
    FOREIGN KEY (fc_boardNum) REFERENCES freeBoard (fb_num) on delete cascade, 
    FOREIGN KEY (fc_userId) REFERENCES user (user_id) on delete cascade
);

CREATE TABLE `infoBoard` (
	`ib_num` INT AUTO_INCREMENT,
    `ib_title` VARCHAR(30),
    `ib_content` VARCHAR(100),
    `ib_viewCnt` INT DEFAULT 0,
    `ib_rcm` INT DEFAULT 0,
    `ib_regDate` TIMESTAMP,
    PRIMARY KEY (ib_num)
);

CREATE TABLE `infoComment` (
	`ic_num` INT AUTO_INCREMENT,
    `ic_boardNum` INT,
    `ic_userId` VARCHAR(30),
    `ic_userName` VARCHAR(30),
    `ic_content` VARCHAR(30),
    `ic_regDate` TIMESTAMP,
    PRIMARY KEY (ic_num),
    FOREIGN KEY (ic_boardNum) REFERENCES infoBoard (ib_num) on delete cascade,
    FOREIGN KEY (ic_userId) REFERENCES user (user_id) on delete cascade
);

CREATE TABLE `course` (
	`course_num` INT AUTO_INCREMENT,
    `course_name` VARCHAR(30),
    `course_content` VARCHAR(100),
    `course_address` VARCHAR(30),
    `course_keyword` VARCHAR(30),
    `course_viewCnt` INT DEFAULT 0,
    `course_rcm` INT DEFAULT 0,
    `course_regDate` TIMESTAMP,
    `course_userId` VARCHAR(30),
    PRIMARY KEY (course_num),
    FOREIGN KEY (course_userId) REFERENCES user (user_id) on delete cascade
);

CREATE TABLE courseComment (
    cc_num INT AUTO_INCREMENT,
    cc_courseNum INT,
    cc_userId VARCHAR(30),
    cc_userName VARCHAR(30),
    cc_content VARCHAR(30),
    cc_regDate TIMESTAMP,
    PRIMARY KEY (cc_num),
    FOREIGN KEY (cc_courseNum) REFERENCES course (course_num) on delete cascade,
    FOREIGN KEY (cc_userId) REFERENCES user (user_id) on delete cascade
);

CREATE TABLE `courseMap`(
	`cm_num` INT AUTO_INCREMENT,
    `cm_courseNum` INT,
	`cm_seq` INT,
	`cm_lat` DOUBLE(10,6),
	`cm_lng` DOUBLE(10,6),
	PRIMARY KEY (cm_num),
	FOREIGN KEY (cm_courseNum) REFERENCES course (course_num) on delete cascade
);

CREATE TABLE `image` (
	`image_num` INT AUTO_INCREMENT,
    `image_type` INT,
    `image_boardNum` INT,
    `image_oriName` VARCHAR(100),
    `image_saveName` VARCHAR(100),
    PRIMARY KEY (image_num)
);

CREATE TABLE `travel`(
	`travel_num` INT AUTO_INCREMENT,
    `travel_title` VARCHAR(100),
    `travel_memo` VARCHAR(100),
    `travel_travelDate` TIMESTAMP,
    `travel_place1Name` VARCHAR(100),
    `travel_place1Address` VARCHAR(100),
	`travel_place2Name` VARCHAR(100),
    `travel_place2Address` VARCHAR(100),
	`travel_place3Name` VARCHAR(100),
    `travel_place3Address` VARCHAR(100),
	`travel_place4Name` VARCHAR(100),
    `travel_place4Address` VARCHAR(100),
    `travel_place5Name` VARCHAR(100),
    `travel_place5Address` VARCHAR(100),
    `travel_userId` varchar(30),
	PRIMARY KEY(travel_num),
    FOREIGN KEY(travel_userId) REFERENCES user(user_id) on delete cascade
);

show TABLES;

INSERT INTO user (user_id, user_pw, user_name, user_birth)
VALUES ('aaa','aaa','aaa','1999-01-01'),('bbb','bbb','bbb','1999-01-02'),('ccc','ccc','ccc','1999-01-03');



INSERT INTO freeBoard(fb_userId, fb_title, fb_writer, fb_content)
VALUES('aaa','11게시물제목11','aaa','11게시물내용11'),('bbb','22게시물제목22','bbb','22게시물내용22'),('ccc','33게시물제목33','ccc','33게시물내용33');

INSERT INTO freeComment (fc_boardNum, fc_userId, fc_userName, fc_content)
VALUES
(1, "aaa", "aaa", "내용1"),
(2, "bbb", "bbb", "내용2"),
(3, "ccc", "ccc", "내용3");

INSERT INTO course(course_name, course_content, course_address,course_keyword, course_userId)
VALUES('코스1','코스내용1','주소1','봄','bbb');

insert into courseMap (cm_courseNum,cm_seq, cm_lat, cm_lng)
values (1,1,3456,4567);

select * from user;
select * from freeBoard;
select * from course;
select * from courseMap;
SELECT * from infoBoard;
-- delete from user
-- where user_id = 'bbb';

