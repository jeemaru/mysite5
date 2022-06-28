<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- css -->
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<!-- js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/css/js/jquery/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">

		<!-- //header -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //header -->
		
		<!-- //nav -->
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		<!-- //nav -->
	
		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
				
				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form action="${pageContext.request.contextPath}/api/guestbook/add" method="get">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></td>
									<td><input id="input-uname" type="text" name="name" value=""></td>
									<th><label class="form-text" for="input-pass">패스워드</label></td>
									<td><input id="input-pass"type="password" name="pass" value=""></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button id="btnSubmit" type="submit">등록</button></td>
								</tr>
							</tbody>
							
						</table>
						<!-- //guestWrite -->
						<input type="hidden" name="action" value="add">
						
					</form>	
					
					
						<!-- 리스트 영역 -->
						<div id="listArea">
						
							
						
						</div>
						<!-- 리스트 영역 -->
					
					
					
					<table class="guestRead">
						<colgroup>
							<col style="width: 10%;">
							<col style="width: 40%;">
							<col style="width: 40%;">
							<col style="width: 10%;">
						</colgroup>
						<tr>
							<td>1234555</td>
							<td>이정재</td>
							<td>2020-03-03 12:12:12</td>
							<td><a href="">[삭제]</a></td>
						</tr>
						<tr>
							<td colspan=4 class="text-left">방명록 글입니다. 방명록 글입니다.</td>
						</tr>
					</table>
					<!-- //guestRead -->
					
					<table class="guestRead">
						<colgroup>
								<col style="width: 10%;">
								<col style="width: 40%;">
								<col style="width: 40%;">
								<col style="width: 10%;">
						</colgroup>
						<tr>
							<td>1234555</td>
							<td>이정재</td>
							<td>2020-03-03 12:12:12</td>
							<td><a href="">[삭제]</a></td>
						</tr>
						<tr>
							<td colspan=4 class="text-left">방명록 글입니다. 방명록 글입니다.</td>
						</tr>
					</table>	
					<!-- //guestRead -->
					
				</div>
				<!-- //guestbook -->
			
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<!-- //footer -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

<script type="text/javascript">
		
/* 준비가 끝나면 */
$(document).ready(function(){
	/* 리스트 요청+그리기 */
	fetchList()
});
/* 저장버튼을 클릭했을때 */
$("#btnSubmit").on("click", function(){
	console.log("저장버튼 클릭");
	
	//데이터수집
	var name = $("[name='name']").val();
	var password = $("[name='password']").val();
	var content = $("[name=content]").val();
	
	//데이터 객체로 묶기
	var guestVo = {
		name: name,
		password: password,
		content: content
	};
	
	$.ajax({
		
		/* url : "${pageContext.request.contextPath }/api/guestbook/add?name="+name+"&password="+password+"&content="+content,	 */
		url : "${pageContext.request.contextPath }/api/guestbook/add",		
		type : "post",
		//contentType : "application/json",
		data : guestVo,   //파라미터 정리된다
		dataType : "json",
		success : function(gVo){
			/* 1개데이터 리스트 추가(그리기)하기 */
			render(gVo, "up");
			
			/* 입력폼 초기화 */
			$("[name='name']").val("");
			$("[name='password']").val("");
			$("[name='content']").val("");
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
});
/* 리스트 요청 */
function fetchList(){
	$.ajax({
		
		url : "${pageContext.request.contextPath }/api/guestbook/list",		
		type : "post",
		//contentType : "application/json",
		//data : {name: ”홍길동"},
		
		dataType : "json",
		success : function(guestbookList){
			//화면 data + html 그린다
			for(var i=0; i<guestbookList.length; i++){
				render(guestbookList[i], "down");  //vo --> 화면에 그린다.
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
}
/* 리스트 그리기 1개씩*/
function render(guestbookVo, opt){
	console.log("render()");
	
	var str = '';
	str += '<table class="guestRead">' ;
	str += '    <colgroup>' ;
	str += '        <col style="width: 10%;">' ;
	str += '        <col style="width: 40%;">' ;
	str += '        <col style="width: 40%;">' ;
	str += '        <col style="width: 10%;">' ;
	str += '    </colgroup>' ;
	str += '    <tr>' ;
	str += '        <td>'+guestbookVo.no+'</td>' ;
	str += '        <td>'+guestbookVo.name+'</td>' ;
	str += '        <td>'+guestbookVo.regDate+'</td>' ;
	str += '        <td><a href="">[삭제]</a></td>' ;
	str += '    </tr>' ;
	str += '    <tr>' ;
	str += '        <td colspan=4 class="text-left">'+guestbookVo.content+'</td>' ;
	str += '    </tr>' ;
	str += '</table>' ;
	
	if(opt == "down"){
		$("#listArea").append(str);	
	
	}else if(opt == "up"){
		$("#listArea").prepend(str);
	
	}else {
		console.log("opt오류");
	}
	
	
	
}
	
	
</script>

</html>