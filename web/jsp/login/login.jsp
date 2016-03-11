<%--
  Created by IntelliJ IDEA.
  User: Alexandr Kolymago
  Date: 14.11.2015
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="t" uri="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet">
    <title><t:translate key="login.page.title"/></title>
</head>
<body>
<div class="page-header">
    <h2 style="text-align: center;"><t:translate key="header.autobase"/></h2>
</div>
<div class="container">
    <div class="col-md-5">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title"><t:translate key="login.message"/></h3>
            </div>
            <div class="panel-body">
                <div class="container">
                    <div class="row">
                        <div class="col-md-4">
                            <form name="loginForm" method="post" action="${pageContext.servletContext.contextPath}/controller">
                                <input type="hidden" name="command" value="login"/>

                                <div class="input-group">
                                    <span class="input-group-addon">@</span>
                                    <input type="text" name="login" class="form-control"
                                           placeholder="<t:translate key="placeholder.username"/>">
                                </div>
                                <br/>

                                <div class="input-group">
                                    <span class="input-group-addon">@</span>
                                    <input type="password" name="password" class="form-control"
                                           placeholder="<t:translate key="placeholder.password"/>">
                                </div>
                                <br/>

                                <p style="color: red;"> ${login_error} </p>
                                <br/>
                                <input type="submit" class="btn btn-default" value="<t:translate key="login.button"/>"/>
                                <a href="<c:url value="/jsp/login/registration.jsp"/>" role="button"
                                   class="btn btn-default">
                                    <t:translate key="reg.button"/>
                                </a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-5">
        <jsp:include page="../internationalization/internationalization.jsp"/>
    </div>
</div>
</body>
</html>
