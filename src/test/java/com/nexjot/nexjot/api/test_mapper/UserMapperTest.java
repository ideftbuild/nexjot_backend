//package com.nexjot.nexjot.api.test_mapper;
//
//import com.nexjot.nexjot.api.dto.user.SignUpDTO;
//import com.nexjot.nexjot.api.mapper.UserMapper;
//import com.nexjot.nexjot.api.model.User;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class UserMapperTest {
//
//    @Test
//    public void testToEntity() {
//        SignUpDTO userDTO = new SignUpDTO("username", "password", null);
//
//        User user = UserMapper.INSTANCE.toEntity(userDTO);
//
//        assertEquals(user.getUsername(), userDTO.getUsername());
//    }
//
//    @Test
//    public void testSignUpDTO() {
//        User user = new User("username",  null);
//
//        SignUpDTO userDTO = UserMapper.INSTANCE.toSignUpDTO(user);
//
//        assertEquals(userDTO.getUsername(), user.getUsername());
//    }
//}
