<%--
  Created by IntelliJ IDEA.
  User: Alexandr Kolymago
  Date: 16.01.2016
  Time: 0:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="tags" %>
<form method="post" action="${pageContext.servletContext.contextPath}/controller">
    <input type="hidden" name="command" value="update_car">

    <div class="panel panel-primary">
        <div class="panel-heading"><t:translate key="message.car.update"/></div>
        <div class="panel-body">
            <p><t:translate key="placeholder.car.healthy"/> <select name="isHealthy">
                <option value="yes"><t:translate key="option.value.yes"/></option>
                <option value="no"><t:translate key="option.value.no"/></option>
            </select>
            </p>
            <p style="color: green">${successCarUpdate}</p>
            <button type="submit" class="btn btn-default"><t:translate
                    key="car.update.button"/></button>
        </div>
    </div>
</form>
