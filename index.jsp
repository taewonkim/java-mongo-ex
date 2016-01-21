<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" session="true"%>

<%
	String user_id = (String) session.getAttribute("user_id");
	if(user_id == null) {
%>
<jsp:include page="signin_view.jsp" flush="true" />
<%
	} else {
%>
<%=user_id%><br />
<a href="signout">로그아웃</a>
<%
	}
%>