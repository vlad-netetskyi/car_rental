<%@ page import ="java.util.List"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="com.github.vlad.netetskyi.entity.Vehicle"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
    <body>
        <jsp:include page="parts/header.jsp"/>
            <form action = "/" method = "POST">
                <div class="container">
                    <label for="fromDate">з</label>
                    <input type="date" name = "fromDate" required>

                    <label for="toDate">по</label>
                    <input type="date" name = "toDate" required> <br/>

                    <input type = "submit" value = "Пошук"/>
                </div>
            </form>
    </body>
</html>