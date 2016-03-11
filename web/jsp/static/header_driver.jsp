<%--
  Created by IntelliJ IDEA.
  User: Alexandr Kolymago
  Date: 25.11.2015
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="tags" prefix="t" %>
<html>
<style>
    .navbar-form {
        margin: 0;
    }
</style>
<header>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="${pageContext.request.contextPath}/jsp/main_driver.jsp">
                            <t:translate key="link.trips"/>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/jsp/car/main_car.jsp">
                            <t:translate key="link.car"/>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/jsp/trip/completeTrip.jsp">
                            <t:translate key="link.complete.trip"/>
                        </a>
                    </li>
                </ul>
                <form class="navbar-form navbar-right" action="${pageContext.servletContext.contextPath}/controller" method="post">
                    <div class="form-group">
                        <a class="navbar-brand" href="#">${user}</a>
                        <input type="hidden" name="command" value="logout" class="form-control"/>
                    </div>
                    <button type="submit" class="btn btn-default"><t:translate key="logout.button"/></button>
                </form>
            </div>
        </div>
    </nav>
</header>
</html>