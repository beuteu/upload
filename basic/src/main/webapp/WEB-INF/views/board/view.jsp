<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	function goBack() {
		history.go(-1);
	}

	function getOriginalName(fileName) {

		/* if(checkImageType(fileName)){
			return;
		} */

		var idx = fileName.indexOf("_") + 1;
		return fileName.substr(idx);

	}

	function getImageLink(fileName) {

		/* if (!checkImageType(fileName)) {
			return;
		} */
		var front = fileName.substr(0, 12);
		var end = fileName.substr(14);

		return front + end;

	}

	
	$(document).ready(function() {
	
	var z = '${view.filepath }'
	var b = getImageLink(z);
	
	console.log(b)
	
	$("#realname").html("<img src='/displayFile?fileName=" + b + "'>");
	
	});
</script>



<style>
#realname IMG {border-radius: 10px; border: 1px solid #cccccc; padding: 5px;}


</style>



<!--container -->
<div id="container">
	<!--snb -->
	<div class="snb">서브메뉴 하나</div>
	<!--//snb -->
	<!--content -->
	<div id="content">

		<table class="type02">
			<caption>* 뷰페이지</caption>
			<colgroup>
				<col width="140px">
				<col width="/">
			</colgroup>
			<tr>
				<th scope="row">번호</th>
				<td>${view.bno }</td>
			</tr>
			<tr>
				<th scope="row">뷰포인트</th>
				<td>${view.viewPoint }</td>
			</tr>
			<tr>
				<th scope="row">작성일</th>
				<td>${view.rgsde }</td>
			</tr>
			<tr>
				<th scope="row">아이디</th>
				<td>${view.userId }</td>
			</tr>
			<tr>
				<th scope="row">타이틀</th>
				<td>${view.title }</td>
			</tr>
			<tr>
				<th scope="row" height="300px;">내용</th>
				<td>${view.content }<br>
				
				<div id="realname"></div>
<%-- 				<img src="/displayFile?fileName=${view.filepath}">
 --%>				</td>
			</tr>
		</table>

		<br> 
		<a	href="/board/list?page=${pageNum }&searchCondition=${search.searchCondition}&searchText=${search.searchText}" style="float: right">
			<button class="middle color blue button">목록</button>
		</a> <br> <br> <br>
	</div>
	<!--//content -->
</div>
<!--//container -->



