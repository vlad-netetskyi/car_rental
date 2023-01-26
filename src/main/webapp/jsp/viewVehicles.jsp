<%@ page import ="java.util.List"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="com.github.vlad.netetskyi.entity.Vehicle"%>
<%@ page import ="com.github.vlad.netetskyi.service.security.User"%>
<%@ page import ="com.github.vlad.netetskyi.service.security.Role"%>
<% User user = (User) request.getSession().getAttribute("user"); %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
    <body>
        <jsp:include page="parts/header.jsp"/>
        <table>
            <%
            List vehicles = (List) request.getAttribute("vehicles");
            for(int i=0; i<vehicles.size();i++){
                Vehicle vehicle = (Vehicle) vehicles.get(i);%>
                <tr>
                    <td><img alt="img" style="max-width:250px;width:100%" src="data:image/jpeg;base64,<%= vehicle.getBase64ImgFile() %>"/></td>
                    <td><%= vehicle.getBrand() %></td>
                    <td><%= vehicle.getModel() %></td>
                    <td>
                        <%if(user != null && !user.isAdmin()) {%>
                            <a href="/bookCar?vehicleId=<%=vehicle.getId()%>&userId=<%=user.getId()%>&fromDate=<%=request.getAttribute("fromDate")%>&toDate=<%=request.getAttribute("toDate")%>">Забронювати</a>
                        <% } %>
                    </td>
                </tr>
              <%}%>
        </table>
    </body>
</html>