<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
						<h5>파일 업로드</h5>
							<div>
															<%--파일 업로드를 위해 반드시 추가해야한다 --%>
								<form method="post" enctype="multipart/form-data" action="photoload">
									<input type="text" name="uid" placeholder="아이디" /> <br/>
									<input type="text" name="uname" placeholder="이름" /> <br/>
									<input type="password" name="upassword" placeholder="패스워드" /> <br/>
									<input type="file" name="uphoto" multiple/> <br/>
									<input class="btn btn-primary btn-sm" type="submit" value="회원가입"/>
								</form>
							</div>
					</div>
					
					<div class="sector">
						<h5>파일 리스트</h5>
							<div>
								<script type="text/javascript">
									$(function() {
										$.ajax({
											url: "photolist",
											method: "get",
											success: function(data) {
												$("#photoList").html(data);
											}
										});
									});
								</script>
								<div id="photoList"></div>
							</div>
					</div>
					
					
					
				<!-- 섹터 구분 -->
				</div>
			</div>
		</div>
	</body>
</html>