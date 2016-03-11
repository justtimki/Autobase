<%--
  Created by IntelliJ IDEA.
  User: Alexandr Kolymago
  Date: 25.11.2015
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="tags" %>
<html>
<head>
    <title><t:translate key="title.complete.trip"/></title>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../static/header_driver.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="panel panel-primary">
                <div class="panel-heading"><t:translate key="message.trip.complete"/></div>
                <div class="panel-body">
                    <form method="post" action="${pageContext.servletContext.contextPath}/controller">
                        <input type="hidden" name="command" value="complete_trip">

                        <p><t:translate key="trip.complete.input"/> <input type="text" name="tripName"></p>
                        <button class="btn btn-default" type="submit"><t:translate key="trip.complete.button"/></button>
                    </form>
                </div>
            </div>
            <p style="color: darkred;">${tripNotComplete}</p>
            <p style="color: green;">${tripComplete}</p>
        </div>
    </div>
</div>
</body>
</html>