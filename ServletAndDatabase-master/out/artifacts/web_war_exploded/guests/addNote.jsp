
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<script type="text/javascript">
    function IsEmpty(){
        if(document.forms['frm'].name.value == "" || document.forms["frm"].password.value == "")
        {
            alert("Invalid username");
            return false;
        }
        return true;
    }
</script>


<form name="loginForm" action="/mvc/loginInformation">
    <table>
        <tr>
            <td>
                Username: <input type="text" name="name">
            </td>
        </tr><tr>
        <td>
            Password: <input type="text" name="password">
        </td>
    </tr>
        <input id="insert" onclick="return IsEmpty()" type="submit" value="Login">
    </table>
</form>
</body>
</html>
