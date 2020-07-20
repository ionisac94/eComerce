package com.md.demo.dto;

import com.md.demo.model.Comment;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CommentDTO {

	private Integer id;

	private Integer itemId;

	private String comment;

	public CommentDTO(Comment comment) {
		this.id = comment.getId();
		this.itemId = comment.getItemId().getId();
		this.comment = comment.getContent();
	}

	public static List<CommentDTO> toCommentDTOList(List<Comment> commentList) {
		return commentList.stream().map(CommentDTO::new).collect(Collectors.toList());
	}
}
