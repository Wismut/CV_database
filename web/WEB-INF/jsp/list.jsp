<%@ page import="ru.javawebinar.webapp.WebAppConfig" %>
<%@ page import="ru.javawebinar.webapp.model.ContactType" %>
<%@ page import="ru.javawebinar.webapp.util.HtmlUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Список всех резюме</title>
</head>
<body>
<section>
    <table>
        <tr>
            <td colspan="5" style="text-align: right"><a href="resume?action=create"><img src="img/add.png">Резюме</a> </td>
        </tr>
        <tr>
            <td>
                <table border="1" cellpadding="8" cellspacing="0">
                    <tr>
                        <th>Имя</th>
                        <th>Проживание</th>
                        <th>Email</th>
                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                    </tr>
                    <%
                        request.setAttribute("resumeList", WebAppConfig.get().getStorage().getAllSorted());
                    %>
                    <c:forEach items="${resumeList}" var="resume">
                    <jsp:useBean id="resume" type="ru.javawebinar.webapp.model.Resume"/>
                    <tr>
                        <td><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
                        <td>${resume.location}></td>
                        <td><%=HtmlUtil.getContact(resume, ContactType.MAIL)%></td>
                        <td><a href="resume?uuid=${resume.uuid}&action=delete"><img src="img/delete.png"></a></td>
                        <td><a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></td>
                    </tr>
                    </c:forEach>

                </table>
            </td>
        </tr>
    </table>
</section>
</body>
</html>
