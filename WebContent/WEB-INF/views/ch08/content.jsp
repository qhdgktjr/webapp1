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
						<h5>HttpSession 객체를 이용한 스칼라 값 전달</h5>
							<div>
								<a class="btn btn-info btn-sm" href="method1">전달</a>
							</div>
					</div>
					
					<div class="sector">
						<h5>HttpSession 객체를 이용한 객체 전달</h5>
							<div>
								<a class="btn btn-info btn-sm" href="method2">전달</a>
							</div>
					</div>
					
					<div class="sector">
						<h5>HttpSession 객체를 이용한 컬렌션 전달</h5>
							<div>
								<a class="btn btn-info btn-sm" href="method3">전달</a>
							</div>
					</div>
					<div class="sector">
						<h5>HttpSession 객체를 이용한 로그인</h5>
							<div>
									<%-- if문 else가 없다 --%>
								<c:if test="${loginStatus == null}">
									<div>
										<form method="post" action="login">
											<input type="text" name="uid" placeholder="아이디"/> <br/>
											<input type="password" name="upassword" placeholder="패스워드"/> <br/>
											<button class="btn btn-success btn-sm">로그인</button>
										</form>
									</div>
								</c:if>
								
								<c:if test="${loginStatus != null}">
									<div>
										<a class="btn btn-danger btn-sm" href="logout">로그아웃</a>
									</div>
								</c:if>
								
							</div>
					</div>
					
					<c:if test="${loginStatus != null }">
						<div class="sector">
							<h5>게시물 입력</h5>
								<div>
									<form method="post" action="boardWrite">
										<input type="text" name="title" placeholder="제목"/> <br/>
										<textarea rows="3" cols="100" name="content" placeholder="내용"></textarea><br/>
										<button class="btn btn-info btn-sm">저장</button>
									</form>
								
								</div>
						</div>
					</c:if>
					
					
				<!-- 섹터 구분 -->
				</div>
			</div>
		</div>
	</body>
</html>