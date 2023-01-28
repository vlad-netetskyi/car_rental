<%@ page import ="com.github.vlad.netetskyi.service.security.User"%>
<%@ page import ="com.github.vlad.netetskyi.service.security.Role"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<% User user = (User) request.getSession().getAttribute("user"); %>

<header>
<a href="/">Головна </a>
<a href="/search">Парк авто </a>
<%if(user != null) {%>
    <a href="/bookings">Бронювання</a>
<% } %>
<a>Умови та гарантії </a>
<a>Контакти</a>
<%if(user != null) {%>
   <%if(user.isAdmin()) {%>
        <a href="/addCar">Додати авто</a>
   <% } %>
    Привіт <%= user.getFirstName() %> , <a href="/logout">(вийти)</a>
<% } else { %>
    <a href="/login"> увійти </a>
    <a href="/signup"> зареєструватись </a>
<% } %>

</header>