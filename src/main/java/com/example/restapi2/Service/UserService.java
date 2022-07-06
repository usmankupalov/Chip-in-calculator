package com.example.restapi2.Service;

import com.example.restapi2.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    User getUser(Long id);
    List<User> getInformationAboutUsers();
    Integer getAllUsers();
}
