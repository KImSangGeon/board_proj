package board_proj.service;

import java.sql.Connection;
import java.util.ArrayList;

import board_proj.daoimpl.BoardDaoImpl;
import board_proj.ds.jndiDS;
import board_proj.dto.BoardDTO;

public class BoardModifyProService {
	
		private BoardDaoImpl dao = BoardDaoImpl.getInstance();
		private Connection con = jndiDS.getConnection();
		
		public BoardModifyProService() {
			dao.setCon(con);
		}
		public boolean isArticleWriter(int board_num, String pass) {
			return dao.isArticleBoardWriter(board_num, pass);
		}
		public boolean updateArticle(BoardDTO article) {
			//1이면 삭제 성공
			return dao.updateArticle(article)==1 ? true : false;
		}
		
		
}
