<%-- 
    Document   : header
    Created on : 17.11.2015, 19:25:50
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
        <div id="admin-nav-menu">    
            <a href="/ApplicationCommision/app/"><fmt:message key="main"/></a>
            <a href="/ApplicationCommision/app/register/"><fmt:message key="register"/></a>
            <form method="post">
                <select id="language" name="language" onchange="submit()">
                    <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                    <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>Русский</option>
                </select>
            </form>
            <form method="post">
                <sarief:showOnlyTo roles="user">
                    <fmt:message key="greeting" /> ${not empty userRole ? user.name : "guest"}
                </sarief:showOnlyTo>
                <sarief:showOnlyTo roles="admin">
                    god mod on
                </sarief:showOnlyTo>
                <sarief:showOnlyTo roles="guest">
                    <c:if test="${loginFailed}"><fmt:message key="login.failed" /></c:if>
                    
                    <input type="text" name="authorizationLogin" />
                    <input type="password" name="authorizationPassword" />
                    <input type="submit" value="<fmt:message key="login" />" />
                </sarief:showOnlyTo>
            </form>

            <sarief:showOnlyTo roles="user,admin">
                <form method="post">
                    <input type="submit" name="authorizationLogout" value="<fmt:message key="logout" />" />
                </form>
            </sarief:showOnlyTo>

        </div>