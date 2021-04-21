package board_proj.service;

import java.sql.Connection;

import board_proj.daoimpl.BoardDaoImpl;
import board_proj.ds.jndiDS;
import board_proj.dto.BoardDTO;

public class BoardDeleteService {
	
		private BoardDaoImpl dao = BoardDaoImpl.getInstance();
		private Connection con = jndiDS.getConnection();
		
		public BoardDeleteService() {
			dao.setCon(con);
		}
		
		public boolean isArticleWriter(int board_num, String pass) {
			return dao.isArticleBoardWriter(board_num, pass);
		}
		
		public boolean removeArticle(int board_num) {
			//1이면 삭제 성공
			return dao.deleteArticle(board_num) ==1 ? true : false;
		}
}
