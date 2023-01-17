package com.github.vlad.netetskyi.repository;

import com.github.vlad.netetskyi.service.security.Role;
import com.github.vlad.netetskyi.service.security.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserRepository {

    private final DataSource dataSource;

    public UserRepository() {
        this.dataSource = DataSourceFactory.getInstance();
    }

    public void save(User user) {
        final String sql = "INSERT INTO car_rental_sh.users(username, password, role, first_name, last_name) VALUES(?,?,?,?,?)";
        try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRole().toString());
            pstmt.setString(4, user.getFirstName());
            pstmt.setString(5, user.getLastName());

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
                return new User(
                        resultSet.getLong("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        Role.valueOf(resultSet.getString("role")),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")

                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
