package com.md.demo.dto;

import com.md.demo.model.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentDTO {

	private Long id;

	private Long itemId;

	private String comment;

	public CommentDTO(Comment comment) {
		this.id = comment.getId();
		this.itemId = comment.getItem().getId();
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
