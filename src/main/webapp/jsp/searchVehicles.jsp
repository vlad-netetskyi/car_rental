<%@ page import ="java.util.List"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="com.github.vlad.netetskyi.entity.Vehicle"%>
<%@ page import ="com.github.vlad.netetskyi.service.security.User"%>
<%@ page import ="com.github.vlad.netetskyi.service.security.Role"%>
<% User user = (User) request.getSession().getAttribute("user"); %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
    <jsp:include page="parts/head.jsp"/>
    <body>
        <jsp:include page="parts/navbar.jsp"/>
        <div class="container-fluid">
            <h3>Автомобілі</h3>
            <form action = "/search" method = "POST" class="row justify-content-md-center">
                <div class="col-auto">
                    <input type="date" class="form-control" id="fromDateInput" name="fromDate" value="<%=request.getAttribute("fromDate")%>" required>
                </div>
                <div class="col-auto">
                    <input type="date" class="form-control" id="toDateInput" name="toDate" value="<%=request.getAttribute("toDate")%>" required>
                </div>
                <div class="col-auto">
                    <select class="form-control" id="cityInput" name="city" required>
                        <option value="Львів">Львів</option>
                        <option value="Івано-Франківськ">Івано-Франківськ</option>
                        <option value="Тернопіль">Тернопіль</option>
                    </select>
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-dark">Пошук</button>
                </div>
            </form>
        </div>

        <div class="container-fluid">
            <%
                List vehicles = (List) request.getAttribute("vehicles");
                if (vehicles != null) {
                    if (!vehicles.isEmpty()) {%>
                        <div class="row">
                        <%for(int i=0; i<vehicles.size();i++){
                            Vehicle vehicle = (Vehicle) vehicles.get(i);%>
                            <div class="col-md-3 car-card">
                                <div class="card">
                                    <div class="car-name"><%= vehicle.getBrand()%> <%= vehicle.getModel()%></div>
                                    <p><img alt="img" class= "img-fluid" src="data:image/jpeg;base64,<%= vehicle.getBase64ImgFile() %>"/></p>
                                    <div class="textbox border-bottom">
                                        <p class="float-start">Рік</p>
                                        <p class="float-end"><%= vehicle.getYear()%></p>
                                    </div>
                                    <div class="textbox border-bottom">
                                        <p class="float-start">Тип кузова</p>
                                        <p class="float-end"><%= vehicle.getType()%></p>
                                    </div>
                                     <div class="textbox border-bottom">
                                        <p class="float-start">Тип палива</p>
                                        <p class="float-end"><%= vehicle.getFuel()%></p>
                                    </div>
                                    <div class="textbox border-bottom">
                                        <p class="float-start">Об'єм двигуна</p>
                                        <p class="float-end"><%= vehicle.getEngineCapacity()%></p>
                                    </div>
                                    <div class="textbox border-bottom">
                                        <p class="float-start">Тип трансмісії</p>
                                        <p class="float-end"><%= vehicle.getTransmission()%></p>
                                    </div>
                                    <div class="textbox border-bottom">
                                        <p class="float-start">Кількість місць</p>
                                        <p class="float-end"><%= vehicle.getSeats()%></p>
                                    </div>
                                    <div class="textbox price-container">
                                        <p class="float-start"><%= vehicle.getPrice()%> грн/доба</p>
                                        <%if(user == null) {%>
                                            <p class="float-end">
                                                <a href="/login">Забронювати</a>
                                             </p>
                                        <% } else if (!user.isAdmin()) { %>
                                            <p class="float-end">
                                                <a href="/bookCar?vehicleId=<%=vehicle.getId()%>&userId=<%=user.getId()%>&fromDate=<%=request.getAttribute("fromDate")%>&toDate=<%=request.getAttribute("toDate")%>">Забронювати</a>
                                             </p>
                                        <% } %>
                                    </div>
                                </div>
                            </div>
                        <%}%>
                        </div>
                    <%} else {%>
                        <p> Пошук не дав результатів </p>
                    <%}%>
                <%}%>
        </div>
    </body>
</html>