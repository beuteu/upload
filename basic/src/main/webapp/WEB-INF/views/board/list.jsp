<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	$(document).ready(function() {
		// 체크 되어 있는 값 추출

		$("#bno").click(function() {

			var chked_val = "";
			$(":checkbox[name='nn']:checked").each(function(pi, po) {
				chked_val += "," + po.value;
			});
			
			if (chked_val != "") {
				chked_val = chked_val.substring(1);
			}

			if (chked_val == null || chked_val == "") {

				alert("1개이상 선택하셔야 합니다.");
				return true;
			}

			$('.chnno').val(chked_val);
			$('#listFrm').attr('action', '/board/delete').submit();
			alert(chked_val + "번이 삭제 되었습니다.")
			return true;
		});

		$("#search").click(function() {
			$('#listFrm').attr('action', '/board/list').submit();
			return true;
		});
		$("#searchAll").click(function() {

			$("#searchText").val("");
			$('#listFrm').attr('action', '/board/list').submit();
			return true;
		});
		
		
		$("#open").click(function() {
			alert("ddd");

			$.cookie("visits", 10);
			return true;
		});
		
		
		var a  = $.cookie("visits");
		
		$("#dd").html(a);
		
		$("#close").click(function() {
		 	
			alert("ddd");
		 	alert($.removeCookie("visits"));
			$.removeCookie("visits");
			return true;
		});
		
		

	});
</script>
<!--container -->
<style>
.thum IMG { border: 1px solid #cccccc; border-radius: 6px; padding: 2px; width: 110px; }
</style>
<div id="open">열기</div>
<div id="dd"></div>
<div id="close">닫기</div>

ㅏㅓㅣㅏ
<div id="container">
	<!--snb -->
	<div class="snb">서브메뉴 하나</div>
	<!--//snb -->
	<!--content -->
	<div id="content">
		<form id='listFrm'>
			<input type="hidden" name="bno" class="chnno">
			<table class="type03">
				<caption>
					* 리스트 게시판
					<span style="font-size: 13px; margin-left: 20px; letter-spacing: 0px; font-weight: normal;">
					<span style="font-size: 14px; color: #337ab7;">게시글수 : ${total}개</span></span>
					<div style="float: right; width: 400px; text-align: right">
						<select name="searchCondition" style="height: 30px; font-size: 12px">
							<option value="t" <c:out value="${search.searchCondition == 't'?'selected':''}"/>>제목</option>
						</select> <input type="text" name="searchText" value="${search.searchText}"	id="searchText">
						<button class="middle color blue button" id="search">검색</button>
						<button class="middle button" id="searchAll">전체보기</button>
					</div>
				</caption>
				<colgroup>
					<col width="80px">
					<col width="100px">
					<col width="100px">
					<col width="/">
					<col width="200px">
					<col width="200px">
					<col width="100px">
				</colgroup>
				<tr>
					<th class='TableCenter'><button class="small button" id="bno">삭제</button></th>
					<th class='TableCenter'>번호</th>
					<th class='TableCenter'>이미지</th>
					<th class='TableCenter'>제목</th>
					<th class='TableCenter'>작성자</th>
					<th class='TableCenter'>등록일</th>
					<th class='TableCenter'>히트수</th>
				</tr>
				<c:if test="${not empty list}">
					<c:forEach items="${list}" var="lee">
						<tr>
							<td class='TableCenter'><input type="checkbox" name="nn" value="${lee.bno }" /></td>
							<td class='TableCenter'>${lee.bno}</td>
							<td class='TableCenter thum'><a href="/board/view?bno=${lee.bno }&page=${pageNum }&searchCondition=${searchCondition}&searchText=${searchText}"><img src="/displayFile?fileName=${lee.filepath}"></a></td>
							<td class="TableLeft"><a href="/board/view?bno=${lee.bno }&page=${pageNum }&searchCondition=${searchCondition}&searchText=${searchText}">${lee.title}</a></td>
							<td class='TableCenter'>${lee.userId}</td>
							<td class='TableCenter'>${lee.rgsde}</td>
							<td class='TableCenter'>${lee.viewPoint}</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty list}">
					<tr>
						<td class='TableCenter' colspan="7">데이터가 없습니다.</td>
					</tr>
				</c:if>
			</table>
		</form>

		<ul class="pagination">
			<c:if test="${page.prev}">
				<li><a href="/board/list?page=${page.start-1 }&searchCondition=${search.searchCondition}&searchText=${search.searchText}">◀</a></li>
			</c:if>
			<c:forEach var='i' begin='${page.start}' end='${page.end}'>
				<li class='page-item <c:out value="${i == page.page?'active':''}"/>'>
					<a class="page-link" href="/board/list?page=${i}&searchCondition=${search.searchCondition}&searchText=${search.searchText}">${i}</a>
				</li>
			</c:forEach>
			<c:if test="${page.next}">
				<li><a href="/board/list?page=${page.end+1}&searchCondition=${search.searchCondition}&searchText=${search.searchText}">▶</a></li>
			</c:if>
		</ul>
		<br> <a href="/board/register" style="float: right;">
			<button class="middle color blue button">등록하기</button>
		</a><br>
		<br>
		<br>
	</div>
	<!--//content -->
</div>
<!--//container -->