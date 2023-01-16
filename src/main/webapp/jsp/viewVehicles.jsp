<%@ page import ="java.util.List"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="com.github.vlad.netetskyi.entity.Vehicle"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
    <body>
        <table>
            <%
            List vehicles = (List) request.getAttribute("vehicles");
            for(int i=0; i<vehicles.size();i++){
                Vehicle vehicle = (Vehicle) vehicles.get(i);%>
                <tr>
                    <td><img alt="img" style="max-width:250px;width:100%" src="data:image/jpeg;base64,<%= vehicle.getBase64ImgFile() %>"/></td>
                    <td><%= vehicle.getBrand() %></td>
                    <td><%= vehicle.getModel() %></td>
                    <td>Book!</td>
                </tr>
              <%}%>
        </table>
    </body>
</html>