package board_proj.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDTO;
import board_proj.service.BoardDeleteService;
import board_proj.service.BoardModifyProService;

public class BoardModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String pass = request.getParameter("board_pass");
		String page = request.getParameter("page");		
		
		System.out.println("num >> " + board_num + "	pass >> " + pass + "	page >> " + page);
	
		BoardModifyProService service = new BoardModifyProService();

		ActionForward foward = null;
		//패스워드 일치여부
	
		boolean isArticleWriter = service.isArticleWriter(board_num, pass);
		if(!isArticleWriter) {
			getMessage(response, "삭제할 권한이 없습니다.");
			return foward;
		}		
		BoardDTO article = new BoardDTO();
		String board_subject = request.getParameter("board_subject");
		String board_content = request.getParameter("board_content");
		
		article.setBoard_num(board_num);
		article.setBoard_subject(board_subject);
		article.setBoard_content(board_content);
		
		boolean isupdateSuccess = service.updateArticle(article);
//		System.out.println(isupdateSuccess);
		if(!isupdateSuccess) {
				getMessage(response, "수정실패");
				return foward;
			}		
				foward = new ActionForward();
				foward.setRedirect(true);
				foward.setPath("boardDetail.do?board_num=" + board_num + "&page=" + page);				
				
		return foward;
	}

	private void getMessage(HttpServletResponse response, String msg) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('"+ msg +"')");
		out.println("history.back()");
		out.println("</script>");
		out.close();
	}
	
	

}

