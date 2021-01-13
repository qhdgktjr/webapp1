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
						<h5>요청 매핑</h5>
							<div>
								GET 방식 : <a class="btn btn-info btn-sm" href="getMethod">request1</a>
								<br/><br/>
								POST 방식 : 
								<form method="post" action="postMethod" style="display:inline-block;">
									<button class="btn btn-info btn-sm">request1</button>
								</form>
							</div>
					</div>
					
					
					<div class="sector">
						<h5>요청 방식 별 회원 가입 처리</h5>
							<div>
								GET 방식 : <a class="btn btn-info btn-sm" href="join">회원가입</a>
								<!-- /webapp1/ch02/join절대 경로
										%=application.getContextPath()% -->
							</div>
					</div>
					
					<div class="sector">
						<h5>비동기 처리</h5>
							<div>
								GET 방식 : <a class="btn btn-info btn-sm" href="javascript:fun1()">회원가입</a>
								<script type="text/javascript">
									function fun1(){
										/* 세팅값 설정 */
										$.ajax({
											/* 기본은 GET방식 */
											url : "joinAsync",
											method : "get",
											/* 응답을 data로 받고 아래 코드를 실행 */
											success : function(data) {
												$("#joinForm").html(data);
											}
										});
									};	
								</script>
								<div id="joinForm" style="margin-top: 10px;"></div>
							</div>
					</div>
				
				
				
				<!-- 섹터 구분 -->
				</div>
			</div>
		</div>
	</body>
</html>