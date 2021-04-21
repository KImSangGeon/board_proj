package board_proj.service;

import java.sql.Connection;
import java.util.ArrayList;

import board_proj.daoimpl.BoardDaoImpl;
import board_proj.ds.jndiDS;
import board_proj.dto.BoardDTO;

public class BoardReplyProService {
	
		private BoardDaoImpl dao = BoardDaoImpl.getInstance();
		private Connection con = jndiDS.getConnection();
		
		public BoardReplyProService() {
			dao.setCon(con);
		}
		public boolean replyArticle(BoardDTO article) {
			return dao.insertReplyArticle(article) == 1 ?true : false;			
		}
		
}
