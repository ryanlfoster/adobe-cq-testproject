<%@ include file="/apps/PersonRegistry/components/global.jspx" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="cq" uri="http://www.day.com/taglibs/cq/1.0" %>
<cq:setContentBundle source="page"/>

<form name="signup" id="signup">
            <label for="first"><fmt:message key="first.name" /> (*):</label>
            <input type="first" id="first" name="first" value="" />
            <label for="last"><fmt:message key="last.name" /> (*):</label>
            <input type="last" id="last" name="last" value="" />
            <label for="description"><fmt:message key="description" /> (*):</label>
            <input type="description" id="description" name="description" value="" />
            <input type="button" value="<fmt:message key="add.person" />" id="submit">
</form>
<p id="status"></p>