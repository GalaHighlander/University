<%--
  Created by IntelliJ IDEA.
  User: Gala
  Date: 5/10/2020
  Time: 3:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Map</title>
    <%


        if(session.getAttribute("uname")==null)
        {
            response.sendRedirect("index.jsp");
            response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");

        }
    %>
    MAP MAP MAP MAP MAP MAP MAP
</head>
<body>

</body>
</html>
