<%--
  Created by IntelliJ IDEA.
  User: Alexandr Kolymago
  Date: 17.01.2016
  Time: 0:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><t:translate key="link.create.trip"/></title>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-2">
        </div>
        <div class="col-md-8">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><t:translate key="header.new.trip"/></h3>
                </div>
                <form action="${pageContext.servletContext.contextPath}/controller" method="post">
                    <div class="panel-body">
                        <p><b><t:translate key="trip.complete.input"/></b> ${tripName} </p>

                        <p><b><t:translate key="trip.list.drivers"/> </b></p>

                        <input type="hidden" name="command" value="create_trip">
                        <table class="table">
                            <tr>
                                <td><t:translate key="message.driver.login"/></td>
                                <td><t:translate key="placeholder.car.name"/></td>
                                <td></td>
                            </tr>
                            <c:forEach items="${drivers}" var="driver">
                                <tr>
                                    <td>${driver.accountName}</td>
                                    <td>${driver.carName}</td>
                                    <td><input type="radio" name="chosenDriver" value='${driver.accountName}'></td>
                                    <input type="hidden" name="tripName" value="${tripName}"/>
                                </tr>
                            </c:forEach>
                        </table>
                        <input type="submit" class="btn btn-default" value="<t:translate key="trip.complete.button"/>">
                    </div>
                </form>
            </div>
        </div>
        <div class="col-md-2">
        </div>
    </div>
</div>
</body>
</html>
