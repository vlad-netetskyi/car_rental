package com.github.vlad.netetskyi.repository;

import com.github.vlad.netetskyi.service.security.Role;
import com.github.vlad.netetskyi.service.security.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepository {

    private final DataSource dataSource;

    private static UserRepository INSTANCE;

    public static synchronized UserRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository();
        }

        return INSTANCE;
    }


    private UserRepository() {
        this.dataSource = DataSourceFactory.getInstance();
    }

    public void save(User user) {
        final String sql = "INSERT INTO car_rental_sh.users(username, password, role, first_name, last_name, phone_number) VALUES(?,?,?,?,?,?)";
        try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRole().toString());
            pstmt.setString(4, user.getFirstName());
            pstmt.setString(5, user.getLastName());
            pstmt.setString(6, user.getPhoneNumber());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public User getByName(String name) {
        final String sql = "SELECT * FROM car_rental_sh.users WHERE username = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                return map(resultSet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<User> getByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }

        var sql = String.format("SELECT * FROM car_rental_sh.users WHERE user_id IN (%s)",
                ids.stream()
                        .map(v -> "?")
                        .collect(Collectors.joining(", ")));
        final List<User> users = new ArrayList<>();
        try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int index = 1;
            for (Long id : ids) {
                pstmt.setLong(index++, id);
            }
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                users.add(map(resultSet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;

    }

    private User map(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("user_id"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                Role.valueOf(resultSet.getString("role")),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("phone_number")
        );
    }
}
