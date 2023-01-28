<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <jsp:include page="parts/head.jsp"/>
    <body>
    <jsp:include page="parts/navbar.jsp"/>
    <form action = "/signup" method = "POST">
        <div class="mb-3">
            <label for="loginInput" class="form-label">Логін</label>
            <input type="text" class="form-control" id="loginInput" name="login" required>
        </div>
        <div class="mb-3">
            <label for="firstNameInput" class="form-label">Ім'я</label>
            <input type="text" class="form-control" id="firstNameInput" name="firstName" required>
        </div>
        <div class="mb-3">
            <label for="lastNameInput" class="form-label">Прізвище</label>
            <input type="text" class="form-control" id="lastNameInput" name="lastName" required>
        </div>
        <div class="mb-3">
            <label for="phoneNumberInput" class="form-label">Телефон</label>
            <input type="tel" class="form-control" id="phoneNumberInput" name="phoneNumber" required>
        </div>
        <div class="mb-3">
            <label for="passwordInput" class="form-label">Пароль</label>
            <input type="password" class="form-control" id="passwordInput" name="password" required>
        </div>
        <button type="submit" class="btn btn-primary">Зареєструватись</button>
    </form>
    </body>
</body>