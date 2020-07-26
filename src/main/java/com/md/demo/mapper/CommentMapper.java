package com.md.demo.mapper;

import com.md.demo.dto.CommentDTO;
import com.md.demo.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

	CommentMapper COMMENT_MAPPER = Mappers.getMapper(CommentMapper.class);

	@Mappings({
			@Mapping(target = "item", source = "comment.item"),
			@Mapping(target = "content", source = "comment.content")
	})
	CommentDTO toDto(Comment comment);

	Comment toEntity(CommentDTO commentDTO);

	List<CommentDTO> toDtos(List<Comment> comments);

	List<Comment> toEntities(List<CommentDTO> commentDTOs);
}
