<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="UTF-8">
		<title>게시판 글쓰기</title>
		<link rel = "stylesheet"  href ="<%=request.getContextPath()%>/board/css/style.css">
</head>
<body>
<%=request.getContextPath()%>
		<section id = "writeForm">
			<h2>게시판글등록</h2>
				<form action="boardWritePro.do" method="post" enctype="multipart/form-data" name="boardform">
			<table>
				<tbody>
					<tr>
						<td class="td_left">
							<label for="BOARD_NAME">글쓴이</label>
						</td>
						<td >
							<input type="text" name="board_name" id="board_name" required />
						</td>
					</tr>
					<tr>
						<td class="td_left">
							<label for="board_pass">비밀번호</label>
						</td>
						<td>
							<input name="board_pass" type="password" id="board_pass" required />
						</td>
					</tr>
					<tr>
						<td class="td_left">
							<label for="board_subject">제 목</label>
						</td>
						<td >
							<input name="board_subject" type="text" id="board_subject" required />
						</td>
					</tr>
					<tr>
						<td class="td_left">
							<label for="board_content">내 용</label>
						</td>
						<td>
							<textarea id="board_content" name="board_content" cols="40" rows="15" required></textarea>
						</td>
					</tr>
					<tr>
						<td class="td_left">
							<label for="board_file"> 파일 첨부 </label>
						</td>
						<td >
							<input name="board_file" type="file" id="board_file" required /></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2" id="commandCell">
							<input type="submit" value="등록">&nbsp;&nbsp; 
							<input type="reset" value="다시쓰기" />
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</section>
	<!-- 게시판 등록 -->
</body>
</html>