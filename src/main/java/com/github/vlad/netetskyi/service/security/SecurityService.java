package com.github.vlad.netetskyi.service.security;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class SecurityService {
    private Map<String, User> users;

    private static SecurityService instance = null;

    public static SecurityService getInstance() {
        if (instance == null) {
            instance = new SecurityService();
            instance.addUser("admin", "admin", Role.ADMIN, "admin", "admin"); // TODO: remove it later
        }

        return instance;
    }

    public SecurityService() {
        this.users = new ConcurrentHashMap<>();
    }

    public boolean addUser(String userName, String password, Role role, String firstName, String lastName) {
        // TODO: hash passwords!
        final User newUser = new User(userName, password, role, firstName, lastName);
        final User prev = users.putIfAbsent(userName, newUser);
        return !newUser.equals(prev);
    }

    public User checkPasswordAndGet(String userName, String password) {
        // TODO: hash passwords!
        final User user = users.get(userName);
        if (Objects.equals(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }
}
