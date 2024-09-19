package com.nexjot.nexjot.api.test_service;

import com.nexjot.nexjot.api.model.User;
import com.nexjot.nexjot.api.security.model.AuthUser;
import com.nexjot.nexjot.api.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserServiceTest {

    private SecurityContext securityContext;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() throws Exception {
        try (AutoCloseable mocks = MockitoAnnotations.openMocks(this)) {
            securityContext = mock(SecurityContext.class);
            SecurityContextHolder.setContext(securityContext);
        }
    }
    /**
     * Verify that the method returns the current authenticated user
     */
    @Test
    public void testGetCurrentAuthUser() {
        Authentication auth = mock(Authentication.class);
        AuthUser authUser = mock(AuthUser.class);
        when(securityContext.getAuthentication()).thenReturn(auth);
        when(auth.getPrincipal()).thenReturn(authUser);
        when(authUser.getUser()).thenReturn(new User());

        userService.getCurrentAuthUser();

        verify(auth, times(1)).getPrincipal();
    }
}

