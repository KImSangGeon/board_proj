package board_proj.daoimpl;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import board_proj.JdbcUtil;
import board_proj.dto.BoardDTO;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardDaoImplTest {
		private static Connection con = JdbcUtil.getConnection();
		private static BoardDaoImpl dao = BoardDaoImpl.getInstance();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao.setCon(con);
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void testSetCon() {
		fail("Not yet implemented");
	}


	@Test
	public void test01NextBoardNum() {
		System.out.println("testNextBoardNum");
		int res = dao.nextBoardNum();
		Assert.assertNotEquals(0, res);
		System.out.println("next Number >> " + res);
	}

	

	@Test
	public void test02SelectListCount() {
		System.out.println("testSelectListCount");
		int res = dao.selectListCount();
		Assert.assertNotEquals(-1, res);
		System.out.println("ListCount >> " + res);
	}

	@Test
	public void test03SelectArticleList() {
		System.out.println("testSelectArticleList");
		int page = 1; 
		int limit = 10;
		ArrayList<BoardDTO> list = dao.selectArticleList(page, limit);
		Assert.assertNotNull(list);
		
		list.stream().forEach(System.out::println);
		System.out.println("======================");
		
		dao.selectArticleList(2, 10).stream().forEach(System.out::println);
	}

	@Test
	public void test05SelectArticle() {
		System.out.println("test05SelectArticle");
		BoardDTO res = dao.selectArticle(41);
//		Assert.assertNotEquals(-1, res);
		Assert.assertNotNull(res); 
		System.out.println(res);
	}

	@Test
	public void test04InsertArticle() {
		System.out.println("testInsertArticle");
		BoardDTO newBoard = new BoardDTO(
				"김상건",
				"1234",
				"5시 퇴근 가능할까",
				"불가능함",
				"text.txt");
		int res = dao.insertArticle(newBoard);
		
	}

	@Test
	public void testInsertReplyArticle() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateArticle() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteArticle() {
		fail("Not yet implemented");
	}

	@Test
	public void test06UpdateReadCount() {
		System.out.println("testUpdateReadCount");
		int res = dao.updateReadCount(41);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectArticle(41));
		
		
		
		}

	@Test
	public void testIsArticleBoardWriter() {
		fail("Not yet implemented");
	}

}
