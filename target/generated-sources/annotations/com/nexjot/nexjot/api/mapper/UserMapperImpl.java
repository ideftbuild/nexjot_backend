package com.nexjot.nexjot.api.mapper;

import com.nexjot.nexjot.api.dto.user.LoginDTO;
import com.nexjot.nexjot.api.dto.user.SignUpDTO;
import com.nexjot.nexjot.api.dto.user.UserDTO;
import com.nexjot.nexjot.api.model.Document;
import com.nexjot.nexjot.api.model.User;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-24T11:24:19+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.2 (Arch Linux)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public SignUpDTO toSignUpDTO(User user) {
        if ( user == null ) {
            return null;
        }

        String username = null;
        String email = null;

        username = user.getUsername();
        email = user.getEmail();

        String password = null;

        SignUpDTO signUpDTO = new SignUpDTO( username, password, email );

        return signUpDTO;
    }

    @Override
    public User toEntity(SignUpDTO signUpDTO) {
        if ( signUpDTO == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( signUpDTO.getUsername() );
        user.setEmail( signUpDTO.getEmail() );

        return user;
    }

    @Override
    public UserDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        Set<Document> set = user.getDocuments();
        if ( set != null ) {
            userDTO.setDocuments( new LinkedHashSet<Document>( set ) );
        }
        userDTO.setUsername( user.getUsername() );
        userDTO.setCreatedAt( user.getCreatedAt() );
        userDTO.setUpdatedAt( user.getUpdatedAt() );

        return userDTO;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( userDTO.getUsername() );
        user.setCreatedAt( userDTO.getCreatedAt() );
        user.setUpdatedAt( userDTO.getUpdatedAt() );
        Set<Document> set = userDTO.getDocuments();
        if ( set != null ) {
            user.setDocuments( new LinkedHashSet<Document>( set ) );
        }

        return user;
    }

    @Override
    public LoginDTO toLoginDTO(User user) {
        if ( user == null ) {
            return null;
        }

        String username = null;

        username = user.getUsername();

        String password = null;

        LoginDTO loginDTO = new LoginDTO( username, password );

        return loginDTO;
    }

    @Override
    public User toEntity(LoginDTO loginDTO) {
        if ( loginDTO == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( loginDTO.getUsername() );

        return user;
    }
}
