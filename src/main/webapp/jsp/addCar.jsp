<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <jsp:include page="parts/head.jsp"/>
    <body>
        <jsp:include page="parts/navbar.jsp"/>
        <form action = "/addCar" method = "POST" enctype="multipart/form-data" class="full-page-form">
            <h3>Додати автомобіль</h3>
            <div class="mb-3">
                <label for="cityInput" class="form-label">Місто</label>
                <select class="form-control" id="cityInput" name="city" required>
                    <option value="Львів">Львів</option>
                    <option value="Івано-Франківськ">Івано-Франківськ</option>
                    <option value="Тернопіль">Тернопіль</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="brandInput" class="form-label">Марка</label>
                <input type="text" class="form-control" id="brandInput" name="brand" required>
            </div>
            <div class="mb-3">
                <label for="modelInput" class="form-label">Модель</label>
                <input type="text" class="form-control" id="modelInput" name="model" required>
            </div>
            <div class="mb-3">
                <label for="typeInput" class="form-label">Кузов</label>
                <input type="text" class="form-control" id="typeInput" name="type" required>
            </div>
            <div class="mb-3">
                <label for="yearInput" class="form-label">Рік</label>
                <input type="number" class="form-control" id="yearInput" name="year" min = "1900" required>
            </div>
            <div class="mb-3">
                <label for="fuelInput" class="form-label">Паливо</label>
                <select class="form-control" id="fuelInput" name="fuel" required>
                    <option value="Бензин">Бензин</option>
                    <option value="Дизель">Дизель</option>
                    <option value="Електро">Електро</option>
                    <option value="Газ">Газ</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="engineCapacityInput" class="form-label">Об'єм двигуна</label>
                <input type="number" class="form-control" id="engineCapacityInput" name="engineCapacity" step = "0.1" required>
            </div>
            <div class="mb-3">
                <label for="transmissionInput" class="form-label">Трансмісія</label>
                <select class="form-control" id="transmissionInput" name="transmission" required>
                    <option value="Автомат">Автомат</option>
                    <option value="Механіка">Мехеніка</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="seatsInput" class="form-label">Кількість місць</label>
                <input type="number" class="form-control" id="seatsInput" name="seats" min = "1" max = "10" required>
            </div>
            <div class="mb-3">
                <label for="priceInput" class="form-label">Вартість (доба), грн</label>
                <input type="number" class="form-control" id="priceInput" name="price" step = "1" required>
            </div>
            <div class="mb-3">
                <label for="fileInput" class="form-label">Фото</label>
                <input type="file" class="form-control" id="fileInput" name="car_img" required>
            </div>
            <button type="submit" class="btn btn-primary">Додати</button>
        </form>
    </body>
</html>