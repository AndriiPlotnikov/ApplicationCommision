<%-- 
    Document   : AuthrorizeAdmin
    Created on : 19.11.2015, 20:14:34
    Author     : andre
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sarief" uri="/WEB-INF/tlds/sarief_library.tld" %>

<c:set var="language" value="${not empty param.language ? param.language : 
                               not empty language ? language : pageContext.request.locale}"
       scope="session" />

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="view.i18n.ApplicationMessages" />


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body> 

        <h1>Hello World! this is authorize admin page</h1>
        <form method="post">
            <sarief:showOnlyTo roles="guest">
                <input type="text" name="authorizationAdminLogin" />
                <input type="password" name="authorizationAdminPassword" />
                <input type="submit" value="<fmt:message key="login" />" />
            </sarief:showOnlyTo>
        </form>
        <jsp:include page="footer.jsp" />