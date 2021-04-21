<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet"	href="/board_proj/board/css/modify.css">
		<script type="text/javascript">
			function modifyboard(){
				modifyform.submit();
			}
		</script>
</head>
<body>
<%-- ${article } --%>
		<section id = "writeForm">
			<h2>게시판글수정</h2>
			<form action="boardModifyPro.do?board_num=${article.board_num}" method="post" name = "modifyform">
				<input type = "hidden" name = "board_num" value = "${article.board_num }">
				<input type = "hidden" name= "page" value = "${page }"/>
					<table>
						<tr>
							<td class = "td_left">
								<label for = "board_name">글쓴이</label>
							</td>
							<td class = "td_right">
								<input type="text" name = "board_name" id = "board_name" value ="${article.board_name}">
							</td>
						</tr>
						<tr>
							<td class ="td_left">
								<label for = "board_pass">비밀번호</label>
							</td>
							<td class ="td_right">
								<input type="password" name = "board_pass" id = "board_pass"/>
							</td>
						</tr>
						<tr>
							<td class = "td_left">
								<label for = "board_subject">제목</label>
							</td>
							<td class = "td_right">
								<input type = "text" name = "board_subject" id ="board_subject" value = "${article.board_subject }"/>
							</td>
						</tr>
						<tr>
							<td class = "td_left">
								<label for = "board_content">내용</label>
							</td>
							<td>
								<textarea id = "board_content" name = "board_content" rows="15" cols="40">${article.board_content }</textarea>
							</td>
						</tr>	
						</table>
							<section id = "commandCell">
								<a href = "javascript:modifyboard()">[수정]</a>&nbsp;&nbsp;
								<a href = "javascript:history.go(-1)">[뒤로]</a>
							</section>
			</form>
		</section>
		
</body>
</html>