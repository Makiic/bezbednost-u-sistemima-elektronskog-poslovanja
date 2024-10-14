package com.bsep.jwt.backend.mappers;

import com.bsep.jwt.backend.dtos.SignUpDto;
import com.bsep.jwt.backend.dtos.UserDto;
import com.bsep.jwt.backend.entites.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "role", target = "role")
    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}
