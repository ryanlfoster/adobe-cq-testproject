<%@ include file="/apps/PersonRegistry/components/global.jspx" %>
<html>
<head>
    <meta charset="UTF-8">
    <cq:includeClientLib css="personregistry.all" />

    <link href="http://bootstrap-ru.com/assets/css/bootstrap.css" rel="stylesheet">
    <style>body{padding-top:60px; }</style>
    <link href="http://bootstrap-ru.com/assets/css/bootstrap-responsive.css" rel="stylesheet">
</head>
<body>
    <cq:include path="topnav" resourceType="PersonRegistry/components/topnav" />
    <div class="container">
        <div><cq:include path="par" resourceType="foundation/components/parsys"/></div>
    </div>

    <cq:includeClientLib js="personregistry.all" />
</body>
</html>