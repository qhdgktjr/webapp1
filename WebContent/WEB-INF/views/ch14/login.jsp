<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Spring</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="<%=application.getContextPath() %>/resources/css/main.css">
	</head>
	
	<body>
		<div class="wrap">
			<%-- 공통헤더    header.jsp를 불러옴, jsp 엑션을 이용ㅡ 아래는 지시자 --%>
			<jsp:include page="/WEB-INF/views/include/header.jsp"/> 
			<%-- 내용 --%>
			<div class="mainCenter">
				<%-- 공통메뉴 --%>
				<jsp:include page="/WEB-INF/views/include/menu.jsp"></jsp:include>
				
				<div class="content">
					<div class="sector">
						<div class="alert alert-primary" role="alert">
							로그인
						</div>
						<!-- name에 접미사를 추가하자. onsubmit이랑 같으면 안된다. -->
						<form name="loginform" onsubmit="login()" method="post" style="width:200px;">
							<div class="form-group">
							    <label for="mid">아이디</label>
							    <input type="text" class="form-control" id="mid" name="mid">
							    <small id="errorMid" class="form-text text-danger"></small>
						  	</div>
							
							  
							<div class="form-group">
							    <label for="mpassword">비밀번호</label>
							     <input type="password" class="form-control" id="mpassword" name="mpassword">
							     <small id="errorMpassword" class="form-text text-danger"></small>
							</div>
							  <button class="btn btn-primary">로 그 인</button>
							  <a href="boardlist2" class="btn btn-primary">취 소</a>
						</form>
						<script>
						
							function login(){
								//form 태그의 기본 이동 기능을 취소
								event.preventDefault();
								//에러 초기화
								$("#errorMid").html("");
								$("#errorMpassword").html("");
								/* 입력값을 받기 */
								var validation = true;
								
								const mid = $("#mid").val();
								if(mid === ""){
									$("#errorMid").html("필수 입력 사항입니다.");
									validation = false;
								}
								
								const mpassword = $("#mpassword").val();
								if(mpassword === ""){
									$("#errorMpassword").html("필수 입력 사항입니다.");	
									validation = false;
								}
								
								if(!validation){
									return;
								}
								
								//AJAX 통신
								$.ajax({
									url: "login",
									method: "post",
									/* 넘겨야 할 데이터  변수값과 객체이름이 같다면 {mid: mid, mpassword: mpassword}*/
									data: {mid, mpassword},
									success: function(data){
										//data값 {"result":"success | wrongMid | wrongMpassword"}
										if(data.result === "success"){
											alert("로그인성공");
											location.href = "boardlist2";
										}else if(data.result === "wrongMid"){
											$("#errorMid").html("아이디가 존재하지 않습니다.");
										}else{
											$("#errorMpassword").html("비맞볼?");
										}
									}
									
								});
							}
						</script>
						
					</div>
					
					
					
					
				
					
					
					
				<!-- 섹터 구분 -->
				</div>
			</div>
		</div>
	</body>
</html>