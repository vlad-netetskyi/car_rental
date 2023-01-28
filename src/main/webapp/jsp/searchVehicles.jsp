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

        <div>
            <%
                List vehicles = (List) request.getAttribute("vehicles");
                if (vehicles != null) {
                    if (!vehicles.isEmpty()) {
                        for(int i=0; i<vehicles.size();i++){
                            Vehicle vehicle = (Vehicle) vehicles.get(i);%>
                            <div>
                                <p><img alt="img" style="max-width:250px;width:100%" src="data:image/jpeg;base64,<%= vehicle.getBase64ImgFile() %>"/></p>
                                <p><%= vehicle.getBrand() %></p>
                                <p><%= vehicle.getModel() %></p>
                                <p>
                                    <%if(user != null && !user.isAdmin()) {%>
                                        <a href="/bookCar?vehicleId=<%=vehicle.getId()%>&userId=<%=user.getId()%>&fromDate=<%=request.getAttribute("fromDate")%>&toDate=<%=request.getAttribute("toDate")%>">Забронювати</a>
                                    <% } %>
                                </p>
                            </div>
                        <%}%>
                    <%} else {%>
                        <p> Пошук не дав результатів </p>
                    <%}%>
                <%}%>
        </div>
    </body>
</html>