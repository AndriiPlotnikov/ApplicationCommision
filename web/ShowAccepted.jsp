<%-- 
    Document   : ShowEntries
    Created on : 17.11.2015, 19:15:01
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
        <h1>Hello World! this is show accepted page</h1>
        <jsp:include page="tables/AcceptedTable.jsp" />
<jsp:include page="footer.jsp" />
        
