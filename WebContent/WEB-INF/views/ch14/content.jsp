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
						<h5>연결 테스트</h5>
							<div>
								<a class="btn btn-success btn-sm" href="javascript:conntest()">연결 테스트</a>
								<script>
									const conntest = function() {
										$.ajax({
											url: "conntest",
											/*지워도 post방식으로 된다. 
											method: "post", */
											success: function(data){
												//html 조각, JSON일 수 있다.
															//반드시 html 조각이어야 한다.
												$("#result1").html(data);
											}
											
										});
										
										
										/* 방식이 두 가지
										$.ajax({
											  url: "conntest",
											}).done(function(data) {
											
											}); 
										*/
									};
								
								</script>
								<div id="result1"></div>
							</div>
					</div>
					
					
					<div class="sector">
						<h5>JSON 응답 받기</h5>
							<div>
								<a class="btn btn-success btn-sm" href="javascript:jsonresponse1()">객체{} 받기</a>
								<script>
									function jsonresponse1() {
										$.ajax({
											url: "jsonresponse1",
											method: "get",
											success: function(data){
												console.log(data);
												//html 내용을 추가, 바꿔치기
												$("#name").html(data.name);
												$("#age").html(data.age);
												$("#carkind").html(data.car.kind);
												$("#carcolor").html(data.car.color);
												
												for(var h of data.hobby) {
													$("#hobby").append(h + ",");
												}
											}
										});
									};
									
								</script>
								<div id="result2">
									<div id="name"></div>
									<div id="age"></div>
									<div id="carkind"></div>
									<div id="carcolor"></div>
									<div id="hobby"></div>
								</div>
								
								<a class="btn btn-success btn-sm" href="javascript:jsonresponse2()">배열[] 받기</a>
								<script>
									function jsonresponse2(){
										$.ajax({
											url:"jsonresponse2",
											success: function(data){
												console.log(data);
												for(var i = 0; i<data.length; i++){
													var	board = data[i];
													$("#result3 tbody").append("<tr>");
													$("#result3 tbody").append("<td>"+ board.bno+"</td>");
													$("#result3 tbody").append("<td>"+ board.btitle+"</td>");
													$("#result3 tbody").append("<td>"+ board.bwriter+"</td>");
													$("#result3 tbody").append("</tr>");
													
												}
												
											}
											
										});
									}
								</script>
								<div id="result3">
								<!-- 부트스트랩 table 선이 생성 -->
									<table class="table">
										<thead>
											<tr>
												<td>번호</td>
												<td>제목</td>
												<td>글쓴이</td>
											</tr>
										</thead>
										<tbody>
										</tbody>
										
									</table>
								</div>
							</div>
					</div>
					
					<div class="sector">
						<h5>PK로 검색하기</h5>
							<div>
								
								<a class="btn btn-success btn-sm" href="javascript:getEmployee(100)">사번 정보 가져오기</a>
								<a class="btn btn-success btn-sm" href="javascript:getEmployee(101)">사번 정보 가져오기</a>
							
								<script>
								function getEmployee(eid){
									$.ajax({
										url: "employee",
										data: {employee_id: eid},
										success: function(data){
											$("#eno").html(data.employee_id);
											$("#first").html(data.first_name);
											$("#last").html(data.last_name);
										}
									})
								}
								</script>
							
								<div id="result4">
									사번: <span id="eno"></span><br/>
									이름: <span id="first"></span><br/>
									성: <span id="last"></span><br/>
								</div>
							</div>
					</div>
					
					
					
				
					
					
					
				<!-- 섹터 구분 -->
				</div>
			</div>
		</div>
	</body>
</html>