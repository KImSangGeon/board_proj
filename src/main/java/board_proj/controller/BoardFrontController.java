package board_proj.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.action.Action;
import board_proj.action.NullAction;
import board_proj.dto.ActionForward;

//모든 확장자가 do는 다 거쳐가야된다.
//1.liadOn 1번쨰 수행하라고 명령 (필터 다음에,),
//2.Param 설정해줘서 properties 작성 
//이유 :속도가 빨라짐, 정리가 깔끔해져 용이함, 가독성 좋음

@WebServlet(urlPatterns = ("*.do"), loadOnStartup = 1, 
																			initParams= {@WebInitParam(name = "configFile",
																			value = "/WEB-INF/commandAction.properties")
																									}
							)
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 해쉬 맵 만들어주기,
	private Map<String, Action> actionMap = new HashMap<>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() - config	 " + config.getInitParameter("configFile"));

		// config 매개변수 받기 위해 위에 Param 설정 -  name으로 매개변수 받기.

		String configFIle = config.getInitParameter("configFile");
		try (InputStream is = config.getServletContext().getResourceAsStream(configFIle)) {
			Properties props = new Properties();
			props.load(is);

//			System.out.println("props >> " + props);

			for (Entry<Object, Object> entry : props.entrySet()) {
				
//				System.out.println(entry.getKey() + " : " + entry.getValue());

				Class<?> cls;
				Action action = null;
				//오타 에러 찾기 편하기 위해서 
				try {
					//key값 액션에 넣어줌
					cls = Class.forName((String) entry.getValue());
					//value값이 되서 액션 맵에 넣어줌
					action 	 = (Action) cls.newInstance();
				} catch (ClassNotFoundException   e) {
					action = new NullAction();
					e.printStackTrace();
				}			
				actionMap.put((String) entry.getKey(), action);
			}
		} catch (IOException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
//			확인 작업
//			for(Entry<String, Action> entry : actionMap.entrySet()) {
//				System.out.println(entry.getKey() + " : " + entry.getValue());
//			}

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

//			String requstURI = request.getRequestURI();
//			String contextPath = request.getContextPath();

//		 	 String command = requstURI.substring(contextPath.length());
//			 System.out.println(requstURI + " >> " + contextPath + " >> " + command);	
		String comand = request.getServletPath();


//			command 는 key 값  
//			dbproperties 작성하는 법 기억하기.

//			try 문 정리
//			getTry(request, response, comand);

		System.out.println(comand);
//		Key값
		Action action = actionMap.get(comand);
//		value값
		ActionForward forward = action.execute(request, response);

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
//	private void getTry(HttpServletRequest request, HttpServletResponse response, String comand) {
//		try {
//			if (comand.equals("/boardWriteForm.do")) {
//				action = new BoardWriteFormAction();
//				forward = action.execute(request, response);
//				
//			} else if (comand.equals("/boardWritePro.do")) {
//				action = new BoardWriteProAction();
//				forward = action.execute(request, response);
//
//			} else if (comand.equals("/boardList.do")) {
////			System.out.println("boardList >>>>>>>>>");
//				action = new BoardListAction();
//				forward = action.execute(request, response);
//
//			} else if (comand.equals("/boardDetail.do")) {
//				action = new BoardDetailAction();
//				forward = action.execute(request, response);
//
//			} else if (comand.equals("/boardDeleteForm.do")) {
//				// 수정
//				action = new BoardDeleteFormAction();
//				forward = action.execute(request, response);
//
//			} else if (comand.equals("/boardDeletePro.do")) {
//				action = new BoardDeleteProAction();
//				forward = action.execute(request, response);
//
//			} else if (comand.equals("/file_down.do")) {
//				action = new BoardUploadProAction();
//				forward = action.execute(request, response);
//
//			} else if (comand.equals("/boardModifyForm.do")) {
//				action = new BoardModifyformAction();
//				forward = action.execute(request, response);
//
//			} else if (comand.equals("/boardModifyPro.do")) {
//				action = new BoardModifyProAction();
//				forward = action.execute(request, response);
//
//			} else if (comand.equals("/boardReplyForm.do")) {
//				action = new BoardReplyFormAction();
//				forward = action.execute(request, response);
//
//			} else if (comand.equals("/boardReplyPro.do")) {
//				action = new BoardReplyProAction();
//				forward = action.execute(request, response);
//		}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

