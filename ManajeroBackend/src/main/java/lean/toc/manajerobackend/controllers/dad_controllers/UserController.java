package lean.toc.manajerobackend.controllers.dad_controllers;



import lean.toc.manajerobackend.models.dad_models.User;
import lean.toc.manajerobackend.services.dad_services.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/dad/users")
public class UserController {
    private IUserService iUserService;

    @GetMapping("/getAll")
    public List<User> getAllUsers(){
        return iUserService.getAllUsers();
    }

    @PostMapping("/add")
    public User addUser (@RequestBody User user){
        return iUserService.addUser(user);
    }
    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable String id){
        return iUserService.getUserById(id);
    }

    @PutMapping("/archive/{id}")
    public void archiveUser(@PathVariable String id) {
        this.iUserService.archiveUser(id);
    }

    @PutMapping("/restore/{id}")
    public void restoreUser(@PathVariable String id) {
        this.iUserService.restoreUser(id);
    }
}
