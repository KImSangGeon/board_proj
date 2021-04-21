package board_proj.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.action.Action;
import board_proj.action.BoardDeleteProAction;
import board_proj.action.BoardDetailAction;
import board_proj.action.BoardListAction;
import board_proj.action.BoardModifyProAction;
import board_proj.action.BoardModifyformAction;
import board_proj.action.BoardReplyFormAction;
import board_proj.action.BoardReplyProAction;
import board_proj.action.BoardUploadProAction;
import board_proj.action.BoardWriteProAction;
import board_proj.dto.ActionForward;
//모든 확장자가 do는 다 거쳐가야된다.
@WebServlet("*.do")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);	
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String requstURI = request.getRequestURI();
//		String contextPath = request.getContextPath();
		String comand = request.getServletPath();
		
		/*
		 * String command = requstURI.substring(contextPath.length());
		 * System.out.println(requstURI + " >> " + contextPath + " >> " + command);
		 */
		System.out.println(comand);
		
		ActionForward forward = null;
		Action action = null;
		
		if(comand.equals("/boardWriteForm.do")) {
//			request.getRequestDispatcher(("/board/qna_board_write.jsp").forward(request, response);
	
			forward = new ActionForward();
			forward.setPath("/board/qna_board_write.jsp");
			
		}else if(comand.equals("/boardWritePro.do")) {
			action = new BoardWriteProAction();			
			try {
			forward = 	action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		}else if (comand.equals("/boardList.do")) {
//			System.out.println("boardList >>>>>>>>>");
			action = new BoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		}else if(comand.equals("/boardDetail.do")) {
			action =new BoardDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		}else if(comand.equals("/boardReplyForm.do")) {
			action = new BoardReplyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else if(comand.equals("/boardDeleteForm.do")) {
//			board_num=75&page=1
			String nowPage = request.getParameter("page");
			request.setAttribute("page", nowPage);
			
			int board_num = Integer.parseInt(request.getParameter("board_num"));
			request.setAttribute("board_num",  board_num);
			
			forward = new ActionForward();
			forward.setPath("/board/qna_board_delete.jsp");
	
		}else if (comand.equals("/boardDeletePro.do")) {			
			action = new BoardDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		}else if(comand.equals("/file_down.do")) {
		action = new BoardUploadProAction();
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		}else if(comand.equals("/boardModifyForm.do")){
			String nowPage = request.getParameter("page");
			request.setAttribute("page", nowPage);
			
			action = new BoardModifyformAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(comand.equals("/boardModifyPro.do")) {
			action = new BoardModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(comand.equals("/boardReplyForm.do")) {
			action = new BoardReplyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (comand.equals("/boardReplyPro.do"))
			action = new BoardReplyProAction();
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//null이 아닐때 고정값.
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
		
		
	}


}
