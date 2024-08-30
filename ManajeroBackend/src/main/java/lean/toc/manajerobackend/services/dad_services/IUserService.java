package lean.toc.manajerobackend.services.dad_services;


import lean.toc.manajerobackend.models.dad_models.User;

import java.util.List;

public interface IUserService {
    User addUser(User user);
    User getUserById (String id);
    List<User> getAllUsers();
    void archiveUser(String id);
    void restoreUser(String id);

}
