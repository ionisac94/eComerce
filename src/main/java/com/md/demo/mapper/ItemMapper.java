package com.md.demo.mapper;

import com.md.demo.dto.ItemDTO;
import com.md.demo.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

	ItemMapper ITEM_MAPPER = Mappers.getMapper(ItemMapper.class);

	@Mappings({
			@Mapping(target = "user", source = "item.user"),
			@Mapping(target = "title", source = "item.title")
	})
	ItemDTO toDto(Item item);

	Item toEntity(ItemDTO itemDTO);

	List<ItemDTO> toDtos(List<Item> items);

	List<Item> toEntities(List<ItemDTO> itemDTOs);
}
