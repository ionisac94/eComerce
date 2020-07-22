package com.md.demo.mapper;

import com.md.demo.dto.UserDTO;
import com.md.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

	@Mappings({
			@Mapping(target = "firstName", source = "user.firstName"),
			@Mapping(target = "lastName", source = "user.lastName")
	})
	UserDTO toDto(User user);

	User toEntity(UserDTO userDTO);
}
