<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<form method="post" action="/board/register" enctype="multipart/form-data">
	<!--container -->
	<div id="container">
		<!--snb -->
		<div class="snb">서브메뉴 하나</div>
		<!--//snb -->
		<!--content -->
		<div id="content">
			<table class="type02">
				<caption>* 등록페이지</caption>
				<colgroup>
					<col width="140px">
					<col width="/">
				</colgroup>
				<tr>
					<th scope="row">제목</th>
					<td><input name="title" style="width: 800px; height: 38px;"></td>
				</tr>
				<tr>
					<th scope="row" height="200px;">내용</th>
					<td><textarea rows="20" cols="50" name="content" style="width: 100%; height: 400px;"></textarea></td>
				</tr>
				<tr>
					<th scope="row" height="32px;">파일</th>
					<td><input type=file name="file"></td>
				</tr>
			</table>
			<br> 
			<a	href="/board/list?page=${page}&searchCondition=${searchCondition}&searchText=${searchText}"	style="float: left">
				<button class="large   button" type="submit">목록으로</button>
			</a> <a href="/board/register" style="float: right">
				<button class="large color blue button" type="submit">등록하기</button>
			</a> <br> <br> <br>
		</div>
		<!--//content -->
	</div>
	<!--//container -->
</form>


