<%--
  Created by IntelliJ IDEA.
  User: Alexandr Kolymago
  Date: 30.11.2015
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title><t:translate key="registration.page.title"/></title>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<div class="page-header">
    <h2 style="text-align: center;"><t:translate key="registration.message"/></h2>
</div>
<main>
    <div class="container">
        <div class="row">
            <div class="col-md-5">
                <form action="${pageContext.servletContext.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="registration">

                    <div class="input-group">
                        <span class="input-group-addon">@</span>
                        <input type="text" name="login" class="form-control"
                               placeholder="<t:translate key="placeholder.username" />">
                    </div>
                    <p style="color: red;"> ${loginError} </p>

                    <div class="input-group">
                        <span class="input-group-addon">@</span>
                        <input type="text" name="password" class="form-control"
                               placeholder="<t:translate key="placeholder.password"/>">
                    </div>
                    <p style="color: red;"> ${passwordError} </p>

                    <div class="input-group">
                        <span class="input-group-addon">@</span>
                        <input type="text" name="carName" class="form-control"
                               placeholder="<t:translate key="placeholder.car.name"/>">
                    </div>
                    <p style="color: red;"> ${carNameError} </p>

                    <div class="input-group">
                        <span class="input-group-addon">@</span>
                        <input type="number" name="carSpeed" class="form-control"
                               placeholder="<t:translate key="placeholder.car.speed"/>">
                    </div>
                    <p style="color: red;"> ${carSpeedError} </p>

                    <div class="input-group">
                        <span class="input-group-addon">@</span>
                        <input type="number" name="carCapacity" class="form-control"
                               placeholder="<t:translate key="placeholder.car.capacity"/>">
                    </div>
                    <p style="color: red;"> ${carCapasityError} </p>

                    <div class="form-group">
                        <label for="sel1"> <t:translate key="placeholder.car.healthy"/></label>
                        <select class="form-control" id="sel1" name="isHealthy">
                            <option value="yes"><t:translate key="option.value.yes"/></option>
                            <option value="no"><t:translate key="option.value.no"/></option>
                        </select>
                    </div>
                    <p style="color: red;"> ${carCarHealthy} </p>

                    <p style="color: red;"> ${accountExist} </p>
                    <input type="submit" class="btn btn-default" value="<t:translate key="reg.button"/>"/>
                    <a class="btn btn-default" role="button" href="<c:url value="/index.jsp" />"><t:translate
                            key="return.button"/></a>
                </form>
            </div>
            <div class="col-md-5">
                <h3 style="text-align: center; color: green">${registrationComplete}</h3>
            </div>
        </div>
    </div>
</main>
</body>
</html>
