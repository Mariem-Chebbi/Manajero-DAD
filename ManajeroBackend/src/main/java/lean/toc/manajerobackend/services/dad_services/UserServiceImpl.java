package lean.toc.manajerobackend.services.dad_services;


import lean.toc.manajerobackend.models.dad_models.User;
import lean.toc.manajerobackend.repositories.dad_repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService{
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        user.setIsArchived(false);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void archiveUser(String id) {
        User user = userRepository.findById(id).orElse(null);
        user.setIsArchived(true);
        userRepository.save(user);
    }

    @Override
    public void restoreUser(String id) {
        User user = userRepository.findById(id).orElse(null);
        user.setIsArchived(false);
        userRepository.save(user);
    }


}
