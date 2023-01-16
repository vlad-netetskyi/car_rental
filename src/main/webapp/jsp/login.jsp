<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <body>
    <a href = "/signup">Sing Up</a>
    <form action = "/login" method = "POST">
        <div class="container">
            <label for="login">username</label>
            <input type = "text" name = "login" placeholder = "enter your username" required>

            <label for="password">password</label>
            <input type = "password" name = "password" placeholder = "enter your password" required>
            <p> ${errorMessage} </p>
            <button type = "submit" value = "login"/>
            <label>
              <input type="checkbox" checked="checked" name="remember"> Remember me
            </label>
        </div>
    </form>
    </body>
</body>