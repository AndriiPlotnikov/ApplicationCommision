<%-- 
    Document   : RegisterForm
    Created on : 17.11.2015, 21:09:42
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

${errorMessage}

<form action="" method="post">
    login: <input type="text" name="login" /><br>
    password: <input type="password" name="password" /><br>
    confirm password: <input type="password" name="confirm" /><br>
    <input type="submit" />
</form>
