package lean.toc.manajerobackend.dad.service.IT;


import lean.toc.manajerobackend.models.dad_models.User;
import lean.toc.manajerobackend.repositories.dad_repositories.UserRepository;
import lean.toc.manajerobackend.services.dad_services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test") // Ensure you have a test profile
public class UserServiceImplIntegrationTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    public void setUp() {
        // Clean up the repository
        userRepository.deleteAll();

        // Create a test user
        testUser = User.builder()
                .email("testuser@example.com")
                .username("testuser")
                .password("password123")
                .matricule("123456")
                .isArchived(false)
                .build();
    }

    @Test
    public void testAddUser() {
        User savedUser = userService.addUser(testUser);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.get_id()).isNotNull();
        assertThat(savedUser.getEmail()).isEqualTo(testUser.getEmail());
    }

    @Test
    public void testGetUserById() {
        User savedUser = userService.addUser(testUser);
        User retrievedUser = userService.getUserById(savedUser.get_id());
        assertThat(retrievedUser).isNotNull();
        assertThat(retrievedUser.getUsername()).isEqualTo(testUser.getUsername());
    }

    @Test
    public void testGetAllUsers() {
        userService.addUser(testUser);
        List<User> users = userService.getAllUsers();
        assertThat(users).isNotEmpty();
        assertThat(users.size()).isEqualTo(1);
    }

    @Test
    public void testArchiveUser() {
        User savedUser = userService.addUser(testUser);
        userService.archiveUser(savedUser.get_id());
        User archivedUser = userService.getUserById(savedUser.get_id());
        assertThat(archivedUser).isNotNull();
        assertThat(archivedUser.getIsArchived()).isTrue();
    }

    @Test
    public void testRestoreUser() {
        User savedUser = userService.addUser(testUser);
        userService.archiveUser(savedUser.get_id());
        userService.restoreUser(savedUser.get_id());
        User restoredUser = userService.getUserById(savedUser.get_id());
        assertThat(restoredUser).isNotNull();
        assertThat(restoredUser.getIsArchived()).isFalse();
    }
}

