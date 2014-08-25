<%@include file="/libs/wcm/global.jsp"%>
<%@ include file="/apps/PersonRegistry/components/global.jspx" %>
<%@ taglib prefix="pr" uri="http://person_registry.yarish.spb.ru/taglibs/PersonRegistry-taglib" %>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <a href="../en/add.html">en</a>&nbsp;&nbsp;&nbsp;<a href="../de/add.html">de</a>
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand">PersonRegistry</a>
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <pr:topNav />
                </ul>
            </div>
        </div>
    </div>
</div>


