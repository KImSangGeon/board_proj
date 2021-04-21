package board_proj.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDTO;
import board_proj.service.BoardModifyService;

public class BoardModifyformAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		board_num=81
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		System.out.println("board_num >> " + board_num);
		
		BoardModifyService service = new BoardModifyService();
		
		BoardDTO article = service.getArticle(board_num);
		System.out.println("article  >> " + article);
		 
		request.setAttribute("article", article);
		ActionForward forward = new ActionForward();
		
		forward.setPath("/board/qna_board_modify.jsp");
		return forward;
	}

}
