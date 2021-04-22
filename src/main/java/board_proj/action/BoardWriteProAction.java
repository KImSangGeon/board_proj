package board_proj.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDTO;
import board_proj.service.BoardWriteService;

public class BoardWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
	
		String realFolder ="";
		String saveFolder = "/boardUpload";
		int fileSize = 5 * 1024 * 1024; //5M
		
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		
		MultipartRequest multi = null;
		ActionForward forward = null;
		
			try {
				multi = new MultipartRequest(
						request, 
						realFolder, 
						fileSize,
						"UTF-8",
						new DefaultFileRenamePolicy());
			} catch (IOException e) {
				e.printStackTrace();
			}
			BoardDTO boardDTO = new BoardDTO();
			boardDTO.setBoard_name(multi.getParameter("board_name"));
			boardDTO.setBoard_pass(multi.getParameter("board_pass"));
			boardDTO.setBoard_subject(multi.getParameter("board_subject"));
			boardDTO.setBoard_content(multi.getParameter("board_content"));
			boardDTO.setBoard_file(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
			System.out.println("realFolder >> " + realFolder);
			System.out.println("boardDTO >> " + boardDTO);
			
			// service
			BoardWriteService service = new BoardWriteService();
			boolean result = service.registerArticle(boardDTO);
			
			if (result) {
				forward = new ActionForward("boardList.do", true);
				
				System.out.println(forward.getPath());
			}else {
				response.setContentType("text/html; charset=UTF-8");
				SendMessage.sendMessage(response, "등록실패");
			}
			
		return forward;
	}

}