<%--
  Created by IntelliJ IDEA.
  User: Alexandr Kolymago
  Date: 15.01.2016
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="tags" prefix="t" %>
<html>
<head>
    <title><t:translate key="title.car"/></title>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="../static/header_driver.jsp"/>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <jsp:include page="carInfo.jsp"/>
            </div>
            <div class="col-md-6">
                <jsp:include page="updateCar.jsp"/>
            </div>
        </div>
    </div>
</body>
</html>
