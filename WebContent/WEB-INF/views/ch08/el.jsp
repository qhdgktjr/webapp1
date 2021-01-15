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
						<h5>EL 스칼라값(숫자, 문자열, 논리값) 읽기</h5>
							<div>
								<div>이름 : ${name}</div>
								<div>나이 : ${age}</div>
								<div>직업 : ${job}</div>
							</div>
					</div>
					
					<div class="sector">
						<h5>EL 객체의 데이터 읽기</h5>
							<div>		<!-- getter를 호출하는 코드, getNo()하면 안된다. -->
								<div>번호 : ${board1.no}</div>
								<div>제목 : ${board1.title}</div>
								<div>내용 : ${board1.content}</div>
								<div>글쓴이 : ${board1.writer}</div>
								<div>날짜 : ${board1.date}</div>
							</div>
					</div>
					
					<div class="sector">
						<h5>컬렉션 이용하기</h5>
							<div>
								<table style="width: auto;" class="table table-sm table-bordered">
									<tr style="background-color:orange; color:black;">
										<th>번호</th>
										<th>제목</th>
										<th>내용</th>
										<th>글쓴이</th>
										<th>날짜</th>
									</tr>
										<!-- 접두사:, html제공이 아니다  -->	
										<c:forEach var="board" items="${boardList}">
											<tr>
												<td>${board.no}</td>
												<td>${board.title}</td>
												<td>${board.content}</td>
												<td>${board.writer}</td>
												<td>
												<fmt:formatDate value="${board.date}" pattern="yyyy-MM-dd"/>
												</td>
											</tr>
										</c:forEach>
								</table>
							</div>
					</div>
					
					
				<!-- 섹터 구분 -->
				</div>
			</div>
		</div>
	</body>
</html>