package com.bridgelabz;

import com.bridgelabz.dto.AuthRequest;
import com.bridgelabz.entity.User;
import com.bridgelabz.repository.UserRepository;
import com.bridgelabz.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_Success() {
        // Arrange
        AuthRequest request = new AuthRequest();
        request.setName("John");
        request.setEmail("john@example.com");
        request.setPassword("password123");

        // We need to capture the user object passed to save()
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setName("John");
        savedUser.setEmail("john@example.com");
        savedUser.setPassword("encrypted-password"); // Just placeholder
        savedUser.setRoles("USER");

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        User registered = userService.register(request);

        // Assert
        verify(userRepository).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();

        assertEquals("John", capturedUser.getName());
        assertEquals("john@example.com", capturedUser.getEmail());
        assertTrue(passwordEncoder.matches("password123", capturedUser.getPassword()));
        assertEquals("USER", capturedUser.getRoles());

        assertNotNull(registered);
        assertEquals("John", registered.getName());
    }
}
