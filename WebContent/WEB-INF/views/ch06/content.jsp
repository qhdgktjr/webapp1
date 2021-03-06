<%@ page contentType="text/html; charset=UTF-8"%>

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
						<h5>포워드 테스트</h5>
							<div>
								<a class="btn btn-info btn-sm" href="forward">서버 내부에서 이동</a>
							</div>
					</div>
					
					<div class="sector">
						<h5>리다이렉트 테스트</h5>
							<div>
								<a class="btn btn-info btn-sm" href="redirect">브라우저 재요청</a>
								<br/><br/>
								<a class="btn btn-info btn-sm" href="login">로그인 하기</a>
								<br/><br/>
								<a class="btn btn-info btn-sm" href="boardWrite">게시물 저장</a>
							</div>
					</div>
					
				<!-- 섹터 구분 -->
				</div>
			</div>
		</div>
	</body>
</html>