<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<link rel="stylesheet"	href="/board_proj/board/css/delete.css">
</head>
<body>
<%-- ${page}<br>${board_num } --%>
		<section id = "passForm">
			<form action="boardDeletePro.do?board_num=${board_num}" name= "deleteForm" id ="pass"  method="post"  autofocus  >
				<input type = "hidden" name= "page" value = "${page }"/>
				<section>
					<h2>게시판 삭제</h2>
					<table>
						<tr>
							<td><label>글 비밀번호 : </label></td>
							<td><input type = "password" name = "board_pass"></td>
						</tr>
					
						<tr>
							<td><input type = "submit" value="삭제"/>&nbsp;&nbsp;
									<input type= "button" value="돌아가기" onclick="javascript:history.go(-1)"/>
							</td>
						</tr>	
						
					</table>
				</section>			
			</form>
			</section>
</body>
</html>