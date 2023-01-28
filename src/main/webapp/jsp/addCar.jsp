<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <jsp:include page="parts/head.jsp"/>
    <body>
        <jsp:include page="parts/navbar.jsp"/>
        <form action = "/addCar" method = "POST" enctype="multipart/form-data">
           brand: <input type = "text" name = "brand"/><br/>
           model: <input type = "text" name = "model"/><br/>
           type: <input type = "text" name = "type"/><br/>
           year: <input type = "number" min = "1900" name = "year"/><br/>
           rent price (daily) $: <input type = "number" step="0.1" name = "price"/><br/>
           image: <input type="file" name="car_img"/><br/>
           <input type = "submit" value = "add", name = "add"/>
        </form>
    </body>
</html>