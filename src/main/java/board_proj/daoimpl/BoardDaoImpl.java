package board_proj.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board_proj.dao.BoardDao;
import board_proj.dto.BoardDTO;

public class BoardDaoImpl implements BoardDao {
	private static final BoardDaoImpl instance = new BoardDaoImpl();
	private Connection con;

	public static BoardDaoImpl getInstance() {
		return instance;
	}
	
	public void setCon(Connection con) {
		this.con = con;
	}
	@Override
	public int selectListCount() {
		String sql = "select count(*) from board";
		try(PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				return rs.getInt(1);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<BoardDTO> selectArticleList(int page, int limit) {
		String sql = "select BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT,"
				+ " 	BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ,"
				+ " 	BOARD_READCOUNT, BOARD_DATE from board "
				+ "	order by BOARD_RE_REF desc, BOARD_RE_SEQ asc" 
				+	"	limit ?, ?                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        ";
		int startRow = (page-1) * limit;   //해당 페이지 시작위치.
		try(PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();					
				do {
					list.add(getBoardDTO(rs));
				}while(rs.next());
				return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private BoardDTO getBoardDTO(ResultSet rs) throws SQLException {
//		BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE,
//		BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE 
		int boardNum = rs.getInt("BOARD_NUM");
		String boardName = rs.getString("BOARD_NAME");
		String boardPass = rs.getString("BOARD_PASS");
		String boardSubject = rs.getString("BOARD_SUBJECT");
		String boardContent = rs.getString("BOARD_CONTENT");
		String boardFile = rs.getString("BOARD_FILE");
		int boardReRef = rs.getInt("BOARD_RE_REF");
		int boardReLev = rs.getInt("BOARD_RE_LEV");
		int boardReSeq = rs.getInt("BOARD_RE_SEQ");
		int boardReadcount = rs.getInt("BOARD_READCOUNT");
		Date boardDate = rs.getDate("BOARD_DATE");
		return new BoardDTO(boardNum, boardName, boardPass, boardSubject, boardContent, boardFile, boardReRef, boardReLev, boardReSeq, boardReadcount, boardDate);
	}
	@Override
	public BoardDTO selectArticle(int board_num) {
		String sql ="select BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, "
				    + 	"	BOARD_CONTENT, BOARD_FILE,"
					+ 	"	BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ,"
					+ "	 BOARD_READCOUNT, BOARD_DATE"
					+ "	from board where BOARD_NUM  = ? ";
		try(PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, board_num);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getBoardDTO(rs);
					
					}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertArticle(BoardDTO article) {
		String sql = "INSERT INTO web_gradle_erp.board" 
					+ 	"	(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, "
					+ "	BOARD_FILE, BOARD_RE_REF)"
					+ 	"	VALUES(?, ?, ? , ?, ?, ?, ?)";
		try(PreparedStatement pstmt = con.prepareStatement(sql)){
			int nextNum = nextBoardNum();
			pstmt.setInt(1, nextNum);
			pstmt.setString(2, article.getBoard_name());
			pstmt.setString(3, article.getBoard_pass());
			pstmt.setString(4, article.getBoard_subject());
			pstmt.setString(5, article.getBoard_content());		
			pstmt.setString(6, article.getBoard_file());		
			pstmt.setInt(7, nextNum);
		
			return pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateArticle(BoardDTO article) {
		String sql = "update board "
						+ 	"	set BOARD_SUBJECT = ?,  BOARD_CONTENT =? "
						+ "	where BOARD_NUM = ? ";
		try(PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, article.getBoard_subject());
			pstmt.setString(2, article.getBoard_content());
			pstmt.setInt(3, article.getBoard_num());
			return pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteArticle(int board_num) {
		String sql = "delete from board  where board_num = ? ";
		try(PreparedStatement pstmt  = con.prepareStatement(sql)){
			pstmt.setInt(1, board_num);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateReadCount(int board_num) {
		String sql = "update board	set BOARD_READCOUNT  = BOARD_READCOUNT +1 "  
				+  "	where BOARD_NUM = ? ";
		try(PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, board_num);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean isArticleBoardWriter(int board_num, String pass) {
		String sql ="select 1 from board where BOARD_NUM = ? and BOARD_PASS =?";
		try(PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, board_num);
			pstmt.setString(2, pass);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public int nextBoardNum() {
		String sql = "select max(BOARD_NUM) from  board";
		try(PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				return rs.getInt(1)+1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	@Override
	public int insertReplyArticle(BoardDTO article) {
		int next_board_num = nextBoardNum();
		int re_ref = article.getBoard_re_ref();
		int re_lev = article.getBoard_re_lev();
		int re_seq = article.getBoard_re_seq();
		
		String sql1 = "update board set BOARD_RE_SEQ  = BOARD_RE_SEQ +1 "
				+ "	where BOARD_RE_REF  = ? and BOARD_RE_SEQ > ?";
		
		String sql2 = "INSERT INTO board"
				+ 	"	(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT,"
				+ " BOARD_CONTENT, BOARD_FILE, "
				+ "	BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ)"
				+"	VALUES(?, ?, ? ,?, ?, ' ', ?, ?, ?)";
		//트랜잭션 적용 
		try {
			con.setAutoCommit(false);
			try(PreparedStatement pstmt = con.prepareStatement(sql1)){
				pstmt.setInt(1, re_ref);
				pstmt.setInt(2, re_seq);				
				//쿼리 날리기전에 체크 필수
				System.out.println(pstmt);
			
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			re_seq += 1;
			re_lev += 1;
			
			try(PreparedStatement pstmt = con.prepareStatement(sql2)){
				pstmt.setInt(1, next_board_num);
				pstmt.setString(2, article.getBoard_name());
				pstmt.setString(3, article.getBoard_pass());
				pstmt.setString(4, article.getBoard_subject());
				pstmt.setString(5, article.getBoard_content());
				pstmt.setInt(6, re_ref);
				pstmt.setInt(7, re_lev);
				pstmt.setInt(8, re_seq);
				//쿼리 날리기전에 체크 필수
				System.out.println(pstmt);
				
				pstmt.executeUpdate();				
			}			
			con.commit();
			return 1;
		}catch(Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				//마지막 닫아주기. 여러개일떄 ds에 넣어서 받아오기.
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return 0;
	}
	
}
