package com.md.demo.mapper;

import com.md.demo.dto.RatingDTO;
import com.md.demo.model.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RatingMapper {

	RatingMapper RATING_MAPPER = Mappers.getMapper(RatingMapper.class);

	@Mappings({
			@Mapping(target = "value", source = "rating.value"),
			@Mapping(target = "item", source = "rating.item")
	})
	RatingDTO toDto(Rating rating);

	Rating toEntity(RatingDTO ratingDTO);
}
