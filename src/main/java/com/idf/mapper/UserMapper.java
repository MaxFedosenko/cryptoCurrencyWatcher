package com.idf.mapper;

import com.idf.dto.UserDTO;
import com.idf.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    
    @Mapping(target = "userId", source = "id")
    UserDTO toUserDTO(User user);
    
    @Mapping(target = "id", source = "userId")
    User toUser(UserDTO userDTO);
    
}
