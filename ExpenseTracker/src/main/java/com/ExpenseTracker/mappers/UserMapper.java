package com.ExpenseTracker.mappers;

import com.ExpenseTracker.dtos.UserRequestDTO;
import com.ExpenseTracker.dtos.UserResponseDTO;
import com.ExpenseTracker.entities.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper{


    UserResponseDTO toDTO(Users user);

    Users toEntity(UserRequestDTO dto);

}
