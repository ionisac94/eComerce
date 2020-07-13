package com.md.demo.dto;

import com.md.demo.model.Comment;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommentDTO {

	private Integer id;

	private Integer itemId;

	private String comment;

	public CommentDTO(Comment comment) {
		this.id = comment.getId();
		this.itemId = comment.getItemId().getId();
		this.comment = comment.getComment();
	}


	public static List<CommentDTO> toCommentDTOList(List<Comment> commentList) {

		List<CommentDTO> list = new ArrayList<>();
		for (Comment comment1 : commentList) {
			CommentDTO commentDTO = new CommentDTO(comment1);
			list.add(commentDTO);
		}
		return list;
	}
}
