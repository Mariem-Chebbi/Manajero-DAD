package lean.toc.manajerobackend.dad.service;


import lean.toc.manajerobackend.models.dad_models.User;
import lean.toc.manajerobackend.repositories.dad_repositories.UserRepository;
import lean.toc.manajerobackend.services.dad_services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceImplTests {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setIsArchived(false);

        // Mocking repository call
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Calling the method under test
        User result = userService.addUser(user);

        // Verifying interactions
        verify(userRepository).save(user);
        assertNotNull(result);
        assertFalse(result.getIsArchived());  // Verify that the user is not archived
    }

    @Test
    public void testGetUserById() {
        String userId = "user123";
        User user = new User();

        // Mocking repository call
        when(userRepository.findById(eq(userId))).thenReturn(Optional.of(user));

        // Calling the method under test
        User result = userService.getUserById(userId);

        // Verifying interactions
        verify(userRepository).findById(userId);
        assertNotNull(result);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        User user2 = new User();

        // Mocking repository call
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        // Calling the method under test
        List<User> result = userService.getAllUsers();

        // Verifying interactions
        verify(userRepository).findAll();
        assertEquals(2, result.size());
    }

    @Test
    public void testArchiveUser() {
        String userId = "user123";
        User user = new User();
        user.setIsArchived(false);

        // Mocking repository calls
        when(userRepository.findById(eq(userId))).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Calling the method under test
        userService.archiveUser(userId);

        // Verifying interactions
        verify(userRepository).findById(userId);
        verify(userRepository).save(user);
        assertTrue(user.getIsArchived());
    }

    @Test
    public void testRestoreUser() {
        String userId = "user123";
        User user = new User();
        user.setIsArchived(true);

        // Mocking repository calls
        when(userRepository.findById(eq(userId))).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Calling the method under test
        userService.restoreUser(userId);

        // Verifying interactions
        verify(userRepository).findById(userId);
        verify(userRepository).save(user);
        assertFalse(user.getIsArchived());
    }
}

