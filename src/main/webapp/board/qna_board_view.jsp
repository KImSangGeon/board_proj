<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<link rel ="stylesheet" href= "/board_proj/board/css/view.css">
</head>
<body>
<%-- ${page }<br>${article}	 --%>
	<section class = "main">
		<section id = "articleForm">
			<h2>글 내용 상세보기</h2>
			<section id = "basicInfoArea">
				제목 : ${article.board_subject }<br>
				첨부파일 :	<a href = "file_down.do?downFIle=${article.board_file}">${article.board_file }</a>							 
			</section>
			<section id = "articleContentArea">
				${article.board_content}
			</section>					
		</section>
		<section id = "commandList">
				<a href = "boardReplyForm.do?board_num=${article.board_num }&page=${page}">[답변]</a>		
				<a href ="boardModifyForm.do?board_num=${article.board_num}&page=${page}">[수정]</a>
				<a href = "boardDeleteForm.do?board_num=${article.board_num }&page=${ page}">[삭제]</a>
				<a href = "boardList.do?page=${page}">[목록]</a>
				&nbsp;&nbsp;
		</section>
</section>
</body>
</html>