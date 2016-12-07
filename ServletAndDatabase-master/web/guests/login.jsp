
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<script type="text/javascript">
function IsEmpty(){
if(document.forms['loginForm'].name.value == "")
{
alert("Please enter your name.");
return false;
}
return true;
}
</script>

<form name="loginForm" action="/mvc/guests/login">
    <table>
    <tr><td>
        Username: <input type="text" name="name">
    </td></tr>
    </tr><td>
        Comments: <input type="text" name="note">
    </td></tr>
    <input id="insert" onclick="return IsEmpty()" type="submit" value="Login">
    </table>
</form>
</body>
</html>
