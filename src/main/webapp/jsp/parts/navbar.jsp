<%@ page import ="com.github.vlad.netetskyi.service.security.User"%>
<%@ page import ="com.github.vlad.netetskyi.service.security.Role"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<% User user = (User) request.getSession().getAttribute("user"); %>

<nav class="navbar navbar-expand-lg bg-dark">
    <a class="navbar-brand" href="/">Car rent</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <a class="nav-link" href="/search">Оренда</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/">Новини</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/">Правила</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/">Контакти</a>
        </li>
         <%if(user != null) {%>
             <li class="nav-item">
               <a class="nav-link" href="/bookings">Бронювання</a>
             </li>
               <%if(user.isAdmin()) {%>
                    <li class="nav-item">
                      <a class="nav-link" href="/addCar">Додати авто</a>
                    </li>
               <% } %>
            <% } else { %>
                <li class="nav-item">
                  <a class="nav-link" href="/login">Увійти</a>
                </li>
            <% } %>
      </ul>
    </div>
     <%if(user != null) {%>
        <div>Привіт, <%= user.getFirstName() %>. <a href="/logout">(вийти)</a></div>
     <%}%>
</nav>