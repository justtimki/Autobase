<%--
  Created by IntelliJ IDEA.
  User: Alexandr Kolymago
  Date: 19.11.2015
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="panel panel-default">
    <div class="panel-heading"><t:translate key="message.drivers"/></div>
    <table class="table">
        <tr>
            <td><t:translate key="message.driver.login"/></td>
            <td><t:translate key="placeholder.car.name"/></td>
        </tr>
        <c:forEach items="${drivers}" var="driver">
            <tr>
                <td>${driver.accountName}</td>
                <td>${driver.carName}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<form action="${pageContext.servletContext.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="show_drivers">
    <input type="submit" class="btn btn-default" value="<t:translate key="drivers.show.button"/>"/>
</form>
