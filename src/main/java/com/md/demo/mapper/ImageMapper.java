package com.md.demo.mapper;

import com.md.demo.dto.ImageDTO;
import com.md.demo.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ImageMapper {

	ImageMapper IMAGE_MAPPER = Mappers.getMapper(ImageMapper.class);

	@Mappings({
			@Mapping(target = "item", source = "image.item"),
			@Mapping(target = "name", source = "image.name")
	})
	ImageDTO toDto(Image image);

	Image toEntity(ImageDTO imageDTO);
}
