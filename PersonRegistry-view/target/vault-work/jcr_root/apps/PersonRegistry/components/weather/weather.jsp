<%@ include file="/apps/PersonRegistry/components/global.jspx" %>
<%@ include file="/apps/PersonRegistry/components/global.jspx" %>
<%@ taglib prefix="pr" uri="http://person_registry.yarish.spb.ru/taglibs/PersonRegistry-taglib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<pr:getWeather zipCode = "${properties.zipcode}" />
<div id="weather">
    <h4>The city is ${weather.city}</h4>
    <h4>The state is ${weather.state}</h4>
    <h4>The description is ${weather.description}</h4>
    <h4>The wind is ${weather.wind}</h4>
    <h4>The current temp is ${weather.temperature}</h4>
    <h4>The current Humidity is ${weather.relativeHumidity}</h4>
</div>