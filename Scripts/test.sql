select max(BOARD_NUM) from board;

INSERT INTO web_gradle_erp.board
(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE)
VALUES(1, '김상건', '1111', '마칠시간', '5시', 'test.txt', 0, 0, 0, 0, '2021-03-03');

select * from board;
 

INSERT INTO board
(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, )
VALUES(1, '김상건', '1111', '마칠시간', '5시', 'test.txt', 0);

select BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE,
BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE 
from board where BOARD_NUM  = 41;
-- listcount 
select count(*) from board;

-- limit 1페이지부터 10개 제한. 
/*
-- [1][2][3]
* (page-- 1) * 10 ==> 1page 0,  2page ==>10, 3 page ==> 20
*
**/
select BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE,
BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE 
from board order by BOARD_RE_REF desc, BOARD_RE_SEQ asc
limit 0, 10;

select BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE,
BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE 
from board order by BOARD_RE_REF desc, BOARD_RE_SEQ asc
limit 11, 10;

update board 
	set BOARD_READCOUNT  = BOARD_READCOUNT +1
	where BOARD_NUM = 41;
	
select* from board b  where board_num= 63;
select * from board b2 ;
delete from board  where board_num =22;
-- 비밀번호 같은지 확인 두 숫자가 다 맞으면 1이오고 아니면 null
select 1 from board where BOARD_NUM =25 and BOARD_PASS ='rr' ;

-- 수정
select BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE,
BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE 
from board order by BOARD_RE_REF desc, BOARD_RE_SEQ asc;

update board 
	set BOARD_SUBJECT = 'dsd',  BOARD_CONTENT ='dddlll'
	where BOARD_NUM = 82;
	
-- 글 답변
select max(board_num) from board ;

select * from board where BOARD_RE_REF  =70;

update board set BOARD_RE_SEQ  = BOARD_RE_SEQ +1 where BOARD_RE_REF  = 87 and BOARD_RE_SEQ > 0;

INSERT INTO board
(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, 
BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ)
VALUES(1, '김상건', '1111', '마칠시간', '5시', 'test.txt', 0);


