package com.ftn.kts_nvt.helper;

import com.ftn.kts_nvt.beans.User;
import com.ftn.kts_nvt.dto.UserDTO;

public class UserMapper implements MapperInterface<User, UserDTO> {
	
    @Override
    public User toEntity(UserDTO dto) {
        return new User(dto.getFirstName(), dto.getLastName(), 
        				dto.getEmail(), dto.getPassword());
    }

    @Override
    public UserDTO toDto(User entity) {
        return new UserDTO(entity.getId(), entity.getFirstName(), entity.getLastName(),
        				   entity.getEmail(), entity.getPassword());
    }
}
