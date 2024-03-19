package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.usersEntities.User;
import com.sameh.medicory.model.users.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper  {
 public User toEntity(UserDTO userDTO);
 public UserDTO toDto(User user);

}
