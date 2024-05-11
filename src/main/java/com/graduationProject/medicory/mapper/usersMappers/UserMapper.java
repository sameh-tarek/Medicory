package com.graduationProject.medicory.mapper.usersMappers;

import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.model.users.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper  {
  User toEntity(UserDTO userDTO);
  UserDTO toDto(User user);


}
