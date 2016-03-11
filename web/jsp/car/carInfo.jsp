<%--
  Created by IntelliJ IDEA.
  User: Alexandr Kolymago
  Date: 25.11.2015
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="tags" %>
<form method="post" action="${pageContext.servletContext.contextPath}/controller">
    <input type="hidden" name="command" value="show_car_info">

    <div class="panel panel-default">
        <div class="panel-heading"><t:translate key="message.car.you.car"/></div>
        <table class="table">
            <tr>
                <td><t:translate key="placeholder.car.name"/></td>
                <td><t:translate key="placeholder.car.speed"/></td>
                <td><t:translate key="placeholder.car.capacity"/></td>
                <td><t:translate key="placeholder.car.healthy"/></td>
            </tr>
            <tr>
                <td>${carName}</td>
                <td>${speed}</td>
                <td>${capacity}</td>
                <td>${isHealthy}</td>
            </tr>
        </table>
        <h3>${noCar}</h3>
    </div>
    <input type="submit" class="btn btn-default" value="<t:translate key='car.show.info'/>"/>
</form>