package board_proj.service;

import java.sql.Connection;

import board_proj.daoimpl.BoardDaoImpl;
import board_proj.ds.jndiDS;
import board_proj.dto.BoardDTO;

public class BoardWriteService {
	
		private BoardDaoImpl dao = BoardDaoImpl.getInstance();
		private Connection con = jndiDS.getConnection();
		
		public BoardWriteService() {
			dao.setCon(con);
		}
		
		public boolean registerArticle(BoardDTO boardDTO) {
			return dao.insertArticle(boardDTO) == 1? true : false;
		}
}
