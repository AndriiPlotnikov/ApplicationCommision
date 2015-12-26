<%-- 
    Document   : EntriesTable
    Created on : 20.11.2015, 18:01:07
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

table of accepted
<table>
    <c:forEach var="entry" items="${accepted}" >
        <tr>
            
            <td>
                ${entry.abitur.name}
            </td>
            <td>
                ${entry.scoreSum}
            </td>
            <td>
                ${entry.accepted}
            </td>


        </tr>
    </c:forEach>
</table>
