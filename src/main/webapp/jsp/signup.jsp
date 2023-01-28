<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <jsp:include page="parts/head.jsp"/>
    <body>
    <jsp:include page="parts/navbar.jsp"/>
    <form action = "/signup" method = "POST">
       логін: <input type = "text" name = "login"><br/>
       ім`я: <input type = "text" name = "firstName"><br/>
       прізвище: <input type = "text" name = "lastName"><br/>
       номер телефону: <input type = "tel" name = "phoneNumber"><br/>
       пароль: <input type = "password" name = "password"><br/>
       <input type = "submit" value = "зареєструватись"/>
    </form>
    </body>
</body>