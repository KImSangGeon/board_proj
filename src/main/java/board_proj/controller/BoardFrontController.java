package board_proj.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.action.Action;
import board_proj.action.BoardDeleteFormAction;
import board_proj.action.BoardDeleteProAction;
import board_proj.action.BoardDetailAction;
import board_proj.action.BoardListAction;
import board_proj.action.BoardModifyProAction;
import board_proj.action.BoardModifyformAction;
import board_proj.action.BoardReplyFormAction;
import board_proj.action.BoardReplyProAction;
import board_proj.action.BoardUploadProAction;
import board_proj.action.BoardWriteFormAction;
import board_proj.action.BoardWriteProAction;
import board_proj.dto.ActionForward;

//모든 확장자가 do는 다 거쳐가야된다.
@WebServlet("*.do")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

		try {
			if (comand.equals("/boardWriteForm.do")) {
//					request.getRequestDispatcher(("/board/qna_board_write.jsp").forward(request, response);
				action = new BoardWriteFormAction();
				forward = action.execute(request, response);
				
			} else if (comand.equals("/boardWritePro.do")) {
				action = new BoardWriteProAction();
				forward = action.execute(request, response);

			} else if (comand.equals("/boardList.do")) {
				System.out.println(123123);
//			System.out.println("boardList >>>>>>>>>");
				action = new BoardListAction();
				forward = action.execute(request, response);

			} else if (comand.equals("/boardDetail.do")) {
				action = new BoardDetailAction();
				forward = action.execute(request, response);

			} else if (comand.equals("/boardDeleteForm.do")) {
				// 수정
				action = new BoardDeleteFormAction();
				forward = action.execute(request, response);

			} else if (comand.equals("/boardDeletePro.do")) {
				action = new BoardDeleteProAction();
				forward = action.execute(request, response);

			} else if (comand.equals("/file_down.do")) {
				action = new BoardUploadProAction();
				forward = action.execute(request, response);

			} else if (comand.equals("/boardModifyForm.do")) {
				action = new BoardModifyformAction();
				forward = action.execute(request, response);

			} else if (comand.equals("/boardModifyPro.do")) {
				action = new BoardModifyProAction();
				forward = action.execute(request, response);

			} else if (comand.equals("/boardReplyForm.do")) {
				action = new BoardReplyFormAction();
				forward = action.execute(request, response);

			} else if (comand.equals("/boardReplyPro.do")) {
				action = new BoardReplyProAction();
				forward = action.execute(request, response);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// null이 아닐때 고정값.
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}

	}
}
