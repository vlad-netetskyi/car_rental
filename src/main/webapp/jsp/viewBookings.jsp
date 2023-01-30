<%@ page import ="java.util.List"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="com.github.vlad.netetskyi.entity.Booking"%>
<%@ page import ="com.github.vlad.netetskyi.service.security.User"%>
<%@ page import ="com.github.vlad.netetskyi.service.security.Role"%>
<% User user = (User) request.getSession().getAttribute("user"); %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
    <jsp:include page="parts/head.jsp"/>
    <body>
        <jsp:include page="parts/navbar.jsp"/>
            <%
                List bookings = (List) request.getAttribute("bookings");

                if (bookings != null && !bookings.isEmpty()) { %>
                <div class="container-fluid">
                    <h3>Бронювання</h3>
                    <table class="table">
                        <tr>
                            <th scope="col">Номер бронювання</th>
                            <th scope="col">Час бронювання</th>
                            <th scope="col">Авто</th>
                            <th scope="col">Час оренди</th>
                            <th scope="col">Ціна</th>
                            <th scope="col">Статус</th>
                            <% if(user.isAdmin()) { %>
                                <th>Замовник</th>
                                <th>Телефон</th>
                                <th>Управління бронюванням</th>
                            <% } %>
                        </tr>

                        <%for(int i=0; i<bookings.size();i++){
                            Booking booking = (Booking) bookings.get(i);%>
                            <tr>
                                <td scope="row"><%= booking.getId() %></td>
                                <td><%= booking.getCreatedAtStr() %></td>
                                <td><%= booking.getVehicleBrand()%> <%= booking.getVehicleModel()%></td>
                                <td><%= booking.getRentDurationStr() %></td>
                                <td><%= booking.getRentTotalPrice() %> грн</td>
                                <td><%= booking.getStatus() %></td>
                                <% if(user.isAdmin()) { %>
                                    <td><%= booking.getUserFullName()%></td>
                                    <td><%= booking.getUserPhone()%></td>
                                    <td>
                                        <% if(booking.canBeAccepted()) { %>
                                            <a href="/updateBooking?id=<%=booking.getId()%>&status=<%=Booking.ACCEPTED%>">Підтвердити</a>
                                        <% } %>
                                        <% if(booking.canBeRejected()) { %>
                                            <a href="/updateBooking?id=<%=booking.getId()%>&status=<%=Booking.REJECTED%>">Відхилити</a>
                                        <% } %>
                                    </td>
                                <% } %>
                            </tr>
                        <%}%>
                    </table>
                </div>
                <%}else{%>
                    <div><p>У Вас поки немає бронювань</p></div>
               <%}%>

    </body>
</html>