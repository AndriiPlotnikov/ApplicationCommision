<%-- 
    Document   : ShowReports
    Created on : 18.11.2015, 6:38:08
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

<jsp:include page="header.jsp" />
        <h1>Hello World! this is show reports of faculty page</h1>
        <jsp:include page="tables/ReportsTable.jsp" />
<jsp:include page="footer.jsp" />
     