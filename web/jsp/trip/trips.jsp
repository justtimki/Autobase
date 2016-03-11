<%--
  Created by IntelliJ IDEA.
  User: Alexandr Kolymago
  Date: 18.11.2015
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" uri="tags" %>

<div class="panel panel-default">
    <div class="panel-heading"><t:translate key="message.trip.for.you"/></div>
    <table class="table">
        <tr>
            <td><t:translate key="trip.num"/></td>
            <td><t:translate key="trip.date"/></td>
            <td><t:translate key="placeholder.trip.name"/></td>
            <td><t:translate key="message.driver.login"/></td>
            <td><t:translate key="placeholder.car.name"/></td>
        </tr>
        <c:forEach items="${trips}" var="trip">
            <tr>
                <td>${trip.id}</td>
                <td>${trip.tripDate}</td>
                <td>${trip.tripName}</td>
                <td>${trip.driverName}</td>
                <td>${trip.carName}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<h3 style="color:darkred; text-align: center;">${noTrips}</h3>

<form action="${pageContext.servletContext.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="show_trips">
    <input type="submit" class="btn btn-default" value="<t:translate key="trip.show.button"/>"/>
</form>