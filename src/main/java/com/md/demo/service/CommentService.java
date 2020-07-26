package com.md.demo.service;

import com.md.demo.model.Comment;

import java.util.List;

public interface CommentService {

	List<Comment> getAllCommentsByItemId(Integer itemId);

	List<Comment> getAllComments();

	Comment findCommentById(Integer id);

	boolean isCommentDeleted(Integer id);

	Comment addComment(Integer itemId, String comment);

	Comment createComment(Comment comment);

	void updateComment(String newContent, Integer commentId);

	Comment modifyComment(Integer itemId, Integer commentId, String comment);
}
