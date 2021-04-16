package board_proj.service;

import java.sql.Connection;
import java.util.ArrayList;

import board_proj.daoimpl.BoardDaoImpl;
import board_proj.ds.jndiDS;
import board_proj.dto.BoardDTO;

public class BoardListService {
	
		private BoardDaoImpl dao = BoardDaoImpl.getInstance();
		private Connection con = jndiDS.getConnection();
		
		public BoardListService() {
			dao.setCon(con);
		}
		
		public int getListCount() {
			return dao.selectListCount();
		}
		
		public ArrayList<BoardDTO> getArticleList(int page, int limit){
			return dao.selectArticleList(page, limit);
		}
		
}
