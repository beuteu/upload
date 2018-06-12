<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
.thum IMG { border: 1px solid #cccccc; border-radius: 6px; width: 110px; }

</style>
<script type="text/javascript">
	
	boardList();
	function boardList() {
		var page = "";
		var paging = "";
		var pageMaker = [];

				$.ajax({
					url : 'http://localhost/board/listAjax.do' + "?page="+ page,
					dataType : 'json',
					type : 'GET',
					success : function(data) {
						var listBoard = ""
						var list = data.list
						var cnt = list[0].cnt;

						console.log("cnt" + cnt);
						pageMaker = data.pageMaker;

						$("#cnt").html("총개수: " + cnt + " 개");

						for (var i = 0; i < list.length; i++) {

							listBoard += "<table class='type03' style='border-top:none'><tr style='cursor: pointer;' class='viewnotice'  data-toggle='modal' data-target='.bd-example-modal-lg' data-bno='"+list[i].bno+"' data-title='"+list[i].title+"'  data-content='"+list[i].content+"'  data-userId='"+list[i].userId+"'  data-rgsde='"+list[i].rgsde+"' ><td class='TableCenter' style='width:140px; height:30px; font-size:14px'>"
									+ list[i].bno
									+ "</td><td class='TableCenter thum' style='width:120px;' >"
									+ "<img src='/displayFile?fileName=" + list[i].filepath + "' >"
									+ "</td><td class='TableCenter' style='width:*;' >"
									+ list[i].title
									+ "</td><td class='TableCenter' style='width:200px'>"
									+ list[i].userId
									+ "</td><td class='TableCenter' style='width:200px;'>"
									+ list[i].rgsde + "</td></tr></table>"
						}
						$("#listBoard").html(listBoard);

					}
				});
	}

	$(document).ready(function() {
		$(".viewnotice").click(function() {

			var bno = $(this).attr("data-bno");
			var title = $(this).attr("data-title");
			var userid = $(this).attr("data-userid");
			var content = $(this).attr("data-content");
			var rgsde = $(this).attr("data-rgsde");
			var viewpoint = $(this).attr("data-viewpoint");

			$(".bno").text(bno);
			$(".title").text(title);
			$(".content").text(content);
			$(".userid").text(userid);
			$(".rgsde").text(rgsde);

		});

		$("#register").on("click", function() {
			
          
			var files1 = document.getElementById("registerpimg").files[0];

			console.log("files1" + files1);

			 var formData = new FormData();
			 
			 formData.append("userid", "beuteu");
	         formData.append("title", $("#title").val());
	         formData.append("content", $("#content2").val());
	         formData.append("file", document.getElementById("registerpimg").files[0]);
			

			/* obj = {
				userid : "beuteu",
				title : $("#title").val(),
				content : $("#content2").val()
			}; */
 
			$.ajax({
				url : 'http://localhost/board/register.do',
				type : 'POST',
			    processData:false,
                contentType:false,
				data : formData,
				success : function() {
					alert("글이 등록 되었습니다");
					boardList();
					$("#title").val("");
					$("#content2").val("");

				}
			});

		});
	});
</script>
<!--container -->
<div id="container">
	<!--snb -->
	<div class="snb">서브메뉴 하나</div>
	<!--//snb -->
	<!--content -->
	<div id="content">

		<table class="type03">
			<caption>
				* AJAX게시판<span id="cnt" style="margin-left: 20px; font-size: 12px;"></span>
			</caption>
			<colgroup>
				<col width='140px'>
				<col width='/'>
				<col width='200px'>
				<col width='200px'>
			</colgroup>
			<tr class='TableCenter'>
				<th class='TableCenter'>번호</th>
				<th class='TableCenter'>제목</th>
				<th class='TableCenter'>작성자</th>
				<th class='TableCenter'>작성일</th>
			</tr>
		</table>

		<div id="listBoard"></div>

		<br> <a href="" style="float: right" data-toggle='modal' data-target='.bd-example-modal-lg2'>
			<button class="large color blue button" type="submit">등록하기</button>
		</a> <br> <br> <br>
	</div>
	<!--//content -->
</div>
<!--//container -->
<!-- Modal -->

<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content" style="padding: 20px">
			<table class="type03">
				<caption>* AJAX게시판 뷰페이지</caption>
				<colgroup>
					<col width='140px'>
					<col width='/'>
				</colgroup>
				<tr>
					<th class='TableCenter'>번호</th>
					<td class="bno TableLeft"></td>
				</tr>
				<tr>
					<th class='TableCenter'>제목</th>
					<td class='title TableLeft'></td>
				</tr>
				<tr>
					<th class='TableCenter'>작성자</th>
					<td class='userid TableLeft'></td>
				</tr>
				<tr>
					<th class='TableCenter'>작성일</th>
					<td class='rgsde TableLeft'></td>
				</tr>
				<tr>
					<th class='TableCenter'>내용</th>
					<td class='content TableLeft'></td>
				</tr>
			</table>
		</div>
	</div>
</div>

<div class="modal fade bd-example-modal-lg2" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content" style="padding: 20px">
			<table class="type03">
				<caption>* AJAX게시판 등록페이지</caption>
				<colgroup>
					<col width='140px'>
					<col width='/'>
				</colgroup>
				<tr>
					<th class='TableLeft'>제목</th>
					<td class='TableLeft'><input name="title" style="width: 600px; height: 38px;" id="title"></td>
				</tr>
				<tr>
					<th class='TableLeft'>내용</th>
					<td class=' TableLeft'><textarea rows="10" cols="50" name="content" style="width: 100%;" id="content2"> </textarea></td>
				</tr>
				<tr>
					<th class='TableLeft'>파일</th>
					<td class=' TableLeft'><input type="file" name="file" class="form-Co-Doc form-control" id="registerpimg"> </textarea></td>
				</tr>
			</table>

			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" id="register"	data-dismiss="modal">글등록</button>
			</div>
		</div>
	</div>
</div>