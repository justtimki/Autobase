<%--
  Created by IntelliJ IDEA.
  User: Alexandr Kolymago
  Date: 16.01.2016
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><t:translate key="order.title"/></title>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../static/header_dispatcher.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading"><t:translate key="message.order.for.you"/></div>
                <table class="table">
                    <tr>
                        <td><t:translate key="trip.num"/></td>
                        <td><t:translate key="placeholder.order.name"/></td>
                        <td><t:translate key="placeholder.car.speed"/></td>
                        <td><t:translate key="placeholder.car.capacity"/></td>
                    </tr>
                    <c:forEach items="${waitingOrders}" var="order">
                        <form method="post" action="${pageContext.servletContext.contextPath}/controller">
                            <input type="hidden" name="command" value="create_trip">
                            <tr>
                                <input type="hidden" name="orderId" value="${order.id}">
                                <td>${order.id}</td>
                                <td>${order.orderName}</td>
                                <td>${order.carSpeed}</td>
                                <td>${order.carCapacity}</td>
                                <td><input type="submit" class="btn btn-default"
                                           value="<t:translate key="link.create.trip"/>"/></td>
                            </tr>
                        </form>
                    </c:forEach>
                </table>
            </div>
            <div class="panel panel-warning">
                <div class="panel-heading"><t:translate key="message.order.performed"/></div>
                <table class="table">
                    <tr>
                        <td><t:translate key="trip.num"/></td>
                        <td><t:translate key="placeholder.order.name"/></td>
                        <td><t:translate key="placeholder.car.speed"/></td>
                        <td><t:translate key="placeholder.car.capacity"/></td>
                    </tr>
                    <c:forEach items="${performedOrders}" var="order">
                        <tr>
                            <td>${order.id}</td>
                            <td>${order.orderName}</td>
                            <td>${order.carSpeed}</td>
                            <td>${order.carCapacity}</td>
                        </tr>

                    </c:forEach>
                </table>
            </div>
            <div class="panel panel-success">
                <div class="panel-heading"><t:translate key="message.order.done"/></div>
                <table class="table">
                    <tr>
                        <td><t:translate key="trip.num"/></td>
                        <td><t:translate key="placeholder.order.name"/></td>
                        <td><t:translate key="placeholder.car.speed"/></td>
                        <td><t:translate key="placeholder.car.capacity"/></td>
                    </tr>
                    <c:forEach items="${doneOrders}" var="order">
                        <tr>
                            <td>${order.id}</td>
                            <td>${order.orderName}</td>
                            <td>${order.carSpeed}</td>
                            <td>${order.carCapacity}</td>
                        </tr>

                    </c:forEach>
                </table>
            </div>
            <h2 style="text-align: center; color: darkred;">${noOrders}</h2>

            <h2 style="text-align: center; color: green;">${newTrip}</h2>

            <form action="${pageContext.servletContext.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="show_orders">
                <input type="submit" class="btn btn-default" value="<t:translate key="order.button"/>">
            </form>
        </div>
    </div>
</div>
</body>
</html>
