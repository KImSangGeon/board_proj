package board_proj.service;

import java.sql.Connection;
import java.util.ArrayList;

import board_proj.daoimpl.BoardDaoImpl;
import board_proj.ds.jndiDS;
import board_proj.dto.BoardDTO;

public class BoardDetailService {
	
		private BoardDaoImpl dao = BoardDaoImpl.getInstance();
		private Connection con = jndiDS.getConnection();
		
		public BoardDetailService() {
			dao.setCon(con);
		}
		public BoardDTO getArticle(int board_num) {
			//조회수 증가
			dao.updateReadCount(board_num);
			//board_num에 해당하는 BoardDto return;
			return dao.selectArticle(board_num);
			
		}
		
}
