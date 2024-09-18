package com.nexjot.nexjot.api.test_model.test_user;

import com.nexjot.nexjot.api.security.model.AuthUser;
import com.nexjot.nexjot.api.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthUserTest {

    private AuthUser authUser;

    @BeforeEach
    public void setUp() {
       User owner = new User();
       this.authUser = new AuthUser(owner, null);
    }
    @Test
    public void testGetAuthorities() {
       GrantedAuthority grantedAuthority = this.authUser.getAuthorities().iterator().next();
       assertEquals(grantedAuthority.getAuthority(), "ROLE_USER");
    }
}
