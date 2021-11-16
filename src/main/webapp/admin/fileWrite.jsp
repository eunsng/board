<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>공지사항 등록</title>
	
	<link rel="stylesheet" href = "/css/admin_main.css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
  	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  	<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
  	
	<script>
	  $( function() {
		
		  $("#btn_save").click(function(){
			  
			  if( $.trim( $("#title").val() ) == "" ){
				  alert("제목을 입력해주세요.");
				  $("#title").focus();
				  return false;
			  }
			  
			  if( $.trim( $("#content").val() ) == "" ){
				  alert("내용을 입력해주세요.");
				  $("#content").focus();
				  return false;
			  }
			  
			  
			  var formdata = new FormData(document.getElementById('frm') );
			  $.ajax({
				  type : "POST",
				  url  : "fileboardWriteSave.do",
				  data : formdata,
				  
				  processData : false,
				  contentType : false,
				  
				  datatype : "json",
				  success  : function(data){
					   if(data == "ok"){
						   alert("저장 완료");
					   }else{
						   alert("실패");
					   }
				  } ,
					  
				  error    : function(){
					  alert("오류발생");
				  			}  
			  });
		  });
	  });
	</script>
</head>

<body>

<div class="div1">

	<div class="div_top">
		<h2>관리자모드</h2>
	</div>
	
	<div class="div2">
		<%@ include file="../include/left_menu.jsp" %>
	</div>
	
	<div class="div3">
	
		<div style="position:relative; left:20px; top:30px; margin-bottom:10px;">
		</div>

		<div style="position:relative; left:20px; top:30px; margin-bottom:5px;">
			<span style ="font-size :20px; font-family:'맑은 고딕'; font-weight:bold; ">자료 게시판 등록</span>
		</div>

		<div style="position:relative; left:20px; top:30px;">
	
<form id ="frm" enctype = "multipart/form-data">
	<table style ="width:600px;">
		<tr>
			<th>제목</th>
			<td>
			<input type ="text" name ="title" id ="title">
			</td>
		</tr>
		<tr>
			<th>암호</th>
			<td>
			<input type ="password" name ="pass" id ="pass">
			</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>
			<input type ="text" name ="name" id ="name" value ="관리자" >
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
			<textarea name ="content" id="content" style ="width:98%; height:150px;"></textarea>
			</td>
		</tr>
		<tr>
			<th>파일</th>
			<td>
			<input type ="file" name="file1" id ="file1" style ="width:50%;"><br>
			<input type ="file" name="file2" id ="file2" style ="width:50%;">
			</td>
		</tr>
	</table>
	
	<div style ="width:600px; text-align:center; margin-top:10px;">
		<button type ="button" id="btn_save">저장</button>
		<button type ="reset">취소</button>
	</div>
	
</form>	
		</div>
	</div>
</div>
</body>
</html>











