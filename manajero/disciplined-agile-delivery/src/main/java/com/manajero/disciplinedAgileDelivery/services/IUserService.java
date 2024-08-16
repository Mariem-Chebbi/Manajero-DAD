package com.manajero.disciplinedAgileDelivery.services;

import com.manajero.disciplinedAgileDelivery.models.User;

import java.util.List;

public interface IUserService {
    User addUser(User user);
    User getUserById (String id);
    List<User> getAllUsers();
    void archiveUser(String id);
    void restoreUser(String id);

}
