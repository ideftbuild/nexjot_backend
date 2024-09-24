package com.nexjot.nexjot.api.mapper;

import com.nexjot.nexjot.api.dto.user.LoginDTO;
import com.nexjot.nexjot.api.dto.user.SignUpDTO;
import com.nexjot.nexjot.api.dto.user.UserDTO;
import com.nexjot.nexjot.api.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    SignUpDTO toSignUpDTO(User user);
    User toEntity(SignUpDTO signUpDTO);

    @Mapping(target = "documents", source="documents")
    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);

    LoginDTO toLoginDTO(User user);
    User toEntity(LoginDTO loginDTO);
}
