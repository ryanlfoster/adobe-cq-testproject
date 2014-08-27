<%@ include file="/apps/PersonRegistry/components/global.jspx" %>
<%@ taglib prefix="pr" uri="http://person_registry.yarish.spb.ru/taglibs/PersonRegistry-taglib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="cq" uri="http://www.day.com/taglibs/cq/1.0" %>
<cq:setContentBundle source="page"/>

<br /><br />
<pr:getPersons resolver="${resourceResolver}" />
<table id="table" class="table table-striped">
    <tr><th>#</th><th><fmt:message key="first.name" /></th><th><fmt:message key="last.name" /></th><th><fmt:message key="description" /></th><th>-</th></tr>
    <c:forEach items="${persons}" var="item" varStatus="loop">
        <tr>
            <td>${loop.index+1}</td>
            <td>${item.name}</td>
            <td>${item.lastName}</td>
            <td>${item.description}</td>
            <td class="removable" id="del${item.id}">X</td>
        </tr>
    </c:forEach>
</table>