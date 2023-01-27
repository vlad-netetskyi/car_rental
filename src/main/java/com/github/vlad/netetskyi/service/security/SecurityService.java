package com.github.vlad.netetskyi.service.security;

import com.github.vlad.netetskyi.repository.UserRepository;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class SecurityService {
    private UserRepository userRepository;

    private static SecurityService instance = null;

    public static SecurityService getInstance() {
        if (instance == null) {
            instance = new SecurityService();
            try {
                instance.addUser("admin", "admin", Role.ADMIN, "admin", "admin", "");
            } catch (Throwable t) {
                // NOP
            }
        }

        return instance;
    }

    public SecurityService() {
        this.userRepository = new UserRepository();
    }

    public void addUser(String userName, String password, Role role, String firstName, String lastName, String phoneNumber) {
        final User newUser = new User(null, userName, hash(password), role, firstName, lastName, phoneNumber);
        userRepository.save(newUser);
    }

    public User checkPasswordAndGet(String userName, String password) {
        final User user = userRepository.getByName(userName);
        if (user != null && Objects.equals(hash(password), user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    public static String hash(String input) {
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
