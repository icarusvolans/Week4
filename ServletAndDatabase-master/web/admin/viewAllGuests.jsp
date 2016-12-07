<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Manual Guest Checkout</title>
</head>
<body>
Manual Guest Checkout
<br><br>
<table>
    <tr>
        <th></th>
        <th>Id</th>
        <th>Name</th>
        <th>Note In</th>
        <th>Checked In</th>
        <th>Note Out</th>
        <th>Checked Out</th>
    </tr>
    <c:forEach var="user" items="${Users1}">
        <tr>
            <td><INPUT type="checkbox" name="chk[]" value="${user.id}"/></td>
            <td><c:out value="${user.id}" /></td>
            <td><c:out value="${user.name}" /></td>
            <td><c:out value="${user.noteIn}" /></td>
            <td><c:out value="${user.checkedIn}" /></td>
            <td><c:out value="${user.noteOut}" /></td>
            <td><c:out value="${user.checkedOut}" /></td>
        </tr>
    </c:forEach>
    <input id="insert" type="submit" value="Check Out" action="/mvc/admin/logout">

</table>
</body>
</html>