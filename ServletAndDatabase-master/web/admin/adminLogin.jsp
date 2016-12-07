<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Login</title>
</head>
<body>
<script type="text/javascript">
    function IsEmpty(){
        if(document.forms['adminForm'].name.value == "" || document.forms["adminForm"].password.value == "")
        {
            alert("Invalid username/password.");
            return false;
        }
        return true;
    }
</script>


<form name="adminForm" action="/mvc/admin/">
    <table>
        <tr>
            <td>
                Admin ID: <input type="text" name="name">
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
