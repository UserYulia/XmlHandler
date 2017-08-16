<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>XmlFileHandler</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" src="js/script.js"></script>
</head>
<body>
<form method="post" action="uploadFile" enctype="multipart/form-data">
    Select file to upload:
    <input type="file" name="uploadFile" />
    <br/><br/>
    <input type="submit" value="Upload" />
</form>
<br>
<hr>
<div>
    <c:if test="${list!=null&&!list.isEmpty()}">
        <table>
            <tr>
                <c:forEach var="title" items="${list.get(1).entrySet()}">
                    <th>${title.getKey()}</th>
                </c:forEach>
            </tr>
            <c:forEach var="item" items="${list}">
                <tr>
                    <c:forEach var="content" items="${item.entrySet()}">
                        <td>${content.getValue()}</td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
        <br>
        <button id = "add">Добавить</button>
        <button id = "delete">Удалить</button>
    </c:if>
</div>
<div id="add_form">
    <hr>
    <form action="controller" method="get">
        <input type="hidden" name="command" value="add">
        <c:forEach var="title" items="${list.get(1).entrySet()}">
            <label>${title.getKey()}</label>
            <input type="text" name="${title.getKey()}">
        </c:forEach>
        <input type="submit" value="add">
    </form>
</div>
<div id="rm_form">
    <hr>
    <form action="controller" method="get">
        <input type="hidden" name="command" value="delete">
        <label>Number of deleted element(starts with 0):</label><br>
        <input type="number" name="number" max="${list.get(1).entrySet().size()}">
        <input type="submit" value="add">
    </form>
</div>
</body>
</html>
