package board_proj.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.service.BoardDeleteService;

public class BoardDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
//		/boardDeletePro.do?board_num=73&hidden page=1 board_pass=d
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String page = request.getParameter("page");
		String pass = request.getParameter("board_pass");

		BoardDeleteService service = new BoardDeleteService();

		ActionForward foward = null;
		
		// 패스워드 일치여부
		boolean isArticleWriter = service.isArticleWriter(board_num, pass);
		System.out.println(isArticleWriter);
		if (!isArticleWriter) {
			SendMessage.sendMessage(response, "삭제할 권한이 없습니다.");
			return foward;
		}

		boolean isDeleteSuccess = service.removeArticle(board_num);
		if (!isDeleteSuccess) {
			SendMessage.sendMessage(response, "삭제실패");
			return foward;
		}
		
		foward = new ActionForward("boardList.do?page=" + page,true);

		return foward;
	}

}
