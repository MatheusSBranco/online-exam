package site.onlineexam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import site.onlineexam.exception.UserException;
import site.onlineexam.model.User;
import site.onlineexam.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
    }

    @Test
    void shouldCreateUser() {
        userService.createUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void shouldFindUserByEmail() {
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findUserByEmail("test@example.com");

        assertTrue(foundUser.isPresent());
        assertEquals("test@example.com", foundUser.get().getEmail());
    }

    @Test
    void shouldReturnEmptyOptionalWhenEmailNotFound() {
        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());

        Optional<User> foundUser = userService.findUserByEmail("nonexistent@example.com");

        assertFalse(foundUser.isPresent());
    }

    @Test
    void shouldReturnAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userRepository.findAll()).thenReturn(users);

        List<User> allUsers = userService.getAllUsers();

        assertEquals(1, allUsers.size());
        assertEquals("test@example.com", allUsers.get(0).getEmail());
    }

    @Test
    void shouldReturnUserById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1L);

        assertNotNull(foundUser);
        assertEquals("test@example.com", foundUser.getEmail());
    }

    @Test
    void shouldThrowUserExceptionWhenUserIdNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        UserException exception = assertThrows(UserException.class, () -> {
            userService.getUserById(1L);
        });

        assertEquals("Invalid user ID", exception.getMessage());
    }

    @Test
    void shouldUpdateUser() {
        when(userRepository.existsById(user.getId())).thenReturn(true);

        userService.updateUser(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void shouldThrowUserExceptionWhenUpdatingNonExistentUser() {
        when(userRepository.existsById(user.getId())).thenReturn(false);

        UserException exception = assertThrows(UserException.class, () -> {
            userService.updateUser(user);
        });

        assertEquals("Invalid user ID", exception.getMessage());
    }

    @Test
    void shouldDeleteUser() {
        when(userRepository.existsById(anyLong())).thenReturn(true);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowUserExceptionWhenDeletingNonExistentUser() {
        when(userRepository.existsById(anyLong())).thenReturn(false);

        UserException exception = assertThrows(UserException.class, () -> {
            userService.deleteUser(1L);
        });

        assertEquals("Invalid user ID", exception.getMessage());
    }
}