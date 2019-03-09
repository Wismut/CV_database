<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                        <%--<th><%=HtmlUtil.EMPTY_TD%></th>--%>
                        <%--<th><%=HtmlUtil.EMPTY_TD%></th>--%>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</section>
</body>
</html>
