<%--
  Created by IntelliJ IDEA.
  User: Alexandr Kolymago
  Date: 14.11.2015
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" uri="tags" %>
<html>
<head>
    <title><t:translate key="main.driver.title"/></title>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<%@ include file="static/header_driver.jsp" %>
<main>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <%@ include file="trip/trips.jsp" %>
            </div>
        </div>
    </div>
</main>
</body>
</html>
