DROP DATABASE IF EXISTS ssafy_project;

CREATE DATABASE ssafy_project;

use ssafy_project;

CREATE TABLE `user` (
	`user_num` INT AUTO_INCREMENT,
    `user_id` VARCHAR(100),
    `user_pw` VARCHAR(100),
    `user_name` VARCHAR(100),
	`user_birth` VARCHAR(100),
    `user_grade` INT default 1,
    `user_boardCnt` INT default 0,
    `user_commentCnt` INT default 0,    
    `user_joinDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_num),
    UNIQUE (user_id)
);

CREATE TABLE `freeBoard` (
	`fb_num` INT AUTO_INCREMENT,
    `fb_userId` VARCHAR(100),
    `fb_title` VARCHAR(100),
    `fb_writer` VARCHAR(100),
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
    `fc_userId` VARCHAR(100),
    `fc_userName` VARCHAR(100),
    `fc_content` VARCHAR(100),
    `fc_regDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (fc_num),
    FOREIGN KEY (fc_boardNum) REFERENCES freeBoard (fb_num) on delete cascade, 
    FOREIGN KEY (fc_userId) REFERENCES user (user_id) on delete cascade
);

CREATE TABLE `infoBoard` (
	`ib_num` INT AUTO_INCREMENT,
    `ib_title` VARCHAR(100),
    `ib_content` VARCHAR(100),
    `ib_viewCnt` INT DEFAULT 0,
    `ib_rcm` INT DEFAULT 0,
    `ib_regDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ib_num)
);

CREATE TABLE `infoComment` (
	`ic_num` INT AUTO_INCREMENT,
    `ic_boardNum` INT,
    `ic_userId` VARCHAR(100),
    `ic_userName` VARCHAR(100),
    `ic_content` VARCHAR(100),
    `ic_regDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ic_num),
    FOREIGN KEY (ic_boardNum) REFERENCES infoBoard (ib_num) on delete cascade,
    FOREIGN KEY (ic_userId) REFERENCES user (user_id) on delete cascade
);

CREATE TABLE `course` (
	`course_num` INT AUTO_INCREMENT,
    `course_userId` VARCHAR(100),
    `course_name` VARCHAR(100),
    `course_content` VARCHAR(100),
    `course_address` VARCHAR(100),
    `course_keyword` VARCHAR(100),
    `course_viewCnt` INT DEFAULT 0,
    `course_rcm` INT DEFAULT 0,
    `course_regDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (course_num),
    FOREIGN KEY (course_userId) REFERENCES user (user_id) on delete cascade
);

CREATE TABLE `courseComment` (
    `cc_num` INT AUTO_INCREMENT,
    `cc_courseNum` INT,
    `cc_userId` VARCHAR(100),
    `cc_userName` VARCHAR(100),
    `cc_content` VARCHAR(100),
    `cc_regDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
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
    `travel_userId` VARCHAR(100),
	PRIMARY KEY(travel_num),
    FOREIGN KEY(travel_userId) REFERENCES user(user_id) on delete cascade
);

CREATE TABLE `place` (
	`place_num` INT AUTO_INCREMENT,
    `place_travelNum` INT,
    `place_seq` INT,
    `place_name` VARCHAR(50),
    `place_address` VARCHAR(50),
    PRIMARY KEY (place_num),
    FOREIGN KEY (place_travelNum) REFERENCES travel(travel_num) on DELETE CASCADE
);

CREATE TABLE `like` (
	`like_num` INT AUTO_INCREMENT,
	`like_userId` VARCHAR(100),
    `like_type` INT,
    `like_boardNum` INT,
    PRIMARY KEY (like_num)
);

show TABLES;

INSERT INTO user (user_id, user_pw, user_name, user_birth, user_grade, user_boardCnt, user_commentCnt, user_joinDate)
VALUES
('aaa','aaa','aaa','1999-01-01', 1, 0, 0, "2023-04-01"),
('bbb','bbb','bbb','1999-01-02', 1, 0, 0, "2023-04-12"),
('ccc','ccc','ccc','1999-01-03', 1, 0, 0, "2023-05-17"),
('hyeseon','hyeseon','박혜선','1997-09-07', 5, 0, 0, "2023-07-02"),
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

INSERT INTO course(course_name, course_content, course_address, course_keyword, course_userId, course_viewCnt, course_rcm)
VALUES
('한밭수목원-엑스포다리','한밭수목원에서 자전거 대여해주고 엑스포에서 야경보기','대전 서구 둔산대로 169','봄가을맑음도시','woojin', 257, 150),
('서울한강-치맥=0','한강에서 치맥 안먹을거면 왜 감','서울 영등포구 여의동로 330 한강사업본부 여의도안내센터','도시연인친구여름','jungu', 190, 142),
('자전거 바닷길 최고','동해바다 최고 산책길, 바다보고 싶다','강원 강릉시 강문동','바다가족산','ssafy', 37, 29),
('가수원에서 엑스포까지','진짜 자전거타기 딱 좋은 코스인데 같이 탈 사람','대전 서구 가수원 타슈정거장','혼자맑음가을','hyeseon', 218, 175),
('봄에 예쁜 산책길','봄바람 휘날리며 흩날리는 벚꽃잎이','대전 유성구 동학사','봄연인맑음','woojin', 95, 72),
('자전거 동호회 1픽','산악 동호회랑도 같이 갔음 마운틴뷰 짱','강원 원주시 원골길 5','고급친구산맑음눈','yeonju', 80, 72),
('자전거 타면서 단풍구경','은행냄새 안나요 걱정하지 마세요','대전 서구 장태산','가을친구산','chunsic', 243, 220),
('가족이랑 자전거 여행','두발자전거, 세발자전거, 커플자전거, 레일바이크','경기 의왕시 왕송못동로 221','가족여름가을','ironman', 64, 49),
('인생최고코스','대청호를 옆에 두고 시원하게 달려보자','대전 대덕구 대청호','혼자친구초급도시','hyeseon', 169, 167),
('겨울맛집 자전거 코스','눈길위로 자전거 타고 현대아울렛 놀러가기','대전 대덕구 신탄진','겨울눈가족','santa', 85, 48),
('시간여행코스','타노스 잡으러 갔다온 길 나는 아이언맨','타이탄','프로친구시골비흐림','ironman', 20, 10),
('루돌프와 함께','자전거타고 선물 배달갑니다','올해 울지 않은 사람 집','눈겨울가족','santa', 100, 54),
('평범하지 않은 코스','특별한 코스 재밌는 코스 코스모스','강원도충청도전라도경상도','봄여름가을겨울','bbb', 154, 148),
('시원한 바람이 부는 길','이제 더이상 아무할말이 없다','데이터누가대신만들어줘','비흐림산고급','aaa', 23, 17);

SELECT * FROM course
WHERE course_keyword LIKE concat('%', '봄', '%') AND course_keyword LIKE concat('%', '맑음', '%');

INSERT INTO courseMap (cm_num, cm_courseNum, cm_seq, cm_lat, cm_lng)
VALUES
(1, 1, 1, 36.364929, 127.388048),
(2, 1, 2, 36.364947, 127.393530),
(3, 1, 3, 36.374554, 127.393489),
(4, 1, 4, 36.374314, 127.384083),
(5, 1, 5, 36.371052, 127.384111),
(6, 1, 6, 36.370805, 127.388077),
(7, 2, 1, 37.522337, 126.958665),
(8, 2, 2, 37.521779, 126.961448),
(9, 2, 3, 37.517961, 126.970974),
(10, 2, 4, 37.516593, 126.975251),
(11, 2, 5, 37.516198, 126.983033),
(12, 2, 6, 37.517063, 126.990001),
(13, 2, 7, 37.519515, 126.994163),
(14, 2, 8, 37.522380, 127.000566),
(15, 2, 9, 37.524740, 127.002806),
(16, 2, 10, 37.526290, 127.003801),
(17, 2, 11, 37.529930, 127.010408),
(18, 3, 1, 37.794152, 128.918960),
(19, 3, 2, 37.792146, 128.920475),
(20, 3, 3, 37.790597, 128.922058),
(21, 3, 4, 37.789542, 128.923223),
(22, 3, 5, 37.788246, 128.925369),
(23, 3, 6, 37.787334, 128.926605),
(24, 3, 7, 37.787009, 128.927766),
(25, 3, 8, 37.784013, 128.931354),
(26, 3, 9, 37.782051, 128.934038),
(27, 3, 10, 37.779709, 128.937314),
(28, 3, 11, 37.773628, 128.945542),
(29, 3, 12, 37.773502, 128.946072),
(30, 3, 13, 37.772724, 128.947357),
(31, 3, 14, 37.771253, 128.949134),
(32, 3, 15, 37.770855, 128.950304),
(33, 4, 1, 36.313836, 127.351895),
(34, 4, 2, 36.319976, 127.350920),
(35, 4, 3, 36.322843, 127.350710),
(36, 4, 4, 36.327125, 127.353202),
(37, 4, 5, 36.332550, 127.353226),
(38, 4, 6, 36.340540, 127.351191),
(39, 4, 7, 36.348170, 127.349264),
(40, 4, 8, 36.352207, 127.349460),
(41, 4, 9, 36.358332, 127.356418),
(42, 4, 10, 36.360541, 127.359102),
(43, 4, 11, 36.366786, 127.368023),
(44, 4, 12, 36.374428, 127.379603),
(45, 4, 13, 36.374852, 127.393268),
(46, 5, 1, 36.368155, 127.258511),
(47, 5, 2, 36.365707, 127.256753),
(48, 5, 3, 36.363748, 127.254418),
(49, 5, 4, 36.361177, 127.251324),
(50, 5, 5, 36.360557, 127.250374),
(51, 5, 6, 36.360307, 127.249159),
(52, 5, 7, 36.360067, 127.247487),
(53, 5, 8, 36.360092, 127.244212),
(54, 5, 9, 36.359549, 127.240845),
(55, 5, 10, 36.359615, 127.239464),
(56, 5, 11, 36.358249, 127.237766),
(57, 6, 1, 37.250629, 128.150580),
(58, 6, 2, 37.250908, 128.150136),
(59, 6, 3, 37.250817, 128.149991),
(60, 6, 4, 37.251071, 128.149645),
(61, 6, 5, 37.251435, 128.149296),
(62, 6, 6, 37.251777, 128.148816),
(63, 6, 7, 37.252004, 128.148456),
(64, 7, 1, 36.221251, 127.338422),
(65, 7, 2, 36.220747, 127.338365),
(66, 7, 3, 36.220169, 127.338596),
(67, 7, 4, 36.219555, 127.338960),
(68, 7, 5, 36.218664, 127.338745),
(69, 7, 6, 36.218004, 127.339454),
(70, 7, 7, 36.216883, 127.340606),
(71, 7, 8, 36.216260, 127.341103),
(72, 7, 9, 36.215466, 127.341311),
(73, 7, 10, 36.214755, 127.340874),
(74, 8, 1, 36.318595, 127.467439),
(75, 8, 2, 36.320024, 127.470833),
(76, 8, 3, 36.326358, 127.473277),
(77, 8, 4, 36.329885, 127.474634),
(78, 9, 1, 36.293303, 127.445024),
(79, 9, 2, 36.383485, 127.479797),
(80, 9, 3, 36.382750, 127.478700),
(81, 9, 4, 36.381741, 127.478649),
(82, 9, 5, 36.379904, 127.478393),
(83, 9, 6, 36.374942, 127.475086),
(84, 9, 7, 36.371334, 127.475867),
(85, 9, 8, 36.368598, 127.475025),
(86, 9, 9, 36.364502, 127.476226),
(87, 9, 10, 36.360756, 127.480170),
(88, 10, 1, 36.406700, 127.417085),
(89, 10, 2, 36.412277, 127.419925),
(90, 10, 3, 36.417396, 127.419685),
(91, 10, 4, 36.430206, 127.405613),
(92, 10, 5, 36.427439, 127.402833),
(93, 10, 6, 36.429035, 127.399987),
(94, 10, 7, 36.425224, 127.396934),
(95, 11, 1, 36.481441, 127.289424),
(96, 11, 2, 36.475137, 127.272440),
(97, 11, 3, 36.464748, 127.275973),
(98, 11, 4, 36.452182, 127.285031),
(99, 11, 5, 36.430552, 127.286023),
(100, 11, 6, 36.421004, 127.298833),
(101, 11, 7, 36.394728, 127.312287),
(102, 12, 1, 36.406559, 127.372491),
(103, 12, 2, 36.405278, 127.366866),
(104, 12, 3, 36.400106, 127.360956),
(105, 12, 4, 36.394154, 127.350228),
(106, 12, 5, 36.389520, 127.356805),
(107, 12, 6, 36.384037, 127.358207),
(108, 12, 7, 36.380293, 127.356496);

INSERT INTO image (image_type, image_boardNum, image_oriName, image_saveName)
VALUES
('1', '1', 'course_1.jpg', '1700788475873_course_1.jpg'),
('1', '3', 'course_8.jpg', '1700793744588_course_8.jpg'),
('1', '2', 'course_5.jpg', '1700788568791_course_5.jpg'),
('1', '4', 'course_6.jpg', '1700788584233_course_6.jpg'),
('1', '5', 'course_6.jpg', '1700792460439_course_6.jpg'),
('1', '6', 'course_8.jpg', '1700788613420_course_8.jpg'),
('1', '7', 'course_11.jpg', '1700788762800_course_11.jpg'),
('1', '8', 'course_12.jpg', '1700788776382_course_12.jpg'),
('1', '9', 'course_13.jpg', '1700788789843_course_13.jpg'),
('1', '10', 'course_14.jpg', '1700788800665_course_14.jpg'),
('1', '11', 'course_15.jpg', '1700788812067_course_15.jpg'),
('1', '12', 'course_15.jpg', '1700788812067_course_15.jpg'),
('1', '13', 'course_3.jpg', '1700792520756_course_3.jpg'),
('1', '14', 'course_15.jpg', '1700788812067_course_7.jpg');


-- INSERT INTO travel (travel_num, travel_title, travel_memo, travel_travelDate, travel_userId)
-- VALUES
-- (1, "대전 빵 여행", "한밭수목원에서 자전거 타고 산책하고 성심당 가서 빵 먹기", "2023-11-22", "aaa");

select * from user;
select * from freeBoard;
select * from course;
select * from courseMap;
SELECT * from infoBoard;
SELECT * FROM travel;
SELECT * FROM place;
select * from image;
-- delete from user
-- where user_id = 'bbb';