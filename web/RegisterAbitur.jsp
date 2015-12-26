<%-- 
    Document   : RegisterAbitur
    Created on : 17.11.2015, 19:13:20
    Author     : andre
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : 
                               not empty language ? language : pageContext.request.locale}"
       scope="session" />

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="view.i18n.ApplicationMessages" />

<jsp:include page="header.jsp" />
        <h1>Hello World! This is Register Abitur Page! I work but I wanna be beatiful! *_*</h1>
        <jsp:include page="forms/RegisterForm.jsp"></jsp:include>
<jsp:include page="footer.jsp" />