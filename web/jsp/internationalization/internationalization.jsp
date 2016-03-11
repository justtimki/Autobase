<%--
  Created by IntelliJ IDEA.
  User: Alexandr Kolymago
  Date: 09.12.2015
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="tags" %>
<html>
<form name="language_form" action="${pageContext.servletContext.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="change_lang"/>

    <div class="form-group">
        <label for="sel1"> <t:translate key="placeholder.choose.language"/></label> <br>
        <select onchange="document.forms['language_form'].submit();" class="form-control" name="lang" id="sel1">
            <option><t:translate key="choose.lang.message"/></option>
            <option value="rus">Русский</option>
            <option value="eng">English</option>
        </select>
    </div>
</form>
</html>
