<%@ page import ="java.util.List"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="com.github.vlad.netetskyi.entity.Vehicle"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
    <jsp:include page="parts/head.jsp"/>
    <body>
        <jsp:include page="parts/navbar.jsp"/>
            <form action = "/search" method = "POST">
                <div class="container">
                    <label for="fromDate">з</label>
                    <input type="date" name = "fromDate" value = "<%=request.getAttribute("defaultFromDate")%>" required>

                    <label for="toDate">по</label>
                    <input type="date" name = "toDate" value = "<%=request.getAttribute("defaultToDate")%>" required> <br/>

                    <input type = "submit" value = "Пошук"/>
                </div>
            </form>
    </body>
</html>