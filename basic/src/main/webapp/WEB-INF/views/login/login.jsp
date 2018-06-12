<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <c:set var="context" value="<%=request.getContextPath()%>"></c:set>
<form id="form-login">
	<div style="height: 100px;">
		<div>
		</div>
	</div>
	<div>
		<div>
			<div style="width: 280px;height: 100px;margin: 0 auto;font-size: 40px;font-weight: bold;">관리자 로그인</div>
		</div>
	</div><br /><br />
    
	<div style=" width: 500px;height: 135px;margin: 0 auto;	display: grid;grid-gap: 5px;background-color: #7b7b82;padding: 5px;">
		<div style="grid-column: 1 / span 2;grid-row: 1;" >
			<input style="width:100%; height:48%;" id="inp-login-customId" name="inp-login-customId" class="int-login" type="text" value="admin" />				
		</div>
 		<div style="grid-column: 3;grid-row: 1 / span 2;">
 			<button id="btn-login" style="width:  100%;height: 100%;margin: 0 auto;text-align: center;" >로그인</button>
 		</div>
 		<div style="grid-column: 1 / span 2;grid-row: 2;">
 			<input style="width:100%; height:48%;" id="inp-login-customPass" name="inp-login-customPass" class="int-login" type="text" value="admin" />			
 		</div>  	
	</div>
	<div style="height: 100px;">	
	</div>
	<input type="hidden" name="nowPage" value="1" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="blockSize" value="5" />
	<input type="hidden" name="pageSize" value="5" />
</form>	
	<script>
		
		
	</script>		

