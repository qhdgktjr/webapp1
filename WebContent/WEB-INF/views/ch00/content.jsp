<%--jsp 주석방식 
	[page 지시자]
		language: .jsp 파일에서 사용되는 프로그래밍 언어(생략 가능)
		pageEncoding: .jsp 파일로 저장할 때 사용되는 문자셋(생략가능)
		contentType: .jsp가 실행후 만들어지는 결과물의 종류
		MIME 타입을 지정
			text/html: 문자 내용으로 html 태그로 구성된 것
			application/json: 문자 내용으로 json 표기로 구성된 것
			image/jpeg: 이미지 내용으로 jkpeg으로 인코딩한 것
			image/phg: 이미지 내으로 pnp로 인코딩한 걸
		
		문자셋 지정
			브라우저로 전달할때 사용되는 문자셋(문자 내용은 반드시 기술을 해야함)
	
	--%>
										<%-- ; 필수     문자셋 없애면  알파벳만 가능--%>
	
	<%-- import 속성 --%>
	<%@ page import="java.util.*, java.text.*" %>
 
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
												<!-- contextroot값을 리턴 -->
		<link rel="stylesheet" href="<%=application.getContextPath() %>/resources/css/main.css">
	</head>
	
	<body>
		<div class="wrap">
			<%-- 공통헤더    header.jsp를 불러옴, jsp 엑션을 이용ㅡ 아래는 지시자 --%>
													<%-- 실행 결과물을 보여줌 --%>
			<jsp:include page="/WEB-INF/views/include/header.jsp"/> 
													<%-- 복사본을 보여줌, 파일에 <%@%>를 사용 못한다. --%>
			<%-- <%@ include file="/WEB-INF/views/include/header.jsp" %> --%>
			
			<%-- 내용 --%>
			<div class="mainCenter">
				<%-- 공통메뉴 --%>
				<jsp:include page="/WEB-INF/views/include/menu.jsp"></jsp:include>
					<div class="content">
						<div class="sector">
								<h5>스크립트릿/표현식</h5>
								<div>
									<%-- Scriptlet(스크립트릿) --%>
									<%
									Date now = new Date();
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
									String strNow = sdf.format(now);
									%>
									<%-- Expressions(표현식) --%>
									오늘 날짜: <%=strNow%>
								</div>
						</div>
					
						<div class="sector">
							<h5>스크립트릿/표현식</h5>
							<div>
								<%for(int i = 1; i<=6; i++) {%>
									<h<%=i%>>Hello JSP</h<%=i%>>
								<%}%>
							</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>