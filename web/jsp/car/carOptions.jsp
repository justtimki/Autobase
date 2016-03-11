<%--
  Created by IntelliJ IDEA.
  User: Alexandr Kolymago
  Date: 25.11.2015
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="tags" %>
<html>
<div class="panel panel-primary">
    <div class="panel-heading">
        <t:translate key="message.trip.information"/>
    </div>
    <div class="panel-body">
        <p><b><t:translate key="placeholder.trip.name"/></b>: <input type="text" name="tripName"/></p>

        <p><b><t:translate key="placeholder.car.speed"/></b>: <input type="text" name="carSpeed"/> </p>

        <p><b><t:translate key="placeholder.car.capacity"/></b>: <input type="text" name="capacity"/></p>

        <p style="color:red;">${carSpeedError} </p>

        <p style="color:red;"> ${carNotFitError} </p>
    </div>
</div>
</html>
