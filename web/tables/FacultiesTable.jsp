<%-- 
    Document   : testTable
    Created on : 18.11.2015, 5:05:59
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


<table>
    <c:forEach var="faculty" items="${Faculties}" >
        <tr>
            <td>
                ${faculty.name}
            </td>

            <td>
                <a href="/ApplicationCommision/app/faculty/?faculty=${faculty.id}"><fmt:message key="display"/></a>
            </td>
        </tr>
    </c:forEach>
</table>
