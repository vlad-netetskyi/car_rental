<%@ page import ="java.util.List"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="com.github.vlad.netetskyi.entity.Booking"%>
<%@ page import ="com.github.vlad.netetskyi.service.security.User"%>
<%@ page import ="com.github.vlad.netetskyi.service.security.Role"%>
<% User user = (User) request.getSession().getAttribute("user"); %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
    <body>
        <jsp:include page="parts/header.jsp"/>
            <%
                List bookings = (List) request.getAttribute("bookings");

                if (bookings != null && !bookings.isEmpty()) { %>
                    <table>
                        <th>Номер бронювання</th>
                        <th>Час бронювання</th>
                        <th>Час оренди</th>
                        <th>Ціна</th>
                        <th>Статус</th>

                        <%for(int i=0; i<bookings.size();i++){
                            Booking booking = (Booking) bookings.get(i);%>
                            <tr>
                                <td><%= booking.getId() %></td>
                                <td><%= booking.getCreatedAtStr() %></td>
                                <td><%= booking.getRentDurationStr() %></td>
                                <td><%= booking.getRentTotalPrice() %> грн</td>
                                <td><%= booking.getStatus() %></td>
                            </tr>
                        <%}%>
                    </table>
                <%}else{%>
                    <div><p>У Вас поки немає бронювань</p></div>
               <%}%>

    </body>
</html>