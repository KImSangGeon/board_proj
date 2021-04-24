package board_proj.dao;

import java.util.ArrayList;

import board_proj.dto.BoardDTO;

public interface BoardDao {
	//게시판 갯수 카운트
	int selectListCount();
	//한페이지당 몇개씩 나타내기
	ArrayList<BoardDTO> selectArticleList(int page, int limit);
	//게시판 등록한거 가져오기	
	BoardDTO selectArticle(int board_num);
	//글 등록
	int insertArticle(BoardDTO article);
	//답변 등록
	int insertReplyArticle(BoardDTO article);
	//글 수정
	int updateArticle(BoardDTO article);
	//글 삭제
	int deleteArticle(int board_num);
	//글 조회수 카운트
	int updateReadCount(int board_num);
	//작성자인지 확인하기
	boolean isArticleBoardWriter(int board_num, String pass);
	//글의 다음순서대로 작성하기위한 카운트
	int nextBoardNum();	
}
