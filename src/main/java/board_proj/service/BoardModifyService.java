package board_proj.service;

import java.sql.Connection;
import java.util.ArrayList;

import board_proj.daoimpl.BoardDaoImpl;
import board_proj.ds.jndiDS;
import board_proj.dto.BoardDTO;

public class BoardModifyService {
	
		private BoardDaoImpl dao = BoardDaoImpl.getInstance();
		private Connection con = jndiDS.getConnection();
		
		public BoardModifyService() {
			dao.setCon(con);
		}
		public BoardDTO getArticle(int board_num) {
			//board_num에 해당하는 BoardDto return;  수정이기에 조회수 증가
			return dao.selectArticle(board_num);
			
		}
		
}
