<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


				<%--GET 원하는 파일을 요청 --%>
<c:forEach var="fileName" items="${fileNames}">
	<div style="display: flex; align-items: center; margin-bottom: 5px;">
				<%-- 서버에 자동으로 요청한다. 요청 매핑메소드를 찾아서 조건에 맞는 데이터를 보내준다. 그래서 항상 html형식으로 전달 --%>
		<img src="photodownload?photo=${fileName}" width="40px" height="40px" class="rounded-circle"/>
				<%--다운로드시 기본으로 된다. --%>					
		<a href="photodownload?photo=${fileName}">${fileName}</a>
	</div>
</c:forEach>
