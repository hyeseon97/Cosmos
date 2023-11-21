DROP DATABASE IF EXISTS ssafy_project;

CREATE DATABASE ssafy_project;

use ssafy_project;

CREATE TABLE `user` (
	`user_num` INT AUTO_INCREMENT,
    `user_id` VARCHAR(30),
    `user_pw` VARCHAR(30),
    `user_name` VARCHAR(30),
	`user_birth` VARCHAR(30),
    `user_grade` INT default 1,
    `user_boardCnt` INT default 0,
    `user_commentCnt` INT default 0,    
    `user_joinDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_num),
    UNIQUE (user_id)
);

CREATE TABLE `freeBoard` (
	`fb_num` INT AUTO_INCREMENT,
    `fb_userId` VARCHAR(30),
    `fb_title` VARCHAR(30),
    `fb_writer` VARCHAR(30),
    `fb_content` VARCHAR(100),
    `fb_viewCnt` INT DEFAULT 0,
    `fb_rcm` INT DEFAULT 0,
    `fb_regDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (fb_num),
    FOREIGN KEY (fb_userId) REFERENCES user (user_id) on delete cascade
);

CREATE TABLE `freeComment` (
	`fc_num` INT AUTO_INCREMENT,
    `fc_boardNum` INT,
    `fc_userId` VARCHAR(30),
    `fc_userName` VARCHAR(30),
    `fc_content` VARCHAR(30),
    `fc_regDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
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
    `ib_regDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ib_num)
);

CREATE TABLE `infoComment` (
	`ic_num` INT AUTO_INCREMENT,
    `ic_boardNum` INT,
    `ic_userId` VARCHAR(30),
    `ic_userName` VARCHAR(30),
    `ic_content` VARCHAR(30),
    `ic_regDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ic_num),
    FOREIGN KEY (ic_boardNum) REFERENCES infoBoard (ib_num) on delete cascade,
    FOREIGN KEY (ic_userId) REFERENCES user (user_id) on delete cascade
);

CREATE TABLE `course` (
	`course_num` INT AUTO_INCREMENT,
    `course_userId` VARCHAR(30),
    `course_name` VARCHAR(30),
    `course_content` VARCHAR(100),
    `course_address` VARCHAR(100),
    `course_keyword` VARCHAR(30),
    `course_viewCnt` INT DEFAULT 0,
    `course_rcm` INT DEFAULT 0,
    `course_regDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (course_num),
    FOREIGN KEY (course_userId) REFERENCES user (user_id) on delete cascade
);

CREATE TABLE courseComment (
    cc_num INT AUTO_INCREMENT,
    cc_courseNum INT,
    cc_userId VARCHAR(30),
    cc_userName VARCHAR(30),
    cc_content VARCHAR(30),
    cc_regDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
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

INSERT INTO user (user_id, user_pw, user_name, user_birth, user_grade, user_boardCnt, user_commentCnt, user_joinDate)
VALUES
('aaa','aaa','aaa','1999-01-01', 1, 0, 0, "2023-04-01"),
('bbb','bbb','bbb','1999-01-02', 1, 0, 0, "2023-04-12"),
('ccc','ccc','ccc','1999-01-03', 1, 0, 0, "2023-05-17"),
('hyeseon','hyeseon','박혜선','1997-09-07', 1, 0, 0, "2023-07-02"),
('jungu','jungu','권준구','1993-10-11', 1, 0, 0, "2023-08-08"),
('woojin','woojin','이우진','1997-10-09', 1, 0, 0, "2023-08-11"),
('yeonju','yeonju','조연주','1996-07-09', 1, 0, 0, "2023-08-30"),
('ssafy','ssafy','김싸피','2023-07-03', 1, 0, 0, "2023-09-21"),
('santa','santa','나산타','2023-12-25', 1, 0, 0, "2023-09-06"),
('ruda','ruda','이루다','2020-05-05', 1, 0, 0, "2023-10-26"),
('chunsic','chunsic','춘식이','2018-08-08', 1, 0, 0, "2023-11-16"),
('ironman','ironman','아이언','1999-99-99', 1, 0, 0, "2023-11-21");

SELECT * FROM user;

INSERT INTO freeBoard(fb_userId, fb_title, fb_writer, fb_content, fb_viewCnt, fb_rcm, fb_regDate)
VALUE
('aaa','자전거 오늘 새로 삼','aaa','어떤대어떤대', 0, 0, "2023-04-01"),
('bbb','추워서 자전거 못타겠다ㅠㅠ','bbb','갑자기 겨울이 왔다 자전거 못타겠다ㅠㅠ', 0, 0, "2023-04-12"),
('hyeseon','한밭수목원 자전거 팟 구함','박혜선','다음달에 탈 사람 구합니다 놀러가자요~', 0, 0, "2023-05-17"),
('woojin','크리스마스 기대중','이우진','어디갈지 뭐할지 고민만 하고 아직 못정했다', 0, 0, "2023-07-02"),
('chunsic','겨울에는 고구마지 군고구마','춘식이','군고구마 냠냠 군고구마 맛집 추천 받아요', 0, 0, "2023-08-08"),
('ironman','아이엠 아이언맨','아이언','나 다 시 부 활 시 켜 줘', 0, 0, "2023-09-21"),
('santa','선물 추천 해주세요','나산타','요즘 아가들은 뭘 좋아하나요', 0, 0, "2023-09-30"),
('woojin','자전거 말고 등산','이우진','겨울은 등산이지 눈 내린 산!!!', 0, 0, "2023-10-09"),
('jungu','자전거 샀다!!','권준구','곧 개봉하고 후기 올립니다', 0, 0, "2023-10-21"),
('jungu','자전거 환불했어요..','권준구','소비권장 금지', 0, 0, "2023-10-22"),
('hyeseon','슬기로운 소비생활','박혜선','고민끝에 아무것도 안샀다', 0, 0, "2023-10-24"),
('santa','루돌프대신 타조타야지','나산타','타조야 선물 배달 가자', 0, 0, "2023-11-01"),
('jungu','자전거 다시 삼','권준구','옆에서 누가 자꾸 꼬셔서 결국 다시 샀어요', 0, 0, "2023-11-04"),
('hyeseon','자전거 환불 안하는 법','박혜선','안사면되요', 0, 0, "2023-11-11"),
('jungu','자전거 2번째 환불','권준구','정말 다시는 안살거다 유혹금지', 0, 0, "2023-11-15");

SELECT * FROM freeBoard;

INSERT INTO freeComment (fc_boardNum, fc_userId, fc_userName, fc_content, fc_regDate)
VALUES
(1, "jungu", "권준구", "예쁜데? 나도 사고싶다", "2023-11-15"),
(2, "bbb", "bbb", "그러게요 갑자기 눈오고 추워졌음", "2023-11-15"),
(3, "ccc", "ccc", "같이가요", "2023-11-15"),
(6, "yeonju", "조연주", "아이언맨 돌려내", "2023-11-15"),
(1, "ssafy", "김싸피", "이걸로 지각 금지", "2023-11-15"),
(7, "hyeseon", "박혜선", "티니핑이 최고", "2023-11-15"),
(15, "aaa", "aaa", "과연...", "2023-11-15"),
(14, "bbb", "bbb", "맞는말", "2023-11-15"),
(6, "hyeseon", "박혜선", "엔드게임에서 최고였어요", "2023-11-15"),
(14, "yeonju", "조연주", "우와혜선이최고다", "2023-11-15"),
(3, "ccc", "ccc", "언제가요?", "2023-11-15"),
(12, "hyeseon", "박혜선", "타 조 : 타 이 거", "2023-11-15"),
(8, "santa", "나산타", "나도 같이 가!", "2023-11-15"),
(2, "hyeseon", "박혜선", "여름갈겨울", "2023-11-15"),
(1, "woojin", "이우진", "나도 하나 장만할까", "2023-11-15");

SELECT * FROM freeComment;

SELECT * FROM freeComment WHERE fc_boardNum=7;

INSERT INTO infoBoard(ib_title, ib_content)
VALUE
('[대회] 제1회 가수원 자전거 빨리 타기 대회 개최','추최자 박혜선 자전거 빨리타는 사람 칭찬해줌'),
('[꿀팁] 자전거 현명하게 사는 법','주변 사람에게 휘둘리지 않는다'),
('[시험] 2023년도 자전거 자격증 시험','자전거 1시간 동안 타면 합격'),
('[대회] 자전거 오래타기 대회(외발자전거)','자전거 가장 오래탄 사람 타슈 이용권 무제한'),
('[공지] 회원등급 재조정 공지','게시글 10개 게시물 10개 이상 골드로 등업'),
('[공지] 사이트 점검 및 업데이트 예정(12/25)','참고하시고 이용 바랍니다.');

INSERT INTO infoComment (ic_boardNum, ic_userId, ic_userName, ic_content)
VALUES
(1, "aaa", "aaa", "칭찬내꺼"),
(2, "bbb", "bbb", "좋은 꿀팁이네요"),
(3, "ccc", "ccc", "1시간이라니.."),
(6, "yeonju", "조연주", "확인완료"),
(1, "ssafy", "김싸피", "오늘부터 연습한다"),
(3, "hyeseon", "박혜선", "1시간 너무 길어ㅠ"),
(2, "aaa", "aaa", "맞는말씀"),
(5, "bbb", "bbb", "열심히 글 작성해야지"),
(4, "hyeseon", "박혜선", "타슈 무제한??"),
(4, "yeonju", "조연주", "타슈타고 출근하자"),
(2, "ccc", "ccc", "소비가좋아"),
(5, "hyeseon", "박혜선", "난 이미 코스모스 등급"),
(1, "santa", "나산타", "칭찬은 좋은 선물이야"),
(2, "hyeseon", "박혜선", "누가 생각난다"),
(1, "woojin", "이우진", "혜선이 칭찬 받아야지");

SELECT * FROM infoComment;

INSERT INTO course(course_name, course_content, course_address, course_keyword, course_userId)
VALUES
('한밭수목원-엑스포다리','한밭수목원에서 자전거 대여해주고 엑스포에서 야경보기','대전 서구 둔산대로 169','봄/가을/맑음/도시','woojin'),
('서울한강-치맥=0','한강에서 치맥 안먹을거면 왜 감','서울 영등포구 여의동로 330 한강사업본부 여의도안내센터','도시/연인/친구/여름','jungu'),
('자전거 바닷길 최고','동해바다 최고 산책길, 바다보고 싶다','강원 강릉시 강문동','바다/가족/산','ssafy'),
('가수원에서 엑스포까지','진짜 자전거타기 딱 좋은 코스인데 같이 탈 사람','대전 서구 가수원 타슈정거장','혼자/맑음/가을','hyeseon'),
('봄에 예쁜 산책길','봄바람 휘날리며 흩날리는 벚꽃잎이','대전 유성구 동학사','봄/연인/맑음','woojin'),
('자전거 동호회 1픽','산악 동호회랑도 같이 갔음 마운틴뷰 짱','강원 평창군 용평면 이승복생가길 160','고급/친구/산/맑음/눈','jyeonju'),
('자전거 타면서 단풍구경','은행냄새 안나요 걱정하지 마세요','서울 송파구 잠실로 148','가을/친구/산','chunsic'),
('가족이랑 자전거 여행','두발자전거, 세발자전거, 커플자전거, 레일바이크','경기 의왕시 왕송못동로 221','가족/여름/가을','samsung'),
('인생최고코스','는 바로 가수원에서 엑스포까지 가는 길','대전 서구 가수원 타슈정거장','혼자/친구/초급/도시','hyeseon'),
('겨울맛집 자전거 코스','눈 올때 자전거 안타본 사람??','강원 평창군 용평면 이승복생가길 160','겨울/눈/중급','santa'),
('시간여행코스','타노스 잡으러 갔다온 길 나는 아이언맨','타이탄','프로/친구/시골/비/흐림','ironman'),
('루돌프와 함께','자전거타고 선물 배달갑니다','올해 울지 않은 사람 집','눈/겨울/가족','santa'),
('평범하지 않은 코스','특별한 코스 재밌는 코스 코스모스','강원도충청도전라도경상도','봄/여름/가을/겨울','bbb'),
('시원한 바람이 부는 길','이제 더이상 아무할말이 없다','데이터누가대신만들어줘','비/흐림/산/고급','aaa');

insert into courseMap (cm_courseNum,cm_seq, cm_lat, cm_lng)
values (1,1,3456,4567);

select * from user;
select * from freeBoard;
select * from course;
select * from courseMap;
SELECT * from infoBoard;
-- delete from user
-- where user_id = 'bbb';

