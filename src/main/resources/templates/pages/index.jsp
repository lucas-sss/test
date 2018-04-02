<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--
  Created by IntelliJ IDEA.
  User: wliu
  Date: 2017/12/9 0009
  Time: 8:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>飞马追书</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/test.js"></script>
</head>
<body>
    <div>
        现在时间时${time}
    </div>
    <a href="#" onclick="clickMe()">点我</a>
</body>
</html>
