<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <jsp:include page="parts/head.jsp"/>
    <body>
    <jsp:include page="parts/navbar.jsp"/>
    <form action = "/login" method = "POST" class="full-page-form">
        <div class="mb-3">
            <label for="loginInput" class="form-label">Логін</label>
            <input type="text" class="form-control" id="loginInput" name="login" required>
        </div>
        <div class="mb-3">
            <label for="passwordInput" class="form-label">Пароль</label>
            <input type="password" class="form-control" id="passwordInput" name="password" required>
        </div>
        <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" id="exampleCheck1">
            <label class="form-check-label" for="exampleCheck1">Запам'ятати мене</label>
        </div>

        <div>
            <button type="submit" class="btn btn-primary">Увійти</button>
            <span>Ще не зареєстровані? <a href = "/signup">Зареєструватись</a></span>
        </div>
    </form>
    </body>
</body>