package com.bridgelabz.Controller;

import com.bridgelabz.controller.AuthController;
import com.bridgelabz.dto.AuthRequest;
import com.bridgelabz.entity.User;
import com.bridgelabz.service.UserService;
import com.bridgelabz.exception.CustomExceptions.BadRequestException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    private UserService userService;
    private AuthController authController;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        authController = new AuthController();
        authController.setUserService(userService); // inject mock here
    }

    @Test
    void testRegisterUser_Success() {
        AuthRequest request = new AuthRequest();
        request.setEmail("test@example.com");
        request.setPassword("password123");

        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setEmail("test@example.com");

        when(userService.register(request)).thenReturn(mockUser);

        User registered = authController.registerUser(request);

        assertNotNull(registered);
        assertEquals("test@example.com", registered.getEmail());
    }

    @Test
    void testRegisterUser_MissingEmail() {
        AuthRequest request = new AuthRequest();
        request.setPassword("password123");

        Exception exception = assertThrows(BadRequestException.class, () -> {
            authController.registerUser(request);
        });

        assertEquals("Email must not be empty", exception.getMessage());
    }

    @Test
    void testRegisterUser_MissingPassword() {
        AuthRequest request = new AuthRequest();
        request.setEmail("test@example.com");

        Exception exception = assertThrows(BadRequestException.class, () -> {
            authController.registerUser(request);
        });

        assertEquals("Password must not be empty", exception.getMessage());
    }
}
