package com.nexjot.nexjot.api.security.service;

import com.nexjot.nexjot.api.model.User;
import com.nexjot.nexjot.api.repository.UserRepository;
import com.nexjot.nexjot.api.security.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Autowired
    public OAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * Registers or updates a user based on their existence in the database.
     * @param userRequest The authenticated OAuth2 user.
     * @return The principal user.
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // Load user from OAuth2 provider (Google, etc.)
        OAuth2User oAuth2User = super.loadUser(userRequest);

        try {
            return processOauth2User(oAuth2User);
        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
        }

    }

    /**
     * Processes the authenticated user
     * @param oAuth2User The authenticated user
     * @return The principal user
     */
    private OAuth2User processOauth2User(OAuth2User oAuth2User) {

        // Update user if it exists, else register user
        User user = userRepository.findByEmail(oAuth2User.getAttribute("email"))
                .map( existingUser -> updateExistingUser(existingUser, oAuth2User))
                .orElseGet(() -> registerUser(oAuth2User));

        return new AuthUser(user, oAuth2User.getAttributes());
    }

    /**
     * Registers a new user
     * @param oAuthUser The authenticated User
     * @return The created User
     */
    private User registerUser(OAuth2User oAuthUser) {
        User user = new User(oAuthUser.getAttribute("name"),
                oAuthUser.getAttribute("email"));
        return userRepository.save(user);
    }

    /**
     * Updates an existing User
     * @param user The User
     * @param oAuth2User The authenticated User
     * @return The updated User
     */
    private User updateExistingUser(User user, OAuth2User oAuth2User) {
        user.setUsername(oAuth2User.getAttribute("name"));
        return userRepository.save(user);
    }

}
