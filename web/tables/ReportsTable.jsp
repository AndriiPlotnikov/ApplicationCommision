<%-- 
    Document   : testTable
    Created on : 18.11.2015, 5:05:59
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

<table>
    <c:forEach var="report" items="${Reports}" >
        <tr>
            <td>
                ${report.year}
            </td>
            <td>
                ${report.places}
            </td>
            <td>
                ${report.minScore}
            </td>
            <td>
                ${report.maxScore}
            </td>

            <td>
                ${report.open}
            </td>
            <sarief:showOnlyTo roles="user">
                <td>
                    <a href="/ApplicationCommision/app/apply/?report=${report.id}"><fmt:message key="apply"/></a>
                </td>
            </sarief:showOnlyTo>


            <sarief:showOnlyTo roles="admin">
                <td>
                    <a href="/ApplicationCommision/app/admin/entries/?report=${report.id}"><fmt:message key="show.entries"/></a>
                </td>
            </sarief:showOnlyTo>
            <td>
                <a href="/ApplicationCommision/app/faculty/report/?report=${report.id}"><fmt:message key="show.accepted"/></a>
            </td>

        </tr>
    </c:forEach>
</table>
